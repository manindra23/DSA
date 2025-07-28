package fast_and_slow_pointers;

import java.util.*;

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    Node head;
    Map<Node, Node> intersectionMap;
    Node intersectionNode;

    public LinkedList() {
        this.head = null;
        this.intersectionNode = null;
        intersectionMap = new HashMap<>();
    }

    public Node prepareLinkedList(List<Integer> valueList, int pos) {
        if(valueList.isEmpty()) {
            return null;
        }

        Node prev = null;
        for(int i = 0; i< valueList.size(); i++) {
            Node newNode = new Node(valueList.get(i));
            if(i == pos) {
                intersectionNode = newNode;
            }
            if (i == 0) {
                head = newNode;
                prev = head;
            } else {
                prev.next = newNode;
                prev = prev.next;
                if (i == valueList.size() - 1 && pos != -1) {
                    newNode.next = intersectionNode;
                    intersectionMap.put(newNode, intersectionNode);
                }
            }
        }

        return head;
    }
}

public class LinkedListCycle {
    /*
    Given the head of a linked list, determine whether the list contains a cycle. A cycle exists if a node in the list can be revisited by
    continuously following the next pointers. Return TRUE if a cycle is present; otherwise, return FALSE.

    Constraints:
        Let n be the number of nodes in a linked list.
            + 0<=n<=500
            + -10^5<=n<=10^5


     */

    public static boolean linkedListCycle(Node head) {
        Node slow = head, fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
    List<List<Integer>> list = Arrays.asList(
            Arrays.asList(2, 4, 6, 8, 10, 12),
            Arrays.asList(1, 3, 5, 7, 9, 11),
            Arrays.asList(0, 1, 2, 3, 4, 6),
            Arrays.asList(3, 4, 7, 9, 11, 17),
            Arrays.asList(5, 1, 4, 9, 2, 3)
    );

    int[] pos = {0, -1, 1, -1, 2 };

    for(int i = 0; i< list.size(); i++) {
        LinkedList newLinkedList = new LinkedList();
        Node head = newLinkedList.prepareLinkedList(list.get(i), pos[i]);
        Node temp = head;

        //Displaying the linked list
        while(temp != null) {
            if(temp.next == null) {
                System.out.println(temp.data);
            } else if (temp.next == newLinkedList.intersectionMap.get(temp)) {
                System.out.println(temp.data + " loops back to ->" + temp.next.data);
                break;
            } else {
                System.out.print(temp.data + "->");
            }
            temp = temp.next;
        }
        //Detecting cycle in the linkedlist
        System.out.println("Does linkedlist have cycle?:" + linkedListCycle(head));
    }

    }
}
