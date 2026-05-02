package designpattern.prototype;

public class PrototypeDemo {
    public static void main(String[] args) {
        showShallowCopy();
        showDeepCopy();
    }

    private static void showShallowCopy() {
        Address address = new Address("北京", "朝阳区");
        ShallowCopyOrder template = new ShallowCopyOrder("TEMPLATE", "USER_001", 100, address);
        ShallowCopyOrder copied = template.clone();

        copied.setOrderNo("ORDER_001");
        copied.getAddress().setCity("上海");

        System.out.println("浅拷贝原对象：" + template);
        System.out.println("浅拷贝新对象：" + copied);
    }

    private static void showDeepCopy() {
        Address address = new Address("北京", "朝阳区");
        DeepCopyOrder template = new DeepCopyOrder("TEMPLATE", "USER_001", 100, address);
        DeepCopyOrder copied = template.clone();

        copied.setOrderNo("ORDER_002");
        copied.getAddress().setCity("上海");

        System.out.println("深拷贝原对象：" + template);
        System.out.println("深拷贝新对象：" + copied);
    }
}
