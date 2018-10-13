package com.briak.stackoverflowclient.entities.tag.presentation

data class TagUI(
        var name: String,

        var description: String,

        var count: Int
) {
    constructor() : this("", "", 0)
}