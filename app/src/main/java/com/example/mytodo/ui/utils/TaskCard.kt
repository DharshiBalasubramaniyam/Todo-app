package com.example.mytodo.ui.utils

import android.icu.lang.UCharacter
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.mytodo.R
import com.example.mytodo.modals.Task

@Composable
fun MyToDoTask(
    task: Task,
    modifier: Modifier = Modifier,
    onCheckChange: (Int) -> Unit,
    onTaskClick: (Int) -> Unit
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = MaterialTheme.shapes.extraSmall
    ) {
        Row(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.fillMaxHeight()
            ) {
                Checkbox(
                    checked = task.isCompleted,
                    onCheckedChange = {
                        onCheckChange(task.id)
                    }
                )
            }
            Spacer(modifier = Modifier.width(3.dp))
            Column(
                modifier = Modifier.clickable { onTaskClick(task.id) }
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.height(47.dp)
                ) {
                    Text(
                        text = UCharacter.toUpperCase(task.title),
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Spacer(Modifier.weight(1f))

                    Button(
                        onClick = {  },
                        shape = MaterialTheme.shapes.large,
                    ) {
                        Text(
                            text = task.category,
                        )
                    }

                }
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.titleMedium
                )
            }

        }
    }
}