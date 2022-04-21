/**
 * 
 */
package map;

import org.junit.jupiter.api.BeforeEach;

/**
 * @author downey
 *
 */
public class MyHashMapTest extends MyLinearMapTest {

	/**
	 * @throws Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		map = new MyHashMap<String, Integer>();
		map.put("One", 1);
		map.put("Two", 2);
		map.put("Three", 3);
		map.put(null, 0);
	}
}
