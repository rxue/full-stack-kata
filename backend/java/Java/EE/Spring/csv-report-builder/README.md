# Test Instruction
## Example command with curl
`curl --insecure --basic -u <username>:<password> --fail -o output.csv "https://localhost:8080/portfolios/12/transactions?startDate=2021-11-30&endDate=2021-12-31"`

## How to run the integration test along with unit tests
`cd` to the `IT` directory, then run command: `. run_test.sh <username> <password>`
