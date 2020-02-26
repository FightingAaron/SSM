package task.V4;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @desc 任务管理器接口
 * @author yzl
 * @date 2018年2月13日		
 * @company 中通服软件科技有限公司
 */
public interface ITaskManager {
	
	 /**
     * 根据 Quartz Cron Expression 调试任务
     * @param className   任务包名和类名
     * @param name Quartz CronTrigger名称
     * @param group Quartz CronTrigger组
     * @param cronExpression Quartz Cron 表达式，如 "0/10 * * ? * * *"等
     */
	
     public JSONObject schedule(String taskClassName ,String name, String group, String cronExpression); 

	
     /**
      * 暂停触发器
      * 
      * @param triggerName 触发器名称
      */
     void pauseTrigger(String triggerName);  
     
     
     /**
      * 暂停触发器
      * 
      * @param triggerName 触发器名称
      * @param group 触发器组
      */
     void pauseTrigger(String triggerName, String group);  
     
     /**
      * 恢复触发器
      * 
      * @param triggerName 触发器名称
      */
     void resumeTrigger(String triggerName);  
     
     /**
      * 恢复触发器
      * 
      * @param triggerName 触发器名称
      * @param group 触发器组
      */
     void resumeTrigger(String triggerName, String group); 
     
     /**
      * 删除触发器
      * 
      * @param triggerName 触发器名称
      * @return
      */
     boolean removeTrigger(String triggerName);  
     
     /**
      * 删除触发器
      * 
      * @param triggerName 触发器名称
      * @param group 触发器组
      * @return
      */
     boolean removeTrigger(String triggerName, String group); 
     
     /**
      * 
      *Description:删除定时任务（删除定时任务会将任务上所有的触发器删除）
      *@param jobName  任务名称
      *@param group	任务所属的组
      *@return
      */
     boolean deleteJob (String jobName, String group);
     /**
      * 根据任务名称及组名获取任务状态
      * @param name
      * @param group
      * @return
      */
     public String  getJobStatusByName(String name,String group) throws Exception;
     
     /**
      * 
      * Description:返回所有任务的名称和状态（状态返回的是和任务同名触发器的状态）  map 的key是名称value是状态
      * @return
      */
     Map<String,String> getAllJobNameAndStatus();
     
     /**
      * 
      * Description:获取当前定时任务的线程数
      * @return
      */
     Map<String, String> getThreadNum();
     
}
