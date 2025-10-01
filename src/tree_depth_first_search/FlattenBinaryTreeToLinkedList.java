package tree_depth_first_search;

import java.util.*;

/*
Given the root of a binary tree, the task is to flatten the tree into a linked list using the same TreeNode class.
The left child pointer of each node in the linked list should always be NULL, and the right child pointer should point
to the next node in the linked list. The nodes in the linked list should be in the same order as that of the preorder
traversal of the given binary tree.

Constraints:
    + -100 <= Node.data <=100
    + The tree contains node in the range [1, 500]
 */

class BinaryTree {
    public static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        TreeNode(int data, TreeNode left, TreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    public TreeNode insert(int data) {
        TreeNode newTreeNode = new TreeNode(data, null, null);
        if(root  == null) {
            root = newTreeNode;
        } else {
            TreeNode temp = root;
            TreeNode prev;

            //assuming there will be no duplicate values in binary tree
            while(temp != null) {
                prev = temp;
                if (data < temp.data) {
                    temp = temp.left;
                    if(temp == null) {
                        prev.left = newTreeNode;
                    }
                } else {
                    temp = temp.right;
                    if(temp == null) {
                        prev.right = newTreeNode;
                    }
                }
            }
        }
        return root;
    }

    //create binary tree (not BST) as done in educative solution
    public TreeNode createBinaryTree(List<TreeNode> nodeList) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        if(nodeList.isEmpty()) {
            return null;
        }
        root = nodeList.get(0);
            queue.offer(root);

        int i = 1;
        while(i < nodeList.size()) {
            TreeNode currNode = queue.poll();
            TreeNode newNode = nodeList.get(i);
            currNode.left = newNode;
            if(newNode != null) {
                queue.offer(newNode);
            }

            i++;
            if(i<nodeList.size()) {
                newNode = nodeList.get(i);
                currNode.right = newNode;
                if(newNode != null) {
                    queue.offer(newNode);
                }
            }

            i++;
        }
        return root;
    }

    public TreeNode flattenTree(TreeNode root) {
        if(root == null) {
            return null;
        }

        TreeNode currNode = root;

        while(currNode != null) {
            if(currNode.left != null) {
                TreeNode last = currNode.left;
                while(last.right != null) {
                    last = last.right;
                }
                last.right = currNode.right;
                currNode.right = currNode.left;
                currNode.left = null;
            }
            currNode = currNode.right;
        }
        return root;
    }

    public void preOrderTraversal(TreeNode root) {
        if(root != null) {
            System.out.println(root.data);
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }

    public void inOrderTraversal(TreeNode root) {
        if(root != null) {
            inOrderTraversal(root.left);
            System.out.println(root.data);
            inOrderTraversal(root.right);
        }
    }

    public void postOrderTraversal(TreeNode root) {
        if(root != null) {
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.println(root.data);
        }
    }

    public String toString() {
        return String.valueOf(root.data);
    }
}


//**find why null pointer exception is coming
public class FlattenBinaryTreeToLinkedList {
    public static void main(String[] args) {

        // Create a list of list of TreeNode objects to represent binary trees
        List<List<BinaryTree.TreeNode>> listOfTrees = Arrays.asList(
                Arrays.asList(new BinaryTree.TreeNode(3), new BinaryTree.TreeNode(2), new BinaryTree.TreeNode(17), new BinaryTree.TreeNode(1), new BinaryTree.TreeNode(4), new BinaryTree.TreeNode(19), new BinaryTree.TreeNode(5)),
                Arrays.asList(new BinaryTree.TreeNode(7), new BinaryTree.TreeNode(6), new BinaryTree.TreeNode(5), new BinaryTree.TreeNode(4), new BinaryTree.TreeNode(3), new BinaryTree.TreeNode(2), null, new BinaryTree.TreeNode(1)),
                Arrays.asList(new BinaryTree.TreeNode(5), new BinaryTree.TreeNode(4), new BinaryTree.TreeNode(6), new BinaryTree.TreeNode(3), new BinaryTree.TreeNode(2), new BinaryTree.TreeNode(7), new BinaryTree.TreeNode(8), new BinaryTree.TreeNode(1), new BinaryTree.TreeNode(9)),
                Arrays.asList(new BinaryTree.TreeNode(5), new BinaryTree.TreeNode(2), new BinaryTree.TreeNode(1), new BinaryTree.TreeNode(6), new BinaryTree.TreeNode(10), new BinaryTree.TreeNode(11), new BinaryTree.TreeNode(44)),
                Arrays.asList(new BinaryTree.TreeNode(1), new BinaryTree.TreeNode(2), new BinaryTree.TreeNode(5), new BinaryTree.TreeNode(3), new BinaryTree.TreeNode(4), new BinaryTree.TreeNode(6)),
                Arrays.asList(new BinaryTree.TreeNode(-1), new BinaryTree.TreeNode(-2), null, new BinaryTree.TreeNode(-5), new BinaryTree.TreeNode(1), new BinaryTree.TreeNode(2), null, new BinaryTree.TreeNode(-6))
        );

        // Create the binary trees using the BinaryTree class
        List<BinaryTree> inputTrees = new ArrayList<BinaryTree>();
        for (int i =0; i < listOfTrees.size(); i++) {
            BinaryTree tree = new BinaryTree();
            System.out.println("Tree number :" + i);
            tree.createBinaryTree(listOfTrees.get(i));
            inputTrees.add(tree);
        }

        for (BinaryTree tree : inputTrees) {
            System.out.println("PreOrderTraversal");
            tree.preOrderTraversal(tree.getRoot());
            System.out.println("InOrderTraversal");
            tree.inOrderTraversal(tree.getRoot());
            System.out.println("PostOrderTraversal");
            tree.postOrderTraversal(tree.getRoot());
            System.out.println("\n\tFlattened tree:" + tree.flattenTree(tree.getRoot()));
        }
    }
}
