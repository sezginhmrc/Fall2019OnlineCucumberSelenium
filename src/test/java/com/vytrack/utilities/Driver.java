package com.vytrack.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Driver {

    // same for everyone
    // this static variable is sharable for parallelization
    // everything happens in one main local
    // we have two test originally have one driver
    // it will give same instance for every test
    // parallelization is difficult concept


    private static ThreadLocal <WebDriver> driverPool= new ThreadLocal<>() ;
    //Each thread holds an implicit reference to its copy of a thread-local
    // * variable as long as the thread is alive
    // copy of driver object at run time
    // two actions same time every thread will get its own coopy
    // threadlocal will clone webdriver object for every thread.


    public static final String USERNAME = "sezginhamurcu1";
    public static final String AUTOMATE_KEY = "BGvT5kCxtSsJp3szsWzK";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";


    private static ChromeOptions chromeOptions;
    private static FirefoxOptions firefoxOptions;
    private static URL url ;

    //so no one can create object of Driver class
    //everyone should call static getter method instead

    private Driver() {

    }
    // if webdriver is okay thread local driver tests in parallel will use getDriver (synchronized )
    // we just pretent the some issues here
    // we will provide each test can get this webdriver method


    /**
     *  synchronized makes method thread safe. It ensures that only 1 thread can use it at the time.
     *  <p>
     *  Thread safety reduces performance but it makes everything safe.
     *  situation is when we need to make threads in an order way
     *
     *
     * @return
     */
    public synchronized static WebDriver getDriver() {
        String GRID_URL = "http://34.204.195.171:4444/wd/hub";
        // 54.196.47.224
        // 54.152.21.73
        //if webdriver object doesn't exist
        //create it
        if (driverPool.get() == null) {
            // if driver wasnt created.
            //specify browser type in configuration.properties file
            String browser = ConfigurationReader.getProperty("browser").toLowerCase();
            // -Dbrowser=firefox
            if (System.getProperty("browser") != null) {
                browser = System.getProperty("browser");
            }

            if (System.getProperty("grid_url") != null) {
                GRID_URL = System.getProperty("grid_url");
            }

            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    driverPool.set(new ChromeDriver(chromeOptions));
                    break;
                case "chromeheadless":
                    //to run chrome without interface (headless mode)
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setHeadless(true);
                    driverPool.set(new ChromeDriver(options));
                    break;
                case "browser-stack-chrome":
                    DesiredCapabilities caps = new DesiredCapabilities();

                    caps.setCapability("os", "Windows");
                    caps.setCapability("os_version", "10");
                    caps.setCapability("browser", "Chrome");
                    caps.setCapability("browser_version", "80");
                    caps.setCapability("name", "sezginhamurcu1's First Test");

                    try {
                        driverPool.set(new RemoteWebDriver(new URL(URL), caps));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "browser-stack-ios":
                    DesiredCapabilities capsIOS = new DesiredCapabilities();
                    capsIOS.setCapability("browserName", "iPhone");
                    capsIOS.setCapability("device", "iPhone 11 Pro Max");
                    capsIOS.setCapability("realMobile", "true");
                    capsIOS.setCapability("os_version", "13");
                    capsIOS.setCapability("name", "Bstack-[Java] Sample Test");

                    try {
                        WebDriver driver = new RemoteWebDriver(new URL(URL), capsIOS);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "browser-stack-android":
                    DesiredCapabilities caps1 = new DesiredCapabilities();
                    caps1.setCapability("browserName", "android");
                    caps1.setCapability("device", "Samsung Galaxy S8");
                    caps1.setCapability("realMobile", "true");
                    caps1.setCapability("os_version", "7.0");
                    caps1.setCapability("name", "sezginhamurcu1's android test");
                    try {
                        driverPool.set(  new RemoteWebDriver(new URL(URL), caps1));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "chrome-remote":
                    try {
                        //we created object of URL and specified above
                        //selenium grid hub as a parameter
                        //make sure it ends with /wd/hub
                        URL url = new URL(GRID_URL);
                        //desiredCapabilities used to specify what kind of node
                        //is required for testing
                        //such as: OS type, browser, version, etc...
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName(BrowserType.CHROME); // chrome browser
                        desiredCapabilities.setPlatform(Platform.ANY);  // any platfrom

                        driverPool.set(new RemoteWebDriver(url, desiredCapabilities));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "firefox-remote":
                    try {
                        //we create object of URL and specify
                        //selenium grid hub as a parameter
                        //make sure it ends with /wd/hub
                        URL url = new URL(GRID_URL);
                        //desiredCapabilities used to specify what kind of node
                        //is required for testing
                        //such as: OS type, browser, version, etc...
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName(BrowserType.FIREFOX);
                        desiredCapabilities.setPlatform(Platform.ANY);
                        driverPool.set(new RemoteWebDriver(url, desiredCapabilities));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;
                default:
                    throw new RuntimeException("Wrong browser name!");
            }
        }
        return driverPool.get();
    }
    /**synchronized makes method thread safe. It ensures that only 1 thread can use it at the time.
     *
     * Thread safety reduces performance but it makes everything safe.
     *
     * @return
     */
    public synchronized static WebDriver getDriver(String browser) {
        //if webdriver object doesn't exist
        //create it
        if (driverPool.get() == null) {
            //specify browser type in configuration.properties file if is not null in properties file
            if(System.getProperty("browser")!=null){

                browser=System.getProperty("browser");  //
            }
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().version("81").setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    driverPool.set(new ChromeDriver(chromeOptions));
                    break;
                case "chrome-remote":
                    try {
                        URL url = new URL("http://52.91.78.7:4444/grid/console/wd/hub");
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName(BrowserType.CHROME);
                        desiredCapabilities.setPlatform(Platform.ANY);
                        driverPool.set(new RemoteWebDriver(url, desiredCapabilities));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "chromeheadless":
                    //to run chrome without interface (headless mode)
                    WebDriverManager.chromedriver().version("81").setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setHeadless(true);
                    driverPool.set(new ChromeDriver(options));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;

                default:
                    throw new RuntimeException("Wrong browser name!");
            }
        }
        return driverPool.get();
    }

    public static void closeDriver() {
        if (driverPool != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}
