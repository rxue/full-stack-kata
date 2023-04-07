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



test("async test", async () => {
    const accessToken = await getToken();
    const result = await axios.post('https://localhost:8080/portfolio/12', {}, {headers: {'Authorization': 'Bearer ' + accessToken}, httpsAgent:agent});
    expect(result.status).toBe(200);
});

