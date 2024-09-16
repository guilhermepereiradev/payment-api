# payment-api
## Running the Project

To run the project, follow these steps:

1. **Run the Spring Boot Application**

   In the project root directory, execute the following command to start the Spring Boot application:

   ```bash
   ./gradlew bootRun

2. **Start WireMock**

   Navigate to the wiremock directory and execute the following script to start WireMock:

   ```bash
   cd wiremock
   ./start.sh

## Follow up questions
1. **How long did you spend on the test? What would you add if you had more time?**

   Around 4-5 hours.

2. **What was the most useful feature that was added to the latest version of your chosen language? Please include a snippet of code that shows how you've used it.**

   Not in the latest version, but in my opinion, one of the most interesting features available since Java 8 is the Streams API.

```java
var product = productsResponse.stream()
        .filter(p -> p.id().equals(productRequest.id()))
        .findFirst()
        .orElseThrow(() -> new EntityNotFoundException("Product not found for id: " + productRequest.id()));
```

3. **What did you find most difficult?**

   Understanding the business requirements and modeling the solution.

4. **What mechanism did you put in place to track down issues in production on this code? If you didn’t put anything, write down what you could do.**

   A log tracing tool could have been added, as log tracing is essential for traceability.

5. **The Wiremock represents one source of information. We should be prepared to integrate with more sources. List the steps that we would need to take to add more sources of items with different formats and promotions.**

   New classes should be created following the model of `ProductsClient`, which uses Spring WebFlux for HTTP connections, or consumers could be built if other protocols such as AMQP are needed.