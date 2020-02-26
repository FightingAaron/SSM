/**
 * 
 */
package init;

/**
 * @author ChenYongHeng
 * @version 2018年9月11日 下午3:28:05
 * 
 * 初始化方法
 */
public class InitAll {
	
	private InitTask initTask;
	
	public void init(){
		initTask.init();
	}

	/**
	 * @return the initTask
	 */
	public InitTask getInitTask() {
		return initTask;
	}

	/**
	 * @param initTask the initTask to set
	 */
	public void setInitTask(InitTask initTask) {
		this.initTask = initTask;
	}

	
}
