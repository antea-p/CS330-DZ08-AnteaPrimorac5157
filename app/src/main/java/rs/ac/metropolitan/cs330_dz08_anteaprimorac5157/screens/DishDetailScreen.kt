package rs.ac.metropolitan.cs330_dz08_anteaprimorac5157.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter

@Composable
fun DishDetailScreen(vm: DishViewModel, dishId: String) {
    val dish = vm.dishes.value.find { it.id == dishId }

    if (dish != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = dish.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(dish.title, style = MaterialTheme.typography.h4)
            Text("Difficulty: ${dish.difficulty}", style = MaterialTheme.typography.body1)
            Text("Serves: ${dish.portion}", style = MaterialTheme.typography.body1)
            Text("Time: ${dish.time}", style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Ingredients:", style = MaterialTheme.typography.h6)
            dish.ingredients.forEach { ingredient ->
                Text(ingredient, style = MaterialTheme.typography.body2)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Description:", style = MaterialTheme.typography.h6)
            Text(dish.description, style = MaterialTheme.typography.body2)
        }
    } else {
        Text("Dish not found", style = MaterialTheme.typography.h6)
    }
}
