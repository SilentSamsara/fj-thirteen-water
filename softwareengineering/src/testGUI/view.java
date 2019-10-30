package testGUI;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI.NormalColor;

import secondpairing.SortCards;
import secondpairing.URLDemo;


public class view extends JFrame  {
    CardLayout cl;//卡片布局
    JPanel jpc;//主面板  其上将创建各个游戏界面面板 
	static URLDemo Demo=new URLDemo();
	SortCards Operation=new SortCards();
	public static JTextArea receive_textField = new JTextArea("等待中...");
	public static JTextArea post_textField = new JTextArea("等待中...");
	/*方法移植开始*/
	
	private ExecutorService service = Executors.newCachedThreadPool(new ThreadFactory() {
        
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "output");
        }
	});
	
	
	/*方法移植结束*/
	
	
	
	
    public view() {
    	
        jpc = new JPanel();//主面板
        cl = new CardLayout();//卡片管理器
        jpc.setLayout(cl);
        
        
        getContentPane().add(jpc);//主面板加到内容面板
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);//设置窗口为不可缩放
        setBounds(500, 150, 1223, 630);
        setTitle("十三水，大家一起水一水");//直接设置窗口标题
        ImageIcon ig = new ImageIcon("Image/图标.png");//这里放上你要设置图标图片
		Image im = ig.getImage();
		setIconImage(im);
////////////////////////////////////////////////////////////////////////////////////////第一面板开始////////////////////////////////////////////////////////////////////////////////////////
        JPanel firstpanel=new JPanel();//第一面板
        firstpanel.setLayout(null);
        //底部黑字
        JLabel tip = new JLabel("<html><body>抵制不良游戏，拒绝盗版游戏。注意自我保护，谨防受骗上当。<br>适度游戏益脑，沉迷游戏伤身。合理安排时间，享受健康生活。</body></html>");
		tip.setVisible(false);
		tip.setFont(new Font("黑体", Font.BOLD, 17));
		tip.setHorizontalAlignment(SwingConstants.CENTER);
		tip.setBounds(279, 488, 669, 94);
		firstpanel.add(tip);
		//广告图标
		ImageIcon adv=new ImageIcon("Image/图标.png");
		JLabel advlabel = new JLabel(adv);
		advlabel.setBounds(57, 25, 340, 131);
		advlabel.setVisible(false);
		firstpanel.add(advlabel);
		//登录面板
		JPanel lpanel = new JPanel();
		lpanel.setOpaque(false);
		lpanel.setVisible(false);
		lpanel.setBounds(64, 167, 316, 318);
		lpanel.setLayout(null);
		firstpanel.add(lpanel);
		//注册面板
		JPanel rpanel = new JPanel();
		rpanel.setOpaque(false);
		rpanel.setVisible(false);
		rpanel.setBounds(64, 167, 316, 336);
		getContentPane().add(rpanel);
		rpanel.setLayout(null);
		firstpanel.add(rpanel);
		//进度条
				JProgressBar progressBar = new JProgressBar();
				progressBar.setBounds(130, 449, 977, 13);
				progressBar.setMaximum(100);
				progressBar.setMinimum(0);
				progressBar.setValue(0);
				progressBar.setStringPainted(true);
				progressBar.setVisible(false);
				firstpanel.add(progressBar);
		//开始按钮
		JButton firstbutton = new JButton("开始游戏");
		firstbutton.setFont(new Font("SimSun", Font.PLAIN, 15));
		firstbutton.setBounds(521, 448, 205, 40);
		firstbutton.setUI(new BEButtonUI().setNormalColor(NormalColor.normal));
		firstbutton.setVisible(true);
		firstpanel.add(firstbutton);
		
		firstbutton.addMouseListener(new MouseAdapter() {			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				progressBar.setVisible(true);
				tip.setVisible(true);
				firstbutton.setVisible(false);
				new Thread(new Runnable() {
					@Override
					public void run() {
						try
						{
							for(int i=0;i<=100;i++) 
							{
									Thread.sleep(10);
									progressBar.setValue(i);
							}
							lpanel.setVisible(true);
							progressBar.setVisible(false);
							advlabel.setVisible(true);
						}
						catch (Exception e2){
							JOptionPane.showMessageDialog(view.this, "加载出错！");	
						}
					}
				}).start();
			}
		});	
		
        //登录面板上各组件

		
		JLabel label_1 = new JLabel("\u8D26\u53F7\uFF1A");
		label_1.setBounds(24, 36, 72, 18);
		lpanel.add(label_1);
		
		JLabel label_2 = new JLabel("\u5BC6\u7801\uFF1A");
		label_2.setBounds(24, 80, 72, 18);
		lpanel.add(label_2);
		
		ImageIcon img_forget=new ImageIcon("Image/忘记密码.png");
		ImageIcon img_regist=new ImageIcon("Image/注册.png");
		JLabel lblNewLabel_1 = new JLabel(img_forget);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(62, 204, 49, 44);
		lpanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(img_regist);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(200, 204, 49, 44);
		lpanel.add(lblNewLabel_2);
		
		JTextField textField = new JTextField();
		textField.setBounds(92, 33, 199, 24);
		lpanel.add(textField);
		
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(92, 77, 199, 24);
		lpanel.add(passwordField);
		
				
				JButton loginbutton = new JButton("登录");
				loginbutton.setBounds(24, 131, 267, 39);
				
				loginbutton.addActionListener(new ActionListener() {		
					@SuppressWarnings("deprecation")
					@Override
					public void actionPerformed(ActionEvent e) {
						User user1=new User();
						user1.setUsername(textField.getText());
						user1.setPassword(passwordField.getText());
						user1.set_json();
						Userdao ud =new Userdaoimpl();
						ud.login(user1);
						// TODO 自动生成的 catch 块
						if(URLDemo.login(Demo, user1.getUsername(), user1.getPassword()))
						{
							JOptionPane.showMessageDialog(null, "用户名或密码错误", "登录失败", JOptionPane.ERROR_MESSAGE);
							textField.setText("");
							passwordField.setText("");
						}
						else
						cl.next(jpc);
						
					}
				});
				lpanel.add(loginbutton);
				
				JButton to_regist = new JButton("教务账号注册");
				to_regist.setActionCommand("\u6559\u52A1\u8D26\u53F7\u6CE8\u518C");
				to_regist.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						lpanel.setVisible(false);
						rpanel.setVisible(true);
					}
				});
				to_regist.setBounds(162, 264, 129, 27);
				lpanel.add(to_regist);
					
				JButton forget = new JButton("忘记密码");
				forget.setBounds(24, 264, 124, 27);
				forget.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						JOptionPane.showMessageDialog(null, "我也没办法啊", "忘记密码？", JOptionPane.ERROR_MESSAGE);
					}
				});
				lpanel.add(forget);
				
				
				
				ImageIcon img_lralpha=new ImageIcon("Image/登录半透明.png");
				JLabel loginlabel = new JLabel(img_lralpha);
				loginlabel.setBounds(0, 0, 316, 318);
				lpanel.add(loginlabel);
                //教务处注册面板各组件
				JLabel rlabel_1 = new JLabel("用户名");
				rlabel_1.setHorizontalAlignment(SwingConstants.CENTER);
				rlabel_1.setBounds(28, 28, 97, 18);
				rpanel.add(rlabel_1);
				
				JLabel rlabel_2 = new JLabel("密码");
				rlabel_2.setHorizontalAlignment(SwingConstants.CENTER);
				rlabel_2.setBounds(28, 62, 97, 18);
				rpanel.add(rlabel_2);
				
				JLabel rlabel = new JLabel("确认密码");
				rlabel.setHorizontalAlignment(SwingConstants.CENTER);
				rlabel.setBounds(28, 99, 97, 18);
				rpanel.add(rlabel);
				
				JPasswordField rpasswordField = new JPasswordField();
				rpasswordField.setBounds(121, 59, 169, 24);
				rpanel.add(rpasswordField);
				
				JTextField rtextField = new JTextField();
				rtextField.setBounds(121, 25, 169, 24);
				rpanel.add(rtextField);
				
				
				
				JPasswordField rpasswordField_1 = new JPasswordField();
				rpasswordField_1.setBounds(121, 96, 169, 24);
				rpanel.add(rpasswordField_1);
				textField.setColumns(10);
				rpasswordField_1.addFocusListener(new FocusListener() {
					
					@SuppressWarnings("deprecation")
					@Override
					public void focusLost(FocusEvent e) {
						if(!rpasswordField.getText().equals(rpasswordField_1.getText())) {
							JOptionPane.showMessageDialog(null, "两次输入密码不一致", "注册失败", JOptionPane.ERROR_MESSAGE);
							rpasswordField_1.setText("");
						}
						
					}	
					@Override
					public void focusGained(FocusEvent e) {
						
					}
				});
				
				JLabel label_3 = new JLabel("\u6559\u52A1\u5904\u8D26\u53F7\uFF1A");
				label_3.setBounds(28, 133, 97, 18);
				rpanel.add(label_3);
				
				JLabel label_4 = new JLabel("\u6559\u52A1\u5904\u5BC6\u7801\uFF1A");
				label_4.setBounds(28, 167, 97, 18);
				rpanel.add(label_4);
				
				JTextField rtextField_1 = new JTextField();
				rtextField_1.setBounds(121, 130, 169, 24);
				rpanel.add(rtextField_1);
				
				
				
				JPasswordField rpasswordField_2 = new JPasswordField();
				rpasswordField_2.setBounds(121, 164, 169, 24);
				rpanel.add(rpasswordField_2);
				
				JButton registbutton = new JButton("确认");
				registbutton.setBounds(48, 208, 242, 39);
				registbutton.addActionListener(new ActionListener() {
					
					@SuppressWarnings("deprecation")
					@Override
					public void actionPerformed(ActionEvent e) {
						User user2=new User();
						user2.setStudent_number(rtextField_1.getText());
						user2.setStudent_password(rpasswordField_2.getText());
						user2.setUsername(rtextField.getText());
						user2.setPassword(rpasswordField.getText());
						user2.set_rjson();
						Userdao ud2 =new Userdaoimpl();
						
						
						
						ud2.regist(user2);
						if(URLDemo.register(Demo, user2.getUsername(), user2.getPassword(), user2.getStudent_number(), user2.getStudent_password()))
						{
							JOptionPane.showMessageDialog(new JFrame().getContentPane(),"注册成功，将使用此账号登录..."); 
							URLDemo.login(Demo, user2.getUsername(), user2.getPassword());						
							cl.next(jpc);
						}
						else
						JOptionPane.showMessageDialog(null, "注册失败", "", JOptionPane.ERROR_MESSAGE);
						
					}
					
				});
				
				rpanel.add(registbutton);
				
				JButton to_login = new JButton("取消");
				to_login.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {								
						rpanel.setVisible(false);
						lpanel.setVisible(true);
					}
				});
				to_login.setBounds(48, 260, 242, 39);
				rpanel.add(to_login);			
				
				
				JLabel registlabel = new JLabel(img_lralpha);
				registlabel.setBounds(0, 0, 316, 318);
				rpanel.add(registlabel);
		//第一面板上的背景，必须放在第一面板其他面板最后，才能显示出来		
        ImageIcon img = new ImageIcon("Image/登录背景.jpg");//要设置的背景图片
        JLabel background=new JLabel(img);
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
        firstpanel.add(background);
        jpc.add(firstpanel);
////////////////////////////////////////////////////////////////////////////////////////第一面板结束////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////第二面板开始////////////////////////////////////////////////////////////////////////////////////////
        JPanel secondpanel=new JPanel();
        secondpanel.setLayout(null);
        
        
        
        ImageIcon img_paihangbang=new ImageIcon("Image/排行榜.png");
        ImageIcon img_historyback=new ImageIcon("Image/历史战局.png");
        
        
        //详情面板
        JPanel xiangqing=new JPanel();
        xiangqing.setLayout(null);
        xiangqing.setVisible(false);
        xiangqing.setBounds(231, 3, 730, 581);
        secondpanel.add(xiangqing);
        xiangqing.setOpaque(false);
        //详情面板各组件
        
        JLabel lid=new JLabel("战局id:");
        lid.setBounds(50, 90, 120, 40);
        lid.setOpaque(false);
        lid.setFont(new Font("黑体", Font.BOLD, 29));
        lid.setForeground(Color.BLACK);
        xiangqing.add(lid);
        
        
        
        JTextField history_id=new JTextField();
        history_id.setForeground(Color.BLACK);
        history_id.setFont(new Font("黑体", Font.BOLD, 29));
        xiangqing.add(history_id);
        history_id.setBounds(170, 85, 150, 40);
        JTextArea textArea_xq = new JTextArea("等待后台发送数据过来等待后台发送数据过来等待后台发送数据过来等待后台发送数据过来等待后台发送数据过来等待后台发送数据过来等待后台发送数据过来等待后台发送数据过来等待后台发送数据过来等待后台发送数据过来等待后台发送数据过来等待后台发送数据过来等待后台发送数据过来等待后台发送数据过来等待后台发送数据过来等待后台发送数据过来等待后台发送数据过来等待后台发送数据过来等待后台发送数据过来等待后台发送数据过");   
        textArea_xq.setForeground(Color.white);
        textArea_xq.setLineWrap(true);//激活自动换行功能 
        textArea_xq.setEditable(false); 
        textArea_xq.setFont(new Font("黑体", Font.BOLD, 29));
        textArea_xq.setBackground(new Color(81, 34, 6));
        JScrollPane js3=new JScrollPane(textArea_xq);
        js3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        js3.setOpaque(false);
        js3.setBorder(null);
        js3.setBounds(148, 159, 545, 342);
        xiangqing.add(js3);
        		//背景标签
        JLabel lxiangqing=new JLabel(img_historyback);
        lxiangqing.setBounds(0, 0, 730, 581);
        xiangqing.add(lxiangqing);
        		//关闭按钮
        JButton xqguanbi=new JButton("123");
        xqguanbi.setBounds(660, 10, 60, 60);
        xqguanbi.setOpaque(false);   
        xiangqing.add(xqguanbi);
        
        
        
        
        //排行榜面板
        JPanel paihang=new JPanel();
        paihang.setLayout(null);
        paihang.setVisible(false);
        paihang.setBounds(231, 3, 730, 581);
        secondpanel.add(paihang);
        paihang.setOpaque(false);
        //排行榜面板各组件
              
        JTextArea textArea = new JTextArea();   
        textArea.setForeground(Color.WHITE);
        textArea.setLineWrap(true);//激活自动换行功能 
        textArea.setEditable(false); 
        textArea.setFont(new Font("黑体", Font.BOLD, 29));
        textArea.setBackground(new Color(81, 34, 6));
        JScrollPane js1=new JScrollPane(textArea);
        js1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        js1.setOpaque(false);
        js1.setBorder(null);
        js1.setBounds(148, 159, 545, 342);
        paihang.add(js1);
        		//背景标签
        JLabel lpaihang=new JLabel(img_paihangbang);
        lpaihang.setBounds(0, 0, 730, 581);
        paihang.add(lpaihang);
        		//关闭按钮
        JButton guanbi=new JButton("123");
        guanbi.setBounds(660, 10, 60, 60);
        guanbi.setOpaque(false);   
        paihang.add(guanbi);
        
         //历史战局面板  
        
        
        JPanel history=new JPanel();
        history.setLayout(null);
        history.setVisible(false);
        history.setBounds(231, 3, 730, 581);
        secondpanel.add(history);
        history.setBackground(new Color(81, 34, 6));
        //历史战局面板各组件
        
        JLabel lzhanji=new JLabel("我的战绩");
        lzhanji.setBounds(50, 90, 130, 40);
        lzhanji.setOpaque(false);
        lzhanji.setFont(new Font("黑体", Font.BOLD, 29));
        lzhanji.setForeground(Color.BLACK);
        history.add(lzhanji);
        
        JTextArea history_textArea = new JTextArea();
        history_textArea.setForeground(Color.WHITE);
        history_textArea.setLineWrap(true);
        history_textArea.setEditable(false);
        history_textArea.setBackground(new Color(81, 34, 6));
        history_textArea.setFont(new Font("黑体", Font.BOLD, 29));
        JScrollPane js2=new JScrollPane(history_textArea);
        js2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        js2.setOpaque(false);
        js2.setBorder(null);
        js2.setBounds(148, 159, 545, 342);      
        history.add(js2);
        		//背景标签
        JLabel lhistory=new JLabel(img_historyback);
        lhistory.setBounds(0, 0, 730, 581);
        history.add(lhistory);
        		//关闭按钮
        JButton historyguanbi=new JButton();
        historyguanbi.setBounds(660, 10, 60, 60);
        historyguanbi.setOpaque(false);
        history.add(historyguanbi);
        
        ImageIcon girl=new ImageIcon("Image/美女.png");
        JLabel lgirl = new JLabel(girl);
        lgirl.setBounds(270, 200, 151, 273);
        secondpanel.add(lgirl);
        
        
        
        ImageIcon girl2=new ImageIcon("Image/美女2.png");
        JLabel lgirl2 = new JLabel(girl2);
        lgirl2.setBounds(1010, 329, 180, 264);
        secondpanel.add(lgirl2);
        
        ImageIcon changci=new ImageIcon("Image/进入场次.png");
        JLabel lchangci = new JLabel(changci);
        lchangci.setBounds(483, 160, 470, 307);
        secondpanel.add(lchangci);

        
        //以下4个场次按钮入口
        JButton button1 = new JButton("");
        button1.setBounds(514, 160, 188, 133);
        secondpanel.add(button1);
        button1.setContentAreaFilled(false);
        button1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		cl.next(jpc);
        	}
		});
        
        
        JButton button2 = new JButton("");
        button2.setBounds(736, 160, 208, 133);
        secondpanel.add(button2);
        button2.setContentAreaFilled(false);
        button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cl.next(jpc);
				
			}
		});
        
        JButton button3 = new JButton("");
        button3.setBounds(500, 329, 202, 132);
        secondpanel.add(button3);
        button3.setContentAreaFilled(false);
        button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cl.next(jpc);
				
			}
		});
        
        JButton button4 = new JButton("");
        button4.setBounds(738, 324, 195, 137);
        secondpanel.add(button4);
        button4.setContentAreaFilled(false);
        button4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cl.next(jpc);
				
			}
		});
        
        
        ImageIcon img_head=new ImageIcon("Image/导航.png");
        JLabel sHead = new JLabel(img_head);
        sHead.setBounds(689, 13, 420, 63);
        secondpanel.add(sHead);
        
        ImageIcon img_set=new ImageIcon("Image/设置.png");
        JLabel sset = new JLabel(img_set);
        sset.setBounds(1104, 13,50 , 52);
        secondpanel.add(sset);
        
        JButton bto_set = new JButton("New button");
        bto_set.setBounds(1104, 23, 50, 36);
        bto_set.setContentAreaFilled(false);
        secondpanel.add(bto_set);
                
        ImageIcon img_friend=new ImageIcon("Image/好友图标改.png");
        JLabel Friend = new JLabel(img_friend);
        Friend.setBounds(105, 524, 50, 44);
        secondpanel.add(Friend);
                
        ImageIcon img_xiangqing=new ImageIcon("Image/背包.png");
        JLabel Xiangqing = new JLabel(img_xiangqing);
        Xiangqing.setBounds(294, 524, 50, 44);
        secondpanel.add(Xiangqing);
        
        ImageIcon img_sport=new ImageIcon("Image/活动.png");
        JLabel Sport = new JLabel(img_sport);
        Sport.setBounds(675, 524, 50, 44);
        secondpanel.add(Sport);       
        
        ImageIcon img_history=new ImageIcon("Image/历史战局改.png");
        JLabel History = new JLabel(img_history);
        History.setBounds(485, 524, 50, 44);
        secondpanel.add(History);
        
        ImageIcon img_paihang=new ImageIcon("Image/排行榜改.png");
        JLabel ph = new JLabel(img_paihang);
        ph.setBounds(870, 524, 50, 44);
        secondpanel.add(ph);
        		//底部各入口按钮
        JButton bfriend = new JButton("好友");
        bfriend.setBounds(163, 524, 113, 44);
        secondpanel.add(bfriend);
        bfriend.setContentAreaFilled(false);
        
        JButton bpackage = new JButton("背包");
        bpackage.setBounds(346, 524, 113, 44);
        secondpanel.add(bpackage);
        bpackage.setContentAreaFilled(false);
        
        JButton bxq = new JButton("战局详情");
        bxq.setBounds(730, 524, 113, 44);
        secondpanel.add(bxq);
        bxq.setContentAreaFilled(false);
        
        JButton bhistory = new JButton("历史战局");
        bhistory.setBounds(538, 524, 113, 44);
        secondpanel.add(bhistory);
        bhistory.setContentAreaFilled(false);
        
        JButton bph = new JButton("排行榜");
        bph.setBounds(927, 524, 113, 44);
        secondpanel.add(bph);
        bph.setContentAreaFilled(false);
        		//底部标签
        ImageIcon img_buttom=new ImageIcon("Image/底部.png");
        JLabel buttom = new JLabel(img_buttom);
        buttom.setBounds(88, 512, 980,70);
        secondpanel.add(buttom);
        		//各个按钮事件设置
        bxq.addActionListener(new ActionListener() {
        	//战局详情
			public void actionPerformed(ActionEvent e) {
				
				//history_textArea.setText(Demo.history(Demo));
				
				xiangqing.setVisible(true);
				history_id.requestFocus();
				history_id.setVisible(true);
				bhistory.setVisible(false);
				bph.setVisible(false);
				button1.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				button4.setVisible(false);
			}
		});
        history_id.addFocusListener(new FocusAdapter() {
        	public void focusLost(FocusEvent e) {
        		String zhanju_id=history_id.getText();
				//zhanju_id是接口的输入  
				textArea_xq.setText(URLDemo.historyid(Demo, zhanju_id));
        	}
		});
        
        xqguanbi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				history_id.setText("");
				textArea_xq.setText("");
				xiangqing.setVisible(false);
				bph.setVisible(true);
				bhistory.setVisible(true);
				button1.setVisible(true);
				button2.setVisible(true);
				button3.setVisible(true);
				button4.setVisible(true);
				
			}
		});
        
        
        bhistory.addActionListener(new ActionListener() {
        	
			public void actionPerformed(ActionEvent e) {
				//Demo.history(Demo);
				String player=""+URLDemo.id;
				history_textArea.setText(URLDemo.history(Demo,player,0));
				history.setVisible(true);
				button1.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				button4.setVisible(false);
			}
		});
        historyguanbi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				history.setVisible(false);
				button1.setVisible(true);
				button2.setVisible(true);
				button3.setVisible(true);
				button4.setVisible(true);
				
			}
		});
        
        bph.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
				textArea.setText(URLDemo.rank(Demo));
				paihang.setVisible(true);
				bhistory.setVisible(false);
				button1.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				button4.setVisible(false);
			}
		});
        guanbi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				paihang.setVisible(false);
				
				bhistory.setVisible(true);
				button1.setVisible(true);
				button2.setVisible(true);
				button3.setVisible(true);
				button4.setVisible(true);
				
			}
		});
        
        
      
        //第二面板背景标签
        ImageIcon img2 = new ImageIcon("Image/大厅.png");
        JLabel second_background=new JLabel(img2);
        second_background.setBounds(0, 0, img2.getIconWidth(), img2.getIconHeight());
        secondpanel.add(second_background);
        jpc.add(secondpanel,"secondpanel");
////////////////////////////////////////////////////////////////////////////////////////第二面板结束////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////第三面板开始////////////////////////////////////////////////////////////////////////////////////////
        JPanel thirdpanel=new JPanel();//第三面板//
        thirdpanel.setLayout(null);
        
        ImageIcon img_player=new ImageIcon("Image/头像.png");
        JLabel lplayer1 = new JLabel(img_player);
        lplayer1.setBounds(460, 466, 95, 90);
        thirdpanel.add(lplayer1);
        
        JLabel lplayer2 = new JLabel(img_player);
        lplayer2.setBounds(460, 122, 95, 90);
        thirdpanel.add(lplayer2);
        
        JLabel lplayer3 = new JLabel(img_player);
        lplayer3.setBounds(14, 238, 95, 90);
        thirdpanel.add(lplayer3);

        JLabel lplayer4 = new JLabel(img_player);
        lplayer4.setBounds(1108, 238, 95, 90);
        thirdpanel.add(lplayer4);   
        
        
       
        JLabel tHead = new JLabel(img_head);
        tHead.setText("");
        tHead.setBounds(689, 13, 420, 63);
        thirdpanel.add(tHead);
        
        
        JLabel tset = new JLabel(img_set);
        tset.setBounds(1104, 13,50 , 52);
        thirdpanel.add(tset);
        
        JButton tbto_set = new JButton("");
        tbto_set.setBounds(1104, 23, 50, 36);
        tbto_set.setContentAreaFilled(false);
        tbto_set.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cl.previous(jpc);
				
			}
		});
        thirdpanel.add(tbto_set);
        
      
        JLabel lready2 = new JLabel("已准备");
        lready2.setFont(new Font("黑体", Font.BOLD, 40));
        lready2.setForeground(new Color(9, 241, 32));
        lready2.setBounds(591, 143, 146, 46);
        thirdpanel.add(lready2);
        
        JLabel lready1 = new JLabel("已准备");
        lready1.setFont(new Font("黑体", Font.BOLD, 40));
        lready1.setForeground(new Color(9, 241, 32));
        lready1.setBounds(591, 484, 146, 46);
        thirdpanel.add(lready1);
        lready1.setVisible(false);
        
        JLabel lready3 = new JLabel("已准备");
        lready3.setFont(new Font("黑体", Font.BOLD, 40));
        lready3.setForeground(new Color(9, 241, 32));
        lready3.setBounds(123, 262, 146, 46);
        thirdpanel.add(lready3);
        
        JLabel lready4 = new JLabel("已准备");
        lready4.setFont(new Font("黑体", Font.BOLD, 40));
        lready4.setForeground(new Color(9, 241, 32));
        lready4.setBounds(976, 262, 146, 46);
        thirdpanel.add(lready4);
        
        JLabel lunready = new JLabel("未准备");
        lunready.setFont(new Font("黑体", Font.BOLD, 40));
        lunready.setForeground(new Color(130, 130, 130));
        lunready.setBounds(591, 484, 146, 46);
        thirdpanel.add(lunready);
        
        JButton btnNewButton = new JButton("准备");
        btnNewButton.setBounds(564, 406, 132, 46);
        thirdpanel.add(btnNewButton);
        btnNewButton.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		btnNewButton.setVisible(false);
				lunready.setVisible(false);
				lready1.setVisible(true);
				new Thread(new Runnable() {
					@Override
					public void run() {
						try
						{
							for(int i=0;i<=100;i++) 
							{
									Thread.sleep(10);
									
							}
							cl.next(jpc);
						}
						catch (Exception e2){
							JOptionPane.showMessageDialog(view.this, "加载出错！");	
						}
					}
				}).start();
			}
		});

        
      ImageIcon img3 = new ImageIcon("Image/游戏背景.png");
        JLabel third_background=new JLabel(img3);
        third_background.setBounds(0, 0, img3.getIconWidth(), img3.getIconHeight());
        thirdpanel.add(third_background);
        jpc.add(thirdpanel);       
////////////////////////////////////////////////////////////////////////////////////////第三面板结束////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////第四面板开始////////////////////////////////////////////////////////////////////////////////////////
        JPanel fourthpanel=new JPanel();
	    fourthpanel.setLayout(null);
      
	    ImageIcon img_alpha=new ImageIcon("Image/出牌半透明.png");
		   JPanel panel = new JPanel();
		    panel.setBounds(231, 69, 757, 442);
		    panel.setOpaque(false);
		    fourthpanel.add(panel);
		    panel.setLayout(null);
		    
		    //JTextField receive_textField = new JTextField("等待后台发送数据1");/*这里1*/
		    receive_textField.setBounds(245, 97, 474, 48);
		    panel.add(receive_textField);
		    
		    receive_textField.setEditable(false);
		    receive_textField.setFont(new Font("黑体", Font.BOLD, 20));
		    
		    //JTextField post_textField = new JTextField("等待后台发送数据2");/*这里2*/
		    post_textField.setBounds(245, 268, 474, 48);
		    panel.add(post_textField);
		    
		    post_textField.setEditable(false);
		    post_textField.setFont(new Font("黑体", Font.BOLD, 20));
		    /*
		     * 
		     * 战局界面
		     * 
		     * */
		    
		    
	        
		    JLabel receive_label = new JLabel("我收到的牌:");
		    receive_label.setFont(new Font("黑体", Font.BOLD, 40));
		    receive_label.setForeground(Color.WHITE);
		    receive_label.setBounds(14, 97, 232, 48);
		    panel.add(receive_label);
		    
		    JLabel post_label = new JLabel("我排好的牌:");
		    post_label.setFont(new Font("黑体", Font.BOLD, 40));
		    post_label.setForeground(Color.WHITE);
		    post_label.setBounds(14, 268, 232, 48);
		    panel.add(post_label);
		    
		    
	        
		    JLabel alphaLabel = new JLabel(img_alpha);
		    alphaLabel.setBounds(0, 0, 757, 439);
		    panel.add(alphaLabel);
		    
		    
		    
		    
		   
		    JLabel fourth_lplayer1 = new JLabel(img_player);
		    fourth_lplayer1.setBounds(460, 466, 95, 90);
		    fourthpanel.add(fourth_lplayer1);
		    
		    JLabel fourth_lplayer2 = new JLabel(img_player);
		    fourth_lplayer2.setBounds(460, 122, 95, 90);
		    fourthpanel.add(fourth_lplayer2);
		    
		    JLabel fourth_lplayer3 = new JLabel(img_player);
		    fourth_lplayer3.setBounds(14, 238, 95, 90);
		    fourthpanel.add(fourth_lplayer3);

		    JLabel fourth_lplayer4 = new JLabel(img_player);
		    fourth_lplayer4.setBounds(1108, 238, 95, 90);
		    fourthpanel.add(fourth_lplayer4);   
		    
		    
		   
		    JLabel fourth_Head = new JLabel(img_head);
		    fourth_Head.setBounds(689, 13, 420, 63);
		    fourthpanel.add(fourth_Head);
		    
		    
		    JLabel fourth_set = new JLabel(img_set);
		    fourth_set.setBounds(1104, 13,50 , 52);
		    fourthpanel.add(fourth_set);
		    
		    JButton fbto_set = new JButton("");
		    fbto_set.setBounds(1104, 23, 50, 36);
		    fbto_set.setContentAreaFilled(false);
		    fourthpanel.add(fbto_set);
		    
		    ImageIcon img_p=new ImageIcon("Image/牌.png");
		    
		    JLabel lp2 = new JLabel(img_p);
		    lp2.setBounds(583, 110, 124, 119);
		    fourthpanel.add(lp2);
		    
		    
		    JLabel lp1 = new JLabel(img_p);
		    lp1.setBounds(583, 451, 124, 119);
		    fourthpanel.add(lp1);
		    
		    
		    JLabel lp3 = new JLabel(img_p);
		    lp3.setBounds(123, 225, 124, 119);
		    fourthpanel.add(lp3);
		    
		    JLabel lp4 = new JLabel(img_p);
		    lp4.setBounds(976, 238, 124, 119);
		    fourthpanel.add(lp4);
		    
		    JButton bagain = new JButton("再来亿把");
		    JButton breturn = new JButton("返回大厅");
		    JButton bt_chupai = new JButton("确定出牌");
		    bagain.setBounds(1057, 465, 132, 46);
		    fourthpanel.add(bagain);
		    bagain.setVisible(false);
		    bagain.addActionListener(new ActionListener() {	    	
				@Override
				public void actionPerformed(ActionEvent e) {
					String inputValue = JOptionPane.showInputDialog("您说的亿是多少？");
					service.submit(new Runnable() {
						@Override
						public void run() {
							// TODO 自动生成的方法存根
							for(int i=0;i<Integer.valueOf(inputValue);i++)
								SortCards.work();
							JOptionPane.showMessageDialog(new JFrame().getContentPane(),"全部完成");
						}
					});
					
				}
			});
		    
		    
		    breturn.setBounds(1057, 524, 132, 46);
		    fourthpanel.add(breturn);
		    breturn.setVisible(false);
		    breturn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					btnNewButton.setVisible(true);
					lunready.setVisible(true);
					lready1.setVisible(false);
					breturn.setVisible(false);
					bagain.setVisible(false);
					bt_chupai.setVisible(true);
					receive_textField.setText("");
					post_textField.setText("");
					cl.show(jpc, "secondpanel");
					
				}
			});
		    
		    
		    	   
		    bt_chupai.setBounds(1057, 465, 132, 46);
		    fourthpanel.add(bt_chupai);

		    bt_chupai.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
//					String inputValue = JOptionPane.showInputDialog("Please input a value");
					// TODO 自动生成的 catch 块
					bt_chupai.setVisible(false);
					bagain.setVisible(true);
					breturn.setVisible(true);
					service.submit(new Runnable() {                
						@Override
	                    public void run() {
							SortCards.work();
							JOptionPane.showMessageDialog(new JFrame().getContentPane(),"出牌成功！");
	                    }
	                });
				}
			});
		  
		    
		    
		  //第四面板背景标签
		    ImageIcon img4 = new ImageIcon("Image/游戏背景.png");
		    JLabel fourth_background=new JLabel(img4);
		    fourth_background.setBounds(0, 0, img4.getIconWidth(), img4.getIconHeight());
		    fourthpanel.add(fourth_background);
		    setVisible(true);
		    jpc.add(fourthpanel);
////////////////////////////////////////////////////////////////////////////////////////第四面板结束////////////////////////////////////////////////////////////////////////////////////////
       
        
////////////////////////////////////////////////
        
    }

 
   
    public static void main(String[] args) {
    	try {
    		BeautyEyeLNFHelper.frameBorderStyle=BeautyEyeLNFHelper.frameBorderStyle.generalNoTranslucencyShadow;
    		org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
    		UIManager.put("ToolBar.isPaintPlainBackground", Boolean.TRUE);
   		UIManager.put("RootPane.setupButtonVisible", false);
    	}catch (Exception e) {
    		
    	}
        new view();
    }
}