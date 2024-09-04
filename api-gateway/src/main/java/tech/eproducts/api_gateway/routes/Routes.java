package tech.eproducts.api_gateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

/**
 * Configuration class for defining routes in the API Gateway.
 * This class maps incoming requests to the appropriate microservices.
 */
@Configuration
public class Routes {

    /**
     * Defines the route for the user management service.
     * Maps requests with the path prefix "/api/user" to the user management service running on port 8081.
     *
     * @return RouterFunction for the user management service route.
     */
    @Bean
    public RouterFunction<ServerResponse> userServiceRoute() {
        return GatewayRouterFunctions.route("user-management-service")
                .route(RequestPredicates.path("/api/users/**"), HandlerFunctions.http("http://localhost:8081"))
                .build();
    }

    /**
     * Defines the route for the order management service.
     * Maps requests with the path prefix "/api/orders" to the order management service running on port 8082.
     *
     * @return RouterFunction for the order management service route.
     */
    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute() {
        return GatewayRouterFunctions.route("order-management-service")
                .route(RequestPredicates.path("/api/orders/**"), HandlerFunctions.http("http://localhost:8082"))
                .build();
    }

    /**
     * Defines the route for the product catalog service.
     * Maps requests with the path prefix "/api/products" to the product catalog service running on port 8083.
     *
     * @return RouterFunction for the product catalog service route.
     */
    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {
        return GatewayRouterFunctions.route("product-catalog-service")
                .route(RequestPredicates.path("/api/products/**"), HandlerFunctions.http("http://localhost:8083"))
                .build();
    }

    /**
     * Defines the route for the review rating service.
     * Maps requests with the path prefix "/api/reviews" to the review rating service running on port 8084.
     *
     * @return RouterFunction for the review rating service route.
     */
    @Bean
    public RouterFunction<ServerResponse> reviewServiceRoute() {
        return GatewayRouterFunctions.route("review-rating-service")
                .route(RequestPredicates.path("/api/reviews/**"), HandlerFunctions.http("http://localhost:8084"))
                .build();
    }

    /**
     * Defines the route for the shopping cart service.
     * Maps requests with the path prefix "/api/carts" to the shopping cart service running on port 8085.
     *
     * @return RouterFunction for the shopping cart service route.
     */
    @Bean
    public RouterFunction<ServerResponse> shoppingCartServiceRoute() {
        return GatewayRouterFunctions.route("shopping-cart-service")
                .route(RequestPredicates.path("/api/carts/**"), HandlerFunctions.http("http://localhost:8085"))
                .build();
    }

    /**
     * Defines the route for the payment service.
     * Maps requests with the path prefix "/api/payments" to the payment service running on port 8086.
     *
     * @return RouterFunction for the payment service route.
     */
    @Bean
    public RouterFunction<ServerResponse> paymentServiceRoute() {
        return GatewayRouterFunctions.route("payment-service")
                .route(RequestPredicates.path("/api/payments/**"), HandlerFunctions.http("http://localhost:8086"))
                .build();
    }
}