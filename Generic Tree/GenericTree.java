import java.util.*;

import javax.sound.sampled.SourceDataLine;

public class GenericTree{
    private static class Node{

        int data;
        ArrayList<Node> children = new ArrayList<>();
    }
    
    //d(10) --> 10 will print itself and its family
    //d(20), d(30), d(40) will print themselves and their families
    //d(10) = self(10) + d(20) + d(20) + d(30) + d(40)
    public static void display(Node node){
        String str = node.data + " --> ";
        for(Node child: node.children){
            str +=child.data + ", ";
        }
        str +=".";
        System.out.println(str);

        for(Node child: node.children){
            display(child);
        }
    }

    // public static int size;  //part of multisolver
    // public static int min;
    // public static int max;
    // public static int height;

    public static Node predecessor;  //part of predecessor and successor
    public static Node successor;
    static int var;

    public static int ceil;  //SMALLEST AMONGST LARGER  //part of ceil and floor
    public static int floor;  //LARGEST AMONGST SMALLER

    public static int maximumSumNode = 0; //part of Maximum Subtree Sum
    public static int maximumSum = Integer.MIN_VALUE;

    public static void main(String[] args){

        int[] arr={10,20,50,-1,60,-1,-1,30,70,-1,80,
                   110,-1,120,-1,-1,90,-1,-1,40,100,
                   -1,-1,-1};
        
        Node root = null;
        Stack<Node> st= new Stack<>();  //create a stack

        for(int i=0; i<arr.length; i++){

            if(arr[i] == -1){  //If it is a leaf node
                st.pop();
            }

            else{
                Node t= new Node();
                t.data= arr[i];
                
                if(st.size() > 0){  //checking if there is a root already in the stack
                    st.peek().children.add(t);
                }

                else{
                    root=t;  //creating the root
                }

                st.push(t);  //Pushing the Node in the stack
            }
        }

        // System.out.println("Generic Tree: ");
        // display(root);

        // System.out.println("");
        // System.out.println("Size of Generic Tree : " + size(root));

        // System.out.println("");
        // System.out.println("Biggest node of Generic Tree : " + max(root));

        // System.out.println("");
        // System.out.println("Mirror Tree: ");
        // mirrorTree(root);
        // display(root);

        // System.out.println("");
        // System.out.println("Removing Leaves: ");
        // removeLeaves(root);
        // display(root);

        // System.out.println("");
        // System.out.println("Linearize Tree: ");
        // Linearize(root);
        // display(root);

        // System.out.println("");
        // System.out.println("Linearize Tree: ");
        // linear2(root);
        // display(root);

        // System.out.println("");
        // System.out.println("Height of Generic Tree: " + height(root));

        // System.out.println("");
        // System.out.println("Traverse Tree: ");
        // traversal(root);

        // System.out.println("");
        // System.out.println("Level Order Line Wise Order Tree: ");
        // levelOrderLineWise(root);

        // System.out.println("Find element in Tree: ");
        // System.out.println(findelement(110,root));

        // System.out.println("Node to root path in Tree: ");
        // System.out.println(nodetoRootPath(root,110));
        
        // System.out.println("Lowest common ancestor in Tree: ");
        // System.out.println(lca(root,110,80));

        // System.out.println("Distance between nodes in a tree: ");
        // System.out.println(distancebetweennodes(root,110,80));

        // System.out.println("Check similar tree: ");
        // System.out.println(similarTree(root,root)); //generally make 2 trees but here i'm comparing with 1 only
    
        // System.out.println("Check similar tree: ");
        // System.out.println(areTreesMirrorInShape(root,root)); //generally make 2 trees but here i'm comparing with 1 only
    
        // System.out.println("Check symmetric: ");
        // System.out.println(isSymmetric(root)); //generally make 2 trees but here i'm comparing with 1 only
    
        // Node root = construct(arr);  //for multisolver question
        // size = 0;
        // min = INTEGER.MAX_VALUE;
        // max = INTEGER.MIN_VALUE;
        // height = 0;
        // multisolver(root,0);
        // System.out.println("Size : " + size);
        // System.out.println("Min : " + min);
        // System.out.println("Max : " + max);
        // System.out.println("Height : " + height);

        // System.out.println("Predecessor and successor: ");
        // pas(root,110); 
        // System.out.println("Predecessor: "  + predecessor.data);
        // System.out.println("Successor: "  + successor.data);

        // ceil = Integer.MAX_VALUE;
        // floor = Integer.MIN_VALUE;
        // System.out.println("Ceil and floor: ");
        // cnf(root,110); 
        // System.out.println("ceil: "  + ceil);
        // System.out.println("floor: "  + floor);

        // System.out.println("Kth Largest: ");
        // System.out.println(kthlargest(root,110)); 

        // System.out.println("Maximum sum subtree: ");
        // System.out.println(maxSumSubTree(root)); 
    }


    public static int size (Node node){  //Generic Tree size
        int s=0;

        for(Node child : node.children){
            int cs = size(child);
            s = s + cs; //all nodes lying inside the main parent node
        }
        s = s + 1;  //+1 for the main parent node
    return s;
    }

    public static int max(Node node){ //biggest node in the tree
        int max = Integer.MIN_VALUE;

        for(Node child : node.children){
            int cm = max(child);
            max = Math.max(cm, max);
        }

        max= Math.max(node.data, max);

        return max;
    }

    public static int height(Node node){  //height of generic tree
        int ht = -1;
        for(Node child : node.children){
            int ch = height(child);
            ht = Math.max(ch,ht);
        }
        ht+=1;
        return ht;
    }

    public static void traversal(Node node){  //traversing a tree

        //area1 euler's left, on the way deep in recursion, node's pre area

        System.out.println("Node Pre: " + node.data);  //euler
        for (Node child : node.children){
            //edge pre
            System.out.println("Edge Pre: " + node.data + "--" + child.data);
            traversal(child);
            System.out.println("Edge Pre: " + node.data + "--" + child.data);
            //edge post
        }

        //area2 euler's right, on the way out of recursion, node's post area

        System.out.println("Node Post: " + node.data);  //euler
        for (Node child : node.children){
            traversal(child);
        }
    } 

    public static void levelOrderLineWise(Node node){  //Level Order Line Wise
        Queue<Node> mainqueue = new ArrayDeque<>();
        mainqueue.add(node);

        Queue<Node> childqueue = new ArrayDeque<>();

        while(mainqueue.size() > 0){
            node = mainqueue.remove();
            System.out.println(node.data + " ");

            for(Node child : node.children){
                childqueue.add(child);
            }

            if (mainqueue.size() == 0){
                mainqueue = childqueue;
                childqueue = new ArrayDeque<>();
                System.out.println();
            }
        }
    }
    
    private static void childQueue(Node child) {
    }
    public static void mirrorTree(Node node){  //mirror a generic tree
        Stack<Node> s= new Stack<>();

        for (Node child : node.children){
            s.push(child);
        }

        node.children= new ArrayList<>();
        while(!s.isEmpty()){
            node.children.add(s.pop());
        }

        for (Node child : node.children){
            mirrorTree(child);
        }
    }
    
    public static void removeLeaves(Node node){  //remove leaves from a tree

        for (int i= node.children.size() -1 ; i>=0 ; i--){
            Node child = node.children.get(i);
            if (child.children.size() == 0){
                node.children.remove(child);
            }
        }
        for (Node child : node.children){
            removeLeaves(child);
        }
    }

    public static void Linearize(Node node){  //linearize a tree
        for (Node child : node.children){
            Linearize(child);
        }

        while (node.children.size() > 1){
            Node lastchild = node.children.remove(node.children.size() -1);
            Node secondlast = node.children.get(node.children.size() -1);   
            Node c = Last(secondlast);
            c.children.add(lastchild);
        } 
    }

    public static Node Last(Node node){
        while (node.children.size() == 1){
            node = node.children.get(0);
        }
        return node;
    }

    public static Node linear2(Node root)  //efficient way of linearize tree
    {
        if(root.children.size() == 0)
            return root;
        Node ltail = linear2(root.children.get(root.children.size() - 1));
        while(root.children.size() > 1)
        {
            Node last = root.children.remove(root.children.size() - 1);
            Node sec_last = root.children.get(root.children.size() - 1);
            Node tail = linear2(sec_last);
            tail.children.add(last);
        }
        return ltail;
    }

    public static boolean findelement(int element, Node node){  //Find element in a tree
        boolean ans = false;
        for (Node child : node.children){
            boolean temp = findelement(element, child);
            ans = ans || temp;
        }

        if (node.data == element){
            ans = true;
        }

        return ans;
    }

    public static ArrayList<Integer> nodetoRootPath(Node node, int data){ //node to root path
        if (node.data == data){
            ArrayList<Integer> list = new ArrayList<>();
            list.add(node.data);
            return list;
        }

        for(Node child: node.children){
            ArrayList<Integer> pathtillchild = nodetoRootPath(child, data);
            if (pathtillchild != null){
                pathtillchild.add(node.data);
                return pathtillchild;
            }
        }
        return null;
    }

    public static int lca (Node node, int data1, int data2){  //lowest common ancestor in a tree
        ArrayList<Integer> path1 = nodetoRootPath(node, data1);
        ArrayList<Integer> path2 = nodetoRootPath(node, data2);

        int i = path1.size() - 1, j = path2.size() - 1;

        while(i >= 0 && j>=0 && path1.get(i) == path2.get(j)){
            i--; j--;
        }

        i++; j++;

        int a= path1.get(i); //or return p2.get(j)
        return a;

    }

    public static int distancebetweennodes (Node node, int data1, int data2){  //distance between nodes
        ArrayList<Integer> path1 = nodetoRootPath(node, data1);
        ArrayList<Integer> path2 = nodetoRootPath(node, data2);

        int i = path1.size() - 1, j = path2.size() - 1;

        while(i >= 0 && j>=0 && path1.get(i) == path2.get(j)){
            i--; j--;
        }

        i++; j++;

        return i+j;

    }

    public static boolean similarTree(Node node1, Node node2){  //check for similar tree
        
        if (node1.children.size() != node2.children.size()){
            return false;
        }

        int i;
        for (i = 0; i < node1.children.size(); i++){
            Node a = node1.children.get(i);
            Node b = node2.children.get(i);
            
            if(similarTree (a,b) == false){
                return false;
            }
        }
        return true;
    }

    public static boolean areTreesMirrorInShape(Node node1, Node node2){  //check for trees mirror in shape
        
        if (node1.children.size() != node2.children.size()){
            return false;
        }

        int i;
        for (i = 0; i < node1.children.size(); i++){
            int j = node1.children.size() - 1 - i;
            Node a = node1.children.get(i);
            Node b = node2.children.get(j);

            if(areTreesMirrorInShape(a,b) == false){
                return false;
            }
        }
        return true;
    }

    public static boolean isSymmetric(Node node){  //Check is tree is symmetric
        return areTreesMirrorInShape(node, node);
    }

    // public static Node construct(int[] arr){  //used in multisolver thats why repeated
    //     Node root = null;
    //     Stack<Node> st= new Stack<>();  //create a stack

    //     for(int i=0; i<arr.length; i++){

    //         if(arr[i] == -1){  //If it is a leaf node
    //             st.pop();
    //         }

    //         else{
    //             Node t= new Node();
    //             t.data= arr[i];
                
    //             if(st.size() > 0){  //checking if there is a root already in the stack
    //                 st.peek().children.add(t);
    //             }

    //             else{
    //                 root=t;  //creating the root
    //             }

    //             st.push(t);  //Pushing the Node in the stack
    //         }
    //     }
    // }


    // public static void multisolver(Node node, int depth){

    //     size++;
    //     min = Math.min(min, node.data);
    //     max = Math.max(max, node.data);
    //     height = Math.max(height, depth);

    //     for (Node child : node.children){
    //         multisolver(child,depth+1);
    //     }
    // }

    public static void pas(Node node, int data){  //predecessor and sucessor

        if (var ==0){
            if(node.data == data){
                var = 1;
            }
            else {
                predecessor = node;
            }
        }

        else if(var == 1){
            successor = node;
            var++;
        }

        for(Node child : node.children){
            pas(child, data);
        }
    }

    public static void cnf(Node node, int data){  //ceil and floor

        if(node.data >= data){
            ceil = Math.min(ceil, node.data);
        }
        else{
            floor = Math.max(floor,node.data);
        }
        for(Node child : node.children){
            cnf(child, data);
        }
    }

    // public static int kthlargest(Node node, int k){  //kth largest element
    //     floor = Integer.MIN_VALUE;
    //     int factor = Integer.MAX_VALUE;

    //     for (int i = 0 ; i < k ; i++){
    //         cnf(node, factor);  //will set floor
    //         factor = floor;
    //         floor = Integer.MIN_VALUE;
    //     }

    //     return factor;
    // }

    static int maxSumSubTree(Node node){  //retrun Sum and maxSumSubTree
        int sum = 0;

        for (Node child : node.children){
            int csum=maxSumSubTree(child);
            sum += csum;
        }

        sum += node.data;

        if (sum > maximumSum){
            maximumSumNode = node.data;
            maximumSum = sum;
        }

        return sum;
    }
    
}
