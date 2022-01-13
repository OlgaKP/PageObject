package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.CardPage;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

    int amount = 500;
    int bigSum = 21000;

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
//        Configuration.holdBrowserOpen = true;
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);

        var balance1 = dashboardPage.getCardBalance(0);
        var balance2 = dashboardPage.getCardBalance(1);
        if (balance1 > balance2) {
            int balance = (balance1 - balance2) / 2;
            dashboardPage.personSecondCard().transferMoney(DataHelper.getSecondCardsInfo(balance));
        }
        else if (balance1 < balance2) {
            int balance = (balance2 - balance1) / 2;
            dashboardPage.personFirstCard().transferMoney(DataHelper.getFirstCardsInfo(balance));
        }
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsWith1Into2() {
        var dashboardPage = new DashboardPage();
        var balanceFirstBefore = dashboardPage.getCardBalance(0);
        var balanceSecondBefore = dashboardPage.getCardBalance(1);
        var balanceFirstAfter = balanceFirstBefore + amount;
        var balanceSecondAfter = balanceSecondBefore - amount;
        var verificationFirstCard = DataHelper.getFirstCardsInfo(amount);
        dashboardPage.personFirstCard().transferMoney(verificationFirstCard);

        assertEquals(balanceFirstAfter, dashboardPage.getCardBalance(0));
        assertEquals(balanceSecondAfter, dashboardPage.getCardBalance(1));
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsWith2Into1() {
        var dashboardPage = new DashboardPage();
        var balanceFirstBefore = dashboardPage.getCardBalance(0);
        var balanceSecondBefore = dashboardPage.getCardBalance(1);
        var balanceFirstAfter = balanceFirstBefore - amount;
        var balanceSecondAfter = balanceSecondBefore + amount;
        var verificationSecondCard = DataHelper.getSecondCardsInfo(amount);
        dashboardPage.personSecondCard().transferMoney(verificationSecondCard);

        assertEquals(balanceFirstAfter, dashboardPage.getCardBalance(0));
        assertEquals(balanceSecondAfter, dashboardPage.getCardBalance(1));
    }

    @Test
    void shouldTransferMoneyOverLimit() {
        var dashboardPage = new DashboardPage(); 
        var balanceFirstBefore = dashboardPage.getCardBalance(0);
        var balanceSecondBefore = dashboardPage.getCardBalance(1);
        var verificationSecondCard = DataHelper.getSecondCardsInfo(bigSum);
        var transferPage = dashboardPage.personSecondCard();
        transferPage.transferMoney(verificationSecondCard);
        transferPage.getErrorAmount();

        assertEquals(balanceFirstBefore, dashboardPage.getCardBalance(0)); //перевод не должен сработать, поэтому balanceFirstBefore
        assertEquals(balanceSecondBefore, dashboardPage.getCardBalance(1));
    }

}

