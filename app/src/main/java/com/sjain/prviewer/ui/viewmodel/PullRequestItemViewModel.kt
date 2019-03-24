package com.sjain.prviewer.ui.viewmodel

import com.sjain.prviewer.model.PullRequest


class PullRequestItemViewModel(val pullRequest: PullRequest) : ListItemViewModel<PullRequest>() {

    override val id: String = PullRequestItemViewModel::class.java.simpleName

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (javaClass != other?.javaClass) return false

        return id == (other as PullRequestItemViewModel).id
    }

    override fun hashCode(): Int = id.hashCode()
}
