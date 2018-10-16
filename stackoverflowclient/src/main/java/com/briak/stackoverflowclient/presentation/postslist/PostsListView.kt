package com.briak.stackoverflowclient.presentation.postslist

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.briak.stackoverflowclient.entities.post.presentation.PostUI
import com.briak.stackoverflowclient.model.di.postslist.PostsListScope

@PostsListScope
@StateStrategyType(AddToEndSingleStrategy::class)
interface PostsListView: MvpView {
    fun showPosts(posts: List<PostUI>)
    fun showProgress(show: Boolean)
    fun showEmpty(show: Boolean)
    fun showRefresh(show: Boolean)
    fun showTag(tag: String)
    fun startPostsJob()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showMessage(message: String)
}