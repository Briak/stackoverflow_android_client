package com.briak.stackoverflowclient.entities.mapper

import com.briak.stackoverflowclient.entities.post.presentation.PostUI
import com.briak.stackoverflowclient.entities.post.server.Post
import javax.inject.Inject

open class PostMapper @Inject constructor() : Mapper<Post, PostUI>() {
    private val ownerMapper = OwnerMapper()

    override fun map(value: Post): PostUI {
        val postUI = PostUI()
        postUI.tags = value.tags
        postUI.owner = ownerMapper.map(value.owner)
        postUI.viewCount = value.viewCount
        postUI.answerCount = value.answerCount
        postUI.score = value.score
        postUI.creationDate = value.creationDate
        postUI.title = value.title

        return postUI
    }

    override fun reverseMap(value: PostUI): Post {
        val post = Post()
        post.tags = value.tags
        post.owner = ownerMapper.reverseMap(value.owner)
        post.viewCount = value.viewCount
        post.answerCount = value.answerCount
        post.score = value.score
        post.creationDate = value.creationDate
        post.title = value.title

        return post
    }
}