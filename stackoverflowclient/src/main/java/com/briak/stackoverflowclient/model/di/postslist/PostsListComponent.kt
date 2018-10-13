package com.briak.stackoverflowclient.model.di.postslist

import com.briak.stackoverflowclient.ui.postslist.PostsListFragment
import dagger.Subcomponent

@Subcomponent(modules = [(PostsListModule::class)])

@PostsListScope
interface PostsListComponent {
    fun inject(fragment: PostsListFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): PostsListComponent
    }
}
