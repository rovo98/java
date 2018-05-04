package basical.reflect;

import java.lang.reflect.Constructor;

/**
 * @author rovo98
 * date: 2018.5.4
 *
 *
 */
public class ConstructorReflection {
    /**
     * 通过反射利用Class对象获取某个类中的构造方法：
     * 1) 批量获取构造方法：
     *      public Constructor[] getConstructors() -> 获取所有公有构造方法
     *      public Constructor[] getDeclaredConstructors() -> 获取所有构造方法
     * 2) 获取单个构造方法:
     *      public Constructor getConstructor(Class<?> ... parameterTypes)
     *      public Constructor getDeclaredConstructor(Class<?> ... parameterTypes)
     * 3) 调用构造方法：
     *      Object - > newInstance(Object ... initArgs);
     */
    public static void main(String[] args) {
        try {
            // 获取Student 类的类类型对象
            Class stuClass = Class.forName("basical.reflect.Student");

            System.out.println("****************获取所有公有构造方法*****************");
            Constructor[] constructors = stuClass.getConstructors();
            for (Constructor c : constructors) {
                System.out.println(c);
            }

            System.out.println("****************获取所有构造方法********************");
            constructors = stuClass.getDeclaredConstructors();
            for (Constructor c : constructors) {
                System.out.println(c);
            }

            System.out.println("****************获取单个公有构造方法*****************");
            Constructor c = stuClass.getConstructor(null);
            System.out.println(c);
            Object stu = c.newInstance();

            System.out.println("****************获取单个私有构造方法******************");
            c = stuClass.getDeclaredConstructor(int.class);
            System.out.println(c);
            c.setAccessible(true);
            stu = c.newInstance(21);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
