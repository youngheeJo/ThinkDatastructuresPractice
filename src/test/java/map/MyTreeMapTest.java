/**
 * 
 */
package map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author downey
 *
 */
public class MyTreeMapTest {

	private MyTreeMap<String, Integer> map;

	/**
	 * @throws Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		map = new MyTreeMap<String, Integer>();
		MyTreeMap<String, Integer>.Node node08 = map.makeNode("08", 8);
		
		MyTreeMap<String, Integer>.Node node03 = map.makeNode("03", 3);
		MyTreeMap<String, Integer>.Node node10 = map.makeNode("10", 10);
		node08.left = node03;
		node08.right = node10;
		
		MyTreeMap<String, Integer>.Node node01 = map.makeNode("01", 1);
		MyTreeMap<String, Integer>.Node node06 = map.makeNode("06", 6);
		MyTreeMap<String, Integer>.Node node14 = map.makeNode("14", 14);
		node03.left = node01;
		node03.right = node06;
		node10.right = node14;
		
		MyTreeMap<String, Integer>.Node node04 = map.makeNode("04", 4);
		MyTreeMap<String, Integer>.Node node07 = map.makeNode("07", 7);
		MyTreeMap<String, Integer>.Node node13 = map.makeNode("13", 13);
		node06.left = node04;
		node06.right = node07;
		node14.left = node13;
		
		map.setTree(node08, 9);
	}

	/**
	 * Test method for {@link MyLinearMap#clear()}.
	 */
	@Test
	public void testClear() {
		map.clear();
		assertEquals(0, map.size());
	}

	/**
	 * Test method for {@link MyLinearMap#containsKey(Object)}.
	 */
	@Test
	public void testContainsKey() {
		assertAll(
				() -> assertTrue(map.containsKey("03")),
				() -> assertFalse(map.containsKey("05"))
		);
	}

	/**
	 * Test method for {@link MyLinearMap#containsValue(Object)}.
	 */
	@Test
	public void testContainsValue() {
		assertAll(
				() -> assertTrue(map.containsValue(3)),
				() -> assertFalse(map.containsValue(5))
		);
	}

	/**
	 * Test method for {@link MyLinearMap#get(Object)}.
	 */
	@Test
	public void testGet() {
		assertAll(
				() ->assertEquals(1,map.get("01")),
				() ->assertEquals(3,map.get("03")),
				() ->assertEquals(4,map.get("04")),
				() ->assertEquals(6,map.get("06")),
				() ->assertEquals(7,map.get("07")),
				() ->assertEquals(8,map.get("08")),
				() ->assertEquals(10,map.get("10")),
				() ->assertEquals(13,map.get("13")),
				() ->assertEquals(14,map.get("14")),
				() ->assertNull(map.get("02")),
				() ->assertNull(map.get("05"))
		);
	}

	/**
	 * Test method for {@link MyLinearMap#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		assertFalse(map.isEmpty());
		map.clear();
		assertTrue(map.isEmpty());
	}

	/**
	 * Test method for {@link MyLinearMap#keySet()}.
	 */
	@Test
	public void testKeySet() {
		Set<String> keySet = map.keySet();
		assertAll(
				() -> assertEquals(9, keySet.size()),
				() -> assertTrue(keySet.contains("03")),
				() -> assertFalse(keySet.contains("05"))
		);
	}

	/**
	 * Test method for {@link MyLinearMap#put(Object, Object)}.
	 */
	@Test
	public void testPut() {
		map.put("06", 66);
		assertAll(
				() -> assertEquals(9, map.size()),
				() -> assertEquals(66, map.get("06"))
		);
		
		map.put("05", 5);
		assertAll(
				() -> assertEquals(10, map.size()),
				() -> assertEquals(5, map.get("05"))
		);
	}

	/**
	 * Test method for {@link MyLinearMap#putAll(Map)}.
	 */
	@Test
	public void testPutAll() {
		Map<String, Integer> m = new HashMap<String, Integer>();
		m.put("02", 2);
		m.put("05", 5);
		m.put("12", 12);
		map.putAll(m);
		assertEquals(12, map.size());
	}

	/**
	 * Test method for {@link MyLinearMap#remove(Object)}.
	 */
	@Test
	public void testRemove() {
		// nothing to test, since this method is not implemented
	}

	/**
	 * Test method for {@link MyLinearMap#size()}.
	 */
	@Test
	public void testSize() {
		assertEquals(9, map.size());
	}

	/**
	 * Test method for {@link MyLinearMap#values()}.
	 */
	@Test
	public void testValues() {
		Collection<Integer> keySet = map.values();
		assertAll(
				() -> assertEquals(9, keySet.size()),
				() -> assertTrue(keySet.contains(3)),
				() -> assertFalse(keySet.contains(5))
		);
	}
}
