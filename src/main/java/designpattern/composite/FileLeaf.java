package designpattern.composite;

/**
 * 叶子节点：表示文件，不能再包含子节点。
 */
public class FileLeaf implements FileComponent {
    private final String name;

    public FileLeaf(String name) {
        this.name = name;
    }

    @Override
    public void show(int depth) {
        System.out.println(indent(depth) + "- " + name);
    }

    private String indent(int depth) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            builder.append("  ");
        }
        return builder.toString();
    }
}
