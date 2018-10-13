package com.briak.stackoverflowclient.entities.post.presentation

data class PostUI(
        var tags: List<String>,

        var owner: OwnerUI,

        var viewCount: Int,

        var answerCount: Int,

        var score: Int,

        var creationDate: Long,

        var title: String
) {
    constructor() : this(listOf<String>(), OwnerUI(), 0, 0, 0, 0, "")
}