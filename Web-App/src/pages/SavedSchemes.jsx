import { useEffect, useState } from "react";

function SavedSchemes({ onBack }) {
  const [savedSchemes, setSavedSchemes] = useState([]);

  useEffect(() => {
    const stored = localStorage.getItem("savedSchemes");
    if (stored) {
      setSavedSchemes(JSON.parse(stored));
    }
  }, []);

  const deleteScheme = (title) => {
    const updated = savedSchemes.filter((scheme) => scheme.title !== title);
    setSavedSchemes(updated);
    localStorage.setItem("savedSchemes", JSON.stringify(updated));
  };

  return (
    <div className="generic-page">
      <button className="back-button" type="button" onClick={onBack}>
        ← Back
      </button>

      <div className="page-header orange">
        <h1>Saved Schemes</h1>
        <p>Your bookmarked government schemes.</p>
      </div>

      <div className="scheme-list">
        {savedSchemes.length === 0 ? (
          <div className="section-card">
            <p>No saved schemes yet.</p>
          </div>
        ) : (
          savedSchemes.map((scheme) => (
            <div key={scheme.title} className="scheme-card">
              <h2>{scheme.title}</h2>
              <p>{scheme.detail}</p>
              <button className="save-button" type="button" onClick={() => deleteScheme(scheme.title)} style={{ marginTop: "12px" }}>
                Delete
              </button>
            </div>
          ))
        )}
      </div>
    </div>
  );
}

export default SavedSchemes;
