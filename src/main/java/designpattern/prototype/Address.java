package designpattern.prototype;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Address implements Cloneable {
    private String city;
    private String detail;

    public Address(String city, String detail) {
        this.city = city;
        this.detail = detail;
    }

    @Override
    public Address clone() {
        try {
            // Address 内部只有 String 字段，String 不可变，直接使用 Object.clone() 的浅拷贝即可。
            return (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            // Address 已经实现 Cloneable，正常情况下不会进入这里。
            throw new AssertionError(e);
        }
    }

    @Override
    public String toString() {
        return "Address{" +
            "city='" + city + '\'' +
            ", detail='" + detail + '\'' +
            '}';
    }
}
