# Kaffee und Kuchen
This is an example of how to decouple dependent micro-services by adding the Camunda Engine as part of the architecture. The project can be seen being demoed at this talk, which took place at Devoxx Belgium 2019.

[![Devoxx Belgium](http://img.youtube.com/vi/ky_mG4giNm4/0.jpg)](http://www.youtube.com/watch?v=ky_mG4giNm4)

There are 3 services a ``cashier`` frontend where you can make orders to an ``order sorter`` the order sorter will then request coffee from the ``barista`` service if required.

![knkarch]

There are two versions of the architecture
 * Synchronous. All communication between services is completed in one transaction per request.
 * Asynchronous. Each request is persisted and completed in a new transaction while waiting for the potential of additional  requests.

## Synchronous Architecture
To start the Synchronous version of this example open and build the following 3 Spring Boot projects
* [Cashier](./Cashier/) - Which will start on http://localhost:8082
* [Barista](./Barista/) - Which will start on http://localhost:8081
* [Sort Order Sync](./SortOrder-Sync/) - Which will start on http://localhost:8080

Each of these can be started within your IDE as a Spring Boot project. You can also build them and start the jar file in the generated target folder.

Once each is started you can enter orders by going to the [Cashier Homepage](http://localhost:8082).
![knkorder1]

When the submit button is clicked it will send a REST call ``http://localhost:8080/order-up`` to the Order-Sorter service with a payload that includes the order and the name of the person making the order.
If the order contains ``coffee`` then it will call the barista service on `http://localhost:8081/WorkIt/`. Once the coffee is ready it returns to the order sorter, which will return the result to the frontend, which will display ``Order of [orderMessage] Is Ready``

## Asynchronous Architecture
This architecture only requires a change to the ``Order Sorter`` the goal is to change this service so that instead of running synchronously for each request, it will actually hold the state of each request and return the result of the request with an asynchronous call.

![kuk-async-arch]

To start this version of the project you need to start (if you haven't already):
* [Cashier](./Cashier/) - Which will start on http://localhost:8082
* [Barista](./Barista/) - Which will start on http://localhost:8081

You will need to shut down [Sort Order Sync](./SortOrder-Sync/) and instead start up [Sort Order aSync](./SortOrder-aSync/). Both use the same port (8080) so you can't run them at the same time.

Once an order is made the state will be kept by the Camunda Engine and you can view the current state by going to Cockpit on ``http://localhost:8080/`` you can log in with:

username `reb`

password `reb`

Navigate to Cockpit or go directly through this link http://localhost:8080/app/cockpit/default/


There are a number of changes made to Sort Order project to give us the features we need.
* Camunda Engine is added to the project
* Callback service is created and called
* Process Model is created and added
* Camunda Engine is autowired in the service and a process is started
* The ``application.yaml`` has the Camunda configuration added to it.


### Camunda Engine is added to the project

The Camunda Engine and the Camunda webapps are added to the project by adding the following dependencies to the ``pom.xml`` file.

```xml

        <dependency>
            <groupId>org.camunda.bpm.springboot</groupId>
            <artifactId>camunda-bpm-spring-boot-starter-rest</artifactId>
            <version>3.3.1</version>
        </dependency>
        <dependency>
            <groupId>org.camunda.bpm.springboot</groupId>
            <artifactId>camunda-bpm-spring-boot-starter-webapp</artifactId>
            <version>3.3.1</version>
        </dependency>

```

### Callback service is created and called

A callback bean called ``tellCashierAboutOrder`` is added to the project so that we can asynchronously contact the front end of the cashier service with the result of the request.

### Process Model is created and added

The process engine needs this [BPMN model](./SortOrder-aSync/src/main/resources/howAreYou.bpmn) to describe how the process should proceed, what should be called and where to commit transactions. This is added to the ``resources`` folder.

![async-process]

### Camunda Engine is autowired in the service and a process is started
The Engine's RuntimeService is added to the ``ServiceController`` class

```java
@Autowired
private RuntimeService runtimeService;
```

and the process model is called directly when the REST request comes in.

```java
runtimeService.startProcessInstanceByKey("ProcessOrder", businessKey, vars);
```

### Camunda configuration added

To log into the webapps a user needs to be defined. This is added to the ``application.yaml`` file

```yaml
camunda:
  bpm:
    admin-user:
      id: reb
      password: reb
      firstName: Reb
```


[knkarch]: ./images/kuk-arch.png "the architecture of the micro services"
[knkorder1]: ./images/kuk-order1.png "Cashier front end"
[kuk-async-arch]: ./images/kuk-async-arch.png "Architecture with the Camunda engine"
[async-process]: ./images/asyncProcess.png "Order Sorter Process Model"
