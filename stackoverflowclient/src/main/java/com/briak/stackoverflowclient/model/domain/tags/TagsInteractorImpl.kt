package com.briak.stackoverflowclient.model.domain.tags

import com.briak.stackoverflowclient.entities.tag.server.Tag
import com.briak.stackoverflowclient.entities.tag.server.TagsList
import com.briak.stackoverflowclient.entities.wikis.server.TagWiki
import com.briak.stackoverflowclient.model.di.tagslist.TagsListScope
import com.briak.stackoverflowclient.model.repositories.tags.TagsRepository
import javax.inject.Inject

@TagsListScope
class TagsInteractorImpl @Inject constructor(
        private val tagsRepository: TagsRepository
) : TagsInteractor {

    override suspend fun getTags(page: Int): TagsList {
        val tags = tagsRepository.getTags(page).await()

        val wikis = tagsRepository.getTagsWikis(buildTagsSequence(tags.items)).await().items

        return buildTags(tags, wikis)
    }

    private fun buildTagsSequence(tags: List<Tag>): String {
        val tagsBuilder = StringBuilder()

        for (i in 0 until tags.size) {
            tagsBuilder.append(tags[i].name)
            tagsBuilder.append(';')
        }

        return tagsBuilder.toString()
    }

    private fun buildTags(tags: TagsList, wikis: List<TagWiki>): TagsList {
        for (i in 0 until tags.items.size) {
            for (j in 0 until wikis.size) {
                if (tags.items[i].name == wikis[j].tagName) {
                    tags.items[i].excerpt = wikis[j].excerpt
                    break
                }
            }
        }

        return tags
    }
}