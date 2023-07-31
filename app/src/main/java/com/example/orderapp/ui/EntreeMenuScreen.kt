package com.example.orderapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.orderapp.R
import com.example.orderapp.datasource.DataSource
import com.example.orderapp.model.MenuItem

@Composable
fun EntreeMenuScreen(
     options: List<MenuItem.EntreeItem>,
     onCancelButtonClicked: () -> Unit,
     onNextButtonClicked: () -> Unit,
     onSelectionChanged: (MenuItem.EntreeItem) -> Unit,
     modifier: Modifier = Modifier
){
    BaseMenuScreen(options = options,
        onSelectionChanged = onSelectionChanged as (MenuItem) -> Unit,
        onCancelButtonClicked = onCancelButtonClicked,
        onNextButtonClicked = onNextButtonClicked,
        modifier = modifier
        )
}

@Preview
@Composable
fun EntreeMenuPreview(){
    EntreeMenuScreen(
        options = DataSource.entreeMenuItems,
        onCancelButtonClicked = { /*TODO*/ },
        onNextButtonClicked = { /*TODO*/ },
        onSelectionChanged = {},
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
            .verticalScroll(
                rememberScrollState()
            )
    )
}
