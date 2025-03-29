package com.midterm.todolist;

import java.util.Scanner;

public class TodolistApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ToDoList toDoList = new ToDoList();

        try {
            while (true) {
                System.out.println("\n=================================");
                System.out.println("         TO-DO LIST MENU         ");
                System.out.println("=================================");
                System.out.println("1. Add Task");
                System.out.println("2. Remove Task");
                System.out.println("3. Complete Task");
                System.out.println("4. View Tasks");
                System.out.println("5. Exit");
                System.out.println("=================================");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("\nEnter task: ");
                        toDoList.addTask(scanner.nextLine());
                        System.out.println("Task added successfully!");
                        break;
                    case 2:
                        System.out.print("\nEnter task number to remove: ");
                        toDoList.removeTask(scanner.nextInt());
                        System.out.println("Task removed!");
                        break;
                    case 3:
                        System.out.print("\nEnter task number to mark as completed: ");
                        toDoList.completeTask(scanner.nextInt());
                        System.out.println("Task marked as completed!");
                        break;
                    case 4:
                        System.out.println("\nYour To-Do List:");
                        toDoList.viewTasks();
                        break;
                    case 5:
                        System.out.println("\nExiting... Have a great day!");
                        return;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            }
        } finally {
            scanner.close();
        }
    }
}