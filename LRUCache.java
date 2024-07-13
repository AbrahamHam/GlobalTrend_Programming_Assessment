import java.util.HashMap;
import java.util.LinkedList;

class LRUCache {
    private int capacity;
    private HashMap<Integer, Integer> map;
    private LinkedList<Integer> list;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        list = new LinkedList<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            System.out.println(-1);
            return -1;
        }

        list.remove(Integer.valueOf(key));
        list.addFirst(key);
        System.out.println(map.get(key));
        return map.get(key);
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            list.remove(Integer.valueOf(key));
        }

        if (map.size() == capacity) {
            int lru = list.removeLast();
            map.remove(lru);
        }

        list.addFirst(key);
        map.put(key, value);
        System.out.println("Added " + key + ":" + value);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);
        cache.get(2);
        cache.put(3, 3);
        cache.get(1);
        cache.get(2);
        cache.get(3);
        cache.put(4, 4);
        cache.get(1);
        cache.get(2);
        cache.get(3);
        cache.get(4);
    }
}