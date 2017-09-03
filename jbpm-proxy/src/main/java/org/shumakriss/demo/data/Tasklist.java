package org.shumakriss.demo.data;

import java.util.List;

/**
 * Created by chris on 9/1/17.
 */
public class Tasklist {
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    private List<Task> tasks;

    @Override
    public String toString() {
        return "Tasklist{" +
                "tasks=" + tasks +
                '}';
    }
}
