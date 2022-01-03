package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var verificationFirstCard = DataHelper.getFirstCardsInfo(authInfo);
        var cardFirst = dashboardPage.personFirstCard();
        var amountFirst = cardFirst.card(verificationFirstCard);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsWithTwoIntoOne() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var verificationSecondCard = DataHelper.getSecondCardsInfo(authInfo);
        var cardSecond = dashboardPage.personSecondCard();
        var amountSecond = cardSecond.card(verificationSecondCard);
    }

    @Test
    void shouldGetFirstCardBalance() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var balanceFirstCard = dashboardPage.getFirstCardBalance();
        System.out.println("=============");
        System.out.println("   " + balanceFirstCard);
        System.out.println("=============");
    }

    @Test
    void shouldGetSecondCardBalance() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var balanceSecondCard = dashboardPage.getSecondCardBalance();
        System.out.println("=============");
        System.out.println("   " + balanceSecondCard);
        System.out.println("=============");
    }

}

