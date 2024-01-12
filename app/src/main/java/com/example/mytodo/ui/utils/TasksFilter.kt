package com.example.mytodo.ui.utils

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.mytodo.R
import com.example.mytodo.modals.TaskCategory

@Composable
fun TasksFilter(
    onFilterSelected: (String) -> Unit,
    activeFilterKey: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = dimensionResource(id = R.dimen.padding_medium), vertical = dimensionResource(id = R.dimen.padding_small)),
    ) {
        filterOptions.forEach { option ->

            if(activeFilterKey == option.value) {
                Button(
                    onClick = {
                        onFilterSelected(option.value)
                    },
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier.padding(end = dimensionResource(id = R.dimen.padding_medium))
                ) {
                    Text(
                        text = option.name
                    )
                }
            }else {
                OutlinedButton(
                    onClick = {
                        onFilterSelected(option.value)
                    },
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier.padding(end = dimensionResource(id = R.dimen.padding_medium))
                ) {
                    Text(
                        text = option.name
                    )
                }
            }
        }
    }
}

data class FilterOption(
    val name: String,
    val value: String
)

val filterOptions = listOf(
    FilterOption("All", ""),
    FilterOption(TaskCategory.URGENT.toString(), TaskCategory.URGENT.toString()),
    FilterOption(TaskCategory.WORK.toString(), TaskCategory.WORK.toString()),
    FilterOption(TaskCategory.PERSONAL.toString(), TaskCategory.PERSONAL.toString()),
    FilterOption(TaskCategory.HOME.toString(), TaskCategory.HOME.toString()),
)