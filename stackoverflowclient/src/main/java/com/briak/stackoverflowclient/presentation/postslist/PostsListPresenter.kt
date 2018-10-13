package com.briak.stackoverflowclient.presentation.postslist

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.briak.stackoverflowclient.entities.mapper.PostsListMapper
import com.briak.stackoverflowclient.model.domain.posts.PostsInteractor
import com.briak.stackoverflowclient.presentation.base.ErrorHandler
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.withContext
import javax.inject.Inject

@InjectViewState
class PostsListPresenter @Inject constructor(
        private val postsInteractor: PostsInteractor,
        private val errorHandler: ErrorHandler,
        private val postsListMapper: PostsListMapper
) : MvpPresenter<PostsListView>() {

    private var refresh = false
    private var hasMore = true
    private var page = 1
    private var tag = ""

    fun setTag(tag: String) {
        this.tag = tag

        viewState.start()
    }

    fun refresh() {
        refresh = true
        hasMore = true
        page = 1

        viewState.startPostsJob()
    }

    suspend fun getPosts() {
        if (hasMore) {
            viewState.showProgress(page == 1 || refresh)

            try {
                withContext(CommonPool) {
                    postsInteractor.getPosts(tag, page)
                }.let { posts ->
                    viewState.showPosts(postsListMapper.map(posts).items)
                    viewState.showEmpty(posts.items.isEmpty())
                    viewState.showProgress(false)

                    refresh = false
                    hasMore = posts.hasMore
                    if (posts.hasMore) {
                        page++
                    }
                }
            } catch (e: Throwable) {
                viewState.showMessage(errorHandler.proceed(e))

                refresh = false
            }
        }
    }
}