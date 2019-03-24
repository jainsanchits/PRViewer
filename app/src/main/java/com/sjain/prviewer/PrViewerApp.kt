package com.sjain.prviewer

import android.app.Activity
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.sjain.prviewer.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class PrViewerApp : MultiDexApplication(), HasActivityInjector{

    companion object {
        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }

    @Inject
    internal lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder()
            .applicationContext(applicationContext)
            .build()
            .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

}