const axios = require('axios');
const https = require('https');
const url = 'https://localhost/api/account/event';
const config = {
  httpsAgent: new https.Agent({  
    rejectUnauthorized: false,  // Ignore certificate errors
  }),
  headers: {
    'Content-Type': 'application/json',  // Specify the content type if needed
  }
};

describe('Error case', () => {
  it('charge more amount than balance', async () => {
    try {
      const response = await axios.put(url, {
        id: 1,
        playerAccountId: 1,
        type: "CHARGE",
        amount: 1000000
      }, config);
    } catch(error) {
      expect(error.response.status).toBe(422);
    }
  });
});

describe('Valid charge', () => {
  const chargeEvent = {
    id: 2,
    playerAccountId: 1,
    type: "CHARGE",
    amount: 10
  };
  it('charge sufficient amount from balance', async () => {
    try {
      const response = await axios.put(url, chargeEvent, config);
      expect(response.status).toBe(200);
      expect(response.data).toBe(90);
    } catch(error) {
      throw new Error('This should never happen');
    }
  });
  it('send the same charge event again', async () => {
    try {
      const response = await axios.put(url, chargeEvent, config);
      expect(response.status).toBe(200);
      expect(response.data).toBe(90);
    } catch(error) {
      throw new Error('This should never happen');
    }
  });
}); 
  describe('Win', () => {
    it('2 win incoming events, so the balance after the second events must be more than the balancce after the first event', async () => {
      try {
        const response = await axios.put(url, {
          id: 3,
          playerAccountId: 1,
          type: "WIN",
          amount: 10
        }, config);
        expect(response.status).toBe(200);
        balance = response.data;
        const response2 = await axios.put(url, {
          id: 4,
          playerAccountId: 1,
          type: "WIN",
          amount: 10
        }, config);
        expect(response2.data).toBeGreaterThan(balance);
      } catch(error) {
        throw new Error('This should never happen');
      }
    });
});
