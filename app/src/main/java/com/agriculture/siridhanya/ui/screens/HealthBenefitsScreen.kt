package com.agriculture.siridhanya.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.agriculture.siridhanya.data.model.HealthBenefit
import com.agriculture.siridhanya.viewmodel.HealthBenefitViewModel

@Composable
fun HealthBenefitsScreen(viewModel: HealthBenefitViewModel) {
    val healthBenefits by viewModel.healthBenefits.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val selectedMillet by viewModel.selectedMilletType.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAF8))
            .padding(16.dp)
    ) {
        // Header
        Text(
            "❤️ Health Benefits",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF558B2F),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            "Why Millets Are Superfoods",
            fontSize = 14.sp,
            color = Color(0xFF757575),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Millet Type Filter
        HealthMilletFilterChips(selectedMillet) { millet ->
            viewModel.filterByMilletType(millet)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Health Benefits List
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xFF558B2F))
            }
        } else if (healthBenefits.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No information available", color = Color(0xFF757575))
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(healthBenefits) { benefit ->
                    HealthBenefitCard(benefit)
                }
            }
        }
    }
}

@Composable
fun HealthMilletFilterChips(selectedMillet: String, onMilletSelected: (String) -> Unit) {
    val millets = listOf("Navane", "Sajje", "Baragu", "Jowar")
    
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(bottom = 12.dp)
    ) {
        items(millets.size) { index ->
            val millet = millets[index]
            FilterChip(
                selected = millet == selectedMillet,
                onClick = { onMilletSelected(millet) },
                label = { Text(millet, fontSize = 12.sp) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = Color(0xFF558B2F),
                    selectedLabelColor = Color.White
                )
            )
        }
    }
}

@Composable
fun HealthBenefitCard(benefit: HealthBenefit) {
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
            // Title and millet type
            Column(modifier = Modifier.padding(bottom = 12.dp)) {
                Text(
                    benefit.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF558B2F)
                )
                Text(
                    benefit.milletType,
                    fontSize = 12.sp,
                    color = Color(0xFF757575)
                )
            }

            // Description
            Text(
                benefit.description,
                fontSize = 13.sp,
                color = Color(0xFF424242),
                modifier = Modifier.padding(bottom = 12.dp),
                lineHeight = 18.sp
            )

            // Benefits List
            if (benefit.benefits.isNotEmpty()) {
                Text(
                    "Key Benefits",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF558B2F),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                benefit.benefits.forEach { benef ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 6.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            "✓",
                            fontSize = 12.sp,
                            color = Color(0xFF4CAF50),
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            benef,
                            fontSize = 12.sp,
                            color = Color(0xFF424242)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
            }

            // Scientific Facts
            if (benefit.scientificFacts.isNotEmpty()) {
                Text(
                    "Scientific Facts",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF558B2F),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                benefit.scientificFacts.take(2).forEach { fact ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF1F8E9), RoundedCornerShape(8.dp))
                            .padding(10.dp)
                            .padding(bottom = 8.dp)
                    ) {
                        Text(
                            fact,
                            fontSize = 11.sp,
                            color = Color(0xFF33691E),
                            lineHeight = 16.sp
                        )
                    }
                }
            }

            // Reading time
            Text(
                "📖 ${benefit.readingTimeMinutes} min read",
                fontSize = 10.sp,
                color = Color(0xFF9CCC65),
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }
}
