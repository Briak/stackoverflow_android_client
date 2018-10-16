package com.briak.stackoverflowclient.presentation.tagslist

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.briak.stackoverflowclient.entities.mapper.TagsListMapper
import com.briak.stackoverflowclient.model.di.tagslist.TagsListScope
import com.briak.stackoverflowclient.model.domain.tags.TagsInteractor
import com.briak.stackoverflowclient.presentation.base.ErrorHandler
import com.briak.stackoverflowclient.ui.base.Screens
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.withContext
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
@TagsListScope
class TagsListPresenter @Inject constructor(
        private val tagsInteractor: TagsInteractor,
        private val errorHandler: ErrorHandler,
        private val tagsListMapper: TagsListMapper
) : MvpPresenter<TagsListView>() {

    @Inject
    lateinit var cicerone: Cicerone<Router>

    private var refresh = false
    private var hasMore = true
    private var page = 1

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.startTagsJob()
    }

    fun refresh() {
        refresh = true
        hasMore = true

        viewState.startTagsJob()
    }

    fun showPostsList(name: String) {
        cicerone.router.navigateTo(Screens.PostsListScreen(name))
    }

    suspend fun getTags() {
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
                    tagsInteractor.getTags(page)
                }.let { tags ->
                    viewState.showTags(tagsListMapper.map(tags).items)
                    viewState.showEmpty(tags.items.isEmpty())
                    viewState.showProgress(false)
                    viewState.showRefresh(false)

                    refresh = false
                    hasMore = tags.hasMore
                    if (tags.hasMore) {
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