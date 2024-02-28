package com.example.loremperpus.core.data.source.remote.network

import com.example.loremperpus.core.data.source.remote.request.LoginRequest
import com.example.loremperpus.core.data.source.remote.request.RegisterRequest
import com.example.loremperpus.core.data.source.remote.response.BookDetailResponse
import com.example.loremperpus.core.data.source.remote.response.BookResponse
import com.example.loremperpus.core.data.source.remote.response.CategoryResponse
import com.example.loremperpus.core.data.source.remote.response.LoginRespose
import com.example.loremperpus.core.data.source.remote.response.MeResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("auth/register")
    suspend fun register(
        @Body login:RegisterRequest
    ): Response<ResponseBody>

    @POST("auth")
    suspend fun login(
        @Body login:LoginRequest
    ): Response<LoginRespose>

    @GET("auth/me")
    suspend fun getme(
        @Header("Authorization") token: String,
    ): Response<MeResponse>

    @GET("book")
    suspend fun getBook(
        @Header("Authorization") token: String,
    ): Response<BookResponse>
    @GET("book/category/{id}")
    suspend fun getlistcategory(
        @Header("Authorization") token: String,
        @Path("id") bookID: Int
    ): Response<CategoryResponse>
    @GET("book/{id}")
    suspend fun getDetailData(
        @Header("Authorization") token: String,
        @Path("id") bookID: Int
    ): Response<BookDetailResponse>

}
