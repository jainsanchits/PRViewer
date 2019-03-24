package com.sjain.prviewer.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sjain.prviewer.R
import com.sjain.prviewer.databinding.ActivityPullRequestBinding
import com.sjain.prviewer.di.qualifier.Owner
import com.sjain.prviewer.di.qualifier.Repo
import com.sjain.prviewer.ui.fragment.PullRequestFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class PullRequestActivity : AppCompatActivity(), HasSupportFragmentInjector {

    companion object {
        const val ARG_OWNER = "owner"
        const val ARG_REPO = "repo"

        fun startYourself(context: Context, owner: String, repo: String) {
            val intent = Intent(context, PullRequestActivity::class.java).apply {
                putExtra(ARG_OWNER, owner)
                putExtra(ARG_REPO, repo)
            }
            (context as Activity).startActivity(intent)
        }
    }

    @Inject
    internal lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    @field:[Owner Inject]
    internal lateinit var owner: String
    @field:[Repo Inject]
    internal lateinit var repo: String

    private var pullRequestFragment: PullRequestFragment? = null

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityPullRequestBinding>(this, R.layout.activity_pull_request)
        binding.owner = owner
        binding.repo = repo
        setSupportActionBar(binding.toolbar)

        pullRequestFragment = supportFragmentManager.findFragmentByTag(PullRequestFragment.TAG) as? PullRequestFragment

        if (pullRequestFragment == null) {
            pullRequestFragment = PullRequestFragment.newInstance()
            supportFragmentManager.beginTransaction().run {
                add(binding.fragmentContainer.id, pullRequestFragment!!, PullRequestFragment.TAG)
                commit()
            }
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector
}
