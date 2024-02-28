package com.example.loremperpus.core.data.repository

import android.util.Log
import com.example.loremperpus.core.data.source.local.LocalDataSource
import com.example.loremperpus.core.data.source.remote.RemoteDataSouce
import com.example.loremperpus.core.data.source.remote.network.Resource
import com.example.loremperpus.core.data.source.remote.request.LoginRequest
import com.example.loremperpus.core.data.source.remote.request.RegisterRequest
import com.example.loremperpus.core.data.source.remote.request.RegisterWithGoogleRequest
import com.inyongtisto.myhelper.extension.getErrorBody
import kotlinx.coroutines.flow.flow


class AppRepository(val local:LocalDataSource,val remote:RemoteDataSouce) {
    fun register(data:RegisterRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.register(data).let {
                if (it.isSuccessful){
                    emit(Resource.success(it))
                }else{
                    emit(Resource.error(it.getErrorBody()?.message?:"Terjadi Kesalahan",null))
                    Log.e("ERROR","Error Http")
                }
            }
        }catch (e:Exception){
            emit(Resource.error(e.message?:"terjadi Kesalahan",null))
            Log.e("TAG","Login Error" + e.message)
        }
    }

    fun login(data:LoginRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.login(data).let {
                if (it.isSuccessful){
                    val body=it.body()
                    emit(Resource.success(body?.token))
                }else{
                    emit(Resource.error(it.getErrorBody()?.message?:"Terjadi Kesalahan",null))
                    Log.e("ERROR","Error Http")
                }
            }
        }catch (e:Exception){
            emit(Resource.error(e.message?:"terjadi Kesalahan",null))
            Log.e("TAG","Login Error" + e.message)
        }
    }

    fun getme(token:String) = flow {
        emit(Resource.loading(null))
        try {
            remote.getme(token).let {
                if (it.isSuccessful){
                    val body=it.body()
                    emit(Resource.success(body))
                }else{
                    emit(Resource.error(it.getErrorBody()?.message?:"Terjadi Kesalahan",null))
                    Log.e("ERROR","Error Http")
                }
            }
        }catch (e:Exception){
            emit(Resource.error(e.message?:"terjadi Kesalahan",null))
            Log.e("TAG","Login Error" + e.message)
        }
    }
    fun getBook(token:String) = flow {
        emit(Resource.loading(null))
        try {
            remote.getBook(token).let {
                if (it.isSuccessful){
                    val body=it.body()
                    emit(Resource.success(body))
                }else{
                    emit(Resource.error(it.getErrorBody()?.message?:"Terjadi Kesalahan",null))
                    Log.e("ERROR","Error Http")
                }
            }
        }catch (e:Exception){
            emit(Resource.error(e.message?:"terjadi Kesalahan",null))
            Log.e("TAG","Login Error" + e.message)
        }
    }
    fun getlistcategory(token:String,id:Int) = flow {
        emit(Resource.loading(null))
        try {
            remote.getlistcategory(token,id).let {
                if (it.isSuccessful){
                    val body=it.body()
                    emit(Resource.success(body))
                }else{
                    emit(Resource.error(it.getErrorBody()?.message?:"Terjadi Kesalahan",null))
                    Log.e("ERROR","Error Http")
                }
            }
        }catch (e:Exception){
            emit(Resource.error(e.message?:"terjadi Kesalahan",null))
            Log.e("TAG","Login Error" + e.message)
        }
    }
    fun getDetailData(token:String,id:Int) = flow {
        emit(Resource.loading(null))
        try {
            remote.getDetailData(token,id).let {
                if (it.isSuccessful){
                    val body=it.body()
                    emit(Resource.success(body))
                }else{
                    emit(Resource.error(it.getErrorBody()?.message?:"Terjadi Kesalahan",null))
                    Log.e("ERROR","Error Http")
                }
            }
        }catch (e:Exception){
            emit(Resource.error(e.message?:"terjadi Kesalahan",null))
            Log.e("TAG","Login Error" + e.message)
        }
    }
}