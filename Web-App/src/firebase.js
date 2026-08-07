import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
import { getFirestore } from "firebase/firestore";
import { getStorage } from "firebase/storage";

const firebaseConfig = {
  apiKey: "AIzaSyDvHYGo-vIErFocBVvUbayJnwrLVog-FDQ",
  authDomain: "agrointel-15ccf.firebaseapp.com",
  projectId: "agrointel-15ccf",
  storageBucket: "agrointel-15ccf.firebasestorage.app",
  messagingSenderId: "1056454344176",
  appId: "1:1056454344176:web:c40489c8a92f019adcb135",
  measurementId: "G-YWL8DJ6H77"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);

// Initialize Firebase Authentication and get a reference to the service
export const auth = getAuth(app);

// Initialize Cloud Firestore and get a reference to the service
export const db = getFirestore(app);

// Initialize Cloud Storage and get a reference to the service
export const storage = getStorage(app);

export default app;
