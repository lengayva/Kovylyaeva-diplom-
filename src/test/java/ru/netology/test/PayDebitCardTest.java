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


public class PayDebitCardTest {
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
    @DisplayName("Оплата дебетовой картой со статусом APPROVED, все поля формы заполнены валидными значениями")
    public void shouldPayDebitValidCard() {
        mainPage.payDebitCard();
        var info = getApprovedCard();
        paymentPage.sendData(info);
        paymentPage.bankApproved();
        var expected = DataHelper.getStatusApprovedCard();
        var paymentInfo = SQLHelper.getPaymentInfo();
        var orderInfo = SQLHelper.getOrderInfo();
        assertEquals(expected, getPaymentInfo().getStatus());
        var expectedAmount = 45000;
        assertEquals(expectedAmount, paymentInfo.getAmount());

    }

    //Тестовый сценарий №2 БАГ
    @Test
    @SneakyThrows
    @DisplayName("Оплата дебетовой картой со статусом DECLINED, " +
            "все поля формы заполнены валидными значениями")
    void shouldPayDebitDeclinedCard() {
        mainPage.payDebitCard();
        var info = DataHelper.getDeclinedCard();
        paymentPage.sendData(info);
        paymentPage.bankDeclined();
        var paymentStatus = getPaymentInfo();
        assertEquals("DECLINED", paymentStatus);
    }

    //Тестовый сценарий №3
    @Test
    @DisplayName("Оплата дебетовой картой, поле Номер карты пустое, " +
            "остальные поля формы заполнены валидными значениями")
    void shouldEmptyFieldCardFormDebit() {
        mainPage.payDebitCard();
        var info = DataHelper.getEmptyCardNumber();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldCardNumberError();
    }

    //Тестовый сценарий №4
    @Test
    @DisplayName("Оплата дебетовой картой, в поле Номер карты одна цифра, " +
            "остальные поля формы заполнены валидными значениями")
    public void shouldOneNumberInFieldCardFormDebit() {
        mainPage.payDebitCard();
        var info = DataHelper.getOneNumberCardNumber();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldCardNumberError();
    }

    //Тестовый сценарий №5
    @Test
    @DisplayName("Оплата дебетовой картой, в поле Номер карты 15 цифр, " +
            "остальные поля формы заполнены валидными значениями")
    public void shouldFifteenNumberInFieldCardNumberFormDebit() {
        mainPage.payDebitCard();
        var info = DataHelper.getFifteenNumberCardNumber();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldCardNumberError();
    }

    //Тестовый сценарий №6
    @Test
    @DisplayName("Оплата картой не из Базы данных, все поля " +
            " все поля формы заполнены валидными значениями")
    public void shouldFakerCardNumberFormDebit() {
        mainPage.payDebitCard();
        var info = DataHelper.getFakerNumberCardNumber();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFakerCardNumber();
    }

    //Тестовый сценарий №7
    @Test
    @DisplayName("Оплата дебетовой картой, поле Месяц пустое" +
            ", остальные поля формы заполнены валидными значениями")
    public void shouldEmptyFieldMonthFormDebit() {
        mainPage.payDebitCard();
        var info = getEmptyMonth();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldMonthError();
    }

    //Тестовый сценарий №8 баг
    @Test
    @DisplayName("Оплата дебетовой картой, в поле Месяц 00" +
            " остальные поля формы заполнены валидными значениями")
    public void shouldFieldWithZeroMonthFormDebit() {
        mainPage.payDebitCard();
        var info = getZeroMonthInField();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldMonthError();
    }

    //Тестовый сценарий №9
    @Test
    @DisplayName("Оплата дебетовой картой, в поле Месяц 13 (не существующий) месяц" +
            " остальные поля формы заполнены валидными значениями")
    public void shouldFieldWithThirteenMonthFormDebit() {
        mainPage.payDebitCard();
        var info = getThirteenMonthInField();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldMonthError();
    }

    //Тестовый сценарий №10
    @Test
    @DisplayName("Оплата дебетовой картой, в поле Месяц одна цифра" +
            ", остальные поля формы заполнены валидными значениями")
    public void shouldOneNumberInFieldMonthFormDebit() {
        mainPage.payDebitCard();
        var info = getOneNumberMonth();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldMonthError();
    }

    //Тестовый сценарий №11
    @Test
    @DisplayName("Оплата дебетовой картой: в поле Месяц предыдущий от текущего" +
            ", остальные поля формы заполнены валидными значениями")
    public void shouldFieldWithPreviousMonthFormDebit() {
        mainPage.payDebitCard();
        var info = getPreviousMonthInField();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldMonthError();
    }

    //Тестовый сценарий №12
    @Test
    @DisplayName("Оплата дебетовой картой, поле Год пустое " +
            ", остальные поля формы заполнены валидными значениями")
    public void shouldEmptyFieldYearFormDebit() {
        mainPage.payDebitCard();
        var info = getEmptyYear();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldYearError();
    }

    //Тестовый сценарий №13
    @Test
    @DisplayName("Оплата дебетовой картой, в поле Год предыдущий от текущего " +
                      " остальные поля формы заполнены валидными значениями")
    public void shouldPreviousYearFieldYearFormDebit() {
        mainPage.payDebitCard();
        var info = getPreviousYearInField();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldYearError();
    }

    //Тестовый сценарий №14
    @Test
    @DisplayName("Оплата дебетовой картой, а поле Год на шесть лет больше чем текущий" +
            " остальные поля формы заполнены валидными значениями")
    public void shouldPlusSixYearFieldYearFormDebit() {
        mainPage.payDebitCard();
        var info = getPlusSixYearInField();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldYearError();
    }

    //Тестовый сценарий №15

    @Test
    @DisplayName("Оплата дебетовой картой, данные в поле Владелец в верхнем регистре" +
            ", остальные поля формы заполнены валидными значениями")
    public void shouldSurnameAndFirstNameUppercaseFromDebit() {
        mainPage.payDebitCard();
        var info = DataHelper.getNameInUppercase();
        paymentPage.sendData(info);
        paymentPage.bankApproved();
    }

    //Тестовый сценарий №16
    @Test
    @DisplayName("Оплата дебетовой картой: данные в поле Владелец через дефис, " +
            "остальные поля формы заполнены валидными значениями")
    public void shouldSurnameOrFirstNameSeparatedByHyphenFromDebit() {
        mainPage.payDebitCard();
        var info = DataHelper.getNameSeparatedByHyphen();
        paymentPage.sendData(info);
        paymentPage.bankApproved();
    }

    //Тестовый сценарий №17
    @Test
    @DisplayName("Оплата дебетовой картой, поле Владелец пустое," +
            " остальные поля формы заполнены валидными значениями")
    public void shouldEmptyFieldNameFormDebit() {
        mainPage.payDebitCard();
        var info = getApprovedCard();
        paymentPage.sendingEmptyNameValidData(info);
        paymentPage.sendingValidDataWithFieldNameError();
    }

    //Тестовый сценарий №18
    @Test
    @DisplayName("Оплата дебетовой картой, в поле Владелец только фамилия" +
            ", остальные поля формы заполнены валидными значениями")
    public void shouldOnlySurnameFormDebit() {
        mainPage.payDebitCard();
        var info = DataHelper.getOnlySurnameInFieldName();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldNameError();
    }

    //Тестовый сценарий №19
    @Test
    @DisplayName("Оплата дебетовой картой, в поле Владелец русские буквы" +
            " остальные поля формы заполнены валидными значениями")
    public void shouldRusNameInFieldNameFormDebit() {
        mainPage.payDebitCard();
        var info = DataHelper.getRusName();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldNameError();
    }

    //Тестовый сценарий №20
    @Test
    @DisplayName("Оплата дебетовой картой, в поле Владелец цифры" +
            " остальные поля формы заполнены валидными значениями")
    public void shouldNumberInFieldNameFormDebit() {
        mainPage.payDebitCard();
        var info = getNumberInFieldName();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldNameError();
    }

    //Тестовый сценарий №21 баг
    @Test
    @DisplayName("Оплата дебетовой картой, в поле Владелец спец. символы" +
            " остальные поля формы заполнены валидными значениями")
    public void shouldSpecialSymbolInFieldNameFormDebit() {
        mainPage.payDebitCard();
        var info = getSpecialSymbolInFieldName();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldNameError();
    }

    //Тестовый сценарий №22
    @Test
    @DisplayName("Оплата дебетовой картой, поле CVC пустое" +
            " остальные поля формы заполнены валидными значениями")
    public void shouldEmptyCVVInFieldCVVFormDebit() {
        mainPage.payDebitCard();
        var info = getEmptyCVVInFieldCVV();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldCVVError();
    }

    //Тестовый сценарий №23
    @Test
    @DisplayName("Оплата дебетовой картой, в поле CVC одна цифра" +
            " остальные поля формы заполнены валидными значениями")
    public void shouldOneNumberInFieldCVVFormDebit() {
        mainPage.payDebitCard();
        var info = getOneNumberInFieldCVV();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldCVVError();
    }

    //Тестовый сценарий №24
    @Test
    @DisplayName("Оплата дебетовой картой, в поле CVC две цифры" +
            " остальные поля формы заполнены валидными значениями")
    public void shouldTwoNumberInFieldCVVАFormDebit() {
        mainPage.payDebitCard();
        var info = getOTwoNumberInFieldCVV();
        paymentPage.sendData(info);
        paymentPage.sendingValidDataWithFieldCVVError();
    }

    //Тестовый сценарий №25
    @Test
    @DisplayName("Оплата дебетовой картой, все поля пустые")
    void shouldEmptyFormDebitCard() {
        mainPage.payDebitCard();
        paymentPage.pressButtonForContinue();
        paymentPage.emptyForm();
    }
}























