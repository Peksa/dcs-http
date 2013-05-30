package models;

public class DcsObject extends DcsEntity
{
	public long id;
	public double time;
	public String name;
	public double lat;
	public double lon;
	public double heading;
	public double alt;
	public String country;
	public String type;
	public String groupName;
	
	public DcsObject()
	{
		this.type = "O";
	}
	
	
	@Override
	public String toString()
	{
		return "DcsObject [id=" + id + ", time=" + time + ", name=" + name
				+ ", lat=" + lat + ", lon=" + lon + ", heading=" + heading
				+ ", alt=" + alt + ", country=" + country + ", type="
				+ type + ", groupName=" + groupName + "]";
	}
	
	public static DcsObject parse(String input)
	{
		try {
			String[] tokens = input.split("\t");
			DcsObject ret = new DcsObject();
			ret.time = Double.parseDouble(tokens[1]);
			ret.id = Long.parseLong(tokens[2]);
			ret.type = tokens[3];
			ret.lat = Double.parseDouble(tokens[4]);
			ret.lon = Double.parseDouble(tokens[5]);
			ret.alt = Double.parseDouble(tokens[6]);
			ret.heading = Double.parseDouble(tokens[7]);
			ret.country = tokens[8];
			ret.name = tokens[9];
			ret.groupName = tokens[10];
			return ret;
		} catch (Exception e) { 
			// Skip any unparsable objects
			return null;
		}
	}
}
