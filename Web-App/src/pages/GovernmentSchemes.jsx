import { governmentSchemes } from "./governmentSchemesData";

function GovernmentSchemes({ onBack, onOpenDetails }) {
  return (
    <div className="generic-page">
      <button className="back-button" type="button" onClick={onBack}>
        ← Back
      </button>
      <div className="page-header orange">
        <h1>Government Schemes</h1>
        <p>Explore supported programs for farmers and subsidies.</p>
      </div>

      <div className="scheme-list">
        {governmentSchemes.map((scheme) => (
          <button
            key={scheme.title}
            className="scheme-card"
            type="button"
            onClick={() => onOpenDetails(scheme)}
            style={{ textAlign: "left", cursor: "pointer" }}
          >
            <h2>{scheme.title}</h2>
            <p>{scheme.detail}</p>
          </button>
        ))}
      </div>

      <div className="section-card" style={{ marginTop: "16px" }}>
        <h2>Need More Government Schemes?</h2>
        <p>Explore the latest Government of India schemes for farmers through the official MyScheme portal.</p>
        <button
          className="save-button"
          type="button"
          onClick={() => window.open("https://www.myscheme.gov.in/", "_blank", "noopener,noreferrer")}
          style={{ marginTop: "14px" }}
        >
          Explore Official Government Schemes
        </button>
      </div>
    </div>
  );
}

export default GovernmentSchemes;
