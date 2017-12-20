package ezpeer.example.myapp.vo;

public class ConversationRecord {

	private String botId;
	private String userInputUtterance;
	private String replyUtterance;
	private String domainId;
	private String intentId;
	private String intentName;
	private String timestamp;
	private String resultType;
	private String slotEntities;
	
	public String getBotId() {
		return botId;
	}
	public void setBotId(String botId) {
		this.botId = botId;
	}
	public String getUserInputUtterance() {
		return userInputUtterance;
	}
	public void setUserInputUtterance(String userInputUtterance) {
		this.userInputUtterance = userInputUtterance;
	}
	public String getReplyUtterance() {
		return replyUtterance;
	}
	public void setReplyUtterance(String replyUtterance) {
		this.replyUtterance = replyUtterance;
	}
	public String getDomainId() {
		return domainId;
	}
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	public String getIntentId() {
		return intentId;
	}
	public void setIntentId(String intentId) {
		this.intentId = intentId;
	}
	public String getIntentName() {
		return intentName;
	}
	public void setIntentName(String intentName) {
		this.intentName = intentName;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getResultType() {
		return resultType;
	}
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	public String getSlotEntities() {
		return slotEntities;
	}
	public void setSlotEntities(String slotEntities) {
		this.slotEntities = slotEntities;
	}
	
	
}
