package rs.ac.metropolitan.cs330_dz08_anteaprimorac5157.screens

import android.Manifest
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import rs.ac.metropolitan.cs330_dz08_anteaprimorac5157.Dish
import rs.ac.metropolitan.cs330_dz08_anteaprimorac5157.ui.theme.Typography

@Composable
fun HomeScreen(vm: DishViewModel) {
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        vm.granted.value = isGranted
    }

    Column {
        if (!vm.granted.value) {
            InternetPermission(launcher)
        } else {
            Log.d("Antea HomeScreen", "Granted internet permission")
            ListDishes(vm = vm)
        }
    }
}

@Composable
private fun InternetPermission(launcher: ManagedActivityResultLauncher<String, Boolean>) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Internet permission not granted",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Magenta,
                modifier = Modifier.padding(8.dp)
            )
            Button(onClick = { launcher.launch(Manifest.permission.INTERNET) }) {
                Text("Request permission")
            }
        }
    }
}

@Composable
fun ListDishes(vm: DishViewModel = viewModel()) {
    LazyColumn {
        items(vm.dishes.value) { dish ->
            PresentDish(dish = dish) {
                vm.navigateToDishDetails(it)
            }
        }
    }
}

@Composable
fun PresentDish(dish: Dish, onDishSelected: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onDishSelected(dish.id) }
    ) {
        Image(
            painter = rememberImagePainter(data = dish.image),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(dish.title, style = Typography.bodyLarge)
            Text(dish.portion, style = Typography.bodyMedium, color = Color.Gray)
            Text(dish.difficulty, style = Typography.bodyMedium)
        }
    }
}

@Preview
@Composable
fun InternetPermissionPreview() {
    InternetPermission(rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { })
}

@Preview
@Composable
fun ListUsersPreview() {
    val vm: DishViewModel = viewModel()
    vm.navController = rememberNavController()
    ListDishes(vm)
}

