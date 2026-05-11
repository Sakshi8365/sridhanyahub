package com.agriculture.siridhanya.data.mock

import com.agriculture.siridhanya.data.model.*

object MockData {
    // Mock Millet Prices
    fun getMockMilletPrices(): List<MilletPrice> = listOf(
        MilletPrice(
            id = "price_1",
            milletType = "Navane",
            city = "Bengaluru",
            currentPrice = 45.50,
            lowPrice = 42.00,
            highPrice = 48.75,
            priceChange = 3.2,
            trend = "up"
        ),
        MilletPrice(
            id = "price_2",
            milletType = "Sajje",
            city = "Davangere",
            currentPrice = 38.00,
            lowPrice = 35.50,
            highPrice = 40.00,
            priceChange = 2.5,
            trend = "up"
        ),
        MilletPrice(
            id = "price_3",
            milletType = "Baragu",
            city = "Mysore",
            currentPrice = 52.00,
            lowPrice = 50.00,
            highPrice = 55.50,
            priceChange = -1.5,
            trend = "down"
        ),
        MilletPrice(
            id = "price_4",
            milletType = "Jowar",
            city = "Hubballi",
            currentPrice = 41.25,
            lowPrice = 40.00,
            highPrice = 43.00,
            priceChange = 0.5,
            trend = "stable"
        )
    )

    // Mock Recipes
    fun getMockRecipes(): List<Recipe> = listOf(
        Recipe(
            id = "recipe_1",
            name = "Navane Uppitu",
            milletType = "Navane",
            description = "Traditional breakfast dish made with roasted Navane flour",
            ingredients = listOf("Navane flour", "Ghee", "Onions", "Green chili", "Salt"),
            instructions = listOf(
                "Roast the millet flour in a pan",
                "Add ghee and sauté onions",
                "Mix in the roasted flour",
                "Add water and cook until done"
            ),
            cookingTimeMinutes = 20,
            servings = 4,
            difficulty = "easy",
            imageUrl = "",
            nutritionInfo = "Rich in fiber and minerals",
            healthBenefits = "Good for digestion, high in antioxidants",
            tips = "Use ghee for authentic taste"
        ),
        Recipe(
            id = "recipe_2",
            name = "Sajje Ambali",
            milletType = "Sajje",
            description = "Cooling fermented drink perfect for summer",
            ingredients = listOf("Sajje flour", "Yogurt", "Cumin seeds", "Water", "Salt"),
            instructions = listOf(
                "Make flour paste with water",
                "Ferment for 6-8 hours",
                "Add yogurt and cumin seeds",
                "Blend and serve chilled"
            ),
            cookingTimeMinutes = 15,
            servings = 2,
            difficulty = "medium",
            imageUrl = "",
            nutritionInfo = "Probiotic rich",
            healthBenefits = "Improves digestion, cooling effect",
            tips = "Ferment in warm place for best results"
        ),
        Recipe(
            id = "recipe_3",
            name = "Baragu Roti",
            milletType = "Baragu",
            description = "Nutritious flatbread made with finger millet",
            ingredients = listOf("Baragu flour", "Salt", "Water", "Oil"),
            instructions = listOf(
                "Mix flour with water and salt",
                "Knead the dough",
                "Roll thin rotis",
                "Cook on hot griddle"
            ),
            cookingTimeMinutes = 25,
            servings = 4,
            difficulty = "easy",
            imageUrl = "",
            nutritionInfo = "High in calcium and iron",
            healthBenefits = "Strengthens bones, aids in muscle development",
            tips = "Keep dough covered while making rotis"
        )
    )

    // Mock Health Benefits
    fun getMockHealthBenefits(): List<HealthBenefit> = listOf(
        HealthBenefit(
            id = "health_1",
            milletType = "Navane",
            title = "Why Navane is Good for Diabetics",
            description = "Navane (Foxtail millet) is an excellent choice for people with diabetes due to its low glycemic index.",
            benefits = listOf(
                "Low Glycemic Index (GI) prevents blood sugar spikes",
                "High fiber content aids in glucose regulation",
                "Rich in antioxidants that protect beta cells",
                "Contains essential minerals like manganese"
            ),
            scientificFacts = listOf(
                "Studies show Navane has GI of only 54 (compared to white rice's 73)",
                "100g of Navane provides 6.7g of fiber - crucial for diabetic management",
                "Contains phenolic compounds that improve insulin sensitivity"
            ),
            imageUrl = "",
            readingTimeMinutes = 3
        ),
        HealthBenefit(
            id = "health_2",
            milletType = "Baragu",
            title = "Baragu - A Superfood for Strong Bones",
            description = "Baragu (Finger millet) is the richest plant source of calcium among cereals.",
            benefits = listOf(
                "Highest calcium content among millets (344mg per 100g)",
                "Prevents osteoporosis and bone weakening",
                "Rich in phosphorus for bone metabolism",
                "Beneficial for children's growth and development"
            ),
            scientificFacts = listOf(
                "Contains 10x more calcium than rice or wheat",
                "Bioavailable calcium is better absorbed compared to milk",
                "Provides all essential amino acids for bone protein formation"
            ),
            imageUrl = "",
            readingTimeMinutes = 4
        ),
        HealthBenefit(
            id = "health_3",
            milletType = "Sajje",
            title = "Sajje - Climate Champion & Nutrition Hero",
            description = "Sajje (Pearl millet) requires 70% less water than paddy, making it perfect for climate-resilient agriculture.",
            benefits = listOf(
                "Climate resilient - thrives in drought conditions",
                "Complete protein source for vegetarians",
                "High in magnesium for heart health",
                "Supports immune system with selenium"
            ),
            scientificFacts = listOf(
                "Requires only 300-400mm rainfall vs paddy's 1200-1500mm",
                "100g Sajje provides 11g of protein",
                "Rich in magnesium (important for 300+ enzyme reactions in body)"
            ),
            imageUrl = "",
            readingTimeMinutes = 3
        )
    )

    // Mock FPOs
    fun getMockFarmersOrganizations(): List<FarmersOrganization> = listOf(
        FarmersOrganization(
            id = "fpo_1",
            name = "Davangere Millet Farmers Cooperative",
            location = "Davangere",
            district = "Davangere",
            contact = "9876543210",
            milletSpecialties = listOf("Navane", "Sajje", "Jowar"),
            imageUrl = "",
            description = "Established cooperative of 500+ millet farmers dedicated to quality production.",
            website = "www.davangere-millets.co.in",
            certification = "Organic"
        ),
        FarmersOrganization(
            id = "fpo_2",
            name = "Bengaluru Urban Farming Network",
            location = "Bengaluru",
            district = "Bengaluru",
            contact = "9876543211",
            milletSpecialties = listOf("Navane", "Baragu"),
            imageUrl = "",
            description = "Urban FPO promoting sustainable millet farming in and around Bengaluru.",
            website = "www.bengaluru-millets.in",
            certification = "Fair Trade"
        ),
        FarmersOrganization(
            id = "fpo_3",
            name = "Mysore Millet Heritage",
            location = "Mysore",
            district = "Mysore",
            contact = "9876543212",
            milletSpecialties = listOf("Baragu", "Sajje"),
            imageUrl = "",
            description = "Traditional farmers preserving ancient millet varieties with heritage cultivation methods.",
            website = "www.mysore-millets.org",
            certification = "Heritage Certified"
        )
    )
}
