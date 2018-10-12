package com.briak.stackoverflowclient.model.di.application

import android.content.Context
import com.briak.stackoverflowclient.model.di.postslist.PostsListComponent
import com.briak.stackoverflowclient.model.di.tagslist.TagsListComponent
import com.briak.stackoverflowclient.model.system.ResourceManager
import dagger.Module
import dagger.Provides
import org.jetbrains.annotations.NotNull
import javax.inject.Singleton

@Module(subcomponents = [(PostsListComponent::class), (TagsListComponent::class)])
class ApplicationModule(private val context: Context) {
    @Provides
    @NotNull
    @Singleton
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideResourceManager(context: Context) = ResourceManager(context)
}