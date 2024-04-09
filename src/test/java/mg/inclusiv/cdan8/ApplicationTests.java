package mg.inclusiv.cdan8;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
		functionTest();
	}

	public void functionTest(){
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		// Ouvrir la page web
		driver.get("http://localhost:9000");


		//test inscription
		WebElement signUpLink = driver.findElement(By.xpath("/html/body/form/p/a"));
		signUpLink.click();

		await(3);

		WebElement firstnameInput = driver.findElement(By.name("firstname"));
		firstnameInput.sendKeys("Jon");

		WebElement lastnameInput = driver.findElement(By.name("lastname"));
		lastnameInput.sendKeys("Walker");

		WebElement usernameInput = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		usernameInput.sendKeys("jon");

		WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		passwordInput.sendKeys("12345");

		await(1);

		WebElement btnRegister = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/form/button"));
		btnRegister.submit();

		await(2);

		//test connexion
		WebElement usernameInputLogin = driver.findElement(By.xpath("/html/body/form/div[1]/input"));
		usernameInputLogin.sendKeys("jon");

		WebElement passwordInputLogin = driver.findElement(By.xpath("/html/body/form/div[2]/input"));
		passwordInputLogin.sendKeys("12345");

		await(1);

		WebElement btnLogin = driver.findElement(By.xpath("/html/body/form/button"));
		btnLogin.submit();

		//test ajout tache
		WebElement taskInput1 = driver.findElement(By.name("name"));
		taskInput1.sendKeys("tache 1");

		await(1);

		WebElement btnAddTask1 = driver.findElement(By.xpath("/html/body/form/div/div/button"));
		btnAddTask1.submit();

		await(2);

		WebElement taskInput2 = driver.findElement(By.name("name"));
		taskInput2.sendKeys("tache 2");

		await(1);

		WebElement btnAddTask2 = driver.findElement(By.xpath("/html/body/form/div/div/button"));
		btnAddTask2.submit();

		await(2);

		//test changer l'état du premier tache
		List<WebElement> tasks = driver.findElements(By.className("task"));
		tasks.get(0).click();

		await(2);

		//test suppression tache
		List<WebElement> linkDeletes = driver.findElements(By.className("delete"));
		linkDeletes.get(0).click();

		await(2);

		//test deconnexion
		List<WebElement> btnLogout = driver.findElements(By.xpath("/html/body/div[1]/a"));
		btnLogout.get(0).click();


	}

	//Timer pour bien visualiser les actions
	public void await(long seconds){
		try {
			Thread.sleep(seconds * 1000);
		}catch (InterruptedException e){
			System.out.println("Interrupted");
		}
	}

}
