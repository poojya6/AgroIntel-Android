import { useMemo } from "react";
import { getLocationRecommendation } from "../recommendationData";

function RevenueAnalysis({ onBack }) {
  const profile = useMemo(() => {
    const saved = localStorage.getItem("farmProfile");
    return saved ? JSON.parse(saved) : { district: "Ludhiana", state: "Punjab" };
  }, []);

  const recommendation = getLocationRecommendation(profile);
  const metrics = [
    { label: "Monthly Revenue", value: "₹1.2L" },
    { label: "Profit Growth", value: "+18%" },
    { label: "Average Yield", value: recommendation.yield },
  ];

  return (
    <div className="generic-page">
      <button className="back-button" type="button" onClick={onBack}>
        ← Back
      </button>
      <div className="page-header green">
        <h1>Revenue Analysis</h1>
        <p>Review your farm revenue and crop earnings at a glance.</p>
      </div>
      <div className="info-grid">
        {metrics.map((metric) => (
          <div key={metric.label} className="info-card">
            <h2>{metric.label}</h2>
            <p className="info-value">{metric.value}</p>
          </div>
        ))}
      </div>
      <div className="section-card">
        <h3>Top Performing Crop</h3>
        <p>{recommendation.crop} is the best fit for {profile.district || recommendation.district}, {profile.state || recommendation.state} with {recommendation.reason.toLowerCase()}</p>
      </div>
    </div>
  );
}

export default RevenueAnalysis;
