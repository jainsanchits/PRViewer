package com.sjain.prviewer.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sjain.prviewer.R
import com.sjain.prviewer.databinding.FragmentPullRequestBinding
import com.sjain.prviewer.ui.viewmodel.PullRequestListViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PullRequestFragment : Fragment() {

    companion object {
        val TAG: String = PullRequestFragment::class.java.simpleName
        private const val ARG_SCROLL_Y = "scroll_y"

        fun newInstance(): PullRequestFragment =
            PullRequestFragment()
    }

    @Inject
    internal lateinit var pullRequestListViewModel: PullRequestListViewModel

    private lateinit var binding: FragmentPullRequestBinding
    private var scrollPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        retainInstance = true
        lifecycle.addObserver(pullRequestListViewModel)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_pull_request, container, false)
        binding.viewModel = pullRequestListViewModel
        binding.lifecycleOwner = this
        setupRecyclerView()
        observeData()
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.pullRequestListView.layoutManager = LinearLayoutManager(binding.pullRequestListView.context)
        binding.pullRequestListView.adapter = pullRequestListViewModel.adapter
    }

    private fun observeData() {
        pullRequestListViewModel.livePullRequests.observe(this, Observer { newList ->
            pullRequestListViewModel.adapter.submitList(newList)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scrollPosition = savedInstanceState?.getInt(ARG_SCROLL_Y) ?: 0
        binding.pullRequestListView.smoothScrollBy(0, scrollPosition)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(ARG_SCROLL_Y, binding.pullRequestListView.scrollY)
        binding.pullRequestListView.scrollY
        super.onSaveInstanceState(outState)
    }
}
