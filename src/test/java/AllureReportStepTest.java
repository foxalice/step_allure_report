import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class AllureReportStepTest {

    private static final String issueId = "2152";
    private static final String IssueTitle = "Add web-driver support \"print to pdf\" configuration without any OS dialogs";

    @Test
    @DisplayName("Проверка имени Issue через annotation")
    public void checkNameIssueAnnotatedSteps() {

        SelenideLogger.addListener("allure", new AllureSelenide());
        Steps steps = new Steps();

        steps.openPage("selenide/selenide/issues");
        steps.inputIssueIdInSearchField(issueId);
        steps.checkIssueName(issueId, IssueTitle);
    }

    @Test
    @DisplayName("Проверка имени Issue через Lambda steps")
    public void checkNameIssueLambdaSteps() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открыть страницу issues проекта Selenide", () -> {
            open("https://github.com/selenide/selenide/issues");
        });

        step("В строке поиска ввести id Issue " + issueId,  () -> {
            $("#js-issues-search").click();
            $("#js-issues-search").setValue(issueId);
            $("#js-issues-search").pressEnter();

        });

        step("Проверить название Issue " + issueId, () -> {
            $("#issue_2152").shouldHave(Condition.
                    text(IssueTitle));
        });
    }


}