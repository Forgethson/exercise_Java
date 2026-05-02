package designpattern.composite;

public class CompositePatternDemo {
    public static void main(String[] args) {
        DirectoryComposite root = new DirectoryComposite("root");
        root.add(new FileLeaf("README.md"));

        DirectoryComposite src = new DirectoryComposite("src");
        src.add(new FileLeaf("Main.java"));

        DirectoryComposite test = new DirectoryComposite("test");
        test.add(new FileLeaf("DemoTest.java"));

        src.add(test);
        root.add(src);

        // 调用方只面向统一的 FileComponent，不需要区分文件和目录。
        root.show(0);
    }
}
