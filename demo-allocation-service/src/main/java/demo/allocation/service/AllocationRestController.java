package demo.allocation.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.protobuf.Descriptors.FieldDescriptor;

@RestController
public class AllocationRestController {

	@Autowired
	private AllocationGrpcClientImpl allocationGrpcClientImpl;

	@GetMapping("/")
	public String echo() {
		return "Allocation Service v1.0";
	}
	
	@GetMapping("/allocation/{employeeID}")
	public Map<FieldDescriptor, Object> getAllocationByEmployeeID(@PathVariable Long employeeID) {
		return allocationGrpcClientImpl.getAllocationDetails(employeeID);
	}
	
	@GetMapping("/allocation")
	public List<Map<FieldDescriptor, Object>> getFullEmployeeListByProjectID(@RequestParam(value = "projectID", required = true) Long projectID) throws InterruptedException {
		return allocationGrpcClientImpl.getEmployeeFullDetails(projectID);
	}

}
