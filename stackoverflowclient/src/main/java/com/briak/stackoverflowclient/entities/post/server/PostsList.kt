package com.briak.stackoverflowclient.entities.post.server

import com.squareup.moshi.Json

data class PostsList(
        var items: List<Post>,

        @Json(name = "has_more")
        var hasMore: Boolean,

        @Json(name = "quota_max")
        var quotaMax: Int,

        @Json(name = "quota_remaining")
        var quotaRemaining: Int
) {
    constructor() : this(listOf<Post>(), false, 0, 0)
}