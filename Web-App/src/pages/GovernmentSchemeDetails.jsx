function GovernmentSchemeDetails({ scheme, onBack }) {
  if (!scheme) {
    return null;
  }

  const openWebsite = () => {
    window.open(scheme.officialWebsite, "_blank", "noopener,noreferrer");
  };

  return (
    <div className="generic-page">
      <button className="back-button" type="button" onClick={onBack}>
        ← Back
      </button>

      <div className="page-header orange">
        <h1>{scheme.title}</h1>
        <p>{scheme.detail}</p>
      </div>

      <div className="scheme-list">
        <div className="section-card">
          <h2>Scheme Name</h2>
          <p>{scheme.title}</p>
        </div>

        <div className="section-card">
          <h2>Description</h2>
          <p>{scheme.description}</p>
        </div>

        <div className="section-card">
          <h2>Benefits</h2>
          <ul>
            {scheme.benefits.map((item) => (
              <li key={item}>{item}</li>
            ))}
          </ul>
        </div>

        <div className="section-card">
          <h2>Eligibility</h2>
          <ul>
            {scheme.eligibility.map((item) => (
              <li key={item}>{item}</li>
            ))}
          </ul>
        </div>

        <div className="section-card">
          <h2>Required Documents</h2>
          <ul>
            {scheme.documents.map((item) => (
              <li key={item}>{item}</li>
            ))}
          </ul>
        </div>

        <div className="section-card">
          <h2>Application Process</h2>
          <ul>
            {scheme.process.map((item) => (
              <li key={item}>{item}</li>
            ))}
          </ul>
        </div>

        <div className="section-card">
          <h2>Official Website</h2>
          <p>{scheme.officialWebsite}</p>
        </div>

        <div className="section-card">
          <h2>Status</h2>
          <p>{scheme.status}</p>
        </div>
      </div>

      <div className="scheme-list" style={{ marginTop: "16px" }}>
        <button className="save-button" type="button" onClick={openWebsite}>
          Visit Official Website
        </button>
        <button className="save-button" type="button" onClick={onBack} style={{ marginTop: "10px" }}>
          Back to Government Schemes
        </button>
      </div>
    </div>
  );
}

export default GovernmentSchemeDetails;
