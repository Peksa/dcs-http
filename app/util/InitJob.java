package util;

import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.jobs.OnApplicationStop;


@OnApplicationStop
public class InitJob extends Job
{
	@Override
	public void doJob()
	{
		DcsSocket.stop = true;
	}
}
