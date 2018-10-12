package com.briak.stackoverflowclient.model.di.application

import com.briak.stackoverflowclient.model.di.postslist.PostsListComponent
import com.briak.stackoverflowclient.model.di.tagslist.TagsListComponent
import com.briak.stackoverflowclient.ui.base.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    (ApplicationModule::class),
    (NavigationModule::class),
    (NetworkModule::class)]
)
@Singleton
interface ApplicationComponent {
    fun postsListComponentBuilder(): PostsListComponent.Builder
    fun tagsListComponentBuilder(): TagsListComponent.Builder

    fun inject(activity: MainActivity)
}