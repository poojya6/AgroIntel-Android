# Firebase Setup Guide

## Step 1: Get Your Firebase Web Config
1. Go to: https://console.firebase.google.com/u/0/project/agrointel-15ccf/overview
2. Click **Settings** (gear icon) → **Project Settings**
3. Scroll down to **"Your apps"** section
4. If you don't see a web app, click **"Add app"** and select **"Web"**
5. You'll see a code block that looks like this:

```javascript
const firebaseConfig = {
  apiKey: "AIz...",
  authDomain: "agrointel-15ccf.firebaseapp.com",
  projectId: "agrointel-15ccf",
  storageBucket: "agrointel-15ccf.appspot.com",
  messagingSenderId: "123...",
  appId: "1:123..."
};
```

**Copy the entire object and paste it when asked**

## Step 2: Required Firebase Services
Enable these in Firebase Console:
- ✅ Authentication (Email/Password)
- ✅ Firestore Database
- ✅ Cloud Storage (for farm data)

## Step 3: Database Rules
In Firebase Console → Firestore → Rules, set:
```
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /{document=**} {
      allow read, write: if request.auth != null;
    }
  }
}
```

This allows authenticated users to read/write.
