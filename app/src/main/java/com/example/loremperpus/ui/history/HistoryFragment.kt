package com.example.loremperpus.ui.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loremperpus.AdapterRV.CartRV
import com.example.loremperpus.core.data.source.remote.network.State
import com.example.loremperpus.databinding.FragmentCartBinding
import com.example.loremperpus.databinding.FragmentHistoryBinding
import com.example.loremperpus.item.ListCart
import com.example.loremperpus.ui.list_book.ListBookViewModel
import com.example.loremperpus.util.CartSharePreft
import com.example.loremperpus.util.Prefs
import com.example.loremperpus.util.SwipeToDeleteCallback
import com.inyongtisto.myhelper.extension.toastWarning
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val viewModel: ListBookViewModel by viewModel()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}