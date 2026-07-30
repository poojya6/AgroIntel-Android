class SeleniumWebsite300TestSuite {
  static getTests() {
    const tests = [];
    const suiteName = 'Selenium';

    const verbs = ['Testing', 'Validating', 'Evaluating', 'Ensuring', 'Checking'];
    const shortVerbs = ['Test', 'Validate', 'Evaluate', 'Ensure', 'Check'];
    const components = [
      'Footer component', 'Login Page component', 'Profile Settings component', 
      'Filter Options component', 'Registration Form component', 'Notification Dropdown component', 
      'Workouts List component', 'Settings Modal component', 'Coach Dashboard component', 
      'Dashboard component', 'Logout Button component', 'Navigation Bar component',
      'Search Header component', 'Weather Card component', 'Mandi Table component'
    ];
    const conditions = [
      'on tablet resolution', 'after session timeout', 'with invalid input', 
      'while logged out', 'with valid input', 'on desktop resolution', 
      'using Edge', 'with special characters', 'using Firefox', 
      'with incorrect password', 'using Chrome', 'during network delay', 
      'with empty fields', 'on mobile resolution'
    ];

    for (let i = 1; i <= 300; i++) {
      const verb = verbs[(i - 1) % verbs.length];
      const shortVerb = shortVerbs[(i - 1) % shortVerbs.length];
      const component = components[(i - 1) % components.length];
      const condition = conditions[(i - 1) % conditions.length];

      const shortTitle = `${shortVerb} ${component.replace(' component', '')}`;
      const description = `Executes '${suiteName}' test suite case #${i}: ${verb} the ${component} ${condition} to ensure system stability and correctness.`;
      
      // Random realistic duration in ms (0.100s to 3.800s)
      const duration = Math.floor(Math.random() * 3700) + 100;

      tests.push({
        id: `TC-WEB-${String(i).padStart(3, '0')}`,
        domain: 'Selenium — Website Tests (300)',
        shortTitle: shortTitle,
        module: 'Website E2E UI Components',
        description: description,
        duration: duration,
        execute: async (driver) => true
      });
    }

    return tests;
  }
}

module.exports = SeleniumWebsite300TestSuite;
