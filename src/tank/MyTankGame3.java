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

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
public class MyTankGame3 extends JFrame implements ActionListener {
	MyPanel mp=null;
	//定义一个开始面板
	MyStartPanel msp=null;
	
	//作出我需要的菜单
	JMenuBar jmb=null;
	//开始游戏
	JMenu jm1=null;
	JMenu jm2=null;
	JMenuItem jmil=null;
	JMenuItem jmi2=null;//退出系统
	JMenuItem jmi3=null;//存盘退出
	JMenuItem jmi4=null;
	JMenuItem jmi5=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyTankGame3 mtg=new MyTankGame3();
	}
	
	//构造函数
	public MyTankGame3(){//面板
		//创建菜单及菜单选项
		jmb=new JMenuBar();
		jm1 =new JMenu("游戏(G)");
		jm2 = new JMenu("下一关(M)");
		
		jmil =new JMenuItem("开始新游戏(N)");
		jmi2 =new JMenuItem("退出游戏(E)");
		jmi3 =new JMenuItem("存盘退出游戏(C)");
		jmi4 =new JMenuItem("继续上局游戏(S)");
		jmi5 =new JMenuItem("下一关(L)");
		//注册监听
		jmi4.addActionListener(this);
		jmi4.setActionCommand("conGame");
		
		//注册监听
		jmi3.addActionListener(this);
		jmi3.setActionCommand("saveExit");
		
		jmi2.addActionListener(this);
		jmi2.setActionCommand("exit");
		
		jmil.addActionListener(this);
		jmil.setActionCommand("newgame");
		
		jmi5.addActionListener(this);
		jmi5.setActionCommand("next");
		//设置快捷方式
		jm1.setMnemonic('G');
		jmil.setMnemonic('N');
		jmi2.setMnemonic('E');
		jmi3.setMnemonic('C');
		jmi4.setMnemonic('S');
		jm2.setMnemonic('M');
		jmi5.setMnemonic('L');
		
		jm1.add(jmil);
		jm1.add(jmi2);
		jm1.add(jmi3);
		jm1.add(jmi4);
		jm2.add(jmi5);
		jmb.add(jm1);
		jmb.add(jm2);
		
		msp=new MyStartPanel();
		Thread t=new Thread(msp);
		t.start();
		
		this.setJMenuBar(jmb);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(msp);
		this.setSize(600, 500);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {//对用户不同的点击作出不同的处理
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("next")) {
			System.out.println("hhhhhj");
			if(Recorder.getExsitEny()==0) {
				System.out.println("7777777");
				if(mp != null)
					this.remove(mp);
				
				mp=new MyPanel("newGame");//启动mp线程			
				Thread t=new Thread(mp);
				t.start();
				this.add(mp);
				//注册监听
				this.addKeyListener(mp);
				//显示,刷新JFrame
				this.setVisible(true);
			}
		}
		if(arg0.getActionCommand().equals("newgame"))
		{
			//创建战场面板
//			System.out.println("gg");
			if(mp != null)
				this.remove(mp);
			
			mp=new MyPanel("newGame");//启动mp线程			
			Thread t=new Thread(mp);
			t.start();
			//先删除旧的开始面板
			this.remove(msp);   ///不移除会出现闪屏
			this.add(mp);
			//注册监听
			this.addKeyListener(mp);
			//显示,刷新JFrame
			this.setVisible(true);	
		}else if(arg0.getActionCommand().equals("exit"))
		{
			//用户点击了退出系统的菜单
			//保存击毁敌人数量.
			Recorder.keepRecording();			
			System.exit(0);
		}//对存盘退出
		else if(arg0.getActionCommand().equals("saveExit"))
		{
			System.out.println("111");
			System.out.println("mp.ets.size="+mp.ets.size());
			//工作
			Recorder rd=new Recorder();
			rd.setEts(mp.ets);
			//保存击毁敌人的数量和敌人的坐标
			rd.keepRecAndEnemyTank();			
			//退出
			System.exit(0);
		}else if(arg0.getActionCommand().equals("conGame"))
		{
			//创建战场面板v     //////未实现相应功能。。
			mp=new MyPanel("con");		
			//启动mp线程
			Thread t=new Thread(mp);
			t.start();
			//先删除旧的开始面板
			this.remove(msp);
			this.add(mp);
			//注册监听
			this.addKeyListener(mp);
			//显示,刷新JFrame
			this.setVisible(true);
		}
	}
}
//就是一个提示作用
class MyStartPanel extends JPanel implements Runnable
{
	int times=0;
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		//提示信息		
		if(times%2==0)
		{
			g.setColor(Color.yellow);
			//开关信息的字体
			Font myFont=new Font("华文新魏",Font.BOLD,30);
			g.setFont(myFont);
			g.drawString("welcome to stage: 1", 30, 150);
		}		
	}

	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			//休眠
			try {				
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}			
			times++;			
			//重画
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
	
	int enSize=3;   //enamy size 敌人数目  每关不同
	
	//定义三张图片,三张图片才能组成一颗炸弹
	Image image1=null;
	Image image2=null;
	Image image3=null;
	
	//构造函数
	public MyPanel(String flag)
	{		
		//恢复记录 
		Recorder.getRecoring();	
		hero=new Hero(50,200);	
		if(flag.equals("newGame"))
		{
			//初始化敌人的坦克
			for(int i=0;i<enSize;i++)
			{
				//创建一辆敌人的坦克对象
				EnemyTank et=new EnemyTank((i+1)*50,0);
				et.setColor(0);
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
		else{
			System.out.println("接着玩");
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
			image1=ImageIO.read(new File("bomb_1.gif"));
			image2=ImageIO.read(new File("bomb_2.gif"));
			image3=ImageIO.read(new File("bomb_3.gif"));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		//播放开战声音
		AePlayWave apw=new AePlayWave("./111.wav");
		apw.start();
		
		//初始化图片d
//		image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
//		image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
//		image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
	}
	
	
	//画出提示信息
	public void showInfo(Graphics g)
	{
		//画出提示信息坦克(该坦克不参与战斗)
		this.drawTank(80,330, g, 0, 0);
		g.setColor(Color.black);
		g.drawString(Recorder.getExsitEny()+"", 110, 350);
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
		g.drawString(Recorder.getAllEnNum()+"", 460, 80);
	}
	
	//重新paint
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		
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
			System.out.println("bombs.size()="+bombs.size());		
			//取出炸弹
			Bomb b=bombs.get(i);
			
			if(b.life>6)
			{
				g.drawImage(image1, b.x, b.y, 30, 30, this);
			}else if(b.life>3)
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
		
		//画出敌人的坦克
		for(int i=0;i<ets.size();i++)
		{
			EnemyTank et=ets.get(i);		
			if(et.isLive)
			{		
				this.drawTank(et.getX(), et.getY(), g, et.getDirect(), 0);
				//再画出敌人的子弹
				//System.out.println("坦克子弹有:"+et.ss.size());
				for(int j=0;j<et.ss.size();j++)
				{
					//取出子弹
					Shot enemyShot=et.ss.get(j);
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
				if(hero.isLive)
				{
					if(this.hitTank(enemyShot, hero)) // hero 被打中
					{
						
					}
				}
			}
		}
	}
	
	
	//判断我的子弹是否击中敌人的坦克
	public void hitEnemyTank()
	{
		//判断是否击中敌人的坦克
		for(int i=0;i<hero.ss.size();i++)
		{
			//取出子弹 
			Shot myShot=hero.ss.get(i);
			//判断子弹是否有效
			if(myShot.isLive)
			{
				//取出每个坦克，与它判断
				for(int j=0;j<ets.size();j++)
				{
					//取出坦克
					EnemyTank et=ets.get(j);
					
					if(et.isLive)
					{
						if(this.hitTank(myShot, et))
						{
							//减少敌人数量
							Recorder.reduceEnNum();
							//增加我的记录
							Recorder.addEnNumRec();
							
							Recorder.setExsitEny();
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
				System.out.println(et.getmylife());
				//击中
				//子弹死亡
				s.isLive=false;
				//敌人坦克死亡
				b2=true;
				if(et.getmylife()==1) { // 死亡的为敌方坦克 
					//创建一颗炸弹,放入Vector
					et.isLive=false;
					System.out.println("gg!");
					Bomb b=new Bomb(et.x,et.y);
					//放入Vector
					bombs.add(b);
				}
				else {// hero 被击中但未死亡
					et.ducelife();
					et.setX(100);
					et.setY(100);
					//((Hero) et).Hero(100,100);
					//System.out.println("hhhh"+ et.mylife);
				}
			}
			
			break;
		case 1:
		case 3:
			if(s.x>et.x&&s.x<et.x+30&&s.y>et.y&&s.y<et.y+20)
			{
				System.out.println(et.getmylife());
				//击中
				//子弹死亡
				s.isLive=false;
				//敌人坦克死亡
				b2=true;
				if(et.getmylife()==1) { // 死亡的为敌方坦克 
					//创建一颗炸弹,放入Vector
					et.isLive=false;
					System.out.println("gg!");
					Bomb b=new Bomb(et.x,et.y);
					//放入Vector
					bombs.add(b);
				}
				else {// hero 被击中但未死亡
					et.ducelife();
					et.setX(100);
					et.setY(100);
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
			g.setColor(Color.yellow);
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
			this.hero.setDirect(0);
			this.hero.moveUp();
			
		}else if(arg0.getKeyCode()==KeyEvent.VK_D)
		{
			//向右
			this.hero.setDirect(1);
			this.hero.moveRight();
		}else if(arg0.getKeyCode()==KeyEvent.VK_S)
		{
			//向下
			this.hero.setDirect(2);
			this.hero.moveDown();
		}else if(arg0.getKeyCode()==KeyEvent.VK_A)
		{
			//向左
			this.hero.setDirect(3);
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
			this.hitEnemyTank();
			//函数，判断敌人的子弹是否击中我
			this.hitMe();		
			//重绘
			this.repaint();
		}
	}
}


