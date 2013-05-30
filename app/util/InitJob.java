package util;

import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class InitJob extends Job
{
	@Override
	public void doJob()
	{
		System.setProperty("java.net.preferIPv4Stack", "true");
		DcsSocket socket = new DcsSocket();
		new Thread(socket).start();
	}
}
