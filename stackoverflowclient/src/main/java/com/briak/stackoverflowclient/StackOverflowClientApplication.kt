package com.briak.stackoverflowclient

import android.app.Application
import com.briak.stackoverflowclient.model.di.application.ApplicationComponent
import com.briak.stackoverflowclient.model.di.application.ApplicationModule
import com.briak.stackoverflowclient.model.di.application.DaggerApplicationComponent
import com.briak.stackoverflowclient.model.di.postslist.PostsListComponent
import com.briak.stackoverflowclient.model.di.tagslist.TagsListComponent

class StackOverflowClientApplication : Application() {
    companion object {
        lateinit var component: ApplicationComponent
        private var tagsListComponent: TagsListComponent? = null
        private var postsListComponent: PostsListComponent? = null

        fun plusTagsListComponent(): TagsListComponent {
            if (tagsListComponent == null) {
                tagsListComponent = component
                        .tagsListComponentBuilder()
                        .build()
            }
            return tagsListComponent!!
        }

        fun plusPostsListComponent(): PostsListComponent {
            if (postsListComponent == null) {
                postsListComponent = component
                        .postsListComponentBuilder()
                        .build()
            }

            return postsListComponent!!
        }
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}