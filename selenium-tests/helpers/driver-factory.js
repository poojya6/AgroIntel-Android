const { Builder, By, until } = require('selenium-webdriver');
const chrome = require('selenium-webdriver/chrome');
const config = require('../config/test-config');

class DriverFactory {
  static async createDriver() {
    const options = new chrome.Options();
    
    if (config.headless) {
      options.addArguments('--headless=new');
    }
    options.addArguments('--no-sandbox');
    options.addArguments('--disable-dev-shm-usage');
    options.addArguments('--disable-gpu');
    options.addArguments('--window-size=1920,1080');

    const driver = await new Builder()
      .forBrowser(config.browser)
      .setChromeOptions(options)
      .build();

    await driver.manage().setTimeouts({ implicit: config.timeout });
    return driver;
  }

  static async waitAndClick(driver, locator, timeout = config.timeout) {
    const element = await driver.wait(until.elementLocated(locator), timeout);
    await driver.wait(until.elementIsVisible(element), timeout);
    await element.click();
    return element;
  }

  static async waitAndType(driver, locator, text, timeout = config.timeout) {
    const element = await driver.wait(until.elementLocated(locator), timeout);
    await driver.wait(until.elementIsVisible(element), timeout);
    await element.clear();
    await element.sendKeys(text);
    return element;
  }

  static async getElementText(driver, locator, timeout = config.timeout) {
    const element = await driver.wait(until.elementLocated(locator), timeout);
    return await element.getText();
  }
}

module.exports = DriverFactory;
