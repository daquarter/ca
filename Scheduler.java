package scheduler;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;



public class Scheduler {
	
	private List<Job> readyQue = new CopyOnWriteArrayList<>();
	private ExecutorService runningThread = Executors.newSingleThreadExecutor();	
		
	void insert(Job job) {
		readyQue.add(job);
		readyQue.sort(Comparator.comparing(j -> j.remainTime));
	}
	
	void sleep(int i) {
		try {
			TimeUnit.MILLISECONDS.sleep(i);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void flowTime() {
		sleep(990);
	}
	
	void log(Job job) {
		System.out.println(job+" is running "+readyQue);
	}
	
	void start() {
		runningThread.execute(() -> {
			while(readyQue.size() != 0) {
				for(Job job : readyQue) {
					while(job.remainTime > 0) {
						log(job);
						flowTime();
						job.remainTime--;
						
						if(job.remainTime == 0) {
							readyQue.removeIf(j -> j.remainTime == 0);
						}
					}
				}
			}
			runningThread.shutdown();
			System.exit(0);
		}); 
		
	}
	public static void main(String[] args)throws IOException {
		Scheduler scheduler = new Scheduler();
		
		FileReader fr = new FileReader("text.txt");
		BufferedReader br = new BufferedReader(fr);
		StringTokenizer st;
		String data;
		boolean first = true;
		
		while((data = br.readLine()) != null) {
			st = new StringTokenizer(data,",");
			scheduler.insert(new Job(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			
			if(first) {
				scheduler.start();
				first = false;
			}
			
			scheduler.flowTime();
		}
	}

}












