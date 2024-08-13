package com.example.pethub.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pethub.data.dummy.getDummyPetData
import com.example.pethub.data.model.Pet
import com.example.pethub.data.model.PetEntity

@Composable
fun PetItem(
    modifier: Modifier = Modifier,
    data: Pet,
    onNavigateToDetailScreen: (String) -> Unit,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(
                bottom = 8.dp,
                start = 10.dp,
                end = 10.dp,
            )
            .clickable {
                onNavigateToDetailScreen(data.id)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary)
        ) {
            AsyncImage(
                model = data.image,
                contentDescription = data.name + " Image",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .padding(4.dp)
                    .size(100.dp)
                    .clip(MaterialTheme.shapes.medium)
            )
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = data.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = modifier
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
                Text(
                    text = data.age + " years | " + data.gender,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = modifier.padding(bottom = 3.dp)
                )
                Spacer(modifier = Modifier.width(3.dp))
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
                        text =  "${data.location}m away",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = modifier
                    )
                }
            }
        }
    }
}

@Composable
fun PetItem(
    modifier: Modifier = Modifier,
    data: PetEntity,
    onNavigateToDetailScreen: (String) -> Unit = {},
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(
                bottom = 8.dp,
                start = 10.dp,
                end = 10.dp,
            )
            .clickable {
                onNavigateToDetailScreen(data.id)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary)
        ) {
            AsyncImage(
                model = data.image,
                contentDescription = data.name + " Image",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .padding(4.dp)
                    .size(100.dp)
                    .clip(MaterialTheme.shapes.medium)
            )
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = data.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = modifier
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
                Text(
                    text = data.age + " years | " + data.gender,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = modifier.padding(bottom = 3.dp)
                )
                Spacer(modifier = Modifier.width(3.dp))
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
                        text =  "${data.location}m away",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = modifier
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PetItemPreview() {
    PetItem(
        data = getDummyPetData()[0],
        onNavigateToDetailScreen = {},
    )
}
