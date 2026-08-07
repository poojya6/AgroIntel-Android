import { useEffect, useState } from "react";

const farmingTips = [
  {
    title: "🌱 Crop Rotation",
    icon: "🌾",
    description:
      "Rotate crops regularly to maintain soil fertility and reduce pest infestations.",
  },
  {
    title: "🧪 Soil Testing",
    icon: "🧪",
    description:
      "Test your soil before sowing to choose the right crop and fertilizer.",
  },
  {
    title: "🌼 Quality Seeds",
    icon: "🌼",
    description:
      "Use certified quality seeds for better germination and higher yield.",
  },
  {
    title: "💧 Water Management",
    icon: "💧",
    description:
      "Avoid overwatering as excessive irrigation can damage crop roots.",
  },
  {
    title: "🌿 Fertilizer Care",
    icon: "🌿",
    description:
      "Apply fertilizers in the recommended quantity to improve crop growth.",
  },
  {
    title: "🐛 Pest Monitoring",
    icon: "🐛",
    description:
      "Monitor crops regularly for pests and diseases to take early action.",
  },
  {
    title: "🌿 Weed Control",
    icon: "🌿",
    description:
      "Keep your farm free from weeds to reduce competition for nutrients.",
  },
  {
    title: "🌾 Harvest Timing",
    icon: "🌾",
    description:
      "Harvest crops at the proper maturity stage to obtain better quality and market value.",
  },
  {
    title: "📦 Storage",
    icon: "📦",
    description:
      "Store harvested grains in a clean and dry place to prevent spoilage.",
  },
  {
    title: "☀️ Regional Planning",
    icon: "☀️",
    description:
      "Choose crops suitable for your region, season, and soil conditions for maximum profitability.",
  },
];

function shuffleTips(items) {
  const shuffled = [...items];
  for (let index = shuffled.length - 1; index > 0; index -= 1) {
    const randomIndex = Math.floor(Math.random() * (index + 1));
    [shuffled[index], shuffled[randomIndex]] = [shuffled[randomIndex], shuffled[index]];
  }
  return shuffled;
}

function DailyFarmingTips({ onBack }) {
  const [tipState, setTipState] = useState({
    queue: [],
    currentTip: farmingTips[0],
  });

  useEffect(() => {
    const shuffled = shuffleTips(farmingTips);
    setTipState({
      queue: shuffled.slice(1),
      currentTip: shuffled[0],
    });
  }, []);

  const handleNextTip = () => {
    setTipState((prevState) => {
      if (prevState.queue.length > 0) {
        const [nextTip, ...remaining] = prevState.queue;
        return {
          queue: remaining,
          currentTip: nextTip,
        };
      }

      const shuffled = shuffleTips(farmingTips);
      return {
        queue: shuffled.slice(1),
        currentTip: shuffled[0],
      };
    });
  };

  return (
    <div className="tips-page">
      <div className="tips-shell">
        <button className="tips-back-btn" type="button" onClick={onBack}>
          ← Back to Dashboard
        </button>

        <div className="tips-hero">
          <div className="tips-icon">🌱</div>
          <div>
            <p className="tips-eyebrow">Smart guidance</p>
            <h1>Daily Farming Tips</h1>
            <p className="tips-subtitle">
              Discover a fresh practical tip for better farm decisions.
            </p>
          </div>
        </div>

        <div className="tips-card">
          <div className="tips-card-icon">{tipState.currentTip?.icon}</div>
          <h2>{tipState.currentTip?.title}</h2>
          <p>{tipState.currentTip?.description}</p>
          <button className="tips-next-btn" type="button" onClick={handleNextTip}>
            Next Tip
          </button>
        </div>
      </div>
    </div>
  );
}

export default DailyFarmingTips;
