package br.ce.wcaquino.taskbackend.controller;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

public class TaskControllerTest {

    @Mock
    private TaskRepo taskRepo;

    @InjectMocks
    private TaskController controller;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void cantSaveTasksWithoutDescription() {

        try {
            Task task = new Task();
            //task.setTask("Description");
            task.setDueDate(LocalDate.now());

            controller.save(task);
        } catch (ValidationException e) {
            Assert.assertEquals("Fill the task description", e.getMessage());
        }
    }

    @Test
    public void cantSaveTasksWithoutDate() {
        try {
            Task task = new Task();
            task.setTask("Description");
            //task.setDueDate(LocalDate.now());
            controller.save(task);
        } catch (ValidationException e) {
            Assert.assertEquals("Fill the due date", e.getMessage());
        }
    }

    @Test
    public void cantSaveTasksWithPasteDate() {
        try {
            Task task = new Task();
            task.setTask("Description");
            task.setDueDate(LocalDate.of(2010, 01, 01));
            controller.save(task);
        } catch (ValidationException e) {
            Assert.assertEquals("Due date must not be in past", e.getMessage());
        }
    }

    @Test
    public void saveTasksWithSuccess() throws ValidationException {

        Task task = new Task();
        task.setTask("Description");
        task.setDueDate(LocalDate.now());
        controller.save(task);

    }
}
