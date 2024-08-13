package com.example.pethub.ui.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pethub.data.di.Injection
import com.example.pethub.data.model.PetEntity
import com.example.pethub.ui.common.UiState
import com.example.pethub.ui.common.ViewModelFactory
import com.example.pethub.ui.components.Loading
import com.example.pethub.ui.components.PetItem
import com.example.pethub.ui.theme.PetHubTheme

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    viewModel: ListViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context = LocalContext.current))
    ),
    onNavigateToDetailScreen: (String) -> Unit,
) {
    viewModel.listedPet.collectAsState(initial = UiState.Loading).value.let {
        when (it) {
            is UiState.Loading -> {
                Loading()
                viewModel.getListedPet()
            }

            is UiState.Success -> {
                ListContent(
                    modifier = modifier,
                    data = it.data,
                    onNavigateToDetailScreen = onNavigateToDetailScreen,
                )
            }

            is UiState.Error -> {

            }
        }
    }
}

@Composable
fun ListContent(
    modifier: Modifier = Modifier,
    data: List<PetEntity>,
    onNavigateToDetailScreen: (String) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 12.dp)
    ) {
        if (data.isNotEmpty()) {
            LazyColumn(
                state = rememberLazyListState(),
                contentPadding = PaddingValues(bottom = 10.dp)
            ) {
                items(data, key = { it.id }) {
                    PetItem(
                        modifier = modifier,
                        data = it,
                        onNavigateToDetailScreen = onNavigateToDetailScreen
                    )
                }
            }
        } else {
            Text(
                text = "Trying To Adopt A Pet?",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = modifier.align(alignment = Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    PetHubTheme {
        ListContent(
            data = listOf()
        )
    }
}