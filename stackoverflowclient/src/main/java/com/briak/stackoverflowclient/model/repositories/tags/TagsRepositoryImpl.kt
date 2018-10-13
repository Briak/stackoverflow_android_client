package com.briak.stackoverflowclient.model.repositories.tags

import com.briak.stackoverflowclient.entities.tag.server.TagsList
import com.briak.stackoverflowclient.entities.wikis.server.TagWikiList
import com.briak.stackoverflowclient.model.data.server.StackOverflowApi
import kotlinx.coroutines.experimental.Deferred
import javax.inject.Inject

class TagsRepositoryImpl @Inject constructor(
        private val stackOverflowApi: StackOverflowApi
) : TagsRepository {

    override fun getTags(page: Int): Deferred<TagsList> = stackOverflowApi.getTags(page)

    override fun getTagsWikis(tags: String): Deferred<TagWikiList> = stackOverflowApi.getWikis(tags)
}