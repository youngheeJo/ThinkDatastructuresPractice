package map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class MyLinearMapTest {

    protected Map<String,Integer> map;

    @BeforeEach
    void setup() throws Exception {
        map = new MyLinearMap<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        map.put(null, 0);
    }

    @Test
    void testClear() {
        map.clear();
        assertEquals(0, map.size());
    }

    @Test
    void testContainsKey() {
        assertAll(
                () -> assertTrue(map.containsKey("Three")),
                () -> assertTrue(map.containsKey(null)),
                () -> assertFalse(map.containsKey("Four"))
        );
    }

    @Test
    void testContainsValue() {
        assertAll(
                () -> assertTrue(map.containsValue(3)),
                () -> assertTrue(map.containsValue(0)),
                () -> assertFalse(map.containsValue(4))
        );
    }

    @Test
    void testGet() {
        assertAll(
                () -> assertEquals(3, map.get("Three")),
                () -> assertEquals(0, map.get(null)),
                () -> assertNull(map.get("Four"))
        );
    }

    @Test
    void testIsEmpty() {
        assertFalse(map.isEmpty());
        map.clear();
        assertTrue(map.isEmpty());
    }

    @Test
    void testKeySet() {
        Set<String> keySet = map.keySet();
        assertEquals(4,keySet.size());
        assertTrue(keySet.contains("Three"));
        assertTrue(keySet.contains(null));
        assertFalse(keySet.contains("Four"));
    }

    @Test
    void testPut() {
        map.put("One", 11);
        assertEquals(4, map.size());
        assertEquals(11, map.get("One"));

        map.put("Five", 5);
        assertEquals(5, map.size());
        assertEquals(5, map.get("Five"));
    }

    @Test
    void testPutAll() {
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Six", 6);
        hashMap.put("Seven", 7);
        hashMap.put("Eight", 8);
        map.putAll(hashMap);
        assertEquals(7, map.size());
    }

    @Test
    void testRemove() {
        Integer oldValue = map.remove("One");
        assertEquals(1, oldValue);
        assertEquals(3, map.size());
        assertNull(map.get("One"));
    }

    @Test
    void testSize() {
        assertEquals(4, map.size());
    }

    @Test
    void testValues() {
        Collection<Integer> values = map.values();
        assertEquals(4, values.size());
        assertTrue(values.contains(3));
        assertTrue(values.contains(0));
        assertFalse(values.contains(4));
    }
}
