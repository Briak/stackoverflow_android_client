package com.briak.stackoverflowclient.model.domain.posts

import com.briak.stackoverflowclient.entities.post.server.PostsList
import com.briak.stackoverflowclient.model.repositories.posts.PostsRepository
import javax.inject.Inject

class PostsInteractorImpl @Inject constructor(
        private val postsRepository: PostsRepository
) : PostsInteractor {
    override suspend fun getPosts(tag: String, page: Int): PostsList =
            postsRepository.getPosts(tag, page).await()
}