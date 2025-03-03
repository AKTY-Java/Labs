package edu.ucalgary.oop.edu.ucalgary.oop;

import edu.ucalgary.oop.Task;

import java.util.List;

public interface IToDoList{
    void addTask(Task task);
    void completeTask(String id);
    void deleteTask(String id);
    void editTask(String id, String newTitle, boolean newIsCompleted);
    void undo();
    List<Task> listTasks();



}