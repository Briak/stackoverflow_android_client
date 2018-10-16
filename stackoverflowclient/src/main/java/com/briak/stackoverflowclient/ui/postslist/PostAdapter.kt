package com.briak.stackoverflowclient.ui.postslist

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.text.format.DateUtils
import android.text.format.DateUtils.MINUTE_IN_MILLIS
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.briak.stackoverflowclient.R
import com.briak.stackoverflowclient.entities.post.presentation.PostUI
import kotlinx.android.synthetic.main.item_post.view.*
import java.util.*

class PostAdapter(private val list: MutableList<PostUI>) : RecyclerView.Adapter<PostAdapter.Holder>() {
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = list.size

    fun add(tags: List<PostUI>) {
        for (i in 0 until tags.size) {
            if (!list.contains(tags[i])) {
                list.add(tags[i])
            }
        }
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: PostUI) = with(itemView) {
            titleView.text = post.title
            descriptionView.text = Html.fromHtml(post.body)
            dateView.text = String.format("asked %s",
                    DateUtils.getRelativeTimeSpanString(
                            post.creationDate * 1000,
                            Date().time, MINUTE_IN_MILLIS))
            authorView.text = post.owner.displayName
        }
    }
}