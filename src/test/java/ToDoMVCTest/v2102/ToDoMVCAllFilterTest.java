package ToDoMVCTest.v2102;

import org.junit.Test;
import org.openqa.selenium.Keys;
import todomvctest.core.BaseTest;

import static todomvctest.core.pages.ToDoMVC.TaskType.ACTIVE;
import static todomvctest.core.pages.ToDoMVC.TaskType.COMPLETED;
import static todomvctest.core.pages.ToDoMVC.*;

public class ToDoMVCAllFilterTest extends BaseTest {

    @Test
    public void testEditAtAll() {
        givenAtAll(aTask("a", COMPLETED));

        startEditing("a", "a edited").pressEnter();
        assertTasks("a edited");
        assertItemsLeft(0);
    }

    @Test
    public void testCancelEditAtAll() {
        givenAtAll(aTask("a", COMPLETED));

        startEditing("a", "a edited").sendKeys(Keys.ESCAPE);
        assertTasks("a");
        assertItemsLeft(0);
    }

    @Test
    public void testEditAndClickOutsideAtAll() {
        givenAtAll("a", "b");

        startEditing("a", "a edited");
        newTask.click();
        assertTasks("a edited", "b");
        assertItemsLeft(2);
    }

    @Test
    public void testCompleteAtAll() {
        givenAtAll(aTask("a", ACTIVE),
                aTask("b", COMPLETED));

        assertItemsLeft(1);
        toggle("a");
        assertItemsLeft(0);
    }

    @Test
    public void testClearCompletedAtAll() {
        givenAtAll(aTask("a", COMPLETED),
                aTask("b", COMPLETED));

        assertItemsLeft(0);
        сlearCompleted();
        assertEmptyVisibleTasks();
    }

    @Test
    public void testActivateAllAtAll() {
        givenAtAll(aTask("a", COMPLETED));

        assertItemsLeft(0);
        toggle("a");
        assertItemsLeft(1);
    }

    @Test
    public void testActivateTasksAtAll() {
        givenAtAll(aTask("a", COMPLETED),
                aTask("b", COMPLETED));

        assertItemsLeft(0);
        toggleAll();
        assertItemsLeft(2);
    }

}
