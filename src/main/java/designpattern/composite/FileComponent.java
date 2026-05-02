package designpattern.composite;

/**
 * 抽象组件：文件和目录都实现同一个接口，调用方可以用统一方式处理。
 */
public interface FileComponent {
    void show(int depth);
}
