package com.briak.stackoverflowclient.ui.tagslist

import android.os.Bundle
import android.view.View
import com.briak.stackoverflowclient.R
import com.briak.stackoverflowclient.StackOverflowClientApplication
import com.briak.stackoverflowclient.ui.base.BaseFragment
import com.briak.stackoverflowclient.ui.base.Screens
import kotlinx.android.synthetic.main.fragment_tags_list.*
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class TagsListFragment: BaseFragment() {
    @Inject
    lateinit var cicerone: Cicerone<Router>

    override val layoutRes: Int = R.layout.fragment_tags_list

    override fun onCreate(savedInstanceState: Bundle?) {
        StackOverflowClientApplication.plusTagsListComponent().inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tagsButton.setOnClickListener {
            cicerone.router.navigateTo(Screens.PostsListScreen)
        }
    }
}