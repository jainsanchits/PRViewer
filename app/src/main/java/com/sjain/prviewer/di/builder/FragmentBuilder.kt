package com.sjain.prviewer.di.builder

import androidx.fragment.app.Fragment
import com.sjain.prviewer.di.component.PullRequestFragmentComponent
import com.sjain.prviewer.ui.fragment.PullRequestFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBuilder {

    @Binds
    @IntoMap
    @FragmentKey(PullRequestFragment::class)
    internal abstract fun bindPullRequestFragment(builder: PullRequestFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>
}
