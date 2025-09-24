import java.util.HashMap;
import java.util.Map;

public class LRUcache<K, V> {

    private int capacity;
    private Map<K, Node<K, V>> map;
    private DoublyLinkedList<K, V> dll;

    public LRUcache(int capacity){
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.dll = new DoublyLinkedList<>();
    }

    public void remove(Node<K, V> node){
        dll.remove(node);
    }

    public synchronized V get(K key) {
        if(!map.containsKey(key)) return null;
        Node<K, V> node = map.get(key);
        dll.moveToFront(node);
        return node.value;
    }

    public synchronized void put(K key, V value){

        if(map.containsKey(key)){
            Node<K, V> node = map.get(key);
            node.value = value;
            dll.moveToFront(node);

        }else{
            if(capacity == map.size()){
                Node<K, V> lru = dll.removeLast();
                if(lru != null)map.remove(lru.key);
            }
            Node<K, V> node = new Node(key, value);
            dll.addFirst(node);
            map.put(key, node);
        }
    }

    public synchronized void remove(K key){

        if(!map.containsKey(key))return;

        Node<K, V> node = map.get(key);
        dll.remove(node);
        map.remove(key);
    }
}
