package demo.allocation.service;

import java.util.ArrayList;
import java.util.List;

import demo.interfaces.grpc.Allocation;

public class AllocationResourceProvider {
	/**
	 * In order to simplify the solution, use this static list as source of
	 * Allocation
	 * 
	 * @return Allocation List
	 */
	public static List<Allocation> getAllocationfromAllocationSource() {
		return new ArrayList<Allocation>() {
			{
				add(Allocation.newBuilder().setAllocationID(1l).setEmployeeID(1l).setAllocationStartDate(1577817000000l)
						.setAllocationEndDate(1609353000000l).setProjectID(1l).build());
				add(Allocation.newBuilder().setAllocationID(2l).setEmployeeID(2l).setAllocationStartDate(1577817000000l)
						.setAllocationEndDate(1609353000000l).setProjectID(1l).build());
				add(Allocation.newBuilder().setAllocationID(3l).setEmployeeID(3l).setAllocationStartDate(1577817000000l)
						.setAllocationEndDate(1609353000000l).setProjectID(1l).build());
				add(Allocation.newBuilder().setAllocationID(4l).setEmployeeID(1l).setAllocationStartDate(1577817000000l)
						.setAllocationEndDate(1609353000000l).setProjectID(2l).build());
			
			}
		};
	}
}
