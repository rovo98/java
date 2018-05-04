package basical.reflect;

/**
 * @author rovo98
 * date: 2018.5.4
 */
public class Student {
    public String name;
    protected int age;
    char sex;
    private String phoneNum;

    @Override
    public String toString() {
        return "Student: [name:" + name + ", age:" + age
                + ", sex:" + sex + ", phoneNum:" + phoneNum + "]";
    }

    // 默认的构造方法
    Student(String str) {
        System.out.println("默认的构造方法 s = " + str);
    }

    // 无参构造方法
    public Student() {
        System.out.println("调用了公有、无参构造方法.");
    }
    // 有一个参数的构造方法
    public Student(char name) {
        System.out.println("姓名: " + name);
    }
    // 多个参数的构造方法
    public Student(char name, int age) {
        System.out.println("姓名: " + name + ", 年龄: " + age);
    }
    // 受保护的构造方法
    protected Student(boolean n) {
        System.out.println("受保护的构造方法执行了.");
    }
    // 私有构造方法
    private Student(int age) {
        System.out.println("私有构造方法执行了 年龄: " + age);
    }

    public void show1(String str) {
        System.out.println("调用了：公有的，String 参数show1()方法.");
    }
    protected void show2() {
        System.out.println("调用了：受保护的，无参的show2()方法.");
    }
    void show3() {
        System.out.println("调用了：默认的，无参的show3()方法");
    }
    private String show4(int age) {
        System.out.println("调用了：私有的，int参数的show4()方法. age = " + age);
        return "test by rovo98";
    }
}
