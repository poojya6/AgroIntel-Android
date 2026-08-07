function Settings({ onBack }) {
  return (
    <div className="generic-page">
      <button className="back-button" type="button" onClick={onBack}>
        ← Back
      </button>
      <div className="page-header blue">
        <h1>Settings</h1>
        <p>Manage app preferences and account settings.</p>
      </div>
      <div className="section-card">
        <h3>Notification Settings</h3>
        <p>Enable or disable alerts for weather, markets, and insights.</p>
      </div>
      <div className="section-card">
        <h3>Privacy</h3>
        <p>Manage your data and app permissions.</p>
      </div>
    </div>
  );
}

export default Settings;
