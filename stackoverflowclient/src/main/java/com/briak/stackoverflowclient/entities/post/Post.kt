package com.briak.stackoverflowclient.entities.post

import java.util.*

data class Post(
        val title: String = "Same default Navigation Drawer on different Activities in Android Studio 3.2 without FrameLayout Tag in XML",
        val description: String = "My android studio project is giving me an error that prevents the app from running. It appears as though the error is coming from my grade file. Here's my code. // Top-level build file where you can ...",
        val date: Date = Date(),
        val author: String = "Manuel Miranda"
)