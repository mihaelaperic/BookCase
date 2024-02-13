package org.unizd.rma.peric.bookcase.presentation.contact.create

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.unizd.rma.books.presentation.components.TextInput
import kotlinx.coroutines.runBlocking
import org.unizd.rma.peric.bookcase.R
import org.unizd.rma.peric.bookcase.presentation.components.GenreSpinnerExample
import org.unizd.rma.peric.bookcase.presentation.components.showDatePicker
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

@Composable
fun CreateBookScreen(
    navController: NavController,
    createBookViewModel: CreateBookViewModel = hiltViewModel()
) {
    Log.d("CreateBookScreen", "Create")

    val context = LocalContext.current

    val book = LocalContext.current
    val file = book.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(book),
        book.packageName + ".provider",
        file
    )

    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()) {
        Log.d("CreateBookScreen", "CameraLauncher $it")
        capturedImageUri = uri
        createBookViewModel.onImageUriChange(capturedImageUri.toString())
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()) {

        if(it) {
            Toast.makeText(book, "Permission granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(book, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }


    Scaffold(
        topBar = {
            TopAppBar (
                title = {
                    Text(text = "Nova knjiga")
                },
                actions = {
                    Button(onClick = {
                        runBlocking {
                            createBookViewModel.createBook()
                            navController.popBackStack()
                        }
                    }) {
                        Text(text = "Spremi")
                    }
                }
            )
        }
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Ime knjige")
            Spacer(modifier = Modifier.width(16.dp))
            TextInput(
                value = createBookViewModel.name,
                onChange = {
                           value ->
                    createBookViewModel.onNameChange(value)
                },
                placeholder = "Unesite naslov")

            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(25.dp))

            Text(text = "Autor")
            Spacer(modifier = Modifier.width(16.dp))

            val doublePattern = remember { Regex("^\\d*\\.?\\d*\$") }
            var text by remember { mutableStateOf(createBookViewModel.author) }

            TextInput(
                value = createBookViewModel.author,
                onChange = {
                        value ->
                    createBookViewModel.onAuthorChange(value)
                    //createbookViewModel.onNameChange(it)
                },
                placeholder = "Unesite ime autora."

            )


            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(25.dp))

            Text(text = "Izaberite žanr")
            Spacer(modifier = Modifier.width(16.dp))
            // category dropdown goes here
            GenreSpinnerExample(createBookViewModel)

            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(25.dp))

            Text(text = "Datum izdavanja")
            Spacer(modifier = Modifier.width(16.dp))

            showDatePicker(context, createBookViewModel = createBookViewModel)

            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(25.dp))

            Button(onClick = {
                val permissionCheckResult = ContextCompat.checkSelfPermission(book,
                    Manifest.permission.CAMERA)

                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED){
                    cameraLauncher.launch(uri)
                } else {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }) {
                Text(text = "Dodaj fotografiju.")

            }

            Spacer(modifier = Modifier.width(16.dp))

            if (capturedImageUri.path?.isNotEmpty() == true) {
                Image(
                    modifier = Modifier.padding(16.dp, 16.dp),
                    painter = rememberImagePainter(capturedImageUri),
                    contentDescription = "Image")
            } else {
                Image(
                    modifier = Modifier.padding(16.dp, 16.dp),
                    painter = painterResource(id = R.drawable.default_cover),
                    contentDescription = "Image")
            }




        }

    }


}


fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyy_MM_dd_HH-mm-ss").format(Date())
    val imageFileName = "image" + timeStamp + "_"
    return File.createTempFile(
        imageFileName,
        ".jpg",
        externalCacheDir
    )
}