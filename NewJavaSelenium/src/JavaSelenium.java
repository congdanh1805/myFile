import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JavaSelenium {
	
	public  static void myTestCase() throws InterruptedException{
		
		
		System.setProperty("webdriver.chrome.driver","C:\\chrome\\chromedriver.exe");
		 WebDriver driver = new ChromeDriver();
		 //create url
		 String url = "https://blueprint-test.cyberlogitec.com.vn/";
		 //get current title of website
		 //open URL
		 driver.manage().window().maximize();
		 driver.get(url);
		 //Step 1:Login
		 WebElement userInput;
		 WebElement userPass;
		 WebElement loginButton;
		 
		 userInput = driver.findElement(By.xpath("//div[@class='form-group material-control']//input[@type='text']"));
		 userPass = driver.findElement(By.xpath("//div[@class='form-group material-control']//input[@type='password']"));
		 loginButton = driver.findElement(By.xpath("//button[@name='login']"));
		 
		 userInput.click();
		 userInput.sendKeys("nguyenlh");
		 userPass.click();
		 userPass.sendKeys("1111");
		 loginButton.click();
		 
		 //get URL
		 String currentURL = driver.getCurrentUrl();
		 String desireURL = "https://blueprint-test.cyberlogitec.com.vn/";
		 //verify user image is display
		 WebElement userImg = driver.findElement(By.xpath("//img[@id='menu-user-image']"));
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		 if(currentURL.equals(desireURL)) {
			if(userImg.isDisplayed()) {
			System.out.println("Step 1 Login: Passed !");
			}
		 }
	     else{
			 System.out.println("Step 1 Login: Failed !");
			 Thread.sleep(3000);
			 driver.close();
		 }
		 //Step 2:Navigate to requirement page
		 Thread.sleep(3000);
		 WebElement requireIcon = driver.findElement(By.xpath("//div[@class='webix_scroll_cont']//div[@webix_tm_id='menu-0']"));
		 wait.until(ExpectedConditions.visibilityOf(requireIcon));
		 requireIcon.click();
		 Thread.sleep(5000);
		 WebElement requireLink = driver.findElement(By.xpath("//div[@view_id='$layout24']/div[2]//a[@webix_l_id='menu-1']"));
		 wait.until(ExpectedConditions.visibilityOf(requireLink));
		 WebElement requirePopUp = driver.findElement(By.xpath("//div[@view_id='$popup1']"));
		 String expectedDisplay = "block";
		 if(requirePopUp.getCssValue("display").equals(expectedDisplay)) {
			 requireLink.click();
		 }
		 else {
			 System.out.println("Step 2 Failed !");
			 Thread.sleep(5000);
			 driver.close();
		 }
		 //verify title and URL
		 String requireCurrentTitle = driver.getTitle();
		 String requireDesireTitle = "Requirement";
		 String currentRequireURL = driver.getCurrentUrl();
		 String desireRequireURL = "https://blueprint-test.cyberlogitec.com.vn/UI_PIM_001";
		 if(currentRequireURL.equals(desireRequireURL)) {
			 if(requireCurrentTitle.equals(requireDesireTitle)) {
				 System.out.println("Step 2 Navigate to requirement page: Passed !");
			 }
		 }
		 else {
			 System.out.println("Step 2 Navigate to requirement page: Failed !");
			Thread.sleep(5000);
			 driver.close();
		 }
		
		 //Step 3:Select project
		 Thread.sleep(5000);
		 WebElement prjCbb = driver.findElement(By.xpath("//div[@view_id='projectCbb']"));
		 wait.until(ExpectedConditions.visibilityOf(prjCbb));
		 if(prjCbb.isDisplayed() == true) {
			 prjCbb.click();
		 }
		 else {
			 System.out.println("Step 3 Select project: Failed !");
			 Thread.sleep(5000);
			 driver.close();
		 }
		 List<WebElement> prjectOpts;
		 Thread.sleep(5000);
		 prjectOpts = driver.findElements(By.xpath("//div[@view_id='projectCbbData']/div//div[@role='option']"));
		 wait.until(ExpectedConditions.visibilityOfAllElements(prjectOpts));
		 String requireType = "Testing";
		 for(WebElement opt:prjectOpts) {
			 String temp = opt.getText();
			 if(temp.equals(requireType)) {
				 opt.click();
				 Thread.sleep(5000);
				 break;
			 }
		 }
		 WebElement inputPrjValue = driver.findElement(By.xpath("//div[@view_id='projectCbb']//input"));
		 if(inputPrjValue.getAttribute("value").equals(requireType)) {
			 System.out.println("Step 3 Select project: Passed !");
		 }
		 else {
			 System.out.println("Step 3 Select project: Failed !");
			 Thread.sleep(5000);
			 driver.close();
		 }
		 //Step 4:Click new task
		 WebElement newtskButt = driver.findElement(By.xpath("//div[@view_id='btn_3']//button"));
		 wait.until(ExpectedConditions.visibilityOf(newtskButt));
		 newtskButt.click();
		 //Verify that one element in new task pop-up is displayed
		 Thread.sleep(5000);
		 WebElement newtskPopUp = driver.findElement(By.xpath("//div[@class='webix_win_body']//div[@view_id='$layout42']"));
		 WebElement jbTypCbb = driver.findElement(By.xpath("//div[@class='webix_win_body']//div[@view_id='$layout42']//div[@view_id='cbbJbTp']//input"));
		 if(newtskPopUp.isDisplayed() == true) {
			 if(jbTypCbb.isDisplayed() == true) {
			   System.out.println("Step 4 Click new task: Passed !");
			 }
		 }
		 else {
			 System.out.println("Step 4 Click new task: Failed !");
			 Thread.sleep(5000);
			 driver.close();
		 }
		 //Step 5:Select Job type
		 jbTypCbb.click();
		 String jbtype = "Documentation";
		 List<WebElement> jbTypes = driver.findElements(By.xpath("//div[@view_id='$suggest15']//div[@role='option']"));
		 for(WebElement opt:jbTypes) {
			 if(opt.getText().equals(jbtype)) {
				 opt.click();
				 break;
			 }
		 }
		 //verify value in combobox jobtype  that has been clicked again String jbtype
		 if(jbTypCbb.getAttribute("value").equals(jbtype)) {
			 System.out.println("Step 5 Select Job type: Passed !");
		 }
		 else {
			 System.out.println("Step 5 Select Job type: Failed !");
			 Thread.sleep(5000);
			 driver.close();
		 }
		//Step 6:Select process type
		 WebElement prcssCbb = driver.findElement(By.xpath("//div[@view_id='cbbProc']//input"));
		 prcssCbb.click();
		 List<WebElement> prcssTypes = driver.findElements(By.xpath("//div[@view_id='$suggest18']//div[@view_id='$suggest18_list']/div//div"));
		 String prcsstype = "Test Execution";
		 for(WebElement opt:prcssTypes) {
			 String temp = opt.getText();
			 if(temp.equals(prcsstype)) {
				 opt.click();
				 break;
			 }
		 }
		//verify value in combobox process type  that has been clicked again String prcsstype
		 String prcssCbbValue = prcssCbb.getAttribute("value");
		 if(prcssCbb.getAttribute("value").equals(prcsstype)) {
			 System.out.println("Step 6 Select process type: Passed !");
		 }
		 else {
			 System.out.println("Step 6 Select process type: Failed !");
			 Thread.sleep(5000);
			 driver.close();
		 }
		 //Step 7:Input title
		 WebElement ttlInput = driver.findElement(By.xpath("//div[@view_id='txtTit']//textarea"));
		 String ttl = "Execute Automation Test";
		 ttlInput.click();
		 ttlInput.sendKeys(ttl);
		 //verify data is inputed
		 if(ttlInput.getAttribute("value").equals(ttl)) {
			 System.out.println("Step 7 Input title: Passed !");
		 }
		 else {
			 System.out.println("Step 7 Input title: Failed !");
			 Thread.sleep(5000);
			 driver.close();
		 }
		 //Step 8:Input cotent
		 WebElement cntInput = driver.findElement(By.xpath("//div[@view_id='txtCntn']/div[@role='textbox']"));
		 String cnt = "Automation Content";
		 cntInput.click();
		 cntInput.sendKeys(cnt);
		 //verify data is inputed
		 if(cntInput.getText().equals(cnt)) {
			 System.out.println("Step 8 Input cotent: Passed !");
		 }
		 else {
			 System.out.println("Step 8 Input cotent: Failed !");
			 Thread.sleep(5000);
			 driver.close();
		 }
		 String cntInputText = cntInput.getText();
		 //Step 9:Click submit button
		 WebElement sbmitButt = driver.findElement(By.xpath("//button[@id='btnSubmit']"));
		 sbmitButt.click();
		 Thread.sleep(5000);
		 WebElement tskId = driver.findElement(By.xpath("//div[@view_id='reqTitNm']/div/input"));
		 WebElement frstRow = driver.findElement(By.xpath("//div[@view_id='gridReq']//div[@role='rowgroup']/div/div"));
		 String tskIdValue = tskId.getAttribute("value");
		 wait.until(ExpectedConditions.visibilityOf(tskId));
		 wait.until(ExpectedConditions.visibilityOf(frstRow));
		 if(tskId != null) {
		   if(tskId.getAttribute("value").endsWith(frstRow.getText()) == true) {
		     System.out.println("Step 9 Click submit button: Passed !");
		     }
		   else {
				 System.out.println("Step 9 Click submit button: Failed !");
				 Thread.sleep(5000);
				 driver.close();
			 }
		 }
		

		 //Step 10:Double click new task
		 //Get data to verify with information in newtask
		 WebElement ttlRequirePage = driver.findElement(By.xpath("//div[@view_id='gridReq']//div[@column='5']/div/div/div"));
		 WebElement jobRequirePage = driver.findElement(By.xpath("//div[@view_id='gridReq']//div[@role='rowgroup']//div[@role='gridcell']/span"));
		 String ttlRequirePageText = ttlRequirePage.getText();
		 String jobRequirePageText = jobRequirePage.getText();

		 Actions action = new Actions(driver);
		 action.doubleClick(frstRow).perform();
		 Thread.sleep(5000);
		 Set<String> allTabs = driver.getWindowHandles();
		 if(allTabs.size() > 1) {
			 System.out.println("Step 10 Double click new task: Passed !");
		 }
		 else {
			 System.out.println("Step 10 Double click new task: Failed !");
			 Thread.sleep(5000);
			 driver.close();
		 }
		 //Step 11:Switch to second tab
		 String currentTab = driver.getWindowHandle();
		 for(String tab:allTabs) {
			 if(currentTab.equals(tab) == false) {
				 driver.switchTo().window(tab);
				 System.out.println("Step 11 Switch to second tab: Passed !");
			 }
		 }
		 //Step 12:Verify data
		 //Get data from newtask
		 WebElement tskIdNewTsk = driver.findElement(By.xpath("//div[@class='mtos-header']//div[@id='mtos-layout-name']"));
		 WebElement jbTypeNewTsk = driver.findElement(By.xpath("//div[@id='jbTpNmDisp']"));
		 WebElement prcssNewTask = driver.findElement(By.xpath("//div[@view_id='processZone']//div[@class='ellipsisStyle importance-info-content']"));
		 WebElement ttlNewTask = driver.findElement(By.xpath("//div[@id='reqTitNmDisp']"));
		 WebElement cntNewTask = driver.findElement(By.xpath("//div[@id='reqCntn']/div[@id='txtCtntDisp']/p/span"));
		 if(tskIdNewTsk.getText().equals(tskIdValue)) {
			 if(jbTypeNewTsk.getText().startsWith(jobRequirePageText)) {
				if(prcssCbbValue.equals( prcssNewTask.getText())) {
					if(ttlRequirePageText.equals(ttlNewTask.getText())) {
						if(cntInputText.equals(cntNewTask.getText())) {
							System.out.println("Step 12 Verify data: Passed !");
							System.out.println("All step has been passed, Test case result : Passed !");
							 Thread.sleep(5000);
							 driver.quit();
						}
					}
				}
			 }
		 }
		 else {
			 System.out.println("Step 12 Verify data: Failed !");
			 Thread.sleep(5000);
			 driver.close();
		 }
	}

	public static void main(String[] args) throws InterruptedException {
		myTestCase();
		
	}
}
