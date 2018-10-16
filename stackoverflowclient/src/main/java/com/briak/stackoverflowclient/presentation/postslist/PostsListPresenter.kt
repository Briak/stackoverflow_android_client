package com.briak.stackoverflowclient.presentation.postslist

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.briak.stackoverflowclient.entities.mapper.PostsListMapper
import com.briak.stackoverflowclient.model.di.postslist.PostsListScope
import com.briak.stackoverflowclient.model.domain.posts.PostsInteractor
import com.briak.stackoverflowclient.presentation.base.ErrorHandler
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.withContext
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@PostsListScope
@InjectViewState
class PostsListPresenter @Inject constructor(
        private val postsInteractor: PostsInteractor,
        private val errorHandler: ErrorHandler,
        private val postsListMapper: PostsListMapper
) : MvpPresenter<PostsListView>() {

    @Inject
    lateinit var cicerone: Cicerone<Router>

    private var refresh = false
    private var hasMore = true
    private var page = 1
    private var tag = ""

    fun back() {
        cicerone.router.exit()
    }

    fun setTag(tag: String) {
        this.tag = tag
        viewState.showTag(tag)

        viewState.startPostsJob()
    }

    fun refresh() {
        refresh = true
        hasMore = true

        viewState.startPostsJob()
    }

    suspend fun getPosts() {
        if (hasMore) {
            if (page == 1) {
                viewState.showProgress(true)
            }
            if (refresh) {
                viewState.showRefresh(true)
                page = 1
            }

            try {
                withContext(CommonPool) {
                    postsInteractor.getPosts(tag, page)
                }.let { posts ->
                    viewState.showPosts(postsListMapper.map(posts).items)
                    viewState.showEmpty(posts.items.isEmpty())
                    viewState.showProgress(false)
                    viewState.showRefresh(false)

                    refresh = false
                    hasMore = posts.hasMore
                    if (posts.hasMore) {
                        page++
                    }
                }
            } catch (e: Throwable) {
                viewState.showMessage(errorHandler.proceed(e))
                viewState.showProgress(false)

                refresh = false
            }
        }
    }
}