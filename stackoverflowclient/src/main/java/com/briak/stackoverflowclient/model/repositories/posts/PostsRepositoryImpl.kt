package com.briak.stackoverflowclient.model.repositories.posts

import com.briak.stackoverflowclient.entities.post.server.PostsList
import com.briak.stackoverflowclient.model.data.server.StackOverflowApi
import com.briak.stackoverflowclient.model.di.postslist.PostsListScope
import kotlinx.coroutines.experimental.Deferred
import javax.inject.Inject

@PostsListScope
class PostsRepositoryImpl @Inject constructor(
        private val stackOverflowApi: StackOverflowApi
) : PostsRepository {
    override fun getPosts(tag: String, page: Int): Deferred<PostsList> = stackOverflowApi.getPosts(tag, page)
}