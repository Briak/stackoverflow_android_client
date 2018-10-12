package com.briak.stackoverflowclient.model.di.postslist

import dagger.Subcomponent

@Subcomponent(modules = [(PostsListModule::class)])

@PostsListScope
interface PostsListComponent {
//    fun inject(fragment: NewsDetailFragment)
//    fun inject(fragment: TopNewsFragment)
//    fun inject(fragment: CategoriesFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): PostsListComponent
    }
}
