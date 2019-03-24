package com.sjain.prviewer.ui.viewmodel

import android.content.Context
import androidx.databinding.ObservableField
import com.sjain.prviewer.ui.activity.PullRequestActivity

class MainActivityViewModel : LifeCycleAwareViewModel() {

    val owner: ObservableField<String> = ObservableField("")
    val repo: ObservableField<String> = ObservableField("")

    fun showPullRequests(context: Context) {
        PullRequestActivity.startYourself(context, owner.get()!!, repo.get()!!)
    }
}