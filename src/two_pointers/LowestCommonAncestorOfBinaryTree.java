package two_pointers;

/*
You are given two nodes, p and q. The task is to return their lowest common ancestor (LCA). Both nodes have a reference to their parent node.
The tree’s root is not provided; you must use the parent pointers to find the nodes’ common ancestor.
Note: The lowest common ancestor of two nodes, p and q, is the lowest node in the binary tree, with both p and q as descendants.
In a tree, a descendant of a node is any node reachable by following edges downward from that node, including the node itself.

Constraints:
    > -10^4 <= Node.data <= 10^4
    > The number of nodes in the tree is in the range [2,500]
    > All Node.data are unique
    > p != q
    > Both p and q are present in the tree
 */

import java.util.*;

class BTNode {
    int data;
    BTNode left;
    BTNode right;
    BTNode parent;

    BTNode(int data, BTNode left, BTNode right, BTNode parent) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
}

class BinaryTree {
    BTNode root;

    public BTNode constructBinaryTree(List<Integer> list) {
        if(list.isEmpty()) {
            return null;
        }
        root = new BTNode(list.get(0), null, null, null);
        Queue<BTNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while(i < list.size()) {
                BTNode curr = queue.poll();
                curr.left = new BTNode(list.get(i), null, null, curr);
                queue.offer(curr.left);
                i++;
                if(i < list.size()) {
                    curr.right = new BTNode(list.get(i), null, null, curr);
                    queue.offer(curr.right);
                    i++;
                }
        }
        return root;
    }

    public BTNode findNode(BTNode root, int value){
        if(root == null) {
            return null;
        }
        Queue<BTNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            BTNode currNode = queue.poll();
            if(currNode.data == value) {
                return currNode;
            }

            if(currNode.left != null) {
                queue.offer(currNode.left);
            }

            if(currNode.right != null) {
                queue.offer(currNode.right);
            }
        }
        return null;
    }

    public BTNode getRootNode() {
        return root;
    }
}

public class LowestCommonAncestorOfBinaryTree {
    private static BTNode lowestCommonAncestorOfBinaryTreeMyMethod(BTNode p, BTNode q) {
        Set<Integer> ancestorSet = new HashSet<>();
        BTNode tempP = p;
        BTNode tempQ = q;
        while (true) {
            if (tempP == null && tempQ == null) {
                System.out.println("Lowest Common Ancestor not found !");
                return null;
            }
            if (tempP != null) {
                boolean result = ancestorSet.add(tempP.data);
                if (!result) {
                    System.out.println("Lowest Common Ancestor found !");
                    return tempP;
                }
                tempP = tempP.parent;
            }
            if (tempQ != null) {
                boolean result = ancestorSet.add(tempQ.data);
                if (!result) {
                    System.out.println("Lowest Common Ancestor found !");
                    return tempQ;
                }
                tempQ = tempQ.parent;
            }
        }
    }

    private static BTNode lowestCommonAncestorOfBinaryTreeTutorialMethod(BTNode p, BTNode q) {
            BTNode tempP = p;
            BTNode tempQ = q;

            if(tempP == null || tempQ == null) {
                return null;
            }

            while(true) {
                if(tempP == tempQ) {
                    return tempP;
                }
                if(tempP.parent == null) {
                    tempP = q;
                } else{
                    tempP = tempP.parent;
                }

                if(tempQ.parent == null) {
                    tempQ = p;
                } else{
                    tempQ = tempQ.parent;
                }
            }
    }

    public static void main(String[] args) {
        List<List<Integer>> list = Arrays.asList(
                Arrays.asList(100, 50, 200, 25, 75, 350),
                Arrays.asList(100, 200, 75, 50, 25, 350),
                Arrays.asList(350, 100, 75, 50, 200, 25),
                Arrays.asList(100, 50, 200, 25, 75, 350),
                Arrays.asList(25, 50, 75, 100, 200, 350)
        );

        List<List<Integer>> inputNodes = Arrays.asList(
                Arrays.asList(25, 75),
                Arrays.asList(50, 350),
                Arrays.asList(100, 200),
                Arrays.asList(50, 25),
                Arrays.asList(350, 200)
        );

        for(int i = 0; i < list.size(); i++) {
            BinaryTree binaryTree = new BinaryTree();
            binaryTree.constructBinaryTree(list.get(i));
            BTNode p = binaryTree.findNode(binaryTree.root, inputNodes.get(i).get(0));
            BTNode q = binaryTree.findNode(binaryTree.root, inputNodes.get(i).get(1));
            //BTNode lowestCommonAncestor = lowestCommonAncestorOfBinaryTreeMyMethod(p, q);
            BTNode lowestCommonAncestor = lowestCommonAncestorOfBinaryTreeTutorialMethod(p, q);
            if (lowestCommonAncestor != null) {
                System.out.println("Lowest common ancestor is node with value = " + lowestCommonAncestor.data);
            }
        }
    }
}
