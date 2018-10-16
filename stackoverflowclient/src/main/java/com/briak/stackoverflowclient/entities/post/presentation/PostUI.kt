package com.briak.stackoverflowclient.entities.post.presentation

data class PostUI(
        var tags: List<String>,

        var owner: OwnerUI,

        var creationDate: Long,

        var title: String,

        var body: String
) {
    constructor() : this(listOf<String>(), OwnerUI(), 0, "", "")
}