package com.sjain.prviewer.di.module

import com.google.gson.Gson
import com.sjain.prviewer.di.component.MainActivityComponent
import com.sjain.prviewer.di.component.PullRequestActivityComponent
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module(subcomponents = [MainActivityComponent::class, PullRequestActivityComponent::class])
object ApplicationModule {

    @Provides
    @Reusable
    @JvmStatic
    fun provideGson(): Gson = Gson()
}
