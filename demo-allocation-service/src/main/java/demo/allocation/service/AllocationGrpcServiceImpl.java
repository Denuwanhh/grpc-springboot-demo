package demo.allocation.service;

import demo.interfaces.grpc.Allocation;
import demo.interfaces.grpc.AllocationServiceGrpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class AllocationGrpcServiceImpl extends AllocationServiceGrpc.AllocationServiceImplBase {

	/* (non-Javadoc)
	 * @see demo.interfaces.grpc.AllocationServiceGrpc.AllocationServiceImplBase#getAllocation(demo.interfaces.grpc.AllocationRequestForGetAllocation, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void getAllocation(Allocation request, StreamObserver<Allocation> responseObserver) {

		Allocation response = Allocation.newBuilder()
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
	public void getAllocationByEmployee(Allocation request, StreamObserver<Allocation> responseObserver) {
		
		for(int i=0; i < 10; i++) {
			
			Allocation response = Allocation.newBuilder()
					.setAllocationID(i)
					.setEmployeeID(request.getEmployeeID())
					.setAllocationStartDate(1577817000000l)
					.setAllocationEndDate(1609353000000l)
					.build();
			
			responseObserver.onNext(response);	
		}
		
		responseObserver.onCompleted();
	}	

}
