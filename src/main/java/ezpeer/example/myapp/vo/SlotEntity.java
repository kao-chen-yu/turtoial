package ezpeer.example.myapp.vo;

public class SlotEntity {

	private Long intentParameterId;
	private String intentParameterName;
	private String originalValue;
	private String standardValue;
	private Integer liveTime;
	private Long createTimeStamp;
	public Long getIntentParameterId() {
		return intentParameterId;
	}
	public void setIntentParameterId(Long intentParameterId) {
		this.intentParameterId = intentParameterId;
	}
	public String getIntentParameterName() {
		return intentParameterName;
	}
	public void setIntentParameterName(String intentParameterName) {
		this.intentParameterName = intentParameterName;
	}
	public String getOriginalValue() {
		return originalValue;
	}
	public void setOriginalValue(String originalValue) {
		this.originalValue = originalValue;
	}
	public String getStandardValue() {
		return standardValue;
	}
	public void setStandardValue(String standardValue) {
		this.standardValue = standardValue;
	}
	public Integer getLiveTime() {
		return liveTime;
	}
	public void setLiveTime(Integer liveTime) {
		this.liveTime = liveTime;
	}
	public Long getCreateTimeStamp() {
		return createTimeStamp;
	}
	public void setCreateTimeStamp(Long createTimeStamp) {
		this.createTimeStamp = createTimeStamp;
	}
	
	
}
