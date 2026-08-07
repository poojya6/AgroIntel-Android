import { useEffect, useState } from "react";
import { auth, db } from "../firebase";
import { doc, setDoc } from "firebase/firestore";

function ProfileSetup({ onComplete }) {
  const [form, setForm] = useState({
    farmName: "",
    land: "",
    state: "",
    district: "",
    village: "",
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const districts = {
    AndhraPradesh: ["Tirupati", "Chittoor", "Nellore", "Kadapa"],
    Telangana: ["Hyderabad", "Warangal", "Nizamabad"],
    TamilNadu: ["Chennai", "Coimbatore", "Madurai"],
  };

  useEffect(() => {
    const saved = localStorage.getItem("farmProfile");
    if (saved) {
      setForm(JSON.parse(saved));
    }
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;

    if (name === "state") {
      setForm((prev) => ({ ...prev, state: value, district: "" }));
      return;
    }

    setForm((prev) => ({ ...prev, [name]: value }));
    setError("");
  };

  const saveProfile = async () => {
    if (!form.farmName || !form.land || !form.state || !form.district || !form.village) {
      setError("Please complete all profile fields.");
      return;
    }

    setLoading(true);
    try {
      const user = auth.currentUser;
      if (user) {
        // Save to Firestore
        await setDoc(doc(db, "farms", user.uid), {
          farmName: form.farmName,
          land: form.land,
          state: form.state,
          district: form.district,
          village: form.village,
          userId: user.uid,
          updatedAt: new Date(),
        });
      }

      // Also save to localStorage for backward compatibility
      localStorage.setItem("farmProfile", JSON.stringify(form));
      setLoading(false);
      alert("Profile details saved successfully!");
      if (onComplete) {
        onComplete();
      }
    } catch (err) {
      setError(err.message || "Failed to save profile");
      setLoading(false);
    }
  };

  return (
    <div className="profile-page">
      <h1>Complete Your Profile</h1>
      <p>Tell us about your farm</p>

      {error && <div style={{ color: "red", marginBottom: "10px", fontSize: "14px" }}>{error}</div>}

      <div className="camera-circle">📷</div>

      <label>Farm Name</label>
      <input
        name="farmName"
        value={form.farmName}
        placeholder="Kumar Agriculture Farm"
        onChange={handleChange}
        disabled={loading}
      />

      <label>Total Land (Acres)</label>
      <input
        name="land"
        value={form.land}
        placeholder="10"
        onChange={handleChange}
        disabled={loading}
      />

      <label>State</label>
      <select name="state" value={form.state} onChange={handleChange} disabled={loading}>
        <option value="">Select State</option>
        <option value="AndhraPradesh">Andhra Pradesh</option>
        <option value="Telangana">Telangana</option>
        <option value="TamilNadu">Tamil Nadu</option>
      </select>

      <label>District</label>
      <select
        name="district"
        value={form.district}
        disabled={!form.state || loading}
        onChange={handleChange}
      >
        <option value="">Select District</option>
        {form.state && districts[form.state].map((district) => (
          <option key={district} value={district}>
            {district}
          </option>
        ))}
      </select>

      <label>Village/Location</label>
      <input
        name="village"
        value={form.village}
        placeholder="📍  Enter village name"
        onChange={handleChange}
        disabled={loading}
      />

      <button onClick={saveProfile} disabled={loading}>
        {loading ? "Saving Profile..." : "Complete Setup"}
      </button>
    </div>
  );
}

export default ProfileSetup;
