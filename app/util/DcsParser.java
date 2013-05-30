package util;

import java.util.concurrent.ConcurrentHashMap;

import db.DB;

import models.DcsEntity;
import models.DcsObject;
import models.DcsPlayer;

public class DcsParser
{
	public static DcsEntity parse(String line)
	{
		String[] tokens = line.split("\t");
		
		switch (tokens[0])
		{
			// DCS World object. Airframe, tank etc.
			case "O":
				DcsObject object = DcsObject.parse(line);
				return object;
			// DCS Player
			case "P":
				DcsPlayer player = DcsPlayer.parse(line);
				return player;
		}
		return null;
	}
}
