package task.V4;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @desc ����������ӿ�
 * @author yzl
 * @date 2018��2��13��		
 * @company ��ͨ������Ƽ����޹�˾
 */
public interface ITaskManager {
	
	 /**
     * ���� Quartz Cron Expression ��������
     * @param className   �������������
     * @param name Quartz CronTrigger����
     * @param group Quartz CronTrigger��
     * @param cronExpression Quartz Cron ���ʽ���� "0/10 * * ? * * *"��
     */
	
     public JSONObject schedule(String taskClassName ,String name, String group, String cronExpression); 

	
     /**
      * ��ͣ������
      * 
      * @param triggerName ����������
      */
     void pauseTrigger(String triggerName);  
     
     
     /**
      * ��ͣ������
      * 
      * @param triggerName ����������
      * @param group ��������
      */
     void pauseTrigger(String triggerName, String group);  
     
     /**
      * �ָ�������
      * 
      * @param triggerName ����������
      */
     void resumeTrigger(String triggerName);  
     
     /**
      * �ָ�������
      * 
      * @param triggerName ����������
      * @param group ��������
      */
     void resumeTrigger(String triggerName, String group); 
     
     /**
      * ɾ��������
      * 
      * @param triggerName ����������
      * @return
      */
     boolean removeTrigger(String triggerName);  
     
     /**
      * ɾ��������
      * 
      * @param triggerName ����������
      * @param group ��������
      * @return
      */
     boolean removeTrigger(String triggerName, String group); 
     
     /**
      * 
      *Description:ɾ����ʱ����ɾ����ʱ����Ὣ���������еĴ�����ɾ����
      *@param jobName  ��������
      *@param group	������������
      *@return
      */
     boolean deleteJob (String jobName, String group);
     /**
      * �����������Ƽ�������ȡ����״̬
      * @param name
      * @param group
      * @return
      */
     public String  getJobStatusByName(String name,String group) throws Exception;
     
     /**
      * 
      * Description:����������������ƺ�״̬��״̬���ص��Ǻ�����ͬ����������״̬��  map ��key������value��״̬
      * @return
      */
     Map<String,String> getAllJobNameAndStatus();
     
     /**
      * 
      * Description:��ȡ��ǰ��ʱ������߳���
      * @return
      */
     Map<String, String> getThreadNum();
     
}
