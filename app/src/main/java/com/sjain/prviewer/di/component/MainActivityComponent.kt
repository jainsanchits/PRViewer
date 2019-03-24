package com.sjain.prviewer.di.component

import com.sjain.prviewer.di.module.MainActivityModule
import com.sjain.prviewer.ui.activity.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent : AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}
