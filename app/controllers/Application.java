package controllers;

import play.*;
import play.mvc.*;
import util.DcsSocket;

import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.*;

public class Application extends Controller
{
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
    public static void index()
    {
        render();
    }
    
    public static void allObjects()
    {
    	renderJSON(gson.toJson(DcsSocket.objects.values()));
    }
    
    
    public static void activeObjects()
    {
    	renderJSON(gson.toJson(DcsSocket.activeObjects.values()));
    }

}