package demo.employee.service;

import demo.interfaces.grpc.EmployeeReply;
import demo.interfaces.grpc.EmployeeRequest;
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
	public void getEmployee(EmployeeRequest request, StreamObserver<EmployeeReply> responseObserver) {
		
              EmployeeReply  response = EmployeeReply.newBuilder()
            		  						.setEmployeeID(request.getEmployeeID())
            		  						.setEmployeeFirstName("Denuwan")
            		  						.setEmployeeLastName("Hettiarachchi")
            		  						.build();
              
              responseObserver.onNext(response);
              responseObserver.onCompleted();
	}

}
