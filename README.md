# Kaffee und Kuchen
This is an example of how to decouple dependent micro-services by adding the Camunda Engine as part of the architecture. The project can be seeing being demoed at this talk which took place at Devoxx Belgium 2019.

[![Devoxx Belgium](http://img.youtube.com/vi/ky_mG4giNm4/0.jpg)](http://www.youtube.com/watch?v=ky_mG4giNm4)

There are 3 services a ``cashier`` frontend where you can make orders to an ``order sorter`` the order sorter will then request coffee from the ``barista`` service if required.

![knkarch]

There are two versions of the architecture
 * Synchronous. All communication between services is completed in one transaction per request
 * Asynchronous. Each request is persisted and is completed in a new transaction while waiting for the potential of additional  requests.

## Synchronous Architecture
To start the Synchronous version of this example open and build the following 3 spring boot projects
* [Cashier](../Cashier/) - Which will start on http://localhost:8082
* [Barista](../Barista/) - Which will start on http://localhost:8081
* [Sort Order Sync](../SortOrder-Sync/) - Which will start on http://localhost:8080

Each of these can started within your IDE as a spring boot project. You can also build them and start the jar file in the generated target folder.

Once each are started you can enter orders by going to the [Cashier Homepage](http://localhost:8082).
![knkorder1]

when then submit button is clicked it will send a REST call ``http://localhost:8080/order-up`` to the Order-Sorter service with a payload that includes the order and the name of the person making the order

## Asynchronous Architecture
![kuk-async-arch]

[knkarch]: ./images/kuk-arch.png "the architecture of the micro services"
[knkorder1]: ./images/kuk-order1.png "Cashier front end"
[kuk-async-arch]: ./images/kuk-async-arch.png "Architecture with the Camunda engine"
