package basical.reflect;

import java.lang.reflect.Method;

/**
 * @author rovo98
 * date: 2018.5.4
 */
public class MethodReflection {
    /**
     * 通过反射利用Class对象获取某个类的方法：
     * 1) 批量获取方法
     *      public Method[] getMethods() -> 获取所有公有成员方法
     *      public Method[] getDeclaredMethods() -> 获取所有方法
     *
     * 2) 获取单个方法
     *      public Method getMethod(String name, Class<?> ... parameterTypes)
     *      public Method getDeclaredMethod(String name, Class<?> ... parameterTypes)
     *
     *3) 调用方法
     *          Object <- invoke(Object obj, Object ... args);
     *          obj - > 调用方法的对象
     *          args -> 调用方法需要传递的实参
     */
    public static void main(String[] args) {
        try {
            Class<?> stuClass = Class.forName("basical.reflect.Student");

            System.out.println("******************获取所有公有方法*****************");
            Method[] methods = stuClass.getMethods();
            for (Method m : methods) {
                System.out.println(m);
            }

            System.out.println("*****************获取所有方法**********************");
            methods = stuClass.getDeclaredMethods();
            for (Method m : methods) {
                System.out.println(m);
            }

            Object stu = stuClass.getConstructor().newInstance();
            System.out.println("************************获取单个公有方法****************");
            Method m = stuClass.getMethod("show1", String.class);
            System.out.println(m);
            m.invoke(stu, "test by rovo98! ");

            System.out.println("***********************获取单个私有方法*****************");
            m = stuClass.getDeclaredMethod("show4", int.class);
            System.out.println(m);
            m.setAccessible(true);
            Object result = m.invoke(stu, 21);
            System.out.println("returned : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
