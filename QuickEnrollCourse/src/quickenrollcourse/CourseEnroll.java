package getmycourse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CourseEnrol {

	@Parameters({"studentNum", "password", "courseId", "semester"})
	@Test
	public void enroll(String studentNum, String password, String courseId, String semester) {
	   
	    int i = 10;
	    boolean success = false;
	    while(!success && i > 0) {
	    	// declaration and instantiation of objects/variables
	    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\pan\\Desktop\\help\\selenium\\demo\\chromedriver.exe");
	    	WebDriver driver = new ChromeDriver();	
	    	driver.get("https://sws.rosi.utoronto.ca/sws/auth/login.do?main.dispatch");
	    	WebElement usernameField = driver.findElement(By.xpath(".//*[@id='personId']"));
	    	usernameField.sendKeys(studentNum);
	    	WebElement passwordField = driver.findElement(By.xpath(".//*[@id='pin']"));
	    	passwordField.sendKeys(password);
	    	driver.findElement(By.xpath(".//*[@id='personForm']/table/tbody/tr[3]/td[2]/input[2]")).click();
	    	driver.findElement(By.xpath("//a[contains(@href,\"/sws/reg/main.do?main.dispatch\")]")).click();;			
	    	WebElement courseIDField = driver.findElement(By.xpath(".//*[@id='code']"));
	    	courseIDField.sendKeys(courseId);
	    	WebElement semesterField = driver.findElement(By.xpath(".//*[@id='sectionCode']"));
	    	semesterField.sendKeys(semester);
	    	driver.findElement(By.xpath(".//*[@id='courseForm']/table/tbody/tr[3]/td[2]/input")).click();
	    	if(driver.findElements(By.className("button")).get(0).getAttribute("value").contains("Add")) {
	    		driver.findElements(By.className("button")).get(0).click();
	    		success = true;
	    		System.out.println("Congratulations" + studentNum + "Success enrolled in" + courseId + semester);
	    	}
	    	i--;
	    	driver.close();
	    }
	    Assert.assertTrue(success);
	}
}
