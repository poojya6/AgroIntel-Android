const recommendationCatalog = {
  "andhra pradesh": {
    tirupati: {
      crop: "Groundnut",
      profitPerAcre: "₹95,000/acre",
      marketPrice: "₹6,800/Q",
      yield: "28 Q/acre",
      risk: "Low",
      reason: "Suitable red soil, good rainfall, and high market demand.",
      top3: ["Groundnut", "Mango", "Tomato"],
    },
    anantapur: {
      crop: "Groundnut",
      profitPerAcre: "₹92,000",
      marketPrice: "₹6,700/Q",
      yield: "27 Q/acre",
      risk: "Low",
      reason: "Well-suited for dryland farming and strong local demand.",
      top3: ["Groundnut", "Sunflower", "Cotton"],
    },
    vijayawada: {
      crop: "Paddy",
      profitPerAcre: "₹84,000",
      marketPrice: "₹2,400/Q",
      yield: "52 Q/acre",
      risk: "Low",
      reason: "Ideal irrigation conditions and strong delta-region demand.",
      top3: ["Paddy", "Cotton", "Groundnut"],
    },
    guntur: {
      crop: "Cotton",
      profitPerAcre: "₹87,000",
      marketPrice: "₹6,900/Q",
      yield: "29 Q/acre",
      risk: "Medium",
      reason: "Good climate support and strong fiber market demand.",
      top3: ["Cotton", "Groundnut", "Paddy"],
    },
    visakhapatnam: {
      crop: "Mango",
      profitPerAcre: "₹90,000",
      marketPrice: "₹5,200/Q",
      yield: "18 Q/acre",
      risk: "Low",
      reason: "Coastal climate and strong horticulture demand make this a strong choice.",
      top3: ["Mango", "Groundnut", "Tomato"],
    },
    default: {
      crop: "Groundnut",
      profitPerAcre: "₹95,000/acre",
      marketPrice: "₹6,800/Q",
      yield: "28 Q/acre",
      risk: "Low",
      reason: "Suitable red soil, good rainfall, and high market demand.",
      top3: ["Groundnut", "Mango", "Cotton"],
    },
  },
  telangana: {
    hyderabad: {
      crop: "Cotton",
      profitPerAcre: "₹88,000",
      marketPrice: "₹7,100/Q",
      yield: "30 Q/acre",
      risk: "Medium",
      reason: "Favorable climate and strong market demand for cotton.",
      top3: ["Cotton", "Maize", "Red Gram"],
    },
    warangal: {
      crop: "Red Gram",
      profitPerAcre: "₹76,000",
      marketPrice: "₹6,200/Q",
      yield: "16 Q/acre",
      risk: "Low",
      reason: "Dryland-friendly and well-suited to the local soil profile.",
      top3: ["Red Gram", "Cotton", "Maize"],
    },
    karimnagar: {
      crop: "Maize",
      profitPerAcre: "₹72,000",
      marketPrice: "₹2,200/Q",
      yield: "34 Q/acre",
      risk: "Low",
      reason: "Reliable yields and strong demand for feed-grade maize.",
      top3: ["Maize", "Cotton", "Red Gram"],
    },
    nizamabad: {
      crop: "Sugarcane",
      profitPerAcre: "₹1,10,000",
      marketPrice: "₹320/Q",
      yield: "400 Q/acre",
      risk: "Medium",
      reason: "Good water access and strong industrial demand support this crop.",
      top3: ["Sugarcane", "Cotton", "Maize"],
    },
    default: {
      crop: "Cotton",
      profitPerAcre: "₹88,000",
      marketPrice: "₹7,100/Q",
      yield: "30 Q/acre",
      risk: "Medium",
      reason: "Favorable climate and strong market demand for cotton.",
      top3: ["Cotton", "Maize", "Red Gram"],
    },
  },
  "tamil nadu": {
    coimbatore: {
      crop: "Sugarcane",
      profitPerAcre: "₹1,15,000",
      marketPrice: "₹340/Q",
      yield: "420 Q/acre",
      risk: "Low",
      reason: "High water availability and strong industrial demand.",
      top3: ["Sugarcane", "Coconut", "Banana"],
    },
    thanjavur: {
      crop: "Paddy",
      profitPerAcre: "₹82,000",
      marketPrice: "₹2,300/Q",
      yield: "55 Q/acre",
      risk: "Low",
      reason: "Ideal wetland conditions and strong paddy market demand.",
      top3: ["Paddy", "Banana", "Sugarcane"],
    },
    madurai: {
      crop: "Banana",
      profitPerAcre: "₹86,000",
      marketPrice: "₹35/Q",
      yield: "320 Q/acre",
      risk: "Medium",
      reason: "Favorable temperature and strong local demand for fruit crops.",
      top3: ["Banana", "Sugarcane", "Cotton"],
    },
    erode: {
      crop: "Cotton",
      profitPerAcre: "₹84,000",
      marketPrice: "₹6,600/Q",
      yield: "28 Q/acre",
      risk: "Low",
      reason: "Well-suited for the district's climate and textile demand.",
      top3: ["Cotton", "Banana", "Sugarcane"],
    },
    default: {
      crop: "Paddy",
      profitPerAcre: "₹82,000",
      marketPrice: "₹2,300/Q",
      yield: "55 Q/acre",
      risk: "Low",
      reason: "Ideal wetland conditions and strong paddy market demand.",
      top3: ["Paddy", "Sugarcane", "Banana"],
    },
  },
  karnataka: {
    mysuru: {
      crop: "Ragi",
      profitPerAcre: "₹75,000",
      marketPrice: "₹3,800/Q",
      yield: "24 Q/acre",
      risk: "Low",
      reason: "Suitable for the region's rainfall pattern and local demand.",
      top3: ["Ragi", "Maize", "Sugarcane"],
    },
    belagavi: {
      crop: "Sugarcane",
      profitPerAcre: "₹1,12,000",
      marketPrice: "₹335/Q",
      yield: "410 Q/acre",
      risk: "Low",
      reason: "Excellent climate and strong demand in the region.",
      top3: ["Sugarcane", "Cotton", "Maize"],
    },
    dharwad: {
      crop: "Maize",
      profitPerAcre: "₹70,000",
      marketPrice: "₹2,100/Q",
      yield: "33 Q/acre",
      risk: "Medium",
      reason: "Well-suited for the district's dryland conditions and feed demand.",
      top3: ["Maize", "Cotton", "Ragi"],
    },
    shivamogga: {
      crop: "Arecanut",
      profitPerAcre: "₹96,000",
      marketPrice: "₹1,200/Q",
      yield: "15 Q/acre",
      risk: "Low",
      reason: "High-value plantation crop with strong regional demand.",
      top3: ["Arecanut", "Coffee", "Ragi"],
    },
    default: {
      crop: "Ragi",
      profitPerAcre: "₹75,000",
      marketPrice: "₹3,800/Q",
      yield: "24 Q/acre",
      risk: "Low",
      reason: "Suitable for the region's rainfall pattern and local demand.",
      top3: ["Ragi", "Maize", "Sugarcane"],
    },
  },
  punjab: {
    ludhiana: {
      crop: "Wheat",
      profitPerAcre: "₹69,000",
      marketPrice: "₹2,650/Q",
      yield: "45 Q/acre",
      risk: "Low",
      reason: "Well-aligned with Punjab's wheat belt and strong market demand.",
      top3: ["Wheat", "Paddy", "Maize"],
    },
    amritsar: {
      crop: "Wheat",
      profitPerAcre: "₹71,000",
      marketPrice: "₹2,700/Q",
      yield: "46 Q/acre",
      risk: "Low",
      reason: "Strong regional suitability and dependable market pricing.",
      top3: ["Wheat", "Paddy", "Maize"],
    },
    patiala: {
      crop: "Paddy",
      profitPerAcre: "₹74,000",
      marketPrice: "₹2,250/Q",
      yield: "50 Q/acre",
      risk: "Medium",
      reason: "Strong irrigation support and good returns from paddy cultivation.",
      top3: ["Paddy", "Wheat", "Maize"],
    },
    jalandhar: {
      crop: "Maize",
      profitPerAcre: "₹66,000",
      marketPrice: "₹2,100/Q",
      yield: "32 Q/acre",
      risk: "Low",
      reason: "Suitable for diversified cropping and strong fodder demand.",
      top3: ["Maize", "Wheat", "Paddy"],
    },
    default: {
      crop: "Wheat",
      profitPerAcre: "₹69,000",
      marketPrice: "₹2,650/Q",
      yield: "45 Q/acre",
      risk: "Low",
      reason: "Well-aligned with Punjab's wheat belt and strong market demand.",
      top3: ["Wheat", "Paddy", "Maize"],
    },
  },
  haryana: {
    karnal: {
      crop: "Wheat",
      profitPerAcre: "₹68,000",
      marketPrice: "₹2,620/Q",
      yield: "44 Q/acre",
      risk: "Low",
      reason: "Strong suitability for the district's agronomic pattern.",
      top3: ["Wheat", "Paddy", "Mustard"],
    },
    hisar: {
      crop: "Cotton",
      profitPerAcre: "₹86,000",
      marketPrice: "₹7,000/Q",
      yield: "29 Q/acre",
      risk: "Medium",
      reason: "Favorable for the region and supported by strong market demand.",
      top3: ["Cotton", "Wheat", "Mustard"],
    },
    gurugram: {
      crop: "Mustard",
      profitPerAcre: "₹64,000",
      marketPrice: "₹5,400/Q",
      yield: "17 Q/acre",
      risk: "Low",
      reason: "Good fit for the region's rabi cropping pattern.",
      top3: ["Mustard", "Wheat", "Cotton"],
    },
    rohtak: {
      crop: "Cotton",
      profitPerAcre: "₹82,000",
      marketPrice: "₹6,800/Q",
      yield: "28 Q/acre",
      risk: "Medium",
      reason: "Supports stronger returns with suitable climatic conditions.",
      top3: ["Cotton", "Wheat", "Mustard"],
    },
    default: {
      crop: "Wheat",
      profitPerAcre: "₹68,000",
      marketPrice: "₹2,620/Q",
      yield: "44 Q/acre",
      risk: "Low",
      reason: "Strong suitability for the district's agronomic pattern.",
      top3: ["Wheat", "Mustard", "Cotton"],
    },
  },
};

const fallbackRecommendation = {
  crop: "Maize",
  profitPerAcre: "₹72,000/acre",
  marketPrice: "₹2,200/Q",
  yield: "35 Q/acre",
  risk: "Medium",
  reason: "General recommendation based on average climatic conditions.",
  top3: ["Maize", "Groundnut", "Cotton"],
};

const normalizeText = (value = "") => value.toString().trim().toLowerCase();

export const getLocationRecommendation = (profile = {}) => {
  const state = normalizeText(profile.state || profile.State || profile.locationState);
  const district = normalizeText(profile.district || profile.District || profile.locationDistrict);

  if (state) {
    const stateRecommendations = recommendationCatalog[state];
    if (stateRecommendations) {
      if (district && stateRecommendations[district]) {
        return { ...stateRecommendations[district], state: profile.state || profile.State || "", district: profile.district || profile.District || "" };
      }

      if (stateRecommendations.default) {
        return { ...stateRecommendations.default, state: profile.state || profile.State || "", district: profile.district || profile.District || "" };
      }
    }
  }

  return { ...fallbackRecommendation, state: profile.state || profile.State || "", district: profile.district || profile.District || "" };
};

const cropMeta = {
  Groundnut: { emoji: "🌰", season: "Kharif Season" },
  Arecanut: { emoji: "🌴", season: "Year-round" },
  Coffee: { emoji: "☕", season: "Year-round" },
  Mango: { emoji: "🥭", season: "Seasonal" },
  Tomato: { emoji: "🍅", season: "Rabi Season" },
  Sunflower: { emoji: "🌻", season: "Kharif Season" },
  Cotton: { emoji: "🌿", season: "Kharif Season" },
  Maize: { emoji: "🌽", season: "Kharif Season" },
  "Red Gram": { emoji: "🫘", season: "Kharif Season" },
  Sugarcane: { emoji: "🎋", season: "Year-round" },
  Coconut: { emoji: "🥥", season: "Year-round" },
  Banana: { emoji: "🍌", season: "Year-round" },
  Paddy: { emoji: "🌾", season: "Kharif Season" },
  Ragi: { emoji: "🌾", season: "Kharif Season" },
  Wheat: { emoji: "🌾", season: "Rabi Season" },
  Mustard: { emoji: "🌱", season: "Rabi Season" },
};

export const getRecommendationCards = (profile = {}) => {
  const recommendation = getLocationRecommendation(profile);
  return recommendation.top3.map((crop, index) => {
    const meta = cropMeta[crop] || { emoji: "🌱", season: "Seasonal" };
    return {
      id: index + 1,
      crop,
      emoji: meta.emoji,
      season: meta.season,
      profitPerAcre: recommendation.profitPerAcre,
      margin: index === 0 ? "58%" : index === 1 ? "52%" : "48%",
      yield: recommendation.yield,
      yieldBadge: index === 0 ? "High" : "Medium",
      price: recommendation.marketPrice,
      priceBadge: index === 0 ? "High" : "Medium",
      risk: recommendation.risk,
      riskBadge: recommendation.risk,
      color: index === 0 ? "#FBB040" : index === 1 ? "#9CA3AF" : "#EF4444",
      reason: recommendation.reason,
    };
  });
};

export const getRecommendationInputDefaults = (profile = {}) => {
  const recommendation = getLocationRecommendation(profile);
  const parsedYield = Number(String(recommendation.yield).replace(/[^\d.]/g, ""));
  const parsedPrice = Number(String(recommendation.marketPrice).replace(/[^\d.]/g, ""));
  return {
    crop: recommendation.crop,
    yieldPerAcre: Number.isFinite(parsedYield) ? String(parsedYield) : "30",
    pricePerQuintal: Number.isFinite(parsedPrice) ? String(parsedPrice) : "2500",
    risk: recommendation.risk,
    reason: recommendation.reason,
  };
};
