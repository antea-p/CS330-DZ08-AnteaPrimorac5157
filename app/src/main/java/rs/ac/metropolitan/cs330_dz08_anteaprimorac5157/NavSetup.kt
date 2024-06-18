package rs.ac.metropolitan.cs330_dz08_anteaprimorac5157

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import rs.ac.metropolitan.cs330_dz08_anteaprimorac5157.screens.DishDetailScreen
import rs.ac.metropolitan.cs330_dz08_anteaprimorac5157.screens.DishViewModel
import rs.ac.metropolitan.cs330_dz08_anteaprimorac5157.screens.HomeScreen

@Composable
fun NavSetup(navController: NavHostController) {
    val vm: DishViewModel = viewModel()
    vm.navController = navController

    NavHost(navController = navController, startDestination = DishListRoute.Home.route) {
        composable(route = DishListRoute.Home.route) {
            HomeScreen(vm)
        }
        composable(route = DishListRoute.DishDetailScreen.route) { navBackStackEntry ->
            val dishId = navBackStackEntry.arguments?.getString("dishId")
            if (dishId != null) {
                DishDetailScreen(vm, dishId)
            } else {
                Toast.makeText(navController.context, "Error, dishId is required!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
