package demo.allocation.service;

import java.util.stream.Collectors;

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

	/*
	 * Server side implementation of Server streaming RPCs where the
	 * client sends a request to the server and gets a stream to read a sequence of
	 * messages back. The client reads from the returned stream until there are no
	 * more messages. gRPC guarantees message ordering within an individual RPC
	 * call.
	 * 
	 * @see demo.interfaces.grpc.AllocationServiceGrpc.AllocationServiceImplBase#
	 * getAllocationByEmployee(demo.interfaces.grpc.
	 * AllocationRequestForGetAllocationByEmp, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void getAllocationByEmployee(Allocation request, StreamObserver<Allocation> responseObserver) {
		
		AllocationResourceProvider.getAllocationfromAllocationSource().stream()
				.filter(alloc -> alloc.getEmployeeID() == request.getEmployeeID())
				.forEach(responseObserver::onNext);

		responseObserver.onCompleted();
	}	

}
