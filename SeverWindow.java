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
		
	}
	/**
	 * 属性
	 */
	//
	JTabbedPane options = new JTabbedPane(JTabbedPane.TOP);
	//服务端信息属性
	Font font_char = new Font("宋体", Font.PLAIN, 15);
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
	
	//
	
	//构造方法
	public SeverWindow()
	{
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
		
		//add to top panel
		options.add("服务器管理", panel_server);
		options.setForeground(Color.BLACK);
		options.setBackground(new Color(255,239,219));
		options.setOpaque(true); //foreground设置透明
		this.getContentPane().add(options);
		setVisible(true);
	}
}
