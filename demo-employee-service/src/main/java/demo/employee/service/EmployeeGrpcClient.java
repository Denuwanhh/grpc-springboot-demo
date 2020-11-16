package demo.employee.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.google.protobuf.Descriptors.FieldDescriptor;

import demo.interfaces.grpc.Allocation;
import demo.interfaces.grpc.AllocationServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;

@Service
public class EmployeeGrpcClient {

	@GrpcClient("allocation-service")
	private AllocationServiceGrpc.AllocationServiceBlockingStub allocationServiceBlockingStub;
	
	public Map<FieldDescriptor, Object> getAllocationByAllocationID(long allocationID) {
		
		Allocation allocationRequest = Allocation.newBuilder()
															.setAllocationID(allocationID)
															.build();
		
		Allocation allocationReply = allocationServiceBlockingStub.getAllocation(allocationRequest);
		
		return allocationReply.getAllFields();
		
	}
	
public List< Map<FieldDescriptor, Object> > getAllocationByEmployeeID(long employeeID) {
		
		Allocation allocationRequest = Allocation.newBuilder()
											.setEmployeeID(employeeID)
											.build();
		
		Iterator<Allocation> allocationReply = allocationServiceBlockingStub.getAllocationByEmployee(allocationRequest);

		Iterable<Allocation> iterable = () -> allocationReply;
		Stream<Allocation> allocationStream = StreamSupport.stream(iterable.spliterator(), false);
		
		return allocationStream
				.map(allocation -> allocation.getAllFields())
				.collect(Collectors.toList());
		
	}
}
