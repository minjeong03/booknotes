package com.example.booknotes.network

import com.example.booknotes.Book
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "http://34.64.157.164:3000/"
private val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BookNotesApiService {
    @GET("api/books")
    suspend fun getBookNotes(): List<Book>
}
object BookNotesApi {
    val retrofitService : BookNotesApiService by lazy {
        retrofit.create(BookNotesApiService::class.java)
    }
}