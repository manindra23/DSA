package heaps;

/*
We are given an input array, tasks, where tasks[i] = [start_i, end_i] represent the start and end times of n tasks. Our goal is to schedule
these tasks on machines given the following criteria:
+A machine can execute only one task at a time.
+A machine can begin executing a new task immediately after completing the previous one.
+An unlimited number of machines are available.
Find the minimum number of machines required to complete these n tasks.

Constraints:
    +n == tasks.length
    +1 <= tasks.length <= 10^3
    +0<= tasks_i.start < tasks_i.end <= 10^4
 */

import java.util.*;

public class ScheduleTasksOnMinimumMachines {
    static class Task {
        private int start;
        private int end;

        public Task(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return this.start;
        }

        public int getEnd() {
            return this.end;
        }

        public String toString() {
            return "{" + start + ", " + end + "}";
        }
    }

    private static PriorityQueue<Task> minHeap1 = new PriorityQueue<>(Comparator.comparingInt(Task::getStart));
    private static PriorityQueue<Task> minHeap2 = new PriorityQueue<>(Comparator.comparingInt(Task::getEnd));

    private static List<List<Task>> scheduleTasksOnMinimumMachinesNaiveMethod(List<Task> tasks) {
        for(Task task: tasks) {
            minHeap1.offer(task);
        }

        List<List<Task>> machines = new ArrayList<>();

        while(!minHeap1.isEmpty()) {
            int numberOfMachines = machines.size();
            int i = 0;
            while(true) {
                //if no machines assigned yet
                if(machines.isEmpty()) {
                    List<Task> machine = new ArrayList<>();
                    Task initialTask = minHeap1.poll();
                    machine.add(initialTask);
                    machines.add(machine);
                    break;
                }


                if(i<= numberOfMachines-1 && machines.get(i).get(machines.get(i).size()-1).getEnd() <= minHeap1.peek().getStart()) {
                    machines.get(i).add(minHeap1.poll());
                    break;
                }

                if(i > numberOfMachines-1) {
                    List<Task> machine = new ArrayList<>();
                    Task task = minHeap1.poll();
                    machine.add(task);
                    machines.add(machine);
                    break;
                }

                i++;
            }
        }
        return machines;
    }

    private static int scheduleTasksOnMinimumMachinesOptimumMethod(List<Task> tasks) {
        //sorting tasks in ascending order by their start time
        tasks.sort(Comparator.comparingInt(Task::getStart));

        for(Task task: tasks) {
            if(!minHeap2.isEmpty() && minHeap2.peek().getEnd() <= task.getStart()) {
                minHeap2.poll();
            }

            minHeap2.offer(task);
        }

        return minHeap2.size();
    }

    public static void main(String[] args) {
        List<Task> tasks1 = Arrays.asList(new Task(1,1), new Task(5,5), new Task(8,8), new Task(4,4), new Task(6,6), new Task(10,10), new Task(7,7));
        List<Task> tasks2 = Arrays.asList(new Task(1, 7), new Task(1, 7), new Task(1, 7), new Task(1, 7), new Task(1, 7), new Task(1, 7));
        List<Task> tasks3 = Arrays.asList(new Task(1, 7), new Task(8, 13), new Task(5, 6), new Task(10, 14), new Task(6, 7));
        List<Task> tasks4 = Arrays.asList(new Task(1, 3), new Task(3, 5), new Task(5, 9), new Task(9, 12), new Task(12, 13), new Task(13, 16), new Task(16, 17));
        List<Task> tasks5 = Arrays.asList(new Task(12, 13), new Task(13, 15), new Task(17, 20), new Task(13, 14), new Task(19, 21), new Task(18, 20));


        /*List<List<Task>> machines = scheduleTasksOnMinimumMachines(tasks5);
        System.out.println("Number of machine required:" + machines.size());
        for(List<Task> machine: machines) {
            System.out.println(machine);
        }*/
        int numberOfMachines = scheduleTasksOnMinimumMachinesOptimumMethod(tasks2);
        System.out.println("Number of machine required:" + numberOfMachines);

    }
}
