# gRPC - Spring boot Implementation

In this implementation, We build a spring boot microservice solution which contains a Netflix-Eureka discovery server and two microservice in Employee & Allocation domains. And the interface project contains the raw protocol-buffer files and using the protocol buffer compiler maven plugin generates the java model and service classes.

![High-level Architecture Diagram](https://miro.medium.com/max/1250/1*u5TYUXnCdoQj7td5ohci4A.jpeg)

## How to Run
1. Get a clone  [Denuwanhh](https://github.com/Denuwanhh)/**[grpc-springboot-demo](https://github.com/Denuwanhh/grpc-springboot-demo)**
2. Navigate to the project folder and run<br/> 
`mvn package`
3. Run applications avialble in 
<br/>`grpc-springboot-demo\demo-eureka-server\target\demo-eureka-server-0.0.1-SNAPSHOT.jar`<br/>
`grpc-springboot-demo\demo-employee-service\target\demo-employee-service-0.0.1-SNAPSHOT.jar`<br/>
`grpc-springboot-demo\demo-allocation-service\target\demo-allocation-service-0.0.1-SNAPSHOT.jar`

## Service Methods

 - Unary RPCs<br/>
 Proto Definition:  `rpc getEmployee (Employee) returns (Employee) {
    }`<br/>
     End Point: `{IP Address}:{8082}/allocation/{allocationID}`
    
 - Server streaming RPCs<br/>
  Proto Definition: `rpc getAllocationByEmployee (Allocation) returns (stream Allocation) {
    }`<br/>
    End Point: `{IP Address}:{8089}/employee/{employeeID}/allocation`
    
 - Client streaming RPCs<br/>
 Proto Definition: `rpc getMostExperiencedEmployee (stream Employee) returns (Employee) {
    }`<br/>
    End Point: `{IP Address}:{8082}/{projectID}/allocation/getexperiencedemployeeinproject`
    
 - Bidirectional streaming RPCs<br/>
 Proto Definition: `rpc getAllEmployeesByIDList (stream Employee) returns (stream Employee) {
    }`<br/>
    End Point: `{IP Address}:{8082}/allocation?projectID={projectID}`

  
  
    
