package com.briak.stackoverflowclient.ui.base

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.AbsListView

abstract class RecyclerViewOnScrollListener(private val linearLayoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {
    private var loading = true

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)

        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
            loading = true
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = recyclerView.childCount
        val totalItemCount = linearLayoutManager.itemCount
        val firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition()

        if (loading && visibleItemCount + firstVisibleItem == totalItemCount - 5) {
            loading = false

            onLoadMore()
        }
    }

    abstract fun onLoadMore()
}