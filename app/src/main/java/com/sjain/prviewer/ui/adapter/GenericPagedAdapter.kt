package com.sjain.prviewer.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.sjain.prviewer.BR
import com.sjain.prviewer.model.DiffModel
import com.sjain.prviewer.ui.viewmodel.ListItemViewModel

@Suppress("UNCHECKED_CAST")
open class GenericPagedAdapter<M : ListItemViewModel<D>, VH : BindingViewHolder<T>, T : ViewDataBinding, D : DiffModel>(
    itemCallback: DiffUtil.ItemCallback<M>,
    private val layoutResourceId: Int
) : PagedListAdapter<M, VH>(itemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = DataBindingUtil.inflate<T>(LayoutInflater.from(parent.context), layoutResourceId, parent, false)
        return BindingViewHolder<T>(binding) as VH
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.setVariable(BR.viewModel, getItem(position))
        holder.binding.executePendingBindings()
    }
}
