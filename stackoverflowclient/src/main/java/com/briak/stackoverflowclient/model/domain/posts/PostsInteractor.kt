package com.briak.stackoverflowclient.model.domain.posts

import com.briak.stackoverflowclient.entities.post.server.PostsList

interface PostsInteractor {
    suspend fun getPosts(tag: String, page: Int): PostsList
}