/*
Question: design a cache that can support
- put(key, value)
- delete(key)
- value get(key)
- List<Entry> getKMostRecentKeys(int k) -- most recently accessed k <key, values>s.
*/
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
public class KMostRecentKeysCache {

    private class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Node head;
    Node tail;
    Map<Integer, Node> map;

    public KMostRecentKeysCache() {
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<Integer, Node>();
    }

    public static void main(String[] args) {
        KMostRecentKeysCache object = new KMostRecentKeysCache();

        object.put(1, 10);
        object.put(2, 20);
        object.put(3, 20);
        object.delete(2);
        object.put(1, 20);
        object.put(4, 40);
        object.get(1);
        List<Node> result = object.getKMostRecentKeys(2);
        for (Node node : result) {
            System.out.println("Key: " + node.key + ", Value: " + node.value);
        }
    }


    private void put(int key, int value) {
        Node node = null;
        if (!map.containsKey(key)) {
            node = new Node(key, value);
            map.put(key, node);
        } else {
            node = map.get(key);
            node.value = value;
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        moveToTail(node);
    }

    private void delete(int key) {
        if (!map.containsKey(key)) {
            return;
        }

        Node node = map.get(key);
        map.remove(key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        moveToTail(node);
        return node.value;
    }

    private List<Node> getKMostRecentKeys(int k) {
        List<Node> result = new ArrayList<Node>();

        Node cur = tail;
        for (int i = 0; i < k; i++) {
            cur = cur.prev;
            result.add(cur);
        }

        return result;
    }
    
    private void moveToTail(Node node) {
        tail.prev.next = node;
        node.prev = tail.prev;
        node.next = tail;
        tail.prev = node;
    }

}