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
fun AccompanimentMenuScreen(
    options: List<MenuItem.AccompanimentItem>,
    onCancelButtonClick: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onSelectionChanged: (MenuItem.AccompanimentItem) -> Unit,
    modifier: Modifier = Modifier
)
{
    BaseMenuScreen(
        options = options,
        onSelectionChanged = onSelectionChanged as (MenuItem) -> Unit,
        onNextButtonClicked = onNextButtonClicked,
        onCancelButtonClicked = onCancelButtonClick,
       modifier = modifier
    )


}


@Preview
@Composable
fun AccompanimentPreview(){
    AccompanimentMenuScreen(
        options = DataSource.accompanimentMenuItems,
        onCancelButtonClick = { /*TODO*/ },
        onNextButtonClicked = { /*TODO*/ },
        onSelectionChanged = {},
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
            .verticalScroll(
                rememberScrollState()
            )
    )
}