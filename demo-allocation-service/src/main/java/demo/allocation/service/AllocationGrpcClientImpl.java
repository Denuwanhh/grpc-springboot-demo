package demo.allocation.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.protobuf.Descriptors.FieldDescriptor;

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

	/**
	 * Client side implementation of unary RPCs where the client sends a single request
	 * to the server and gets a single response back, just like a normal function
	 * call.
	 * 
	 * @param allocationID
	 * @return Employee Object Map<FieldDescriptor, Object>
	 */
	public Map<FieldDescriptor, Object> getEmployeeDetailsByAllocationID(long allocationID) {

		return employeeServiceBlockingStub
					.getEmployee(Employee.newBuilder()
											.setEmployeeID(AllocationResourceProvider
															.getAllocationfromAllocationSource()
															.stream()
															.filter(alloc -> alloc.getAllocationID() == allocationID)
															.findFirst()
															.get()
															.getEmployeeID())
											.build())
					.getAllFields();
	}

	public List<Map<FieldDescriptor, Object>> getEmployeeFullDetails(long projectID) throws InterruptedException {

		final CountDownLatch finishLatch = new CountDownLatch(1);
		List<Employee> employeeIDList = new ArrayList<Employee>();
		List<Map<FieldDescriptor, Object>> employeeDetailsFinalList = new ArrayList<Map<FieldDescriptor, Object>>();

		employeeIDList.add(Employee.newBuilder().setEmployeeID(1l).build());
		employeeIDList.add(Employee.newBuilder().setEmployeeID(2l).build());
		employeeIDList.add(Employee.newBuilder().setEmployeeID(3l).build());
		employeeIDList.add(Employee.newBuilder().setEmployeeID(4l).build());
		employeeIDList.add(Employee.newBuilder().setEmployeeID(5l).build());
		employeeIDList.add(Employee.newBuilder().setEmployeeID(6l).build());
		employeeIDList.add(Employee.newBuilder().setEmployeeID(7l).build());
		employeeIDList.add(Employee.newBuilder().setEmployeeID(8l).build());
		employeeIDList.add(Employee.newBuilder().setEmployeeID(9l).build());
		employeeIDList.add(Employee.newBuilder().setEmployeeID(10l).build());

		StreamObserver<Employee> responseObserver = employeeServiceStub
				.getAllEmployeesByIDList(new StreamObserver<Employee>() {
					@Override
					public void onNext(Employee value) {
						employeeDetailsFinalList.add(value.getAllFields());
					}

					@Override
					public void onError(Throwable t) {
						finishLatch.countDown();
					}

					@Override
					public void onCompleted() {
						finishLatch.countDown();
					}

				});

		employeeIDList.stream().forEach(responseObserver::onNext);

		responseObserver.onCompleted();

		finishLatch.await(1, TimeUnit.MINUTES);

		return employeeDetailsFinalList;
	}

	/**
	 * Client side implementation of Client streaming RPCs where the client writes a
	 * sequence of messages and sends them to the server, again using a provided
	 * stream. Once the client has finished writing the messages, it waits for the
	 * server to read them and return its response. Again gRPC guarantees message
	 * ordering within an individual RPC call.
	 * @param projectID
	 * @return
	 * @throws InterruptedException
	 */
	public Map<String, Map<FieldDescriptor, Object>> getMostExperiencedEmployeeInProject(long projectID) throws InterruptedException {

		final CountDownLatch finishLatch = new CountDownLatch(1);
		Map<String, Map<FieldDescriptor, Object>> responce = new HashMap<String, Map<FieldDescriptor, Object>>();
		

		StreamObserver<Employee> responseObserver = employeeServiceStub
				.getMostExperiencedEmployee(new StreamObserver<Employee>() {
								
					@Override
					public void onNext(Employee value) {
						responce.put("Employee", value.getAllFields());
					}

					@Override
					public void onError(Throwable t) {
						finishLatch.countDown();
					}

					@Override
					public void onCompleted() {
						finishLatch.countDown();
					}

				});

		AllocationResourceProvider
			.getAllocationfromAllocationSource()
			.stream()
			.filter(alloc -> alloc.getProjectID() == projectID)
			.forEach(alloc -> {
					responseObserver.onNext(Employee.newBuilder().setEmployeeID(alloc.getEmployeeID()).build());
			});

		responseObserver.onCompleted();

		finishLatch.await(1, TimeUnit.MINUTES);

		return responce;
	}

}
