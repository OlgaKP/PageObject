package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CardPage {
    private SelenideElement amount = $("h1");
    private SelenideElement sumAmount = $("[data-test-id='amount'] input");
    private SelenideElement sumFrom = $("[data-test-id='from'] input");
    private SelenideElement buttonFrom = $("[data-test-id='action-transfer']");

    public CardPage() {
        amount.shouldBe(visible);
    }

    public DashboardPage card(DataHelper.CardsInfo cardsInfo) {
        sumAmount.sendKeys(Keys.chord(Keys.CONTROL,"a"),Keys.DELETE);
        sumAmount.setValue(String.valueOf(cardsInfo.getSum()));
        sumFrom.sendKeys(Keys.chord(Keys.CONTROL,"a"),Keys.DELETE);
        sumFrom.setValue(cardsInfo.getNumCard());
        buttonFrom.click();
        return new DashboardPage();
    }

}
