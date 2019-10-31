import java.util.*;

class LinkedList{
    static class Node{
        private int data;
        private Node next;
        public Node(int data){
            this.data = data;
        }
        public int getData(){
            return data;
        }

        public Node getNext(){
            return next;
        }
        public void setNext(Node n){
            next = n;
        }
        public void setData(int data){
            this.data = data;
        }
    }
    Node head;
    private int length;
    public Node getHead(){
        return head;
    }
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

    public void deleteMiddle(){
        if(head==null)return;

        Node slow =  head;
        Node fast =  head;
        Node previous = null;
        while(fast!=null && fast.next!=null){
            previous = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        previous.next = slow.next;
        
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

    /*
        Iterative method
        reversing links
    */
    public void reverse(){
        if(head==null)return;
        Node current = head;
        Node previous = null;
        Node next = current.next;
        while(current!=null){
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
       
        head = previous;
    }

    public Node reverse(Node mid){
        Node previous = null;
        while(mid!=null){
            Node n = new Node(mid.data);
            n.next = previous;
            previous =n; 
            mid = mid.next;
        }
        return previous;

    }
    public Boolean isPlaindrome(){
        Node reverse = reverse(head);
        return isEqual(head,reverse);
    }

    public Boolean isEqual(Node head , Node reversed){
        while(head!=null && reversed!=null)
        {
            if(head.data!=reversed.data)
                return false;
                head = head.next;
                reversed = reversed.next;
        }
        return head==null && reversed==null;
    }

    /* creating looop*/
    public void createLoop(){
        Node current  = head;
        while(current.next!=null){
            current = current.next;
        }

        current.next = head.next.next.next;
    }

    /* 
        checking loop
    */
    public Node loop(){
        Node slow = head;
        Node fast = head;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast)
                break;
        }

        if(fast==null || fast.next==null)
            return null;
        
        slow=head;
        while(slow!=fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
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

    /*****************************************************/
    public Node intersection(Node list1 , Node list2){
        if(list1==null || list2==null)
            return null;
        Result tail1 = getSizeAndTail(list1);
        Result tail2 = getSizeAndTail(list2);

        if(tail1.tail != tail2.tail)
            return null;

        Node shorter = tail1.size<tail2.size ? list1 : list2;
        Node longer = tail1.size<tail2.size ? list2 : list1;

        longer = getKthNode(longer,Math.abs(tail1.size - tail2.size));  

        while(shorter!=longer){
            shorter = shorter.next;  
            longer = longer.next;
        }
        return shorter;
    }

    public Node getKthNode(Node longer , int k) {
        Node current = longer;
        while(k>0 && current!=null){
            current = current.next;
            k--;
        }
        return current;
    }

    static class Result{
        int size;
        Node tail;
        public Result(Node res, int size){
            this.size = size;
            this.tail = res;
        }
    }

    public Result getSizeAndTail(Node list){
        int size = 1;
        Node current = list;
        while(current.next!=null)
        {
            size++;
            current = current.next;
        }
        return new Result(current,size);
    }
    /************************************************/
}
