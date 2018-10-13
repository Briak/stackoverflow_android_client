package com.briak.stackoverflowclient.entities.mapper

import com.briak.stackoverflowclient.entities.tag.presentation.TagUI
import com.briak.stackoverflowclient.entities.tag.presentation.TagsListUI
import com.briak.stackoverflowclient.entities.tag.server.Tag
import com.briak.stackoverflowclient.entities.tag.server.TagsList
import javax.inject.Inject

open class TagsListMapper @Inject constructor() : Mapper<TagsList, TagsListUI>() {
    override fun map(value: TagsList): TagsListUI {
        val tagsListUI = TagsListUI()
        tagsListUI.hasMore = value.hasMore
        tagsListUI.quotaMax = value.quotaMax
        tagsListUI.quotaRemaining = value.quotaRemaining

        val itemsUI = mutableListOf<TagUI>()
        val tagUIMapper = TagMapper()
        for (i in 0 until value.items.size) {
            itemsUI.add(tagUIMapper.map(value.items[i]))
        }
        tagsListUI.items = itemsUI

        return tagsListUI
    }

    override fun reverseMap(value: TagsListUI): TagsList {
        val tagsList = TagsList()
        tagsList.hasMore = value.hasMore
        tagsList.quotaMax = value.quotaMax
        tagsList.quotaRemaining = value.quotaRemaining

        val items = mutableListOf<Tag>()
        val tagMapper = TagMapper()
        for (i in 0 until value.items.size) {
            items.add(tagMapper.reverseMap(value.items[i]))
        }
        tagsList.items = items

        return tagsList
    }
}