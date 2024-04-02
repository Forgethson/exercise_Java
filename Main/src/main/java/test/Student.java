package test;

public class Student {
    public int ID;
    public String name;
    private int score;

    public Student() {
    }

    public Student(int ID, String name, int score) {
        this.ID = ID;
        this.name = name;
        this.score = score;
    }

    public void show() {
        System.out.println("Student " + name + " get " + score + " scores.");
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
