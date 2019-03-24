package com.sjain.prviewer.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import com.sjain.prviewer.model.DiffModel

abstract class ListViewModel<T : ListItemViewModel<D>, D : DiffModel> : LifeCycleAwareViewModel() {

    val diffUtilItemCallback = object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }

    }

    var isLoading: LiveData<Boolean> = MutableLiveData(false)
}
