package com.briak.stackoverflowclient.model.domain.tags

import com.briak.stackoverflowclient.entities.tag.server.TagsList
import com.briak.stackoverflowclient.model.repositories.tags.TagsRepository
import java.lang.StringBuilder
import javax.inject.Inject

class TagsInteractorImpl @Inject constructor(
        private val tagsRepository: TagsRepository
) : TagsInteractor {

    override suspend fun getTags(page: Int): TagsList {
        val tags = tagsRepository.getTags(page).await()

        val tagsBuilder = StringBuilder()
        for (i in 0 until tags.items.size) {
            tagsBuilder.append(tags.items[i].name)
            tagsBuilder.append(';')
        }
        val wikis = tagsRepository.getTagsWikis(tagsBuilder.toString()).await().items
        for (i in 0 until tags.items.size) {
            for (j in 0 until wikis.size) {
                if (tags.items[i].name == wikis[j].tagName) {
                    tags.items[i].excerpt = wikis[j].excerpt
                    continue
                }
            }
        }

        return tags
    }
}