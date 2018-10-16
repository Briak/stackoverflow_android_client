package com.briak.stackoverflowclient.model.repositories.posts

import com.briak.stackoverflowclient.entities.post.server.PostsList
import com.briak.stackoverflowclient.model.di.postslist.PostsListScope
import kotlinx.coroutines.experimental.Deferred

@PostsListScope
interface PostsRepository {
    fun getPosts(tag: String, page: Int): Deferred<PostsList>
}