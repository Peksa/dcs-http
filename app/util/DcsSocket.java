package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.jboss.netty.channel.socket.ClientSocketChannelFactory;

import play.jobs.Job;
import play.jobs.OnApplicationStart;

import models.DcsObject;


public class DcsSocket implements Runnable
{
	private static ServerSocket serverSocket;
	private static double latestTime = -1L;
	public static boolean stop = false;
	
	public static ConcurrentMap<Long, DcsObject> objects = new ConcurrentHashMap<>();
	public static ConcurrentMap<Long, DcsObject> oldObjects = new ConcurrentHashMap<>();
	public static ConcurrentMap<Long, DcsObject> activeObjects = new ConcurrentHashMap<>();
	
	
	@Override
	public void run()
	{
		while (!stop)
		{
			Socket clientSocket = null;
			try {
			    serverSocket = new ServerSocket(9595);
			    
			    System.out.println("Waiting for connection");
			    clientSocket = serverSocket.accept();
			    System.out.println("Got connection!");
			    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			    String inputLine;
	
			    while ((inputLine = in.readLine()) != null) {   
			    	DcsObject obj = parseDcsObject(inputLine);
			    	if (obj == null)
			    		continue;
			    	if (obj.time > latestTime)
			    	{
			    		oldObjects = objects;
			    		objects = new ConcurrentHashMap<>();
			    		activeObjects.clear();
			    		latestTime = obj.time;
			    	}
			    	objects.put(obj.id, obj);
			    	if (isActive(obj))
			    		activeObjects.put(obj.id, obj);
			    }
			} 
			catch (IOException e) {
			    e.printStackTrace();
			} finally {
				try {
					if (serverSocket != null)
						serverSocket.close();
				}
				catch (IOException e) {}
				try {
					if (clientSocket != null)
						clientSocket.close();
				}
				catch (IOException e) { }
				objects.clear();
			}
		}
	}

	private boolean isActive(DcsObject obj)
	{
		DcsObject old = oldObjects.get(obj.id);
		if (old == null)
			return false;
		if (obj.heading != old.heading || obj.lat != old.lat || obj.lon != old.lon)
			return true;
		return false;
	}

	private static DcsObject parseDcsObject(String inputLine)
	{
		try {
			String[] tokens = inputLine.split("\t");
			DcsObject ret = new DcsObject();
			ret.time = Double.parseDouble(tokens[0]);
			ret.id = Long.parseLong(tokens[1]);
			ret.type = tokens[2];
			ret.lat = Double.parseDouble(tokens[3]);
			ret.lon = Double.parseDouble(tokens[4]);
			ret.alt = Double.parseDouble(tokens[5]);
			ret.heading = Double.parseDouble(tokens[6]);
			ret.country = tokens[7];
			ret.name = tokens[8];
			ret.groupName = tokens[9];
			return ret;
		} catch (Exception e) { 
			return null;
		}
	}
}
