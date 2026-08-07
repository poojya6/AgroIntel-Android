import { useState } from "react";
import { getRecommendationCards, getLocationRecommendation } from "../cropRecommendations";

function CropRecommendations({ onBack }) {
  const [profile] = useState(() => {
    const saved = localStorage.getItem("farmProfile");
    return saved ? JSON.parse(saved) : { district: "Ludhiana", state: "Punjab", land: "4", season: "Rabi" };
  });

  const recommendation = getLocationRecommendation(profile);
  const recommendations = getRecommendationCards(profile);

  return (
    <div className="recommendations-page">
      <button className="back-button" type="button" onClick={onBack}>
        ← Top 3 Profitable Crops
      </button>

      <div className="profile-banner">
        <div className="banner-icon">⭐</div>
        <div className="banner-content">
          <strong>Based on Your Farm Profile</strong>
          <p>{profile.district || recommendation.district}, {profile.state || recommendation.state} • {profile.land} Acres • {profile.season || "Seasonal"} Season</p>
        </div>
      </div>

      <div className="recommendations-list">
        {recommendations.map((rec) => (
          <div key={rec.id} className="recommendation-card">
            <div className="card-header">
              <div className="rank-badge" style={{ backgroundColor: rec.color }}>
                #{rec.id}
              </div>
              <div className="crop-info">
                <div className="crop-header">
                  <span className="crop-emoji">{rec.emoji}</span>
                  <div>
                    <strong className="crop-name">{rec.crop}</strong>
                    <p className="crop-season">{rec.season}</p>
                  </div>
                </div>
              </div>
              <div className="star-icon">⭐</div>
            </div>

            <div className="card-profit">
              <div>
                <p className="profit-label">Expected Profit/Acre</p>
                <strong className="profit-amount">{rec.profitPerAcre}</strong>
              </div>
              <span className="margin-badge">↑ {rec.margin} margin</span>
            </div>

            <div className="card-metrics">
              <div className="metric">
                <p className="metric-label">Yield</p>
                <strong className="metric-value">{rec.yield}</strong>
                <span className={`metric-badge ${rec.yieldBadge.toLowerCase()}`}>
                  {rec.yieldBadge}
                </span>
              </div>
              <div className="metric">
                <p className="metric-label">Price</p>
                <strong className="metric-value">{rec.price}</strong>
                <span className={`metric-badge ${rec.priceBadge.toLowerCase()}`}>
                  {rec.priceBadge}
                </span>
              </div>
              <div className="metric">
                <p className="metric-label">Risk</p>
                <strong className="metric-value">{rec.risk}</strong>
                <span className={`metric-badge ${rec.riskBadge.toLowerCase()}`}>
                  {rec.riskBadge}
                </span>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default CropRecommendations;
