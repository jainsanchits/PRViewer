package com.sjain.prviewer.di.module

import com.sjain.prviewer.di.component.PullRequestFragmentComponent
import com.sjain.prviewer.di.qualifier.Owner
import com.sjain.prviewer.di.qualifier.Repo
import com.sjain.prviewer.ui.activity.PullRequestActivity
import dagger.Module
import dagger.Provides

@Module(subcomponents = [PullRequestFragmentComponent::class])
object PullRequestActivityModule {

    @Owner
    @Provides
    @JvmStatic
    fun provideOwner(pullRequestActivity: PullRequestActivity) =
        pullRequestActivity.intent.getStringExtra(PullRequestActivity.ARG_OWNER)

    @Repo
    @Provides
    @JvmStatic
    fun provideRepo(pullRequestActivity: PullRequestActivity) =
        pullRequestActivity.intent.getStringExtra(PullRequestActivity.ARG_REPO)
}
