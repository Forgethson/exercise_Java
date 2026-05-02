package designpattern.bridge;

/**
 * 实现部分：消息发送方式。
 * 这个维度可以独立扩展，例如短信、邮件、站内信。
 */
public interface MessageSender {
    void send(String message);
}
