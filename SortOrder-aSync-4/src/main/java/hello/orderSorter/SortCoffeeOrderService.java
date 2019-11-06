package hello.orderSorter;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Random;

@Component(value = "sortCoffeeOrder")
public class SortCoffeeOrderService implements JavaDelegate {
    private final RestTemplate restTemplate;

    public SortCoffeeOrderService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String message = (String) execution.getVariable("message");
        Random rando = new Random();

        if(message.contains("soya") && rando.nextBoolean()){
            Thread.sleep(10000);
            throw new BpmnError("NoSoya");
        } else {


            if (message.toLowerCase().contains("coffee")) {
                URI uri = URI.create("http://localhost:8081/WorkIt/");
                OrderMessageRequest request = new OrderMessageRequest();
                request.orderMessage = message;
                restTemplate.put(uri, request);
                System.out.println("I've sent an order for coffee");
            }
        }
    }
}

