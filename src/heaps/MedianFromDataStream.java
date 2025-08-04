package heaps;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
Design a data structure that stores a dynamically changing list of integers and can find the median in constant time O(1), as the list grows.
To do that, implement a class named MedianOfStream with the following functionality:
    +Constructor(): Initializes an instance of the class.
    +insertNum(int num): Adds a new integer num to the data structure.
    +findMedian(): Returns the median of all integers added so far.

Note: The median is the middle value in a sorted list of integers.
    +For an odd-sized list eg [4,5, 6], the median is the middle element: 5
    +For an even-sized list eg [2,4,6,8], the median is the average of the two middle elements (4+6)/2 = 5

Constraints:
    + -10^5<=num<=10^5 where num is the integer received from data stream
    + There will be at least one element in the data structure before the median is computed.
    + At most, 500 calls will be made to the function that calculates the median.
 */
class Median {
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    Median () {
    }

    void insertNum(int num) {
        if(minHeap.isEmpty() && maxHeap.isEmpty()) {
            maxHeap.add(num);
        } else {
            if(!maxHeap.isEmpty()) {
                if(num <= maxHeap.peek()) {
                    maxHeap.add(num);
                } else {
                    minHeap.add(num);
                }
            } else {
                if(num >= minHeap.peek()) {
                    minHeap.add(num);
                } else {
                    maxHeap.add(num);
                }
            }
        }

        //move elements from minheap to maxheap and vice-versa if size difference is more than 1 between them
        if((minHeap.size() - maxHeap.size()) >=2) {
            int diff = minHeap.size() - maxHeap.size();
            while(diff >= 2) {
                maxHeap.add(minHeap.poll());
                diff--;
            }
        }

        if((maxHeap.size() - minHeap.size()) >=2) {
            int diff = maxHeap.size() - minHeap.size();
            while(diff >= 2) {
                minHeap.add(maxHeap.poll());
                diff--;
            }
        }
    }

    double findMedian() {
        if(!minHeap.isEmpty() && !maxHeap.isEmpty()) {
            if(maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            } else if((minHeap.size() > maxHeap.size())) {
                return minHeap.peek();
            } else {
                return (minHeap.peek() + maxHeap.peek()) / 2.0;
            }
        }

        if(minHeap.isEmpty() && !maxHeap.isEmpty()) {
            return maxHeap.peek();
        } else {
            return minHeap.peek();
        }
    }


    //Optimized methods for insertNum and findMedian
    void insertNumOptimized(int num) {
        if(maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        if(maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    double findMedianOptimized() {
        //maxHeap will always be greater than or equal to minHeap size because re-balancing occurs at each insertion
        //hence, we return root element of maxHeap for else case
        if(!maxHeap.isEmpty() && maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek())/2.0;
        } else {
            return !maxHeap.isEmpty()?maxHeap.peek(): 0.0;
        }
    }
}

public class MedianFromDataStream {
    public static void main(String[] args) {
        int[] nums = {35, 22, 30, 25, 1};
        Median median = null;
        for (int i = 0; i < nums.length; i++) {
            System.out.print(i + 1);
            System.out.print(".\tData stream: [");
            median = new Median();
            for (int j = 0; j <= i; j++) {
                System.out.print(nums[j]);
                if (j != i)
                    System.out.print(", ");
                median.insertNumOptimized(nums[j]);
            }
            System.out.println("]");
            System.out.println("\tThe median for the given numbers is: " + median.findMedianOptimized());
            System.out.println("---------------------------------------------------------------");
        }
    }
}


