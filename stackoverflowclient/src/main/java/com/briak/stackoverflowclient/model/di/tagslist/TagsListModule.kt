package com.briak.stackoverflowclient.model.di.tagslist

import com.arellomobile.mvp.MvpPresenter
import com.briak.stackoverflowclient.model.domain.tags.TagsInteractor
import com.briak.stackoverflowclient.model.domain.tags.TagsInteractorImpl
import com.briak.stackoverflowclient.model.repositories.tags.TagsRepository
import com.briak.stackoverflowclient.model.repositories.tags.TagsRepositoryImpl
import com.briak.stackoverflowclient.presentation.tagslist.TagsListPresenter
import com.briak.stackoverflowclient.presentation.tagslist.TagsListView
import dagger.Binds
import dagger.Module

@Module
abstract class TagsListModule {
    @Binds
    @TagsListScope
    abstract fun provideTagsRepository(tagsRepository: TagsRepositoryImpl): TagsRepository

    @Binds
    @TagsListScope
    abstract fun provideTagsInteractor(tagsInteractor: TagsInteractorImpl): TagsInteractor

    @Binds
    @TagsListScope
    abstract fun provideTagsListPresenter(tagsListPresenter: TagsListPresenter): MvpPresenter<TagsListView>
}