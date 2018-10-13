package com.briak.stackoverflowclient.entities.tag.server

import com.squareup.moshi.Json

data class TagsList(
        var items: List<Tag>,

        @Json(name = "has_more")
        var hasMore: Boolean,

        @Json(name = "quota_max")
        var quotaMax: Int,

        @Json(name = "quota_remaining")
        var quotaRemaining: Int

) {
        constructor() : this(listOf(), false, 0, 0)
}