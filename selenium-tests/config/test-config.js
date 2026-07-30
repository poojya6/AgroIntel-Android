const path = require('path');

module.exports = {
  baseUrl: process.env.BASE_URL || 'http://localhost:3000',
  browser: process.env.BROWSER || 'chrome',
  headless: process.env.HEADLESS !== 'false',
  timeout: 10000,
  reportsDir: path.join(__dirname, '..', 'reports'),
  testUser: {
    name: 'Test Farmer',
    email: 'farmer@agrointel.com',
    password: 'Password123!',
    phone: '9876543210',
    location: 'Punjab, India',
    landSizeAcres: '5.5'
  }
};
