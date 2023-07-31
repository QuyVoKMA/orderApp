package com.example.orderapp.model

data class OrderUiState(
    val entree: MenuItem.EntreeItem? = null,
    val sideDishItem: MenuItem.SideDishItem? = null,
    val accompanimentItem: MenuItem.AccompanimentItem? =null,
    val itemTotalPrice: Double = 0.0,
    val orderTax: Double = 0.0,
    val orderTotalPrice: Double = 0.0
)
