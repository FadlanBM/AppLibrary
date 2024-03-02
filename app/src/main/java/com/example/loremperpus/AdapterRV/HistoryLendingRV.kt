package com.example.loremperpus.AdapterRV

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loremperpus.R
import com.example.loremperpus.item.ListHistoryLending

class HistoryLendingRV(
    val item: List<ListHistoryLending>,
    val context: Context,
) : RecyclerView.Adapter<HistoryLendingRV.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val code: TextView =view.findViewById(R.id.tvCodeHistory)
        val borrowDate: TextView =view.findViewById(R.id.tvBorrowDateHistory)
        val DateLast: TextView =view.findViewById(R.id.tvDateLastHistory)
        val returnDate: TextView =view.findViewById(R.id.tvReturnDateHistory)
        val status: TextView =view.findViewById(R.id.tvStatusHistory)
        val toView: LinearLayout =view.findViewById(R.id.llToViewHistory)
        val toDelete: LinearLayout =view.findViewById(R.id.llDeleteHistory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return HistoryLendingRV.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_history, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item=item[position]
       holder.code.text=item.code
        holder.borrowDate.text=item.BorrowData
        holder.returnDate.text=item.ReturnDate
        holder.DateLast.text=item.DateLast
        holder.status.text = if (item.Status == "false") "Not Active" else "Active"

    }
}