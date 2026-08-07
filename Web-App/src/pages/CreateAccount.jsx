import { useEffect, useState } from "react";
import { createUserWithEmailAndPassword, updateProfile } from "firebase/auth";
import { auth, db } from "../firebase";
import { doc, setDoc } from "firebase/firestore";

function CreateAccount({ onNext, onSignIn }) {
  const [form, setForm] = useState({
    fullName: "",
    phone: "",
    email: "",
    password: "",
    acceptedTerms: false,
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  useEffect(() => {
    const saved = localStorage.getItem("accountInfo");
    if (saved) {
      setForm((prev) => ({ ...prev, ...JSON.parse(saved) }));
    }
  }, []);

  const handleChange = (e) => {
    const { name, type, value, checked } = e.target;
    setForm((prev) => ({
      ...prev,
      [name]: type === "checkbox" ? checked : value,
    }));
    setError("");
  };

  const handleSubmit = async () => {
    if (!form.fullName || !form.phone || !form.email || !form.password) {
      setError("Please fill in all fields.");
      return;
    }
    if (!form.acceptedTerms) {
      setError("Please agree to the Terms of Service and Privacy Policy.");
      return;
    }

    setLoading(true);
    try {
      // Create user with Firebase Authentication
      const userCredential = await createUserWithEmailAndPassword(
        auth,
        form.email,
        form.password
      );

      // Update user profile with full name
      await updateProfile(userCredential.user, {
        displayName: form.fullName,
      });

      // Save user data to Firestore
      await setDoc(doc(db, "users", userCredential.user.uid), {
        fullName: form.fullName,
        phone: form.phone,
        email: form.email,
        createdAt: new Date(),
      });

      // Reset any previous account data so a new account starts with zeroed dashboard values
      localStorage.removeItem("farmProfile");
      localStorage.removeItem("accountInfo");
      localStorage.setItem("accountInfo", JSON.stringify({
        fullName: form.fullName,
        phone: form.phone,
        email: form.email,
      }));

      setLoading(false);
      onNext();
    } catch (err) {
      setError(err.message || "Failed to create account");
      setLoading(false);
    }
  };

  return (
    <div className="create-page">
      <h1>Create Account</h1>

      <p className="join-text">Join AgroIntel today</p>

      {error && <div style={{ color: "red", marginBottom: "10px", fontSize: "14px" }}>{error}</div>}

      <label>Full Name</label>
      <input
        name="fullName"
        value={form.fullName}
        onChange={handleChange}
        placeholder="Ramesh Kumar"
        disabled={loading}
      />

      <label>Phone Number</label>
      <input
        name="phone"
        value={form.phone}
        onChange={handleChange}
        placeholder="+91 98765 43210"
        disabled={loading}
      />

      <label>Email Address</label>
      <input
        name="email"
        value={form.email}
        onChange={handleChange}
        placeholder="farmer@example.com"
        disabled={loading}
      />

      <label>Password</label>
      <input
        type="password"
        name="password"
        value={form.password}
        onChange={handleChange}
        placeholder="Create a strong password"
        disabled={loading}
      />

      <div className="terms">
        <input
          type="checkbox"
          name="acceptedTerms"
          checked={form.acceptedTerms}
          onChange={handleChange}
          disabled={loading}
        />
        <span>I agree to the Terms of Service and Privacy Policy</span>
      </div>

      <button onClick={handleSubmit} disabled={loading}>
        {loading ? "Creating Account..." : "Create Account"}
      </button>

      <p className="signin-text">
        Already have an account? <button className="link-button" type="button" onClick={onSignIn} disabled={loading}>Sign In</button>
      </p>
    </div>
  );
}

export default CreateAccount;
