package com.briak.stackoverflowclient.entities.tag.server

import com.squareup.moshi.Json

data class Tag(
        @Json(name = "has_synonyms")
        var hasSynonyms: Boolean,

        @Json(name = "is_moderator_only")
        var isModeratorOnly: Boolean,

        @Json(name = "is_required")
        var isRequired: Boolean,

        var name: String,

        var count: Int,

        var excerpt: String
) {
    constructor() : this(false, false, false, "", 0, "")
}