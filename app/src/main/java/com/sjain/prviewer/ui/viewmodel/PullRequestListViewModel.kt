package com.sjain.prviewer.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.DiffUtil
import com.sjain.prviewer.R
import com.sjain.prviewer.databinding.ItemPullRequestBinding
import com.sjain.prviewer.model.PullRequest
import com.sjain.prviewer.service.AbstractPullRequestDataSource
import com.sjain.prviewer.service.PullRequestDataSource
import com.sjain.prviewer.ui.adapter.BindingViewHolder
import com.sjain.prviewer.ui.adapter.GenericPagedAdapter

class PullRequestListViewModel(
    abstractPullRequestDataSource: AbstractPullRequestDataSource
) : ListViewModel<PullRequestItemViewModel, PullRequest>() {

    companion object {
        private const val PAGE_SIZE = 10
        private const val ENABLE_PAGE_PACEHOLDERS = false
    }

    var livePullRequests: LiveData<PagedList<PullRequestItemViewModel>>

    init {
        val pagedListConfig =
            PagedList.Config.Builder()
                .setEnablePlaceholders(ENABLE_PAGE_PACEHOLDERS)
                .setInitialLoadSizeHint(PAGE_SIZE)
                .setPageSize(PAGE_SIZE)
                .build()

        livePullRequests = LivePagedListBuilder(abstractPullRequestDataSource, pagedListConfig).build()

        isLoading = Transformations
            .switchMap(abstractPullRequestDataSource.livePullRequestDataSource, PullRequestDataSource::isLoading)
    }

    val adapter = Adapter(diffUtilItemCallback)

    class Adapter(itemCallback: DiffUtil.ItemCallback<PullRequestItemViewModel>) :
        GenericPagedAdapter<PullRequestItemViewModel, BindingViewHolder<ItemPullRequestBinding>,
                ItemPullRequestBinding, PullRequest>(itemCallback, R.layout.item_pull_request)
}
