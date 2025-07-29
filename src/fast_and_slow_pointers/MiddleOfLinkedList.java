package fast_and_slow_pointers;

/*
Given the head of a singly linked list, return the middle node of the linked list. If the number of nodes in the linked list is even,
there will be two middle nodes, so return the second one.

Constraints:
    Let n be the number of nodes in a linked list.
    + 1<=n<=100
    + 1<=Node.value<=100
    + head != NULL

 */

import java.util.Arrays;
import java.util.List;

class LinkedList2 {
    public class ListNode {
        int data;
        ListNode next;

        public ListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    ListNode head;

    public LinkedList2() {
        head =  null;
    }

    public ListNode prepareLinkedList(List<Integer> valueList) {
        head = new ListNode(valueList.get(0));
        ListNode temp = head;

        for(int i = 1; i < valueList.size(); i++) {
            temp.next = new ListNode(valueList.get(i));
            temp = temp.next;
        }
        return head;
    }
}

public class MiddleOfLinkedList {
    public static LinkedList2.ListNode middleOfLinkedList(LinkedList2.ListNode head) {
        LinkedList2.ListNode slow = head, fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    public static void main(String[] args) {
        List<List<Integer>> inputs = Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5),
                Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(3, 2, 1),
                Arrays.asList(10),
                Arrays.asList(1, 2)
        );

        for(List<Integer> list: inputs) {
            LinkedList2.ListNode head = new LinkedList2().prepareLinkedList(list);
            System.out.println("Given list:" + list);
            LinkedList2.ListNode middle = middleOfLinkedList(head);
            System.out.println("Middle of the linkedlist=" + middle.data);
        }
    }
}
