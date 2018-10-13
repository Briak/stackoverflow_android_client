package com.briak.stackoverflowclient.entities.post.server

import com.squareup.moshi.Json

data class Post(
        var tags: List<String>,

        var owner: Owner,

        @Json(name = "is_answered")
        var isAnswered: Boolean,

        @Json(name = "view_count")
        var viewCount: Int,

        @Json(name = "answer_count")
        var answerCount: Int,

        var score: Int,

        @Json(name = "last_activity_date")
        var lastActivityDate: Long,

        @Json(name = "creation_date")
        var creationDate: Long,

        @Json(name = "question_id")
        var questionId: Long,

        var link: String,

        var title: String
) {
    constructor() : this(listOf<String>(), Owner(), false, 0, 0, 0,
            0, 0, 0, "", "")
}