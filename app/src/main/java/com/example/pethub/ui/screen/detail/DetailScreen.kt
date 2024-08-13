package com.example.pethub.ui.screen.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.pethub.data.di.Injection
import com.example.pethub.data.dummy.getDummyPetData
import com.example.pethub.data.model.Pet
import com.example.pethub.ui.common.UiState
import com.example.pethub.ui.common.ViewModelFactory
import com.example.pethub.ui.components.Loading
import com.example.pethub.ui.theme.PetHubTheme
import com.example.pethub.ui.utils.toTime

@Composable
fun DetailScreen(
    id: String,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context = LocalContext.current))
    ),
) {
    val petData by viewModel.petData
    val isListed by viewModel.isListed.collectAsState()

    DisposableEffect(id) {
        viewModel.getPetDetail(id)
        viewModel.isPetListed(id)
        onDispose {

        }
    }

    when (petData) {
        is UiState.Loading -> {
            Loading()
        }

        is UiState.Success -> {
            val toggleListPet = {
                viewModel.toggleListedPet((petData as UiState.Success<Pet>).data.toPetEntity())
            }

            DetailContent(
                modifier = modifier,
                data = (petData as UiState.Success<Pet>).data,
                isListed = isListed,
                toggleListPet = toggleListPet,
            )
        }

        is UiState.Error -> {

        }
    }
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    data: Pet,
    isListed: Boolean,
    toggleListPet: () -> Unit = {},
) {

    val context = LocalContext.current

    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(
                        start = 8.dp,
                        end = 8.dp,
                        bottom = 45.dp
                    )
            ) {
                AsyncImage(
                    model = data.image,
                    contentDescription = data.name + " Image",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(MaterialTheme.shapes.medium)
                )
                Spacer(modifier = modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = data.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    if (data.gender == "Male") {
                        Box(
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.tertiaryContainer,
                                    shape = MaterialTheme.shapes.small
                                )
                        ) {
                            Text(
                                text = data.gender,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onTertiaryContainer,
                                modifier = modifier.padding(
                                    start = 10.dp,
                                    end = 10.dp,
                                    top = 6.dp,
                                    bottom = 6.dp
                                )
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.errorContainer,
                                    shape = MaterialTheme.shapes.small
                                )
                        ) {
                            Text(
                                text = data.gender,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onErrorContainer,
                                modifier = modifier.padding(
                                    start = 10.dp,
                                    end = 10.dp,
                                    top = 6.dp,
                                    bottom = 6.dp
                                )
                            )
                        }
                    }
                }
                Spacer(modifier = modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "location",
                        modifier = Modifier.size(18.dp),
                        tint = Color.Red
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "${data.location}m away",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = modifier
                    )
                }
                Spacer(modifier = modifier.height(12.dp))
                Text(
                    text = data.postTime.toTime(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = modifier.height(30.dp))
                Text(
                    text = "My Story",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = modifier.height(12.dp))
                Text(
                    text = data.description,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = modifier.height(33.dp))
                Text(
                    text = "Cat Info",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = modifier.height(18.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(24.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = data.age + " yrs",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                            )
                            Spacer(modifier = modifier.height(5.dp))
                            Text(
                                text = "Age",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.secondary,
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(24.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = data.color,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                            )
                            Spacer(modifier = modifier.height(5.dp))
                            Text(
                                text = "Color",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.secondary,
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(24.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = data.weight,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                            )
                            Spacer(modifier = modifier.height(5.dp))
                            Text(
                                text = "Weight",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.secondary,
                            )
                        }
                    }
                }
                Spacer(modifier = modifier.height(33.dp))
                Text(
                    text = "Owner Info",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = modifier.height(18.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.fillMaxWidth()
                ) {
                    Image(
                        painterResource(id = data.owner.image),
                        contentDescription = "Owner Image",
                        modifier = modifier
                            .padding(6.dp)
                            .size(60.dp)
                            .clip(CircleShape)
                    )
                    Column(
                        modifier = modifier.padding(start = 3.dp)
                    ) {
                        Text(
                            text = data.owner.name,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = modifier.height(5.dp))
                        Text(
                            text = data.owner.role,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = { openWhatsApp(context, data.owner.number) },
                        modifier = Modifier
                            .padding(20.dp)
                            .size(20.dp)
                            .background(Color(0xFF25D366), shape = CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "Contact Me",
                            modifier = Modifier.size(24.dp),
                            tint = Color.White
                        )
                    }
                }
                Spacer(modifier = modifier.height(33.dp))
                if (isListed) {
                    Button(
                        onClick = toggleListPet,
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        shape = CutCornerShape(0f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        )
                    ) {
                        Text(
                            text = "Cancel Adopt!",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                } else {
                    Button(
                        onClick = toggleListPet,
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        shape = CutCornerShape(0f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Text(
                            text = "Adopt Me!",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
        }
    }
}

fun openWhatsApp(context: Context, number: String) {
    val intent = Intent(Intent.ACTION_VIEW)

    intent.data = Uri.parse("https://wa.me/$number")

    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        Toast.makeText(context, "WhatsApp is not installed", Toast.LENGTH_SHORT).show()
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    PetHubTheme {
        DetailContent(
            data = getDummyPetData()[0],
            isListed = false,
            toggleListPet = {},
        )
    }
}