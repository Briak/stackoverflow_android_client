package com.briak.stackoverflowclient.entities.wikis.server

import com.squareup.moshi.Json

data class TagWiki(
        @Json(name = "excerpt_last_edit_date")
        val excerptLastEditDate: Long,

        @Json(name = "body_last_edit_date")
        val bodyLastEditDate: Long,

        val excerpt: String,

        @Json(name = "tag_name")
        val tagName: String
)