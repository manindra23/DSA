package two_pointers;

/*
-Given the head of a singly linked list and an integer n, remove the nth node from the end of the list and return the head of the modified list
-Constraints:
    + the number of nodes in the list is k
    + 1 <= k <= 10^3
    + -10^3 <= node.value <= 10^3
    + 1<=n<=k

 */

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

class SinglyLinkedList {
    Node head;

    SinglyLinkedList(Node head) {
        this.head = head;
    }

    Node removeNthNodeFromEndNaiveMethod(Node head, int n) {
        if(n <= 0) {
            System.out.println("Invalid value for node position");
            return null;
        }
        Node temp = head;
        int k = 0;
        while (temp != null) {
            k++;
            temp = temp.next;
        }

        int index = 0;
        temp = head;
        while (temp != null) {
            if((k - n - 1) < -1) {
                System.out.println("Invalid value for node. Total number of nodes are only " + k);
                break;
            }
            if ((k - n - 1) == -1) {
                //head node removal case
                Node toBeRemoved = head;
                head = head.next;
                toBeRemoved.next = null;
                System.out.println("Head Node with value = " + toBeRemoved.data + " deleted");
                break;
            }
            if (index == (k - n - 1)) {
                Node toBeRemoved = temp.next;
                temp.next = temp.next.next;
                toBeRemoved.next = null;
                System.out.println("Node with value = " + toBeRemoved.data + " deleted");
                break;
            }
            temp = temp.next;
            index++;
        }
        return head;
    }

    Node removeNthNodeFromEndOptimumMethod(Node head, int n) {
        if(n <= 0) {
            System.out.println("Invalid value for node position");
            return null;
        }

        Node left = head, right = head;
        //move right pointer n steps forward
        for(int i = 0; i<n; i++) {
            right = right.next;
            //case of n being more than number of nodes in linked list
            if(right == null && i == n-2) {
                System.out.println("Invalid value for node position; exceeding number of nodes");
                return null;
            }
        }
        //right pointer reached null in linked list meaning that it is the case of head node removal.
        if(right == null) {
            Node toBeRemoved = head;
            head = head.next;
            toBeRemoved.next = null;
            System.out.println("Head node removal case - node with value:" + toBeRemoved.data + " removed");
            return head;
        }

        //move both left and right by 1 step until right pointer is at the last node
        while(right.next != null) {
            left = left.next;
            right = right.next;
            System.out.println("left:" + left.data + ", right:" + right.data);
        }

        //remove the target node
        Node toBeRemoved = left.next;
        left.next = left.next.next;
        toBeRemoved.next = null;
        System.out.println("Node with value = " + toBeRemoved.data + " deleted");
        return head;
    }
}

public class RemoveNthNodeFromEnd {
    public static void main(String[] args) {
        //Populating singly linked list
        Node head = new Node(1);
        Node node2 = new Node(4);
        head.next = node2;
        Node node3 = new Node(10);
        node2.next = node3;
        Node node4 = new Node(20);
        node3.next = node4;
        Node node5 = new Node(9);
        node4.next = node5;
        Node node6 = new Node(7);
        node5.next = node6;
        Node node7 = new Node(5);
        node6.next = node7;
        node7.next = null;
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList(head);
        //Run only one of these methods at a time as both are using same head variable and execution of one may affect the other one.
        //singlyLinkedList.removeNthNodeFromEndNaiveMethod(head, 4);
        singlyLinkedList.removeNthNodeFromEndOptimumMethod(head, 4);
    }
}
