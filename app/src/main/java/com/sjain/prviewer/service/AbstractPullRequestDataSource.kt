package com.sjain.prviewer.service

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import com.sjain.prviewer.ui.viewmodel.PullRequestItemViewModel
import io.reactivex.disposables.CompositeDisposable

@AutoFactory
class AbstractPullRequestDataSource(
    @Provided pullRequestServiceFactory: PullRequestServiceFactory,
    owner: String,
    repo: String
) : DataSource.Factory<Int, PullRequestItemViewModel>() {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val pullRequestService: PullRequestService by lazy {
        pullRequestServiceFactory.create(owner, repo)
    }
    var livePullRequestDataSource = MutableLiveData<PullRequestDataSource>()

    override fun create(): DataSource<Int, PullRequestItemViewModel> {
        val pullRequestDataSource = PullRequestDataSource(pullRequestService, compositeDisposable)
        livePullRequestDataSource.postValue(pullRequestDataSource)
        return pullRequestDataSource
    }
}
