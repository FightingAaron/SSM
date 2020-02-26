/**
 * 
 */
package plugin;

import javax.swing.JDialog;

import redis.clients.jedis.Jedis;

/**
 * @author ChenYongHeng
 * @version 2018年11月26日 下午4:50:00
 */
public class RedisUtil {
	
	public static void main(String[] args) {
		Jedis redis=new Jedis("localhost");
		redis.set("author", "Aaron");
		System.out.println(redis.ping());
		System.out.println(redis.get("author"));
	}
	
}
