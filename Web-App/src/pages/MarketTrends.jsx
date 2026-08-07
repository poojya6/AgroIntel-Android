function MarketTrends({ onBack }) {
  const crops = [
    {
      name: "Rice (Paddy)",
      icon: "🌾",
      states: ["West Bengal", "Punjab", "Andhra Pradesh", "Telangana"],
      uses: ["Staple food", "Exports", "Food Processing"],
      demand: "★★★★★ Very High",
    },
    {
      name: "Wheat",
      icon: "🌾",
      states: ["Punjab", "Haryana", "Uttar Pradesh", "Madhya Pradesh"],
      uses: ["Flour", "Bakery", "Food Industry"],
      demand: "★★★★★ Very High",
    },
    {
      name: "Maize",
      icon: "🌽",
      states: ["Karnataka", "Andhra Pradesh", "Bihar"],
      uses: ["Food", "Poultry Feed", "Starch Industry"],
      demand: "★★★★ High",
    },
    {
      name: "Cotton",
      icon: "🌿",
      states: ["Gujarat", "Maharashtra", "Telangana"],
      uses: ["Textile Industry"],
      demand: "★★★★ High",
    },
    {
      name: "Sugarcane",
      icon: "🎋",
      states: ["Uttar Pradesh", "Maharashtra", "Karnataka", "Tamil Nadu"],
      uses: ["Sugar", "Ethanol", "Jaggery"],
      demand: "★★★★ High",
    },
    {
      name: "Groundnut",
      icon: "🌰",
      states: ["Andhra Pradesh", "Gujarat", "Karnataka", "Tamil Nadu"],
      uses: ["Edible Oil", "Snacks", "Exports"],
      demand: "★★★★ High",
    },
  ];

  return (
    <div className="market-page">
      <div className="market-header-blue">
        <button className="back-button-market" type="button" onClick={onBack}>
          ← Most Demanded Crops in India
        </button>

        <div className="market-status-inline">
          <div className="status-content">
            <div className="status-icon">🌱</div>
            <div className="status-text">
              <p className="status-label">Educational Overview</p>
              <h2 className="status-title">Offline Insights</h2>
            </div>
          </div>
          <span className="status-badge-inline active">● Educational</span>
        </div>
      </div>

      <div className="market-content">
        <div className="price-list">
          {crops.map((crop) => (
            <div key={crop.name} className="price-card">
              <div className="price-card-main">
                <div className="status-icon">{crop.icon}</div>
                <div>
                  <strong className="crop-name">{crop.name}</strong>
                  <p className="crop-time">Main Producing States</p>
                </div>
              </div>
              <div className="price-card-info">
                <p className="crop-price">{crop.states.join(" • ")}</p>
              </div>
              <div className="price-card-footer">
                <p className="volume-label">Major Uses</p>
                <span className="volume-badge high">{crop.uses.join(" • ")}</span>
              </div>
              <div className="price-card-footer">
                <p className="volume-label">Demand</p>
                <span className="volume-badge high">{crop.demand}</span>
              </div>
            </div>
          ))}
        </div>

        <div className="section-card" style={{ marginTop: "16px" }}>
          <h3>About this Information</h3>
          <p>
            This page provides general information about the most commonly cultivated and highly demanded crops in India. It is intended for educational purposes and does not use real-time market data or external APIs.
          </p>
        </div>
      </div>
    </div>
  );
}

export default MarketTrends;
