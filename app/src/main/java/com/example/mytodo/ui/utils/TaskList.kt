package com.example.mytodo.ui.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.mytodo.R
import com.example.mytodo.modals.Task

@Composable
fun MyToDoTasksList(
    tasksList: List<Task>,
    onTaskClick: (Int) -> Unit,
    onCheckChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = tasksList, key = { it.id }) { item ->
            MyToDoTask(
                task = item,
                onCheckChange = onCheckChange,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small)),
                onTaskClick = onTaskClick
            )

        }
    }
}