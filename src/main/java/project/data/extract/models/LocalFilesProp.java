package project.data.extract.models;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.io.File;
import java.io.IOException;

/**
 * Local Files Properties
 * Defines and initializes set directories
 * @author Lenie.Gonzales
 */
@Data @Configuration
@ConfigurationProperties(prefix = "project.local")
public class LocalFilesProp {
    private String root_dir,
            getInsert_scripts_dir ,
            appl_dir;
    private String extracted_details_file,
            insert_queries_file,
            select_queries_file,
            insert_scripts_dir;

    /**
     * Creates output sql files and creates output folder
     * @param applicationNumber application/policy number on which data is to be extracted
     * @throws IOException
     */
    public void setAppl_dir(String applicationNumber) throws IOException {
        appl_dir = root_dir +applicationNumber+"/";
        this.select_queries_file = appl_dir+"select_scripts-"+applicationNumber+".sql";
        this.insert_scripts_dir=appl_dir+"tables/";
        this.insert_queries_file = appl_dir+"insert_scripts-"+applicationNumber+".sql";

        new File(insert_scripts_dir).mkdirs();
        new File(insert_queries_file).createNewFile();
        new File(select_queries_file).createNewFile();
    }
}
