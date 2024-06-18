package rs.ac.metropolitan.cs330_dz08_anteaprimorac5157

import android.util.Log
import com.beust.klaxon.Klaxon

data class DishModel(val dishes: List<Dish>) {
    companion object {
        fun fromJson(json: String): DishModel? {
            return try {
                val result = Klaxon().parse<DishModel>(json)
                result
            } catch (e: Exception) {
                Log.e("Antea DishModel", "Error parsing JSON", e)
                null
            }
        }
    }
}

data class Dish(
    val id: String,
    val title: String,
    val difficulty: String,
    val portion: String,
    val time: String,
    val description: String,
    val ingredients: List<String>,
    val method: List<Map<String, String>>,
    val image: String
)
