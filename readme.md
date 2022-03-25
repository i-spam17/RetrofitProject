check-list test by retrofit

1. category API
   1.1. positive tests 
   1.2. negative tests
   1.2.1. check field ID from GET request

2. Product API
   2.1. positive tests
   2.1.1. check getProductsList GET request
   2.1.2. check createProduct POST request
   2.1.3. check updateProduct PUT request
   2.1.4. check getSpecificProduct GET request
   2.1.5. in tearDown check delete product DELETE request

   2.2. negative tests
   2.2.1. check POST request with different BODY
   2.2.2. check PUT request with different BODY
   2.2.3. check field ID from GET specific product request 
   2.2.4. check field ID from DELETE request 
   
run tests:
mvn clean test allure:serve