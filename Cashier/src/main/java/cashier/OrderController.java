package cashier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class OrderController {
    private final RestTemplate restTemplate;

    RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();

    public OrderController() {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String sendOrder(String order, String name){
        System.out.println("I'll ask someone to get that for you "+ name);
        URI uri = URI.create("http://localhost:8080/orderUp/");
        OrderMessageRequest request = new OrderMessageRequest();
        request.orderMessage = order;
        request.orderName = name;
        restTemplate.put(uri, request);
        System.out.println("All good - the order has been sent");
        return "All Good";
    }


}
