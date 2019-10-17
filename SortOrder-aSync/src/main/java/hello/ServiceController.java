package hello;

import hello.orderSorter.OrderMessageRequest;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
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
    private RuntimeService runtimeService;


    @RequestMapping(value = "/orderUp", method = RequestMethod.POST)
    public String index(@RequestBody OrderMessageRequest orderMessageRequest){

        System.out.println("Got this message for Mike: " + orderMessageRequest.orderMessage);
        Map<String, Object> vars = new HashMap<String, Object>();

        vars.put("message", orderMessageRequest.orderMessage);
        String businessKey = orderMessageRequest.orderName;

        runtimeService.startProcessInstanceByKey("ProcessOrder", businessKey, vars);

        return "Your Order is being Taken Care of";
    }
}
