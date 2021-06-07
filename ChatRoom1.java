import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class ChatRoom1 extends Thread implements ActionListener{
    static JFrame java_Chat;       // 创建一个新的java窗口
    JPanel Chat_pnl;     // 创建一个面板组件
    JButton testBtn;    // 创建页面按钮
    JButton delChat;    // 创建“清除聊天记录”按钮
    JButton exiChat;    // 创建“退出”按钮
    JButton sendChat;   // 创建“发送”按钮
    JButton saveChat;   // 创建“保存聊天记录”按钮

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public ChatRoom1(String name, String ip) {
        java_Chat = new JFrame("Java_test");
        java_Chat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // 设置窗口关闭选项
        java_Chat.setSize(600, 420);        // 设置整个窗口的相关大小
        java_Chat.setVisible(true);     // 设置窗口是可见的
        java_Chat.setResizable(false); // 设置窗口不可以调整大小

        Chat_pnl = new JPanel();        // 面板组件初始化
        java_Chat.getContentPane().add(Chat_pnl);       // 在窗口中加入面板组件
        Chat_pnl.setLayout(null);       // 设置面板布局为null
        Chat_pnl.setBackground(new Color(176, 196, 222));    // 设置面板背景颜色

        // 实例化各按钮
        testBtn = new JButton("Test-button");
        delChat = new JButton("清除聊天记录");
        exiChat = new JButton("退出");
        sendChat = new JButton("发送");
        saveChat = new JButton("保存聊天记录");
        // 将按钮加入面板
        Chat_pnl.add(testBtn);
        Chat_pnl.add(delChat);
        Chat_pnl.add(exiChat);
        Chat_pnl.add(sendChat);
        Chat_pnl.add(saveChat);
        // 设置按钮的格式
        //testBtn.setBounds(40, 40, 110, 25);    // 设置按钮的页面布局
        delChat.setBounds(40,40,110,25);
        exiChat.setBounds(40,70,110,25);
        sendChat.setBounds(40,100,110,25);
        saveChat.setBounds(40,130,110,25);
        testBtn.setFont(new Font("楷体", Font.PLAIN, 14));    // 设置按钮字体
        delChat.setFont(new Font("楷体", Font.PLAIN, 14));
        exiChat.setFont(new Font("楷体", Font.PLAIN, 14));
        sendChat.setFont(new Font("楷体", Font.PLAIN, 14));
        saveChat.setFont(new Font("楷体", Font.PLAIN, 14));
        testBtn.setBackground(Color.pink);      // 设置按钮背景
        delChat.setBackground(Color.ORANGE);
        exiChat.setBackground(Color.LIGHT_GRAY);
        sendChat.setBackground(Color.white);
        saveChat.setBackground(Color.green);
        testBtn.addActionListener(this);       // 添加点击事件
        delChat.addActionListener(this);
        exiChat.addActionListener(this);
        sendChat.addActionListener(this);
        saveChat.addActionListener(this);
    }

    public static void main(String args[]) {
        new ChatRoom1("测试用户", "127.0.0.1");
        System.out.println("乱码");
    }
}
