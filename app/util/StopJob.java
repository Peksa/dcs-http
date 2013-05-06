package util;

import play.jobs.Job;
import play.jobs.OnApplicationStart;


@OnApplicationStart
public class StopJob extends Job
{
	@Override
	public void doJob()
	{
		DcsSocket socket = new DcsSocket();
		new Thread(socket).start();
	}
}
