package demo.employee.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.protobuf.Descriptors.FieldDescriptor;

import demo.interfaces.grpc.AllocationReply;
import demo.interfaces.grpc.AllocationRequestForGetAllocation;
import demo.interfaces.grpc.AllocationServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;

@Service
public class EmployeeGrpcClient {

	@GrpcClient("allocation-service")
	private AllocationServiceGrpc.AllocationServiceBlockingStub allocationServiceBlockingStub;
	
	public Map<FieldDescriptor, Object> getAllocationByAllocationID(long allocationID) {
		
		AllocationRequestForGetAllocation allocationRequestForGetAllocation = AllocationRequestForGetAllocation.newBuilder()
																					.setAllocationID(allocationID)
																					.build();
		
		AllocationReply allocationReply = allocationServiceBlockingStub.getAllocation(allocationRequestForGetAllocation);
		
		return allocationReply.getAllFields();
		
	}
	
}
