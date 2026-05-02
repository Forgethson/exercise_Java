package designpattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合节点：表示目录，可以包含文件，也可以包含其他目录。
 */
public class DirectoryComposite implements FileComponent {
    private final String name;
    private final List<FileComponent> children = new ArrayList<>();

    public DirectoryComposite(String name) {
        this.name = name;
    }

    public void add(FileComponent component) {
        children.add(component);
    }

    @Override
    public void show(int depth) {
        System.out.println(indent(depth) + "+ " + name);
        for (FileComponent child : children) {
            child.show(depth + 1);
        }
    }

    private String indent(int depth) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            builder.append("  ");
        }
        return builder.toString();
    }
}
