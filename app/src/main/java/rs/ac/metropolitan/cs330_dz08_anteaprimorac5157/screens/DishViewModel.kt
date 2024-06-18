package rs.ac.metropolitan.cs330_dz08_anteaprimorac5157.screens

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.beust.klaxon.JsonParsingException
import com.beust.klaxon.Klaxon
import rs.ac.metropolitan.cs330_dz08_anteaprimorac5157.Dish
import rs.ac.metropolitan.cs330_dz08_anteaprimorac5157.DishListRoute
import rs.ac.metropolitan.cs330_dz08_anteaprimorac5157.DishModel
import java.net.URL


class DishViewModel : ViewModel() {
    lateinit var navController: NavHostController
    var granted = mutableStateOf(false)
    var dishes = mutableStateOf(listOf<Dish>())

    init {
        try {
            val jsonFile = loadResource("assets/mexicanfood.json").readText()
            val dishList: List<Dish> = Klaxon().parseArray(jsonFile) ?: emptyList()
            dishes.value = dishList
        } catch (e: Exception) {
            Log.e("Antea DishViewModel", "Error loading JSON", e)
        }
    }

    private fun loadResource(path: String): URL {
        println("Loading resource: $path")
        return Thread.currentThread().contextClassLoader.getResource(path)
    }


    fun navigateToDishDetails(id: String) {
        navController.navigate(DishListRoute.DishDetailScreen.createRoute(id))
    }

    fun goBack() {
        navController.popBackStack()
    }
}