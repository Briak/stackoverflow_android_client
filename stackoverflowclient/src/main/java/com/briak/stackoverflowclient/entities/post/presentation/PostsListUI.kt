package com.briak.stackoverflowclient.entities.post.presentation

data class PostsListUI(
        var items: List<PostUI>,

        var hasMore: Boolean
) {
    constructor() : this(listOf<PostUI>(), false)
}