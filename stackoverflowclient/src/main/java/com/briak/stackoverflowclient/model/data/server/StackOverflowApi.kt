package com.briak.stackoverflowclient.model.data.server

import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface StackOverflowApi {
//    companion object {
//        const val API_PATH = "/v2"
//        const val API_KEY = "6e1ea05222d34fc8b296bf19cc57b12d"
//    }
//
//    @GET("$API_PATH/top-headlines")
//    fun getHeadliners(
//            @Query("country") country: String,
//            @Query("category") category: String,
//            @Query("apiKey") apiKey: String = API_KEY
//    ): Deferred<RSS>
//
//    @GET("$API_PATH/everything")
//    fun getEverything(
//            @Query("q") query: String?,
//            @Query("from") fromDate: String?,
//            @Query("to") toDate: String?,
//            @Query("domains") domain: String = "wsj.com,nytimes.com",
//            @Query("apiKey") apiKey: String = API_KEY
//    ): Deferred<RSS>
}