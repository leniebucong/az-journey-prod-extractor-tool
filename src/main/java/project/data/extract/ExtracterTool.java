package project.data.extract;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import project.data.extract.exceptions.NoApplicationFoundException;
import project.data.extract.models.LocalFilesProp;
import project.data.extract.services.ProdService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

@SpringBootApplication
@PropertySource(value = "classpath:application.properties")
public class ExtracterTool {

	private static ProdService service;
	public static void main(String[] args) throws IOException {
		System.setProperty("java.awt.headless", "false");
		System.setProperty("spring.main.banner-mode", "OFF");
		ConfigurableApplicationContext run = SpringApplication.run(ExtracterTool.class, args);
		LocalFilesProp filesProp = run.getBean(LocalFilesProp.class);
		service = run.getBean(ProdService.class);
		while(run(filesProp));
	}


	private static Boolean run(LocalFilesProp filesProp)   {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nEnter Application Number : ");
		String appl = scanner.nextLine();
		appl = appl.replaceAll("\\D", "");

			try{
				filesProp.setAppl_dir(appl);
				System.out.println("Extracting "+appl+"...");
				service.extractApp(appl);
				System.out.println("Extracted Successfully\nFile location: "+ service.getFilesProp().getAppl_dir());
			}catch(NoApplicationFoundException e){
				System.out.println(e.getMessage());
				run(filesProp);
			}catch(IOException e){
				System.out.println(e.getMessage());
				System.exit(1);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}

		return true;
	}

}
