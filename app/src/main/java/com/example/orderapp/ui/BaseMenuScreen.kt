package com.example.orderapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.orderapp.R
import com.example.orderapp.model.MenuItem

@Composable
fun BaseMenuScreen(
    options: List<MenuItem>,
    modifier: Modifier = Modifier,
    onCancelButtonClicked: () -> Unit = {},
    onNextButtonClicked: () -> Unit = {},
    onSelectionChanged: (MenuItem) -> Unit
){
    var selectedItemName by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
    ) {
        options.forEach { item ->
            val onClick = {
                selectedItemName = item.name
                onSelectionChanged(item)
            }
            MenuItemRow(
                item = item,
                selectedItemName = selectedItemName,
                onClick = onClick,
                modifier = Modifier.selectable(
                    selected = selectedItemName == item.name,
                    onClick = onClick
                )
            )
        }
        MenuScreenButtonGroup(
            selectedItemName = selectedItemName,
            onCancelButtonClicked = onCancelButtonClicked,
            onNextButtonClicked = { onNextButtonClicked() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium))
        )
    }
}

@Composable
fun MenuItemRow(
    item: MenuItem,
    selectedItemName: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = selectedItemName == item.name, onClick = onClick)
        Column(verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.headlineSmall)
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyLarge)
            Text(
                text = item.getFormattedPrice(),
                style = MaterialTheme.typography.bodyMedium)
            Divider(
                thickness = dimensionResource(id = R.dimen.thickness_divider),
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_medium))
            )

        }
    }
}

@Composable
fun MenuScreenButtonGroup(
    selectedItemName: String,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))) {
        OutlinedButton(onClick = onCancelButtonClicked, modifier = Modifier.weight(1f)) {
            Text(stringResource(id = R.string.cancel).uppercase())
        }
        Button(onClick = onNextButtonClicked, modifier = Modifier.weight(1f), enabled = selectedItemName.isNotEmpty()) {
            Text(text = stringResource(id = R.string.next).uppercase())
        }
    }
}