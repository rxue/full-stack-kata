# How to run the app locally in terminal
`cd` to the current directory, then run `mvn spring-boot:run`
## After app started successfully, how to test locally
Documentation is available in https://localhost/swagger-ui/index.html

There is one account with *id* `1`, so you can send the following request example with `curl`:

* charge: `curl --insecure -X PUT -H "Content-Type: application/json" -d '{"id": "1","playerAccountId":"1","amount":"5", "type":"CHARGE", "timestamp":"2025-03-10T15:30:00Z"}' https://localhost/api/account/event`
* win: `curl --insecure -X PUT -H "Content-Type: application/json" -d '{"id": "2","playerAccountId":"1","amount":"10", "type":"WIN", "timestamp":"2025-03-17T15:30:00Z"}' https://localhost/api/account/event`


# How to run all the automated tests in terminal
`cd` to `tests` directory, then execute with command `source run_all_tests.sh`
