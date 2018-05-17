import java.io.File;
import java.net.URL;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

  public static void main(String[] args) {
    URL url = ClassLoader.getSystemResource("");
    System.out.println(url.getPath());
    System.setProperty("webdriver.chrome.driver", url.getPath() + "driver" + File.separator + "chromedriver.exe");

    ChromeDriver driver = new ChromeDriver();

    driver.get("http://www.baidu.com/ ");

    // Find the text input element by its name

    // Check the title of the page
    System.out.println("Page title is: " + driver.getTitle());

    driver.close();
  }
}
