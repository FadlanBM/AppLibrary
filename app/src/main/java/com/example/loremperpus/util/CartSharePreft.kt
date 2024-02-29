package com.example.loremperpus.util

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONArray

class CartSharePreft constructor(context: Context) {
    companion object{
        private const val shareName="MyAppCart"
        private const val shareId="id"
        private const val shareNoInven="inven"
    }

    val sharePreft: SharedPreferences =context.getSharedPreferences(shareName,Context.MODE_PRIVATE)
    val editorid: SharedPreferences.Editor=sharePreft.edit()
    val editorNoInven: SharedPreferences.Editor=sharePreft.edit()

    fun saveId(id:String){
        val jsonArray= JSONArray()
        val exisId=sharePreft.getString(shareId,null)
        if (exisId!=null){
            val idArray= JSONArray(exisId)
            for (i in 0 until idArray.length()){
                jsonArray.put(idArray.getString(i))
            }
        }
        jsonArray.put(id)
        editorid.putString(shareId,jsonArray.toString())
        editorid.apply()
    }

    fun getId():List<String>{
        val idExis=sharePreft.getString(shareId,null)
        val dataList= mutableListOf<String>()
        if (idExis!=null){
            val jsonArray= JSONArray(idExis)
            for (i in 0 until jsonArray.length()){
                val list=jsonArray.getString(i)
                dataList.add(list)
            }
        }
        return dataList
    }
    fun getNOInvens():List<String>{
        val InvenstExis=sharePreft.getString(shareNoInven,null)
        val dataList= mutableListOf<String>()
        if (InvenstExis!=null){
            val jsonArray= JSONArray(InvenstExis)
            for (i in 0 until jsonArray.length()){
                val list=jsonArray.getString(i)
                dataList.add(list)
            }
        }
        return dataList
    }

    fun saveNoInven(data: String){
        val jsonArray= JSONArray()
        val priceExis= sharePreft.getString(shareNoInven,null)
        if (priceExis!=null){
            val priceArray= JSONArray(priceExis)
            for (i in 0 until priceArray.length() ){
                jsonArray.put(priceArray.getString(i))
            }
        }
        jsonArray.put(data)

        editorNoInven.putString(shareNoInven,jsonArray.toString()).apply()
    }

    fun updateInven(position: Int, data: String) {
        val inventExist = sharePreft.getString(shareNoInven, null)
        val jsonArray = JSONArray()

        if (inventExist != null) {
            val countJson = JSONArray(inventExist)
            var status = false

            for (i in 0 until countJson.length()) {
                if (i == position) {
                    jsonArray.put(data) // Ganti data yang lama dengan data yang baru pada posisi tertentu
                    status = true
                } else {
                    jsonArray.put(countJson.getString(i))
                }
            }

            if (!status) {
                jsonArray.put(data) // Jika posisi tidak ditemukan, tambahkan data baru
            }

            editorNoInven.putString(shareNoInven, jsonArray.toString()).apply()
        } else {
            // Jika tidak ada data sebelumnya, tambahkan data baru
            jsonArray.put(data)
            editorNoInven.putString(shareNoInven, jsonArray.toString()).apply()
        }
    }


    fun deleteData(potition: Int) {
        val dataExisid = sharePreft.getString(shareId, null)
        val inventExit = sharePreft.getString(shareNoInven, null)
        if (dataExisid != null && inventExit != null) {
            val dataArrayid = JSONArray(dataExisid)
            val inventid = JSONArray(inventExit)
            for (i in dataArrayid.length() - 1 downTo 0) {
                if (i == potition) {
                    dataArrayid.remove(i)
                    inventid.remove(i)
                }
            }
            editorid.putString(shareId, dataArrayid.toString()).apply()
            editorNoInven.putString(shareNoInven, inventid.toString()).apply()
        }
    }


    fun clearCart(){
        editorid.clear().apply()
    }
}