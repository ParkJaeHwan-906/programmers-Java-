package Ssafy.Algorithm.LinkedList;

public class Node <T>{
    public T data;
    public Node<T> link;

    public Node(T data, Node<T> link) {
        this.data = data;
        this.link = link;
    }

    public Node(T data) {
        this(data, null);
    }

    @Override
    public String toString() {
        return "Node [ data ="+data +", link"+link+"]";
    }
}
