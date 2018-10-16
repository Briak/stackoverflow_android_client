package com.briak.stackoverflowclient.presentation.tagslist

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.*
import com.briak.stackoverflowclient.entities.tag.presentation.TagUI
import com.briak.stackoverflowclient.model.di.tagslist.TagsListScope

@TagsListScope
@StateStrategyType(AddToEndSingleStrategy::class)
interface TagsListView: MvpView {
    fun showProgress(show: Boolean)
    fun showRefresh(show: Boolean)
    fun showEmpty(show: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showMessage(message: String)

    @StateStrategyType(SkipStrategy::class)
    fun startTagsJob()

    @StateStrategyType(AddToEndStrategy::class)
    fun showTags(tags: List<TagUI>)

}