import java.util.*;

public class LinkedList{

    public class Node{
        int data;
        Node next;
    }
        
    Node head;  //first node address
    Node tail;  //last node address
    int size;  //size of linked list

    public void addLast (int val){  //adding at the end

        Node temp = new Node();  //creating a new node
        temp.data = val;  //inserting value
        temp.next = null;  //as value will be first node next will be empty

        if (size==0){
            head = tail = temp;  //first node tail=head
        }

        else{
            tail.next = temp;  //or else link to tail is size is not 0
            tail = temp;  //update the new tail
        }
        size++;  //update regularly
    }

    public int size(){  //to display size
        return size;
    }

    public void display(){

        Node temp = head;

        while (temp != null){  //if linked list is not empty
            System.out.print(temp.data + "-->");
            temp = temp.next;  //counter of temp shifts to next node
        }

        System.out.println();
    }

    public void removeFirst(){  //to remove first element from linked list

        if (size == 0){  //t check for an empty list
            System.out.println("List is empty");
        }

        else if (size == 1){  //only one item in the list
            head = tail = null;
            size = 0;
        }

        else{  //for more than one element
            head = head.next;
            size--;
        }
    }
    
    public int getFirst(){  //to get first element

        if (size == 0){  //check empty
            System.out.println("List is empty");
            return -1;
        }
        
        else{
            return head.data;
        }
    }

    public int getLast(){  //to get last element

        if (size == 0){  //check empty
            System.out.println("List is empty");
            return -1;
        }
        
        else{
            return tail.data;
        }
    }

    public int getAt(int idx){  //to get index element

        if (size == 0){  //check empty
            System.out.println("List is empty");
            return -1;
        }

        else if (idx < 0 || idx >= size){  //check negative index or index greater than size
            System.out.println("Invalid arguments");
            return -1;
        }
        
        else{
            Node temp = head;

            for (int i = 0 ; i < idx ; i++){
                temp = temp.next;
            }
            return temp.data;
        }
    }

    public void addFirst(int val){

        Node temp = new Node();  //creating a new node
        temp.data = val;  //inserting value
        temp.next = head;  //new node poits to the head
        head = temp;  //make head the new node

        if (size == 0){
            tail = temp;
        }

        size++;
    }

    public void addAtIndex(int idx, int val){  //add element at a given index

        if (idx < 0 || idx > size){  //check negative index or index greater than size
            System.out.println("Invalid arguments");
        }

        else if (idx == 0){
            addFirst(val);
        }

        else if (idx == size){
            addLast(val);
        }

        else{
            Node node = new Node();  //new node gets created
            node.data = val;  //value of node gets set

            Node temp = head;  //setting temp on head
            for (int i = 0 ; i < idx - 1 ; i++ ){  //index -1 before temp
                temp = temp.next;
            }

            node.next = temp.next;  //pointing nodes next to temps next
            temp.next = node;  //pointing temps next to new node;

            size++;
        }
    }

    public void removeLast(){  //to remove last from linked list

        if (size == 0){  //t check for an empty list
            System.out.println("List is empty");
        }

        else if (size == 1){  //only one item in the list
            head = tail = null;
            size = 0;
        }

        else{  //for more than one element
            Node temp = head;

            for (int i = 0 ; i < size - 2  ; i++){  //why size -2 because for eg size is 5 then last element index is 4 and previous element index is 3
                temp = temp.next;
            }

            tail=temp;
            temp.next = null;  //temp is last so dosen't point to anyone
            size--;
        }
    }

    private Node getNodeAt(int idx){  //used in reverse DI
        Node temp = head;

        for (int i = 0 ; i < idx ; i++){
                temp = temp.next;
            }
            return temp;  //this dosen't return the data it returns the entire node 
    }

    public void reverseDI(){   //reverse a linked list data iterative
        int leftindex = 0;
        int rightndex = size - 1;

        while (leftindex < rightndex){
            Node left = getNodeAt(leftindex);
            Node right = getNodeAt(rightndex);

            int temp = left.data;  //by swapping
            left.data = right.data;
            right.data = temp;

            leftindex++;
            rightndex--;
        }
    }

    public void reversePI(){  //reverse a linked list pointer iterative
    
        Node prev = null;
        Node curr = head;

        while (curr != null){  //when current does not become null

            Node next = curr.next;  //to save this just as a temp
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        Node temp = head;  //swapping head and tail
        head = tail;
        tail = temp;
    }

    public void removeAt (int idx){

        if (idx < 0 || idx > size){
            System.out.println("Invalid arguments");
        }

        else if (idx == 0){
            removeFirst();
        }

        else if (idx == size - 1){
            removeLast();
        }

        else{
            Node temp = head;
            for (int i = 0 ; i < idx - 1 ; i++){  //runs till it finds the previous index 
                temp = temp.next;
            }

            temp.next = temp.next.next;
            size--;
        }
    }

    // public static class LLToStackAdapter{  //linked list to stack
        
    //     LinkedList<Integer> List;

    //     public LLToStackAdapter(){
    //         list = new LinkedList<>();
    //     }

    //     int size(){
    //         return list.size();
    //     }

    //     void push (int val){
    //         list.addFirst(val);
    //     }

    //     int pop(){
    //         if (size() == 0){
    //             System.out.println("Stack underflow");
    //             return -1;
    //         } 

    //         else{
    //             return list.removeFirst();
    //         }
    //     }

    //     int top(){
    //         if (size() == 0){
    //             System.out.println("Stack underflow");
    //             return -1;
    //         } 

    //         else{
    //             return list.getFirst();
    //         }
    //     }
    // }

    // public static class LLToQueueAdapter {
    //     LinkedList<Integer> list;

    //     public LLToQueueAdapter() {
    //         list = new LinkedList<>();
    //     }

    //     int size() {
    //         return list.size(); 
    //     }

    //     void add(int val) {
    //         list.addLast(val);
    //     }

    //     int remove() {

    //         if(size() == 0){
    //         System.out.println("Queue underflow");
    //         return -1;
    //         } 
            
    //         else {
    //             return list.removeFirst();
    //         }
    //     }

    //     int peek() {
            
    //         if(size() == 0){
    //             System.out.println("Queue underflow");
    //             return -1;
    //         } 
            
    //         else {
    //             return list.getFirst();
    //         }    
    //     }
    // }

    public int kthNodeFromEnd(int k){  //return kth node data from end

        Node slow = head;  //making 2 poiners
        Node fast = head;

        for (int i = 0 ; i < k ; i++){
            fast = fast.next;  //point fast next of slow
        }

        while (fast != tail){  //till the time fast is not on tail
            slow = slow.next;    //slow moves 1 ahead
            fast = fast.next;  //fast moves 1 ahead of slow
        }

        return slow.data;  //slow points to the main data
    }

    public int mid(){  //returns middle element

        Node slow = head;  //making 2 poiners
        Node fast = head;

        while (fast.next != null  && fast.next.next != null){  //till the time fast of next and next is not null
            slow = slow.next;    //slow moves 1 ahead
            fast = fast.next.next;  //fast moves 2 ahead of slow
        }

        return slow.data;
    }

    // public static LinkedList mergeTwoSortedList(LinkedList l1, LinkedList l2){  //merges 2 sorted linked list
        
    //     Node one = l1.head;
    //     Node two = l2.head;

    //     LinkedList res = new LinkedList();

    //     while (one != null && two != null){

    //         if (one.data < two.data){
    //             res.addLast(one.data);
    //             one = one.next;
    //         }

    //         else{
    //             res.addLast(two.data);
    //             two = two.next;
    //         }
    //     }

    //     while (one != null){
    //         res.addLast(one.data);
    //         one = one.next;
    //     }

    //     while (two != null){
    //         res.addLast(two.data);
    //         two = two.next;
    //     }
    // }

    // public static Node midNode (Node node, Node tail){  //used in merge sort used to find middle element

    //     Node fast = head;
    //     NOde slow = head;

    //     while(fast != tail && f.next != tail){
    //         fast = fast.next.next;
    //         slow = slow.next;
    //     }

    //     return slow;
    // }

    // public static LinkedList mergeSort(Node head , Node tail){  //merge sort

    //     if (head == tail){
    //         LinkedList baseResult = new LinkedList();
    //         baseResult.addLast(head.data);
    //         return baseResult;
    //     }

    //     Node mid = midNode(head, tail);

    //     LinkedList firstSortedHalf = mergeSort(head, mid);
    //     LinkedList secondSortedHalf = mergeSort(mid.next, tail);
    //     LinkedList completeList = LinkedList.mergeTwoSortedList(firstSortedHalf, secondSortedHalf);

    //     return completeList;
    // }

    public void removeDuplicates(){  //removing all the duplicates from a linked list

        LinkedList newList = new LinkedList();

        while(this.size() > 0){
            int val = this.getFirst();
            this.removeFirst();

            if(newList.size() == 0 || newList.tail.data != val){
                newList.addLast(val);
            }
        }

        this.head = newList.head;
        this.tail = newList.tail;
        this.size = newList.size;
    }

    public void oddEven(){  //displaying odd first then even following

        LinkedList odd = new LinkedList();
        LinkedList even = new LinkedList();

        while(this.size > 0){
            
            int val = this.getFirst();
            this.removeFirst();

            if (val%2 == 0){
                even.addLast(val);
            }

            else{
                odd.addLast(val);
            }
        }

        if (odd.size > 0 && even.size >0){  //both odd and even elements
            odd.tail.next = even.head;
            this.head = odd.head;
            this.tail = even.tail;
            this.size = odd.size + even.size;
        }

        else if(odd.size >0){  //no even elements
            this.head = odd.head;
            this.tail = odd.tail;
            this.size = odd.size;
        }

        else if(even.size > 0){  //no odd elements
            this.head = even.head;
            this.tail = even.tail;
            this.size = even.size;
        }
    }

    public void kReverse(int k){  //k no geven every k times reverse the linked list

        LinkedList prev = null;

        while(this.size >0){
            
            LinkedList curr = new LinkedList();

            if (this.size() >= k){
                for (int i = 0 ; i < k ; i++){

                    int val = this.getFirst();
                    this.removeFirst();
                    curr.addFirst(val);
                }
            }

            else{
                int originalsize = this.size();
                for (int i = 0 ; i < originalsize ; i++){

                    int val = this.getFirst();
                    this.removeFirst();
                    curr.addLast(val);
                }
            }

            if (prev == null){
                prev = curr;
            }

            else{
                prev.tail.next = curr.head;
                prev.tail = curr.tail;
                prev.size = curr.size;
            }
        }

        this.head = prev.head;
        this.tail = prev.tail;
        this.size = prev.size;
    }

    

    public static void main(String[] args) {

	    LinkedList ll = new LinkedList();

        ll.addLast(100);  //adding at last
        ll.addLast(21);
        ll.addLast(180);

        ll.addFirst(399);  //adding first
        ll.addFirst(840);
        ll.addFirst(633);

        ll.addAtIndex(2,560);  //adding element at given index
        ll.addAtIndex(3,967);
        ll.addAtIndex(5,760);

        // ll.removeLast();  //removes last element from linked list;

        // ll.removeAt(2);  //remove the given index

        // System.out.println(" ");
        // System.out.println("Size of Linked List: " + ll.size());

        System.out.println(" ");
        System.out.println("The linked list is: ");
        ll.display();

        // System.out.println(" ");
        // System.out.println("The linked list is after removing first element: ");
        // ll.removeFirst();
        // ll.display();

        // System.out.println(" ");
        // System.out.println("The first element of linked list is: " + ll.getFirst());

        // System.out.println(" ");
        // System.out.println("The last element of linked list is: " + ll.getLast());

        // System.out.println(" ");
        // System.out.println("The element in the given index is: " + ll.getAt(2));

        // System.out.println(" ");
        // System.out.println("Reversed linked list is: ");
        // ll.reverseDI();
        // ll.display();

        // System.out.println(" ");
        // System.out.println("Reversed linked list is: ");
        // ll.reversePI();
        // ll.display();

        // System.out.println(" ");
        // System.out.println("The kth element from the last is: " + ll.kthNodeFromEnd(2));
        
        // System.out.println(" ");
        // System.out.println("The middle element is: " + ll.mid());

        // System.out.println(" ");
        // System.out.println("The merged linked list is: ");
        // ll.mergeTwoSortedLists(30 54 32, 55 64 77 234 77 43 66);
        // ll.display();

        // System.out.println(" ");
        // System.out.println("The merge sort linked list is: ");
        // ll.mergeSort(10 20 30 40 50, 7 9 12 15 37 43 44 48 52 56);
        // ll.display();

        // System.out.println(" ");
        // System.out.println("The linked list without duplicates is: ");
        // ll.removeDuplicates();
        // ll.display();

        // System.out.println(" ");
        // System.out.println("The odd even linked list is: ");
        // ll.oddEven();
        // ll.display();

        System.out.println(" ");
        System.out.println("The kReverse linked list is: ");
        ll.kReverse(3);
        ll.display();

    }
}



