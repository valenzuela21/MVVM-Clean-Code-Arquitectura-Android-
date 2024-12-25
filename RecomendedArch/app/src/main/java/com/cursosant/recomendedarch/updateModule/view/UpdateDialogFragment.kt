package com.cursosant.recomendedarch.updateModule.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.DialogInterface.OnShowListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.cursosant.recomendedarch.common.utils.Constants
import com.cursosant.recomendedarch.updateModule.viewModel.UpdateViewModel
import com.cursosant.recomendedarch.R
import com.cursosant.recomendedarch.common.viewModel.ShareFragmentViewModel
import com.cursosant.recomendedarch.databinding.FragmentDialogUpdateBinding
import org.koin.android.ext.android.inject

class UpdateDialogFragment : DialogFragment(), OnShowListener {

    private var _binding: FragmentDialogUpdateBinding? = null
    private val binding get() = _binding!!

    private var _wineId = -1.0

    private val sVm: ShareFragmentViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val id = it.getDouble(Constants.ARG_ID, 0.0)
            _wineId = id
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentDialogUpdateBinding.inflate(layoutInflater)//LayoutInflater.from(requireContext()))

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
                binding.viewModel?.updateWine(binding.rating.rating.toString())
            }
            negativeButton.setOnClickListener { dismiss() }
        }

        setupViewModel()
        setupObservers()
        getWineById()
    }

    private fun setupViewModel() {
        val vm: UpdateViewModel by inject()
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, vm)
    }

    private fun setupObservers() {
        binding.viewModel?.let { vm ->
            vm.snackbarMsg.observe(viewLifecycleOwner) { resMsg ->
                resMsg?.let {
                    showMsg(resMsg)
                    if (resMsg == R.string.room_save_success) dismiss()
                }
            }
        }
    }

    private fun showMsg(msgRes: Int) {
        Toast.makeText(requireContext(), msgRes, Toast.LENGTH_SHORT).show()
    }

    private fun getWineById() {
        if (_wineId != -1.0)  binding.viewModel?.requestWine(_wineId)
    }


    override fun onPause() {
        super.onPause()
        binding.viewModel?.onPause()
    }
    override fun onDismiss(dialog: DialogInterface) {
        sVm.isDismiss.value = true
        super.onDismiss(dialog)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}