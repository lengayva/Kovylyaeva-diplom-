package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.SQLHelper.*;

public class PayCreditCardTest {
    PaymentPage paymentPage = new PaymentPage();
    MainPage mainPage = new MainPage();

    @BeforeAll
    public static void setUpAll() {

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void openPage() {

        open(System.getProperty("website.url"));
    }

    @AfterEach
    void cleanDB() {

        SQLHelper.databaseCleanUp();
    }

    @AfterAll
    public static void tearDownAll() {

        SelenideLogger.removeListener("allure");
    }

    // Тестовый сценарий №1
    @Test
    @SneakyThrows
    @DisplayName("Оплата кредитной картой со статусом APPROVED, " +
            "все поля заполнены валидными значениями")
    void shouldApproveCreditCard() {
        mainPage.buyCreditCard();
        var info = getApprovedCard();
        paymentPage.sendData(info);
        paymentPage.bankApproved();
        var expected = getStatusApprovedCard();
        var creditRequest = getCreditRequestInfo();
        var orderInfo = getOrderInfo();
        assertEquals(expected, getCreditRequestInfo().getStatus());
        assertEquals(orderInfo.getPayment_id(), creditRequest.getBank_id());
    }

    // Тестовый сценарий №2
    @Test
    @SneakyThrows
    @DisplayName("Оплата кредитной картой со статусом DECLINED, " +
            "все поля заполнены валидными значениями")
    void shouldPayCreditDeclinedCard() {
        mainPage.buyCreditCard();
        var info = getDeclinedCard();
        paymentPage.sendData(info);
        paymentPage.bankDeclined();
        var expected = getStatusDeclinedCard();
        var paymentInfo = getPaymentInfo().getStatus();
        assertEquals(expected, paymentInfo);
    }

    // Тестовый сценарий №3
    @Test
    @DisplayName("Оплата кредитной картой, поле Номер карты пустое, " +
            "остальные поля формы заполнены валидными значениями")
    public void shouldEmptyFieldCardWithCredit() {
        mainPage.buyCreditCard();
        var info = getEmptyCardNumber();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldCardNumberError();
    }

    // Тестовый сценарий №4
    @Test
    @DisplayName("Оплата кредитной картой, в поле Номер карты одна цифра, " +
            "остальные поля формы заполнены валидными значениями")
    public void shouldOneNumberInFieldCardNumberWithCredit() {
        mainPage.buyCreditCard();
        var info = getOneNumberCardNumber();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldCardNumberError();
    }

    // Тестовый сценарий №5
    @Test
    @DisplayName("Оплата кредитной картой, в поле Номер карты 15 цифр, " +
            "остальные поля формы заполнены валидными значениями")
    public void shouldFifteenNumberInFieldCardNumberWithCredit() {
        mainPage.buyCreditCard();
        var info = getFifteenNumberCardNumber();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldCardNumberError();
    }

    // Тестовый сценарий №6
    @Test
    @DisplayName("Оплата картой не из Базы данных," +
            " все поля формы заполнены валидными значениями")
    public void shouldFakerCardInFieldCardNumberWithCredit() {
        mainPage.buyCreditCard();
        var info = getFakerNumberCardNumber();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFakerCardNumber();
    }

    // Тестовый сценарий №7
    @Test
    @DisplayName("Оплата кредитной картой, поле Месяц пустое" +
            ", остальные поля формы заполнены валидными значениями")
    public void shouldEmptyFieldMonthWithCredit() {
        mainPage.buyCreditCard();
        var info = getEmptyMonth();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldMonthError();
    }

    // Тестовый сценарий №8
    @Test
    @DisplayName("Оплата кредитной картой, в поле Месяц 00" +
            " остальные поля формы заполнены валидными значениями")
    public void shouldFieldWithZeroMonthWithCredit() {
        mainPage.buyCreditCard();
        var info = getZeroMonthInField();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldMonthError();
    }

    // Тестовый сценарий №9
    @Test
    @DisplayName("Оплата кредитной картой, в поле Месяц 13 (не существующий) месяц" +
            " остальные поля формы заполнены валидными значениями")
    public void shouldFieldWithThirteenMonthWithCredit() {
        mainPage.buyCreditCard();
        var info = getThirteenMonthInField();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldMonthError();
    }

    // Тестовый сценарий №10
    @Test
    @DisplayName("Оплата кредитной картой, в поле Месяц одна цифра" +
            ", остальные поля формы заполнены валидными значениями")
    public void shouldOneNumberInFieldMonthWithCredit() {
        mainPage.buyCreditCard();
        var info = getOneNumberMonth();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldMonthError();
    }

    // Тестовый сценарий №11
    @Test
    @DisplayName("Оплата кредитной картой: в поле Месяц предыдущий от текущего" +
            ", остальные поля формы заполнены валидными значениями")
    public void shouldFieldWithPreviousMonthWithCredit() {
        mainPage.buyCreditCard();
        var info = getPreviousMonthInField();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldMonthError();
    }

    // Тестовый сценарий №12
    @Test
    @DisplayName("Оплата кредитной картой, поле Год пустое " +
            ", остальные поля формы заполнены валидными значениями")
    public void shouldEmptyFieldYearWithCredit() {
        mainPage.buyCreditCard();
        var info = getEmptyYear();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldYearError();
    }

    // Тестовый сценарий №13
    @Test
    @DisplayName("Оплата кредитной картой, в поле Год предыдущий от текущего " +
            " остальные поля формы заполнены валидными значениями")
    public void shouldPreviousYearFieldYearWithCredit() {
        mainPage.buyCreditCard();
        var info = getPreviousYearInField();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldYearError();
    }

    // Тестовый сценарий №14
    @Test
    @DisplayName("Оплата кредитной картой, а поле Год на шесть лет больше чем текущий" +
            " остальные поля формы заполнены валидными значениями")
    public void shouldPlusSixYearFieldYearWithCredit() {
        mainPage.buyCreditCard();
        var info = getPlusSixYearInField();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldYearError();
    }

    //Тестовый сценарий №15
    @Test
    @DisplayName("Оплата кредитной картой, данные в поле Владелец в верхнем регистре" +
            ", остальные поля формы заполнены валидными значениями")
    public void shouldSurnameAndFirstNameUppercaseFromCredit() {
        mainPage.buyCreditCard();
        var info = DataHelper.getNameInUppercase();
        paymentPage.sendData(info);
        paymentPage.bankApproved();
    }

    //Тестовый сценарий №16
    @Test
    @DisplayName("Оплата дебетовой картой: данные в поле Владелец через дефис, " +
            "остальные поля формы заполнены валидными значениями")
    public void shouldSurnameOrFirstNameSeparatedByHyphenFromCredit() {
        mainPage.buyCreditCard();
        var info = DataHelper.getNameSeparatedByHyphen();
        paymentPage.sendData(info);
        paymentPage.bankApproved();
    }

    //Тестовый сценарий №17
    @Test
    @DisplayName("Оплата кредитной картой, поле Владелец пустое," +
            " остальные поля формы заполнены валидными значениями")
    public void shouldEmptyFieldNameWithCredit() {
        mainPage.buyCreditCard();
        var info = getApprovedCard();
        paymentPage.sendingEmptyNameValidData(info);
        paymentPage.sendingValidDataWithFieldNameError();
    }

    //Тестовый сценарий №18
    @Test
    @DisplayName("Оплата кредитной картой, в поле Владелец только фамилия" +
            ", остальные поля формы заполнены валидными значениями")
    public void shouldOnlySurnameInFieldNameWithCredit() {
        mainPage.buyCreditCard();
        var info = getOnlySurnameInFieldName();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldNameError();
    }

    //Тестовый сценарий №19
    @Test
    @DisplayName("Оплата кредитной картой, в поле Владелец русские буквы" +
            " остальные поля формы заполнены валидными значениями")
    public void shouldRussianNameInFieldNameWithCredit() {
        mainPage.buyCreditCard();
        var info = getRusName();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldNameError();
    }

    //Тестовый сценарий №20
    @Test
    @DisplayName("Оплата кредитной картой, в поле Владелец цифры" +
            " остальные поля формы заполнены валидными значениями")
    public void shouldNumberInFieldNameWithCredit() {
        mainPage.buyCreditCard();
        var info = getNumberInFieldName();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldNameError();
    }

    //Тестовый сценарий №21
    @Test
    @DisplayName("Оплата кредитной картой, в поле Владелец спец. символы" +
            " остальные поля формы заполнены валидными значениями")
    public void shouldSpecialSymbolInFieldNameWithCredit() {
        mainPage.buyCreditCard();
        var info = getSpecialSymbolInFieldName();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldNameError();
    }

    //Тестовый сценарий №22

    @Test
    @DisplayName("Оплата кредитной картой, поле CVC пустое" +
            " остальные поля формы заполнены валидными значениями")
   public void shouldEmptyCVVInFieldCVVWithCredit() {
        mainPage.buyCreditCard();
        var info = getEmptyCVVInFieldCVV();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldCVVError();
    }

    //Тестовый сценарий №23
    @Test
    @DisplayName("Оплата кредитной картой, в поле CVC одна цифра" +
            " остальные поля формы заполнены валидными значениями")
    public void shouldOneNumberInFieldCVVWithCredit() {
        mainPage.buyCreditCard();
        var info = getOneNumberInFieldCVV();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldCVVError();
    }

    //Тестовый сценарий №24
    @Test
    @DisplayName("Оплата кредитной картой, в поле CVC две цифры" +
            " остальные поля формы заполнены валидными значениями")
    public void shouldTwoNumberInFieldCVVWithCredit() {
        mainPage.buyCreditCard();
        var info = getOTwoNumberInFieldCVV();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldCVVError();
    }

    //Тестовый сценарий №25
    @Test
    @DisplayName("Оплата кредитной картой, все поля пустые")
    void shouldEmptyFormWithCredit() {
        mainPage.buyCreditCard();
        paymentPage.pressButtonForContinue();
        paymentPage.emptyForm();

    }
}































