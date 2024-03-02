package com.example.loremperpus.core.data.repository

import android.util.Log
import com.example.loremperpus.core.data.source.local.LocalDataSource
import com.example.loremperpus.core.data.source.remote.RemoteDataSouce
import com.example.loremperpus.core.data.source.remote.network.Resource
import com.example.loremperpus.core.data.source.remote.request.LandingRequest
import com.example.loremperpus.core.data.source.remote.request.ListLendingRequest
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
    fun getDetailBook(token:String,id:Int) = flow {
        emit(Resource.loading(null))
        try {
            remote.getDetailBook(token,id).let {
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
    fun getLending(token:String) = flow {
        emit(Resource.loading(null))
        try {
            remote.getLending(token).let {
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
    fun postDataLendings(token:String,request: LandingRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.postDataLendings(token,request).let {
                if (it.isSuccessful){
                    val body=it.body()?.data
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

    fun getDetailDataLendings(token:String,id: Int) = flow {
        emit(Resource.loading(null))
        try {
            remote.getDetailDataLendings(token,id).let {
                if (it.isSuccessful){
                    val body=it.body()
                    emit(Resource.success(body?.data))
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
    fun postDataListLandings(token:String,request:ListLendingRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.postDataListLandings(token,request).let {
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
    fun getDetailHistoryLending(token:String,id:Int) = flow {
        emit(Resource.loading(null))
        try {
            remote.getDetailHistoryLending(token,id).let {
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