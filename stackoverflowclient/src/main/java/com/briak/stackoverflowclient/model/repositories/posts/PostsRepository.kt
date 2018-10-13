package com.briak.stackoverflowclient.model.repositories.posts

import com.briak.stackoverflowclient.entities.post.server.PostsList
import kotlinx.coroutines.experimental.Deferred

interface PostsRepository {
    fun getPosts(tag: String, page: Int): Deferred<PostsList>
}