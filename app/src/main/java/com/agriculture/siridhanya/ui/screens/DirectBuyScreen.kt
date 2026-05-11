package com.agriculture.siridhanya.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
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
import com.agriculture.siridhanya.data.model.FarmersOrganization
import com.agriculture.siridhanya.viewmodel.DirectBuyViewModel

@Composable
fun DirectBuyScreen(viewModel: DirectBuyViewModel) {
    val organizations by viewModel.organizations.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAF8))
            .padding(16.dp)
    ) {
        // Header
        Text(
            "🌾 Direct Buy",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF558B2F),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            "Connect with Farmer Producer Organizations (FPO)",
            fontSize = 14.sp,
            color = Color(0xFF757575),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // District Filter
        DirectBuyDistrictFilterChips { district ->
            viewModel.filterByDistrict(district)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Organizations List
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xFF558B2F))
            }
        } else if (organizations.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No FPOs available in this district", color = Color(0xFF757575))
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(organizations) { org ->
                    FPOCard(org)
                }
            }
        }
    }
}

@Composable
fun DirectBuyDistrictFilterChips(onDistrictSelected: (String) -> Unit) {
    val districts = listOf(
        "Davangere", "Bengaluru", "Mysore", "Hubballi",
        "Belgaum", "Tumkur", "Chitradurga", "Madhugiri"
    )
    
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(bottom = 12.dp)
    ) {
        items(minOf(4, districts.size)) { index ->
            val district = districts[index]
            FilterChip(
                selected = false,
                onClick = { onDistrictSelected(district) },
                label = { Text(district, fontSize = 11.sp) },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color(0xFFD7CCC8)
                )
            )
        }
    }
}

@Composable
fun FPOCard(fpo: FarmersOrganization) {
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
            // Organization Header
            Column(modifier = Modifier.padding(bottom = 12.dp)) {
                Text(
                    fpo.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF558B2F)
                )
                Text(
                    "${fpo.location}, ${fpo.district}",
                    fontSize = 12.sp,
                    color = Color(0xFF757575)
                )
            }

            // Description
            Text(
                fpo.description,
                fontSize = 12.sp,
                color = Color(0xFF424242),
                modifier = Modifier.padding(bottom = 12.dp),
                maxLines = 2
            )

            // Specialties
            if (fpo.milletSpecialties.isNotEmpty()) {
                Text(
                    "Specialties",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF558B2F),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    fpo.milletSpecialties.forEach { specialty ->
                        Text(
                            specialty,
                            fontSize = 10.sp,
                            color = Color(0xFF558B2F),
                            modifier = Modifier
                                .background(Color(0xFFE8F5E9), RoundedCornerShape(4.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            // Certification & Info
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFAFAF8), RoundedCornerShape(8.dp))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        "Certification",
                        fontSize = 10.sp,
                        color = Color(0xFF757575)
                    )
                    Text(
                        fpo.certification,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF4CAF50)
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        "Contact",
                        fontSize = 10.sp,
                        color = Color(0xFF757575)
                    )
                    Text(
                        fpo.contact.takeLast(6),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF558B2F)
                    )
                }
            }

            // Action Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { },
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF558B2F)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        Icons.Filled.Phone,
                        contentDescription = "Call",
                        modifier = Modifier.size(16.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Contact", color = Color.White, fontSize = 12.sp)
                }

                Button(
                    onClick = { },
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF8D6E63)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Browse Products", color = Color.White, fontSize = 12.sp)
                }
            }
        }
    }
}
