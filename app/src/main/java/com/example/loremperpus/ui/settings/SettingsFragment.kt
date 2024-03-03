package com.example.loremperpus.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loremperpus.AdapterRV.AdapterListSetting
import com.example.loremperpus.databinding.FragmentNotificationsBinding
import com.example.loremperpus.item.ListSetting

class SettingsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private lateinit var recyclerViewSettings: RecyclerView

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        recyclerViewSettings=binding.recyclerViewSettings
        val settingsList = listOf(
            ListSetting("Data Pribadi", "Melihat Data Pribadi"),
            ListSetting("Ubah Data Pribadi", "Edit Data Pribadi"),
            ListSetting("Reset Password", "Reset Password"),
            ListSetting("Wishlist", "Wishlist"),
            ListSetting("Logout", "Keluar Dari Akun"),
        )

        val settingsAdapter = AdapterListSetting(settingsList,requireContext())
        recyclerViewSettings.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewSettings.adapter = settingsAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}