package basical.reflect;

import java.lang.reflect.Field;

/**
 * @author rovo98
 * date: 2018.5.4
 */
public class FieldsReflection {
    /**
     * 通过反射利用Class对象获取某个类的成员变量
     *
     * 1) 批量获取成员变量
     *      public Field[] getFields() -> 获取所有公有成员变量
     *      public Field[] getDeclaredFields() -> 获取所有成员变量
     * 2) 获取单个成员变量
     *      public Field getField(String fieldName)
     *      public Field getDeclaredField(String fieldName)
     * 3) 对成员变量进行赋值:
     *      set(Object obj, Object value);
     *
     *      obj - > 要设置的字段所在的对象
     *      value -> 要设置字段的值
     */
    public static void main(String[] args) {
        try {
            Class<?> stuClass = Class.forName("basical.reflect.Student");

            System.out.println("**********************获取所有公有成员变量**************");
            Field[] fields = stuClass.getFields();
            for (Field f : fields) {
                System.out.println(f);
            }

            System.out.println("*********************获取所有成员变量*******************");
            fields = stuClass.getDeclaredFields();
            for (Field f : fields) {
                System.out.println(f);
            }

            Object stu = stuClass.getConstructor().newInstance();
            System.out.println("********************获取单个公有成员变量******************");
            Field f = stuClass.getField("name");
            System.out.println(f);
            f.set(stu, "rovo98");
            System.out.println("验证赋值: " + stu.toString());

            System.out.println("*******************获取单个私有成员变量********************");
            f = stuClass.getDeclaredField("phoneNum");
            System.out.println(f);
            f.setAccessible(true);
            f.set(stu, "8888888-888");
            System.out.println("验证赋值: " + stu.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
