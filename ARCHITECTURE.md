# Siri-Dhanya Hub - Project Architecture & Design

## Project Overview

**Siri-Dhanya Hub** is an Android application designed to empower both farmers and consumers in Karnataka's millet ecosystem. The app bridges the gap between producers and consumers through:

- **Real-time Market Intelligence** (Mandi Watch)
- **Digital Recipe Repository** (Recipe Lab)
- **Health & Nutrition Education** (Health Benefits)
- **Direct Farmer Connections** (Direct Buy)

## Architecture Pattern: MVVM + Repository

```
┌─────────────────────────────────────────────────────────┐
│              UI Layer (Jetpack Compose)                 │
│  ┌─────────────────────────────────────────────────────┐│
│  │ Screen Composables                                  ││
│  │ • MandiWatchScreen  • RecipeLabScreen              ││
│  │ • HealthBenefitsScreen  • DirectBuyScreen          ││
│  └─────────────────────────────────────────────────────┘│
│                           ↕                              │
│  ┌─────────────────────────────────────────────────────┐│
│  │ ViewModels (Business Logic)                         ││
│  │ • PriceViewModel  • RecipeViewModel                 ││
│  │ • HealthBenefitViewModel  • DirectBuyViewModel      ││
│  └─────────────────────────────────────────────────────┘│
└─────────────────────────────────────────────────────────┘
                           ↕
┌─────────────────────────────────────────────────────────┐
│          Data Layer (Firebase & Repositories)           │
│  ┌─────────────────────────────────────────────────────┐│
│  │ Repositories (Data Access)                          ││
│  │ • PriceRepository  • RecipeRepository               ││
│  │ • HealthBenefitRepository  • DirectBuyRepository    ││
│  └─────────────────────────────────────────────────────┘│
│                           ↕                              │
│  ┌─────────────────────────────────────────────────────┐│
│  │ Data Sources                                        ││
│  │ • Firebase Realtime Database                        ││
│  │ • Google Generative AI API                          ││
│  │ • Glide Image Cache                                 ││
│  └─────────────────────────────────────────────────────┘│
└─────────────────────────────────────────────────────────┘
                           ↕
┌─────────────────────────────────────────────────────────┐
│              Model Layer (Data Classes)                  │
│  ┌─────────────────────────────────────────────────────┐│
│  │ • MilletPrice  • Recipe  • HealthBenefit           ││
│  │ • FarmersOrganization  • User  • SavedRecipe        ││
│  └─────────────────────────────────────────────────────┘│
└─────────────────────────────────────────────────────────┘
```

## Data Flow

### Example: Mandi Watch Feature

```
User Action (Select City)
        ↓
MandiWatchScreen (Composable)
        ↓
PriceViewModel.filterByCity(city)
        ↓
PriceRepository.getPricesByCity(city)
        ↓
Firebase.getReference("prices")
        ↓
Flow<List<MilletPrice>>
        ↓
PriceViewModel._prices.value = newList
        ↓
MandiWatchScreen recomposes
        ↓
UI displays updated prices
```

## Firebase Database Structure

```
siri-dhanya-hub/
├── prices/
│   ├── price_1: {milletType, city, currentPrice, lowPrice, highPrice, trend}
│   ├── price_2: {...}
│   └── ...
├── recipes/
│   ├── recipe_1: {name, milletType, ingredients, instructions, ...}
│   ├── recipe_2: {...}
│   └── ...
├── healthBenefits/
│   ├── health_1: {milletType, title, benefits, scientificFacts, ...}
│   ├── health_2: {...}
│   └── ...
├── organizations/
│   ├── fpo_1: {name, location, district, milletSpecialties, ...}
│   ├── fpo_2: {...}
│   └── ...
├── listings/
│   ├── listing_1: {organizationId, milletType, price, quantity, ...}
│   ├── listing_2: {...}
│   └── ...
└── users/
    ├── userId_1:
    │   ├── savedRecipes: [recipeId_1, recipeId_2, ...]
    │   ├── watchedPrices: [location_1, millet_1, ...]
    │   └── userType: "farmer" or "consumer"
    └── userId_2: {...}
```

## Key Design Decisions

### 1. **Jetpack Compose Over XML**

- ✅ Modern, declarative UI framework
- ✅ Easier to learn and maintain
- ✅ Better state management
- ✅ Hot reload during development

### 2. **Firebase Realtime Database**

- ✅ Real-time updates for price changes
- ✅ Easy integration with Android
- ✅ Scalable for growing user base
- ✅ Built-in authentication

### 3. **Flow-based State Management**

- ✅ Reactive data handling
- ✅ Efficient memory management
- ✅ Automatic recomposition on data change
- ✅ Thread-safe operations

### 4. **Repository Pattern**

- ✅ Separation of concerns
- ✅ Easy to unit test
- ✅ Centralized data access logic
- ✅ Flexible to switch data sources

## Feature Implementation Details

### 1. Mandi Watch (Price Tracking)

**Key Components:**

- `PriceViewModel`: Manages price data and filtering
- `PriceRepository`: Fetches prices from Firebase
- `MandiWatchScreen`: Displays prices with trend indicators

**Algorithms:**

```kotlin
// Price Trend Calculation
fun getTrendForPrice(lowPrice, highPrice, currentPrice) {
    return when {
        currentPrice >= highPrice * 0.95 -> "up"    // Within 5% of high
        currentPrice <= lowPrice * 1.05 -> "down"   // Within 5% of low
        else -> "stable"
    }
}

// Percentage Change
fun calculatePriceChange(oldPrice, newPrice) {
    return if (oldPrice == 0.0) 0.0 else ((newPrice - oldPrice) / oldPrice) * 100
}
```

### 2. Recipe Lab (Recipe Management)

**Key Features:**

- Search recipes by name, millet type
- Save/unsave favorite recipes
- Display step-by-step instructions
- Show nutritional information

**Data Persistence:**

- Saved recipes stored in Firebase under `users/{userId}/savedRecipes`
- Uses Kotlin Flow for reactive updates

### 3. Health Benefits (Educational Content)

**Content Structure:**

- Millet type (Navane, Sajje, Baragu, Jowar)
- Health condition benefits (Diabetes, Bone Health, etc.)
- Scientific facts and studies
- Key benefits list

**Rendering:**

- Expandable cards for benefits
- Scientific facts in highlighted boxes
- Reading time estimation

### 4. Direct Buy (FPO Connection)

**Features:**

- List of Farmer Producer Organizations
- Filtered by district or millet type
- Direct contact information
- Product listings and pricing
- Certification status

## UI/UX Design System

### Color Palette (Earthy & Natural)

```
Primary Green: #558B2F    (Earthy green - nature, growth)
Dark Green: #33691E       (Deep green - stability)
Brown: #8D6E63            (Earthy brown - agriculture)
Gold: #B8860B             (Harvest gold - prosperity)

Supporting Colors:
Green accent: #AED581     (Light green - freshness)
Tan: #D7CCC8              (Natural tan - earth)
Cream: #EFEBE9            (Warm cream - comfort)

Status Colors:
Trend Up: #4CAF50         (Green - positive)
Trend Down: #F44336       (Red - negative)
Trend Stable: #FF9800     (Orange - neutral)
```

### Typography

- **Headlines (28sp)**: Bold, Green - Main titles
- **Titles (18sp)**: SemiBold, Green - Section headers
- **Body (12-14sp)**: Regular, Gray - Content text
- **Labels (10-12sp)**: Regular, Light Gray - Descriptions

### Components

- **Cards**: White background, rounded 12dp, subtle shadow
- **Buttons**: Full-width, rounded 8dp, earthy colors
- **Chips**: Rounded filter chips with border
- **Icons**: Material Design + custom emojis (🌾, 📖, ❤️)

## Testing Strategy

### Unit Tests

- Repository methods with mock Firebase
- ViewModel business logic
- Data model serialization/deserialization

### Integration Tests

- Firebase data retrieval
- Search and filter functionality
- Save/unsave operations

### UI Tests

- Screen navigation
- Button interactions
- Data display correctness

## Performance Optimization

1. **Lazy Loading**
   - Use LazyColumn for efficient list rendering
   - RecyclerView-like behavior with Compose

2. **Image Optimization**
   - Glide for caching and loading
   - Image compression before Firebase storage

3. **Database Queries**
   - Filter on client-side where possible
   - Limit data retrieval with specific references

4. **Memory Management**
   - StateFlow for efficient state management
   - Proper cleanup in ViewModels

## Security Measures

1. **Firebase Rules** (Restrict unauthorized access)
2. **API Key Management** (Secure key storage)
3. **Data Validation** (Input validation on all forms)
4. **SSL/TLS** (Encrypted network communication)

## Scalability Plan

### Phase 1 (Current)

- Core 4 features
- Mock data initially
- Single region (Karnataka)

### Phase 2 (3 months)

- Real-time price updates via API
- User authentication
- Multiple states in India

### Phase 3 (6 months)

- E-commerce integration
- Video recipes
- Community forum

### Phase 4 (12 months)

- ML-based price prediction
- Multilingual support
- International expansion

## Maintenance & Support

**Code Updates:**

- Monthly dependency updates
- Quarterly feature releases
- Hotfixes for critical bugs (24-hour SLA)

**Data Management:**

- Daily Firebase backups
- Price updates (auto-sync or manual)
- Recipe additions (community or admin)

**User Support:**

- Email support
- In-app feedback mechanism
- Community forum

---

**Design Document Version**: 1.0  
**Last Updated**: May 11, 2026  
**Architecture Status**: ✅ Production-Ready
