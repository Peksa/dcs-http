package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import db.DB;

import models.DcsEntity;
import models.DcsObject;
import play.Logger;


public class DcsSocket implements Runnable
{
	private static ServerSocket serverSocket;
	public static boolean stop = false;
	private static final int DCS_PORT = 9595;
	
	@Override
	public void run()
	{
		while (!stop)
		{
			Socket clientSocket = null;
			try {
			    serverSocket = new ServerSocket(DCS_PORT);
			    
			    Logger.info("Waiting for connection by DCS on: %s:%d", serverSocket.getInetAddress().getHostAddress(), serverSocket.getLocalPort());
			    clientSocket = serverSocket.accept();
			    Logger.info("Got connection from DCS %s:%d", clientSocket.getInetAddress().getHostAddress(), clientSocket.getPort());
			    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			    String inputLine;
	
			    while ((inputLine = in.readLine()) != null)
			    {   
			    	DcsEntity entity = DcsParser.parse(inputLine);
			    	DB.updateEntity(entity);
			    }
			} 
			catch (IOException e) {
				Logger.error(e, "Connection error");
				if (serverSocket == null)
				{
					Logger.error("Unable to listen on port %s", DCS_PORT);
					break;
				}
			} finally {
				cleanup(serverSocket, clientSocket);
				DB.clear();
			}
		}
	}

	private void cleanup(ServerSocket serverSocket2, Socket clientSocket)
	{
		if (serverSocket != null)
		{
			try {
				serverSocket.close();
			} catch (IOException e) {
				// ignore
			}
			
		}
			
		if (clientSocket != null)
		{
			try {
				clientSocket.close();
			} catch (IOException e) {
				// ignore
			}
		}
	}
}
