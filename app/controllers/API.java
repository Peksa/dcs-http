package controllers;

import play.mvc.Controller;
import util.DcsSocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class API extends Controller
{
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    public static void allObjects()
    {
    	renderJSON(gson.toJson(DcsSocket.objects.values()));
    }
    
    public static void activeObjects()
    {
    	renderJSON(gson.toJson(DcsSocket.activeObjects.values()));
    }
}