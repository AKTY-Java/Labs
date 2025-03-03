package edu.ucalgary.oop;

class ToDoList implements IToDoList {
    List<Task> taskList = new List<Task>();
    Stack<List<Task>> history = new Stack<List<Task>>();

    search(String id) {
        for (int i; i < taskList.size(); i++) {
            if (taskList.get(i).getID == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    add(Task task) {
        taskList.add(task);
    }

    @Override
    completeTask(String id) {
        int taskIndex;
        Task task;
        taskIndex = search(id);
        task = taskList.get(taskIndex);
        if (task == id) {
            task.setIsCompleted(true);
        }
    }

    @Override
    deleteTask(String id) {
        int taskIndex;
        taskIndex = search(id);
        if (taskIndex != -1) {
            taskList.remove(taskIndex);
        }
    }

    @Override
    editTask(String id, String newTitle, boolean newIsCompleted) {
        int taskIndex = search(id);
        Task task;
        if (taskIndex != -1) {
            task = taskList.get(taskIndex);
            task.setTitle(newTitle);
            task.setIsCompleted(newIsCompleted);
        }
    }

    @Override
    undoTask() {
        
    }

    @Override
    listTask() {
        return taskList;
    }
}