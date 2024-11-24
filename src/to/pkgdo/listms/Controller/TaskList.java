/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package to.pkgdo.listms.Controller;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import to.pkgdo.listms.Model.Task;
/**
 *
 * @author USER
 */


public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int taskId) {
        tasks.removeIf(task -> task.getId() == taskId);
    }

    public void markTaskAsCompleted(int taskId) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                task.setCompleted(true);
                break;
            }
        }
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public List<Task> getCompletedTasks() {
        List<Task> completedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isCompleted()) {
                completedTasks.add(task);
            }
        }
        return completedTasks;
    }

    public List<Task> getTasksDueToday() {
        List<Task> tasksDueToday = new ArrayList<>();
        Date today = new Date();

        for (Task task : tasks) {
            if (task.getDueDate().equals(today)) {
                tasksDueToday.add(task);
            }
        }

        return tasksDueToday;
    }

    public void saveToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            outputStream.writeObject(tasks);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(String fileName) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
            tasks = (List<Task>) inputStream.readObject();
            inputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

