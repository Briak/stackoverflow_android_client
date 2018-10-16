package com.briak.stackoverflowclient.model.data.server

import com.briak.stackoverflowclient.entities.post.server.PostsList
import com.briak.stackoverflowclient.entities.tag.server.TagsList
import com.briak.stackoverflowclient.entities.wikis.server.TagWikiList
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StackOverflowApi {
    companion object {
        const val API_PATH = "/2.2"
        const val SITE = "stackoverflow"
    }

    @GET("$API_PATH/tags")
    fun getTags(
            @Query("page") page: Int,
            @Query("pagesize") pageSize: Int = 20,
            @Query("order") order: String = "desc",
            @Query("sort") sort: String = "popular",
            @Query("site") site: String = SITE
    ): Deferred<TagsList>

    @GET("$API_PATH/tags/{tags}/wikis")
    fun getWikis(
            @Path("tags") tags: String,
            @Query("site") site: String = SITE
    ): Deferred<TagWikiList>

    @GET("$API_PATH/questions")
    fun getPosts(
            @Query("tagged") tagged: String,
            @Query("page") page: Int,
            @Query("pagesize") pageSize: Int = 20,
            @Query("order") order: String = "desc",
            @Query("sort") sort: String = "activity",
            @Query("site") site: String = SITE,
            @Query("filter") filter: String = "withbody"
    ): Deferred<PostsList>
}