package ToDoMVCTest.v2102;

import org.junit.Test;
import org.openqa.selenium.Keys;
import todomvctest.core.BaseTest;

import static todomvctest.core.pages.ToDoMVC.TaskType.ACTIVE;
import static todomvctest.core.pages.ToDoMVC.TaskType.COMPLETED;
import static todomvctest.core.pages.ToDoMVC.*;

public class ToDoMVCActiveFilterTest extends BaseTest {

    @Test
    public void testAddAtActive() {
        givenAtActive(aTask("a", ACTIVE));

        assertTasks("a");
        assertItemsLeft(1);
    }

    @Test
    public void testCompeleteAtActive() {
        givenAtActive(aTask("a", ACTIVE));

        toggle("a");
        assertEmptyVisibleTasks();
        assertItemsLeft(0);
    }

    @Test
    public void testCompleteAllAtActive() {
        givenAtActive("a", "b");

        toggleAll();
        assertEmptyVisibleTasks();
        assertItemsLeft(0);
    }

    @Test
    public void testEditAtActive() {
        givenAtActive("a", "b");

        startEditing("a", "a edited").pressEnter();
        assertTasks("a edited", "b");
        assertItemsLeft(2);
    }

    @Test
    public void testCancelEditAtActive() {
        givenAtActive("a", "b");

        startEditing("b", "b edited").sendKeys(Keys.ESCAPE);
        assertTasks("a", "b");
        assertItemsLeft(2);
    }

    @Test
    public void testDeleteWhileEditingAtActive() {
        givenAtActive(aTask("a", ACTIVE));

        startEditing("a", " ").pressEnter();
        assertEmptyVisibleTasks();
    }

    @Test
    public void testClearCompletedAtActive() {
        givenAtActive(aTask("a", COMPLETED));

        сlearCompleted();
        assertEmptyVisibleTasks();
    }

    @Test
    public void testDeleteAtActive() {
        givenAtActive(aTask("a", ACTIVE));

        delete("a");
        assertEmptyVisibleTasks();
    }
}
