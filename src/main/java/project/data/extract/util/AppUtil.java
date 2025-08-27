package project.data.extract.util;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

@Service
public class AppUtil {
    private Logger logger = LoggerFactory.getLogger(AppUtil.class);

    public void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            logger.error("[Exception Thrown] in class "+this.getClass().getName()+" ("+Thread.currentThread()
                    .getStackTrace()[1]
                    .getMethodName()+") method -> exception message: "+e.getMessage() +" ["+e.getClass().getName()+"]", e);
        }
    }
}
