package com.briak.stackoverflowclient.presentation.tagslist

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.briak.stackoverflowclient.entities.tag.presentation.TagUI

interface TagsListView: MvpView {
    fun showTags(tags: List<TagUI>)
    fun showProgress(show: Boolean)
    fun showEmpty(show: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showMessage(message: String)

    @StateStrategyType(SkipStrategy::class)
    fun startTagsJob()
}