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
	 * Server side implementation of unary RPCs where the client sends a single request
	 * to the server and gets a single response back, just like a normal function
	 * call.
	 * 
	 * @see
	 * demo.interfaces.grpc.EmployeeServiceGrpc.EmployeeServiceImplBase#getEmployee(
	 * demo.interfaces.grpc.EmployeeRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void getEmployee(Employee request, StreamObserver<Employee> responseObserver) {
		responseObserver.onNext(EmployeeResourceProvider.getEmployeeListfromEmployeeSource()
													.stream()
													.filter(emp -> emp.getEmployeeID() == request.getEmployeeID())
													.findFirst()
													.get());
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

	/*
	 * Server side implementation of Client streaming RPCs where the client writes a
	 * sequence of messages and sends them to the server, again using a provided
	 * stream. Once the client has finished writing the messages, it waits for the
	 * server to read them and return its response. Again gRPC guarantees message
	 * ordering within an individual RPC call.
	 * 
	 * @see demo.interfaces.grpc.EmployeeServiceGrpc.EmployeeServiceImplBase#
	 * getMostExperiencedEmployee(io.grpc.stub.StreamObserver)
	 */
	@Override
	public StreamObserver<Employee> getMostExperiencedEmployee(StreamObserver<Employee> responseObserver) {
		return new StreamObserver<Employee>() {

			Employee response = null;

			@Override
			public void onNext(Employee value) {
				
				Employee currentEmployee = EmployeeResourceProvider.getEmployeeListfromEmployeeSource()
											.stream()
											.filter(emp -> emp.getEmployeeID() == value.getEmployeeID())
											.findFirst()
											.get();
																						
				if(response == null || currentEmployee.getEmployeeWorkingYears() > response.getEmployeeWorkingYears()) {
					response = currentEmployee;
				}
			}

			@Override
			public void onError(Throwable t) {
				responseObserver.onError(t);
			}

			@Override
			public void onCompleted() {
				responseObserver.onNext(response);
				responseObserver.onCompleted();
			}
		};
	}	
	
}
