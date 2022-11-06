import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AnzhelikaBaaTest {

    /**TC_1_1  - Тест кейс:
    1. Открыть страницу https://openweathermap.org/
    2. Набрать в строке поиска город Paris
    3. Нажать пункт меню Search
    4. Из выпадающего списка выбрать Paris, FR
    5. Подтвердить, что заголовок изменился на "Paris, FR" */



    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\joeba\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );
        searchButton.click();

        Thread.sleep(1000);

        WebElement parisFRChoiceInDropdownMenu = driver.findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );

        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);


        driver.quit();

    }

    /**
     * TC_11_01
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на пункт меню Guide
     * 3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide и что title этой
     * страницы OpenWeatherMap API guide - OpenWeatherMap
     */


    @Test
    public void testTagText_WhenSearchingGuide() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\joeba\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult1 = "https://openweathermap.org/guide";
        String expectedResult2 = "OpenWeatherMap API guide - OpenWeatherMap";

        driver.get(url);
        Thread.sleep(5000);

        WebElement guideButton = driver.findElement(
                By.xpath("//div[@id= 'desktop-menu']//a[@href='/guide']")
        );
        guideButton.click();
        Thread.sleep(5000);

        String actualResult1 = driver.getCurrentUrl();
        String actualResult2 = driver.getTitle();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);

        driver.quit();

    }

    /**
     * TC_11_02
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на единицы измерения Imperial: °F, mph
     * <p>
     * 3.  Подтвердить, что температура для города показана в Фарингейтах
     */

    @Test
    public void testTempInF_WhenUsingFWeatherUnits() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\joeba\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String weatherMeasureUnits = "Imperial: °F, mph";
        String fTempSymbol = "°F";
        boolean expectedResult = true;

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement fUnitsButton = driver.findElement(
                By.xpath("//div[@id='weather-widget']/div[1]/div/div/div[1]/div[2]/div[3]")
        );
        fUnitsButton.click();

        Thread.sleep(5000);
        WebElement tempForCityInF = driver.findElement(
                By.xpath("//div[@class='section-content']/div/div/div/div/span")
        );

        Thread.sleep(3000);
        boolean actualResult = tempForCityInF.getText().contains(fTempSymbol);

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();

    }

    /**TC_11_03
     1.  Открыть базовую ссылку
     2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential for the site to work.
     We also use non-essential cookies to help us improve our services. Any data collected is anonymised.
     You can allow all cookies or manage them individually.”
     3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies” */

    @Test
    public void testTwoButtons_WhenSearchingTextAboutCookies() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\joeba\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";

        String expectedResult1 = "Allow all";
        String expectedResult2 = "Manage cookies";
        String expectedResult = "We use cookies which are essential for the site to work. We also use non-essential " +
                "cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies" +
                " or manage them individually.";

        driver.get(url);
        Thread.sleep(5000);

        WebElement textPanel = driver.findElement(
                By.className("stick-footer-panel__description")
        );

        Thread.sleep(2000);

        WebElement allowAllButton = driver.findElement(
                By.xpath("//button[text()='Allow all']")
        );

        Thread.sleep(2000);

        WebElement manageCookiesButton = driver.findElement(
                By.xpath("//a[@href='/cookies-settings']")
        );

        Thread.sleep(5000);

        String actualResult1 = allowAllButton.getText();
        String actualResult2 = manageCookiesButton.getText();
        String actualResult = textPanel.getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();

    }

    /**TC_11_04
     1.  Открыть базовую ссылку
     2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question” */

    @Test
    public void testSubMenu_WhenUsingDDownSupportMenu() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\joeba\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult1 = "FAQ";
        String expectedResult2 = "How to start";
        String expectedResult3 = "Ask a question";

        driver.manage().window().maximize();
        driver.get(url);
        Thread.sleep(5000);

        WebElement dDownSupport = driver.findElement(
                By.xpath("//div[@id='support-dropdown']")
        );
        dDownSupport.click();
        Thread.sleep(5000);

        WebElement faqButton = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@href='/faq']")
        );
        Thread.sleep(5000);

        WebElement howToStartButton = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@href='/appid']")
        );
        Thread.sleep(5000);

        WebElement askQuestionButton = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li[3]/a[@href='https://home.openweathermap.org/questions']")
        );
        Thread.sleep(5000);

        String actualResult1 =  faqButton.getText();
        String actualResult2 = howToStartButton.getText();
        String actualResult3 = askQuestionButton.getText();;

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);

        driver.quit();

    }

    /** TC_11_05
     1. Открыть базовую ссылку
     2. Нажать пункт меню Support → Ask a question
     3. Заполнить поля Email, Subject, Message
     4. Не подтвердив CAPTCHA, нажать кнопку Submit
     5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.” */

    @Test
    public void testCaptcha_WhenSubmittingCompletedFields() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\joeba\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String supportButton = "Support";
        String dDrop = "Ask a question";
        String buttonSubmit = "Submit";
        String eMail = "tester@test.com";
        String subject = "Other";
        String message = "Hello";

        String expectedResult = "reCAPTCHA verification failed, please try again.";

        driver.manage().window().maximize();
        driver.get(url);
        Thread.sleep(5000);

        WebElement menuSupport = driver.findElement(
                By.xpath("//div[@id='support-dropdown']")
        );
        menuSupport.click();
        Thread.sleep(500);

        WebElement dDownAskAQuestion = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li[3]/a[@href='https://home.openweathermap.org/questions']")
        );
        dDownAskAQuestion.click();
        Thread.sleep(5000);

        for (String winHandle:
                driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        WebElement emailField = driver.findElement(
                By.xpath("//div[@class='col-sm-8']/input[@id='question_form_email']")
        );
        emailField.click();
        emailField.sendKeys(eMail);

        Thread.sleep(5000);
        WebElement subjectField = driver.findElement(
                By.id("question_form_subject")
        );
        subjectField.click();
        subjectField.sendKeys(subject);

        Thread.sleep(5000);
        WebElement messageField = driver.findElement(
                By.id("question_form_message")
        );
        messageField.click();
        messageField.sendKeys(message);

        Thread.sleep(5000);
        WebElement submit = driver.findElement(
                By.xpath("//input[@class='btn btn-default']")
        );
        submit.click();

        Thread.sleep(5000);
        WebElement reCaptcha = driver.findElement(
                By.xpath("//div[@class='help-block']")
        );

        String actualResult = reCaptcha.getText();

        Assert.assertEquals(actualResult, expectedResult);


        driver.quit();
    }

    /**TC_11_06
     1.  Открыть базовую ссылку
     2.  Нажать пункт меню Support → Ask a question
     3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
     4. Оставить пустым поле Email
     5. Заполнить поля  Subject, Message
     6. Подтвердить CAPTCHA
     7. Нажать кнопку Submit
     8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank”  */

//    @Test
//    public void testMistakeMessage_WhenEmailFieldIsBlank() throws InterruptedException {
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\joeba\\Downloads\\chromedriver_win32\\chromedriver.exe");
//        WebDriver driver = new ChromeDriver();

//        String url = "https://openweathermap.org/";
//        String supportButton = "Support";
//        String dDrop = "Ask a question";
//        String buttonSubmit = "Submit";
//        String subject = "Other";
//        String message = "Hello";
//
//        String expectedResult = "can't be blank";
//
//        driver.manage().window().maximize();
//        driver.get(url);
//
//        Thread.sleep(5000);
//        WebElement menuSupport = driver.findElement(
//                By.xpath("//div[@id='support-dropdown']")
//        );
//        menuSupport.click();
//
//        Thread.sleep(500);
//        WebElement dDownAskAQuestion = driver.findElement(
//                By.xpath("//ul[@id='support-dropdown-menu']/li[3]/a[@href='https://home.openweathermap.org/questions']")
//        );
//        dDownAskAQuestion.click();
//
//        for (String winHandle:
//                driver.getWindowHandles()) {
//            driver.switchTo().window(winHandle);
//        }
//
//        WebElement emailField = driver.findElement(
//                By.xpath("//div[@class='col-sm-8']/input[@id='question_form_email']")
//        );
//        emailField.click();
//
//        Thread.sleep(5000);
//        WebElement subjectField = driver.findElement(
//                By.id("question_form_subject")
//        );
//        subjectField.click();
//        subjectField.sendKeys(subject);
//
//        Thread.sleep(5000);
//        WebElement messageField = driver.findElement(
//                By.id("question_form_message")
//        );
//        messageField.click();
//        messageField.sendKeys(message);
//
//        Thread.sleep(5000);
//        WebElement fieldCaptcha = driver.findElement(
//                By.xpath("//div[@class='recaptcha-checkbox-border']")
//        );
//        fieldCaptcha.click();
//
//        WebDriverWait wait = new WebDriverWait(driver, 25);
//        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
//                By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")));
//
//        wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//div[@class='recaptcha-checkbox-border']"))).click();
//        Thread.sleep(5000);

        @Test
        public void testErrorEmail() throws InterruptedException {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\joeba\\Downloads\\chromedriver_win32\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String subject = "Other";
        String message = "Hi Lilu we are waiting for you";

        String expectedResult = "can't be blank";


        driver.get(url);

        Thread.sleep(5000);
        driver.manage().window().maximize();

        WebElement clickOnSupport = driver.findElement(By.xpath("//div[@id='support-dropdown']"));
        clickOnSupport.click();

        String originalWindow = driver.getWindowHandle();
        Thread.sleep(5000);
        WebElement selectSubmenu_AskAQuestion = driver.findElement(By.xpath(
                "//ul[@id='support-dropdown-menu']//a[@href='https://home.openweathermap.org/questions']"));
        selectSubmenu_AskAQuestion.click();

        Thread.sleep(5000);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        Thread.sleep(5000);

        WebElement enterSubject = driver.findElement(By.xpath(
                "//select[@class='form-control select required']"));

        enterSubject.click();

        enterSubject.sendKeys(subject);

        Thread.sleep(5000);

        WebElement enterMessage = driver.findElement(By.xpath(
                "//textarea[@class='form-control text required']"));
        enterMessage.click();
        enterMessage.sendKeys(message);

        Thread.sleep(5000);

        String window2 = driver.getWindowHandle();

        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title='reCAPTCHA']")));

        WebElement enterCaptcha = driver.findElement(By.xpath(
                "//span[@class='recaptcha-checkbox goog-inline-block recaptcha-checkbox-unchecked "
                        + "rc-anchor-checkbox']"));
        enterCaptcha.click();

        Thread.sleep(10000);

        driver.switchTo().window(window2);

        WebElement pressSubmit = driver.findElement(By.xpath(
                "//input[@data-disable-with='Create Question form']"));
        pressSubmit.click();

        WebElement confirmErrorEmail = driver.findElement(By.xpath("//span[@class='help-block']"));

        String actualResult = confirmErrorEmail.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }


 /** TC_11_07
1.  Открыть базовую ссылку
2.  Нажать на единицы измерения Imperial: °F, mph
3.  Нажать на единицы измерения Metric: °C, m/s
4.  Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С */

 @Test
 public void testSwitchTempUnits_WhenClickingOnTempFields() throws InterruptedException {
     System.setProperty("webdriver.chrome.driver", "C:\\Users\\joeba\\Downloads\\chromedriver_win32\\chromedriver.exe");
     WebDriver driver = new ChromeDriver();

     String url = "https://openweathermap.org/";
     String weatherMeasureUnits1 = "Imperial: °F, mph";
     String weatherMeasureUnits2 = "Metric: °C, m/s";
     String cTempSymbol = "C";
     Boolean expectedResult = true;

     driver.manage().window().maximize();
     driver.get(url);
     Thread.sleep(5000);

     WebElement fUnitsButton = driver.findElement(
             By.xpath("//div[@class='switch-container']//div[text()='Imperial: °F, mph']")
     );
     fUnitsButton.click();
     Thread.sleep(5000);

     WebElement cUnitsButton = driver.findElement(
             By.xpath("//div[@class='switch-container']//div[text()='Metric: °C, m/s']")
     );
     cUnitsButton.click();

     Thread.sleep(5000);
     WebElement temperatureC = driver.findElement(
             By.xpath("//span[@class='heading'][contains(text(),'°C')]")
     );

     Thread.sleep(5000);
     boolean actualResult = temperatureC.getText().contains(cTempSymbol);

     Assert.assertTrue(actualResult);

     driver.quit();
 }


    /**TC_11_08
1.  Открыть базовую ссылку
2.  Нажать на лого компании
3.  Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась */

    @Test
    public void testSiteReload_WhenClickingOnLogo() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\joeba\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";

        driver.manage().window().maximize();
        driver.get(url);
        Thread.sleep(5000);

        WebElement logoOfCompany = driver.findElement(
                By.xpath("//a[@href='/']/img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']")
        );
        logoOfCompany.click();
        Thread.sleep(5000);

        driver.quit();
    }







        /**TC_11_09
    1.  Открыть базовую ссылку
    2.  В строке поиска в навигационной панели набрать “Rome”
    3.  Нажать клавишу Enter
    4.  Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
    5. Подтвердить, что в строке поиска на новой странице вписано слово “Rome”  */



    /**TC_11_10
1.  Открыть базовую ссылку
2.  Нажать на пункт меню API
3.  Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок */

    @Test
    public void testDeskTopMenuClickAPIFind30Buttons1() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\joeba\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //Arrange
        String url = "https://openweathermap.org/";
        driver.get(url);
        driver.manage().window().maximize();
        int expectedResult = 30;
        Thread.sleep(5000);

        WebElement element = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']//a[text()= 'API']"));
        element.click();

        int countButton = driver.findElements(
                By.xpath("//a[contains(@class, 'btn_block orange round') " +
                        "or contains(@class, 'ow-btn round btn-orange') ]")).size();

        int actualResult = countButton;

        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();

    }





//    https://formy-project.herokuapp.com
//
//    https://demoqa.com/automation-practice-form




}
