package de.milchschlumpf.navigationcomposebug

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val (currentSection, setCurrentSection) = rememberSaveable {
        mutableStateOf(HomeSections.Home1)
    }
    val navItems = HomeSections.values().toList()

    MaterialTheme {
        Scaffold(bottomBar = {
            BottomNav(
                currentSection = currentSection,
                onSectionSelected = setCurrentSection,
                items = navItems,
                navController = navController
            )
        }) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavHost(
                    navController = navController,
                    startDestination = HomeSections.Home1.navigation
                ) {
                    composable(HomeSections.Home1.navigation) {
                        HomeScreen("Home1")
                    }
                    composable(HomeSections.Home2.navigation) {
                        HomeScreen("Home2")
                    }
                    composable(HomeSections.Home3.navigation) {
                        HomeScreen("Home3")
                    }
                    composable(HomeSections.Home4.navigation) {
                        HomeScreen("Home4")
                    }
                }
            }
        }
    }
}

enum class HomeSections(
    val resourceId: Int,
    val resourceIdSelected: Int,
    val navigation: String,
) {
    Home1(R.drawable.ic_outline_home, R.drawable.ic_baseline_home, "home1"),
    Home2(R.drawable.ic_outline_home, R.drawable.ic_baseline_home, "home2"),
    Home3(R.drawable.ic_outline_home, R.drawable.ic_baseline_home, "home3"),
    Home4(R.drawable.ic_outline_home, R.drawable.ic_baseline_home, "home4")
}