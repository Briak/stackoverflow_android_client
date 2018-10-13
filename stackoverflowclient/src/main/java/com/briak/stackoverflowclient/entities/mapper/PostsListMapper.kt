package com.briak.stackoverflowclient.entities.mapper

import com.briak.stackoverflowclient.entities.post.presentation.PostUI
import com.briak.stackoverflowclient.entities.post.presentation.PostsListUI
import com.briak.stackoverflowclient.entities.post.server.Post
import com.briak.stackoverflowclient.entities.post.server.PostsList
import javax.inject.Inject

open class PostsListMapper @Inject constructor() : Mapper<PostsList, PostsListUI>() {
    private val postMapper = PostMapper()

    override fun map(value: PostsList): PostsListUI {
        val postsListUI = PostsListUI()
        postsListUI.hasMore = value.hasMore

        val itemsUI = mutableListOf<PostUI>()

        for (i in 0 until value.items.size) {
            itemsUI.add(postMapper.map(value.items[i]))
        }
        postsListUI.items = itemsUI

        return postsListUI
    }

    override fun reverseMap(value: PostsListUI): PostsList {
        val postsList = PostsList()
        postsList.hasMore = value.hasMore

        val items = mutableListOf<Post>()
        for (i in 0 until value.items.size) {
            items.add(postMapper.reverseMap(value.items[i]))
        }
        postsList.items = items

        return postsList
    }
}