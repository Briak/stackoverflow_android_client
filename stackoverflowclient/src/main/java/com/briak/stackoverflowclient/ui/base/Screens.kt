package com.briak.stackoverflowclient.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import com.briak.stackoverflowclient.ui.postslist.PostsListFragment
import com.briak.stackoverflowclient.ui.tagslist.TagsListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    object TagsListScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = TagsListFragment()
    }

    data class PostsListScreen(private val tag: String): SupportAppScreen() {
        override fun getFragment(): Fragment = PostsListFragment.getInstance(tag)
    }

    companion object {
        const val ERROR_DIALOG = "error dialog"
    }
}