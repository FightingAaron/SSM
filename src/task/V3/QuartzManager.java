package task.V3;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

public class QuartzManager {

    private Scheduler scheduler;  

    /** 
     * @Description: ���һ����ʱ���� 
     *  
     * @param jobName ������ 
     * @param jobGroupName  �������� 
     * @param triggerName �������� 
     * @param triggerGroupName ���������� 
     * @param jobClass  ���� 
     * @param cron   ʱ�����ã��ο�quartz˵���ĵ�  
     */  
    @SuppressWarnings({ "unchecked", "rawtypes" })  
    public void addJob(String jobName, String jobGroupName, 
            String triggerName, String triggerGroupName, Class jobClass, String cron) {  
        try {  
            // �������������飬����ִ����
            JobDetail jobDetail= JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();

            // ������  
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // ��������,��������  
            triggerBuilder.withIdentity(triggerName, triggerGroupName);
            triggerBuilder.startNow();
            // ������ʱ���趨  
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            // ����Trigger����
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();

            // ������������JobDetail��Trigger
            scheduler.scheduleJob(jobDetail, trigger);  

            // ����  
            if (!scheduler.isShutdown()) {  
                scheduler.start();  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  

    /** 
     * @Description: �޸�һ������Ĵ���ʱ��
     *  
     * @param jobName 
     * @param jobGroupName
     * @param triggerName ��������
     * @param triggerGroupName ���������� 
     * @param cron   ʱ�����ã��ο�quartz˵���ĵ�   
     */  
    public void modifyJobTime(String jobName, 
            String jobGroupName, String triggerName, String triggerGroupName, String cron) {  
        try {  
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);  
            if (trigger == null) {  
                return;  
            }    

            String oldTime = trigger.getCronExpression();  
            if (!oldTime.equalsIgnoreCase(cron)) { 
                /** ��ʽһ ������ rescheduleJob ��ʼ */
                // ������  
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                // ��������,��������  
                triggerBuilder.withIdentity(triggerName, triggerGroupName);
                triggerBuilder.startNow();
                // ������ʱ���趨  
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
                // ����Trigger����
                trigger = (CronTrigger) triggerBuilder.build();
                // ��ʽһ ���޸�һ������Ĵ���ʱ��
                scheduler.rescheduleJob(triggerKey, trigger);
                /** ��ʽһ ������ rescheduleJob ���� */

                /** ��ʽ������ɾ����Ȼ���ڴ���һ���µ�Job  */
                //JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobName, jobGroupName));  
                //Class<? extends Job> jobClass = jobDetail.getJobClass();  
                //removeJob(jobName, jobGroupName, triggerName, triggerGroupName);  
                //addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, cron); 
                /** ��ʽ�� ����ɾ����Ȼ���ڴ���һ���µ�Job */
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  

    /** 
     * @Description: �Ƴ�һ������ 
     *  
     * @param jobName 
     * @param jobGroupName 
     * @param triggerName 
     * @param triggerGroupName 
     */  
    public void removeJob(String jobName, String jobGroupName,  
            String triggerName, String triggerGroupName) {  
        try {  
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);

            scheduler.pauseTrigger(triggerKey);// ֹͣ������  
            scheduler.unscheduleJob(triggerKey);// �Ƴ�������  
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));// ɾ������  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  

    /** 
     * @Description:�������ж�ʱ���� 
     */  
    public void startJobs() {  
        try {  
            scheduler.start();  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  

    /** 
     * @Description:�ر����ж�ʱ���� 
     */  
    public void shutdownJobs() {  
        try {  
            if (!scheduler.isShutdown()) {  
                scheduler.shutdown();  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }  

}