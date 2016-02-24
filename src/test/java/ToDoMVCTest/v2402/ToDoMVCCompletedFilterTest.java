package ToDoMVCTest.v2402;

import org.junit.Test;
import org.openqa.selenium.Keys;

import static todomvctest.pages.ToDoMVC.TaskType.COMPLETED;
import static todomvctest.pages.ToDoMVC.*;

public class ToDoMVCCompletedFilterTest extends BaseTest {

    @Test
    public void testEditAtCompleted() {
        givenAtCompleted(aTask("a", COMPLETED),
                aTask("b", COMPLETED));

        startEditing("a", "a edited").sendKeys(Keys.ENTER);
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
