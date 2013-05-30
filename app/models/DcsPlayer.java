package models;

public class DcsPlayer extends DcsEntity
{
	String name;
	
	public DcsPlayer()
	{
		this.type = "P";
	}
	
	public static DcsPlayer parse(String input)
	{
		try {
			String[] tokens = input.split("\t");
			DcsPlayer ret = new DcsPlayer();
			ret.name = tokens[1];
			return ret;
		} catch (Exception e) { 
			return null;
		}
	}
}
