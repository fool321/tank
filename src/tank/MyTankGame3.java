/**
 * ����:̹����Ϸ��5.0[]
 * 1.����̹��.
 * 2.�ҵ�̹�˿������������ƶ�
 * 3.���Է����ӵ�,�ӵ�����(���5)
 * 4.���ҵ�̹�˻��е���̹��ʱ�����˾���ʧ(��ը��Ч��)
 * 5.�ұ����к���ʾ��ըЧ��
 * 6.��ֹ����̹���ص��˶�(*)
 *    6.1�������ж��Ƿ���ײ�ĺ���д��EnemyTank��
 * 7.���Էֹ�(*)
 * 	  7.1��һ����ʼ��Panle,����һ���յ�
 *    7.2��˸Ч��
 * 8.����������Ϸ��ʱ����ͣ�ͼ�����*��
 * 	  8.1���û������ͣʱ���ӵ����ٶȺ�̹���ٶ���Ϊ0,����̹�˵ķ���Ҫ�仯
 * 9.���Լ�¼��ҵĳɼ���*��
 *    9.1���ļ���.
 *    9.2��дһ����¼�࣬��ɶ���Ҽ�¼
 *    9.3����ɱ��湲�����˶���������̹�˵Ĺ���.
 *    9.4�����˳���Ϸ,���Լ�¼��ʱ�ĵ���̹�����꣬�����Իָ�
 * 10.java��β��������ļ���*��
 * 		10.1�������ļ��Ĳ���
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
	// ����һ����ʼ���
	MyStartPanel msp = null;

	// ��������Ҫ�Ĳ˵�
	JMenuBar jmb = null;
	// ��ʼ��Ϸ
	JMenu jm1 = null;
	JMenu jm2 = null;
	JMenu jm3 = null;
	JMenu jm4 = null;
	JMenuItem jmil = null;
	JMenuItem jmi2 = null;// �˳�ϵͳ
	JMenuItem jmi3 = null;// �����˳�
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

	// ���캯��
	public MyTankGame3() {// ���
		// �����˵����˵�ѡ��
		jmb = new JMenuBar();
		jm1 = new JMenu("��Ϸ(G)");
		jm2 = new JMenu("��һ��(M)");
		jm3 = new JMenu("�÷����(P)");
		jm4 = new JMenu("��Ϸ����(A)");

		jmil = new JMenuItem("��ʼ����Ϸ(N)");
		jmi2 = new JMenuItem("�˳���Ϸ(E)");
		jmi3 = new JMenuItem("�����˳���Ϸ(C)");
		jmi4 = new JMenuItem("�����Ͼ���Ϸ(S)");
		jmi5 = new JMenuItem("��һ��(L)");
		jmi6 = new JMenuItem("��ǰ�÷�(Q)");
		jmi7 = new JMenuItem("��ʷ���ְ�(W)");
		jmi8 = new JMenuItem("��Ϸ����(B)");

		// ע�����
		jmi4.addActionListener(this);
		jmi4.setActionCommand("conGame");

		// ע�����
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
		// ���ÿ�ݷ�ʽ
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

	public void actionPerformed(ActionEvent arg0) {// ���û���ͬ�ĵ��������ͬ�Ĵ���
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals("next")) {
			if (Recorder.getstage() == 3) {// ////////////// 3
				if ((Recorder.getEnNummm(Recorder.getstage() - 1)) == 0) {// ����
					// ������Ϸ ѯ���������			
					scord.i=2;
					return;
				}
			}
			// System.out.println("hhhhhj");
			// System.out.println(Recorder.getstage()+" gg");
			// System.out.println(Recorder.getEnNummm(Recorder.getstage()-1)+" gg");
			if ((Recorder.getEnNummm(Recorder.getstage() - 1)) == 0) {// �˹��Ѿ�����//Recorder.getExsitEny()==0
				// System.out.println("7777777");
				Recorder.addstage();
				if (mp != null)
					this.remove(mp);
				mp = new MyPanel("next", Recorder.getstage());// ����mp�߳�
				// System.out.println("lzffff "+ Recorder.getstage());
				Thread t = new Thread(mp);
				t.start();
				this.add(mp);
				// ע�����
				this.addKeyListener(mp);
				// ��ʾ,ˢ��JFrame
				this.setVisible(true);
			}
		}
		if (arg0.getActionCommand().equals("newgame")) {
			// ����ս�����
//			System.out.println("gg");
			Recorder.resetstage();
			if (mp != null)
				this.remove(mp);

			mp = new MyPanel("newGame", 1);// ����mp�߳�
			Thread t = new Thread(mp);
			t.start();
			// ��ɾ���ɵĿ�ʼ���
			this.remove(msp); /// ���Ƴ����������
			this.add(mp);
			// ע�����
			this.addKeyListener(mp);
			// ��ʾ,ˢ��JFrame
			this.setVisible(true);
		} else if (arg0.getActionCommand().equals("exit")) {
			// �û�������˳�ϵͳ�Ĳ˵�
			// ������ٵ�������.
			Recorder.keepRecording();
			System.exit(0);
		} // �Դ����˳�
		else if (arg0.getActionCommand().equals("saveExit")) {
			// System.out.println("111");
			// System.out.println("mp.ets.size="+mp.ets.size());
			// ����
			Recorder rd = new Recorder();
			rd.setEts(mp.ets);
			// ������ٵ��˵������͵��˵�����
			rd.keepRecAndEnemyTank();
			// �˳�
			System.exit(0);
		} else if (arg0.getActionCommand().equals("conGame")) {
			// ����ս�����v //////δʵ����Ӧ���ܡ���
			mp = new MyPanel("con", 1);
			// ����mp�߳�
			Thread t = new Thread(mp);
			t.start();
			// ��ɾ���ɵĿ�ʼ���
			this.remove(msp);
			this.add(mp);
			// ע�����
			this.addKeyListener(mp);
			// ��ʾ,ˢ��JFrame
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
		super("�÷����");
		DefaultTableModel model = null;
		JTable table = null;
		int stage = Recorder.getstage();
		int num = Recorder.getEnNummm(stage);
//      System.out.println(stage);
//      System.out.println(num);
		String[][] datas = {};
		String[] titles = { "����ǳ�", "�÷�" };
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


class help extends JFrame {// �̳�JFrame����������

	// �������
	JPanel jp1, jp2, jp3;// �������
	JLabel jlb1;
	JButton jb1;

	public help()//���캯��
	{
		//�������
		jp1=new JPanel();//�����������
		jp2=new JPanel();
		jp3=new JPanel();
		
		jlb1=new JLabel("��鿴 readme�ĵ�");
		
		jb1=new JButton("got it");	
		
		//���ò��ֹ�����
		this.setLayout(new GridLayout(3,1));//���񲼾֣�3��һ��

		//������
		this.add(jp1);//������
		this.add(jp2);
		this.add(jp3);
		
		jp1.add(jlb1);//������1�ı�ǩ���ı���
		jp3.add(jb1);//������3�İ�ť
		
		jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose(); 
			}
		});
		//���ô�������
		this.setTitle("����");//�����������
		this.setSize(300, 150);//���ý�������
		this.setLocation(400, 200);//���ý����ʼλ��
	//	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ý���������һ��ر�
		this.setVisible(true);//���ý������ʾ
		this.setAlwaysOnTop(true);
	}
}

class Swing_JTextField extends JFrame {// �̳�JFrame����������

	// �������
	JPanel jp1, jp2, jp3;// �������
	JTextField jtf1;// �����ı���
	JLabel jlb1,jlb2;// �����ǩ
	JButton jb1;//,jb2;// ���尴ť

	public static void main() {//������
		Swing_JTextField a=new Swing_JTextField();//��ʾ����
	}

	public Swing_JTextField()//���캯��
	{
		//�������
		jp1=new JPanel();//�����������
		jp2=new JPanel();
		jp3=new JPanel();
		
		jlb1=new JLabel("�û���");
		jlb2=new JLabel("����ȷ����");
		jb1=new JButton("����÷�");	
		jtf1=new JTextField(10);//�����ı���
		
		//���ò��ֹ�����
		this.setLayout(new GridLayout(3,1));//���񲼾֣�3��һ��

		//������
		this.add(jp1);//������
		this.add(jp2);
		this.add(jp3);
		
		jp1.add(jlb1);//������1�ı�ǩ���ı���
		jp1.add(jtf1);
		jp3.add(jb1);//������3�İ�ť
		
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
		//���ô�������
		this.setTitle("ͨ�أ�");//�����������
		this.setSize(300, 150);//���ý�������
		this.setLocation(400, 200);//���ý����ʼλ��
	//	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ý���������һ��ر�
		this.setVisible(true);//���ý������ʾ
		this.setAlwaysOnTop(true);
	}
}

//
	class windows extends JFrame {
		pan pa = null;

		public void innit() {
			this.setTitle("ͳ�Ʒ���");
			this.setSize(500, 400);
			this.setLocation(230, 170);
			pa = new pan();
			this.add(pa);
			this.setVisible(true);
			// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		class pan extends JPanel implements Runnable {
			public void drawTank(int x, int y, Graphics g, int direct, int type) {
				// �ж���ʲô���͵�̹�� /// ��������ɫ������ҲҪ�䣬��װ����
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
				// �жϷ���
				switch (direct) {
				case 0:// ����
					g.fill3DRect(x, y, 5, 30, false);
					g.fill3DRect(x + 15, y, 5, 30, false);
					g.fill3DRect(x + 5, y + 5, 10, 20, false);
					g.fillOval(x + 5, y + 10, 10, 10);
					g.drawLine(x + 10, y + 15, x + 10, y);
					break;
				case 1:// ��Ͳ����
					g.fill3DRect(x, y, 30, 5, false);
					g.fill3DRect(x, y + 15, 30, 5, false);
					g.fill3DRect(x + 5, y + 5, 20, 10, false);
					g.fillOval(x + 10, y + 5, 10, 10);
					g.drawLine(x + 15, y + 10, x + 30, y + 10);
					break;
				case 2:// ����
					g.fill3DRect(x, y, 5, 30, false);
					g.fill3DRect(x + 15, y, 5, 30, false);
					g.fill3DRect(x + 5, y + 5, 10, 20, false);
					g.fillOval(x + 5, y + 10, 10, 10);
					g.drawLine(x + 10, y + 15, x + 10, y + 30);
					break;
				case 3: // ����
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
				g.drawString(Recorder.getkill(1) + "    ��    100  =    " + Recorder.getkill(1) * 100 + "\n", 100, 75);

				this.drawTank(50, 90, g, 0, 2);
				g.setColor(Color.black);
				g.drawString(Recorder.getkill(2) + "    ��    200  =    " + Recorder.getkill(2) * 200 + "\n", 100, 115);

				this.drawTank(50, 130, g, 0, 3);
				g.setColor(Color.black);
				g.drawString(Recorder.getkill(3) + "    ��    300  =    " + Recorder.getkill(3) * 300 + "\n", 100, 155);

				this.drawTank(50, 170, g, 0, 4);
				g.setColor(Color.black);
				g.drawString(Recorder.getkill(4) + "    ��    400  =    " + Recorder.getkill(4) * 400 + "\n", 100, 195);

				this.drawTank(50, 210, g, 0, 5);
				g.setColor(Color.black);
				g.drawString(Recorder.getkill(5) + "    ��    1000  =    " + Recorder.getkill(5) * 1000 + "\n", 100,
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
					// ����
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

//����һ����ʾ����
	class MyStartPanel extends JPanel implements Runnable {
		int times = 0;

		public void paint(Graphics g) {
			super.paint(g);
			g.fillRect(0, 0, 400, 300);
			// ��ʾ��Ϣ
			if (times % 2 == 0) {
				g.setColor(Color.yellow);
				// ������Ϣ������
				Font myFont = new Font("������κ", Font.BOLD, 30);
				g.setFont(myFont);
				g.drawString("welcome to stage: 1", 30, 150);
			}
		}

		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				// ����
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
				times++;
				// �ػ�
				this.repaint();
			}
		}
	}

//�ҵ����
class MyPanel extends JPanel implements KeyListener,Runnable
{
	//����һ���ҵ�̹��
	Hero hero=null;
	//������˵�̹����
	Vector<EnemyTank> ets=new Vector<EnemyTank>();
	Vector<Node> nodes=new Vector<Node>();	
	//����ը������
	Vector<Bomb> bombs=new Vector<Bomb>();
	//����ǽ�鼯��
	Vector<bug> bugs = new Vector<bug>();
	
	int enSize=Recorder.getEnNum();   //enamy size ������Ŀ  ÿ�ز�ͬ
	int stage;
	//��������ͼƬ,����ͼƬ�������һ��ը��
	Image image1=null;
	Image image2=null;
	Image image3=null;
	AePlayWave apw=new AePlayWave(this.getClass().getResource("./111.wav").getPath());
	
	//���캯��
	public MyPanel(String flag,int stage)// int stage
	{		
		//�ָ���¼ 
		
		Recorder.getRecoring();	
		hero=new Hero(50,200);	
		//Vector<bug> bbBugs = new Vector<bug>()
		if(flag.equals("newGame"))
		{
			apw.start();
			//��ʼ��ש��
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
			//��ʼ�����˵�̹��
			for(int i=0;i<enSize;i++)
			{
				//����һ�����˵�̹�˶���
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
				//��MyPanel�ĵ���̹�����������õ���̹��
				et.setEts(ets);   //����̹�˼�Ľ���������֪��
				//�������˵�̹��
				Thread t=new Thread(et);
				t.start();
				//������̹�����һ���ӵ�
				Shot s=new Shot(et.x+10,et.y+30,2);
				//���������̹��
				et.ss.add(s);
				Thread t2=new Thread(s);
				t2.start();
				//����
				ets.add(et);
			}
		}
		else if(flag.equals("next")) {//֮��Ĺؿ�
			this.stage = stage;
			enSize = Recorder.getEnNummm(stage-1);
			//��ʼ��ש��
			bug b11 =  null;
			bug b12 =  null;
			if(stage==2) {//�ڶ���
				b11 = map.b21;b12=map.b22;
			}
			else if(stage==3) {//��3��
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

			//��ʼ�����˵�̹��
			for(int i=0;i<enSize;i++)
			{
				//����һ�����˵�̹�˶���
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
				//��MyPanel�ĵ���̹�����������õ���̹��
				et.setEts(ets);   //����̹�˼�Ľ���������֪��
				//�������˵�̹��
				Thread t=new Thread(et);
				t.start();
				//������̹�����һ���ӵ�
				Shot s=new Shot(et.x+10,et.y+30,2);
				//���������̹��
				et.ss.add(s);
				Thread t2=new Thread(s);
				t2.start();
				//����
				ets.add(et);
			}
		
		}
		else if(flag.equals("cons")){
			this.stage = stage;
			//System.out.println("������");
			nodes=new Recorder().getNodesAndEnNums();  // ���ļ��ж��� x Y direct
			//��ʼ�����˵�̹��
			for(int i=0;i<nodes.size();i++)
			{
				Node node=nodes.get(i);
				//����һ�����˵�̹�˶���
				EnemyTank et=new EnemyTank(node.x,node.y);
				et.setColor(0);
				et.setDirect(node.direct);
				//��MyPanel�ĵ���̹�����������õ���̹��
				et.setEts(ets);
				
				//�������˵�̹��
				Thread t=new Thread(et);
				t.start();
				//������̹�����һ���ӵ�
				Shot s=new Shot(et.x+10,et.y+30,2);
				//���������̹��
				et.ss.add(s);
				Thread t2=new Thread(s);
				t2.start();
				//����
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
	
	
	//������ʾ��Ϣ
	public void showInfo(Graphics g)
	{
		//������ʾ��Ϣ̹��(��̹�˲�����ս��)
		this.drawTank(80,330, g, 0, 0);
		g.setColor(Color.black);
		g.drawString(Recorder.getEnNummm(stage-1)+"", 110, 350);
		this.drawTank(170, 330, g, 0, 1);
		g.setColor(Color.black);
		g.drawString(Recorder.currntlife+"", 210, 350);
		
		//������ҵ��ܳɼ�
		g.setColor(Color.black);
		Font f=new Font("����",Font.BOLD,20);
		g.setFont(f);
		g.drawString("�����ܳɼ�", 420, 30);
		
		this.drawTank(420, 60, g, 0, 0);
		
		g.setColor(Color.black);
		g.drawString(Recorder.getscore()+"", 460, 80);
		//System.out.println(Recorder.getscore()+"gg");
	}
	
	//����paint
	public void paint(Graphics g)
	{

		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		//g.drawPolygon((20,100), 100, 2);
		//������ʾ��Ϣ
		this.showInfo(g);

		//�����Լ���̹��
		if(hero.isLive)
		{
			this.drawTank(hero.getX(), hero.getY(), g, this.hero.direct, 1);
		}
		//��ss,��ȡ��ÿ���ӵ���������
		for(int i=0;i<hero.ss.size();i++)
		{
			Shot myShot=hero.ss.get(i);
			
			//�����ӵ�,����һ���ӵ�
			if(myShot!=null&&myShot.isLive==true)
			{
				g.draw3DRect(myShot.x, myShot.y, 1, 1,false);
			}
			if(myShot.isLive==false)
			{
				//��ss��ɾ�������ӵ�
				hero.ss.remove(myShot);
			}
			
		}	
		//����ը��
		for(int i=0;i<bombs.size();i++)
		{
			//System.out.println("bombs.size()="+bombs.size());		
			//ȡ��ը��
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
			
			//��b������ֵ��С
			b.lifeDown();
			//���ը������ֵΪ0,�ͰѸ�ը����bombs����ȥ��
			if(b.life==0)
			{
				bombs.remove(b);
			}	
		}
		
		//System.out.println("lzf666 " + bugs.size() + " lzy");
		//����ǽ��
		for(int i=0;i<bugs.size();i++) {
			//v
			bug lzBug = bugs.get(i);
			if(lzBug.islive) {//û�б��ݻ�
				//System.out.println("lzf666 "+ lzBug.type );
				switch(lzBug.type) {
					case 1:
						g.setColor(Color.yellow); // ש��
						break;
					case 2:
						g.setColor(Color.red);//����
						break;
					case 3:
						g.setColor(Color.blue);//����
						break;
				}
				//g.setColor(Color.yellow);
				if(lzBug.type==3)
					g.fillRect(lzBug.x, lzBug.y, 30, 30); //���ڱ߿�ֱ�����
				else
					g.fill3DRect(lzBug.x, lzBug.y, 30, 30, false); // ���ڱ߿�
			}

		}
		//�������˵�̹��
		for(int i=0;i<ets.size();i++)
		{
			EnemyTank et=ets.get(i);		
			if(et.isLive)
			{		
				this.drawTank(et.getX(), et.getY(), g, et.getDirect(), et.color);
				//�ٻ������˵��ӵ�
				//System.out.println("̹���ӵ���:"+et.ss.size());
				
				for(int j=0;j<et.ss.size();j++)
				{
					//ȡ���ӵ�
					Shot enemyShot=et.ss.get(j);

					if(et.type == 2)
						enemyShot.speed = 2;
					if(et.type == 5)
						enemyShot.speed = 3;
					if(enemyShot.isLive)
					{
						//System.out.println("�� "+i+"̹�˵� "+j+"���ӵ�x="+enemyShot.x);
						g.draw3DRect(enemyShot.x, enemyShot.y, 1, 1,false);
					}else{
						//������˵�̹�������ʹ�Vectorȥ��
						et.ss.remove(enemyShot);
					}
				}
			}
		}	
	}
	
	//���˵�̹���Ƿ������
	public void hitMe()
	{
		//ȡ��ÿһ�����˵�̹��
		for(int i=0;i<this.ets.size();i++)
		{
			//ȡ��̹��
			EnemyTank et=ets.get(i);
			
			//ȡ��ÿһ���ӵ�
			for(int j=0;j<et.ss.size();j++)
			{
				//ȡ���ӵ�
				Shot enemyShot=et.ss.get(j);
				if(enemyShot.isLive==false)
					continue;
				if(hero.isLive)
				{
					//�������� �ӵ���ʧ
					bug n1 = null;
					for(int k=0;k<bugs.size();k++) {
						//�ж���ײ
						n1 = bugs.get(k);
						if(n1.type==3)
							continue;
						if(enemyShot.x>=n1.x&&enemyShot.x<=n1.x+20&&enemyShot.y>=n1.y&&enemyShot.y<=n1.y+30) {
							if(n1.type==1) {//ש��
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
					if(this.hitTank(enemyShot, hero)) // hero ������
					{
						
					}
				}
			}
		}
	}
	//�ж��ҵ��ӵ��Ƿ���е��˵�̹��
	public void hitEnemyTank(int stage)
	{
		//�ж��Ƿ���е��˵�̹��
		for(int i=0;i<hero.ss.size();i++)
		{
			//ȡ���ӵ� 
			Shot myShot=hero.ss.get(i);
			//�ж��ӵ��Ƿ���Ч
			
			if(myShot.isLive)
			{
				//�ӵ� ײǽ
				bug n1 = null;
				for(int k=0;k<bugs.size();k++) {
					//�ж���ײ
					n1 = bugs.get(k);
					if(n1.type==3)
						continue;
					if(myShot.x>=n1.x&&myShot.x<=n1.x+20&&myShot.y>=n1.y&&myShot.y<=n1.y+30) {
						if(n1.type==1) {//ש��
							n1.islive = false;
							myShot.isLive = false;
							bugs.remove(n1);
						}
						else if(n1.type ==2) {
							myShot.isLive = false;
						}
					}
				}
				
				//ȡ��ÿ��̹�ˣ������ж�
				for(int j=0;j<ets.size();j++)
				{
					//ȡ��̹��
					EnemyTank et=ets.get(j);
					
					if(et.isLive)
					{
						if(this.hitTank(myShot, et))
						{
							Recorder.addkill(et.type);
							//���ٵ�������
							//Recorder.reduceEnNum();
							//�����ҵļ�¼
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
	
	//дһ������ר���ж��ӵ��Ƿ���е���̹��
	public boolean hitTank(Shot s,Tank et)
	{
		boolean b2=false;
		
		//�жϸ�̹�˵ķ���
		switch(et.direct)
		{
		//�������̹�˵ķ������ϻ�������
		case 0:
		case 2:
			if(s.x>et.x&&s.x<et.x+20&&s.y>et.y&&s.y<et.y+30)
			{
				//System.out.println(et.getmylife());
				//����
				//�ӵ�����
				s.isLive=false;
				//����̹������
				b2=true;
				if(et.getmylife()==1) { // ������Ϊ�з�̹�� 
					
					
					//����һ��ը��,����Vector
					et.isLive=false;
					
					//System.out.println("gg!");
					Bomb b=new Bomb(et.x,et.y);
					//����Vector
					bombs.add(b);
				}
				else {// hero �����е�δ����
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
				//����
				//�ӵ�����
				s.isLive=false;
				//����̹������
				b2=true;
				if(et.getmylife()==1) { // ������Ϊ�з�̹�� 
					//����һ��ը��,����Vector
					et.isLive=false;
					//System.out.println("gg!");
					Bomb b=new Bomb(et.x,et.y);
					//����Vector
					bombs.add(b);
				}
				else {// hero �����е�δ����
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
	
	//����̹�˵ĺ���(��չ)
	public void drawTank(int x,int y,Graphics g,int direct,int type)
	{
		//�ж���ʲô���͵�̹��            ///       ��������ɫ������ҲҪ�䣬��װ����
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
		//�жϷ���
		switch(direct)
		{
		case 0://����
			g.fill3DRect(x, y, 5, 30,false); 
			g.fill3DRect(x+15,y , 5, 30,false);
			g.fill3DRect(x+5,y+5 , 10, 20,false);
			g.fillOval(x+5, y+10, 10, 10);
			g.drawLine(x+10, y+15, x+10, y);
			break;
		case 1://��Ͳ����					
			g.fill3DRect(x, y, 30, 5,false);
			g.fill3DRect(x, y+15, 30, 5, false);
			g.fill3DRect(x+5, y+5, 20, 10, false);
			g.fillOval(x+10, y+5, 10, 10);
			g.drawLine(x+15, y+10, x+30, y+10);
			break;
		case 2://����
			g.fill3DRect(x, y, 5, 30,false);
			g.fill3DRect(x+15,y , 5, 30,false);
			g.fill3DRect(x+5,y+5 , 10, 20,false);
			g.fillOval(x+5, y+10, 10, 10);
			g.drawLine(x+10, y+15, x+10, y+30);
			break;
		case 3:	//����
			g.fill3DRect(x, y, 30, 5,false);
			g.fill3DRect(x, y+15, 30, 5, false);
			g.fill3DRect(x+5, y+5, 20, 10, false);
			g.fillOval(x+10, y+5, 10, 10);
			g.drawLine(x+15, y+10, x, y+10);
			break;
		}				
	}

	//�����´��� a ��ʾ���� s ��ʾ �� w ��ʾ����  d��ʾ��
	
	public void keyPressed(KeyEvent arg0) {  // ˫�׼��̣�����
		// TODO Auto-generated method stub	
		if(arg0.getKeyCode()==KeyEvent.VK_W)
		{
			//�����ҵ�̹�˵ķ���
			if(this.hero.setDirect(0))
				this.hero.moveUp();
			
		}else if(arg0.getKeyCode()==KeyEvent.VK_D)
		{
			//����
			if(this.hero.setDirect(1))
				this.hero.moveRight();
		}else if(arg0.getKeyCode()==KeyEvent.VK_S)
		{
			//����
			if(this.hero.setDirect(2))
				this.hero.moveDown();
		}else if(arg0.getKeyCode()==KeyEvent.VK_A)
		{
			//����
			if(this.hero.setDirect(3))
				this.hero.moveLeft();
		}
		
		if(arg0.getKeyCode()==KeyEvent.VK_J)
		{
			//�ж�����Ƿ���j			
			//����
			//System.out.println("this.hero.ss.size()="+this.hero.ss.size());
			if(this.hero.ss.size()<=4)
			{
				this.hero.shotEnemy();
			}
			
		}
		//�������»���Panel
		this.repaint(); ///������
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
		//ÿ��100����ȥ�ػ�
		while(true)
		{
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			this.hitEnemyTank(stage);
			//�������жϵ��˵��ӵ��Ƿ������
			this.hitMe();		
			//�ػ�
			this.repaint();
		}
	}
}
