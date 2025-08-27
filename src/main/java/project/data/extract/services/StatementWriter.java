package project.data.extract.services;

import org.springframework.stereotype.Service;
import project.data.extract.models.LocalFilesProp;

import java.io.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class StatementWriter {

    public String getFieldName(String field){return field;};

    /*ORIGINAL METHOD*/
    public Map<String,Map<String, String>> writeAllTable(LocalFilesProp filesProp){

        //OBJECT USED FOR GENERATING SELECT QUERY
        Map<String,Map<String, String>> tables = new TreeMap<>();   //return value
        Map<String, String> fieldsForCondition = new TreeMap<>();   //fields for where clause

        //GENERATING INSERT QUERY
        try {
            //Print writer for 1 file only
            PrintWriter writerForInsertStatement = new PrintWriter(new FileWriter(filesProp.getInsert_queries_file(), true));

            StringBuilder insertKeys = new StringBuilder();
            StringBuilder insertValues = new StringBuilder();

            Class<?> thisClass = this.getClass();
            String tableName = thisClass.getSimpleName();

            //Write insert statement to each table
            File file = new File(filesProp.getInsert_scripts_dir()+"/"+tableName+ ".sql");
            PrintWriter tableWriter = new PrintWriter(new FileWriter(file, true));

            //END WRITE PER EACH TABLE

                //Getting fieldNames and Values
                for (Field field : thisClass.getDeclaredFields()) {
                    field.setAccessible(true);

                    String ifn = this.getFieldName(field.getName());    //Field Name
                    Object value = field.get(this);                     //Value of the field

                    if (value==null) continue;

                    if (!ifn.equals(field.getName()))
                        fieldsForCondition.put(ifn, value.toString());

                    String valueString = "";

                    if (field.getType().equals(Date.class))
                        valueString = "TO_DATE('" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value) + "', 'YYYY-MM-DD HH24:MI:SS')";

                    else
                        valueString = "'" + value + "'";

                    insertKeys.append(ifn);
                    insertKeys.append(",");

                    insertValues.append(valueString);
                    insertValues.append(",");
                }

            String insertStatement = insertKeys == null ? null : "INSERT INTO "+tableName+" (" + insertKeys.substring(0, (insertKeys.length() - 1)) + ") VALUES (" + insertValues.substring(0, (insertValues.length() - 1)) + "); COMMIT;";

            writerForInsertStatement.println(insertStatement);
            tableWriter.println(insertStatement);
            writerForInsertStatement.close();
            tableWriter.close();

            tables.put(tableName, fieldsForCondition);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return tables;
    }


    public void init(String rootDir, String pol) throws IOException {
        File file = new File(rootDir+this.getClass().getSimpleName() + ".sql");
        PrintWriter writer = new PrintWriter(new FileWriter(file, true));
        writer.println("\n\n-- POLICY NUMBER: "+pol);
        writer.close();
    };

    public void getSelect(List<Map<String,Map<String, String>>> tables, LocalFilesProp filesProp) throws IOException {

        String selectSql = filesProp.getSelect_queries_file();

        PrintWriter fileWriter = new PrintWriter(new FileWriter(selectSql, false));
        fileWriter.println("--Generated "+ LocalDateTime.now());

        //TreeMap< tablename , Map <fieldnames , TreeSet of values>>
        TreeMap<String,Map<String, TreeSet<String>>> selectData = new TreeMap<>();

        for (Map<String, Map<String, String>> table : tables) {
            if (table==null) continue;
            for (String tableName : table.keySet()) {
                if (!selectData.containsKey(tableName))
                    selectData.put(tableName, new TreeMap<>());

                Map<String, TreeSet<String>> fieldAndValues = selectData.get(tableName);
                for (String field : table.get(tableName).keySet()) {
                    if (!fieldAndValues.containsKey(field)){
                        fieldAndValues.put(field, new TreeSet<>());
                    }
                    fieldAndValues.get(field).add(table.get(tableName).get(field));
                }
            }
        }

        //GENERATING SELECT QUERY
        for (String table : selectData.keySet()) {
            String root = " SELECT * FROM "+table+"@DSS WHERE ";
            StringBuilder select = new StringBuilder();
            select.append(root);

            StringBuilder condition = new StringBuilder();
            for (String key : selectData.get(table).keySet()) {
                condition.append(condition.length()==0? key : " AND "+key+" ");
                TreeSet<String> values = selectData.get(table).get(key);
                String vals = null;
                if (values.size()>1){
                    for (String value : values)
                        vals = vals == null ? value : vals +","+value;
                    condition.append(" in ("+vals+")");
                }else condition.append(" = "+values.first());

            }
            select.append(condition);
            select.append(";");
            fileWriter.println(select);
        }
        fileWriter.close();
    }

}
