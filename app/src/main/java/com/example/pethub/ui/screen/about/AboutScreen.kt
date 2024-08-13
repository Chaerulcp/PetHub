package com.example.pethub.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pethub.R

@Preview(showBackground = true)
@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier.padding(top = 25.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.pict1),
            contentDescription = "Developer Profile",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .height(400.dp)
                .width(400.dp)
                .padding(33.dp)
                .fillMaxWidth()
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "CHAERUL CANDRA PRANUGRAH",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "chaerulcandrap@gmail.com",
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}
