import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeverWindow extends JFrame implements ActionListener
{
	public static void main(String args[]) 
	{
		new SeverWindow();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if(source.equals(quit_serve))
		{
			this.dispose();
		}
		else if(source.equals(send))
		{
			this.systemMessage = text_notice.getText();
			text_notice.setText("");
		}
		
	}
	/**
	 * 属性
	 */
	//
	JTabbedPane options = new JTabbedPane(JTabbedPane.TOP);
	//服务端信息属性
	Font font_char = new Font("宋体", Font.PLAIN, 15);
	Font font_uspage = new Font("宋体", Font.PLAIN, 15);
	//
	JPanel panel_server = new JPanel();
	JPanel panel_sInfo = new JPanel(new GridLayout(2, 6));
	//
	public JLabel label_sStatus = new JLabel("服务器状态:");
	public JLabel label_onlineUse = new JLabel("在线人数:");
	public JLabel label_sName = new JLabel("服务器名称:");
	public JLabel label_sProtocol = new JLabel("访问协议:");
	public JLabel label_sIP = new JLabel("IP地址:");
	public JLabel label_sPort = new JLabel("端口号:");
	//
	public JTextField text_sStatus = new JTextField(10);
	public JTextField text_onlineUse = new JTextField(10);
	public JTextField text_sName = new JTextField(10);
	public JTextField text_sProtocol = new JTextField(10);
	public JTextField text_sIP = new JTextField(10);
	public JTextField text_sPort = new JTextField(10);
	//
	JButton quit_serve = new JButton("退出服务器");
	
	//用户信息管理
	JPanel panel_user = new JPanel();
	
	public JLabel label_usList = new JLabel("在线用户列表:");
	public JLabel label_message = new JLabel("消息记录:");
	public JLabel label_notice = new JLabel("通知:");
	
	public JList userList = new JList();
	public JTextArea text_message = new JTextArea(20, 20);
	public JTextArea text_notice = new JTextArea(20, 16);
	
	JButton send = new JButton("发 送");
	JButton dele;
	
	JScrollPane ul = new JScrollPane();
	//record notice
	public String systemMessage ="";
	//构造方法
	public SeverWindow()
	{
		super("国服服务器");
		JFrame window = new JFrame("服务器管理");
		this.setSize(750, 400);
		Dimension d = window.getSize();
		//main page style
		options.setBackground(Color.PINK);
		options.setFont(font_char);
		//居中显示
		Dimension sd = Toolkit.getDefaultToolkit().getScreenSize();
		sd.width = d.width > sd.width ? sd.width : d.width;
		sd.height = d.height > sd.height ? sd.height : d.height;
		this.setLocation((sd.width - d.width) / 2,(sd.height - d.height) / 2);
		//
		
		//outter panel
		panel_server.setLayout(null);
		panel_server.setBackground(new Color(176, 196, 222));
		//info-panel
		panel_sInfo.setBackground(new Color(176, 196, 222));
		panel_sInfo.setFont(font_char);
		panel_sInfo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(""), BorderFactory.createEmptyBorder(1, 1, 1, 1)));
		//set info-panel components
		label_sStatus.setForeground(Color.BLACK);
		label_sStatus.setFont(font_char);
		
		text_sStatus.setBackground(new Color(255,239,219));
		text_sStatus.setFont(font_char);
		text_sStatus.setEditable(false);
		
		label_onlineUse.setForeground(Color.BLACK);
		label_onlineUse.setFont(font_char);
		
		text_onlineUse.setText(" 0 ");
		text_onlineUse.setBackground(new Color(255,239,219));
		text_onlineUse.setFont(font_char);
		text_onlineUse.setEditable(false);
		
		label_sName.setForeground(Color.BLACK);
		label_sName.setFont(font_char);
		
		text_sName.setBackground(new Color(255,239,219));
		text_sName.setFont(font_char);
		text_sName.setEditable(false);
		
		label_sProtocol.setForeground(Color.BLACK);
		label_sProtocol.setFont(font_char);
		
		text_sProtocol.setBackground(new Color(255,239,219));
		text_sProtocol.setFont(font_char);
		text_sProtocol.setEditable(false);
		
		label_sIP.setForeground(Color.BLACK);
		label_sIP.setFont(font_char);
		
		text_sIP.setBackground(new Color(255,239,219));
		text_sIP.setFont(font_char);
		text_sIP.setEditable(false);
		
		label_sPort.setForeground(Color.BLACK);
		label_sPort.setFont(font_char);
		
		text_sPort.setText(" 8000 ");
		text_sPort.setBackground(new Color(255,239,219));
		text_sPort.setFont(font_char);
		text_sPort.setEditable(false);
		
		quit_serve.setFont(new Font("宋体", Font.PLAIN, 13));
		quit_serve.setBounds(320, 225, 110, 60);
		quit_serve.setContentAreaFilled(false);
		quit_serve.setOpaque(true); //foreground设置透明
		quit_serve.setBorderPainted(false); 
		quit_serve.setBackground(Color.PINK);
		quit_serve.setToolTipText("点击退出服务器");
		quit_serve.setMnemonic('Q');
		quit_serve.addActionListener(this);
		
		//add components
		panel_sInfo.add(label_sStatus);
		panel_sInfo.add(text_sStatus);
		panel_sInfo.add(label_onlineUse);
		panel_sInfo.add(text_onlineUse);
		panel_sInfo.add(label_sName);
		panel_sInfo.add(text_sName);
		panel_sInfo.add(label_sProtocol);
		panel_sInfo.add(text_sProtocol);
		panel_sInfo.add(label_sIP);
		panel_sInfo.add(text_sIP);
		panel_sInfo.add(label_sPort);
		panel_sInfo.add(text_sPort);
		
		//set info panel size
		panel_sInfo.setBounds(35, 50, 660, 120);
		panel_server.add(panel_sInfo);
		panel_server.add(quit_serve);
		
		//set user-panel
		panel_user.setLayout(null);
		panel_user.setBackground(new Color(176, 196, 222));
		panel_user.setFont(font_uspage);
		
		//set user-panel components
		label_usList.setForeground(Color.BLACK);
		label_usList.setFont(font_uspage);
		
		userList.setFont(font_uspage);
		userList.setVisibleRowCount(20);
		userList.setFixedCellWidth(180);
		userList.setFixedCellHeight(20);//每个元素的高度
		userList.setBackground(new Color(255,239,219));
		userList.setListData(new String[] { "" });
		
		    //add ScrollBar
		ul.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//automatic display
		ul.setBackground(new Color(176, 196, 222));
		ul.setFont(font_uspage);
		ul.getViewport().setView(userList);//设置视图
		ul.setViewportView(userList);
		
		label_message.setForeground(Color.BLACK);
		label_message.setFont(font_uspage);
		
		text_message.setFont(font_uspage);
		text_message.setLineWrap(true); 
		text_message.setBackground(new Color(255,239,219));
		
		label_notice.setForeground(Color.BLACK);
		label_notice.setFont(font_uspage);
		
		text_notice.setFont(font_uspage);
		text_notice.setLineWrap(true); 
		text_notice.setBackground(new Color(255,239,219));
		
		send.setFont(new Font("宋体", Font.PLAIN, 14));
		//send.setBounds(320, 225, 110, 60);
		send.setEnabled(true);
		send.setContentAreaFilled(false);
		send.setOpaque(true); //foreground设置透明
		send.setBorderPainted(false); 
		send.setBackground(Color.PINK);
		send.setToolTipText("点击发送通知");
		send.setMnemonic('S');
		send.addActionListener(this);
		
		label_usList.setBounds(20, 10, 200, 50);
		userList.setBounds(20, 60, 200, 250);
		label_message.setBounds(260, 10, 200, 50);
		text_message.setBounds(260, 60, 200, 250);
		label_notice.setBounds(500, 10, 200, 50);
		text_notice.setBounds(500, 60, 200, 200);
		send.setBounds(560, 270, 80, 40);
		
		panel_user.add(label_usList);
		panel_user.add(userList);
		panel_user.add(ul);
		panel_user.add(label_message);
		panel_user.add(text_message);
		panel_user.add(label_notice);
		panel_user.add(text_notice);
		panel_user.add(send);
		
		//add to top panel
		options.add("服务器管理", panel_server);
		options.add("资源管理", panel_user);
		options.setFont(font_uspage);
		options.setForeground(Color.BLACK);
		options.setBackground(new Color(255,239,219));
		options.setOpaque(true); //foreground设置透明
		this.getContentPane().add(options);
		setVisible(true);
	}
}
