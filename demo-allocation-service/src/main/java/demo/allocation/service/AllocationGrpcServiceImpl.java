package demo.allocation.service;

import demo.interfaces.grpc.AllocationReply;
import demo.interfaces.grpc.AllocationRequestForGetAllocation;
import demo.interfaces.grpc.AllocationRequestForGetAllocationByEmp;
import demo.interfaces.grpc.AllocationServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class AllocationGrpcServiceImpl extends AllocationServiceGrpc.AllocationServiceImplBase {

	/* (non-Javadoc)
	 * @see demo.interfaces.grpc.AllocationServiceGrpc.AllocationServiceImplBase#getAllocation(demo.interfaces.grpc.AllocationRequestForGetAllocation, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void getAllocation(AllocationRequestForGetAllocation request, StreamObserver<AllocationReply> responseObserver) {

		AllocationReply response = AllocationReply.newBuilder()
									.setAllocationID(request.getAllocationID())
									.setEmployeeID(1)
									.setAllocationStartDate(1577817000000l)
									.setAllocationEndDate(1609353000000l)
									.build();
		responseObserver.onNext(response);
        responseObserver.onCompleted();
	}

	/* (non-Javadoc)
	 * @see demo.interfaces.grpc.AllocationServiceGrpc.AllocationServiceImplBase#getAllocationByEmployee(demo.interfaces.grpc.AllocationRequestForGetAllocationByEmp, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void getAllocationByEmployee(AllocationRequestForGetAllocationByEmp request, StreamObserver<AllocationReply> responseObserver) {
		AllocationReply response = AllocationReply.newBuilder()
				.setAllocationID(1)
				.setEmployeeID(request.getEmployeeID())
				.setAllocationStartDate(1577817000000l)
				.setAllocationEndDate(1609353000000l)
				.build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
	
	

}
