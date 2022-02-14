import java.util.*;

public class BinaryTree{

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

    public static class Pair{
        Node node;
        int state;

        Pair(Node node, int state){  
            this.node = node;
            this.state = state;
        }
    }

    public static void display (Node node){

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

    public static int size (Node node){

        if (node == null){
            return 0;
        }

        int leftsize = size(node.left);
        int rightsize = size(node.right);
        int totalsize = leftsize + rightsize + 1;  //+1 because of the the main top node
        return totalsize;
    }

    public static int sum (Node node){

        if (node == null){
            return 0;
        }

        int leftsum = sum(node.left);
        int rightsum = sum(node.right);
        int totalsum = leftsum + rightsum + node.data; 
        return totalsum;
    }

    public static int max (Node node){
        
        if (node == null){
            return Integer.MIN_VALUE;
        }

        int leftmax = max(node.left);
        int rightmax = max(node.right);
        int totalmax = Math.max(node.data, Math.max(leftmax,rightmax));  //comparing with the main upper node
        return totalmax;
    }

    public static int min (Node node){

        if (node == null){
            return Integer.MAX_VALUE;
        }

        int leftmin = min(node.left);
        int rightmin = min(node.right);
        int totalmin = Math.min(node.data, Math.min(leftmin,rightmin));  //comparing with the main upper node
        return totalmin;
    }

    public static int height (Node node){

        if (node == null){
            return -1;  //-1 for edges height 0 for nodes height
        }

        int leftheight = height(node.left);
        int rightheight = height(node.right);
        int totalheight = Math.max(leftheight,rightheight) + 1;  //+1 for the upper most node
        return totalheight;
    }

    public static void traversal(Node node){  //traversal in binary tree

        if (node == null){
            return;
        }
        
        System.out.println(node.data + " in preorder");  //euler left ->pre
        traversal(node.left);
        System.out.println(node.data + " in inorder");  //euler between ->in
        traversal(node.right);
        System.out.println(node.data + " in postorder");  //euler right ->post
    }

    public static void levelOrder(Node node){  //level order tree

        Queue<Node> mainqueue = new ArrayDeque<>();

        mainqueue.add(node);

        while(mainqueue.size() > 0){
            int count = mainqueue.size();

            for(int i = 0 ; i < count ; i++){

                node = mainqueue.remove();
                System.out.println(node.data + " ");

                if (node.left != null){
                    mainqueue.add(node.left);
                }

                if (node.right != null){
                    mainqueue.add(node.right);
                }
            }

            System.out.println(" ");
        }
    }

    public static void PrePostInTraversal(Node node){  //iteration in pre post and in traversal

        Pair rtp = new Pair(node, 1);
        Stack<Pair> st = new Stack<>();
        st.push(rtp);  //rootpair

        String pre = "";
        String post = "";
        String in = "";

        while (st.size() > 0) {
        
            Pair top = st.peek();

            if (top.state == 1) {
                pre += top.node.data + " ";
                top.state++;

                if (top.node.left != null) {
                    Pair lp = new Pair(top.node.left, 1);
                    st.push(lp);
                }
            }

            else if (top.state == 2) {
                in += top.node.data + " ";
                top.state++;

                if (top.node.right != null) {
                Pair rp = new Pair(top.node.right, 1);
                st.push(rp);
                }
            }
            
            else {
                post += top.node.data + " ";
                top.state++;
                st.pop();
            }
        }

        System.out.println(pre);
        System.out.println(in);
        System.out.println(post);
    }

    public static ArrayList<Integer> nodeToRootPath(Node node, int data){  //node to root path

        if(node == null){
            return new ArrayList<>();
        }

        if(node.data == data){
            ArrayList<Integer> list = new ArrayList<>();
            list.add(node.data);
            return list;
        }

        ArrayList<Integer> llist = nodeToRootPath(node.left, data);
        
        if(llist.size() > 0){  //left list
            llist.add(node.data);
            return llist;
        }

        ArrayList<Integer> rlist = nodeToRootPath(node.right, data);
    
        if(rlist.size() > 0){  //right list
            rlist.add(node.data);
            return rlist;
        }

        return new ArrayList<>();
    }

    public static void kLevelsDown(Node node, int data){  //to print k levels down

        if (data == 0 ){
            System.out.println(node.data);
        }
        
        if (node == null || data < 0){
            return;
        }
        
        kLevelsDown(node.right, data -1);
        kLevelsDown(node.left, data - 1);
    }

    public static void pathToLeafFromRoot(Node node, String path, int sum, int lo, int hi){  //path to leaf from root

        if(node.left != null && node.right != null){
            pathToLeafFromRoot(node.left, path + node.data + " ", sum + node.data, lo, hi);
            pathToLeafFromRoot(node.right, path + node.data + " ", sum + node.data, lo, hi);
        } 
    
        else if(node.left != null){
            pathToLeafFromRoot(node.left, path + node.data + " ", sum + node.data, lo, hi);
        } 
    
        else if(node.right != null){
            pathToLeafFromRoot(node.right, path + node.data + " ", sum + node.data, lo, hi);
        } 
    
        else {
            path += node.data;
            sum += node.data;

            if(sum >= lo && sum <= hi){
                System.out.println(path);
            }
        }
    }

    public static Node LeftCloneTree(Node node){  //to create left clone tree
        
        if(node == null){
            return null;
        }

        Node lelftcloneroot = LeftCloneTree(node.left);
        Node rightcloneroot = LeftCloneTree(node.right);

        node.right = rightcloneroot;  
        node.left = new Node(node.data, lelftcloneroot, null);
    
        return node;
    }

    public static Node transBackFromLeftClonedTree(Node node){  //left cloned tree to normal

        if(node == null){
            return null;
        }

        node.left = transBackFromLeftClonedTree(node.left.left);
        node.right = transBackFromLeftClonedTree(node.right);

        return node;
    } 

    public static void printSingleChildNodes(Node node, Node parent){  //print only single child node

        if(node == null){
            return;
        }

        if(parent != null && parent.left == null && parent.right == node){
            System.out.println(node.data);
        } 
        
        else if(parent != null && parent.right == null && parent.left == node){
            System.out.println(node.data);
        }

        printSingleChildNodes(node.left, node);
        printSingleChildNodes(node.right, node);
    }

    public static Node removeLeaves(Node node){  //removes leaves from tree

        if(node == null){
            return null;
        }

        if(node.left == null && node.right == null){
            return null;
        }
    
        node.left = removeLeaves(node.left);
        node.right = removeLeaves(node.right);

        return node;
    }

    public static int diameter1(Node node) {  //diameter of a tree

        if (node == null) {
            return 0;
        }

        int lh = height(node.left);  //left height
        int rh = height(node.right);  //reight height
        int ld = diameter1(node.left);  //left dia  //max distance between 2 nodes of lhs
        int rd = diameter1(node.right);  //right dia  //max distance between 2 nodes of rhs

        int td = Math.max(lh + rh + 2, Math.max(ld, rd));  //total dia  //max distance between left's deepest and right's deepest
        return td;
    }

    static int dia = 0;

    public static int diameter2(Node node) {

        if (node == null) {
            return -1;
        }

        int lh = diameter2(node.left);  
        int rh = diameter2(node.right);
        int th = Math.max(lh, rh) + 1;

        if (lh + rh + 2 > dia) {
            dia = lh + rh + 2;
        }

        return th;
    }

    static class DPair {

        int ht;
        int dia;
    }

    public static DPair diameter3(Node node) {

        if (node == null) {
            DPair bp = new DPair();  //base pair
            bp.ht = -1;
            bp.dia = 0;
            return bp;
        }

        DPair lp = diameter3(node.left);  //left pair
        DPair rp = diameter3(node.right);  //right pair

        DPair mp = new DPair();
        mp.ht = Math.max(lp.ht, rp.ht) + 1;
        mp.dia = Math.max(lp.ht + rp.ht + 2, Math.max(lp.dia, rp.dia));
        return mp;
    }

    static int tilt = 0;
    public static int tilt(Node node){  //tilt is the difference between left and right side of the node
        if(node == null){
            return 0;
        }

        int ls = tilt(node.left);  //left size
        int rs = tilt(node.right);  //right size
        int ts = ls + rs + node.data;  //total size
    
        tilt += Math.abs(ls - rs);

        return ts;
    }

    public static class BSTPair {  //binary search tree

        int min;
        int max;
        boolean isBST;
    }

    public static BSTPair isBST(Node node){
    
        if(node == null){

            BSTPair bp = new BSTPair();
            bp.min = Integer.MAX_VALUE;
            bp.max = Integer.MIN_VALUE;
            bp.isBST = true;
            return bp;
        }

        BSTPair lp = isBST(node.left);  //left pair
        BSTPair rp = isBST(node.right);  //right pair
    
        BSTPair mp = new BSTPair();  //my pair for the top node

        mp.min = Math.min(node.data, Math.min(lp.min, rp.min));
        mp.max = Math.max(node.data, Math.max(lp.max, rp.max));

        mp.isBST = lp.isBST && rp.isBST && node.data >= lp.max && node.data <= rp.min;

        return mp;
    }

    public static class BalPair {  //to see if tree is balanced or not
        int ht;
        boolean isBal;
    }

    public static BalPair isBalanced(Node node){  
        
        if(node == null){

            BalPair bp = new BalPair();
            bp.ht = -1;
            bp.isBal = true;
            return bp;
        }

        BalPair lp = isBalanced(node.left);
        BalPair rp = isBalanced(node.right);
    
        BalPair mp = new BalPair();

        mp.ht = Math.max(lp.ht, rp.ht) + 1;
        mp.isBal = lp.isBal && rp.isBal && Math.abs(lp.ht - rp.ht) <= 1;

        return mp;
    }

    public static class BSTPair {  //largest bst of a tree

        int min;
        int max;
        boolean isBST;
        Node lbstroot;
        int lbstsize;
    }

    public static BSTPair isBST(Node node) {

        if (node == null) {
            BSTPair bp = new BSTPair();
            bp.min = Integer.MAX_VALUE;
            bp.max = Integer.MIN_VALUE;
            bp.isBST = true;
            bp.lbstroot = null;
            bp.lbstsize = 0;
            return bp;
        }

        BSTPair lp = isBST(node.left);  //left pair checks bst
        BSTPair rp = isBST(node.right);  //right pair checks bst

        BSTPair mp = new BSTPair();
    
        mp.min = Math.min(node.data, Math.min(lp.min, rp.min));
        mp.max = Math.max(node.data, Math.max(lp.max, rp.max));
        mp.isBST = lp.isBST && rp.isBST && node.data >= lp.max && node.data <= rp.min;

        if (mp.isBST) {
            mp.lbstroot = node;
            mp.lbstsize = lp.lbstsize + rp.lbstsize + 1;
        }
        
        else if (lp.lbstsize > rp.lbstsize) {
            mp.lbstroot = lp.lbstroot;
            mp.lbstsize = lp.lbstsize;
        } 
        
        else {
            mp.lbstroot = rp.lbstroot;
            mp.lbstsize = rp.lbstsize;
        }

        return mp;
    }

    public static void main(String[] args){
        Integer[] arr = {50 ,25, 12, null, null, 37, 30, null, 
                        null, null, 75, 62, null, 70, null, null, 
                        87, null, null};
        
        Node root = new Node(arr[0], null, null);

        Pair rootpair = new Pair(root, 1);  //1=left node 2=rightnode 3=pop

        Stack<Pair> st = new Stack<>();

        st.push(rootpair);
        
        int idx = 0;
        while (st.size() > 0){
            Pair top = st.peek();

            if (top.state == 1){

                 idx++;
                 if (arr[idx] != null){

                     top.node.left = new Node(arr[idx], null, null);

                     Pair leftpair = new Pair(top.node.left, 1);

                     st.push(leftpair);
                 }

                 else{
                     top.node.left = null;
                 }

                 top.state++;
            }

            else if (top.state == 2){

                idx++;
                 if (arr[idx] != null){

                     top.node.right = new Node(arr[idx], null, null);

                     Pair rightpair = new Pair(top.node.right, 1);

                     st.push(rightpair);
                 }

                 else{
                     top.node.right = null;
                 }

                 top.state++;
            }

            else {
                st.pop();
            }
        }


    // System.out.println("");
    // System.out.println("The binary tree is :");
    // display(root);

    // System.out.println("");
    // System.out.println("The size of binary tree is : " + size(root));

    // System.out.println("");
    // System.out.println("The max of binary tree is : " + max(root));

    // System.out.println("");
    // System.out.println("The min of binary tree is : " + min(root));

    // System.out.println("");
    // System.out.println("The height of binary tree is : " + height(root));
    
    // System.out.println("");
    // System.out.println("The traversal in binary tree is : ");
    // traversal(root);

    // System.out.println("");
    // System.out.println("The level order traversal in binary tree is : ");
    // levelOrder(root);

    // System.out.println("");
    // System.out.println("The PrePostInTraversal order traversal in binary tree is : ");
    // PrePostInTraversal(root);

    // System.out.println("");
    // System.out.println("The node to root path of binary tree is : " + nodeToRootPath(root, 12));
    
    // System.out.println("");
    // System.out.println("The k levels down of binary tree is : ");
    // kLevelsDown(root, 2);

    // System.out.println("");
    // System.out.println("The path to leaf from root of binary tree is : ");
    // pathToLeafFromRoot(root, "", 0, 150, 250);

    // System.out.println("");
    // System.out.println("The left clone tree is : ");
    // LeftCloneTree(root);
    // display(root);

    // System.out.println("");
    // System.out.println("The normal from a left clone tree is : ");
    // transBackFromLeftClonedTree(root);
    // display(root);

    // System.out.println("");
    // System.out.println("The single child nodes are : ");
    // printSingleChildNodes(root,null);

    // System.out.println("");
    // System.out.println("Tree without leaves is : ");
    // removeLeaves(root);
    // display(root);


    // System.out.println("");
    // System.out.println("Tree diameter is : ");
    // int diameter = 0;
    // diameter = diameter1(root);
    // System.out.println(diameter);

    // dia = 0;
    // diameter2(root);
    // System.out.println(dia);

    // DPair mp = diameter3(root);
    // System.out.println(mp.dia);

    // System.out.println("");
    // System.out.println("The tilt of the tree is : ");
    // tilt(root);
    // System.out.println(tilt);

    // System.out.println("");
    // System.out.println("The binary search tree is : ");
    // BSTPair p = isBST(root);
    // System.out.println(p.isBST);

    // System.out.println("");
    // System.out.println("The balanced tree is : ");
    // BalPair bp = isBalanced(root);
    // System.out.println(bp.isBal);

    // System.out.println("");
    // System.out.println("The largest BST of tree is : ");
    // BSTPair p = isBST(root);
    // System.out.println(p.lbstroot.data + "@" + p.lbstsize);

    }
}