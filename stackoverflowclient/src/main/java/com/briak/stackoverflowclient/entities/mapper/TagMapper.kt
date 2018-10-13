package com.briak.stackoverflowclient.entities.mapper

import com.briak.stackoverflowclient.entities.tag.presentation.TagUI
import com.briak.stackoverflowclient.entities.tag.server.Tag
import javax.inject.Inject

open class TagMapper @Inject constructor() : Mapper<Tag, TagUI>() {
    override fun map(value: Tag): TagUI {
        val articleUI = TagUI()
        articleUI.name = value.name
        articleUI.description = value.excerpt
        articleUI.count = value.count

        return articleUI
    }

    override fun reverseMap(value: TagUI): Tag {
        val article = Tag()
        article.name = value.name
        article.count = value.count

        return article
    }
}