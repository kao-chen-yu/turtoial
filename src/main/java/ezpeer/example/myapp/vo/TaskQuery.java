package ezpeer.example.myapp.vo;

import java.util.List;
import java.util.Map;

public class TaskQuery {
	
	private String sessionId;
	private Long botId;
	private String utterance;
	private Long skillId;
	private String skillName;
	private String intentName;
	private Map<String,String> requestData;
	private List<SlotEntity> slotEntities;
	private List<ConversationRecord> conversationRecords;
	private Map<String,SessionEntry> sessionEntries;
	private Long domainId;
	private Long intentId;
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Long getBotId() {
		return botId;
	}
	public void setBotId(Long botId) {
		this.botId = botId;
	}
	public String getUtterance() {
		return utterance;
	}
	public void setUtterance(String utterance) {
		this.utterance = utterance;
	}
	public Long getSkillId() {
		return skillId;
	}
	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public String getIntentName() {
		return intentName;
	}
	public void setIntentName(String intentName) {
		this.intentName = intentName;
	}
	public Map<String, String> getRequestData() {
		return requestData;
	}
	public void setRequestData(Map<String, String> requestData) {
		this.requestData = requestData;
	}
	public List<SlotEntity> getSlotEntities() {
		return slotEntities;
	}
	public void setSlotEntities(List<SlotEntity> slotEntities) {
		this.slotEntities = slotEntities;
	}
	public List<ConversationRecord> getConversationRecords() {
		return conversationRecords;
	}
	public void setConversationRecords(List<ConversationRecord> conversationRecords) {
		this.conversationRecords = conversationRecords;
	}
	public Map<String, SessionEntry> getSessionEntries() {
		return sessionEntries;
	}
	public void setSessionEntries(Map<String, SessionEntry> sessionEntries) {
		this.sessionEntries = sessionEntries;
	}
	public Long getDomainId() {
		return domainId;
	}
	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}
	public Long getIntentId() {
		return intentId;
	}
	public void setIntentId(Long intentId) {
		this.intentId = intentId;
	}
	
	
	

}
