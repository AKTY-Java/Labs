package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Iterator;

class ToDoList implements IToDoList {
    ArrayList<Task> taskList = new ArrayList<Task>();
    Stack<ArrayList<Task>> history = new Stack<ArrayList<Task>>();

    int search(String id) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public void addTask(Task task) {
        taskList.add(task);
        history.push(taskList);
    }

    public void completeTask(String id) {
        int taskIndex;
        Task task;

        taskIndex = search(id);
        task = taskList.get(taskIndex);
        if (task.getId() == id) {
            task.setIsCompleted(true);
            history.push(taskList);
        }
    }

    public void deleteTask(String id) {
        int taskIndex;

        taskIndex = search(id);
        if (taskIndex != -1) {
            taskList.remove(taskIndex);
            history.push(taskList);
        }
    }

    public void editTask(String id, String newTitle, boolean newIsCompleted) {
        int taskIndex = search(id);
        Task task;

        if (taskIndex != -1) {
            task = taskList.get(taskIndex);
            task.setTitle(newTitle);
            task.setIsCompleted(newIsCompleted);
            history.push(taskList);
        }
    }

    public void undo() {
        if (!history.isEmpty()) {
            taskList = history.pop();
        }
    }

    public ArrayList<Task> listTasks() {
        return taskList;
    }
}
