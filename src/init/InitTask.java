/**
 * 
 */
package init;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import task.V4.ITaskManager;

import com.alibaba.fastjson.JSONObject;
import com.quartz.bean.TaskBean;
import com.quartz.constant.Constant;
import com.quartz.service.QuartzService;

/**
 * @author ChenYongHeng
 * @version 2018年9月11日 下午3:37:02
 */
public class InitTask {
	
	//日志
	private static final Logger log = Logger.getLogger(InitTask.class);
	
	private ITaskManager  taskManager;
	private QuartzService quartzService;
	
	public void init(){

		log.info("*****启动时初始化定时任务开始*****");
		try {
			List<TaskBean> taskList = quartzService.getAllTasks();
			for (TaskBean task : taskList) {
				if ("Y".equals(task.getIfAutoBoot())&&"NORMAL".equals(task.getStatus())){
					String id = task.getId();
					String taskClass = task.getTaskClass();
					String executionPolicy =task.getExecutionPolicy();
					//启动任务
					JSONObject msg =taskManager.schedule(taskClass, id, Constant.TASK_DEFAULT_GROUP_NAME, executionPolicy);
					if ("0".equals(msg.getString(Constant.STATE))){
						log.info("定时任务"+id+"启动成功.");
					} else {
						log.info("定时任务"+id+"启动失败。失败原因:"+msg.getString(Constant.MSG));
					}
				}
			}
			log.info("*****启动时初始化定时任务完成*****");
		} catch (Exception e) {
			log.error("*****启动时初始化定时任务失败*****");
			log.error("初始化定时任务失败原因:", e);
		}
	
		
	}

	/**
	 * @return the taskManager
	 */
	public ITaskManager getTaskManager() {
		return taskManager;
	}

	/**
	 * @param taskManager the taskManager to set
	 */
	public void setTaskManager(ITaskManager taskManager) {
		this.taskManager = taskManager;
	}

	/**
	 * @return the quartzService
	 */
	public QuartzService getQuartzService() {
		return quartzService;
	}

	/**
	 * @param quartzService the quartzService to set
	 */
	public void setQuartzService(QuartzService quartzService) {
		this.quartzService = quartzService;
	}
}
