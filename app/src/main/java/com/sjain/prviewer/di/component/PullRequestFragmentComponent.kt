package com.sjain.prviewer.di.component

import com.sjain.prviewer.di.module.PullRequestFragmentModule
import com.sjain.prviewer.ui.fragment.PullRequestFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [PullRequestFragmentModule::class])
interface PullRequestFragmentComponent : AndroidInjector<PullRequestFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<PullRequestFragment>()
}
