package rs.ac.metropolitan.cs330_dz08_anteaprimorac5157.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import rs.ac.metropolitan.cs330_dz08_anteaprimorac5157.ui.theme.getDifficultyColor

@Composable
fun DishDetailScreen(vm: DishViewModel, dishId: String) {
    val dish = vm.dishes.value.find { it.id == dishId }

    if (dish != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            AsyncImage(
                model = dish.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(MaterialTheme.colorScheme.surface)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(dish.title, style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
            Text(
                text = dish.portion,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic, color = MaterialTheme.colorScheme.secondary)
            )
            Text(
                text = "Difficulty: ${dish.difficulty}",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic, color = getDifficultyColor(dish.difficulty))
            )
            Text("Time: ${dish.time}", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onBackground)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Ingredients:", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.primary)
            Column(modifier = Modifier.padding(start = 16.dp)) {
                dish.ingredients.forEach { ingredient ->
                    Text("• $ingredient", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Description:", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.primary)
            Text(dish.description, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
        }
    } else {
        Text("Dish not found", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.error)
    }
}
