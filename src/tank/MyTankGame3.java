/**
 * 功能:坦克游戏的5.0[]
 * 1.画出坦克.
 * 2.我的坦克可以上下左右移动
 * 3.可以发射子弹,子弹连发(最多5)
 * 4.当我的坦克击中敌人坦克时，敌人就消失(爆炸的效果)
 * 5.我被击中后，显示爆炸效果
 * 6.防止敌人坦克重叠运动(*)
 *    6.1决定把判断是否碰撞的函数写到EnemyTank类
 * 7.可以分关(*)
 * 	  7.1做一个开始的Panle,它是一个空的
 *    7.2闪烁效果
 * 8.可以在玩游戏的时候暂停和继续（*）
 * 	  8.1当用户点击暂停时，子弹的速度和坦克速度设为0,并让坦克的方向不要变化
 * 9.可以记录玩家的成绩（*）
 *    9.1用文件流.
 *    9.2单写一个记录类，完成对玩家记录
 *    9.3先完成保存共击毁了多少辆敌人坦克的功能.
 *    9.4存盘退出游戏,可以记录当时的敌人坦克坐标，并可以恢复
 * 10.java如何操作声音文件（*）
 * 		10.1对声音文件的操作
 */
package tank;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.lang.reflect.Method;
import java.text.MessageFormat;

public class MyTankGame3 extends JFrame implements ActionListener {
	MyPanel mp = null;
	// 定义一个开始面板
	MyStartPanel msp = null;

	// 作出我需要的菜单
	JMenuBar jmb = null;
	// 开始游戏
	JMenu jm1 = null;
	JMenu jm2 = null;
	JMenu jm3 = null;
	JMenu jm4 = null;
	JMenuItem jmil = null;
	JMenuItem jmi2 = null;// 退出系统
	JMenuItem jmi3 = null;// 存盘退出
	JMenuItem jmi4 = null;
	JMenuItem jmi5 = null;
	JMenuItem jmi6 = null;
	JMenuItem jmi7 = null;
	JMenuItem jmi8 = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyTankGame3 mtg = new MyTankGame3();
	//	Swing_JTextField aa=new Swing_JTextField();
		while(true) {
			//int i=1;
			System.out.print("");
			if(scord.i==2) {
				Swing_JTextField a=new Swing_JTextField();
				break;
			}
			//System.out.println(i--);
		}
		//Swing_JTextField.main();
	}

	// 构造函数
	public MyTankGame3() {// 面板
		// 创建菜单及菜单选项
		jmb = new JMenuBar();
		jm1 = new JMenu("游戏(G)");
		jm2 = new JMenu("下一关(M)");
		jm3 = new JMenu("得分面板(P)");
		jm4 = new JMenu("游戏帮助(A)");

		jmil = new JMenuItem("开始新游戏(N)");
		jmi2 = new JMenuItem("退出游戏(E)");
		jmi3 = new JMenuItem("存盘退出游戏(C)");
		jmi4 = new JMenuItem("继续上局游戏(S)");
		jmi5 = new JMenuItem("下一关(L)");
		jmi6 = new JMenuItem("当前得分(Q)");
		jmi7 = new JMenuItem("历史积分版(W)");
		jmi8 = new JMenuItem("游戏帮助(B)");

		// 注册监听
		jmi4.addActionListener(this);
		jmi4.setActionCommand("conGame");

		// 注册监听
		jmi3.addActionListener(this);
		jmi3.setActionCommand("saveExit");

		jmi2.addActionListener(this);
		jmi2.setActionCommand("exit");

		jmil.addActionListener(this);
		jmil.setActionCommand("newgame");

		jmi5.addActionListener(this);
		jmi5.setActionCommand("next");

		jmi6.addActionListener(this);
		jmi6.setActionCommand("cursd");// current score

		jmi7.addActionListener(this);
		jmi7.setActionCommand("hissd");// history score
		
		jmi8.addActionListener(this);
		jmi8.setActionCommand("help");
		// 设置快捷方式
		jm1.setMnemonic('G');
		jmil.setMnemonic('N');
		jmi2.setMnemonic('E');
		jmi3.setMnemonic('C');
		jmi4.setMnemonic('S');
		jm2.setMnemonic('M');
		jm3.setMnemonic('P');
		jm4.setMnemonic('A');
		jmi5.setMnemonic('L');
		jmi6.setMnemonic('Q');
		jmi7.setMnemonic('W');
		jmi8.setMnemonic('B');

		jm1.add(jmil);
		jm1.add(jmi2);
		//jm1.add(jmi3);
		//jm1.add(jmi4);
		jm2.add(jmi5);
		jm3.add(jmi6);
		jm3.add(jmi7);
		jm4.add(jmi8);

		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm3);
		jmb.add(jm4);

		msp = new MyStartPanel();
		Thread t = new Thread(msp);
		t.start();

		this.setJMenuBar(jmb);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(msp);
		this.setSize(600, 500);
		this.setLocation(200, 100);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {// 对用户不同的点击作出不同的处理
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals("next")) {
			if (Recorder.getstage() == 3) {// ////////////// 3
				if ((Recorder.getEnNummm(Recorder.getstage() - 1)) == 0) {// 结束
					// 结束游戏 询问玩家姓名			
					scord.i=2;
					return;
				}
			}
			// System.out.println("hhhhhj");
			// System.out.println(Recorder.getstage()+" gg");
			// System.out.println(Recorder.getEnNummm(Recorder.getstage()-1)+" gg");
			if ((Recorder.getEnNummm(Recorder.getstage() - 1)) == 0) {// 此关已经结束//Recorder.getExsitEny()==0
				// System.out.println("7777777");
				Recorder.addstage();
				if (mp != null)
					this.remove(mp);
				mp = new MyPanel("next", Recorder.getstage());// 启动mp线程
				// System.out.println("lzffff "+ Recorder.getstage());
				Thread t = new Thread(mp);
				t.start();
				this.add(mp);
				// 注册监听
				this.addKeyListener(mp);
				// 显示,刷新JFrame
				this.setVisible(true);
			}
		}
		if (arg0.getActionCommand().equals("newgame")) {
			// 创建战场面板
//			System.out.println("gg");
			Recorder.resetstage();
			if (mp != null)
				this.remove(mp);

			mp = new MyPanel("newGame", 1);// 启动mp线程
			Thread t = new Thread(mp);
			t.start();
			// 先删除旧的开始面板
			this.remove(msp); /// 不移除会出现闪屏
			this.add(mp);
			// 注册监听
			this.addKeyListener(mp);
			// 显示,刷新JFrame
			this.setVisible(true);
		} else if (arg0.getActionCommand().equals("exit")) {
			// 用户点击了退出系统的菜单
			// 保存击毁敌人数量.
			Recorder.keepRecording();
			System.exit(0);
		} // 对存盘退出
		else if (arg0.getActionCommand().equals("saveExit")) {
			// System.out.println("111");
			// System.out.println("mp.ets.size="+mp.ets.size());
			// 工作
			Recorder rd = new Recorder();
			rd.setEts(mp.ets);
			// 保存击毁敌人的数量和敌人的坐标
			rd.keepRecAndEnemyTank();
			// 退出
			System.exit(0);
		} else if (arg0.getActionCommand().equals("conGame")) {
			// 创建战场面板v //////未实现相应功能。。
			mp = new MyPanel("con", 1);
			// 启动mp线程
			Thread t = new Thread(mp);
			t.start();
			// 先删除旧的开始面板
			this.remove(msp);
			this.add(mp);
			// 注册监听
			this.addKeyListener(mp);
			// 显示,刷新JFrame
			this.setVisible(true);
		}
		if (arg0.getActionCommand().equals("cursd")) {
			windows.main(arg0);
		}
		if (arg0.getActionCommand().equals("hissd")) {
			TableDemo.main(arg0);
		}
		if (arg0.getActionCommand().equals("help")) {
			help a=new help();
		}
	}
}

//
class TableDemo extends JFrame {
	public TableDemo() {
		super("得分面板");
		DefaultTableModel model = null;
		JTable table = null;
		int stage = Recorder.getstage();
		int num = Recorder.getEnNummm(stage);
//      System.out.println(stage);
//      System.out.println(num);
		String[][] datas = {};
		String[] titles = { "玩家昵称", "得分" };
		model = new DefaultTableModel(datas, titles);
		table = new JTable(model);

		scord.readFromFile();
		int i = scord.num;
		// System.out.println("gggggggg"+i);
		if (i >= 1) {
			model.addRow(new String[] { scord.sc1.name, scord.sc1.sc });
		}
		if (i >= 2) {
			model.addRow(new String[] { scord.sc2.name, scord.sc2.sc });
		}
		if (i >= 3) {
			model.addRow(new String[] { scord.sc3.name, scord.sc3.sc });
		}
		if (i >= 4) {
			model.addRow(new String[] { scord.sc4.name, scord.sc4.sc });
		}
		if (i >= 5) {
			model.addRow(new String[] { scord.sc5.name, scord.sc5.sc });
		}
		scord.inni();

		add(new JScrollPane(table));

		setSize(400, 300);
		this.setLocation(240, 180);

		setVisible(true);
	}

	public static void main(ActionEvent arg0) {
		new TableDemo();
	}
}


class help extends JFrame {// 继承JFrame顶层容器类

	// 定义组件
	JPanel jp1, jp2, jp3;// 定义面板
	JLabel jlb1;
	JButton jb1;

	public help()//构造函数
	{
		//创建组件
		jp1=new JPanel();//创建三个面板
		jp2=new JPanel();
		jp3=new JPanel();
		
		jlb1=new JLabel("请查看 readme文档");
		
		jb1=new JButton("got it");	
		
		//设置布局管理器
		this.setLayout(new GridLayout(3,1));//网格布局，3行一列

		//添加组件
		this.add(jp1);//添加面板
		this.add(jp2);
		this.add(jp3);
		
		jp1.add(jlb1);//添加面板1的标签和文本框
		jp3.add(jb1);//添加面板3的按钮
		
		jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose(); 
			}
		});
		//设置窗口属性
		this.setTitle("帮助");//创建界面标题
		this.setSize(300, 150);//设置界面像素
		this.setLocation(400, 200);//设置界面初始位置
	//	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置界面和虚拟机一起关闭
		this.setVisible(true);//设置界面可显示
		this.setAlwaysOnTop(true);
	}
}

class Swing_JTextField extends JFrame {// 继承JFrame顶层容器类

	// 定义组件
	JPanel jp1, jp2, jp3;// 定义面板
	JTextField jtf1;// 定义文本框
	JLabel jlb1,jlb2;// 定义标签
	JButton jb1;//,jb2;// 定义按钮

	public static void main() {//主函数
		Swing_JTextField a=new Swing_JTextField();//显示界面
	}

	public Swing_JTextField()//构造函数
	{
		//创建组件
		jp1=new JPanel();//创建三个面板
		jp2=new JPanel();
		jp3=new JPanel();
		
		jlb1=new JLabel("用户名");
		jlb2=new JLabel("请正确输入");
		jb1=new JButton("计入得分");	
		jtf1=new JTextField(10);//创建文本框
		
		//设置布局管理器
		this.setLayout(new GridLayout(3,1));//网格布局，3行一列

		//添加组件
		this.add(jp1);//添加面板
		this.add(jp2);
		this.add(jp3);
		
		jp1.add(jlb1);//添加面板1的标签和文本框
		jp1.add(jtf1);
		jp3.add(jb1);//添加面板3的按钮
		
		jp1.add(jlb2);
		
		jlb2.setVisible(false);
		jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(jtf1.getText().length()==0)
					jlb2.setVisible(true);
				else {
					//System.out.println(jtf1.getText());
					int num = Recorder.getkill(1) * 100 + Recorder.getkill(2) * 200 + Recorder.getkill(3) * 300
							+ Recorder.getkill(4) * 400 + Recorder.getkill(5) * 1000;
					scord.nameString = jtf1.getText();
					String numString =null;
					numString = String.valueOf(num);
					scord.method1(scord.nameString+" "+numString);
					dispose();
				}
				
				//dispose();
				//scord.nameString = 
			}
		});
		//设置窗口属性
		this.setTitle("通关！");//创建界面标题
		this.setSize(300, 150);//设置界面像素
		this.setLocation(400, 200);//设置界面初始位置
	//	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置界面和虚拟机一起关闭
		this.setVisible(true);//设置界面可显示
		this.setAlwaysOnTop(true);
	}
}

//
	class windows extends JFrame {
		pan pa = null;

		public void innit() {
			this.setTitle("统计分数");
			this.setSize(500, 400);
			this.setLocation(230, 170);
			pa = new pan();
			this.add(pa);
			this.setVisible(true);
			// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		class pan extends JPanel implements Runnable {
			public void drawTank(int x, int y, Graphics g, int direct, int type) {
				// 判断是什么类型的坦克 /// 不仅是颜色，属性也要变，封装？？
				switch (type) {
				case 0:
					g.setColor(Color.cyan);
					break;
				case 1:
					g.setColor(Color.yellow); // hero
					break;
				case 2:
					g.setColor(Color.pink);
					break;
				case 3:
					g.setColor(Color.red);
					break;
				case 4:
					g.setColor(Color.green);
					break;
				case 5:
					g.setColor(Color.blue);// boss
					break;
				}
				// 判断方向
				switch (direct) {
				case 0:// 向上
					g.fill3DRect(x, y, 5, 30, false);
					g.fill3DRect(x + 15, y, 5, 30, false);
					g.fill3DRect(x + 5, y + 5, 10, 20, false);
					g.fillOval(x + 5, y + 10, 10, 10);
					g.drawLine(x + 10, y + 15, x + 10, y);
					break;
				case 1:// 炮筒向右
					g.fill3DRect(x, y, 30, 5, false);
					g.fill3DRect(x, y + 15, 30, 5, false);
					g.fill3DRect(x + 5, y + 5, 20, 10, false);
					g.fillOval(x + 10, y + 5, 10, 10);
					g.drawLine(x + 15, y + 10, x + 30, y + 10);
					break;
				case 2:// 向下
					g.fill3DRect(x, y, 5, 30, false);
					g.fill3DRect(x + 15, y, 5, 30, false);
					g.fill3DRect(x + 5, y + 5, 10, 20, false);
					g.fillOval(x + 5, y + 10, 10, 10);
					g.drawLine(x + 10, y + 15, x + 10, y + 30);
					break;
				case 3: // 向左
					g.fill3DRect(x, y, 30, 5, false);
					g.fill3DRect(x, y + 15, 30, 5, false);
					g.fill3DRect(x + 5, y + 5, 20, 10, false);
					g.fillOval(x + 10, y + 5, 10, 10);
					g.drawLine(x + 15, y + 10, x, y + 10);
					break;
				}
			}

			public void showInfo(Graphics g) {
				g.setColor(Color.black);
				g.drawString("stage " + Recorder.getstage() + "\n", 100, 30);

				this.drawTank(50, 50, g, 0, 0);
				g.setColor(Color.black);
				g.drawString(Recorder.getkill(1) + "    ×    100  =    " + Recorder.getkill(1) * 100 + "\n", 100, 75);

				this.drawTank(50, 90, g, 0, 2);
				g.setColor(Color.black);
				g.drawString(Recorder.getkill(2) + "    ×    200  =    " + Recorder.getkill(2) * 200 + "\n", 100, 115);

				this.drawTank(50, 130, g, 0, 3);
				g.setColor(Color.black);
				g.drawString(Recorder.getkill(3) + "    ×    300  =    " + Recorder.getkill(3) * 300 + "\n", 100, 155);

				this.drawTank(50, 170, g, 0, 4);
				g.setColor(Color.black);
				g.drawString(Recorder.getkill(4) + "    ×    400  =    " + Recorder.getkill(4) * 400 + "\n", 100, 195);

				this.drawTank(50, 210, g, 0, 5);
				g.setColor(Color.black);
				g.drawString(Recorder.getkill(5) + "    ×    1000  =    " + Recorder.getkill(5) * 1000 + "\n", 100,
						235);

				g.setColor(Color.black);
				int num = Recorder.getkill(1) * 100 + Recorder.getkill(2) * 200 + Recorder.getkill(3) * 300
						+ Recorder.getkill(4) * 400 + Recorder.getkill(5) * 1000;
				g.drawString("total:  " + num, 200, 275);

			}

			public void paint(Graphics g) {
				super.paint(g);
				showInfo(g);
			}

			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					// 休眠
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
					}
					this.repaint();
				}
			}
		}

		public static void main(ActionEvent arg0) {
			windows w = new windows();
			w.innit();
		}
	}

//就是一个提示作用
	class MyStartPanel extends JPanel implements Runnable {
		int times = 0;

		public void paint(Graphics g) {
			super.paint(g);
			g.fillRect(0, 0, 400, 300);
			// 提示信息
			if (times % 2 == 0) {
				g.setColor(Color.yellow);
				// 开关信息的字体
				Font myFont = new Font("华文新魏", Font.BOLD, 30);
				g.setFont(myFont);
				g.drawString("welcome to stage: 1", 30, 150);
			}
		}

		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				// 休眠
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
				times++;
				// 重画
				this.repaint();
			}
		}
	}

//我的面板
class MyPanel extends JPanel implements KeyListener,Runnable
{
	//定义一个我的坦克
	Hero hero=null;
	//定义敌人的坦克组
	Vector<EnemyTank> ets=new Vector<EnemyTank>();
	Vector<Node> nodes=new Vector<Node>();	
	//定义炸弹集合
	Vector<Bomb> bombs=new Vector<Bomb>();
	//定义墙块集合
	Vector<bug> bugs = new Vector<bug>();
	
	int enSize=Recorder.getEnNum();   //enamy size 敌人数目  每关不同
	int stage;
	//定义三张图片,三张图片才能组成一颗炸弹
	Image image1=null;
	Image image2=null;
	Image image3=null;
	AePlayWave apw=new AePlayWave(this.getClass().getResource("./111.wav").getPath());
	
	//构造函数
	public MyPanel(String flag,int stage)// int stage
	{		
		//恢复记录 
		
		Recorder.getRecoring();	
		hero=new Hero(50,200);	
		//Vector<bug> bbBugs = new Vector<bug>()
		if(flag.equals("newGame"))
		{
			apw.start();
			//初始化砖块
			Recorder.allrenew();
			enSize=Recorder.getEnNum();
			this.stage = 1;
			bug b11 =  null;
			bug b12 =  null;
			b11 = map.b11;b12=map.b12;
			//hero.setbugs(b11, b12);
			int xnum = (b12.x-b11.x)/20;
			int ynum = (b12.y-b11.y)/30;
			//System.out.println(xnum);System.out.println(ynum);
			
			for(int i=1;i<=ynum;i++) {
				for(int j=1;j<=xnum;j++) {
					bug zkBug = new bug((j-1)*20+b11.x, (i-1)*30+b11.y, b11.type);
					bugs.add(zkBug);
					Thread g1 = new Thread(zkBug);
					g1.start();
					hero.setbugs(zkBug);
				}
			}
//			for(int i=0;i<=5;i++) {
//				bug zkBug = new bug(50+30*i, 100, 1);
//				bugs.add(zkBug);
//				Thread g1 = new Thread(zkBug);
//				g1.start();
//			}
			//初始化敌人的坦克
			for(int i=0;i<enSize;i++)
			{
				//创建一辆敌人的坦克对象
				EnemyTank et=new EnemyTank((i+1)*50,0);
				for(int ii=1;ii<=ynum;ii++) {
					for(int j=1;j<=xnum;j++) {
						bug zkBug = new bug((j-1)*20+b11.x, (ii-1)*30+b11.y, b11.type);
						et.setbugs(zkBug);
					}
				}
				
				int ram = (int)(Math.random()*100)+1;
				if(ram<=70&&ram>=0) {// type 1 putong
					et.settank(1);///1
				}
				else if(ram <= 80) {
					et.settank(2);
				}
				else if(ram <= 90) {
					et.settank(3);
				}
				else {
					et.settank(4);
				}
				//et.setColor(0);
				et.setDirect(2);
				//将MyPanel的敌人坦克向量交给该敌人坦克
				et.setEts(ets);   //敌人坦克间的交互，互相知晓
				//启动敌人的坦克
				Thread t=new Thread(et);
				t.start();
				//给敌人坦克添加一颗子弹
				Shot s=new Shot(et.x+10,et.y+30,2);
				//加入给敌人坦克
				et.ss.add(s);
				Thread t2=new Thread(s);
				t2.start();
				//加入
				ets.add(et);
			}
		}
		else if(flag.equals("next")) {//之后的关卡
			this.stage = stage;
			enSize = Recorder.getEnNummm(stage-1);
			//初始化砖块
			bug b11 =  null;
			bug b12 =  null;
			if(stage==2) {//第二关
				b11 = map.b21;b12=map.b22;
			}
			else if(stage==3) {//第3关
				b11 = map.b31;b12=map.b32;
			}
//			System.out.println("!!!!!!!!!"+ b11.y);
//			System.out.println("!!!!!!!!!"+ b12.y);
			
			//b11 = map.b11;b12=map.b12;
			//hero.setbugs(b11, b12);
			int xnum = (b12.x-b11.x)/20;
			int ynum = (b12.y-b11.y)/30;
			//System.out.println(xnum);System.out.println(ynum);
			
			for(int i=1;i<=ynum;i++) {
				for(int j=1;j<=xnum;j++) {
					bug zkBug = new bug((j-1)*20+b11.x, (i-1)*30+b11.y, b11.type);
					bugs.add(zkBug);
					Thread g1 = new Thread(zkBug);
					g1.start();
					hero.setbugs(zkBug);
				}
			}

			//初始化敌人的坦克
			for(int i=0;i<enSize;i++)
			{
				//创建一辆敌人的坦克对象
				EnemyTank et=new EnemyTank((i+1)*50,0);
				for(int ii=1;ii<=ynum;ii++) {
					for(int j=1;j<=xnum;j++) {
						bug zkBug = new bug((j-1)*20+b11.x, (ii-1)*30+b11.y, b11.type);
						et.setbugs(zkBug);
					}
				}
				
				int ram = (int)(Math.random()*100)+1;
				if(i==3 && stage==3)
					et.settank(5);
				else if(ram<=70&&ram>=0) {// type 1 putong
					et.settank(1);
				}
				else if(ram <= 80) {
					et.settank(2);
				}
				else if(ram <= 90) {
					et.settank(3);
				}
				else {
					et.settank(4);
				}
				//et.setColor(0);
				et.setDirect(2);
				//将MyPanel的敌人坦克向量交给该敌人坦克
				et.setEts(ets);   //敌人坦克间的交互，互相知晓
				//启动敌人的坦克
				Thread t=new Thread(et);
				t.start();
				//给敌人坦克添加一颗子弹
				Shot s=new Shot(et.x+10,et.y+30,2);
				//加入给敌人坦克
				et.ss.add(s);
				Thread t2=new Thread(s);
				t2.start();
				//加入
				ets.add(et);
			}
		
		}
		else if(flag.equals("cons")){
			this.stage = stage;
			//System.out.println("接着玩");
			nodes=new Recorder().getNodesAndEnNums();  // 从文件中读出 x Y direct
			//初始化敌人的坦克
			for(int i=0;i<nodes.size();i++)
			{
				Node node=nodes.get(i);
				//创建一辆敌人的坦克对象
				EnemyTank et=new EnemyTank(node.x,node.y);
				et.setColor(0);
				et.setDirect(node.direct);
				//将MyPanel的敌人坦克向量交给该敌人坦克
				et.setEts(ets);
				
				//启动敌人的坦克
				Thread t=new Thread(et);
				t.start();
				//给敌人坦克添加一颗子弹
				Shot s=new Shot(et.x+10,et.y+30,2);
				//加入给敌人坦克
				et.ss.add(s);
				Thread t2=new Thread(s);
				t2.start();
				//加入
				ets.add(et);
			}
		}
		
		
		try {
			//new File("bomb_1.gif")
			
			image1=ImageIO.read(this.getClass().getResource("bomb_1.gif"));
			image2=ImageIO.read(this.getClass().getResource("bomb_2.gif"));
			image3=ImageIO.read(this.getClass().getResource("bomb_3.gif"));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	
	//画出提示信息
	public void showInfo(Graphics g)
	{
		//画出提示信息坦克(该坦克不参与战斗)
		this.drawTank(80,330, g, 0, 0);
		g.setColor(Color.black);
		g.drawString(Recorder.getEnNummm(stage-1)+"", 110, 350);
		this.drawTank(170, 330, g, 0, 1);
		g.setColor(Color.black);
		g.drawString(Recorder.currntlife+"", 210, 350);
		
		//画出玩家的总成绩
		g.setColor(Color.black);
		Font f=new Font("宋体",Font.BOLD,20);
		g.setFont(f);
		g.drawString("您的总成绩", 420, 30);
		
		this.drawTank(420, 60, g, 0, 0);
		
		g.setColor(Color.black);
		g.drawString(Recorder.getscore()+"", 460, 80);
		//System.out.println(Recorder.getscore()+"gg");
	}
	
	//重新paint
	public void paint(Graphics g)
	{

		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		//g.drawPolygon((20,100), 100, 2);
		//画出提示信息
		this.showInfo(g);

		//画出自己的坦克
		if(hero.isLive)
		{
			this.drawTank(hero.getX(), hero.getY(), g, this.hero.direct, 1);
		}
		//从ss,中取出每颗子弹，并画出
		for(int i=0;i<hero.ss.size();i++)
		{
			Shot myShot=hero.ss.get(i);
			
			//画出子弹,画出一颗子弹
			if(myShot!=null&&myShot.isLive==true)
			{
				g.draw3DRect(myShot.x, myShot.y, 1, 1,false);
			}
			if(myShot.isLive==false)
			{
				//从ss中删除掉该子弹
				hero.ss.remove(myShot);
			}
			
		}	
		//画出炸弹
		for(int i=0;i<bombs.size();i++)
		{
			//System.out.println("bombs.size()="+bombs.size());		
			//取出炸弹
			Bomb b=bombs.get(i);
			
			if(b.life>60)
			{
				g.drawImage(image1, b.x, b.y, 30, 30, this);
			}else if(b.life>30)
			{
				g.drawImage(image2, b.x, b.y, 30, 30, this);
			}else{
				g.drawImage(image3, b.x, b.y, 30, 30, this);
			}
			
			//让b的生命值减小
			b.lifeDown();
			//如果炸弹生命值为0,就把该炸弹重bombs向量去掉
			if(b.life==0)
			{
				bombs.remove(b);
			}	
		}
		
		//System.out.println("lzf666 " + bugs.size() + " lzy");
		//画出墙块
		for(int i=0;i<bugs.size();i++) {
			//v
			bug lzBug = bugs.get(i);
			if(lzBug.islive) {//没有被摧毁
				//System.out.println("lzf666 "+ lzBug.type );
				switch(lzBug.type) {
					case 1:
						g.setColor(Color.yellow); // 砖块
						break;
					case 2:
						g.setColor(Color.red);//铁块
						break;
					case 3:
						g.setColor(Color.blue);//河流
						break;
				}
				//g.setColor(Color.yellow);
				if(lzBug.type==3)
					g.fillRect(lzBug.x, lzBug.y, 30, 30); //无内边框，直接填充
				else
					g.fill3DRect(lzBug.x, lzBug.y, 30, 30, false); // 有内边框
			}

		}
		//画出敌人的坦克
		for(int i=0;i<ets.size();i++)
		{
			EnemyTank et=ets.get(i);		
			if(et.isLive)
			{		
				this.drawTank(et.getX(), et.getY(), g, et.getDirect(), et.color);
				//再画出敌人的子弹
				//System.out.println("坦克子弹有:"+et.ss.size());
				
				for(int j=0;j<et.ss.size();j++)
				{
					//取出子弹
					Shot enemyShot=et.ss.get(j);

					if(et.type == 2)
						enemyShot.speed = 2;
					if(et.type == 5)
						enemyShot.speed = 3;
					if(enemyShot.isLive)
					{
						//System.out.println("第 "+i+"坦克的 "+j+"颗子弹x="+enemyShot.x);
						g.draw3DRect(enemyShot.x, enemyShot.y, 1, 1,false);
					}else{
						//如果敌人的坦克死亡就从Vector去掉
						et.ss.remove(enemyShot);
					}
				}
			}
		}	
	}
	
	//敌人的坦克是否击中我
	public void hitMe()
	{
		//取出每一个敌人的坦克
		for(int i=0;i<this.ets.size();i++)
		{
			//取出坦克
			EnemyTank et=ets.get(i);
			
			//取出每一颗子弹
			for(int j=0;j<et.ss.size();j++)
			{
				//取出子弹
				Shot enemyShot=et.ss.get(j);
				if(enemyShot.isLive==false)
					continue;
				if(hero.isLive)
				{
					//碰到铁块 子弹消失
					bug n1 = null;
					for(int k=0;k<bugs.size();k++) {
						//判断碰撞
						n1 = bugs.get(k);
						if(n1.type==3)
							continue;
						if(enemyShot.x>=n1.x&&enemyShot.x<=n1.x+20&&enemyShot.y>=n1.y&&enemyShot.y<=n1.y+30) {
							if(n1.type==1) {//砖块
								n1.islive = false;
								bugs.remove(n1);
								enemyShot.isLive = false;
								hero.bugs = bugs;
							}
							else if(n1.type ==2) {
								enemyShot.isLive = false;
							}
						}
					}
					if(this.hitTank(enemyShot, hero)) // hero 被打中
					{
						
					}
				}
			}
		}
	}
	//判断我的子弹是否击中敌人的坦克
	public void hitEnemyTank(int stage)
	{
		//判断是否击中敌人的坦克
		for(int i=0;i<hero.ss.size();i++)
		{
			//取出子弹 
			Shot myShot=hero.ss.get(i);
			//判断子弹是否有效
			
			if(myShot.isLive)
			{
				//子弹 撞墙
				bug n1 = null;
				for(int k=0;k<bugs.size();k++) {
					//判断碰撞
					n1 = bugs.get(k);
					if(n1.type==3)
						continue;
					if(myShot.x>=n1.x&&myShot.x<=n1.x+20&&myShot.y>=n1.y&&myShot.y<=n1.y+30) {
						if(n1.type==1) {//砖块
							n1.islive = false;
							myShot.isLive = false;
							bugs.remove(n1);
						}
						else if(n1.type ==2) {
							myShot.isLive = false;
						}
					}
				}
				
				//取出每个坦克，与它判断
				for(int j=0;j<ets.size();j++)
				{
					//取出坦克
					EnemyTank et=ets.get(j);
					
					if(et.isLive)
					{
						if(this.hitTank(myShot, et))
						{
							Recorder.addkill(et.type);
							//减少敌人数量
							//Recorder.reduceEnNum();
							//增加我的记录
							//Recorder.addEnNumRec();
							//if(et.mylife)
							Recorder.setscore(et.type);
							Recorder.reduceenmy(stage);
							//Recorder.setExsitEny();
						}
					}
					
				}
			}
		}
	}
	
	//写一个函数专门判断子弹是否击中敌人坦克
	public boolean hitTank(Shot s,Tank et)
	{
		boolean b2=false;
		
		//判断该坦克的方向
		switch(et.direct)
		{
		//如果敌人坦克的方向是上或者是下
		case 0:
		case 2:
			if(s.x>et.x&&s.x<et.x+20&&s.y>et.y&&s.y<et.y+30)
			{
				//System.out.println(et.getmylife());
				//击中
				//子弹死亡
				s.isLive=false;
				//敌人坦克死亡
				b2=true;
				if(et.getmylife()==1) { // 死亡的为敌方坦克 
					
					
					//创建一颗炸弹,放入Vector
					et.isLive=false;
					
					//System.out.println("gg!");
					Bomb b=new Bomb(et.x,et.y);
					//放入Vector
					bombs.add(b);
				}
				else {// hero 被击中但未死亡
					et.ducelife();
					//et.setX(100);
					//et.setY(100);
					b2 = false;
					//((Hero) et).Hero(100,100);
					//System.out.println("hhhh"+ et.mylife);
				}
			}
			
			break;
		case 1:
		case 3:
			if(s.x>et.x&&s.x<et.x+30&&s.y>et.y&&s.y<et.y+20)
			{
				//System.out.println(et.getmylife());
				//击中
				//子弹死亡
				s.isLive=false;
				//敌人坦克死亡
				b2=true;
				if(et.getmylife()==1) { // 死亡的为敌方坦克 
					//创建一颗炸弹,放入Vector
					et.isLive=false;
					//System.out.println("gg!");
					Bomb b=new Bomb(et.x,et.y);
					//放入Vector
					bombs.add(b);
				}
				else {// hero 被击中但未死亡
					et.ducelife();
					//et.setX(100);
					//et.setY(100);
					b2 = false;
					//System.out.println("hhhh"+ et.mylife);
				}
			}
		}
		
		return b2;
		
	}
	
	//画出坦克的函数(扩展)
	public void drawTank(int x,int y,Graphics g,int direct,int type)
	{
		//判断是什么类型的坦克            ///       不仅是颜色，属性也要变，封装？？
		switch(type)
		{
		case 0:
			g.setColor(Color.cyan);
			break;
		case 1:
			g.setColor(Color.yellow); //hero
			break;
		case 2:
			g.setColor(Color.pink);
			break;
		case 3:
			g.setColor(Color.red);
			break;
		case 4:
			g.setColor(Color.green);
			break;
		case 5:
			g.setColor(Color.blue);// boss
			break;
		}	
		//判断方向
		switch(direct)
		{
		case 0://向上
			g.fill3DRect(x, y, 5, 30,false); 
			g.fill3DRect(x+15,y , 5, 30,false);
			g.fill3DRect(x+5,y+5 , 10, 20,false);
			g.fillOval(x+5, y+10, 10, 10);
			g.drawLine(x+10, y+15, x+10, y);
			break;
		case 1://炮筒向右					
			g.fill3DRect(x, y, 30, 5,false);
			g.fill3DRect(x, y+15, 30, 5, false);
			g.fill3DRect(x+5, y+5, 20, 10, false);
			g.fillOval(x+10, y+5, 10, 10);
			g.drawLine(x+15, y+10, x+30, y+10);
			break;
		case 2://向下
			g.fill3DRect(x, y, 5, 30,false);
			g.fill3DRect(x+15,y , 5, 30,false);
			g.fill3DRect(x+5,y+5 , 10, 20,false);
			g.fillOval(x+5, y+10, 10, 10);
			g.drawLine(x+10, y+15, x+10, y+30);
			break;
		case 3:	//向左
			g.fill3DRect(x, y, 30, 5,false);
			g.fill3DRect(x, y+15, 30, 5, false);
			g.fill3DRect(x+5, y+5, 20, 10, false);
			g.fillOval(x+10, y+5, 10, 10);
			g.drawLine(x+15, y+10, x, y+10);
			break;
		}				
	}

	//键按下处理 a 表示向左 s 表示 下 w 表示向上  d表示右
	
	public void keyPressed(KeyEvent arg0) {  // 双套键盘？？？
		// TODO Auto-generated method stub	
		if(arg0.getKeyCode()==KeyEvent.VK_W)
		{
			//设置我的坦克的方向
			if(this.hero.setDirect(0))
				this.hero.moveUp();
			
		}else if(arg0.getKeyCode()==KeyEvent.VK_D)
		{
			//向右
			if(this.hero.setDirect(1))
				this.hero.moveRight();
		}else if(arg0.getKeyCode()==KeyEvent.VK_S)
		{
			//向下
			if(this.hero.setDirect(2))
				this.hero.moveDown();
		}else if(arg0.getKeyCode()==KeyEvent.VK_A)
		{
			//向左
			if(this.hero.setDirect(3))
				this.hero.moveLeft();
		}
		
		if(arg0.getKeyCode()==KeyEvent.VK_J)
		{
			//判断玩家是否按下j			
			//开火
			//System.out.println("this.hero.ss.size()="+this.hero.ss.size());
			if(this.hero.ss.size()<=4)
			{
				this.hero.shotEnemy();
			}
			
		}
		//必须重新绘制Panel
		this.repaint(); ///？？？
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("gg");
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void run() {
		// TODO Auto-generated method stub
		//每隔100毫秒去重绘
		while(true)
		{
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			this.hitEnemyTank(stage);
			//函数，判断敌人的子弹是否击中我
			this.hitMe();		
			//重绘
			this.repaint();
		}
	}
}
