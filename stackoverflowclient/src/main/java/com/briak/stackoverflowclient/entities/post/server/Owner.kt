package com.briak.stackoverflowclient.entities.post.server

import com.squareup.moshi.Json

data class Owner(
        val reputation: Int,

        @Json(name = "user_id")
        val userId: Long,

        @Json(name = "user_type")
        val userType: String,

        @Json(name = "accept_rate")
        val acceptRate: Int,

        @Json(name = "profile_image")
        var profileImage: String,

        @Json(name = "display_name")
        var displayName: String,

        @Json(name = "link")
        val link: String
) {
    constructor() : this(0, 0, "", 0, "", "", "")
}