package com.example.orderapp

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

enum class LunchTrayScreen (@StringRes val title: Int){
    Start(title = R.string.lunch_tray),
    Entree(title = R.string.choose_entree),
    SideDish(title = R.string.choose_side_dish),
    Accompaniment(title = R.string.choose_accompaniment),
    OrderCheckout(title = R.string.order_checkout)
}

@Composable
fun OrderApp(
    currentScreen: LunchTrayScreen,
    canNavigationBack: Boolean,
    navigateUp: () ->Unit,
    modifier: Modifier =Modifier
){

}