package ezpeer.example.myapp.vo;

public class ReturnValueModel {
	
	private String reply;
	private String resultType;
	private ActionModel actions;
	
	public ActionModel getActions() {
		return actions;
	}
	public void setActions(ActionModel actions) {
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
