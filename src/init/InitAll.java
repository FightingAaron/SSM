/**
 * 
 */
package init;

/**
 * @author ChenYongHeng
 * @version 2018��9��11�� ����3:28:05
 * 
 * ��ʼ������
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
