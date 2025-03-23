package com.midterm.todolist;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class ToDoListTests {
    private ToDoList toDoList;

    @BeforeEach
    void setUp() {
        toDoList = new ToDoList();
    }

    @Test
    void testAddTask() {
        toDoList.addTask("Buy milk");
        List<String> tasks = toDoList.getTasks();

        assertEquals(1, tasks.size());
        assertEquals("[ ] Buy milk", tasks.get(0));
    }

    @Test
    void testRemoveTask_ValidIndex() {
        toDoList.addTask("Go to gym");
        toDoList.addTask("Read book");

        toDoList.removeTask(0);
        List<String> tasks = toDoList.getTasks();

        assertEquals(1, tasks.size());
        assertEquals("[ ] Read book", tasks.get(0));
    }

    @Test
    void testRemoveTask_InvalidIndex() {
        toDoList.addTask("Workout");
        toDoList.removeTask(5); // Invalid index

        List<String> tasks = toDoList.getTasks();
        assertEquals(1, tasks.size());
    }

    @Test
    void testCompleteTask_ValidIndex() {
        toDoList.addTask("Finish project");
        toDoList.completeTask(0);

        List<String> tasks = toDoList.getTasks();
        assertEquals("[✔] Finish project", tasks.get(0));
    }

    @Test
    void testCompleteTask_InvalidIndex() {
        toDoList.addTask("Clean room");
        toDoList.completeTask(3); // Invalid index

        List<String> tasks = toDoList.getTasks();
        assertEquals("[ ] Clean room", tasks.get(0));
    }

    @Test
    void testViewTasks() {
        toDoList.addTask("Write report");
        toDoList.addTask("Attend meeting");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        toDoList.viewTasks();
        String output = outContent.toString().trim();

        assertTrue(output.contains("0. [ ] Write report"));
        assertTrue(output.contains("1. [ ] Attend meeting"));

        System.setOut(System.out);
    }
}
