package hello;

import hello.orderSorter.OrderMessageRequest;
import hello.orderSorter.SortCakeOrderService;
import hello.orderSorter.SortCoffeeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ServiceController {

    @Autowired
    SortCakeOrderService sortCakeOrder;

    @Autowired
    SortCoffeeOrderService sortCoffeeOrder;

    @RequestMapping(value = "/orderUp", method = RequestMethod.PUT)
    public String index(@RequestBody OrderMessageRequest orderMessageRequest) throws Exception {

        if(orderMessageRequest.orderMessage.toLowerCase().contains("cake")){
            sortCakeOrder.execute();
        }
        if(orderMessageRequest.orderMessage.toLowerCase().contains("coffee")){
            sortCoffeeOrder.execute();
        }





        return "Order is Taken Care of";
    }
}
