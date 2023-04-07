const axios = require('axios');
const https = require('https');
const { URLSearchParams } = require('url');
function getArgument(argName) {
    const myArg = process.argv.find(arg => arg.startsWith('--' + argName + '='));
    return myArg ? myArg.split('=')[1] : null;
}

const formData = new URLSearchParams();
formData.append('client_id', 'external-api');
formData.append('username', getArgument('username'));
formData.append('password', getArgument('password'));
formData.append('grant_type', 'password');
const headers = {
    'Content-Type': 'application/x-www-form-urlencoded'
};

const agent = new https.Agent({
    rejectUnauthorized: false
  });
async function getToken() {
    const tokenResponse = await axios.post('https://tryme.fasolutions.com/auth/realms/fa/protocol/openid-connect/token', formData, {headers});
    return tokenResponse.data["access_token"];
}


const endpointURL = 'https://localhost:8080/portfolio/12'
test("with valid access token", async () => {
    const accessToken = await getToken();
    const config = {
        headers: {'Authorization': 'Bearer ' + accessToken},
        httpsAgent:agent
      }; 
    const result = await axios.post(endpointURL, {}, config);
    expect(result.status).toBe(200);
});

test("with invalid access token", async () => {
    try {
        const config = {
            headers: {'Authorization': 'Bearer x'},
            httpsAgent:agent
          }; 
        await axios.post(endpointURL, {}, config);
    } catch (err) {
        expect(err.response.status).toBe(401);
    }
});

test("with basic authentication", async () => {
    const config = {
        headers: { 'Content-Type': 'application/json' },
        auth: { username: getArgument('username'), password: getArgument('password') },
        httpsAgent:agent
      };    
    const result = await axios.post(endpointURL, {}, config);
    expect(result.status).toBe(200);
});

