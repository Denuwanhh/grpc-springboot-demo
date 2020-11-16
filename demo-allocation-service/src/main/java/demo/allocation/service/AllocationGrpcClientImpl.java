package demo.allocation.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.protobuf.Descriptors.FieldDescriptor;

import demo.interfaces.grpc.Employee;
import demo.interfaces.grpc.EmployeeServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;

@Service
public class AllocationGrpcClientImpl {
	
	@GrpcClient("employee-service")
    private EmployeeServiceGrpc.EmployeeServiceBlockingStub employeeServiceBlockingStub;

	public Map<FieldDescriptor, Object> getAllocationDetails(long employeeID) {

		Employee employeeRequest = Employee.newBuilder().setEmployeeID(employeeID).build();
		
		Employee employeeReply = employeeServiceBlockingStub.getEmployee(employeeRequest);

		return employeeReply.getAllFields();
	}

}
