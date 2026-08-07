# Firebase Backend Integration Guide

## ✅ Completed Setup

Your AgroIntel app is now integrated with Firebase:

### 1. **Authentication**
- ✅ User registration with email/password
- ✅ Firebase Auth state management
- ✅ Auto-logout on browser refresh
- ✅ User profile stored in Firestore

### 2. **Data Collections Created**
The app uses these Firestore collections:
- `users/` - User profiles and account info
- `farms/` - Farm profile data
- `recommendations/` - Crop recommendations
- `profitCalculations/` - Profit calculation history
- `market/` - Market prices data
- `weather/` - Weather data by location
- `schemes/` - Government schemes
- `notifications/` - User notifications

---

## 🔒 Firebase Security Rules Setup

1. Go to: **Firebase Console** → **Firestore Database** → **Rules**
2. Replace all rules with:

```
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    // Allow users to read/write their own data
    match /users/{userId} {
      allow read, write: if request.auth.uid == userId;
    }
    
    match /farms/{userId} {
      allow read, write: if request.auth.uid == userId;
    }
    
    match /recommendations/{userId} {
      allow read, write: if request.auth.uid == userId;
    }
    
    match /profitCalculations/{document=**} {
      allow read, write: if request.auth.uid == resource.data.userId;
      allow create: if request.auth.uid == request.resource.data.userId;
    }
    
    match /notifications/{document=**} {
      allow read, write: if request.auth.uid == resource.data.userId;
      allow create: if request.auth.uid == request.resource.data.userId;
    }
    
    // Public read access for market and weather data
    match /market/{document=**} {
      allow read: if request.auth != null;
    }
    
    match /weather/{document=**} {
      allow read: if request.auth != null;
    }
    
    match /schemes/{document=**} {
      allow read: if request.auth != null;
    }
  }
}
```

3. Click **Publish**

---

## 📊 Sample Data Setup

### Add Market Prices (for MarketTrends page):

1. Go to **Firestore** → **Create collection** → `market`
2. Add document with ID: `prices`
3. Add this data:

```json
{
  "lastUpdated": "2024-07-30",
  "crops": [
    {
      "name": "Wheat",
      "currentPrice": 2650,
      "previousPrice": 2600,
      "unit": "Q",
      "timestamp": "2 min ago",
      "volume": "High"
    },
    {
      "name": "Rice",
      "currentPrice": 5200,
      "previousPrice": 5150,
      "unit": "Q",
      "timestamp": "5 min ago",
      "volume": "High"
    },
    {
      "name": "Cotton",
      "currentPrice": 6500,
      "previousPrice": 6400,
      "unit": "Q",
      "timestamp": "10 min ago",
      "volume": "Medium"
    }
  ]
}
```

---

## 🔌 API Methods Available

All API methods are in `src/api.js`. Use them like:

```javascript
import { getCropRecommendations, saveProfitCalculation } from "./api";

// Get recommendations
const result = await getCropRecommendations(userId);
if (result.success) {
  console.log(result.data);
}

// Save calculation
await saveProfitCalculation(userId, {
  crop: "Wheat",
  land: 10,
  expectedProfit: 69000
});
```

---

## 📝 API Reference

### User Functions
- `getUserProfile(userId)` - Fetch user info
- `saveUserProfile(userId, profileData)` - Update user profile

### Farm Functions
- `getFarmProfile(userId)` - Fetch farm details
- `saveFarmProfile(userId, farmData)` - Update farm details

### Crop Functions
- `getCropRecommendations(userId)` - Fetch recommendations
- `saveCropRecommendations(userId, recommendations)` - Save recommendations

### Profit Functions
- `getProfitCalculations(userId)` - Fetch calculation history
- `saveProfitCalculation(userId, calculationData)` - Save new calculation

### Market Functions
- `getMarketPrices()` - Fetch current market prices

### Weather Functions
- `getWeatherData(location)` - Fetch weather by location

### Schemes Functions
- `getGovernmentSchemes(state)` - Fetch schemes for state

### Notification Functions
- `getNotifications(userId)` - Fetch user notifications
- `saveNotification(userId, notification)` - Create notification
- `markNotificationRead(notificationId)` - Mark as read

---

## 🧪 Test the Integration

1. Start the app: `npm run dev`
2. Create a new account (uses Firebase Auth)
3. Fill farm profile (saves to Firestore)
4. Check **Firebase Console** → **Firestore** → **Collections** to see saved data

---

## ⚡ Next Steps

- Connect MarketTrends to fetch live data from Firestore
- Connect CropRecommendations to fetch from backend
- Add weather API integration
- Create admin dashboard to manage market data

Need help? Check the API methods in `src/api.js`
