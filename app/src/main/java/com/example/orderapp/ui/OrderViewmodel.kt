package com.example.orderapp.ui

import androidx.lifecycle.ViewModel
import com.example.orderapp.model.MenuItem
import com.example.orderapp.model.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

class OrderViewModel : ViewModel() {
    private val taxRAte = 0.08

    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> =_uiState.asStateFlow()

    fun updateEntree(entree: MenuItem.EntreeItem){
        val previousEntree = _uiState.value.entree
        updateItem(entree, previousEntree)
    }

    private fun updateItem(newItem: MenuItem, previousItem: MenuItem?){
        _uiState.update { currentState ->
            val previousItemPrice = previousItem?.price ?: 0.0
            val itemTotalPrice = currentState.itemTotalPrice - previousItemPrice + newItem.price

            val tax = itemTotalPrice * taxRAte
            currentState.copy(
                itemTotalPrice = itemTotalPrice,
                orderTax = tax,
                orderTotalPrice = itemTotalPrice + tax ,
                entree = if (newItem is MenuItem.EntreeItem) newItem else currentState.entree,
                sideDishItem = if (newItem is MenuItem.SideDishItem) newItem else currentState.sideDishItem,
                accompanimentItem = if (newItem is MenuItem.AccompanimentItem) newItem else currentState.accompanimentItem
            )
        }
    }
}

fun Double.formatPrice(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}