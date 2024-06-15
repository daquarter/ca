package scheduler;

public class Job {
	int pID;
	int remainTime;
	
	public Job(int pid, int time) {
		pID = pid;
		remainTime = time;
	}
	
	@Override
	public String toString() {
		return "[PID : "+pID + ",remainTime : "+remainTime+"]";
	}
}
