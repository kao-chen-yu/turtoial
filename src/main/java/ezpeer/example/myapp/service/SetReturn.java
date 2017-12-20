package ezpeer.example.myapp.service;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import ezpeer.example.myapp.vo.ReturnModel;
import ezpeer.example.myapp.vo.ReturnValueModel;

@Service
public class SetReturn {
	
	
	public ReturnModel setReturnModel() {
		
		ReturnModel result = new ReturnModel();
		
		result.setReturnCode("0");
		result.setReturnErrorSolution(" ");
		result.setReturnMessage("Success");
		 Gson gson = new Gson();
		ReturnValueModel returnValue = setReturnValueModel();
		result.setReturnValue(gson.toJson(returnValue));
		return result;
	}
	
	public ReturnValueModel setReturnValueModel() {
		
		ReturnValueModel returnValue = new ReturnValueModel();
		
		returnValue.setReply("------架構中------");
		returnValue.setResultType("RESULT");
		return returnValue;
	}

}
