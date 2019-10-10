package hello.orderSorter;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component(value = "sortCakeOrder")
public class SortCakeOrderService implements JavaDelegate {

    long waitTimeMillis = 1000;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        String message = (String)execution.getVariable("message");

        if(message.toLowerCase().contains("cake")){
            int loops = 3;
            System.out.println("We're Working on getting you Cake! -- "+ message );
            while(loops >= 0) {
                System.out.print("..");
                Thread.sleep(waitTimeMillis);
                loops--;
            }
            System.out.println();
            System.out.println("Cake is up -- "+ message );
        }

        System.out.println("All good - order is complete");
    }

}

