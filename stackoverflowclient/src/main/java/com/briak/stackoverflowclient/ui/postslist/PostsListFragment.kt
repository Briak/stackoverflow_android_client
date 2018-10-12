package com.briak.stackoverflowclient.ui.postslist

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.briak.stackoverflowclient.R
import com.briak.stackoverflowclient.entities.post.Post
import com.briak.stackoverflowclient.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_posts_list.*

class PostsListFragment:
        BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_posts_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tags = mutableListOf<Post>()
        for (i in 0 until 20) {
            tags.add(Post())
        }

        val itemDecorator = DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(context!!, R.drawable.decorator_listview)!!)

        postsListView.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(itemDecorator)
            adapter = PostAdapter(tags)
        }
    }

}