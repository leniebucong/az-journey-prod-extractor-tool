package project.data.extract;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;


public abstract class Sample{
     abstract String getFieldName(String field);

     void getInsertStatement(List<Child> object){
        TreeMap<String, TreeSet<String>> values = new TreeMap<>();
        StringBuilder selectStatement = new StringBuilder();



        object.stream().forEach(obj ->{
            String selectRoot = "SELECT * FROM "+obj.getClass().getSimpleName()+" WHERE ";
            try {
                StringBuilder selectCondition = new StringBuilder();;

                Class<?> thisClass = obj.getClass();
                for (Field field : thisClass.getDeclaredFields()) {
                    field.setAccessible(true);
                    String fieldName = obj.getFieldName(field.getName());
                    Object o = field.get(obj);
                    System.out.println(fieldName + o);

                    if (o==null) continue;

                    System.out.println(fieldName+o);

                    if (!fieldName.equals(field.getName())){
                        if (values.containsKey(fieldName)){
                            values.get(fieldName).add("'" + o + "'");
                        }else values.put(fieldName, new TreeSet<>());
                    }
                }

                for (String key : values.keySet()){
                    System.out.println(key);
                    System.out.println(values.get(key));
                    if (values.get(key).size()>1){
                        StringBuilder vs = new StringBuilder();
                        values.get(key).stream().forEach(v -> vs.append(vs == null ? v : v+","));
                        selectCondition.append(selectCondition.length()==0 ?
                                key+" in ("+vs.substring(0,vs.length()-1)+")" :
                                " AND "+key+" in ("+vs.substring(0,vs.length()-1)+")");
                        continue;
                    }
                    selectCondition.append(
                            selectCondition==null ?
                            key+" = "+values.get(key).first() :
                            " AND "+key+" = "+values.get(key).first());
                }

                selectStatement.append(selectRoot).append(selectCondition);
                System.out.println(selectStatement);

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });


     }

     @Test
     public void sampletest(){
         System.out.println(String.format("%.0f", Integer.parseInt("50000")*Double.parseDouble("1.25")));
         System.out.println(Integer.parseInt("50000")*Double.parseDouble("1.25"));
     }

}
