package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ToDoList implements IToDoList {
    private List<Task> tasks;
    private Stack<List<Task>> history;

    public ToDoList() {
        tasks = new ArrayList<>();
        history = new Stack<>();
    }

    private void pushCurrentStateToHistory() {
        List<Task> tasksCopy = new ArrayList<>();
        for (Task t : tasks) {
            tasksCopy.add(t.copy());
        }
        history.push(tasksCopy);
    }

    @Override
    public void addTask(Task task) {
        pushCurrentStateToHistory();
        tasks.add(task);
    }

    @Override
    public void completeTask(String id) {
        pushCurrentStateToHistory();
        for (Task t : tasks) {
            if (t.getId().equals(id)) {
                t.setCompleted(true);
                break;
            }
        }
    }

    @Override
    public void deleteTask(String id) {
        pushCurrentStateToHistory();
        tasks.removeIf(t -> t.getId().equals(id));
    }

    @Override
    public void editTask(String id, String newTitle, boolean newIsCompleted) {
        pushCurrentStateToHistory();
        for (Task t : tasks) {
            if (t.getId().equals(id)) {
                t.setTitle(newTitle);
                t.setCompleted(newIsCompleted);
                break;
            }
        }
    }

    @Override
    public void undo() {
        if (!history.isEmpty()) {
            tasks = history.pop();
        }
    }

    @Override
    public List<Task> listTasks() {
        return tasks;
    }
}
