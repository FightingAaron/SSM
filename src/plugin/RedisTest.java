/**
 * 
 */
package plugin;

import com.ssm1.bean.H_test;

import redis.clients.jedis.Jedis;
import redis.clients.util.SafeEncoder;

/**
 * @author ChenYongHeng
 * @version 2018年12月6日 下午2:23:29
 */
public class RedisTest {
	
	public static void main(String[] args) {
		
		//直接使用RedisUtils实例进行五大数据类型的操作：
		/*JedisUtil jedisUtil=JedisUtil.getInstance();
		JedisUtil.Strings strings=jedisUtil.new Strings();
		strings.set("testClass", "RedisTest");
		System.out.println(strings.get("testClass"));*/
		
		
		//通过RedisUtil实例获取Jedis连接对象；这样就可以用原生的方式使用；最后使用完后需要手动将其归还到池子中：
		/*Jedis jedis=JedisUtil.getInstance().getJedis(); 
		for (int i = 0; i < 10; i++) { 
			jedis.set("test"+i, "test"+i); 
			System.out.println(i+"=="+jedis.get("test"+i));  
		
		}
		JedisUtil.getInstance().returnJedis(jedis); */
		
		
		//将java对象存到redis中：
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
