# AgroIntel Selenium End-to-End Test Suite & Excel Analysis Reporter

This directory (`selenium-tests/`) contains a complete, automated Selenium E2E test suite for the AgroIntel application built with Node.js, Selenium WebDriver, and ExcelJS.

## 📁 Directory Layout

```
selenium-tests/
├── config/
│   └── test-config.js         # Base URL, browser choices, timeouts, credentials
├── helpers/
│   ├── driver-factory.js      # Selenium WebDriver setup (Chrome / Headless options)
│   └── excel-reporter.js      # ExcelJS report generator (.xlsx with styled analysis sheets)
├── test-suites/
│   ├── auth.test.js           # Sign In, Sign Up, Profile Setup, Session test cases
│   ├── dashboard.test.js      # Dashboard widgets, navigation, quick actions
│   ├── crop-recommendation.test.js # Soil N-P-K & Climate recommendation engine tests
│   ├── profit-prediction.test.js   # Yield, revenue, cost breakdown, profit forecasting
│   ├── market-trends.test.js  # Mandi market prices, top crops, government schemes
│   └── weather-ai.test.js     # Weather API forecasts & AI Insights advisory
├── reports/                   # Output directory for generated Excel (.xlsx) reports
├── package.json               # Node.js project dependencies
└── run-tests.js               # Main test runner script
```

## 🚀 Getting Started

### 1. Install Dependencies
```bash
cmd /c npm install
```

### 2. Run Test Suite & Generate Excel Report
```bash
cmd /c npm test
```

## 📊 Excel Report Features
The test runner automatically creates an Excel report (`reports/AgroIntel_Selenium_E2E_Analysis_Report_<timestamp>.xlsx`) with three comprehensive sheets:

1. **Executive Summary**: High-level KPIs, total tests, pass rate %, overall duration, and target application environment metrics.
2. **Detailed Test Results**: Itemized list of every test case, module name, status (`PASS`/`FAIL`), execution duration in milliseconds, timestamp, and error stack traces.
3. **Module Analysis**: Categorized breakdown per feature module showing individual pass rates and average test execution times.
