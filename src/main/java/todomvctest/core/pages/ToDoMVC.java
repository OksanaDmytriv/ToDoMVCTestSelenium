package todomvctest.core.pages;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

public class ToDoMVC {

    //static WebDriver driver;
    //static WebDriverWait wait;
    static Actions actions;

    @BeforeClass
    public static void setUp() {
        //driver = new FirefoxDriver();
        actions = new Actions(getWebDriver());
        //wait = new WebDriverWait(driver, 6);
    }

    public static ElementsCollection tasks = $$("#todo-list li");

    public static SelenideElement newTask = $("#new-todo");

    @Step
    public static void add(String... taskTexts) {
        for (String text : taskTexts) {
            newTask.setValue(text).pressEnter();
        }
    }

    @Step
    public static void assertItemsLeft(int number) {
        $("#todo-count>strong").shouldHave(exactText(Integer.toString(number)));
    }

    @Step
    public static void сlearCompleted() {
        $("#clear-completed").click();
        $("#clear-completed").shouldBe(hidden);
    }

    @Step
    public static void toggle(String taskText) {
        tasks.find(exactText(taskText)).$(".toggle").click();
    }

    @Step
    public static void toggleAll() {
        $("#toggle-all").click();
    }

    public static void doubleClick(int index) {
        actions.doubleClick(getWebDriver().findElements(By.cssSelector("#todo-list li")).get(index)).perform();

        //actions.doubleClick(tasks.find(exactText(taskName))).perform();
    }

    @Step
    public static SelenideElement startEditing(String oldText, String newText) {
        doubleClick(0);
        return tasks.find(cssClass("editing")).$(".edit").setValue(newText);
    }

    @Step
    public static void filterAll() {
        $("[href='#/']").click();
    }

    @Step
    public static void filterActive() {
        $("[href='#/active']").click();
    }

    @Step
    public static void filterCompleted() {
        $("[href='#/completed']").click();
    }

    public static void hover(String taskText) {
        //return actions.moveToElement(tasks.find(exactText(taskText))).build().perform();

        //actions.moveToElement(tasks.find(exactText(taskText))).perform();
        //getWebDriver().findElement(By.cssSelector(".destroy")).click();

        //actions.moveToElement(tasks.find(exactText(taskText))).moveToElement(getWebDriver().findElement(By.cssSelector(".destroy"))).click().build().perform();
    }

    @Step
    public static void delete(String taskText) {
        //hover(taskText);
        //$(".destroy").click();
        //getWebDriver().findElement(By.className("destroy")).click();

        //tasks.find(exactText(taskText)).hover().$(".destroy").click();
    }

    @Step
    public static void assertTasks(String... taskTexts) {
        tasks.shouldHave(exactTexts(taskTexts));
    }

    @Step
    public static void assertEmptyTasks() {
        tasks.shouldBe(empty);
    }

    @Step
    public static void assertVisibleTasks(String... taskTexts) {
        tasks.filter(visible).shouldHave(exactTexts((taskTexts)));
    }

    @Step
    public static void assertEmptyVisibleTasks() {
        tasks.filter(visible).shouldBe(empty);
    }

    public static enum TaskType {
        ACTIVE, COMPLETED;
    }

    public static class Task {
        String text;
        TaskType type;

        public Task(String text, TaskType type) {
            this.text = text;
            this.type = type;
        }
    }

    public static Task aTask(String text, TaskType type) {
        Task task = new Task(text, type);
        return task;
    }

    @Step
    public static String toJSON(Task task) {
        return "{\"completed\":" + ((TaskType.ACTIVE == task.type) ? false : true) + ", \"title\":\"" + task.text + "\"},";
    }

    public static void ensureOpenedToDoMVC() {
        if (!url().equals("https://todomvc4tasj.herokuapp.com/"))
            open("https://todomvc4tasj.herokuapp.com/");
    }

    private static void givenHelper(Task... tasks) {
        ensureOpenedToDoMVC();
        String js = "localStorage.setItem('todos-troopjs', '[";
        for (Task task : tasks) {
            js += toJSON(task);
        }
        if (tasks.length > 0) {
            js = js.substring(0, (js.length() - 1));
        }
        js += "]');";
        executeJavaScript(js);
        refresh();
    }

    @Step
    public static void givenAtAll() {
        givenHelper();
    }

    @Step
    public static void givenAtAll(String... taskTexts) {
        Task[] tasksArray = new Task[taskTexts.length];
        for (int i = 0; i < taskTexts.length; i++) {
            tasksArray[i] = aTask(taskTexts[i], TaskType.ACTIVE);
        }
        givenHelper(tasksArray);
    }

    @Step
    public static void givenAtAll(Task... tasks) {
        givenHelper(tasks);
    }

    @Step
    public static void givenAtActive(Task... tasks) {
        givenHelper(tasks);
        filterActive();
    }

    @Step
    public static void givenAtCompleted(Task... tasks) {
        givenHelper(tasks);
        filterCompleted();
    }

    @Step
    public static void givenAtActive(String... taskTexts) {
        givenAtAll(taskTexts);
        filterActive();
    }
}
