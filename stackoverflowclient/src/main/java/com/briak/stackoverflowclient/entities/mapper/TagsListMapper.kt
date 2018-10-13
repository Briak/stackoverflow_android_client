package com.briak.stackoverflowclient.entities.mapper

import com.briak.stackoverflowclient.entities.tag.presentation.TagUI
import com.briak.stackoverflowclient.entities.tag.presentation.TagsListUI
import com.briak.stackoverflowclient.entities.tag.server.Tag
import com.briak.stackoverflowclient.entities.tag.server.TagsList
import javax.inject.Inject

open class TagsListMapper @Inject constructor() : Mapper<TagsList, TagsListUI>() {
    private val tagMapper = TagMapper()

    override fun map(value: TagsList): TagsListUI {
        val tagsListUI = TagsListUI()
        tagsListUI.hasMore = value.hasMore

        val itemsUI = mutableListOf<TagUI>()

        for (i in 0 until value.items.size) {
            itemsUI.add(tagMapper.map(value.items[i]))
        }
        tagsListUI.items = itemsUI

        return tagsListUI
    }

    override fun reverseMap(value: TagsListUI): TagsList {
        val tagsList = TagsList()
        tagsList.hasMore = value.hasMore

        val items = mutableListOf<Tag>()
        for (i in 0 until value.items.size) {
            items.add(tagMapper.reverseMap(value.items[i]))
        }
        tagsList.items = items

        return tagsList
    }
}