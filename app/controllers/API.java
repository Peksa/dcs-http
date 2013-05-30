package controllers;

import play.mvc.Controller;
import play.mvc.Util;
import util.DcsSocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import db.DB;

public class API extends Controller
{
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    public static void allObjects()
    {
    	prettyJSON(DB.getObjects());
    }
    
    public static void activeObjects()
    {
    	prettyJSON(DB.getActiveObjects());
    }
    
    public static void players()
    {
    	prettyJSON(DB.getPlayers());
    }
    
    @Util
	private static void prettyJSON(Object json)
	{
		renderJSON(gson.toJson(json));
	}
}