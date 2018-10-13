package com.briak.stackoverflowclient.presentation.postslist

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.briak.stackoverflowclient.entities.post.presentation.PostUI

interface PostsListView: MvpView {
    fun showPosts(posts: List<PostUI>)
    fun showProgress(show: Boolean)
    fun showEmpty(show: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showMessage(message: String)

    @StateStrategyType(SkipStrategy::class)
    fun startPostsJob()

    fun start()
}