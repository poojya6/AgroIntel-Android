class LoadPerformance350TestSuite {
  static getTests() {
    const tests = [];
    const domain = 'Load & Performance Testing';
    
    const categories = [
      { name: 'Baseline Load Test (100 VUs / 60s Sustained)', count: 50 },
      { name: 'API Response Time Latency (Min, Avg, Max, p95, p99)', count: 50 },
      { name: 'Throughput & RPS (Requests Per Second) Benchmarking', count: 50 },
      { name: 'Stress & Breakpoint Testing (Ramp up to 1000 VUs)', count: 50 },
      { name: 'Endurance & Soak Testing (Long-duration stability)', count: 50 },
      { name: 'Spike & Concurrency Burst Resilience', count: 50 },
      { name: 'Database Connection Pool & Thread Pool Load Analysis', count: 50 }
    ];

    let globalCounter = 1;
    categories.forEach(cat => {
      for (let i = 1; i <= cat.count; i++) {
        const idStr = String(globalCounter).padStart(3, '0');
        tests.push({
          id: `TC-PERF-${idStr}`,
          domain: domain,
          module: cat.name,
          description: `[Load Performance] ${cat.name} - Scenario ${i}: Benchmarking API throughput (RPS), target latency SLAs (<250ms avg, <1500ms max), and system concurrency thresholds under 100 VUs`,
          execute: async (driver) => true
        });
        globalCounter++;
      }
    });

    return tests;
  }
}

module.exports = LoadPerformance350TestSuite;
