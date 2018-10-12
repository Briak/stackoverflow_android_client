package com.briak.stackoverflowclient.extensions

import android.view.View
import com.briak.stackoverflowclient.ui.base.JobHolder
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.actor
import java.text.SimpleDateFormat
import java.util.*

fun View.onClick(action: suspend (View) -> Unit) {
    val eventActor = actor<View>(UI, parent = contextJob, capacity = Channel.CONFLATED) {
        for (event in channel) action(event)
    }

    setOnClickListener {
        eventActor.offer(it)
    }
}

val View.contextJob: Job?
    get() = (context as? JobHolder)?.job

//TODO integrate
//val backgroundPool: CoroutineDispatcher by lazy {
//    val numProcessors = Runtime.getRuntime().availableProcessors()
//    when {
//        numProcessors <= 2 -> newFixedThreadPoolContext(2, "background")
//        else -> CommonPool
//    }
//}

fun View.visible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun Date.toShortDate(): String = SimpleDateFormat("EEE, MMMM d, yyyy", Locale("en_US")).format(this)
fun Date.toServerDate(): String = SimpleDateFormat("yyyy-MM-dd", Locale("en_US")).format(this)
fun Date.toUserDate(): String = SimpleDateFormat("MMMM d, yyyy", Locale("en_US")).format(this)

fun String?.isNotNullOrEmpty(): Boolean = this != null && this.isNotEmpty()