import { useEffect, useState } from "react";
import "./App.css";
import { auth } from "./firebase";
import { signOut, onAuthStateChanged } from "firebase/auth";
import CreateAccount from "./pages/CreateAccount";
import ProfileSetup from "./pages/ProfileSetup";
import PredictProfit from "./pages/PredictProfit";
import CropRecommendations from "./pages/CropRecommendations";
import MarketTrends from "./pages/MarketTrends";
import WeatherUpdates from "./pages/WeatherUpdates";
import GovernmentSchemes from "./pages/GovernmentSchemes";
import Notifications from "./pages/Notifications";
import Settings from "./pages/Settings";
import AIInsights from "./pages/AIInsights";
import ProfilePage from "./pages/ProfilePage";
import DailyFarmingTips from "./pages/DailyFarmingTips";
import GovernmentSchemeDetails from "./pages/GovernmentSchemeDetails";
import SavedSchemes from "./pages/SavedSchemes";
import SignIn from "./SignIn";

function SplashScreen() {
  return (
    <div className="splash-screen">
      <div className="splash-card">
        <div className="logo-circle">🍃</div>
        <div className="splash-copy">
          <p className="app-title">AgroIntel</p>
          <p className="tagline">Forecast System</p>
        </div>
        <div className="splash-loader" aria-label="Loading">
          <span />
          <span />
          <span />
        </div>
      </div>
    </div>
  );
}

function WelcomeScreen({ onCreate, onSignIn }) {
  return (
    <div className="welcome-screen">
      <div>
        <div className="logo-circle">🍃</div>
        <h1>AgroIntel Forecast</h1>
        <p className="tagline">Your Smart Farming Companion</p>

        <button className="create-btn" onClick={onCreate}>
          Create New Account
        </button>
        <button className="signin-btn" onClick={onSignIn}>
          Sign In
        </button>

        <p className="footer-text">
          By continuing, you agree to our Terms & Privacy Policy
        </p>
      </div>
    </div>
  );
}




const clearUserDashboardState = () => {
  localStorage.removeItem("farmProfile");
  localStorage.removeItem("accountInfo");
  localStorage.removeItem("lastPrediction");
};

function Dashboard({ onNavigate, savedProfile, user }) {
  const [profile, setProfile] = useState({});
  const [account, setAccount] = useState({});
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  const loadProfile = () => {
    const savedProfile = localStorage.getItem("farmProfile");
    const savedAccount = localStorage.getItem("accountInfo");

    if (savedProfile) {
      setProfile(JSON.parse(savedProfile));
    } else {
      setProfile({});
    }

    if (savedAccount) {
      setAccount(JSON.parse(savedAccount));
    }
  };

  useEffect(() => {
    if (savedProfile) {
      setProfile(savedProfile);
    } else {
      loadProfile();
    }

    const savedAccount = localStorage.getItem("accountInfo");
    if (savedAccount) {
      setAccount(JSON.parse(savedAccount));
    } else if (user) {
      setAccount({
        fullName: user.displayName || "",
        email: user.email || "",
      });
    }
  }, [savedProfile, user]);

  useEffect(() => {
    const handleStorageChange = (event) => {
      if (event.key === "farmProfile" || event.key === "accountInfo") {
        loadProfile();
      }
    };

    window.addEventListener("storage", handleStorageChange);
    return () => window.removeEventListener("storage", handleStorageChange);
  }, []);

  const hasProfileData = ["farmName", "land", "state", "district", "village", "location"].some(
    (key) => profile[key] !== undefined && profile[key] !== null && profile[key] !== ""
  );
  const savedPrediction = localStorage.getItem("lastPrediction");
  const parsedPrediction = savedPrediction ? JSON.parse(savedPrediction) : null;
  const accountName =
    (account.fullName && account.fullName.trim()) ||
    (user?.displayName && user.displayName.trim()) ||
    (user?.email ? user.email.split("@")[0] : "");
  const accountEmail = account.email || user?.email || "farmer@example.com";
  const fullName = accountName || "Farmer";
  const location = hasProfileData
    ? profile.location || (profile.district ? `${profile.district}, ${profile.state}` : profile.state || "")
    : "Add your farm details";
  const acres = profile.land !== undefined && profile.land !== null && profile.land !== ""
    ? `${profile.land} Acres`
    : "0 Acres";
  const predictedProfit = parsedPrediction?.totalProfit || "₹0";
  const activeCrops = profile.activeCrops !== undefined && profile.activeCrops !== null && profile.activeCrops !== ""
    ? `${profile.activeCrops} Crops`
    : Array.isArray(profile.crops)
    ? `${profile.crops.length} Crops`
    : hasProfileData
    ? "3 Crops"
    : "0 Crops";
  const weather = hasProfileData ? "28°C" : "0°C";
  const marketStatus = hasProfileData ? "Good" : "No Data";
  const welcomeLabel = hasProfileData ? "Welcome back," : "Welcome,";

  const handleLogout = () => {
    signOut(auth).then(() => {
      clearUserDashboardState();
      setIsMenuOpen(false);
      onNavigate("welcome");
    }).catch((error) => {
      console.error("Logout error:", error);
    });
  };

  const handleNavigate = (target) => {
    setIsMenuOpen(false);
    onNavigate(target);
  };

  return (
    <div className="dashboard">
      {isMenuOpen && (
        <>
          <div className="sidebar-backdrop" onClick={() => setIsMenuOpen(false)} />
          <aside className="sidebar-menu">
            <div className="sidebar-header">
              <div className="sidebar-avatar">
                {account.fullName
                  ? account.fullName
                      .split(" ")
                      .map((part) => part[0])
                      .join("")
                  : "RK"}
              </div>
              <div className="sidebar-user">
                <strong>{fullName || "Ramesh Kumar"}</strong>
                <p>{accountEmail || "farmer@example.com"}</p>
              </div>
            </div>
            <nav className="sidebar-nav">
              <button type="button" onClick={() => handleNavigate("dashboard")}>Home Dashboard</button>
              <button type="button" onClick={() => handleNavigate("predict")}>Profit Prediction</button>
              <button type="button" onClick={() => handleNavigate("recommendations")}>Crop Recommendations</button>
              <button type="button" onClick={() => handleNavigate("market")}>Market Trends</button>
              <button type="button" onClick={() => handleNavigate("schemes")}>Government Schemes</button>
              <button type="button" onClick={() => handleNavigate("profile")}>My Profile</button>
              <button type="button" className="sidebar-logout" onClick={handleLogout}>Logout</button>
            </nav>
          </aside>
        </>
      )}
      <div className="dashboard-header">
        <div className="dashboard-top-row">
          <button className="menu-icon" type="button" onClick={() => setIsMenuOpen(true)}>
            ☰
          </button>
        </div>
        <div className="dashboard-welcome">
          <span>{welcomeLabel}</span>
          <div className="dashboard-title-row">
            <h1>{fullName}</h1>
            <span className="acres-badge">{acres}</span>
          </div>
          {location && <p>{location}</p>}
        </div>
      </div>

      <div className="dashboard-grid">
        <div className="stat-card green">
          <div className="stat-icon">₹</div>
          <div>
            <p>Predicted Profit</p>
            <strong>{predictedProfit}</strong>
          </div>
        </div>
        <div className="stat-card purple">
          <div className="stat-icon">📈</div>
          <div>
            <p>Market Status</p>
            <strong>{marketStatus}</strong>
          </div>
        </div>
      </div>

      <h2 className="dashboard-section-title">Quick Actions</h2>
      <div className="dashboard-actions">
        <button className="action-card" type="button" onClick={() => onNavigate("predict")}> 
          <div className="action-icon">📊</div>
          <div>
            <strong>Calculate Profit</strong>
            <p>Estimate crop profitability</p>
          </div>
        </button>
        <button className="action-card" type="button" onClick={() => onNavigate("recommendations")}>
          <div className="action-icon">🌱</div>
          <div>
            <strong>Crop Recommendations</strong>
            <p>Get AI-powered suggestions</p>
          </div>
        </button>
        <button className="action-card" type="button" onClick={() => onNavigate("market")}>
          <div className="action-icon">📈</div>
          <div>
            <strong>Market Trends</strong>
            <p>Live price updates</p>
          </div>
        </button>
        <button className="action-card" type="button" onClick={() => onNavigate("tips")}>
          <div className="action-icon">🌱</div>
          <div>
            <strong>Daily Farming Tips</strong>
            <p>Fresh advice every visit</p>
          </div>
        </button>
      </div>

    </div>
  );
}

function App() {
  const [view, setView] = useState("splash");
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [savedProfile, setSavedProfile] = useState(null);

  useEffect(() => {
    const saved = localStorage.getItem("farmProfile");
    if (saved) {
      setSavedProfile(JSON.parse(saved));
    }
  }, []);

  useEffect(() => {
    const unsubscribe = onAuthStateChanged(auth, (currentUser) => {
      setUser(currentUser);
      setLoading(false);

      setView((currentView) => {
        if (currentUser) {
          return currentView === "splash" || currentView === "welcome" || currentView === "create" || currentView === "signin"
            ? "dashboard"
            : currentView;
        }

        if (currentView === "dashboard" || currentView === "predict" || currentView === "recommendations" || currentView === "market" || currentView === "weather" || currentView === "schemes" || currentView === "notifications" || currentView === "settings" || currentView === "insights" || currentView === "profile") {
          return "welcome";
        }

        return currentView;
      });
    });

    return () => unsubscribe();
  }, []);

  useEffect(() => {
    const timer = setTimeout(() => {
      setView((currentView) => {
        if (currentView === "splash") {
          return user ? "dashboard" : "welcome";
        }
        return currentView;
      });
    }, 2200);

    return () => clearTimeout(timer);
  }, [user]);

  
if (view === "splash") {
  return <SplashScreen />;
}
 

 if (loading) {
  return <div>Loading...</div>;
}

  if (view === "create") {
    return <CreateAccount onNext={() => setView("profileSetup")} onSignIn={() => setView("signin")} />;
  }

  if (view === "signin") {
  return (
    <SignIn
      onSuccess={() => setView("dashboard")}
      onBack={() => setView("create")}
    />
  );
}

  if (view === "profileSetup") {
    return <ProfileSetup onComplete={() => setView("dashboard")} />;
  }

  if (view === "dashboard") {
    return <Dashboard onNavigate={setView} savedProfile={savedProfile} user={user} />;
  }

  if (view === "predict") {
    return <PredictProfit onBack={() => setView("dashboard")} />;
  }

  if (view === "recommendations") {
    return <CropRecommendations onBack={() => setView("dashboard")} />;
  }

  if (view === "market") {
    return <MarketTrends onBack={() => setView("dashboard")} />;
  }

  if (view === "tips") {
    return <DailyFarmingTips onBack={() => setView("dashboard")} />;
  }

  if (view === "weather") {
    return <WeatherUpdates onBack={() => setView("dashboard")} />;
  }


  if (view === "schemes") {
    return <GovernmentSchemes onBack={() => setView("dashboard")} onOpenDetails={(scheme) => setView({ type: "schemeDetails", scheme })} onOpenSaved={() => setView("savedSchemes")} />;
  }

  if (view && view.type === "schemeDetails") {
    return (
      <GovernmentSchemeDetails
        scheme={view.scheme}
        onBack={() => setView("schemes")}
        onSave={(scheme) => {
          const saved = JSON.parse(localStorage.getItem("savedSchemes") || "[]");
          if (!saved.some((item) => item.title === scheme.title)) {
            const updated = [...saved, scheme];
            localStorage.setItem("savedSchemes", JSON.stringify(updated));
          }
          setView("savedSchemes");
        }}
      />
    );
  }

  if (view === "savedSchemes") {
    return <SavedSchemes onBack={() => setView("schemes")} />;
  }

  if (view === "notifications") {
    return <Notifications onBack={() => setView("dashboard")} />;
  }

  if (view === "settings") {
    return <Settings onBack={() => setView("dashboard")} />;
  }

  if (view === "insights") {
    return <AIInsights onBack={() => setView("dashboard")} />;
  }

  if (view === "profile") {
    return <ProfilePage onBack={() => setView("dashboard")} onProfileSave={setSavedProfile} />;
  }

  return <WelcomeScreen onCreate={() => setView("create")} onSignIn={() => setView("signin")} />;
}

export default App;
 