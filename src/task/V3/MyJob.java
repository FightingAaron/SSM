package task.V3;
/**
 * ºÃ≥–÷∏∂®¿‡
 */
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job{

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(new Date() + ": job 1 doing something...");
    }
}