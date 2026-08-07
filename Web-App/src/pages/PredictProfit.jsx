import { useEffect, useState } from "react";
import { auth, db } from "../firebase";
import { doc, setDoc } from "firebase/firestore";
import { getRecommendationInputDefaults } from "../recommendationData";
const cropOptions = [
  "Wheat",
  "Corn",
  "Rice",
  "Soybean",
  "Cotton",
  "Sugarcane",
];

const seasonOptions = ["Rabi", "Kharif", "Zaid", "Summer"];

const formatCurrency = (value) => {
  const amount = Number(value);
  if (Number.isNaN(amount)) return "₹0";
  return `₹${amount.toLocaleString()}`;
};

const calculateResult = ({ land, yieldPerAcre, costPerAcre, pricePerQuintal, crop }) => {
  const landArea = Number(land) || 0;
  const yieldValue = Number(yieldPerAcre) || 0;
  const cost = Number(costPerAcre) || 0;
  const price = Number(pricePerQuintal) || 0;
  const totalQuantity = landArea * yieldValue;
  const totalRevenue = totalQuantity * price;
  const totalCost = cost * landArea;
  const profit = totalRevenue - totalCost;
  const margin = totalRevenue ? Math.round((profit / totalRevenue) * 100) : 0;

  return {
    totalProfit: formatCurrency(profit),
    totalRevenue: formatCurrency(totalRevenue),
    totalCost: formatCurrency(totalCost),
    profitMargin: `${margin}%`,
    profitPerAcre: formatCurrency(landArea ? profit / landArea : 0),
    totalQuantity: `${totalQuantity.toLocaleString()} Quintals`,
    crop,
    verdict: margin > 40 ? "Highly Profitable" : "Moderately Profitable",
    description:
      margin > 40
        ? "This crop shows strong profit potential based on the selected inputs."
        : "The crop can still be viable if costs are managed carefully.",
  };
};

function PredictProfit({ onBack }) {
  const [profile, setProfile] = useState({});
  const [inputs, setInputs] = useState(() => {
    const defaults = getRecommendationInputDefaults();
    return {
      crop: defaults.crop,
      land: "4",
      season: "Rabi",
      yieldPerAcre: defaults.yieldPerAcre,
      costPerAcre: "25000",
      pricePerQuintal: defaults.pricePerQuintal,
    };
  });
  const getDefaultResult = (crop = inputs.crop) => ({
    totalProfit: "₹0",
    totalRevenue: "₹0",
    totalCost: "₹0",
    profitMargin: "0%",
    profitPerAcre: "₹0",
    totalQuantity: "0 Quintals",
    crop,
    verdict: "Awaiting Prediction",
    description: "Fill the inputs and calculate to see the profit prediction.",
  });
  const [result, setResult] = useState(() => getDefaultResult());
  const [hasCalculated, setHasCalculated] = useState(false);

  useEffect(() => {
    const savedProfile = localStorage.getItem("farmProfile");
    if (savedProfile) {
      const profileData = JSON.parse(savedProfile);
      setProfile(profileData);
      const defaults = getRecommendationInputDefaults(profileData);
      setInputs((prev) => ({
        ...prev,
        crop: defaults.crop,
        yieldPerAcre: defaults.yieldPerAcre,
        pricePerQuintal: defaults.pricePerQuintal,
        ...(profileData.land ? { land: profileData.land } : {}),
      }));
    }

    const savedPrediction = localStorage.getItem("lastPrediction");
    if (savedPrediction) {
      try {
        const parsedPrediction = JSON.parse(savedPrediction);
        setResult({
          ...getDefaultResult(parsedPrediction.crop || inputs.crop),
          ...parsedPrediction,
        });
        setHasCalculated(true);
      } catch (error) {
        console.error("Unable to load saved prediction:", error);
      }
    }
  }, []);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setInputs((prev) => ({ ...prev, [name]: value }));
  };

  

  const handleCalculate = () => {
    const calculatedResult = calculateResult(inputs);
    const predictionSummary = {
      ...calculatedResult,
      land: inputs.land,
      crop: inputs.crop,
      season: inputs.season,
      pricePerQuintal: inputs.pricePerQuintal,
    };

    localStorage.setItem("lastPrediction", JSON.stringify(predictionSummary));
    setResult(calculatedResult);
    setHasCalculated(true);
  };

  const handleEdit = () => {
    setHasCalculated(false);
    setResult(getDefaultResult(inputs.crop));
  };

  return (
    <div className="predict-page">
      <button className="back-button" type="button" onClick={onBack}>
        ← Back
      </button>

      <div className="profit-form-card">
        <div className="profit-form-header">
          <div className="profit-form-header-icon">🧮</div>
          <div className="profit-form-header-text">
            <h1>Calculate Crop Profitability</h1>
            <p>Enter your crop details for AI-powered predictions</p>
          </div>
        </div>
        <div className="profit-form-content">
          <label>Crop Type</label>
          <select name="crop" value={inputs.crop} onChange={handleInputChange}>
            {cropOptions.map((crop) => (
              <option key={crop} value={crop}>
                {crop}
              </option>
            ))}
          </select>

          <label>Land Area (Acres)</label>
          <input
            name="land"
            type="number"
            min="0"
            value={inputs.land}
            onChange={handleInputChange}
            placeholder="4"
          />

          <label>Season</label>
          <select name="season" value={inputs.season} onChange={handleInputChange}>
            {seasonOptions.map((season) => (
              <option key={season} value={season}>
                {season}
              </option>
            ))}
          </select>

          <label>Expected Yield (Quintal/Acre)</label>
          <input
            name="yieldPerAcre"
            type="number"
            min="0"
            value={inputs.yieldPerAcre}
            onChange={handleInputChange}
            placeholder="45"
          />

          <label>Estimated Cultivation Cost (₹/Acre)</label>
          <input
            name="costPerAcre"
            type="number"
            min="0"
            value={inputs.costPerAcre}
            onChange={handleInputChange}
            placeholder="25000"
          />

          <label>Market Price (₹/Quintal)</label>
          <input
            name="pricePerQuintal"
            type="number"
            min="0"
            value={inputs.pricePerQuintal}
            onChange={handleInputChange}
            placeholder="2650"
          />

          <button className="calculate-button" type="button" onClick={handleCalculate}>
            Calculate Profit
          </button>
        </div>
      </div>

      <div className="predict-result-page">
        <div className="profit-header">
          <h1>Profit Prediction Result</h1>
          <p>{inputs.crop} · {inputs.season} · {inputs.land} Acres</p>
        </div>

        <div className="profit-summary-card">
          <p>Total Predicted Profit</p>
          <strong>{result.totalProfit}</strong>
          <span>{result.totalQuantity} expected at ₹{Number(inputs.pricePerQuintal).toLocaleString()} per quintal</span>
        </div>

        <div className="profit-metrics-grid">
          <div className="metric-card blue">
            <span className="metric-icon">₹</span>
            <p>Total Revenue</p>
            <strong>{result.totalRevenue}</strong>
          </div>
          <div className="metric-card red">
            <span className="metric-icon">₹</span>
            <p>Total Cost</p>
            <strong>{result.totalCost}</strong>
          </div>
          <div className="metric-card green">
            <span className="metric-icon">↗</span>
            <p>Profit Margin</p>
            <strong>{result.profitMargin}</strong>
          </div>
          <div className="metric-card yellow">
            <span className="metric-icon">₹</span>
            <p>Profit/Acre</p>
            <strong>{result.profitPerAcre}</strong>
          </div>
        </div>

        <div className="profit-verdict-card">
          <strong>{hasCalculated ? `✓ ${result.verdict}` : result.verdict}</strong>
          <p>{result.description}</p>
        </div>

        <div className="result-actions">
          <button className="edit-button" type="button" onClick={handleEdit}>
            {hasCalculated ? "Edit Inputs" : "Reset"}
          </button>
          <button className="recommend-button" type="button" onClick={onBack}>
            Back to Dashboard
          </button>
        </div>
      </div>
    </div>
  );
}

export default PredictProfit;
