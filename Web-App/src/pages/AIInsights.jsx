import { useMemo } from "react";

const getInsightIcon = (color) => {
  const icons = {
    green: "📈",
    blue: "🌾",
    yellow: "💡",
    purple: "⚡",
    red: "⚠️",
  };
  return icons[color] || "📌";
};

function AIInsights({ onBack }) {
  const stats = useMemo(
    () => [
      { label: "Insights Generated", value: "24" },
      { label: "Accuracy Rate", value: "94%" },
    ],
    []
  );

  const insights = useMemo(
    () => [
      {
        title: "Market Opportunity",
        description: "Wheat prices expected to rise by 8% in next 2 months",
        tag: "High",
        color: "green",
        icon: "📈",
      },
      {
        title: "Planting Recommendation",
        description: "Optimal planting window: Nov 15 - Nov 30 for maximum yield",
        tag: "High",
        color: "blue",
        icon: "🌾",
      },
      {
        title: "Cost Optimization",
        description: "Switch to organic fertilizer can reduce costs by 15%",
        tag: "Normal",
        color: "yellow",
        icon: "💡",
      },
      {
        title: "Weather Alert",
        description: "Above-average rainfall predicted - adjust irrigation schedule",
        tag: "Medium",
        color: "purple",
        icon: "⚡",
      },
    ],
    []
  );

  return (
    <div className="insights-page">
      <button className="back-button" type="button" onClick={onBack}>
        ← AI Insights
      </button>

      <div className="insights-header">
        <div style={{ display: "flex", alignItems: "center", gap: "12px", marginBottom: "8px" }}>
          <span style={{ fontSize: "24px" }}>🧠</span>
          <div>
            <h2 style={{ margin: "0", fontSize: "18px", fontWeight: 600 }}>Personalized Insights</h2>
            <p style={{ margin: "0", fontSize: "13px", opacity: 0.9 }}>Based on your farm data</p>
          </div>
        </div>
      </div>

      <div className="insights-stats">
        {stats.map((item) => (
          <div key={item.label} className="insight-stat-card">
            <p>{item.label}</p>
            <strong>{item.value}</strong>
          </div>
        ))}
      </div>

      <div className="insight-list">
        <h2>Latest Insights</h2>
        {insights.map((item) => (
          <div key={item.title} className={`insight-card ${item.color}`}>
            <div className="insight-card-label">
              <span>{item.title}</span>
              <strong className="insight-tag">{item.tag}</strong>
            </div>
            <p>{item.description}</p>
            <button type="button" className="link-button">
              View Details →
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default AIInsights;

