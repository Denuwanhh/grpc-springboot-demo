package demo.allocation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.interfaces.grpc.EmployeeReply;

//import demo.interfaces.grpc.EmployeeReply;

@RestController
public class AllocationRestController {

	@Autowired
	private AllocationGrpcClientImpl allocationGrpcClientImpl;

	@RequestMapping("/")
	public String echo() {
		return "Allocation Service v1.0";
	}
	
	@RequestMapping(path="/allocation/{employeeID}")
	public String getAllocationByEmployeeID(@PathVariable Long employeeID) {
		return allocationGrpcClientImpl.getAllocationDetails(employeeID);
	}

}
