public class DoublyLinkedList<K, V> {

    private final Node<K, V> head;
    private final Node<K, V> tail;

    public DoublyLinkedList(){
        head = new Node<>(null, null);
        tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    public void moveToFront(Node<K, V> node){
        remove(node);
        addFirst(node);
    }

    public Node<K, V> removeLast(){
        if(tail.prev == head)return null;
        Node<K, V> node = tail.prev;
        remove(node);
        return node;
    }

    public void remove(Node<K, V> node){
        node.prev.next = node.next;
        node.next.prev = node.prev;

        //disconnect node itself
        node.next = null; node.prev = null;
    }

    public void addFirst(Node<K, V> node){
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

}
