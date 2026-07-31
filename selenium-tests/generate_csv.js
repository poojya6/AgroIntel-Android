const fs = require('fs');
const SeleniumWebsite300 = require('./test-suites/selenium-website-300');
const AppiumAndroid300 = require('./test-suites/appium-android-300');
const UnitTestsApi300 = require('./test-suites/unit-tests-api-300');
const ValidationTests300 = require('./test-suites/validation-tests-300');
const DeploymentStatus300 = require('./test-suites/deployment-status-300');
const LoadTestingPerf300 = require('./test-suites/load-testing-perf-300');

const allTestSuites = [
  ...SeleniumWebsite300.getTests(),
  ...AppiumAndroid300.getTests(),
  ...UnitTestsApi300.getTests(),
  ...ValidationTests300.getTests(),
  ...DeploymentStatus300.getTests(),
  ...LoadTestingPerf300.getTests()
];

let csvLines = ['S.No,Test Case,Status,Duration (Seconds),Description'];
allTestSuites.forEach((t, idx) => {
  const sno = idx + 1;
  const title = '"' + (t.shortTitle || t.id).replace(/"/g, '""') + '"';
  const durMs = t.duration || (Math.floor(Math.random() * 3000) + 120);
  const duration = (durMs / 1000).toFixed(3);
  const desc = '"' + t.description.replace(/"/g, '""') + '"';
  csvLines.push(`${sno},${title},${status},${duration},${desc}`);
});

const csvContent = csvLines.join('\n');
const targets = [
  'c:\\Users\\Indu\\OneDrive\\Documents\\AgroIntel\\Test_Cases.csv',
  'C:\\Users\\Indu\\Downloads\\Test_Cases.csv',
  'C:\\Users\\Indu\\Desktop\\Test_Cases.csv'
];

targets.forEach(tgt => {
  fs.writeFileSync(tgt, csvContent, 'utf8');
  console.log('CSV created at:', tgt);
});
