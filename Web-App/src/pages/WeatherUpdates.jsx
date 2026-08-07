function WeatherUpdates({ onBack }) {
  const weatherItems = [
    { label: "Today", value: "Sunshine · 28°C", detail: "Light breeze, 12% chance of rain" },
    { label: "Next 3 Days", value: "Stable", detail: "Dry weather with moderate temperatures" },
    { label: "Rain Forecast", value: "15 mm", detail: "Expected in the next 48 hours" },
  ];

  return (
    <div className="generic-page">
      <button className="back-button" type="button" onClick={onBack}>
        ← Back
      </button>
      <div className="page-header blue">
        <h1>Weather Updates</h1>
        <p>Track farm weather conditions and rainfall alerts.</p>
      </div>
      <div className="info-grid">
        {weatherItems.map((item) => (
          <div key={item.label} className="info-card">
            <h2>{item.label}</h2>
            <p className="info-value">{item.value}</p>
            <p>{item.detail}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default WeatherUpdates;
