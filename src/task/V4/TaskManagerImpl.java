package task.V4;


import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import net.sf.json.JSONObject;


import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.quartz.constant.Constant;



/**
 * 
 * @desc ���������
 * @author yzl
 * @date 2018��2��13��		
 * @company ��ͨ������Ƽ����޹�˾
 */
@Component
public class TaskManagerImpl implements ITaskManager {

	Scheduler scheduler;
	ThreadPoolTaskExecutor executor;
	Logger log = LoggerFactory.getLogger(TaskManagerImpl.class);
	
	/**
	 * Description:�������
	 * @param taskClassName ��Ҫ���ص��������� 
	 * @param name ��������
	 * @param group ����������
	 * @param cronExpression ʱ����ʽ
	 * @return
	 *@see com.ccssoft.ITaskManager.task.ITaskManger#schedule(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public JSONObject schedule(String taskClassName ,String name, String group, String cronExpression) {
		log.info("======��Ӷ�ʱ����");
		log.info("======��Ҫ���ص���������:"+taskClassName);
		log.info("======��������:"+name);
		log.info("======����������:"+group);
		log.info("======ʱ����ʽ:"+cronExpression);
		
		JSONObject msg=null;
		try {
			msg = new JSONObject();
			//У�����
			if (taskClassName == null || "".equals(taskClassName)) {
				msg.put(Constant.STATE, "-1");
				msg.put(Constant.MSG, "��Ҫ�Ӽ��ص�������������Ϊ��");
				log.info("��Ҫ�Ӽ��ص�������������Ϊ��");
				return msg;
			}
			if (name == null || "".equals(name)) {
				msg.put(Constant.STATE, "-1");
				msg.put(Constant.MSG, "����������Ϊ��");
				log.info("����������Ϊ��");
				return msg;
			}
			if (group == null || "".equals(group)) {
				msg.put(Constant.STATE, "-1");
				msg.put(Constant.MSG, "���������鲻��Ϊ��");
				log.info("���������鲻��Ϊ��");
				return msg;
			}
			if (cronExpression == null || "".equals(cronExpression)) {
				msg.put(Constant.STATE, "-1");
				msg.put(Constant.MSG, "������ʽ����Ϊ��");
				log.info("������ʽ����Ϊ��");
				return msg;
			}
			JobDetail job = scheduler.getJobDetail(JobKey.jobKey(name, group));
			if (job != null ) {
				msg.put(Constant.STATE, "-1");
				msg.put(Constant.MSG, "�������Ѿ����ڣ����������");
				log.info("�������Ѿ����ڣ����������");
				return msg;
			}
			Class<Job> taskClass = (Class<Job>) Class.forName(taskClassName);
			//��������
			job = newJob(taskClass).withIdentity(name, group).build();
			//����������
			CronTrigger trigger = newTrigger().withIdentity(name, group).withSchedule(cronSchedule(cronExpression))
			        .build();
			scheduler.scheduleJob(job, trigger);
		} catch (ClassNotFoundException e) {
			log.error("ͨ����������������ʱʧ��", e);
			msg.put(Constant.STATE, "-1");
			msg.put(Constant.MSG, "ͨ����������������ʱʧ��");
			return msg;
		} catch (SchedulerException e) {
			log.error("��������������ʹ�����ʱʧ��", e);
			msg.put(Constant.STATE, "-1");
			msg.put(Constant.MSG, "��������������ʹ�����ʱʧ��");
			return msg;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			msg.put(Constant.STATE, "-1");
			msg.put(Constant.MSG, e.getMessage());
			return msg;
		}
		msg.put(Constant.STATE, "0");
		msg.put(Constant.MSG, "�����ɹ�");
		return msg;
	}

	

	@Override
	public void pauseTrigger(String triggerName) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * Description:��ͣ��ʱ����Ĵ�����
	 * @param triggerName ����������
	 * @param group ��������������
	 * @see com.ccssoft.ITaskManager.task.ITaskManger#pauseTrigger(java.lang.String, java.lang.String)
	 */
	@Override
	public void pauseTrigger(String triggerName, String group) {
		
        log.info("======��ͣ��ʱ����Ĵ�����,����������:"+triggerName+"||��������������:"+group);
		try {
			scheduler.pauseTrigger(new TriggerKey(triggerName, group));
		} catch (SchedulerException e) {
			log.error("��ͣ��ʱ����Ĵ�����ʧ��",e);
		}
		
	}

	@Override
	public void resumeTrigger(String triggerName) {
		// TODO Auto-generated method stub

	}
	/**
	 * 
	 * Description:�ָ���ʱ����Ĵ�����
	 * @param triggerName ����������
	 * @param group ��������������
	 * @see com.ccssoft.ITaskManager.task.ITaskManger#resumeTrigger(java.lang.String, java.lang.String)
	 */
	@Override
	public void resumeTrigger(String triggerName, String group) {

		log.info("======�ָ���ʱ����Ĵ�����,����������:"+triggerName+"||��������������:"+group);
		try {
			scheduler.resumeTrigger(new TriggerKey(triggerName, group));
		} catch (SchedulerException e) {
			log.error("�ָ���ʱ����Ĵ�����ʧ��", e);
		}
	}

	@Override
	public boolean removeTrigger(String triggerName) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * 
	 * Description: ɾ����ʱ����Ĵ�����
	 * @param triggerName ����������
	 * @param group ��������������
	 * @return
	 * @see com.ccssoft.ITaskManager.task.ITaskManger#removeTrigdger(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean removeTrigger(String triggerName, String group) {
		log.info("======ɾ����ʱ����Ĵ�����,����������"+triggerName+"||��������������:"+group);
		try {
			//����ͣ����������ɾ��������
			scheduler.pauseTrigger(new TriggerKey(triggerName, group));
			//ɾ��������
			scheduler.unscheduleJob(new TriggerKey(triggerName, group));
		} catch (SchedulerException e) {
			log.error("ɾ����ʱ����Ĵ�����ʧ��",e);
			return false;
		}
		return true;
	}


	/**
	 * 
	 * Description:ɾ����ʱ����ɾ����ʱ����Ὣ���������еĴ�����ɾ����
      *@param jobName  ��������
      *@param group	������������
      *@return
	 * @see com.ccssoft.ITaskManager.task.ITaskManger#deleteJob(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean deleteJob(String jobName, String group) {
		log.info("======ɾ����ʱ����ɾ����ʱ����Ὣ���������еĴ�����ɾ����,��������:"+jobName+"||������������"+group);
		try {
			//����ͣ��ʱ������ͣ��ʱ������ͣ���������еĴ�����������ɾ��
			scheduler.pauseJob(new JobKey(jobName,group));
			//ɾ����ʱ����
			scheduler.deleteJob(new JobKey(jobName, group));
		} catch (SchedulerException e) {
			log.error("ɾ����ʱ����ʧ��",e);
			return false;
		}
		return true;
	}

	/**
     * �����������Ƽ�������ȡ����״̬
     * @param name
     * @param group
     * 
     *  STATE_BLOCKED 4 ���� 
		STATE_COMPLETE 2 ��� 
		STATE_ERROR 3 ���� 
		STATE_NONE -1 ������ 
		STATE_NORMAL 0 ���� 
		STATE_PAUSED 1 ��ͣ**
     * 
     */
    public String  getJobStatusByName(String name,String group) throws Exception{
    	String status = "NORMAL";
		TriggerState triggerState = scheduler.getTriggerState(new TriggerKey(name, group));
		status =  triggerState.toString();
    	return status;
    }
	

	/**
     * 
     * Description:����������������ƺ�״̬��״̬���ص��Ǻ�����ͬ����������״̬��  map ��key������value��״̬
     * @return
     */
	@Override
	public Map<String, String> getAllJobNameAndStatus() {
		Map<String,String> allJobNameAndStatusMap = new HashMap<String, String>();
		try {
			List<String>  jobGroupList = scheduler.getJobGroupNames();
			for (String jobGroup : jobGroupList) {
				Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.jobGroupEquals(jobGroup));
				for ( JobKey jobKey : jobKeys ) {
					String jobName = jobKey.getName();
					String groupName = jobKey.getGroup();
					log.info("******��������:"+jobName);
					log.info("******������������:"+groupName);
					TriggerState triggerState =scheduler.getTriggerState(new TriggerKey(jobName, groupName));
					log.info("******����״̬:"+triggerState);
					allJobNameAndStatusMap.put(jobName, triggerState.toString());
				}
			}
		} catch (SchedulerException e) {
			log.error("������е��������ƺ�״̬ʧ��",e);
		}
		return allJobNameAndStatusMap;
	}


	/**
	 * 
	 * Description:��ȡ��ǰ��ʱ������߳���
	 * @return
	 * @see com.ccssoft.ITaskManager.task.ITaskManger#getThreadNum()
	 */
	@Override
	public Map<String, String> getThreadNum() {
		Map<String, String> threadNumMap = new HashMap<String, String>();
		log.info("*****�����߳�core:"+executor.getCorePoolSize());
		log.info("*****��Ծ�߳�active:"+executor.getActiveCount());
		log.info("*****�̳߳����ֵmax��"+executor.getMaxPoolSize());
		log.info("*****��ǰ�̳߳��߳�������"+executor.getPoolSize());
		threadNumMap.put("core", executor.getCorePoolSize()+"");
		threadNumMap.put("active", executor.getActiveCount()+"");
		threadNumMap.put("max", executor.getMaxPoolSize()+"");
		threadNumMap.put("pool", executor.getPoolSize()+"");
		
		return threadNumMap;
	}



	public Scheduler getScheduler() {
		return scheduler;
	}



	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}



	public ThreadPoolTaskExecutor getExecutor() {
		return executor;
	}



	public void setExecutor(ThreadPoolTaskExecutor executor) {
		this.executor = executor;
	}
	
}
