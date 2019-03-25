package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 一个反射的例子
 *
 * @Author: Liam
 * @Date: 2019/3/25 17:51
 */
public class Reflect {
    public static void main(String[] args) throws Exception {
        ReflectBean bean = new Reflect().new ReflectBean();
        bean.setId(100);
        bean.setAddress("北京");
        //得到类对象
        Class userCla = bean.getClass();
        //得到类中的所有属性集合
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            //设置此属性是可以访问的
            f.setAccessible(true);
            //得到此属性的值
            Object val = f.get(bean);
            System.out.println("name:" + f.getName() + "\t value = " + val);
            //得到此属性的类型
            String type = f.getType().toString();
            if (type.endsWith("String")) {
                System.out.println(f.getType() + "\t是String");
                //给属性设值
                f.set(bean, "12");
            } else if (type.endsWith("int") || type.endsWith("Integer")) {
                System.out.println(f.getType() + "\t是int");
                //给属性设值
                f.set(bean, 12);
            } else {
                System.out.println(f.getType() + "\t");
            }
        }
        //得到类中的方法
        Method[] methods = userCla.getMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if (method.getName().startsWith("get")) {
                System.out.print("methodName:" + method.getName() + "\t");
                //得到get方法的值
                System.out.println("value:" + method.invoke(bean));
            }
        }
    }

    class ReflectBean {
        private Integer id;
        private int age;
        private String name;
        private String address;

        public ReflectBean() {
            System.out.println("实例化");
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}