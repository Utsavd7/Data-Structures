import java.util.*;

public class BST{

    public static class Node{
        int data;
        Node left;
        Node right;

        Node (int data, Node left, Node right){  //constructor when there are no childs
            this.data = data;  
            this.left = left;  //set left to left
            this.right = right;  //set right to right
        }
    }

    public static Node construct(int[] arr, int lo, int hi){

        if (lo > hi){
            return null;
        }

        int mid = (lo + hi)/2;
        int data = arr[mid];

        Node lc = construct(arr, lo, mid-1);  //left case
        Node rc = construct(arr, mid+1, hi);  //right case

        Node node = new Node(data, lc, rc);
        return node;
    }

    public static void display (Node node){  //to display the tree

        if (node == null){  //to check empty node
            return;
        }

        String str = "";

        str += node.left == null ? " . " : node.left.data + " ";  //ternary operator
        str += " <-- " + node.data + " --> ";
        str += node.right == null ? " . " : node.right.data + " ";  //if right is null thenn "."

        System.out.println(str);

        display(node.left);
        display(node.right);
    }

    public static int size(Node node) {  //size of bst
        
        if (node == null) {
            return 0;
        }

        int ls = size(node.left);
        int rs = size(node.right);
        int ts = ls + rs + 1;
    
        return ts;
    }

    public static int sum(Node node) {  //sum of bst
        
        if (node == null) {
            return 0;
        }

        int ls = sum(node.left);
        int rs = sum(node.right);
        int ts = ls + rs + node.data;
    
        return ts;
    }

    public static int max(Node node) {  //max of bst
        
        if(node.right == null){
            return node.data;
        } 
        
        else {
            return max(node.right);
        }
    }

    public static int min(Node node) {  //min of bst
    
        if(node.left == null){
            return node.data;
        } 
        
        else {
            return min(node.left);
        }
    }

    public static boolean find(Node node, int data){  //find in bst
        
        if(node == null){
            return false;
        }

        if(data > node.data){
            return find(node.right, data);
        } 
        
        else if(data < node.data){
            return find(node.left, data);
        } 
        
        else {
            return true;
        }
    }  

    public static Node addAtLeaf(Node node, int data) {  //add node at the leaf
    
        if (node == null) {
            return new Node(data, null, null);
        }

        if (data > node.data) {
            node.right = addAtLeaf(node.right, data);
            return node;
        }
        
        else if (data < node.data) {
            node.left = addAtLeaf(node.left, data);
            return node;
        } 
        
        else {
            return node;
        }
    }

    public static Node remove(Node node, int data) {  //removes given data
        
        if (node == null) {
            return null;
        }

        if (data > node.data) {
            node.right = remove(node.right, data);
            return node;
        } 
        
        else if (data < node.data) {
            node.left = remove(node.left, data);
            return node;
        } 
        
        else {

            if(node.left == null && node.right == null){  //if both null
                return null;
            } 
            
            else if(node.left == null){  //if left side is null
                return node.right;
            } 
            
            else if(node.right == null){  //if right side is null
                return node.left;
            }
            
            else {
                int max = max(node.left);  //gets left max
                node.data = max;
                node.left = remove(node.left, max);  //remove max node from left
                return node;
            }
        }
    }

    static int sum = 0;
    public static void replaceSumOfLarger(Node node){  //replace sum of larger
        
        if(node == null){
            return;
        }

        replaceSumOfLarger(node.right);

        int temp = node.data;
        node.data = sum;
        sum += temp;

        replaceSumOfLarger(node.left);
    }  

    public static int lowestCommonAncestor(Node node, int data1, int data2) {
    
        if (data1 < node.data && data2 < node.data) {
            return lowestCommonAncestor(node.left, data1, data2);
        }
        
        else if (data1 > node.data && data2 > node.data) {
            return lowestCommonAncestor(node.right, data1, data2);
        }
        
        else {
            return node.data;
        }
    }

    public static void printInGivenRange(Node node, int data1, int data2) {  //print nodes between the given range
    
        if(node == null){
            return;
        }

        if(node.data > data1 && node.data > data2){
            printInGivenRange(node.left, data1, data2);
        }
        
        else if(node.data < data1 && node.data < data2){
            printInGivenRange(node.right, data1, data2);
        } 
        
        else {  //one small one big node
            printInGivenRange(node.left, data1, data2);
            System.out.println(node.data);
            printInGivenRange(node.right, data1, data2);
        }
    }

    public static void tofindtargetSumPair(Node root, Node node, int target) {  //target sum pair which is given 
        
        if (node == null) {  //to check if the node is empty or not
            return;
        }

        tofindtargetSumPair(root, node.left, target);  //pre order traversal

        int temp = target - node.data;  //create a complement
        
        if (temp > node.data) {  //main case always check 

            if (find(root, temp)) {  //use the boolean find here
                System.out.println(node.data + " + " + temp + " = " + target);
            }
        }
    
        tofindtargetSumPair(root, node.right, target);  //post order traversal 
    }

    public static void main(String[] args){
        int[] arr = {12, 25, 37, 50, 62, 75, 87};
        
        Node root = construct(arr, 0, arr.length - 1);

        // System.out.println("");
        // System.out.println("The BST is: ");
        // display(root);

        // System.out.println("");
        // System.out.println("The BST size is: ");
        // int size = size(root);
        // System.out.println(size);

        // System.out.println("");
        // System.out.println("The BST sum is: ");
        // int sum = sum(root);
        // System.out.println(sum);

        // System.out.println("");
        // System.out.println("The BST max is: ");
        // int max = max(root);
        // System.out.println(max);

        // System.out.println("");
        // System.out.println("The BST min is: ");
        // int min = min(root);
        // System.out.println(min);

        // System.out.println("");
        // System.out.println("The found is: ");
        // boolean found = find(root, 12);
        // System.out.println(found);

        // System.out.println("");
        // System.out.println("The BST after adding is: ");
        // root = addAtLeaf(root, 15);
        // display(root);

        // System.out.println("");
        // System.out.println("The BST after removing is: ");
        // root = remove(root, 15);
        // display(root);

        // System.out.println("");
        // System.out.println("The replace sum of larger is: ");
        // replaceSumOfLarger(root);
        // display(root);

        // System.out.println("");
        // System.out.println("The lowest common ancestor is: ");
        // int lca = lowestCommonAncestor(root, 12, 75);
        // System.out.println(lca);

        System.out.println("");
        System.out.println("The nodes in the given range are: ");
        printInGivenRange(root, 50, 75);

        System.out.println("");
        System.out.println("The target sum is: ");
        tofindtargetSumPair(root, root, 62);
    }
}