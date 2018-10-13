package com.briak.stackoverflowclient.model.domain.tags

import com.briak.stackoverflowclient.entities.tag.server.TagsList

interface TagsInteractor {
    suspend fun getTags(page: Int): TagsList
}