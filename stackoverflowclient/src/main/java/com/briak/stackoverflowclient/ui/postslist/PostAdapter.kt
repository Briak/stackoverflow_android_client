package com.briak.stackoverflowclient.ui.postslist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.briak.stackoverflowclient.R
import com.briak.stackoverflowclient.entities.post.presentation.PostUI
import kotlinx.android.synthetic.main.item_post.view.*

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
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: PostUI) = with(itemView) {
            titleView.text = post.title
            descriptionView.text = "akjdaqkj ajcac"
//            dateView.text = post.date.toShortDate()
            authorView.text = post.owner.displayName
        }
    }
}