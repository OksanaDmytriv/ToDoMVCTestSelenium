package ToDoMVCTest.v2102;

import org.junit.Test;
import org.openqa.selenium.Keys;
import todomvctest.core.BaseTest;

import static todomvctest.core.pages.ToDoMVC.TaskType.COMPLETED;
import static todomvctest.core.pages.ToDoMVC.*;

public class ToDoMVCCompletedFilterTest extends BaseTest {

    @Test
    public void testEditAtCompleted() {
        givenAtCompleted(aTask("a", COMPLETED),
                aTask("b", COMPLETED));

        startEditing("a", "a edited").pressEnter();
        assertTasks("a edited", "b");
        assertItemsLeft(0);
    }

    @Test
    public void testCancelEditAtCompleted() {
        givenAtCompleted(aTask("a", COMPLETED));

        startEditing("a", "a edited").sendKeys(Keys.ESCAPE);
        assertTasks("a");
        assertItemsLeft(0);
    }

    @Test
    public void testActivateAllAtCompleted() {
        givenAtCompleted(aTask("a", COMPLETED),
                aTask("b", COMPLETED));

        toggleAll();
        assertEmptyVisibleTasks();
        assertItemsLeft(2);
    }

    @Test
    public void testDeleteAtCompleted() {
        givenAtCompleted(aTask("a", COMPLETED),
                aTask("b", COMPLETED));

        delete("a");
        assertTasks("b");
        assertItemsLeft(0);
    }

    @Test
    public void testClearCompletedAtCompleted() {
        givenAtCompleted(aTask("a", COMPLETED),
                aTask("b", COMPLETED));

        assertItemsLeft(0);
        сlearCompleted();
        assertEmptyVisibleTasks();
    }


}
