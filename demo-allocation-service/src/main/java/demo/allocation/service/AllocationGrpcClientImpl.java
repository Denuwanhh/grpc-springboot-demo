package demo.allocation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.protobuf.Descriptors.FieldDescriptor;

import demo.interfaces.grpc.Allocation;
import demo.interfaces.grpc.Employee;
import demo.interfaces.grpc.EmployeeServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;

@Service
public class AllocationGrpcClientImpl {

	@GrpcClient("employee-service")
	private EmployeeServiceGrpc.EmployeeServiceBlockingStub employeeServiceBlockingStub;

	@GrpcClient("employee-service")
	private EmployeeServiceGrpc.EmployeeServiceStub employeeServiceStub;

	public Map<FieldDescriptor, Object> getAllocationDetails(long employeeID) {

		Employee employeeRequest = Employee.newBuilder().setEmployeeID(employeeID).build();

		Employee employeeReply = employeeServiceBlockingStub.getEmployee(employeeRequest);

		return employeeReply.getAllFields();
	}

	public List<Map<FieldDescriptor, Object>> getEmployeeFullDetails(long projectID) {
		List<Employee> employeeIDList = new ArrayList<Employee>() {
			{
				add(Employee.newBuilder().setEmployeeID(1l).build());
				add(Employee.newBuilder().setEmployeeID(2l).build());
				add(Employee.newBuilder().setEmployeeID(3l).build());
				add(Employee.newBuilder().setEmployeeID(4l).build());
				add(Employee.newBuilder().setEmployeeID(5l).build());
				add(Employee.newBuilder().setEmployeeID(6l).build());
				add(Employee.newBuilder().setEmployeeID(7l).build());
				add(Employee.newBuilder().setEmployeeID(8l).build());
				add(Employee.newBuilder().setEmployeeID(9l).build());
				add(Employee.newBuilder().setEmployeeID(10l).build());
			}
		};
		List<Map<FieldDescriptor, Object>> employeeDetailsList = new ArrayList<Map<FieldDescriptor, Object>>();

		StreamObserver<Employee> collect = employeeServiceStub.getAllEmployeesByIDList(new StreamObserver<Employee>() {

			@Override
			public void onNext(Employee value) {
				System.out.println("Employee: " + value.getEmployeeFirstName());
				System.out.println("Employee: " + value.getEmployeeLastName());
				System.out.println("Employee: " + value.getEmployeeID());
				
				employeeDetailsList.add(value.getAllFields());
			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
				System.out.println("Error" + t);

			}

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				System.out.println("Compleate");
			}

		});

		employeeIDList.stream().forEach(collect::onNext);

		collect.onCompleted();

		return employeeDetailsList;
	}

}
