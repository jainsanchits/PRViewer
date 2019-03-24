package com.sjain.prviewer.di.module

import com.sjain.prviewer.ui.viewmodel.MainActivityViewModel
import dagger.Module
import dagger.Provides

@Module
object MainActivityModule {

    @Provides
    @JvmStatic
    fun provideViewModel() = MainActivityViewModel()
}
