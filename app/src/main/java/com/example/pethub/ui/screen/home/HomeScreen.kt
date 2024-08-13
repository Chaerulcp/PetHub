package com.example.pethub.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pethub.data.di.Injection
import com.example.pethub.ui.common.UiState
import com.example.pethub.ui.common.ViewModelFactory
import com.example.pethub.ui.components.Loading
import com.example.pethub.ui.components.PetItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context = LocalContext.current))
    ),
    onNavigateToDetailScreen: (String) -> Unit,
) {
    val petsData by viewModel.petsData
    val user by viewModel.user.collectAsState()

    val listState = rememberLazyListState()

    LaunchedEffect(key1 = viewModel) {
        if (petsData is UiState.Loading) {
            viewModel.getPets()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column {
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Hai ${user?.name},",
                modifier = Modifier.padding(
                    start = 16.dp,
                    bottom = 8.dp
                ),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Adopsi kucing yang berada disekitarmu!",
                modifier = Modifier.padding(
                    start = 16.dp,
                    bottom = 8.dp
                ),
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
            )

            when (petsData) {
                is UiState.Loading -> {
                    Loading()
                }

                is UiState.Success -> {
                    val pets = (petsData as UiState.Success).data

                    LazyColumn(
                        state = listState,
                        contentPadding = PaddingValues(bottom = 10.dp)
                    ) {
                        items(pets, key = { it.id }) {
                            PetItem(
                                modifier = modifier,
                                data = it,
                                onNavigateToDetailScreen = onNavigateToDetailScreen
                            )
                        }
                    }
                }

                is UiState.Error -> {

                }
            }
        }
    }
}