import java.util.*;
import java.io.*;

class LinkedList{
    static class Node{
        private Node next;
        private int data;
        Node(int data){
            this.data=data;
        }
    }
    Node head;
    private int length;
    public void add(int data){
        if(head==null){
            head = new Node(data);
            length++;
            return;
        }
        Node current = head;
        while(current.next!=null){
            current = current.next;
        }
        current.next = new Node(data);
        length++;
    }

    public void prepend(int data){
        if(head==null){
            head = new Node(data);
            length++;
            return;
        }
        Node current = new Node(data);
        current.next = head;
        head = current;
        length++;
    }

    /*  
        first check if head(first node) has the 
        required data if true then remove that node 
        by moving head to next node.
        else using current pointer check if next node
        data is required data if true then remove its
        link from previous node thus result in removing
        of that node.
    */ 
    public void remove(int data){
        if(head==null)return;
        Node current = head;
        if(head.data==data){
            head=head.next;
            length--;
            return;
        }

        while(current.next!=null){
            if(current.next.data==data){
                current.next = current.next.next;
                length--;
                return;
            }
            current = current.next;
        }
    }

    /*
        using for loop to move current pointer 1 less then
        required index then linking to next node thus removing
        node from position.
    */
    public void delete(int index){
        if(head==null)return;
        if(index==1){
            head = head.next;
            length--;
            return;
        }
        Node current = head;
        for(int i=1;i<index-1;i++){
            current = current.next;
        }
        current.next = current.next.next;
        length--;
    }

    /*
        using two pointer one slow that is increment
        by one node second fast incremented by 2 node each 
        time when fast reach the end slow will be at the middle 
        of linkedlist
    */
    public void middle(){
        if(head==null)return;
        
        Node slow = head;
        Node fast = head;

        while(slow!=null && fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        System.out.println(slow.data);
        
    }

    public void distinct(){
        if(head==null)return;
        HashSet<Integer> set = new HashSet<>();
        Node current = head;
        Node previous = null;
        while(current!=null){
            if(set.contains(current.data)){
                previous.next = current.next;
                length--;
            }
            else{
                set.add(current.data);
                previous = current;
            }
            current = current.next;
        }
    }

    public void nthend(int k){
        if(head==null)return;
        Node current  = head;
        for(int i=1;i<length-k+1;i++){
            current = current.next;
            
        }
        System.out.println(current.data);
    }

    /* using for loop to move current pointer to required
        index */
    public void show(int n){
        if(head==null)return;
        Node current = head;
        for(int i=1;i<n;i++){
            current = current.next;
        }
        System.out.println(current.data);
    }

    public void show(){
        if(head==null)return;
        Node current = head;
        while(current!=null){
            System.out.print(current.data+" ");
            current = current.next;
        }
        System.out.println();
    }

    public int size(){
        return length;
    }
}
