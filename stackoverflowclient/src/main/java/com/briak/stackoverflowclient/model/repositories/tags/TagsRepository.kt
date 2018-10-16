package com.briak.stackoverflowclient.model.repositories.tags

import com.briak.stackoverflowclient.entities.tag.server.TagsList
import com.briak.stackoverflowclient.entities.wikis.server.TagWikiList
import com.briak.stackoverflowclient.model.di.tagslist.TagsListScope
import kotlinx.coroutines.experimental.Deferred

@TagsListScope
interface TagsRepository {
    fun getTags(page: Int): Deferred<TagsList>
    fun getTagsWikis(tags: String): Deferred<TagWikiList>
}