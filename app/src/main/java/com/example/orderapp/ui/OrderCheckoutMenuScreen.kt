package com.example.orderapp.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.orderapp.R
import com.example.orderapp.datasource.DataSource
import com.example.orderapp.model.MenuItem
import com.example.orderapp.model.OrderUiState

@Composable
fun OrderCheckoutScreen(
    orderUiState: OrderUiState,
    onNextButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
        Text(
            text = stringResource(R.string.order_summary),
            fontWeight = FontWeight.Bold
        )
        ItemSummary(item = orderUiState.entree, modifier = Modifier.fillMaxWidth())
        ItemSummary(item = orderUiState.sideDishItem, modifier = Modifier.fillMaxWidth())
        ItemSummary(item = orderUiState.accompanimentItem, modifier = Modifier.fillMaxWidth())

        Divider(
            thickness = dimensionResource(id = R.dimen.thickness_divider),
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_small))
        )
        
        OrderSubCost(
            resId = R.string.subtotal,
            price = orderUiState.itemTotalPrice.formatPrice(),
            Modifier.align(Alignment.End)
            )
        OrderSubCost(
            resId = R.string.tax,
            price = orderUiState.orderTax.formatPrice(),
            Modifier.align(Alignment.End)
        )
        Text(text = stringResource(R.string.total, orderUiState.orderTotalPrice.formatPrice()),
            modifier = Modifier.align(Alignment.End),
            fontWeight = FontWeight.Bold
            )
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_medium)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
        ) {
            OutlinedButton(
                onClick = onCancelButtonClicked,
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(stringResource(id = R.string.cancel).uppercase())
            }
            Button(onClick = onNextButtonClicked, modifier = Modifier.weight(1f)) {
                Text(stringResource(id = R.string.submit).uppercase())
            }
        }
       
    }
}

@Composable
fun ItemSummary(
    item: MenuItem?,
    modifier: Modifier = Modifier
){
    Row(modifier =modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        Text(item?.name ?: "")
        Text(item?.getFormattedPrice() ?: "")
    }
}

@Composable
fun OrderSubCost(
    @StringRes resId: Int,
    price: String,
    modifier: Modifier = Modifier
){
    Text(text = stringResource(resId, price), modifier = modifier)
}

@Preview
@Composable
fun OrderCheckoutPreview(){
    OrderCheckoutScreen(
        orderUiState = OrderUiState(
            entree = DataSource.entreeMenuItems[0],
            sideDishItem = DataSource.sideDishMenuItems[0],
            accompanimentItem = DataSource.accompanimentMenuItems[0],
            itemTotalPrice = 15.00,
            orderTax = 1.00,
            orderTotalPrice = 16.00
        ),
        onNextButtonClicked = { /*TODO*/ },
        onCancelButtonClicked = { /*TODO*/ },
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
            .verticalScroll(
                rememberScrollState()
            )
        )

}