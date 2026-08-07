import { db, auth } from "./firebase";
import {
  collection,
  doc,
  getDoc,
  setDoc,
  getDocs,
  query,
  where,
  updateDoc,
  addDoc,
} from "firebase/firestore";

// User Services
export const saveUserProfile = async (userId, profileData) => {
  try {
    await setDoc(doc(db, "users", userId), {
      ...profileData,
      updatedAt: new Date(),
    }, { merge: true });
    return { success: true };
  } catch (error) {
    console.error("Error saving user profile:", error);
    return { success: false, error: error.message };
  }
};

export const getUserProfile = async (userId) => {
  try {
    const docSnap = await getDoc(doc(db, "users", userId));
    if (docSnap.exists()) {
      return { success: true, data: docSnap.data() };
    }
    return { success: false, error: "User profile not found" };
  } catch (error) {
    console.error("Error fetching user profile:", error);
    return { success: false, error: error.message };
  }
};

// Farm Services
export const saveFarmProfile = async (userId, farmData) => {
  try {
    await setDoc(doc(db, "farms", userId), {
      ...farmData,
      userId,
      updatedAt: new Date(),
    }, { merge: true });
    return { success: true };
  } catch (error) {
    console.error("Error saving farm profile:", error);
    return { success: false, error: error.message };
  }
};

export const getFarmProfile = async (userId) => {
  try {
    const docSnap = await getDoc(doc(db, "farms", userId));
    if (docSnap.exists()) {
      return { success: true, data: docSnap.data() };
    }
    return { success: false, error: "Farm profile not found" };
  } catch (error) {
    console.error("Error fetching farm profile:", error);
    return { success: false, error: error.message };
  }
};

// Crop Recommendations
export const getCropRecommendations = async (userId) => {
  try {
    const q = query(
      collection(db, "recommendations"),
      where("userId", "==", userId)
    );
    const querySnapshot = await getDocs(q);
    const recommendations = [];
    querySnapshot.forEach((doc) => {
      recommendations.push(doc.data());
    });
    return { success: true, data: recommendations };
  } catch (error) {
    console.error("Error fetching recommendations:", error);
    return { success: false, error: error.message };
  }
};

export const saveCropRecommendations = async (userId, recommendations) => {
  try {
    await setDoc(doc(db, "recommendations", userId), {
      userId,
      crops: recommendations,
      createdAt: new Date(),
      updatedAt: new Date(),
    }, { merge: true });
    return { success: true };
  } catch (error) {
    console.error("Error saving recommendations:", error);
    return { success: false, error: error.message };
  }
};

// Profit Calculations
export const saveProfitCalculation = async (userId, calculationData) => {
  try {
    const docRef = await addDoc(collection(db, "profitCalculations"), {
      userId,
      ...calculationData,
      createdAt: new Date(),
    });
    return { success: true, id: docRef.id };
  } catch (error) {
    console.error("Error saving profit calculation:", error);
    return { success: false, error: error.message };
  }
};

export const getProfitCalculations = async (userId) => {
  try {
    const q = query(
      collection(db, "profitCalculations"),
      where("userId", "==", userId)
    );
    const querySnapshot = await getDocs(q);
    const calculations = [];
    querySnapshot.forEach((doc) => {
      calculations.push({ id: doc.id, ...doc.data() });
    });
    return { success: true, data: calculations };
  } catch (error) {
    console.error("Error fetching profit calculations:", error);
    return { success: false, error: error.message };
  }
};

// Market Prices
export const getMarketPrices = async () => {
  try {
    const docSnap = await getDoc(doc(db, "market", "prices"));
    if (docSnap.exists()) {
      return { success: true, data: docSnap.data() };
    }
    return { success: false, error: "Market data not found" };
  } catch (error) {
    console.error("Error fetching market prices:", error);
    return { success: false, error: error.message };
  }
};

// Weather Data
export const getWeatherData = async (location) => {
  try {
    const q = query(
      collection(db, "weather"),
      where("location", "==", location)
    );
    const querySnapshot = await getDocs(q);
    if (!querySnapshot.empty) {
      return { success: true, data: querySnapshot.docs[0].data() };
    }
    return { success: false, error: "Weather data not found" };
  } catch (error) {
    console.error("Error fetching weather data:", error);
    return { success: false, error: error.message };
  }
};

// Government Schemes
export const getGovernmentSchemes = async (state) => {
  try {
    const q = query(
      collection(db, "schemes"),
      where("state", "==", state)
    );
    const querySnapshot = await getDocs(q);
    const schemes = [];
    querySnapshot.forEach((doc) => {
      schemes.push(doc.data());
    });
    return { success: true, data: schemes };
  } catch (error) {
    console.error("Error fetching schemes:", error);
    return { success: false, error: error.message };
  }
};

// Notifications
export const saveNotification = async (userId, notification) => {
  try {
    const docRef = await addDoc(collection(db, "notifications"), {
      userId,
      ...notification,
      read: false,
      createdAt: new Date(),
    });
    return { success: true, id: docRef.id };
  } catch (error) {
    console.error("Error saving notification:", error);
    return { success: false, error: error.message };
  }
};

export const getNotifications = async (userId) => {
  try {
    const q = query(
      collection(db, "notifications"),
      where("userId", "==", userId)
    );
    const querySnapshot = await getDocs(q);
    const notifications = [];
    querySnapshot.forEach((doc) => {
      notifications.push({ id: doc.id, ...doc.data() });
    });
    return { success: true, data: notifications };
  } catch (error) {
    console.error("Error fetching notifications:", error);
    return { success: false, error: error.message };
  }
};

export const markNotificationRead = async (notificationId) => {
  try {
    await updateDoc(doc(db, "notifications", notificationId), {
      read: true,
    });
    return { success: true };
  } catch (error) {
    console.error("Error marking notification read:", error);
    return { success: false, error: error.message };
  }
};
