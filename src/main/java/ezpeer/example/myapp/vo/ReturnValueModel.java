package ezpeer.example.myapp.vo;

import java.util.List;

public class ReturnValueModel {
	
	private String reply;
	private String resultType;
	private List<ActionModel> actions;
	
	
	public List<ActionModel> getActions() {
		return actions;
	}
	public void setActions(List<ActionModel> actions) {
		this.actions = actions;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getResultType() {
		return resultType;
	}
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	

}
