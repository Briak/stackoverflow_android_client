package com.briak.stackoverflowclient.ui.postslist

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.briak.stackoverflowclient.R
import com.briak.stackoverflowclient.StackOverflowClientApplication
import com.briak.stackoverflowclient.entities.post.presentation.PostUI
import com.briak.stackoverflowclient.extensions.onClick
import com.briak.stackoverflowclient.extensions.visible
import com.briak.stackoverflowclient.presentation.postslist.PostsListPresenter
import com.briak.stackoverflowclient.presentation.postslist.PostsListView
import com.briak.stackoverflowclient.ui.base.BaseFragment
import com.briak.stackoverflowclient.ui.base.ErrorDialogFragment
import com.briak.stackoverflowclient.ui.base.RecyclerViewOnScrollListener
import com.briak.stackoverflowclient.ui.base.Screens
import kotlinx.android.synthetic.main.fragment_posts_list.*
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class PostsListFragment :
        BaseFragment(),
        PostsListView {

    @Inject
    @InjectPresenter
    lateinit var presenter: PostsListPresenter

    @ProvidePresenter
    fun providePostsListPresenter(): PostsListPresenter = presenter

    override val layoutRes: Int = R.layout.fragment_posts_list

    private var postsJob: Job? = null

    companion object {
        fun getInstance(tag: String): PostsListFragment {
            val fragment = PostsListFragment()
            val bundle = Bundle()
            bundle.putString("TAG", tag)
            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        StackOverflowClientApplication.plusPostsListComponent().inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        postsJob?.cancel()
        StackOverflowClientApplication.clearPostsListComponent()

        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refreshPostsListView?.apply {
            setColorSchemeResources(R.color.colorAccent)

            setOnRefreshListener {
                (postsListView.adapter as PostAdapter).clear()
                presenter.refresh()
            }
        }

        initAdapter()

        presenter.setTag(arguments?.getString("TAG")!!)

        postsListBackView?.onClick {
            presenter.back()
        }
    }

    override fun showPosts(posts: List<PostUI>) {
        postsListView.apply {
            if (adapter == null) {
                adapter = PostAdapter(posts.toMutableList())
            } else {
                (adapter as PostAdapter).add(posts)
            }
        }
    }

    override fun showProgress(show: Boolean) {
        launch(UI) {
            postsListProgressView.visible(show)
            postsListView.visible(!show)
            refreshPostsListView.isRefreshing = false
            emptyView.visible(false)
        }
    }

    override fun showEmpty(show: Boolean) {
        emptyView.visible(show)
        postsListView.visible(!show)
    }

    override fun showRefresh(show: Boolean) {
        launch(UI) {
            postsListProgressView.visible(false)
            postsListView.visible(!show)
            emptyView.visible(false)
        }
    }

    override fun showTag(tag: String) {
        postsListToolbarTitle.text = String.format("Questions tagged [$tag]")
    }

    override fun showMessage(message: String) {
        launch(UI) {
            ErrorDialogFragment.getInstance(message).show(activity!!.supportFragmentManager, Screens.ERROR_DIALOG)
            postsListView.visible(false)
        }
    }

    override fun startPostsJob() {
        postsJob = launch(UI) {
            presenter.getPosts()
        }
    }

    private fun initAdapter() {
        val itemDecorator = DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(context!!, R.drawable.decorator_listview)!!)

        val linearLayoutManager = LinearLayoutManager(activity)
        val scrollListener = object : RecyclerViewOnScrollListener(linearLayoutManager) {
            override fun onLoadMore() {
                startPostsJob()
            }
        }

        postsListView.apply {
            layoutManager = linearLayoutManager
            addItemDecoration(itemDecorator)
            adapter = PostAdapter(mutableListOf())
            addOnScrollListener(scrollListener)
        }
    }
}