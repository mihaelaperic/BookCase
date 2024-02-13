package org.unizd.rma.peric.bookcase.presentation.contact.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import org.unizd.rma.peric.bookcase.domain.models.BookResponseModel

@Composable
fun  DetailsBookScreen(
    navController: NavController,
    detailsBookViewModel: DetailsBookViewModel,
    id: String
) {
    LaunchedEffect(Unit,
        block = {
            detailsBookViewModel.loadBookDetails(id)
        })
    var book: BookResponseModel? = detailsBookViewModel.book

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Podaci o knjizi")
                },
                actions = {
                    Button(onClick = {
                        navController.popBackStack()
                    }) {
                        Text(text = "Back")
                    }
                }
            )
        }
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ){
            Text(text = "Naslov knjige")
            Spacer(modifier = Modifier.width(16.dp))
            Text(book!!.title)
            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Autor")
            Spacer(modifier = Modifier.width(16.dp))
            Text(book.author)
            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Žanr")
            Spacer(modifier = Modifier.width(16.dp))
            Text(book.releaseDate)
            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Datum")
            Spacer(modifier = Modifier.width(16.dp))
            Text(book.genre)
            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(16.dp))

            if (book.coverImage.path?.isNotEmpty() == true) {
                Image(
                    modifier = Modifier.padding(16.dp, 16.dp),
                    painter = rememberImagePainter(book.coverImage),
                    contentDescription = "Image")
            } else {
                Image(
                    modifier = Modifier.padding(16.dp, 16.dp),
                    painter = painterResource(id = org.unizd.rma.peric.bookcase.R.drawable.default_cover),
                    contentDescription = "Image")

            }

        }
    }
}


