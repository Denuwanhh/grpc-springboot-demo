package demo.allocation.service;

import org.springframework.stereotype.Service;

import demo.interfaces.grpc.EmployeeReply;
import demo.interfaces.grpc.EmployeeRequest;
import demo.interfaces.grpc.EmployeeServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;

@Service
public class AllocationGrpcClientImpl {
	
	@GrpcClient("employee-service")
    private EmployeeServiceGrpc.EmployeeServiceBlockingStub employeeServiceBlockingStub;

	public String getEmployeeDetails(long employeeID) {

		EmployeeRequest employeeRequest = EmployeeRequest.newBuilder().setEmployeeID(employeeID).build();
		
		EmployeeReply employeeReply = employeeServiceBlockingStub.getEmployee(employeeRequest);

		return employeeReply.getEmployeeFirstName().concat(" ").concat(employeeReply.getEmployeeLastName());
	}

}
