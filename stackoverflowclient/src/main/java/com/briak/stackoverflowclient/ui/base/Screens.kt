package com.briak.stackoverflowclient.ui.base

import android.support.v4.app.Fragment
import com.briak.stackoverflowclient.ui.postslist.PostsListFragment
import com.briak.stackoverflowclient.ui.tagslist.TagsListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    object TagsListScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = TagsListFragment()
    }

    object PostsListScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = PostsListFragment()
    }
}