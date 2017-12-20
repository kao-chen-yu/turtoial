package ezpeer.example.myapp.vo;

public class ReturnModel {
	
	private String returnCode;
	private String returnErrorSolution;
	private String returnMessage;
	private ReturnValueModel returnValue;
	
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnErrorSolution() {
		return returnErrorSolution;
	}
	public void setReturnErrorSolution(String returnErrorSolution) {
		this.returnErrorSolution = returnErrorSolution;
	}
	public String getReturnMessage() {
		return returnMessage;
	}
	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}
	public ReturnValueModel getReturnValue() {
		return returnValue;
	}
	public void setReturnValue(ReturnValueModel returnValue) {
		this.returnValue = returnValue;
	}
	

}
