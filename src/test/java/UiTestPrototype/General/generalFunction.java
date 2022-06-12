package UiTestPrototype.General;

import UiTestPrototype.configs.VariableConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Properties;




@Component
public class generalFunction{

    private ThreadLocal<WebDriver> webDriver ;
    public static long PAGE_LOAD_TIMEOUT = 20;
    public static long IMPLICIT_WAIT = 10;
    public static WebDriver driver ;
    public static Properties prop ;
    public static String BrowserName;
    private VariableConfig Conf = new VariableConfig();



    public generalFunction(){

        }

    public void propertySet() throws IOException {
        driver = null ;
        BrowserName = System.getProperty("browser","firefox");

        try {
            prop = new Properties();
            loadProperties(new File(readFileFromResources("src/test/java/UiTestPrototype/configs/config.properties")));
        } catch (IOException exp) {
            exp.printStackTrace();
        }
        switch (BrowserName) {
            case "chrome":
                prop.setProperty("navigator","chrome");
                break;
            case "firefox":
                prop.setProperty("navigator","firefox");
                break;
        }
        prop.store(new FileOutputStream(new File(readFileFromResources("src/test/java/UiTestPrototype/configs/config.properties"))), "update property file ");
    }

    public void loadProperties(File file) throws IOException {
        prop.load(new FileInputStream(file));
    }

    //find the property file from the path and return exact Path
    public String readFileFromResources(String uri) throws FileNotFoundException {
        File toReturn = ResourceUtils.getFile(uri);
        boolean exist = toReturn.exists();
        if (!exist) {
            try {
                toReturn = new File(getClass().getClassLoader().getResource(uri).toURI());
            }catch (URISyntaxException exp)
            {
                exp.printStackTrace();
            }
        }
        return toReturn.getAbsolutePath();
    }

    public void getTheDriver(){
        if(BrowserName.equals(("chrome"))) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        }else if (BrowserName.equals(("firefox"))) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else if (BrowserName.equals(("edge"))) {
            WebDriverManager.edgedriver().setup();
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }
    public void openUrl(String ult ) throws IOException {
        driver.get(ult);
    }

    public static void removeDriver(){
        if(driver != null){
            driver.quit();
            driver = null ;
        }
    }
}
