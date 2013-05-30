package models;

public class DcsPlayer extends DcsEntity
{
	String name;
	
	public DcsPlayer()
	{
		this.entity = "P";
	}
	
	public static DcsPlayer parse(String input)
	{
		try {
			String[] tokens = input.split("\t");
			DcsPlayer ret = new DcsPlayer();
			ret.time = Double.parseDouble(tokens[1]);
			ret.name = tokens[2];
			return ret;
		} catch (Exception e) { 
			return null;
		}
	}
}
