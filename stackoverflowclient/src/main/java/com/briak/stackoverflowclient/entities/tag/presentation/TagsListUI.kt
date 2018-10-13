package com.briak.stackoverflowclient.entities.tag.presentation

data class TagsListUI(
        var items: List<TagUI>,

        var hasMore: Boolean,

        var quotaMax: Int,

        var quotaRemaining: Int
) {
    constructor() : this(listOf(), false, 0, 0)
}