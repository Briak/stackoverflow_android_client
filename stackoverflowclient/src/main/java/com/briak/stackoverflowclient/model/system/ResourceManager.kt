package com.briak.stackoverflowclient.model.system

import android.content.Context
import javax.inject.Inject

open class ResourceManager @Inject constructor(private val context: Context) {
    fun getString(id: Int): String = context.getString(id)
}