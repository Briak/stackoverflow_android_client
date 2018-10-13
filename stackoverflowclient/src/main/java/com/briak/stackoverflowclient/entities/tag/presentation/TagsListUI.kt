package com.briak.stackoverflowclient.entities.tag.presentation

data class TagsListUI(
        var items: List<TagUI>,

        var hasMore: Boolean
) {
    constructor() : this(listOf(), false)
}