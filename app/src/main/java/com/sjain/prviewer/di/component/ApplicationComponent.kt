package com.sjain.prviewer.di.component

import android.content.Context
import com.sjain.prviewer.PrViewerApp
import com.sjain.prviewer.di.builder.ActivityBuilder
import com.sjain.prviewer.di.builder.FragmentBuilder
import com.sjain.prviewer.di.module.ApplicationModule
import com.sjain.prviewer.di.module.NetModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [AndroidSupportInjectionModule::class, ApplicationModule::class, ActivityBuilder::class, NetModule::class, FragmentBuilder::class])
@Singleton
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun build(): ApplicationComponent
    }

    fun inject(prViewerApp: PrViewerApp)
}