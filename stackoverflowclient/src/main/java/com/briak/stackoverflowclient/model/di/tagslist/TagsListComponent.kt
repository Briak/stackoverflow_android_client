package com.briak.stackoverflowclient.model.di.tagslist

import com.briak.stackoverflowclient.ui.tagslist.TagsListFragment
import dagger.Subcomponent

@Subcomponent(modules = [(TagsListModule::class)])

@TagsListScope
interface TagsListComponent {
    fun inject(fragment: TagsListFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): TagsListComponent
    }
}
