package com.midterm.todolist;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;


public class TodolistApplicationTests {

    @Test
    public void testSwitchCases() {
        // Simulating user inputs: 1 (Add Task) -> 2 (Remove Task) -> 3 (Mark Completed) -> 
        // 4 (View Tasks) -> 5 (Exit) -> 6 (Invalid Choice)
        String input = "1\nTest Task\n2\n1\n3\n1\n4\n5\n6\n"; 
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        // Capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Run the main method of the To-Do List Application
        TodolistApplication.main(new String[]{});

        // Convert captured output to string
        String output = outContent.toString();

        // Verify if all cases were executed
        assertTrue(output.contains("Task added successfully!"));  // Case 1: Add Task
        assertTrue(output.contains("Task removed!"));             // Case 2: Remove Task
        assertTrue(output.contains("Task marked as completed!")); // Case 3: Mark Completed
        assertTrue(output.contains("Your To-Do List:"));          // Case 4: View Tasks
        assertTrue(output.contains("Exiting... Have a great day!"));// Case 5: Exit
    }
}
