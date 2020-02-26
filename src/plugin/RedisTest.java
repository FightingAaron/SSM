/**
 * 
 */
package plugin;

import com.ssm1.bean.H_test;

import redis.clients.jedis.Jedis;
import redis.clients.util.SafeEncoder;

/**
 * @author ChenYongHeng
 * @version 2018��12��6�� ����2:23:29
 */
public class RedisTest {
	
	public static void main(String[] args) {
		
		//ֱ��ʹ��RedisUtilsʵ����������������͵Ĳ�����
		/*JedisUtil jedisUtil=JedisUtil.getInstance();
		JedisUtil.Strings strings=jedisUtil.new Strings();
		strings.set("testClass", "RedisTest");
		System.out.println(strings.get("testClass"));*/
		
		
		//ͨ��RedisUtilʵ����ȡJedis���Ӷ��������Ϳ�����ԭ���ķ�ʽʹ�ã����ʹ�������Ҫ�ֶ�����黹�������У�
		/*Jedis jedis=JedisUtil.getInstance().getJedis(); 
		for (int i = 0; i < 10; i++) { 
			jedis.set("test"+i, "test"+i); 
			System.out.println(i+"=="+jedis.get("test"+i));  
		
		}
		JedisUtil.getInstance().returnJedis(jedis); */
		
		
		//��java����浽redis�У�
		H_test test=new H_test();
		test.setId("123321");
		
		
		JedisUtil jedisUtil=JedisUtil.getInstance();
		JedisUtil.Strings strings=jedisUtil.new Strings();
		strings.set("object3", SerializeUtil.serialize(test));
		
		//jedis.set(SafeEncoder.encode("object1"),SerializeUtil.serialize(p));
		
		byte[] testBy = jedisUtil.getJedis().get(SafeEncoder.encode("object3"));
		H_test test1 = (H_test) SerializeUtil.unserialize(testBy);
		System.out.println(test1.getId());

		
		
	}

}
