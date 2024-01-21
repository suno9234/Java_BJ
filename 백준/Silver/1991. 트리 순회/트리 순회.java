
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        Tree tree = new Tree();
        for(int i = 0 ; i < n; i++){
            String nl = bufferedReader.readLine();
            Node nodeL = null;
            Node nodeR = null;
            Node nodeP;
            char parent = nl.charAt(0);
            char left = nl.charAt(2) ;
            char right = nl.charAt(4);
            if(left != '.'){
                nodeL = new Node(left,null,null);
            }
            if(right != '.'){
                nodeR = new Node(right, null,null);
            }
            nodeP = new Node(parent,nodeL,nodeR);
            tree.insertNode(nodeP);
        }
        tree.preOrder();
        System.out.println();
        tree.inOrder();
        System.out.println();
        tree.postOrder();

    }
}

class Tree{
    Node headNode;

    public void insertNode(Node node){
        if (headNode == null) {
            this.headNode = node;
        }else{
            insertNode(this.headNode,node);
        }
    }

    private void insertNode(Node headNode ,Node node){
        if(headNode.left != null) {
            if(headNode.left.value == node.value){
                headNode.left = node;
                return;
            }
            insertNode(headNode.left, node);
        }
        if(headNode.right != null) {
            if(headNode.right.value == node.value){
                headNode.right = node;
                return;
            }
            insertNode(headNode.right, node);
        }
    }

    public void preOrder(){
        preOrder(this.headNode);
    }
    public void inOrder(){
        inOrder(this.headNode);
    }
    public void postOrder(){
        postOrder(this.headNode);
    }
    public void preOrder(Node headNode){
        if(headNode== null){
            return ;
        }
        System.out.print(headNode.value);
        preOrder(headNode.left);
        preOrder(headNode.right);
    }
    public void inOrder(Node headNode){
        if(headNode == null){
            return;
        }
        inOrder(headNode.left);
        System.out.print(headNode.value);
        inOrder(headNode.right);
    }
    public void postOrder(Node headNode){
        if(headNode == null){
            return;
        }
        postOrder(headNode.left);
        postOrder(headNode.right);
        System.out.print(headNode.value);
    }
}

class Node{
    char value;
    Node left,right;
    public Node(char value,Node left,Node right){
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
