package jobs;

import play.jobs.Job;
import play.jobs.OnApplicationStart;
import util.DcsSocket;

@OnApplicationStart
public class InitJob extends Job
{
	@Override
	public void doJob()
	{
		DcsSocket socket = new DcsSocket();
		new Thread(socket).start();
	}
}
