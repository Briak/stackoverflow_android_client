package com.briak.stackoverflowclient.model.repositories.posts

import com.briak.stackoverflowclient.entities.post.server.PostsList
import com.briak.stackoverflowclient.model.data.server.StackOverflowApi
import kotlinx.coroutines.experimental.Deferred
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
        private val stackOverflowApi: StackOverflowApi
) : PostsRepository {
    override fun getPosts(tag: String, page: Int): Deferred<PostsList> = stackOverflowApi.getPosts(tag, page)
}