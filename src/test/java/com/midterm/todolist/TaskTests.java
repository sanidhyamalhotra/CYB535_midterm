package com.midterm.todolist;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskTests {
    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task("Finish homework");
    }

    @Test
    void testTaskCreation() {
        assertEquals("Finish homework", task.getDescription());
        assertFalse(task.isCompleted());
    }

    @Test
    void testMarkTaskAsComplete() {
        task.markComplete();
        assertTrue(task.isCompleted());
    }

    @Test
    void testTaskToStringBeforeCompletion() {
        assertEquals("[ ] Finish homework", task.toString());
    }

    @Test
    void testTaskToStringAfterCompletion() {
        task.markComplete();
        assertEquals("[✔] Finish homework", task.toString());
    }
}
