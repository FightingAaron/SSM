package com.quartz.constant;
/**
 * 
 * @desc 常量
 * @author 张印园
 * @date 2018-2-6		
 * @company 中通服软件科技有限公司
 */
public class Constant {
	
	//导入数据使用常量
	public static final String SUCCESS_TOTAL ="successTotal";//导入成功的条数
	public static final String FAIL_TOTAL = "failTotal";//导入失败的条数
	public static final String FAIL_RESULT_LIST = "failResultList";//导入失败的结果集
	public static final String FAIL_RESULT_FILE_PATH = "";//导入失败的结果文件路径
	public static final String UPLOAD_PATH = "WEB-INF/upload";//文件上传的路径
	public static final String TEMP_PAHT = "WEB-INF/tem";//文件缓存的路径
	
	//任务组名常量
	public static final String TASK_DEFAULT_GROUP_NAME = "default";
	//数据库常量
	public static final String DEFAULT_DATASOURCE = "default";
	public static final String ITSP_DATASOURCE = "itsp";
	//消息常量
	public static final String ISSUCCESS = "isSuccess";
	public static final String MESSAGE = "message";
	public static final String MSG = "msg";
	public static final String STATE = "state";
	
	public static final String SUCCESS = "success";//成功
	public static final String FAIL = "fail";//失败
	public static final String TYPE = "type";//类型
	
	public static final int EXPORTTOTAL = 30000;//导出条数策略
	public static final int SIZE = 10000;//当excel导出的条数大于上面的EXCEPORTTOTAL值是分页查询数据，SIZE为每页的条数
	public static final int DOWNLOADPERSONNUM = 10; //最大下载人数
	public static final int UPLOADPERSONNUN = 10;//最大下载人数
	
}
