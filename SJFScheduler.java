package exam;

import exam.process;
import exam.SJFScheduler;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

public class SJFScheduler{
  private List<process> readyQue = new CopyOnWriteArrayList<>();
  private ExecutorService runningThread = Executors.newSingleThreadExecutor();

  void insert(process p){
    readyQue.add(p);
    readyQue.sort(Comparator.comparingInt(j -> j.remainTime));
  }

  void log(process p){
    System.out.pringln(p+" is running "+readyQue);
  }

  void sleep(int mesc){
    try{
      TimeUnit.MILLISECONDS.sleep(mesc);
    }catch(InterruptedException e){
      e.printStackTrace();
    }
  }

  void flowtime(){
    sleep(990);  
  }

  void start(){
    runningThread.execute(()->{
      while(readyQue.size()!=0){
          for(process p:readyQue){
            while(p.remainTime>0){
                log(p);
                sleep(1000);
                p.remainTime--;
                if(p.remainTime==0) readyQue.removeIf(j->j.remainTime==0);
          }
        }
    }
    runningThread.shutdownNow();
    System.exit(0);
  });
}

  public static main(String args[]){
    SJFScheduler sch = new SJFScheduler();
    // insert start flowtime
  }
}
