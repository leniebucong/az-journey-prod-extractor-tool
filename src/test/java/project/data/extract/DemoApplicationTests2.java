package project.data.extract;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;


class DemoApplicationTests2 {


    @Test
    public void test() throws NoSuchFieldException, IllegalAccessException {
        Field annotations = Child.class.getDeclaredField("name");
        annotations.setAccessible(true);
        System.out.println(annotations.getName());
    }



}
