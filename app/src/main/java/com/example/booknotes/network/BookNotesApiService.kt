package com.example.booknotes.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "http://34.64.157.164/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface BookNotesApiService {
    @GET("books")
    suspend fun getBookNotes(): String
}
object BookNotesApi {
    val retrofitService : BookNotesApiService by lazy {
        retrofit.create(BookNotesApiService::class.java)
    }
}