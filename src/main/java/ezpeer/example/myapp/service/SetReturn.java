package ezpeer.example.myapp.service;

import org.springframework.stereotype.Service;

import ezpeer.example.myapp.vo.ReturnModel;
import ezpeer.example.myapp.vo.ReturnValueModel;

@Service
public class SetReturn {
	
	
	public ReturnModel setReturnModel() {
		
		ReturnModel result = new ReturnModel();
		
		result.setReturnCode("0");
		result.setReturnErrorSolution(" ");
		result.setReturnMessage("Success");
		
		ReturnValueModel returnValue = setReturnValueModel();
		result.setReturnValue(returnValue.toString());
		return result;
	}
	
	public ReturnValueModel setReturnValueModel() {
		
		ReturnValueModel returnValue = new ReturnValueModel();
		
		returnValue.setReply("------架構中------");
		returnValue.setResultType("RESULT");
		return returnValue;
	}

}