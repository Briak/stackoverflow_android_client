package com.briak.stackoverflowclient.ui.tagslist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.briak.stackoverflowclient.R
import com.briak.stackoverflowclient.entities.tag.presentation.TagUI
import com.briak.stackoverflowclient.extensions.onClick
import kotlinx.android.synthetic.main.item_tag.view.*

class TagAdapter(private val list: MutableList<TagUI>,
                 private val listener: OnTagClickListener) : RecyclerView.Adapter<TagAdapter.Holder>() {
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
        holder.itemView.onClick {
            listener.onTagClick(list[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tag, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = list.size

    fun add(tags: List<TagUI>) {
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
        fun bind(tag: TagUI) = with(itemView) {
            nameView.text = tag.name
            descriptionView.text = tag.description
            countView.text = String.format(" x %d", tag.count)
        }
    }

    interface OnTagClickListener {
        fun onTagClick(tag: TagUI)
    }
}