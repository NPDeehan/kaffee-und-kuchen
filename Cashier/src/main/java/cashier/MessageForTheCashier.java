package cashier;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageForTheCashier {
    @RequestMapping(value = "/messageForCashier", method = RequestMethod.PUT)
    public String message(@RequestBody OrderMessageRequest orderMessageRequest){

        System.out.println("We've got a pick-up of "+ orderMessageRequest.orderMessage + " for " + orderMessageRequest.orderName);

        return "success";

    }

}
