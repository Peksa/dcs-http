package models;

public class DcsObject
{
	public long id;
	public double time;
	public String name;
	public double lat;
	public double lon;
	public double heading;
	public String country;
	public String type;
	public String groupName;
	@Override
	public String toString()
	{
		return "DcsObject [id=" + id + ", time=" + time + ", name=" + name
				+ ", lat=" + lat + ", lon=" + lon + ", heading=" + heading
				+ ", country=" + country + ", type=" + type + ", groupName="
				+ groupName + "]";
	}
	
	
}
