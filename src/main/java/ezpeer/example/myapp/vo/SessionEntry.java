package ezpeer.example.myapp.vo;

public class SessionEntry {

	private Integer timeToLive;
	private Integer liveTime;
	private Long timeStamp;
	private String value;
	public Integer getTimeToLive() {
		return timeToLive;
	}
	public void setTimeToLive(Integer timeToLive) {
		this.timeToLive = timeToLive;
	}
	public Integer getLiveTime() {
		return liveTime;
	}
	public void setLiveTime(Integer liveTime) {
		this.liveTime = liveTime;
	}
	public Long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
