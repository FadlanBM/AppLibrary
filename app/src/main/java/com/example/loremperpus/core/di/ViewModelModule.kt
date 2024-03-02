package com.example.loremperpus.core.di


import com.example.loremperpus.ui.Lending.LandingBookViewModel
import com.example.loremperpus.ui.auth.AuthViewModel
import com.example.loremperpus.ui.cart.CartViewModel
import com.example.loremperpus.ui.history.HistoryViewModel
import com.example.loremperpus.ui.list_book.ListBookViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule= module {
    viewModel { AuthViewModel(get()) }
    viewModel { ListBookViewModel(get()) }
    viewModel { CartViewModel(get()) }
    viewModel { LandingBookViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
}