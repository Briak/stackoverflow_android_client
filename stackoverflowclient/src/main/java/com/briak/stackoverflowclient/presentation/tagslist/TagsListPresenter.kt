package com.briak.stackoverflowclient.presentation.tagslist

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.briak.stackoverflowclient.entities.mapper.TagsListMapper
import com.briak.stackoverflowclient.model.domain.tags.TagsInteractor
import com.briak.stackoverflowclient.presentation.base.ErrorHandler
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.withContext
import javax.inject.Inject

@InjectViewState
class TagsListPresenter @Inject constructor(
        private val tagsInteractor: TagsInteractor,
        private val errorHandler: ErrorHandler,
        private val tagsListMapper: TagsListMapper
) : MvpPresenter<TagsListView>() {
    private var refresh: Boolean = false

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.startTagsJob()
    }

    fun refresh() {
        refresh = true
        viewState.startTagsJob()
    }

    suspend fun getTags() {
        viewState.showProgress(!refresh)

        try {
            withContext(CommonPool) {
                tagsInteractor.getTags(1)
            }.let { tags ->
                viewState.showTags(tagsListMapper.map(tags).items)
                viewState.showEmpty(tags.items.isEmpty())
                viewState.showProgress(false)

                refresh = false
            }
        } catch (e: Throwable) {
            viewState.showMessage(errorHandler.proceed(e))

            refresh = false
        }
    }
}