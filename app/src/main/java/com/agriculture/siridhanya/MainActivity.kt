package com.agriculture.siridhanya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agriculture.siridhanya.ui.screens.*
import com.agriculture.siridhanya.viewmodel.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SiriDhanyaHubApp()
        }
    }
}

@Composable
fun SiriDhanyaHubApp() {
    var selectedTab by remember { mutableIntStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFAFAF8)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Content Area
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                when (selectedTab) {
                    0 -> MandiWatchScreen(viewModel = viewModel<PriceViewModel>())
                    1 -> RecipeLabScreen(viewModel = viewModel<RecipeViewModel>())
                    2 -> HealthBenefitsScreen(viewModel = viewModel<HealthBenefitViewModel>())
                    3 -> DirectBuyScreen(viewModel = viewModel<DirectBuyViewModel>())
                }
            }

            // Bottom Navigation Bar
            NavigationBar(
                modifier = Modifier
                    .background(Color.White),
                containerColor = Color.White,
                contentColor = Color(0xFF558B2F)
            ) {
                val items = listOf(
                    NavigationItem("Mandi", Icons.Filled.Home, 0),
                    NavigationItem("Recipes", Icons.Filled.FavoriteBorder, 1),
                    NavigationItem("Health", Icons.Filled.Info, 2),
                    NavigationItem("Buy", Icons.Filled.ShoppingCart, 3)
                )

                items.forEach { item ->
                    NavigationBarItem(
                        selected = selectedTab == item.id,
                        onClick = { selectedTab = item.id },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label,
                                modifier = Modifier.padding(4.dp)
                            )
                        },
                        label = {
                            Text(
                                item.label,
                                fontSize = androidx.compose.material3.MaterialTheme.typography.labelSmall.fontSize
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF558B2F),
                            selectedTextColor = Color(0xFF558B2F),
                            unselectedIconColor = Color(0xFF9E9E9E),
                            unselectedTextColor = Color(0xFF9E9E9E),
                            indicatorColor = Color(0xFFE8F5E9)
                        )
                    )
                }
            }
        }
    }
}

data class NavigationItem(
    val label: String,
    val icon: ImageVector,
    val id: Int
)
