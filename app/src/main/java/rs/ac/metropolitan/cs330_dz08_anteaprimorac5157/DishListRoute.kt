package rs.ac.metropolitan.cs330_dz08_anteaprimorac5157

sealed class DishListRoute(val route: String) {
    object Home : DishListRoute("home")
    object DishDetailScreen : DishListRoute("dishDetailScreen/{dishId}") {
        fun createRoute(dishId: String) = "dishDetailScreen/$dishId"
    }
}
