/**
 *
 */
package map;

import java.util.*;

/**
 * Implementation of a Map using a binary search tree.
 *
 * @param <K>
 * @param <V>
 *
 */
public class MyTreeMap<K, V> implements Map<K, V> {

	private int size = 0;
	private Node root = null;

	/**
	 * Represents a node in the tree.
	 *
	 */
	protected class Node {
		public K key;
		public V value;
		public Node parent = null;
		public Node left = null;
		public Node right = null;

		/**
		 * @param key
		 * @param value
		 * @param left
		 * @param right
		 */
		public Node(K key, V value, Node parent) {
			this.key = key;
			this.value = value;
			this.parent = parent;
		}
	}

	@Override
	public void clear() {
		size = 0;
		root = null;
	}

	@Override
	public boolean containsKey(Object target) {
		return findNode(target) != null;
	}

	/**
	 * Returns the entry that contains the target key, or null if there is none.
	 *
	 * @param target
	 */
	private Node findNode(Object target) {
		// some implementations can handle null as a key, but not this one
		if (target == null) {
			throw new IllegalArgumentException();
		}

		// something to make the compiler happy
		@SuppressWarnings("unchecked")
		Comparable<? super K> k = (Comparable<? super K>) target;

		Node node = root;
		while (node != null) {
			int resultOfCompared = k.compareTo(node.key);
			if (resultOfCompared == 0) {
				return node;
			} else if (resultOfCompared > 0) {
				node = node.right;
			} else {
				node = node.left;
			}
		}
		return null;
	}

	/**
	 * Compares two keys or two values, handling null correctly.
	 *
	 * @param target
	 * @param obj
	 * @return
	 */
	private boolean equals(Object target, Object obj) {
		if (target == null) {
			return obj == null;
		}
		return target.equals(obj);
	}

	@Override
	public boolean containsValue(Object target) {
		return containsValueHelper(root, target);
	}

	private boolean containsValueHelper(Node node, Object target) {
		Deque<Node> stack = new ArrayDeque<>();
		stack.push(node);

		while (!stack.isEmpty()) {
			Node eachNode = stack.pop();
			if (equals(target, eachNode.value)) {
				return true;
			}

			if (eachNode.right != null) {
				stack.push(eachNode.right);
			}

			if (eachNode.left != null) {
				stack.push(eachNode.left);
			}
		}
		return false;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		throw new UnsupportedOperationException();
	}

	@Override
	public V get(Object key) {
		Node node = findNode(key);
		if (node == null) {
			return null;
		}
		return node.value;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Set<K> keySet() {
		Set<K> set = new LinkedHashSet<K>();
		addInOrder(root, set);
		return set;
	}

	private void addInOrder(Node node, Set<K> set) {
		if (node == null) {
			return;
		}
		addInOrder(node.left, set);
		set.add(node.key);
		addInOrder(node.right, set);
	}

	@Override
	public V put(K key, V value) {
		if (key == null) {
			throw new NullPointerException();
		}
		if (root == null) {
			root = new Node(key, value, null);
			size++;
			return null;
		}
		return putHelper(root, key, value);
	}

	private V putHelper(Node node, K key, V value) {
		Comparable<K> k = (Comparable<K>) key;

		Node eachNode = node;

		while (eachNode != null) {
			int resultOfCompare = k.compareTo(eachNode.key);
			if (resultOfCompare == 0) {
				V oldValue = eachNode.value;
				eachNode.value = value;
				return oldValue;
			} else if (resultOfCompare > 0) {
				if (eachNode.right == null) {
					eachNode.right = new Node(key, value, eachNode);
					size++;
					return null;
				} else {
					eachNode = eachNode.right;
				}
			} else {
				if (eachNode.left == null) {
					eachNode.left = new Node(key, value, eachNode);
					size++;
					return null;
				} else {
					eachNode = eachNode.left;
				}
			}
		}
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> map) {
		for (Entry<? extends K, ? extends V> entry: map.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public V remove(Object key) {
		Node targetNode = findNode(key);

		if (targetNode != null) {
			return removeHelper(targetNode);
		}

		return null;
	}

	private V removeHelper(Node targetNode) {
		Node parentNode = targetNode.parent;
		if (parentNode == null) {
			if (targetNode.left != null) {
				root = targetNode.left;
			} else if (targetNode.right != null) {
				root = targetNode.right;
			} else {
				root = null;
			}
		} else {
			if (equals(parentNode.right.key, targetNode.key)) {
				if (targetNode.left != null) {
					parentNode.right = targetNode.left;
				} else if (targetNode.right != null) {
					parentNode.right = targetNode.right;
				} else {
					parentNode.right = null;
				}
			} else {
				if (targetNode.left != null) {
					parentNode.left = targetNode.left;
				} else if (targetNode.right != null) {
					parentNode.left = targetNode.right;
				} else {
					parentNode.left = null;
				}
			}
		}
		size--;
		return targetNode.value;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Collection<V> values() {
		Set<V> set = new HashSet<V>();
		Deque<Node> stack = new LinkedList<Node>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node node = stack.pop();
			if (node == null) continue;
			set.add(node.value);
			stack.push(node.left);
			stack.push(node.right);
		}
		return set;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, Integer> map = new MyTreeMap<String, Integer>();
		map.put("Word1", 1);
		map.put("Word2", 2);
		Integer value = map.get("Word1");
		System.out.println(value);

		for (String key: map.keySet()) {
			System.out.println(key + ", " + map.get(key));
		}
	}

	/**
	 * Makes a node.
	 *
	 * This is only here for testing purposes.  Should not be used otherwise.
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public Node makeNode(K key, V value) {
		return new Node(key, value, null);
	}

	/**
	 * Sets the instance variables.
	 *
	 * This is only here for testing purposes.  Should not be used otherwise.
	 *
	 * @param node
	 * @param size
	 */
	public void setTree(Node node, int size ) {
		this.root = node;
		this.size = size;
	}

	/**
	 * Returns the height of the tree.
	 *
	 * This is only here for testing purposes.  Should not be used otherwise.
	 *
	 * @return
	 */
	public int height() {
		return heightHelper(root);
	}

	private int heightHelper(Node node) {
		if (node == null) {
			return 0;
		}
		int left = heightHelper(node.left);
		int right = heightHelper(node.right);
		return Math.max(left, right) + 1;
	}
}
