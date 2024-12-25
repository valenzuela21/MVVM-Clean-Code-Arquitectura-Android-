package com.cursosant.clean.common

import com.cursosant.clean.mainModule.model.DataSource
import com.cursosant.clean.mainModule.model.DataSourceImpl
import com.cursosant.clean.mainModule.model.MainRepositoryImpl
import com.cursosant.clean.mainModule.model.Mainrepository
import com.cursosant.clean.mainModule.presenter.MainPresenter
import com.cursosant.clean.mainModule.presenter.MainPresenterImpl
import com.cursosant.clean.mainModule.view.MainView
import com.cursosant.clean.mainModule.view.OnClickListener
import com.cursosant.clean.mainModule.view.ResultAdapter
import org.koin.dsl.module

fun provideMainRepository(dataSource: DataSource): Mainrepository = MainRepositoryImpl(dataSource)
fun providerDataSource(): DataSource = DataSourceImpl()

val mainModule = module{
    factory<ResultAdapter> {(listener: OnClickListener) -> ResultAdapter(listener) }
    single { providerDataSource() }
    single { provideMainRepository(get()) }
    factory<MainPresenter> {(view: MainView) -> MainPresenterImpl(view, get()) }
}