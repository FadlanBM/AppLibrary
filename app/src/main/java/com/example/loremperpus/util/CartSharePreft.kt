package com.example.loremperpus.util

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONArray

class CartSharePreft constructor(context: Context) {
    companion object{
        private const val shareName="MyAppCart"
        private const val shareId="id"
    }

    val sharePreft: SharedPreferences =context.getSharedPreferences(shareName,Context.MODE_PRIVATE)
    val editorid: SharedPreferences.Editor=sharePreft.edit()

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

    fun deleteData(potition: Int){
        val dataExisid=sharePreft.getString(shareId,null)
        if (dataExisid!=null) {
            val dataArrayid = JSONArray(dataExisid)
            for (i in 0 until dataArrayid.length()) {
                if (i == potition) {
                    dataArrayid.remove(i)
                }
            }
            editorid.putString(shareId,dataArrayid.toString()).apply()
        }
    }

    fun clearCart(){
        editorid.clear().apply()
    }
}