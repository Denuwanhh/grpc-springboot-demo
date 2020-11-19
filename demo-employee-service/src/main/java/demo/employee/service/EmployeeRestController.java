package demo.employee.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.protobuf.Descriptors.FieldDescriptor;

@RestController
public class EmployeeRestController {
	
	@Autowired
	private EmployeeGrpcClient employeeGrpcClient;
	
	@RequestMapping("/")
	public String echo() {
		return "Employee Service v1.0";
	}
	
	@RequestMapping(path = "/employee/{employeeID}/allocation")
	public List<Map<FieldDescriptor, Object>> getAllocationByEmployeeID(@PathVariable Long employeeID, @RequestParam(value = "isSyncClient", required = false, defaultValue = "Y") String isSyncClient) throws InterruptedException {
		
		return isSyncClient.equals("Y") ? employeeGrpcClient.getAllocationByEmployeeIDViaSynchronousClient(employeeID)
				: employeeGrpcClient.getAllocationByEmployeeIDViaAsynchronousClient(employeeID);
	}
	
}
