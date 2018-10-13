package com.briak.stackoverflowclient.model.di.postslist

import com.arellomobile.mvp.MvpPresenter
import com.briak.stackoverflowclient.model.domain.posts.PostsInteractor
import com.briak.stackoverflowclient.model.domain.posts.PostsInteractorImpl
import com.briak.stackoverflowclient.model.repositories.posts.PostsRepository
import com.briak.stackoverflowclient.model.repositories.posts.PostsRepositoryImpl
import com.briak.stackoverflowclient.presentation.postslist.PostsListPresenter
import com.briak.stackoverflowclient.presentation.postslist.PostsListView
import dagger.Binds
import dagger.Module

@Module
abstract class PostsListModule {
    @Binds
    @PostsListScope
    abstract fun providePostsRepository(postsRepository: PostsRepositoryImpl): PostsRepository

    @Binds
    @PostsListScope
    abstract fun providePostsInteractor(postsInteractor: PostsInteractorImpl): PostsInteractor

    @Binds
    @PostsListScope
    abstract fun providePostsPresenter(postsPresenter: PostsListPresenter): MvpPresenter<PostsListView>
}