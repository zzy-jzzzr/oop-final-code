import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class ChatRoom extends Thread implements ActionListener 
{
	// 创建一个新的聊天窗口
    static JFrame frame_chat;
    // 创建一个面板
    JPanel panel_chat;
    //面板标签
    final JLabel label_head = new JLabel();
    JLabel label_OnlineUserList;
    JLabel label_ChatBox;
    JLabel label_ChatMessage;
    JLabel label_UserNum;
    JLabel label_Count;
    // 创建按钮
    JButton button_delRecord;
    JButton button_exit;
    JButton button_send;
    JButton button_save;
    //创建输入框
    TextArea inputBox;
    TextArea ChatRecord;
    java.awt.List UserList;
    JCheckBox isPriChat;
    JComboBox selChatObj;
    
    //窗口位置定位工具
    Dimension windowPos;
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    
    Notice noti = null;
    String noti_comp = "";
    String server_IP;
    String user_name;
    
    Thread thread;
    
    static String selected = "";

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Object source = e.getSource();
        // 清除聊天记录按钮相应
        if (source.equals(button_delRecord)) 
        {
            clearMessage();
        }
        // 退出按钮响应
        else if (source.equals(button_exit)) 
        {
            exit();
        }
        // 发送按钮响应
        else if (source.equals(button_send)) 
        {
            sendMessage();
        }
        // 保存聊天记录按钮响应
        else if (source.equals(button_save)) 
        {
            saveMessage();
        }
        // 切换用户操作响应
        else if (source.equals(UserList)) // 双击列表框
        {
        	
            changeUser();
        }
    }

    // 监听窗口关闭响应
    class Windowclose extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            exit();
        }
    }

    // 聊天界面构造函数
    public ChatRoom(String name, String ip)
    {
    	server_IP = ip;
    	user_name = name;
        frame_chat = new JFrame("国服JAVA聊天室 " + "[当前英雄：" + name + "](｡ì _ í｡)");
        // 设置窗口关闭选项
        frame_chat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_chat.setSize(800, 650);        // 设置整个窗口的相关大小
        frame_chat.setVisible(true);     // 设置窗口是可见的
        frame_chat.setResizable(false); // 设置窗口不可以调整大小

        panel_chat = new JPanel();        // 面板组件初始化
        frame_chat.getContentPane().add(panel_chat);       // 在窗口中加入面板组件
        panel_chat.setLayout(null);       // 设置面板布局为null
        panel_chat.setBackground(new Color(176, 196, 222));    // 设置面板背景颜色

        String[] List = {"所有人"};
        // 实例化各按钮
        button_delRecord = new JButton("清除聊天记录");
        button_exit = new JButton("退出");
        button_send = new JButton("发送");
        button_save = new JButton("保存聊天记录");
        // 实例化信息输入框
        inputBox = new TextArea("",300,170,TextArea.SCROLLBARS_VERTICAL_ONLY);
        ChatRecord = new TextArea("", 300, 200, TextArea.SCROLLBARS_VERTICAL_ONLY);
        UserList = new java.awt.List();
        isPriChat = new JCheckBox("私密消息");
        selChatObj = new JComboBox(List);
        // 实例化各标签
        label_OnlineUserList = new JLabel("在线用户列表");
        label_ChatBox = new JLabel("聊天框");
        label_UserNum = new JLabel("/在线人数：");
        label_ChatMessage = new JLabel("聊天内容输入:");
        label_Count = new JLabel("0");
        // 将组件加入面板
        panel_chat.add(button_delRecord);
        panel_chat.add(button_exit);
        panel_chat.add(button_send);
        panel_chat.add(button_save);
        panel_chat.add(inputBox);
        panel_chat.add(ChatRecord);
        panel_chat.add(UserList);
        panel_chat.add(isPriChat);
        panel_chat.add(selChatObj);
        panel_chat.add(label_head);
        panel_chat.add(label_OnlineUserList);
        panel_chat.add(label_ChatBox);
        panel_chat.add(label_ChatMessage);
        panel_chat.add(label_UserNum);
        panel_chat.add(label_Count);
        // 设置按钮的格式
        // 设置按钮的页面布局
        button_delRecord.setBounds(235, 505, 120, 25);
        button_exit.setBounds(235, 540, 120, 25);
        button_send.setBounds(650, 560, 120, 25);
        button_save.setBounds(235, 470, 120, 25);
        inputBox.setBounds(380, 430, 395, 125);
        ChatRecord.setBounds(225, 60, 550, 330);
        UserList.setBounds(5, 90, 210, 500);
        isPriChat.setBounds(235, 400, 120, 25);     
        selChatObj.setBounds(235, 435, 120, 25);  
        label_head.setBounds(30, 5, 55, 55);
        label_OnlineUserList.setBounds(5, 63, 120, 25);
        label_ChatBox.setBounds(227, 30, 180, 30);
        label_ChatMessage.setBounds(380, 400, 120, 25);
        label_UserNum.setBounds(90, 63, 90, 25);
        label_Count.setBounds(165, 63, 60, 25);
        // 设置按钮字体
        button_delRecord.setFont(new Font("黑体", Font.PLAIN, 14));
        button_exit.setFont(new Font("黑体", Font.PLAIN, 14));
        button_send.setFont(new Font("黑体", Font.PLAIN, 14));
        button_save.setFont(new Font("黑体", Font.PLAIN, 14));
        inputBox.setFont(new Font("黑体", Font.PLAIN, 14));
        inputBox.setForeground(Color.black);
        inputBox.setBackground(new Color(255, 239, 219));
        ChatRecord.setBackground(new Color(255, 239, 219));
        ChatRecord.setEditable(false);
        UserList.setFont(new Font("黑体", Font.PLAIN, 17));
        UserList.setBackground(new Color(255, 239, 219));
        isPriChat.setFont(new Font("黑体", Font.PLAIN, 14));
        selChatObj.setFont(new Font("黑体", Font.PLAIN, 14));
        isPriChat.setForeground(Color.black);
        selChatObj.setForeground(Color.black);
        label_OnlineUserList.setFont(new Font("黑体", Font.BOLD, 14));
        label_OnlineUserList.setForeground(Color.black);
        label_ChatBox.setFont(new Font("黑体", Font.PLAIN, 16));
        label_ChatBox.setForeground(Color.BLACK);
        label_ChatMessage.setFont(new Font("黑体", Font.PLAIN, 14));
        label_ChatMessage.setForeground(Color.black);
        label_UserNum.setFont(new Font("黑体", Font.BOLD, 14));
        label_UserNum.setForeground(Color.black);
        label_Count.setFont(new Font("黑体", Font.BOLD, 14));
        label_Count.setForeground(Color.black);
        // 设置按钮背景
        button_delRecord.setBackground(Color.pink);
        button_exit.setBackground(Color.pink);
        button_send.setBackground(Color.pink);
        button_save.setBackground(Color.pink);
        // 添加点击事件
        frame_chat.addWindowListener(new Windowclose());
        button_delRecord.addActionListener(this);
        button_exit.addActionListener(this);
        button_send.addActionListener(this);
        button_save.addActionListener(this);
        UserList.addActionListener(this);
        UserList.addMouseListener(new MouseAdapter() 
        {
            public void mousePressed (MouseEvent e) 
            {
                if(e.getClickCount() == 3)
                {
                	selected = UserList.getSelectedItem();
                	ChatRecord.append(name + "拍了拍" + "[" + selected + "]" + "\n");
                }
            }
        });
        // 设置TextArea的格式
        ChatRecord.setForeground(new Color(0, 0, 0));
        ChatRecord.setEditable(false); // 不可写入
        selChatObj.addItemListener(new ItemListener()
        {     // 添加选项选中状态改变的监听器
            @Override
            public void itemStateChanged(ItemEvent mei)
            {
                freshHead();
            }
        });
        // 设置头标签的格式
        label_head.setHorizontalAlignment(SwingConstants.CENTER);
        label_head.setIcon(new ImageIcon(
                new ImageIcon("headpics/1.JPG").getImage().getScaledInstance(label_head.getWidth(),label_head.getHeight(),Image.SCALE_DEFAULT)
        ));
        // 固定窗口打开时在屏幕中间
        windowPos = toolkit.getScreenSize();
        frame_chat.setLocation(windowPos.width / 2 - frame_chat.getWidth() / 2,
                (int) (windowPos.height / 2 - windowPos.getHeight() / 2));
        // 实例化线程、启动线程
        thread = new Thread(this);
        thread.start();

        Image img = toolkit.getImage("images\\appico.jpg");
        frame_chat.setIconImage(img);
    }

    // 清除聊天记录方法
    public void clearMessage()
    {
        ChatRecord.setText("");
    }

    // 退出聊天框方法
    public void exit() 
    {
        User exit = new User();
        exit.name = user_name;
        exit.isOnQuit = true;
        exit.isOnReg = false;
        exit.isOnLogin = false;
        // 发送退出信息
        try {
            Socket toServer = new Socket(server_IP, 1921);
            // 向服务器发送信息
            ObjectOutputStream outObj = new ObjectOutputStream(toServer.getOutputStream());
            outObj.writeObject(exit);
            outObj.close();
            toServer.close();

            frame_chat.dispose();
        } 
        catch (Exception e)
        {
        	e.printStackTrace();
        }
    }

    // 发送聊天记录方法
    public void sendMessage() 
    {
        Record chatobj = new Record();
        chatobj.sender = user_name;
        chatobj.chatComp = inputBox.getText();
        chatobj.receiver = String.valueOf(selChatObj.getSelectedItem());
        chatobj.isPrivate = isPriChat.isSelected();
        try {
            Socket toServer = new Socket(server_IP, 1921);
            ObjectOutputStream outObj = new ObjectOutputStream(toServer.getOutputStream());
            outObj.writeObject(chatobj);
            inputBox.setText("");
            outObj.close();
            toServer.close();
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
    }

    // 保存聊天记录方法
    public void saveMessage() 
    {
        try
        {
            FileOutputStream fileoutput = new FileOutputStream(this.user_name + "_message.txt", true);
            String temp = ChatRecord.getText();
            fileoutput.write(temp.getBytes());
            fileoutput.close();
            JOptionPane.showMessageDialog(null, "聊天记录保存在" + this.user_name + "_message.txt");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

    // ¸更换当前用户
    public void changeUser()
    {
        boolean key = true;
        String selected = UserList.getSelectedItem();
        for (int i = 0; i < selChatObj.getItemCount(); i++) 
        {
            if (selected.equals(selChatObj.getItemAt(i)))
            {
                key = false;
                break;
            }
        }
        if (key)
        {
            selChatObj.insertItemAt(selected, 0);
        }
        String head = getUserHead(UserList.getSelectedItem());
        selChatObj.setSelectedItem(selected);

        Image user_head = new ImageIcon("headpics/" + head + ".JPG").getImage().getScaledInstance(
                label_head.getWidth(),label_head.getHeight(),Image.SCALE_DEFAULT);
        label_head.setIcon(new ImageIcon(user_head));
    }

    // 获取某用户的头像图片名
    private String getUserHead(String selectedItem)
    {
        String head = null;
        for (Object a : noti.userOnline)
        {
            String User = ((User) a).name;
            head = ((User) a).headpic;
            if (User.equals(selectedItem))
            {
                break;
            }
        }
        return head;
    }

    protected void freshHead()
    {
        String head = getUserHead(selChatObj.getSelectedItem().toString());
        Image user_head = new ImageIcon("headpics/" + head + ".JPG").getImage().getScaledInstance(
                label_head.getWidth(),label_head.getHeight(),Image.SCALE_DEFAULT);
        label_head.setIcon(new ImageIcon(user_head));
    }

    // 线程的run()函数
    @SuppressWarnings("deprecation")
    public void run()
    {
        int intMessageCounter = 0;        // 消息总数
        int intUserTotal = 0;
        boolean isFirstLogin = true; // 判断是否刚登陆
        boolean isFound; // 判断是否找到用户
        ArrayList<User> user_exit = new ArrayList<User>();
        String bePatted = "";
        try 
        {
            for(;;) 
            {
                // IP地址和端口，端口为自定，1921 非常有意义
                Socket toServer = new Socket(server_IP, 1921);
                // 将信息发往服务器
                noti = new Notice();
                ObjectOutputStream streamToServer = new ObjectOutputStream(toServer.getOutputStream());
                streamToServer.writeObject(noti);
                // 收来自服务器的信息
                ObjectInputStream streamFromServer = new ObjectInputStream(toServer.getInputStream());
                noti = (Notice) streamFromServer.readObject();

                if(!bePatted.equals(selected))
                {
                	bePatted = selected;
                	ChatRecord.append(this.user_name + "拍了拍" + "[" + bePatted + "]" + "\n");
                	//ispat = false;
                	selected = "";
                }
                if (isFirstLogin) // 如果刚登陆
                {
                    intMessageCounter = noti.records.size(); // 屏蔽该用户登陆前的聊天内容
                    isFirstLogin = false;
                }
                if (!noti_comp.equals(noti.notice))
                {
                    // 将聊天记录同步到文本框里面
                	noti_comp = noti.notice;
                    ChatRecord.append("[系统消息]：" + noti_comp + "\n");
                }
                for (int i = intMessageCounter; i < noti.records.size(); i++)
                {
                    Record temp = (Record) noti.records.get(i);
                    String temp_message;
                    if (temp.sender.equals(user_name))
                    {
                        if (temp.receiver.equals(user_name))
                        {
                            temp_message = "[系统提示：发送消息失败！请不要自言自语！]" + "\n";
                        }
                        else 
                        {
                            if (!temp.isPrivate) // 不是私聊
                            {
                                temp_message = "[你] 对 [" + temp.receiver + "] "
                                        + "说" + temp.chatComp
                                        + "\n";
                            }
                            else
                            {
                                temp_message = "[你] 悄悄对 [" + temp.receiver
                                        + "] " + "说" + temp.chatComp
                                        + "\n";
                            }
                        }
                    } 
                    else 
                    {
                        if (temp.receiver.equals(user_name)) 
                        {
                            if (!temp.isPrivate) // 不是私聊
                            {
                                temp_message = "[" + temp.sender + "] 对 [你] "
                                        + "说" + temp.chatComp
                                        + "\n";
                            }
                            else 
                            {
                                temp_message = "[" + temp.sender + "] 悄悄对 [你] "
                                        + "说" + temp.chatComp
                                        + "\n";
                            }
                        } 
                        else
                        {
                            if (!temp.sender.equals(temp.receiver)) // 对方没有自言自语
                            {
                                if (!temp.isPrivate) // 不是私聊
                                {
                                    temp_message = "[" + temp.sender + "] 对 [" + temp.receiver + "] " + "说" + temp.chatComp + "\n";
                                } 
                                else
                                {
                                    temp_message = "";
                                }
                            } 
                            else
                            {
                                temp_message = "";
                            }
                        }
                    }
                    ChatRecord.append(temp_message);
                    intMessageCounter++;
                }

                // 刷新在线用户列表
                UserList.removeAll();
                for (int i = 0; i < noti.userOnline.size(); i++) 
                {
                    String User = ((User) noti.userOnline.get(i)).name;
                    UserList.add(User);
                }
                int a = noti.userOnline.size();
                label_Count.setText(Integer.toString(a));
                // 显示用户进入聊天室的信息
                if (noti.userOnline.size() > intUserTotal)
                {
                    String tempstr = ((User) noti.userOnline.get(noti.userOnline.size() - 1)).name;
                    if (!tempstr.equals(user_name))
                    {
                        ChatRecord.append("[" + tempstr + "] 上线了" + "\n");
                    }
                }
                if (noti.userOnline.size() < intUserTotal)
                {
                    for (int b = 0; b < user_exit.size(); b++) 
                    {
                        isFound = false;
                        for (int c = 0; c < noti.userOnline.size(); c++) 
                        {
                            String tempstr = ((User) user_exit.get(b)).name;

                            if (tempstr.equals(((User) noti.userOnline.get(c)).name))
                            {
                                isFound = true;
                                break;
                            }
                        }
                        if (!isFound) // 没有发现该用户
                        {
                            String tempStr = ((User) user_exit.get(b)).name;

                            if (!tempStr.equals(user_name)) 
                            {
                                ChatRecord.append("[" + tempStr + "]" + "下线了" + "\n");
                            }
                        }
                    }
                }
                user_exit = noti.userOnline;
                intUserTotal = noti.userOnline.size();
                streamToServer.close();
                streamFromServer.close();
                toServer.close();
                Thread.sleep(3000);
            }

        } 
        catch (Exception e)
        {
            @SuppressWarnings("unused")
            JOptionPane jop = new JOptionPane();
            JOptionPane.showMessageDialog(null, "不能连接服务器！");
            e.printStackTrace();
            frame_chat.dispose();
        }
    }

    public static void main(String args[]) 
    {
        new ChatRoom("测试用例", "127.0.0.1");
    }
}
