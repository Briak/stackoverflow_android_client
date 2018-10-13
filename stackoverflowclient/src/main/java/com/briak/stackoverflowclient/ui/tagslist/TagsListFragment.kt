package com.briak.stackoverflowclient.ui.tagslist

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.briak.stackoverflowclient.R
import com.briak.stackoverflowclient.StackOverflowClientApplication
import com.briak.stackoverflowclient.entities.tag.presentation.TagUI
import com.briak.stackoverflowclient.extensions.visible
import com.briak.stackoverflowclient.presentation.tagslist.TagsListPresenter
import com.briak.stackoverflowclient.presentation.tagslist.TagsListView
import com.briak.stackoverflowclient.ui.base.BaseFragment
import com.briak.stackoverflowclient.ui.base.ErrorDialogFragment
import com.briak.stackoverflowclient.ui.base.RecyclerViewOnScrollListener
import com.briak.stackoverflowclient.ui.base.Screens
import kotlinx.android.synthetic.main.fragment_tags_list.*
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class TagsListFragment :
        BaseFragment(),
        TagsListView,
        TagAdapter.OnTagClickListener {

    @Inject
    lateinit var cicerone: Cicerone<Router>

    @Inject
    @InjectPresenter
    lateinit var presenter: TagsListPresenter

    @ProvidePresenter
    fun provideTagsListPresenter(): TagsListPresenter = presenter

    override val layoutRes: Int = R.layout.fragment_tags_list

    private var tagsJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        StackOverflowClientApplication.plusTagsListComponent().inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        tagsJob?.cancel()

        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refreshTagsListView?.apply {
            setColorSchemeResources(R.color.colorAccent)

            setOnRefreshListener {
                (tagsListView.adapter as TagAdapter).clear()
                presenter.refresh()
            }
        }

        initAdapter()
    }

    override fun showTags(tags: List<TagUI>) {
        tagsListView.apply {
            if (adapter == null) {
                adapter = TagAdapter(tags.toMutableList(), this@TagsListFragment)
            } else {
                (adapter as TagAdapter).add(tags)
            }
        }
    }

    override fun showProgress(show: Boolean) {
        launch(UI) {
            tagsListProgressView?.visible(show)
            tagsListView?.visible(!show)
            refreshTagsListView?.isRefreshing = false

            if (show) {
                emptyView?.visible(false)
            }
        }
    }

    override fun showEmpty(show: Boolean) {
        emptyView.visible(show)
        tagsListView.visible(!show)
    }

    override fun showMessage(message: String) {
        launch(UI) {
            ErrorDialogFragment.getInstance(message).show(activity!!.supportFragmentManager, Screens.ERROR_DIALOG)
            tagsListView.visible(false)
        }
    }

    override fun startTagsJob() {
        tagsJob = launch(UI) {
            presenter.getTags()
        }
    }

    override fun onTagClick(tag: TagUI) {
        cicerone.router.navigateTo(Screens.PostsListScreen(tag.name))
    }

    private fun initAdapter() {
        val itemDecorator = DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(context!!, R.drawable.decorator_listview)!!)

        val linearLayoutManager = LinearLayoutManager(activity)
        val scrollListener = object : RecyclerViewOnScrollListener(linearLayoutManager) {
            override fun onLoadMore() {
                startTagsJob()
            }
        }

        tagsListView.apply {
            layoutManager = linearLayoutManager
            addItemDecoration(itemDecorator)
            addOnScrollListener(scrollListener)
        }
    }
}