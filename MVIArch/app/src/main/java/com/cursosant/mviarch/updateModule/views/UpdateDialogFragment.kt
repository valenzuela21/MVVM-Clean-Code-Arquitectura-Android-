package com.cursosant.mviarch.updateModule.views

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.DialogInterface.OnShowListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider

import androidx.lifecycle.lifecycleScope
import com.cursosant.mviarch.commonModule.utils.Constants
import com.cursosant.mviarch.R
import com.cursosant.mviarch.commonModule.entries.Wine
import com.cursosant.mviarch.WineApplication
import com.cursosant.mviarch.databinding.FragmentDialogUpdateBinding
import com.cursosant.mviarch.updateModule.UpdateViewModel
import com.cursosant.mviarch.updateModule.UpdateViewModelFactory
import com.cursosant.mviarch.updateModule.intent.UpdateIntent
import com.cursosant.mviarch.updateModule.model.RoomDatabase
import com.cursosant.mviarch.updateModule.model.UpdateRepository
import com.cursosant.mviarch.updateModule.model.UpdateState

import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateDialogFragment : DialogFragment(), OnShowListener {

    private var _binding: FragmentDialogUpdateBinding? = null
    private val binding get() = _binding!!

    private var _wineId = -1.0
    private lateinit var wine: Wine

    private var onUpdateListener: () -> Unit = {}

    private lateinit var vm:  UpdateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val id = it.getDouble(Constants.ARG_ID, 0.0)
            _wineId = id
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentDialogUpdateBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(requireContext()).apply {
            setTitle(R.string.dialog_update_title)
            setPositiveButton(R.string.dialog_update_ok, null)
            setNegativeButton(R.string.dialog_update_cancel, null)
            setView(binding.root)
        }

        val dialog = builder.create()
        dialog.setOnShowListener(this)

        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) = binding.root

    override fun onShow(dialogInterface: DialogInterface?) {
        val dialog = dialog as? AlertDialog
        dialog?.let {
            val positiveButton = it.getButton(DialogInterface.BUTTON_POSITIVE)
            val negativeButton = it.getButton(DialogInterface.BUTTON_NEGATIVE)
            positiveButton.setOnClickListener {
                showProgress(true)
                lifecycleScope.launch(Dispatchers.IO) {
                    wine.rating.average = binding.rating.rating.toString()
                    vm.channel.send(UpdateIntent.UpdateWine(wine))

                }
            }
            negativeButton.setOnClickListener { dismiss() }
        }
        setupViewModel()
        setupObservers()
        getWineById()
    }

    private fun setupViewModel() {
       vm = ViewModelProvider(this, UpdateViewModelFactory(UpdateRepository(RoomDatabase())))[UpdateViewModel::class.java]
    }

    private fun showMsg(msgRes: Int) {
        Toast.makeText(requireContext(), msgRes, Toast.LENGTH_SHORT).show()
    }

    private fun showProgress(isVisible: Boolean) {
        binding.contentProgress.root.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun getWineById() {
        lifecycleScope.launch(Dispatchers.IO) {
            vm.channel.send(UpdateIntent.RequestWine(_wineId))
        }
    }

    private fun setupObservers(){
            lifecycleScope.launch {
                vm.state.collect{state ->
                    when(state) {
                        is UpdateState.Init -> {}
                        is UpdateState.ShowProgress ->  showProgress(true)
                        is UpdateState.HideProgress ->  showProgress(false)
                        is UpdateState.RequestWineSuccess -> {
                            wine =  state.wine
                            setupUI()
                        }
                        is UpdateState.UpdateWineSuccess -> {
                            showMsg(R.string.room_save_success)
                            dismiss()
                        }
                        is UpdateState.Fail -> showMsg(state.msgRes)
                    }
                }
            }
    }

    private fun setupUI() {
        binding.tvWine.text = wine.wine
        binding.rating.rating = wine.rating.average.toFloat()
    }

    fun setOnUpdateListener(block: () -> Unit) {
        onUpdateListener = block
    }

    override fun onDismiss(dialog: DialogInterface) {
        onUpdateListener()
        super.onDismiss(dialog)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}