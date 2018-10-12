package com.briak.stackoverflowclient.ui.postslist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.briak.stackoverflowclient.R
import com.briak.stackoverflowclient.entities.post.Post
import com.briak.stackoverflowclient.extensions.toShortDate
import kotlinx.android.synthetic.main.item_post.view.*

class PostAdapter(private val list: List<Post>) : RecyclerView.Adapter<PostAdapter.Holder>() {
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = list.size

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post) = with(itemView) {
            titleView.text = post.title
            descriptionView.text = post.description
            dateView.text = post.date.toShortDate()
            authorView.text = post.author
        }
    }
}