package org.unizd.rma.peric.bookcase

import android.content.res.Resources.Theme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import kotlin.contracts.contract

import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.unizd.rma.peric.bookcase.ui.theme.*
import org.unizd.rma.peric.bookcase.presentation.contact.create.CreateBookScreen
import org.unizd.rma.peric.bookcase.presentation.contact.create.CreateBookViewModel
import org.unizd.rma.peric.bookcase.presentation.contact.details.DetailsBookScreen
import org.unizd.rma.peric.bookcase.presentation.contact.details.DetailsBookViewModel
import org.unizd.rma.peric.bookcase.presentation.contact.edit.UpdateBookScreen
import org.unizd.rma.peric.bookcase.presentation.contact.edit.UpdateBookViewModel
import org.unizd.rma.peric.bookcase.presentation.contact.list.ListBookScreen
import org.unizd.rma.peric.bookcase.presentation.contact.list.ListBooksViewModel


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BooksTheme {
                val navController = rememberNavController()
                Router(navController = navController)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Bok $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BooksTheme {
        Greeting("Android")
    }
}

@Composable
fun Router(navController: NavHostController) {
    val listBooksViewModel: ListBooksViewModel = hiltViewModel()
    val detailsBooksViewModel: DetailsBookViewModel = hiltViewModel()
    val createBookViewModel: CreateBookViewModel = hiltViewModel()
    val updateBookViewModel: UpdateBookViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "list") {

        composable("list") {
            ListBookScreen(navController = navController, listBookViewModel = listBooksViewModel)
        }

        composable("create") {

            CreateBookScreen(navController = navController, createBookViewModel)
        }

        // Update these on new solution
        composable("details/{id}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("id")
            //val expense = listExpenseViewModel.getExpenseById(itemId!!)
            if (itemId != null) {
                DetailsBookScreen(navController = navController, detailsBookViewModel = detailsBooksViewModel, itemId)
            }
        }

        composable("update{id}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("id")
            if (itemId != null) {
                UpdateBookScreen(navController = navController, updateBookViewModel, itemId)
            }

        }

    }
}


