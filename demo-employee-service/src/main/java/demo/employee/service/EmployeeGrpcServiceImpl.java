package demo.employee.service;

import java.util.ArrayList;
import java.util.List;

import demo.interfaces.grpc.Employee;
import demo.interfaces.grpc.EmployeeServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class EmployeeGrpcServiceImpl extends EmployeeServiceGrpc.EmployeeServiceImplBase {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * demo.interfaces.grpc.EmployeeServiceGrpc.EmployeeServiceImplBase#getEmployee(
	 * demo.interfaces.grpc.EmployeeRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void getEmployee(Employee request, StreamObserver<Employee> responseObserver) {

		Employee response = Employee.newBuilder().setEmployeeID(request.getEmployeeID()).setEmployeeFirstName("Denuwan")
				.setEmployeeLastName("Hettiarachchi").build();

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	/* (non-Javadoc)
	 * @see demo.interfaces.grpc.EmployeeServiceGrpc.EmployeeServiceImplBase#getAllEmployeesByName(io.grpc.stub.StreamObserver)
	 */
	@Override
	public StreamObserver<Employee> getAllEmployeesByIDList(StreamObserver<Employee> responseObserver) {
		return new StreamObserver<Employee>() {
			
			List<Employee> responseList = new ArrayList<Employee>();
			
			@Override
			public void onNext(Employee value) {
				responseList.add(Employee.newBuilder()
							.setEmployeeID(value.getEmployeeID())
							.setEmployeeFirstName("First Name")
							.setEmployeeLastName("Last Name")
							.build());
			}
			
			@Override
			public void onError(Throwable t) {
				responseObserver.onError(t);				
			}
			
			@Override
			public void onCompleted() {
				responseList.stream().forEach(responseObserver::onNext);
				responseObserver.onCompleted();
			}
		};
	}
	
}
