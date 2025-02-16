package Ssafy.Algorithm.LinkedList;

import java.util.EmptyStackException;

public class Stack<E> implements IStack<E>{

    private Node<E> top = null; // 초기 헤더 값, null

    /*
    Node 의 link 는 이전 Node 를 가리킨다
    top 은 현재 노드를 가리킨다

    즉 pop, peek, isEmpty 에서 top 이 null -> 데이터가 없다면
    예외를 발생 시킨다

    pop 을 보면 top 값으로 현재 상위 Node 를 가져오고
    상위 노드의 link ( 이전 Node ) 를 top 으로 변환한다
     */

    @Override
    public void push(E e) {
        /*
        현재 노드가 기존 top 을 가리킨다.
        top 은 현재 노드를 가리킨다.
         */
        top = new Node<>(e, top);
    }

    @Override
    public E pop() {
        if(isEmpty()) { // 스택이 비어있다면
            throw new EmptyStackException();
        }

        Node<E> popNode = top;
        top = popNode.link;
        popNode.link = null;
        return popNode.data;
    }

    @Override
    public E peek() {
        if(top == null) {
            throw new EmptyStackException();
        }

        Node<E> peekNode = top;

        return peekNode.data;
    }

    @Override
    public boolean isEmpty() {
        // 값이 없는 초기 상태
        return top == null;
    }

    @Override
    public int size() {
        /*
        top 값으로 현재 노드를 가져온 뒤, link 값을 이용해 마지막까지 탐색한다.
         */
        int size = 0;
        for(Node<E> tmp =top; tmp != null; tmp = tmp.link) {
            ++size;
        }
        return size;
    }
}
