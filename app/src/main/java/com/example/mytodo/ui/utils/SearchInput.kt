package com.example.mytodo.ui.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.mytodo.R

@Composable
fun SearchInput(
    onClear: () -> Unit,
    onSearchValueChanged: (String) -> Unit
) {

    var searchKey by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(id = R.dimen.padding_medium),
                vertical = dimensionResource(id = R.dimen.padding_extra_small)
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = searchKey,
            onValueChange = {
                searchKey = it
                onSearchValueChanged(searchKey)
            },
            placeholder = {
                Text(text = "Search your todos")
            },
            colors = OutlinedTextFieldDefaults.colors(),
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            trailingIcon = {
                IconButton(onClick = {
                    searchKey = ""
                    onSearchValueChanged(searchKey)
                    onClear()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "Search"
                    )
                }
            }
        )
    }
}