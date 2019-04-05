package basic;

/**
 * 测试Java的值传递和引用传递(结果表明只有值传递)
 *
 * @Author: Liam
 * @Date: 2019/4/5 19:12
 */
public class ByReferenceByValue {
    public static void main(String[] args) {
        // 创建一个对象
        Person p = new Person("person");
        System.out.println("改变对象前：p = " + p.toString());
        Person.changeObject(p);
        System.out.println("changeObject(p): 调用p.set方法");
        // 从改变结果中看出，实际对象内容已经改变
        System.out.println("改变对象后：p = " + p.toString());
        // --------------------------------------------------
        // 创建两个对象
        Person p1 = new Person("person1");
        Person p2 = new Person("person2");
        System.out.println("\n对象交换前：p1 = " + p1.toString());
        System.out.println("对象交换前：p2 = " + p2.toString());
        // 交换p1对象和p2对象
        Person.swapObject(p1, p2);
        System.out.println("swapObject(p1, p2)");
        // 从交换结果中看出，实际对象并未交换
        System.out.println("对象交换后：p1 = " + p1.toString());
        System.out.println("对象交换后：p2 = " + p2.toString());
        // --------------------------------------------------
        // 创建两个基本类型变量
        int i = 0;
        int j = 1;
        System.out.println("\n基本类型变量交换前：a = " + i);
        System.out.println("基本类型变量交换前：b = " + j);
        // 交换a和b
        Person.swapInt(i, j);
        System.out.println("swapInt(a, b)");
        // 从交换结果中看出，实际变量并未交换
        System.out.println("基本类型变量交换后：a = " + i);
        System.out.println("基本类型变量交换后：b = " + j);
        // --------------------------------------------------
        // 建立两个对象数组
        Person[] arraya = new Person[2];
        Person[] arrayb = new Person[2];
        // 分别构造数组对象
        arraya[0] = new Person("persona.0");
        arraya[1] = new Person("persona.1");
        arrayb[0] = new Person("personb.0");
        arrayb[1] = new Person("personb.1");
        System.out.println("\n对象数组交换前：arraya[0] = " + arraya[0].toString() + ", arraya[1] = " + arraya[1].toString());
        System.out.println("对象数组交换前：arrayb[0] = " + arrayb[0].toString() + ", arrayb[1] = " + arrayb[1].toString());
        // 交换这两个对象数组
        Person.swapObjectArray(arraya, arrayb);
        System.out.println("swapObjectArray(arraya, arrayb)");
        // 从交换结果中看出，实际数组并未交换
        System.out.println("对象数组交换后：arraya[0] = " + arraya[0].toString() + ", arraya[1] = " + arraya[1].toString());
        System.out.println("对象数组交换后：arrayb[0] = " + arrayb[0].toString() + ", arrayb[1] = " + arrayb[1].toString());
        // --------------------------------------------------
        // 建立两个普通数组
        int[] a = new int[2];
        int[] b = new int[2];
        // 给数组各元素赋值
        for (int m = 0; m < a.length; m++) {
            a[m] = m;
            b[m] = m + 1;
        }
        System.out.println("\n基本类型数组交换前：a[0] = " + a[0] + ", a[1] = " + a[1]);
        System.out.println("基本类型数组交换前：b[0] = " + b[0] + ", b[1] = " + b[1]);
        // 交换两个基本类型数组
        Person.swapIntArray(a, b);
        System.out.println("swapIntArray(a, b)");
        // 从交换结果中看出，实际数组并未交换
        System.out.println("基本类型数组交换后：a[0] = " + a[0] + ", a[1] = " + a[1]);
        System.out.println("基本类型数组交换后：b[0] = " + b[0] + ", b[1] = " + b[1]);
        // --------------------------------------------------
        // 改变对象数组的内容
        System.out.println("\n对象数组内容交换并改变前：arraya[0] = " + arraya[0].toString() + ", arraya[1] = " + arraya[1].toString());
        System.out.println("对象数组内容交换并改变前：arrayb[0] = " + arrayb[0].toString() + ", arrayb[1] = " + arrayb[1].toString());
        Person.changeObjectArray(arraya, arrayb);
        System.out.println("changeObjectArray(arraya, arrayb): arraya[1]与arrayb[1]互换并赋新值给arraya[1]");
        // 从改变结果中看出，实际数组已被改变
        System.out.println("对象数组内容交换并改变后：arraya[0] = " + arraya[0].toString() + ", arraya[1] = " + arraya[1].toString());
        System.out.println("对象数组内容交换并改变后：arrayb[0] = " + arrayb[0].toString() + ", arrayb[1] = " + arrayb[1].toString());
        // --------------------------------------------------
        // 改变基本类型数组的内容
        System.out.println("\n基本类型数组内容交换并改变前：a[0] = " + a[0] + ", a[1] = " + a[1]);
        System.out.println("基本类型数组内容交换并改变前：b[0] = " + b[0] + ", b[1] = " + b[1]);
        Person.changeIntArray(a, b);
        System.out.println("changeIntArray(a, b): a[1]与b[1]互换并赋新值给a[1]");
        // 从改变结果中看出，实际数组已被改变
        System.out.println("基本类型数组内容交换并改变后：a[0] = " + a[0] + ", a[1] = " + a[1]);
        System.out.println("基本类型数组内容交换并改变后：b[0] = " + b[0] + ", b[1] = " + b[1]);
    }
}

class Person {
    private String name;

    public Person(String x) {
        this.name = x;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    // 更改传递对象内容
    public static void changeObject(Person p) {
        p.setName("changeObject");
    }

    // 交换对象引用
    public static void swapObject(Person p1, Person p2) {
        Person tmp = p1;
        p1 = p2;
        p2 = tmp;
    }

    // 交换基本类型
    public static void swapInt(int a, int b) {
        int tmp = a;
        a = b;
        b = tmp;
    }

    // 交换对象数组
    public static void swapObjectArray(Person[] p1, Person[] p2) {
        Person[] tmp = p1;
        p1 = p2;
        p2 = tmp;
    }

    // 交换基本类型数组
    public static void swapIntArray(int[] x, int[] y) {
        int[] tmp = x;
        x = y;
        y = tmp;
    }

    // 改变对象数组中的内容
    public static void changeObjectArray(Person[] p1, Person[] p2) {
        Person tmp = p1[1];
        p1[1] = p2[1];
        p2[1] = tmp;
        // 再将p1[1]修改
        Person p = new Person("person");
        p1[1] = p;
    }

    // 改变基本类型数组中的内容
    public static void changeIntArray(int[] x, int[] y) {
        int tmp = x[1];
        x[1] = y[1];
        y[1] = tmp;
        x[1] = 5;
    }
}
