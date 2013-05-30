package util;

import play.jobs.Job;
import play.jobs.OnApplicationStop;


@OnApplicationStop
public class StopJob extends Job
{
	@Override
	public void doJob()
	{
		DcsSocket.stop = true;
	}
}
