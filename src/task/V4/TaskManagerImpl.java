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
 * @desc 任务管理器
 * @author yzl
 * @date 2018年2月13日		
 * @company 中通服软件科技有限公司
 */
@Component
public class TaskManagerImpl implements ITaskManager {

	Scheduler scheduler;
	ThreadPoolTaskExecutor executor;
	Logger log = LoggerFactory.getLogger(TaskManagerImpl.class);
	
	/**
	 * Description:添加任务
	 * @param taskClassName 需要加载的任务类名 
	 * @param name 任务名称
	 * @param group 任务所属组
	 * @param cronExpression 时间表达式
	 * @return
	 *@see com.ccssoft.ITaskManager.task.ITaskManger#schedule(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public JSONObject schedule(String taskClassName ,String name, String group, String cronExpression) {
		log.info("======添加定时任务");
		log.info("======需要加载的任务类名:"+taskClassName);
		log.info("======任务名称:"+name);
		log.info("======任务所属组:"+group);
		log.info("======时间表达式:"+cronExpression);
		
		JSONObject msg=null;
		try {
			msg = new JSONObject();
			//校验参数
			if (taskClassName == null || "".equals(taskClassName)) {
				msg.put(Constant.STATE, "-1");
				msg.put(Constant.MSG, "需要加加载的任务类名不可为空");
				log.info("需要加加载的任务类名不可为空");
				return msg;
			}
			if (name == null || "".equals(name)) {
				msg.put(Constant.STATE, "-1");
				msg.put(Constant.MSG, "任务名不可为空");
				log.info("任务名不可为空");
				return msg;
			}
			if (group == null || "".equals(group)) {
				msg.put(Constant.STATE, "-1");
				msg.put(Constant.MSG, "任务所属组不可为空");
				log.info("任务所属组不可为空");
				return msg;
			}
			if (cronExpression == null || "".equals(cronExpression)) {
				msg.put(Constant.STATE, "-1");
				msg.put(Constant.MSG, "任务表达式不可为空");
				log.info("任务表达式不可为空");
				return msg;
			}
			JobDetail job = scheduler.getJobDetail(JobKey.jobKey(name, group));
			if (job != null ) {
				msg.put(Constant.STATE, "-1");
				msg.put(Constant.MSG, "该任务已经存在，不可再添加");
				log.info("该任务已经存在，不可再添加");
				return msg;
			}
			Class<Job> taskClass = (Class<Job>) Class.forName(taskClassName);
			//创建任务
			job = newJob(taskClass).withIdentity(name, group).build();
			//创建触发器
			CronTrigger trigger = newTrigger().withIdentity(name, group).withSchedule(cronSchedule(cronExpression))
			        .build();
			scheduler.scheduleJob(job, trigger);
		} catch (ClassNotFoundException e) {
			log.error("通过任务类名创建类时失败", e);
			msg.put(Constant.STATE, "-1");
			msg.put(Constant.MSG, "通过任务类名创建类时失败");
			return msg;
		} catch (SchedulerException e) {
			log.error("调度器调度任务和触发器时失败", e);
			msg.put(Constant.STATE, "-1");
			msg.put(Constant.MSG, "调度器调度任务和触发器时失败");
			return msg;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			msg.put(Constant.STATE, "-1");
			msg.put(Constant.MSG, e.getMessage());
			return msg;
		}
		msg.put(Constant.STATE, "0");
		msg.put(Constant.MSG, "启动成功");
		return msg;
	}

	

	@Override
	public void pauseTrigger(String triggerName) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * Description:暂停定时任务的触发器
	 * @param triggerName 触发器名称
	 * @param group 触发器所属的组
	 * @see com.ccssoft.ITaskManager.task.ITaskManger#pauseTrigger(java.lang.String, java.lang.String)
	 */
	@Override
	public void pauseTrigger(String triggerName, String group) {
		
        log.info("======暂停定时任务的触发器,触发器名称:"+triggerName+"||触发器所属的组:"+group);
		try {
			scheduler.pauseTrigger(new TriggerKey(triggerName, group));
		} catch (SchedulerException e) {
			log.error("暂停定时任务的触发器失败",e);
		}
		
	}

	@Override
	public void resumeTrigger(String triggerName) {
		// TODO Auto-generated method stub

	}
	/**
	 * 
	 * Description:恢复定时任务的触发器
	 * @param triggerName 触发器名称
	 * @param group 触发器所属的组
	 * @see com.ccssoft.ITaskManager.task.ITaskManger#resumeTrigger(java.lang.String, java.lang.String)
	 */
	@Override
	public void resumeTrigger(String triggerName, String group) {

		log.info("======恢复定时任务的触发器,触发器名称:"+triggerName+"||触发器所属的组:"+group);
		try {
			scheduler.resumeTrigger(new TriggerKey(triggerName, group));
		} catch (SchedulerException e) {
			log.error("恢复定时任务的触发器失败", e);
		}
	}

	@Override
	public boolean removeTrigger(String triggerName) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * 
	 * Description: 删除定时任务的触发器
	 * @param triggerName 触发器名称
	 * @param group 触发器所属的组
	 * @return
	 * @see com.ccssoft.ITaskManager.task.ITaskManger#removeTrigdger(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean removeTrigger(String triggerName, String group) {
		log.info("======删除定时任务的触发器,触发器名称"+triggerName+"||触发器所属的组:"+group);
		try {
			//先暂停触发器，再删除触发器
			scheduler.pauseTrigger(new TriggerKey(triggerName, group));
			//删除触发器
			scheduler.unscheduleJob(new TriggerKey(triggerName, group));
		} catch (SchedulerException e) {
			log.error("删除定时任务的触发器失败",e);
			return false;
		}
		return true;
	}


	/**
	 * 
	 * Description:删除定时任务（删除定时任务会将任务上所有的触发器删除）
      *@param jobName  任务名称
      *@param group	任务所属的组
      *@return
	 * @see com.ccssoft.ITaskManager.task.ITaskManger#deleteJob(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean deleteJob(String jobName, String group) {
		log.info("======删除定时任务（删除定时任务会将任务上所有的触发器删除）,任务名称:"+jobName+"||任务所属的组"+group);
		try {
			//先暂停定时任务（暂停定时任务将暂停任务上所有的触发器），再删除
			scheduler.pauseJob(new JobKey(jobName,group));
			//删除定时任务
			scheduler.deleteJob(new JobKey(jobName, group));
		} catch (SchedulerException e) {
			log.error("删除定时任务失败",e);
			return false;
		}
		return true;
	}

	/**
     * 根据任务名称及组名获取任务状态
     * @param name
     * @param group
     * 
     *  STATE_BLOCKED 4 阻塞 
		STATE_COMPLETE 2 完成 
		STATE_ERROR 3 错误 
		STATE_NONE -1 不存在 
		STATE_NORMAL 0 正常 
		STATE_PAUSED 1 暂停**
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
     * Description:返回所有任务的名称和状态（状态返回的是和任务同名触发器的状态）  map 的key是名称value是状态
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
					log.info("******任务名称:"+jobName);
					log.info("******任务所属的组:"+groupName);
					TriggerState triggerState =scheduler.getTriggerState(new TriggerKey(jobName, groupName));
					log.info("******任务状态:"+triggerState);
					allJobNameAndStatusMap.put(jobName, triggerState.toString());
				}
			}
		} catch (SchedulerException e) {
			log.error("获得所有的任务名称和状态失败",e);
		}
		return allJobNameAndStatusMap;
	}


	/**
	 * 
	 * Description:获取当前定时任务的线程数
	 * @return
	 * @see com.ccssoft.ITaskManager.task.ITaskManger#getThreadNum()
	 */
	@Override
	public Map<String, String> getThreadNum() {
		Map<String, String> threadNumMap = new HashMap<String, String>();
		log.info("*****核心线程core:"+executor.getCorePoolSize());
		log.info("*****活跃线程active:"+executor.getActiveCount());
		log.info("*****线程池最大值max："+executor.getMaxPoolSize());
		log.info("*****当前线程池线程数量："+executor.getPoolSize());
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
