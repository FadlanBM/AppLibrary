package com.example.loremperpus.AdapterRV

import android.content.Context
import android.renderscript.ScriptGroup.Binding
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.loremperpus.R
import com.example.loremperpus.databinding.ActivityLendingFormBinding
import com.example.loremperpus.item.ListCart
import com.example.loremperpus.item.ListCategory
import com.example.loremperpus.util.CartSharePreft
import com.example.loremperpus.util.Constants
import com.inyongtisto.myhelper.extension.showToast
import com.squareup.picasso.Picasso

class ListLendingRV(
    val item: List<ListCart>,
    val context: Context,
    val binding: ActivityLendingFormBinding,
) : RecyclerView.Adapter<ListLendingRV.ViewHolder>() {

    private val result = MutableLiveData<String>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgbook: ImageView = view.findViewById(R.id.imgBookLending)
        val title: TextView = view.findViewById(R.id.tvTitleLending)
        val auhtor: TextView = view.findViewById(R.id.tvAuthorLending)
        val inventaris: EditText = view.findViewById(R.id.tiNoInventarisLending)
        val btnFinal: ImageButton = view.findViewById(R.id.btnSaveInventaris)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_lendings, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = item[position]
        holder.title.text = items.title
        holder.auhtor.text = items.author
        getInventaris(position)
        holder.inventaris.setText(result.value)

        Picasso.get().load(Constants.BASE_Image + items.img).into(holder.imgbook)

        holder.btnFinal.setOnClickListener {
            if (holder.inventaris.text.isEmpty()){
                holder.inventaris.error="Form No Inventaris is empty"
            }else{
                val updatedInventaris = holder.inventaris.text.toString()
                CartSharePreft(context).updateInven(position, updatedInventaris)
                holder.inventaris.isEnabled = !holder.inventaris.isEnabled
                val drawableResId = if (holder.inventaris.isEnabled) {
                    R.drawable.checkmark
                } else {
                    R.drawable.close
                }
                holder.btnFinal.setImageResource(drawableResId)
            }
        }


        binding.btnAddTransaksi.setOnClickListener {
            if (holder.inventaris.isEnabled) {
                context.showToast("Inventory Form has not been validated")
            } else {
                val inven = CartSharePreft(context).getNOInvens()
                Log.e("Inven", inven.toString())
            }
        }
    }

    fun getInventaris(potition: Int) {
        val inventoryArray = CartSharePreft(context).getNOInvens()
        val oldValue = if (inventoryArray.isNotEmpty() && potition >= 0 && potition < inventoryArray.size) {
            inventoryArray[potition]
        } else {
            null
        }
        result.value = oldValue
    }
}
