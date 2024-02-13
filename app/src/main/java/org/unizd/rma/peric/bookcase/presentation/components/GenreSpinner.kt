package org.unizd.rma.peric.bookcase.presentation.components

import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.unizd.rma.peric.bookcase.presentation.contact.create.CreateBookViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GenreSpinnerExample(createBookViewModel: CreateBookViewModel) {
    var isExpanded by remember {
        mutableStateOf(false)
    }



    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it }) {

        TextField(
            value = createBookViewModel.genre,
            onValueChange = {
                // This can be left empty, as the value is managed by the ViewModel
            },
            readOnly = true,
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }) {

            listOf("LJUBAVNI", "TRILER", "FANTASTIKA", "DRAMA").forEach { genre ->
                DropdownMenuItem(
                    onClick = {
                        createBookViewModel.onGenreChange(genre)
                        isExpanded = false
                    }) {

                    Text(text = genre)
                }
            }
        }
    }
}