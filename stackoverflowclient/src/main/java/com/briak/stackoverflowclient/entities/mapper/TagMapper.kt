package com.briak.stackoverflowclient.entities.mapper

import com.briak.stackoverflowclient.entities.tag.presentation.TagUI
import com.briak.stackoverflowclient.entities.tag.server.Tag
import javax.inject.Inject

open class TagMapper @Inject constructor() : Mapper<Tag, TagUI>() {
    override fun map(value: Tag): TagUI {
        val tagUI = TagUI()
        tagUI.name = value.name
        tagUI.description = value.excerpt
        tagUI.count = value.count

        return tagUI
    }

    override fun reverseMap(value: TagUI): Tag {
        val tag = Tag()
        tag.name = value.name
        tag.count = value.count

        return tag
    }
}