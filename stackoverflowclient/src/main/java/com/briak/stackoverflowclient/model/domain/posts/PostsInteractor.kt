package com.briak.stackoverflowclient.model.domain.posts

import com.briak.stackoverflowclient.entities.post.server.PostsList
import com.briak.stackoverflowclient.model.di.postslist.PostsListScope

@PostsListScope
interface PostsInteractor {
    suspend fun getPosts(tag: String, page: Int): PostsList
}