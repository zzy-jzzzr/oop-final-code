import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.*;
public class LoginWindow extends JFrame implements ActionListener
{
	public static void main(String args[])
	{
		new LoginWindow();
	}
	
	/**
	 * 属性
	 */
	private static final long serialVersionUID = 1L;
	//登录面板
	private JPanel panel_login = new JPanel();
	//按钮：登录，注册，退出
	private JButton button_login = new JButton("登录");
	private JButton button_reg = new JButton("注册");
	private JButton button_exit = new JButton("退出");
	//标签：服务端，用户名，密码，logo
	private JLabel label_server = new JLabel("服务器:");
	private JLabel label_username = new JLabel("用户名:");
	private JLabel label_pwd = new JLabel("密码:");
	private JLabel label_logo;
	//文本框：服务端，用户名
	private JTextField text_server = new JTextField(20);
	private JTextField text_username = new JTextField(20);
	//密码框：密码
	private JPasswordField pwdfield_pwd = new JPasswordField(20);
	//服务器IP地址
	private String server_IP;
	
	// 用于将窗口定位
	private Dimension scrnsize;
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	public void login() throws IOException
	{
		//保存用户信息
		User temp_user = new User();
		temp_user.name = text_username.getText();
		temp_user.setPwd(String.valueOf(pwdfield_pwd.getPassword()));
		UserList.user_list.add(temp_user);
		//在客户端建立负责连接到服务器的套接字对象
		Socket user_socket = new Socket(server_IP, 1001);
		//在客户端建立向服务端的输出流
		ObjectOutputStream user_out_stream = new ObjectOutputStream(user_socket.getOutputStream());
		//向服务端写用户信息
		user_out_stream.writeObject(temp_user);
		//关闭流
		user_out_stream.close();
		user_socket.close();
		
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object source = e.getSource();
		if(source.equals(button_login))
		{
			if(text_username.getText().equals("") || pwdfield_pwd.getPassword().equals(""))
			{
				JOptionPane.showMessageDialog(null, "请输入用户名或密码", "错误提示",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				server_IP = text_server.getText();
				try 
				{
					login();
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
		}
		else if(source.equals(button_reg))
		{
			
		}
		else if(source.equals(button_exit))
		{
			System.exit(0);
		}
		
	}
	public LoginWindow()
	{
		//创建登录窗口
		new JFrame("登录");
		//创建登录面板
		//setPanel_login(new JPanel());
		//获取内容面板添加组件登录面板
		this.getContentPane().add(getPanel_login());
		//设置默认端口
		text_server.setText("127.0.0.2");
		//设置鼠标停留显示信息和快捷键（alt + xxx）
		button_login.setToolTipText("点击登录");
		button_login.setMnemonic('L');
		button_reg.setToolTipText("点击注册");
		button_reg.setMnemonic('R');
		button_exit.setToolTipText("点击退出");
		button_exit.setMnemonic('E');
		
		//手动设置页面布局
		panel_login.setLayout(null);
		panel_login.setBackground(new Color(176, 196, 222));//淡钢蓝的背景色给这个聊天室增添了一抹纯粹
		//设置组件位置
		label_server.setBounds(50, 80, 100, 30);
		label_username.setBounds(50, 120, 100, 30);
		label_pwd.setBounds(50, 160, 100, 30);
		text_server.setBounds(120, 80, 100, 30);
		text_username.setBounds(120, 120, 100, 30);
		pwdfield_pwd.setBounds(120, 160, 100, 30);
		button_login.setBounds(50, 200, 80, 25);
		button_reg.setBounds(130, 200, 80, 25);
		button_exit.setBounds(210, 200, 80, 25);
		//设置字体
		Font font_char = new Font("宋体", Font.PLAIN, 17);
		label_server.setFont(font_char);
		label_username.setFont(font_char);
		label_pwd.setFont(font_char);
		text_server.setFont(font_char);
		text_username.setFont(font_char);
		pwdfield_pwd.setFont(font_char);
		button_login.setFont(font_char);
		button_reg.setFont(font_char);
		button_exit.setFont(font_char);
		
		//
		label_server.setForeground(Color.BLACK);
		label_username.setForeground(Color.BLACK);
		label_pwd.setForeground(Color.BLACK);
		
		//设置logo
		ImageIcon logopic = new ImageIcon("images/talklogo.png");
		logopic.setImage(logopic.getImage().getScaledInstance(66, 66, Image.SCALE_DEFAULT));
		label_logo = new JLabel(logopic);
		label_logo.setBounds(0, 0, 66, 66);
		
		//添加组件
		panel_login.add(label_server);
		panel_login.add(label_username);
		panel_login.add(label_pwd);
		panel_login.add(label_logo);
		
		panel_login.add(text_server);
		panel_login.add(text_username);
		panel_login.add(pwdfield_pwd);
		
		panel_login.add(button_login);
		panel_login.add(button_reg);
		panel_login.add(button_exit);
		
		//设定界面初始长宽
		setResizable(false);
		setSize(800, 800);
		setVisible(true);
		scrnsize = toolkit.getScreenSize();
		setLocation(scrnsize.width / 2 - this.getWidth() / 2, scrnsize.height / 2 - this.getHeight() / 2);
		
		//监听三个按钮
		button_login.addActionListener(this);
		button_reg.addActionListener(this);
		button_exit.addActionListener(this);
	}
	/**
	 * 属性的getter和setter
	 */
	public JPanel getPanel_login() 
	{
		return panel_login;
	}
	public JButton getButton_login() 
	{
		return button_login;
	}
	public JButton getButton_reg()
	{
		return button_reg;
	}
	public JButton getButton_exit() 
	{
		return button_exit;
	}
	public JLabel getLabel_server() 
	{
		return label_server;
	}
	public JLabel getJLabel_username() 
	{
		return label_username;
	}
	public JLabel getJLabel_pwd()
	{
		return label_pwd;
	}
	public JLabel getJLabel_logo()
	{
		return label_logo;
	}
	public JTextField getTextfield_server()
	{
		return text_server;
	}
	public JTextField getText_username() 
	{
		return text_username;
	}
	public JPasswordField getPwdfield_pwd()
	{
		return pwdfield_pwd;
	}
	public void setPanel_login(JPanel panel_login) 
	{
		this.panel_login = panel_login;
	}
	public void setButton_login(JButton button_login)
	{
		this.button_login = button_login;
	}
	public void setButton_reg(JButton button_reg) 
	{
		this.button_reg = button_reg;
	}
	public void setButton_exit(JButton button_exit) 
	{
		this.button_exit = button_exit;
	}
	public void setLabel_server(JLabel label_server)
	{
		this.label_server = label_server;
	}
	public void setJLabel_username(JLabel jLabel_username) 
	{
		label_username = jLabel_username;
	}
	public void setJLabel_pwd(JLabel jLabel_pwd) 
	{
		label_pwd = jLabel_pwd;
	}
	public void setJLabel_logo(JLabel jLabel_logo) 
	{
		label_logo = jLabel_logo;
	}
	public void setTextfield_server(JTextField textfield_server) 
	{
		this.text_server = textfield_server;
	}
	public void setText_username(JTextField text_username) 
	{
		this.text_username = text_username;
	}
	public void setPwdfield_pwd(JPasswordField pwdfield_pwd)
	{
		this.pwdfield_pwd = pwdfield_pwd;
	}
	
}

