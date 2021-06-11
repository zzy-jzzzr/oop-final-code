import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class RegisterWindow extends JFrame  implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static void main(String args[])
	{
		new RegisterWindow("127.0.0.1");
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if(source.equals(button_reg))
		{
			try
			{
				reg();
			} 
			catch (IOException e1)
			{
				e1.printStackTrace();
			} 
			catch (ClassNotFoundException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(source.equals(button_back))
		{
			new LoginWindow();
			this.dispose();
		}
	}
	
	private JPanel panel_reg = new JPanel();
	
	private JLabel label_name = new JLabel("用户名:");
	private JLabel label_head = new JLabel("头像:");
	private final JLabel label_headpic;
	private JLabel label_pwd = new JLabel("密码:");
	private JLabel label_checkpwd = new JLabel("确认密码:");
	private JLabel label_gender = new JLabel("性别:");
	private JLabel label_age = new JLabel("年龄:");
	private JLabel label_email = new JLabel("电子邮箱:");
	
	private JTextField text_name = new JTextField(20);
	private JComboBox headBox = new JComboBox();
	private JPasswordField pwdfield_pwd = new JPasswordField(30);
	private JPasswordField pwdfield_checkpwd = new JPasswordField(30);
	private ButtonGroup gender = new ButtonGroup();
	private JRadioButton  male = new JRadioButton("男");
	private JRadioButton female = new JRadioButton("女",true);
	private JTextField text_age = new JTextField(20);
	private JTextField text_email = new JTextField(20);
	
	private JButton button_reg = new JButton("确认");
	private JButton button_back = new JButton("返回");
	
	//服务器IP地址
	private String server_IP;	
	// 用于将窗口定位
	private Dimension scrnsize;
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	Font font_char = new Font("宋体", Font.PLAIN, 17);
	@SuppressWarnings("unchecked")
	public RegisterWindow(String serverIP)
	{
		super("用户注册");
		
		server_IP = serverIP;
		//获取内容面板添加组件注册面板
		this.getContentPane().add(panel_reg);
		
		
		panel_reg.setLayout(null);
		panel_reg.setBackground(new Color(176, 196, 222));
		
		label_name.setFont(font_char);
		label_name.setForeground(Color.BLACK);
		
		text_name.setFont(font_char);
		text_name.setBackground(new Color(255,239,219));
		
		label_head.setFont(font_char);
		label_head.setForeground(Color.BLACK);
		
		ImageIcon head = new ImageIcon("headpics/1.JPG");
		head.setImage(head.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
		label_headpic = new JLabel(head);
		label_headpic.setHorizontalAlignment(SwingConstants.CENTER);
		
		label_pwd.setFont(font_char);
		label_pwd.setForeground(Color.BLACK);
		
		pwdfield_pwd.setFont(font_char);
		pwdfield_pwd.setBackground(new Color(255,239,219));
		
		label_checkpwd.setFont(font_char);
		label_checkpwd.setForeground(Color.BLACK);
		
		pwdfield_checkpwd.setFont(font_char);
		pwdfield_checkpwd.setBackground(new Color(255,239,219));
		
		label_gender.setFont(font_char);
		label_gender.setForeground(Color.BLACK);
		
		label_age.setFont(font_char);
		label_age.setForeground(Color.BLACK);
		
		text_age.setFont(font_char);
		text_age.setBackground(new Color(255,239,219));
		
		label_email.setFont(font_char);
		label_email.setForeground(Color.BLACK);
		
		text_email.setFont(font_char);
		text_email.setBackground(new Color(255,239,219));
		
		button_reg.setFont(font_char);
		button_reg.setBounds(320, 225, 110, 60);
		button_reg.setContentAreaFilled(false);
		button_reg.setOpaque(true); //foreground设置透明
		button_reg.setBorderPainted(false);
		button_reg.setToolTipText("点击确认注册，返回登录界面即可登录");
		button_reg.setMnemonic('R');
		button_reg.setBackground(Color.PINK);
		button_reg.addActionListener(this);
		
		button_back.setFont(font_char);
		button_back.setBounds(320, 225, 110, 60);
		button_back.setContentAreaFilled(false);
		button_back.setOpaque(true); //foreground设置透明
		button_back.setBorderPainted(false);
		button_back.setToolTipText("返回登录界面");
		button_back.setMnemonic('B');
		button_back.setBackground(Color.PINK);
		button_back.addActionListener(this);
		
		
		headBox.setAutoscrolls(true);
		headBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		headBox.addItemListener(new ItemListener() 
		{
			public void itemStateChanged(ItemEvent ie)
			{
				ImageIcon head = new ImageIcon("headpics/"+headBox.getSelectedItem().toString()+".jpg");
				head.setImage(head.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
				label_headpic.setIcon(head);
			}
		});
		
		//性别单选
		gender.add(male);
		gender.add(female);
		
		//
		label_name.setBounds(70, 10, 100, 50);
		label_head.setBounds(70, 70, 100, 50);
		label_pwd.setBounds(70, 130, 100, 50);
		label_checkpwd.setBounds(70, 190, 100, 50);
		label_gender.setBounds(70, 250, 100, 50);
		label_age.setBounds(70, 310, 100, 50);
		label_email.setBounds(70, 370, 100, 50);
		
		text_name.setBounds(180, 10, 150, 45);
		headBox.setBounds(180, 70, 150, 45);
		pwdfield_pwd.setBounds(180, 130, 150, 45);
		pwdfield_checkpwd.setBounds(180, 190, 150, 45);
		male.setBounds(180, 250, 70, 45);
		female.setBounds(235, 250, 70, 45);
		text_age.setBounds(180, 310, 150, 45);
		text_email.setBounds(180, 370, 150, 45);
		
		label_headpic.setBounds(390, 80, 150, 150);
		
		button_reg.setBounds(420, 280, 100, 40);
		button_back.setBounds(420, 350, 100, 40);
		
		//
		panel_reg.add(label_name);
		panel_reg.add(label_head);
		panel_reg.add(label_headpic);
		panel_reg.add(label_pwd);
		panel_reg.add(label_checkpwd);
		panel_reg.add(label_gender);
		panel_reg.add(label_age);
		panel_reg.add(label_email);
		
		panel_reg.add(text_name);
		panel_reg.add(headBox);
		panel_reg.add(pwdfield_pwd);
		panel_reg.add(pwdfield_checkpwd);
		panel_reg.add(male);
		panel_reg.add(female);
		panel_reg.add(text_age);
		panel_reg.add(text_email);
		
		panel_reg.add(button_reg);
		panel_reg.add(button_back);
		
		//
		this.setSize(600,450);
		this.setVisible(true);
		this.setResizable(false);
		//
		scrnsize=toolkit.getScreenSize();
    	this.setLocation(scrnsize.width/2-this.getWidth()/2, scrnsize.height/2-this.getHeight()/2);
		
	}
	
	public void reg() throws IOException, ClassNotFoundException
	{
		User user = new User();
		user.name = text_name.getText();
		user.setPwd(String.valueOf(pwdfield_pwd.getPassword()));
		user.age = text_age.getText();
		user.email = text_email.getText();
		user.gender = male.isSelected()?"男":"女";
		user.headpic = headBox.getSelectedItem().toString();
		
		//检查信息是否为空
		if(user.name.length() == 0 || user.getPwd().length() == 0 || user.age.length() == 0)
		{
			JOptionPane.showMessageDialog(null, "任何信息不能为空", "错误提示",JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if(!user.getPwd().equals(String.valueOf(pwdfield_checkpwd.getPassword())))
		{
			JOptionPane.showMessageDialog(null, "两次密码输入不一致", "错误提示",JOptionPane.ERROR_MESSAGE);
			return;
		}
		else
		{
			int age = Integer.parseInt(user.age);
			if(age <= 0 || age >= 150)
			{
				JOptionPane.showMessageDialog(null, "年龄夸张！", "错误提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			else
			{
				int flag = 0;
				for (int i = 0; i < user.email.length(); i++)
				{
				    if(user.email.charAt(i)=='@')
				    {
				        flag++;	
				    }	
				}
				if(flag != 1)
				{
					JOptionPane.showMessageDialog(null, "邮箱输入有误", "错误提示",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else
				{
					user.isOnReg = true;
					user.isOnLogin = false;
					user.isOnQuit = false;
					//在客户端建立负责连接到服务器的套接字对象
					@SuppressWarnings("resource")
					Socket user_socket = new Socket(server_IP, 1921);
					//在客户端建立向服务端的输出流
					ObjectOutputStream user_out_stream = new ObjectOutputStream(user_socket.getOutputStream());
					//向服务端写用户信息
					user_out_stream.writeObject((User) user);
					
					//
					BufferedReader read =  new BufferedReader(new InputStreamReader(user_socket.getInputStream()));
					String isSuccess = read.readLine();
					JOptionPane.showMessageDialog(null,isSuccess);
					if(isSuccess.equals("注册成功"))
					{
						text_age.setText("");
						text_email.setText("");
						text_name.setText("");
						pwdfield_pwd.setText("");
						pwdfield_checkpwd.setText("");
					}
					
					//关闭流
					user_out_stream.close();
					read.close();
				}
			}
		}
	}
}
