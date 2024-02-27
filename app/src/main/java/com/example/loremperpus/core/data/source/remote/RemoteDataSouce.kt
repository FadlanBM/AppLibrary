package com.example.loremperpus.core.data.source.remote

import com.example.loremperpus.core.data.source.remote.network.ApiService
import com.example.loremperpus.core.data.source.remote.request.LoginRequest
import com.example.loremperpus.core.data.source.remote.request.RegisterRequest
import com.example.loremperpus.core.data.source.remote.request.RegisterWithGoogleRequest

class RemoteDataSouce(private val api:ApiService) {
    suspend fun register(data:RegisterRequest)=api.register(data)
    suspend fun login(data:LoginRequest)=api.login(data)
    suspend fun getme(token:String)=api.getme(token)
    suspend fun getBook(token:String)=api.getBook(token)
}