# Test Instruction with `curl` on my created REST API
## example command
`curl --basic -u "user" -o output.csv http://localhost:8080/portfolio/12`

# Tracer Bullets: myself as client
## TODO 1: figure out endpoint http address
### Based on Chrome developer tool => https://tryme.fasolutions.com./graphql
## TODO 2: make an example request with `curl`
### Request access token
Guide to get access token: https://documentation.fasolutions.com/en/authentication-and-access.html

```
curl -d "client_id=external-api" -d "xxx" -d "password=xxx" -d "grant_type=password" "https://tryme.fasolutions.com/auth/realms/fa/protocol/openid-connect/token"
```
### method `POST`
### request body payload:
```
curl -X POST -H "Content-Type: application/json" -d '{"query":"pfQuery {portfolio(id:13) shortName}", "variables":null, "operatioName":"pfQuery"}' https://tryme.fasolutions.com./graphql


```
