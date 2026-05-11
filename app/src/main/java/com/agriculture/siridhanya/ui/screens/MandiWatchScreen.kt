package com.agriculture.siridhanya.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agriculture.siridhanya.data.model.MilletPrice
import com.agriculture.siridhanya.viewmodel.PriceViewModel

@Composable
fun MandiWatchScreen(viewModel: PriceViewModel) {
    val prices by viewModel.prices.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val selectedCity by viewModel.selectedCity.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAF8))
            .padding(16.dp)
    ) {
        // Header
        Text(
            "🌾 Mandi Watch",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF558B2F),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            "Live Millet Prices across Karnataka",
            fontSize = 14.sp,
            color = Color(0xFF757575),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // City Filter
        CityFilterChips(selectedCity) { city ->
            viewModel.filterByCity(city)
        }

        // Millet Type Filter
        MilletTypeFilterChips { milletType ->
            viewModel.filterByMilletType(milletType)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Price List
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xFF558B2F))
            }
        } else if (prices.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No prices available", color = Color(0xFF757575))
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(prices) { price ->
                    PriceCard(price, viewModel)
                }
            }
        }
    }
}

@Composable
fun CityFilterChips(selectedCity: String, onCitySelected: (String) -> Unit) {
    val cities = listOf("Davangere", "Bengaluru", "Mysore", "Hubballi")
    
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(bottom = 12.dp)
    ) {
        items(cities.size) { index ->
            val city = cities[index]
            FilterChip(
                selected = city == selectedCity,
                onClick = { onCitySelected(city) },
                label = { Text(city, fontSize = 12.sp) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = Color(0xFF558B2F),
                    selectedLabelColor = Color.White
                )
            )
        }
    }
}

@Composable
fun MilletTypeFilterChips(onMilletSelected: (String) -> Unit) {
    val millets = listOf("Navane", "Sajje", "Baragu", "Jowar")
    
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(bottom = 12.dp)
    ) {
        items(millets.size) { index ->
            val millet = millets[index]
            FilterChip(
                selected = false,
                onClick = { onMilletSelected(millet) },
                label = { Text(millet, fontSize = 12.sp) },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color(0xFFD7CCC8)
                )
            )
        }
    }
}

@Composable
fun PriceCard(price: MilletPrice, viewModel: PriceViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Header with millet type and city
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        price.milletType,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF558B2F)
                    )
                    Text(
                        price.city,
                        fontSize = 12.sp,
                        color = Color(0xFF757575)
                    )
                }
                
                // Trend Indicator
                Box(
                    modifier = Modifier
                        .background(
                            color = when (price.trend) {
                                "up" -> Color(0xFF4CAF50)
                                "down" -> Color(0xFFF44336)
                                else -> Color(0xFFFF9800)
                            },
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp)
                ) {
                    Text(
                        viewModel.getTrendIcon(price.trend),
                        fontSize = 16.sp
                    )
                }
            }

            // Current Price
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        "Current Price",
                        fontSize = 12.sp,
                        color = Color(0xFF757575)
                    )
                    Text(
                        "₹${String.format("%.2f", price.currentPrice)}/kg",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF33691E)
                    )
                }
                
                // Price change percentage
                Text(
                    "${if (price.priceChange > 0) "+" else ""}${String.format("%.2f", price.priceChange)}%",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = when {
                        price.priceChange > 0 -> Color(0xFF4CAF50)
                        price.priceChange < 0 -> Color(0xFFF44336)
                        else -> Color(0xFF757575)
                    }
                )
            }

            // 7-Day Range
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFAFAF8), RoundedCornerShape(8.dp))
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            "7-Day Low",
                            fontSize = 10.sp,
                            color = Color(0xFF757575)
                        )
                        Text(
                            "₹${String.format("%.2f", price.lowPrice)}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFFF44336)
                        )
                    }
                    
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "Range",
                            fontSize = 10.sp,
                            color = Color(0xFF757575)
                        )
                        Text(
                            "₹${String.format("%.2f", price.highPrice - price.lowPrice)}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF558B2F)
                        )
                    }
                    
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            "7-Day High",
                            fontSize = 10.sp,
                            color = Color(0xFF757575)
                        )
                        Text(
                            "₹${String.format("%.2f", price.highPrice)}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF4CAF50)
                        )
                    }
                }
            }
        }
    }
}
