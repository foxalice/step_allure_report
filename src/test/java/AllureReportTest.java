import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;

public class AllureReportTest {
    private static final String ISSUE_ID = "2152";
    @Test
    @DisplayName("Проверка имени Issue без step")
    public void checkNameIssue() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/selenide/selenide/issues");
        $("#js-issues-search").click();
        $("#js-issues-search").setValue(ISSUE_ID);
        $("#js-issues-search").pressEnter();
        $("#issue_2152_link").shouldHave(Condition.
                text("Add web-driver support \"print to pdf\" configuration without any OS dialogs"));
    }
}