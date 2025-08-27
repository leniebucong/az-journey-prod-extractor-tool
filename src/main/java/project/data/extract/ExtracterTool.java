package project.data.extract;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import project.data.extract.exceptions.NoApplicationFoundException;
import project.data.extract.models.LocalFilesProp;
import project.data.extract.services.ProdService;
import project.data.extract.util.AppUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

@SpringBootApplication
@PropertySource(value = "classpath:application.properties")
public class ExtracterTool {

	private static ProdService service;
	private static Scanner scanner;
	private static Logger logger = LoggerFactory.getLogger(ExtracterTool.class);
	private static AppUtil appUtil;

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		init(0);
		ConfigurableApplicationContext applicationContext = SpringApplication.run(ExtracterTool.class, args);
		appUtil = applicationContext.getBean(AppUtil.class);
		service = applicationContext.getBean(ProdService.class);
		init(1);

		while(run(applicationContext.getBean(LocalFilesProp.class)));
		scanner.close();
	}


	private static void init(int process) {
		if (process ==0){	//INITIALIZING PROPERTIES
			System.setProperty("java.awt.headless", "false");
			System.setProperty("spring.main.banner-mode", "OFF");

			String propertiesFilePath = System.getProperty("externalProperty");
			if (propertiesFilePath == null){
				System.out.println("Configuration file could not be found");
				System.exit(1);
			}

			Properties properties = new Properties();
			try(FileInputStream fileInputStream = new FileInputStream(propertiesFilePath)){
				properties.load(fileInputStream);
				Set<Object> keys = properties.keySet();
				for (Object key : keys) {
					System.setProperty(key.toString(), properties.get(key).toString());
				}
			}catch(IOException ioException){
				logger.info("[Exception Thrown] in class "+ ExtracterTool.class.getName()+" ("+Thread.currentThread()
						.getStackTrace()[1]
						.getMethodName()+") method -> exception message: "+ioException.getMessage()
						+" ["+ioException.getClass().getName()+"]", ioException);
			}
		}

		else if (process==1) {
			//CLEARING CONSOLE
			appUtil.clearConsole();
		}
	}

	private static Boolean run(LocalFilesProp filesProp)   {
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
