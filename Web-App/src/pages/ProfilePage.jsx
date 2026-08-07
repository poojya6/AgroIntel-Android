import { useEffect, useState } from "react";
import { auth, db } from "../firebase";
import { doc, getDoc, setDoc } from "firebase/firestore";

function ProfilePage({ onBack, onProfileSave }) {
  const [profile, setProfile] = useState({
    fullName: "",
    phone: "",
    email: "",
    farmName: "",
    location: "",
    state: "",
    district: "",
    land: "",
    activeCrops: "0",
    crops: [
      { name: "Wheat", acres: "4" },
      { name: "Cotton", acres: "3" },
      { name: "Rice", acres: "3" },
    ],
  });
  const [saved, setSaved] = useState(false);

  useEffect(() => {
    const loadProfile = async () => {
      const user = auth.currentUser;
      if (!user) return;

      try {
        const userSnap = await getDoc(doc(db, "users", user.uid));
        const farmSnap = await getDoc(doc(db, "farms", user.uid));

        const updatedProfile = {
          fullName: "",
          phone: "",
          email: "",
          farmName: "",
          location: "",
          state: "",
          district: "",
          land: "",
          activeCrops: "0",
          crops: [
            { name: "Wheat", acres: "4" },
            { name: "Cotton", acres: "3" },
            { name: "Rice", acres: "3" },
          ],
        };

        if (userSnap.exists()) {
          const userData = userSnap.data();
          updatedProfile.fullName = userData.fullName || "";
          updatedProfile.phone = userData.phone || "";
          updatedProfile.email = userData.email || "";
        }

        if (farmSnap.exists()) {
          const farmData = farmSnap.data();
          updatedProfile.farmName = farmData.farmName || "";
          updatedProfile.land = farmData.land ? String(farmData.land) : "";
          updatedProfile.state = farmData.state || "";
          updatedProfile.district = farmData.district || "";
          updatedProfile.location = [farmData.village, farmData.district, farmData.state]
            .filter(Boolean)
            .join(", ");
          updatedProfile.crops = farmData.crops || [
            { name: "Wheat", acres: farmData.land ? String(Math.max(Number(farmData.land) - 6, 4)) : "4" },
            { name: "Cotton", acres: "3" },
            { name: "Rice", acres: "3" },
          ];
          updatedProfile.activeCrops = farmData.activeCrops
            ? String(farmData.activeCrops)
            : String(updatedProfile.crops.length);
        }

        setProfile(updatedProfile);
      } catch (err) {
        console.log(err);
      }
    };

    loadProfile();
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setProfile((prev) => ({ ...prev, [name]: value }));
    setSaved(false);
  };

  const handleCropChange = (index, field, value) => {
    setProfile((prev) => {
      const crops = prev.crops.map((crop, idx) =>
        idx === index ? { ...crop, [field]: value } : crop
      );
      return { ...prev, crops, activeCrops: String(crops.length) };
    });
    setSaved(false);
  };

  const addCrop = () => {
    setProfile((prev) => {
      const crops = [...prev.crops, { name: "New Crop", acres: "0" }];
      return { ...prev, crops, activeCrops: String(crops.length) };
    });
    setSaved(false);
  };

  const removeCrop = (index) => {
    setProfile((prev) => {
      const crops = prev.crops.filter((_, idx) => idx !== index);
      return { ...prev, crops, activeCrops: String(crops.length) };
    });
    setSaved(false);
  };

  const saveProfileLocally = (profileData) => {
    const [village = "", district = "", state = ""] = profileData.location
      .split(",")
      .map((part) => part.trim());

    const savedProfile = {
      farmName: profileData.farmName,
      land: profileData.land,
      state: profileData.state || state,
      district: profileData.district || district,
      village,
      crops: profileData.crops,
      activeCrops: profileData.activeCrops,
      location: profileData.location,
    };

    localStorage.setItem("farmProfile", JSON.stringify(savedProfile));
    if (onProfileSave) {
      onProfileSave(savedProfile);
    }
  };

  const handleSave = async () => {
    const user = auth.currentUser;
    if (!user) return;

    saveProfileLocally(profile);
    setSaved(true);

    try {
      await setDoc(doc(db, "users", user.uid), {
        fullName: profile.fullName,
        phone: profile.phone,
        email: profile.email,
      }, { merge: true });

      await setDoc(doc(db, "farms", user.uid), {
        farmName: profile.farmName,
        land: profile.land ? Number(profile.land) : 0,
        village: profile.location,
        state: profile.state,
        district: profile.district,
        activeCrops: profile.activeCrops ? Number(profile.activeCrops) : 0,
        crops: profile.crops,
      }, { merge: true });

      alert("Profile Updated Successfully!");
    } catch (err) {
      console.log(err);
      alert("Profile saved locally; Firestore sync failed.");
    }
  };

  useEffect(() => {
    localStorage.setItem(
      "farmProfile",
      JSON.stringify({
        farmName: profile.farmName,
        land: profile.land,
        state: profile.state,
        district: profile.district,
        location: profile.location,
        crops: profile.crops,
        activeCrops: profile.activeCrops,
      })
    );
  }, [profile]);

  return (
    <div className="profile-page-view">
      <button className="back-button" type="button" onClick={onBack}>
        ← Back
      </button>

      <div className="profile-header green">
        <div className="profile-avatar">
          {profile.fullName?.split(" ").map((p) => p[0]).join("") || "RK"}
        </div>
        <h1>{profile.fullName || "Ramesh Kumar"}</h1>
        <p>Farmer</p>
      </div>

      <div className="profile-card">
        <h2>Personal Information</h2>
        <label>Full Name</label>
        <input name="fullName" value={profile.fullName} onChange={handleChange} />
        <label>Phone Number</label>
        <input name="phone" value={profile.phone} onChange={handleChange} />
        <label>Email Address</label>
        <input name="email" value={profile.email} onChange={handleChange} />
        <label>Member Since</label>
        <input value="January 2026" disabled />
      </div>

      <div className="profile-card">
        <h2>Farm Details</h2>
        <label>Farm Name</label>
        <input name="farmName" value={profile.farmName} onChange={handleChange} />
        <label>Total Land (Acres)</label>
        <input name="land" value={profile.land} onChange={handleChange} />
        <label>State</label>
        <select name="state" value={profile.state} onChange={handleChange}>
          <option value="">Select State</option>
          <option value="Andhra Pradesh">Andhra Pradesh</option>
          <option value="Telangana">Telangana</option>
          <option value="Tamil Nadu">Tamil Nadu</option>
          <option value="Karnataka">Karnataka</option>
          <option value="Punjab">Punjab</option>
          <option value="Haryana">Haryana</option>
        </select>
        <label>District</label>
        <select name="district" value={profile.district} onChange={handleChange}>
          <option value="">Select District</option>
          {profile.state === "Andhra Pradesh" && (
            <>
              <option value="Tirupati">Tirupati</option>
              <option value="Anantapur">Anantapur</option>
              <option value="Vijayawada">Vijayawada</option>
              <option value="Guntur">Guntur</option>
              <option value="Visakhapatnam">Visakhapatnam</option>
            </>
          )}
          {profile.state === "Telangana" && (
            <>
              <option value="Hyderabad">Hyderabad</option>
              <option value="Warangal">Warangal</option>
              <option value="Karimnagar">Karimnagar</option>
              <option value="Nizamabad">Nizamabad</option>
            </>
          )}
          {profile.state === "Tamil Nadu" && (
            <>
              <option value="Coimbatore">Coimbatore</option>
              <option value="Thanjavur">Thanjavur</option>
              <option value="Madurai">Madurai</option>
              <option value="Erode">Erode</option>
            </>
          )}
          {profile.state === "Karnataka" && (
            <>
              <option value="Mysuru">Mysuru</option>
              <option value="Belagavi">Belagavi</option>
              <option value="Dharwad">Dharwad</option>
              <option value="Shivamogga">Shivamogga</option>
            </>
          )}
          {profile.state === "Punjab" && (
            <>
              <option value="Ludhiana">Ludhiana</option>
              <option value="Amritsar">Amritsar</option>
              <option value="Patiala">Patiala</option>
              <option value="Jalandhar">Jalandhar</option>
            </>
          )}
          {profile.state === "Haryana" && (
            <>
              <option value="Karnal">Karnal</option>
              <option value="Hisar">Hisar</option>
              <option value="Gurugram">Gurugram</option>
              <option value="Rohtak">Rohtak</option>
            </>
          )}
        </select>
        <label>Location</label>
        <input name="location" value={profile.location} onChange={handleChange} />
      </div>

      <button className="save-button" type="button" onClick={handleSave}>
        Save Profile
      </button>
      {saved && <p className="save-message">Profile updated successfully.</p>}
    </div>
  );
}

export default ProfilePage;
