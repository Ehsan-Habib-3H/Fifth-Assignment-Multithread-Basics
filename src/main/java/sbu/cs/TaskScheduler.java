package sbu.cs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskScheduler {
    public static class Task implements Runnable {
        /*
            ------------------------- You don't need to modify this part of the code -------------------------
         */ String taskName;
        public int processingTime;

        public Task(String taskName, int processingTime) {
            this.taskName = taskName;
            this.processingTime = processingTime;
        }
        /*
            ------------------------- You don't need to modify this part of the code -------------------------
         */

        @Override
        public void run() {
            try {
                Thread.sleep(processingTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static ArrayList<String> doTasks(ArrayList<Task> tasks) {
        ArrayList<String> finishedTasks = new ArrayList<>();
        Collections.sort(tasks, (t1, t2) -> Integer.compare(t1.processingTime, t2.processingTime));
        for (Task task : tasks) {
            Thread thread = new Thread(task);
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            finishedTasks.add(task.taskName);
        }
        return finishedTasks;
    }

    public static void main(String[] args) {
        // Test your code here
    }
}
