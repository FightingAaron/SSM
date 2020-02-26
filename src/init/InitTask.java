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
 * @version 2018��9��11�� ����3:37:02
 */
public class InitTask {
	
	//��־
	private static final Logger log = Logger.getLogger(InitTask.class);
	
	private ITaskManager  taskManager;
	private QuartzService quartzService;
	
	public void init(){

		log.info("*****����ʱ��ʼ����ʱ����ʼ*****");
		try {
			List<TaskBean> taskList = quartzService.getAllTasks();
			for (TaskBean task : taskList) {
				if ("Y".equals(task.getIfAutoBoot())&&"NORMAL".equals(task.getStatus())){
					String id = task.getId();
					String taskClass = task.getTaskClass();
					String executionPolicy =task.getExecutionPolicy();
					//��������
					JSONObject msg =taskManager.schedule(taskClass, id, Constant.TASK_DEFAULT_GROUP_NAME, executionPolicy);
					if ("0".equals(msg.getString(Constant.STATE))){
						log.info("��ʱ����"+id+"�����ɹ�.");
					} else {
						log.info("��ʱ����"+id+"����ʧ�ܡ�ʧ��ԭ��:"+msg.getString(Constant.MSG));
					}
				}
			}
			log.info("*****����ʱ��ʼ����ʱ�������*****");
		} catch (Exception e) {
			log.error("*****����ʱ��ʼ����ʱ����ʧ��*****");
			log.error("��ʼ����ʱ����ʧ��ԭ��:", e);
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
