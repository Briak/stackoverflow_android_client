package com.briak.stackoverflowclient.model.domain.tags

import com.briak.stackoverflowclient.entities.tag.server.TagsList
import com.briak.stackoverflowclient.model.di.tagslist.TagsListScope

@TagsListScope
interface TagsInteractor {
    suspend fun getTags(page: Int): TagsList
}