package com.sjain.prviewer.di.module

import com.sjain.prviewer.di.qualifier.Owner
import com.sjain.prviewer.di.qualifier.Repo
import com.sjain.prviewer.service.AbstractPullRequestDataSourceFactory
import com.sjain.prviewer.ui.fragment.PullRequestFragment
import com.sjain.prviewer.ui.viewmodel.PullRequestListViewModel
import dagger.Module
import dagger.Provides

@Module
object PullRequestFragmentModule {

    @Provides
    @JvmStatic
    fun providePullRequestFragment(fragment: PullRequestFragment) = fragment

    @Provides
    @JvmStatic
    fun provideViewModel(
        abstractPullRequestDataSourceFactory: AbstractPullRequestDataSourceFactory,
        @Owner owner: String,
        @Repo repo: String
    ) =
        PullRequestListViewModel(abstractPullRequestDataSourceFactory.create(owner, repo))
}
