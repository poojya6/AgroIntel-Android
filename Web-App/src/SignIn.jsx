import { useState } from "react";
import { signInWithEmailAndPassword } from "firebase/auth";
import { auth } from "./firebase";

function SignIn({ onSuccess, onBack }) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleLogin = async () => {
    if (!email || !password) {
      setError("Please enter email and password");
      return;
    }

    setLoading(true);

    try {
      await signInWithEmailAndPassword(auth, email, password);
      localStorage.removeItem("farmProfile");
      localStorage.removeItem("accountInfo");
      onSuccess();
    } catch (err) {
      setError(err.message);
    }

    setLoading(false);
  };

  return (
    <div className="create-page">
      <h1>Sign In</h1>

      <p className="join-text">
        Welcome back to AgroIntel
      </p>

      {error && (
        <p style={{ color: "red" }}>
          {error}
        </p>
      )}

      <label>Email Address</label>

      <input
        type="email"
        placeholder="farmer@example.com"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />

      <label>Password</label>

      <input
        type="password"
        placeholder="Enter your password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />

      <button onClick={handleLogin}>
        {loading ? "Signing In..." : "Sign In"}
      </button>

      <p className="signin-text">
        Don't have an account?
        <button
          className="link-button"
          type="button"
          onClick={onBack}
        >
          Create Account
        </button>
      </p>
    </div>
  );
}

export default SignIn;