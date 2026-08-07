function Notifications({ onBack }) {
  const notifications = [
    { title: "Market Alert", detail: "Wheat demand rising, consider selling this week." },
    { title: "Weather Alert", detail: "Light rains expected tomorrow afternoon." },
    { title: "Profile Reminder", detail: "Update your farm details for better insights." },
  ];

  return (
    <div className="generic-page">
      <button className="back-button" type="button" onClick={onBack}>
        ← Back
      </button>
      <div className="page-header purple">
        <h1>Notifications</h1>
        <p>Important updates and alerts for your farm.</p>
      </div>
      <div className="notification-list">
        {notifications.map((item) => (
          <div key={item.title} className="notification-card">
            <h2>{item.title}</h2>
            <p>{item.detail}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Notifications;
