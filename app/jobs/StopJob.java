package jobs;

import play.jobs.Job;
import play.jobs.OnApplicationStop;
import util.DcsSocket;


@OnApplicationStop
public class StopJob extends Job
{
	@Override
	public void doJob()
	{
		DcsSocket.stop = true;
	}
}
