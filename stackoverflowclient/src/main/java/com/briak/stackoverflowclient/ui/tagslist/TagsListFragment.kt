package com.briak.stackoverflowclient.ui.tagslist

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.briak.stackoverflowclient.R
import com.briak.stackoverflowclient.StackOverflowClientApplication
import com.briak.stackoverflowclient.entities.tag.Tag
import com.briak.stackoverflowclient.ui.base.BaseFragment
import com.briak.stackoverflowclient.ui.base.Screens
import kotlinx.android.synthetic.main.fragment_tags_list.*
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class TagsListFragment :
        BaseFragment(),
        TagAdapter.OnTagClickListener {

    @Inject
    lateinit var cicerone: Cicerone<Router>

    override val layoutRes: Int = R.layout.fragment_tags_list

    override fun onCreate(savedInstanceState: Bundle?) {
        StackOverflowClientApplication.plusTagsListComponent().inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tags = mutableListOf<Tag>()
        for (i in 0 until 20) {
            tags.add(Tag())
        }

        val itemDecorator = DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(context!!, R.drawable.decorator_listview)!!)

        tagsListView.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(itemDecorator)
            adapter = TagAdapter(tags, this@TagsListFragment)
        }

    }

    override fun onTagClick(tag: Tag) {
        cicerone.router.navigateTo(Screens.PostsListScreen)
    }
}