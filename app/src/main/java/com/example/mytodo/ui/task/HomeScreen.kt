package com.example.mytodo.ui.task

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mytodo.R
import com.example.mytodo.modals.Task
import com.example.mytodo.ui.AppViewModelProvider
import com.example.mytodo.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch
import com.example.mytodo.ui.utils.MyToDoTasksList
import com.example.mytodo.ui.utils.MyToDoTopAppBar
import com.example.mytodo.ui.utils.SearchInput
import com.example.mytodo.ui.utils.TasksFilter

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navigateToNewTask: () -> Unit,
    navigateToTaskInformation: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.homeUiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MyToDoTopAppBar(
                title = stringResource(HomeDestination.titleRes),
                canNavigateBack = false,
                canHaveSearch = true,
                scrollBehavior = scrollBehavior,
                onSearchValueChanged = {
                    viewModel.onSearchKeyChanged(it)
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToNewTask,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.item_entry_title)
                )
            }
        },
    ) {innerPadding ->
        HomeBody(
            tasksList = homeUiState.tasksList,
            onTaskClick = navigateToTaskInformation,
            onCheckChange = {
                coroutineScope.launch {
                    viewModel.updateTaskStatus(it)
                }
            },
            onFilterSelected = {
                viewModel.onFilterKeyChanged(it)
            },
            activeFilterKey = viewModel.filterKey,
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
private fun HomeBody(
    tasksList: List<Task>,
    onTaskClick: (Int) -> Unit,
    onFilterSelected: (String) -> Unit,
    activeFilterKey: String,
    onCheckChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        TasksFilter(
            onFilterSelected = onFilterSelected,
            activeFilterKey = activeFilterKey,
        )
        if (tasksList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_item_description),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
            MyToDoTasksList(
                tasksList = tasksList,
                onTaskClick = onTaskClick,
                onCheckChange = onCheckChange,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}



