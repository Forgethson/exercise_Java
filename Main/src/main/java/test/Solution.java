package test;


import java.util.HashMap;

class Solution {


    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
    }
}

class Student implements Comparable<Student> {
    int id;
    String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(Student s) {
        // 先按照name升序
        int i = name.compareTo(s.name);
        if (i == 0) {
            // 再按照id降序
            return Integer.compare(s.id, id);
        }
        return i;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
