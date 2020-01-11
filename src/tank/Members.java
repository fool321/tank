package tank;

import java.util.*;
import java.io.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

//播放声音的类
class AePlayWave extends Thread {

	private String filename;

	public AePlayWave(String wavfile) {
		filename = wavfile;
	}

	public void run() {

		File soundFile = new File(filename);

		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}

		AudioFormat format = audioInputStream.getFormat();
		SourceDataLine auline = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

		try {
			auline = (SourceDataLine) AudioSystem.getLine(info);
			auline.open(format);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		auline.start();
		int nBytesRead = 0;
		// 这是缓冲
		byte[] abData = new byte[512];

		try {
			while (nBytesRead != -1) {
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if (nBytesRead >= 0)
					auline.write(abData, 0, nBytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			auline.drain();
			auline.close();
		}

	}

}

class Node {
	int x;
	int y;
	int direct;

	public Node(int x, int y, int direct) {
		this.x = x;
		this.y = y;
		this.direct = direct;
	}
}

class bug implements Runnable {// 地形块
	int x;
	int y;
	int type;
	boolean islive = true;

	// 1 砖 阻止移动 可打穿
	// 2 铁 阻止移动 不可打穿 // shot 。 live 上相关
	// 3 河流 阻止移动 炮弹无视
	public bug(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}

//每关地图
class map {
	int b1 = 5, b2 = 6, b3 = 7, b4 = 8, b5 = 9, b6 = 10, b7 = 11, b8 = 12;
	Vector<bug> bugs1 = new Vector<bug>();
	Vector<bug> bugs2 = new Vector<bug>();
	Vector<bug> bugs3 = new Vector<bug>();
	Vector<bug> bugs4 = new Vector<bug>();
	Vector<bug> bugs5 = new Vector<bug>();
	Vector<bug> bugs6 = new Vector<bug>();
	Vector<bug> bugs7 = new Vector<bug>();
	Vector<bug> bugs8 = new Vector<bug>();
	static bug b11 = new bug(100, 100, 1);
	static bug b12 = new bug(300, 160, 3);
	static bug b21 = new bug(100, 180, 2);
	static bug b22 = new bug(300, 240, 2);
	static bug b31 = new bug(100, 230, 1);
	static bug b32 = new bug(300, 290, 1);

	static bug b41 = new bug(10, 150, 1);
	static bug b42 = new bug(50, 210, 1);
//	for(int i=1;i<=b1;i++) {
//		
//	}
}

class sc {
	String name;
	String sc;

	public sc(String nameString, String scroString) {
		this.name = nameString;
		this.sc = scroString;
	}

	public String getsc() {
		return this.sc;
	}

	public String getna() {
		return this.name;
	}
}

class scord {
	public static int i = 0;
	public static String nameString=null; 
	public static int num = 0;// 需重置
	static public sc sc1 = null;
	static public sc sc2 = null;
	static public sc sc3 = null;
	static public sc sc4 = null;
	static public sc sc5 = null;

	public static void inni() {// 重置
		num = 0;
		sc1 = null;
		sc2 = null;
		sc3 = null;
		sc4 = null;
		sc5 = null;
	}

	public static void readFromFile() {
		List<Integer> inputList = null;
		try (FileReader reader = new FileReader("TGame.confg.txt"); BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
		) {

			String line;
			while ((line = br.readLine()) != null) {
				num++;
				if (num == 6)
					return;
				String[] inputdatas = line.split(" ");
				int i = 0;
				String a1 = null;
				String a2 = null;
				for (String s : inputdatas) {
					if (i == 0) {
						a1 = s;
						i++;
					} else {
						a2 = s;
					}
					// System.out.print("'"+s+"' ");
					// System.out.println(s);
				}
				switch (num) {
				case 1:
					sc1 = new sc(a1, a2);
					break;
				case 2:
					sc2 = new sc(a1, a2);
					break;
				case 3:
					sc3 = new sc(a1, a2);
					break;
				case 4:
					sc4 = new sc(a1, a2);
					break;
				case 5:
					sc5 = new sc(a1, a2);
					break;
				default:
					break;
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void method1(String a1) {
		FileWriter fw = null;
		try {
			// 如果文件存在，则追加内容；如果文件不存在，则创建文件
			File f = new File("TGame.confg.txt");
			fw = new FileWriter(f, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		//pw.println();
		pw.println(a1);
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeTxt(String txtPath, String content) {
		FileOutputStream fileOutputStream = null;
		File file = new File(txtPath);
		try {
			if (file.exists()) {
				// 判断文件是否存在，如果不存在就新建一个txt
				// file.createNewFile();
			}
			fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(content.getBytes());
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String readTxt(String txtPath) {
		File file = new File(txtPath);
		//System.out.println("djakl");
		if (file.isFile() && file.exists()) {
			//System.out.println("????");
			try {
				FileInputStream fileInputStream = new FileInputStream(file);
				InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

				StringBuffer sb = new StringBuffer();
				String text = null;
				while ((text = bufferedReader.readLine()) != null) {
					sb.append(text);
				}
				System.out.println(sb.toString());
				return sb.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}

//记录类,同时也可以保存玩家的设置
class Recorder {
	// 记录每关有多少敌人
	private static int enNum = 3; /////
	private static int enNum1 = 5;
	private static int enNum2 = 7;
	private static int enNum3 = 10;
	private static int enNum4 = 13;
	private static int enNum5 = 16;
	private static int score = 0;
	// 当前还有多少敌人
	private static int exsitEny = enNum;
	// 设置我有多少可以用的人
	private static int myLife = 4;

	private static int kill1 = 0; /////
	private static int kill2 = 0;
	private static int kill3 = 0;
	private static int kill4 = 0;
	private static int kill5 = 0;

	public static void addkill(int i) {
		switch (i) {
		case 1:
			kill1++;
			break;
		case 2:
			kill2++;
			break;
		case 3:
			kill3++;
			break;
		case 4:
			kill4++;
			break;
		case 5:
			kill5++;
			break;

		default:
			break;
		}
	}

	public static int getkill(int i) {
		int gg = 0;
		switch (i) {
		case 1:
			gg = kill1;
			break;
		case 2:
			gg = kill2;
			break;
		case 3:
			gg = kill3;
			break;
		case 4:
			gg = kill4;
			break;
		case 5:
			gg = kill5;
			break;

		default:
			break;
		}
		return gg;
	}

	public static void allrenew() {
		enNum = 3; /////
		enNum1 = 5;
		enNum2 = 7;
		enNum3 = 10;
		enNum4 = 13;
		enNum5 = 16;
		score = 0;
		myLife = 4;
		currntlife = myLife;
		kill1 = 0;
		kill2 = 0;
		kill3 = 0;
		kill4 = 0;
		kill5 = 0;
	}

	// 记录总共消灭了多少敌人
	private static int allEnNum = 0;

	public static int currntlife = myLife;

	private static int stage = 1;

	public static int getscore() {
		return score;
	}

	public static void setscore(int i) {
		switch (i) {
		case 1:
			score += 100;
			break;
		case 2:
			score += 200;
			break;
		case 3:
			score += 300;
			break;
		case 4:
			score += 400;
			break;
		case 5:
			score += 1000;
			break;

		default:
			break;
		}
	}

	public static void addstage() {
		stage++;
	}

	public static int getstage() {
		return stage;
	}

	public static void resetstage() {
		stage = 1;
	}

	// 从文件中恢复记录点
	static Vector<Node> nodes = new Vector<Node>();

	private static FileWriter fw = null;
	private static BufferedWriter bw = null;
	private static FileReader fr = null;
	private static BufferedReader br = null;

	private Vector<EnemyTank> ets = new Vector<EnemyTank>();

	// 完成读取认为
	public Vector<Node> getNodesAndEnNums() {
		try {
			fr = new FileReader("d:\\myRecording.txt");
			br = new BufferedReader(fr);
			String n = "";
			// 先读取第一行 //第一行有多少敌人
			n = br.readLine();
			allEnNum = Integer.parseInt(n);
			while ((n = br.readLine()) != null) {
				String[] xyz = n.split(" ");

				Node node = new Node(Integer.parseInt(xyz[0]), Integer.parseInt(xyz[1]), Integer.parseInt(xyz[2]));
				nodes.add(node);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {

			try {
				// 后打开则先关闭
				br.close();
				fr.close();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}

		return nodes;

	}

	// 保存击毁敌人的数量和敌人坦克坐标,方向

	public void keepRecAndEnemyTank() {
		try {

			// 创建
			fw = new FileWriter("d:\\myRecording.txt");
			bw = new BufferedWriter(fw);

			bw.write(allEnNum + "\r\n");

			System.out.println("size=" + ets.size());
			// 保存当前活的敌人坦克的坐标和方向
			for (int i = 0; i < ets.size(); i++) {
				// 取出第一个坦克
				EnemyTank et = ets.get(i);

				if (et.isLive) {
					// 活的就保存
					String recode = et.x + " " + et.y + " " + et.direct;

					// 写入
					bw.write(recode + "\r\n");

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {

			// 关闭流
			try {
				// 后开先关闭
				bw.close();
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}

	// 从文件中读取，记录
	public static void getRecoring() // 只是打开，一点用都没有
	{
		try {
			fr = new FileReader("d:\\myRecording.txt");
			br = new BufferedReader(fr);
			String n = br.readLine();
			allEnNum = Integer.parseInt(n);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {

			try {
				// 后打开则先关闭
				br.close();
				fr.close();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}

	// 把玩家击毁敌人坦克数量保存到文件中
	public static void keepRecording() {
		try {

			// 创建
			fw = new FileWriter("d:\\myRecording.txt");
			bw = new BufferedWriter(fw);

			bw.write(allEnNum + "\r\n");

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {

			// 关闭流
			try {
				// 后开先关闭
				bw.close();
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}

	public static int getEnNum() {
		return enNum;
	}

	public static int getEnNummm(int i) {
		switch (i) {
		case 0:
			return enNum;
		case 1:
			return enNum1;
		case 2:
			return enNum2;
		case 3:
			return enNum3;
		case 4:
			return enNum4;
		case 5:
			return enNum5;

		default:
			break;
		}
		return i;
	}

	public static void setEnNum(int enNum) {
		Recorder.enNum = enNum;
	}

	public static int getMyLife() {
		return myLife;
	}

	public static int getExsitEny() {
		return exsitEny;
	}

	public static void setExsitEny() {
		Recorder.exsitEny -= 1;
	}

	public static void reduceenmy(int stage) {
		switch (stage) {
		case 1:
			enNum--;
			break;
		case 2:
			enNum1--;
			break;
		case 3:
			enNum2--;
			break;
		case 4:
			enNum3--;
			break;
		case 5:
			enNum4--;
			break;
		case 6:
			enNum5--;
			break;
		default:
			break;
		}
	}

	public static void setMyLife(int myLife) {
		Recorder.myLife = myLife;
	}

	// 减少敌人数
	public static void reduceEnNum() {
		enNum--;
	}

	// 消灭敌人
	public static void addEnNumRec() {
		allEnNum++;
	}

	public static int getAllEnNum() {
		return allEnNum;
	}

	public static void setAllEnNum(int allEnNum) {
		Recorder.allEnNum = allEnNum;
	}

	public Vector<EnemyTank> getEts() {
		return ets;
	}

	public void setEts(Vector<EnemyTank> ets1) {
		this.ets = ets1;
		System.out.println("ok");
	}
}

//炸弹类
class Bomb {
	// 定义炸弹的坐标
	int x, y;
	// 炸弹的生命
	int life = 90;
	boolean isLive = true;

	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// 减少生命值
	public void lifeDown() {
		if (life > 0) {
			life--;
		} else {
			this.isLive = false;
		}

	}

}

//子弹类
class Shot implements Runnable {
	int x;
	int y;
	int direct;
	int speed = 1;
//	public void setspeed(int i) {  // useless 
//		if(i == 2)//pink
//			this.speed = 2;
//		if(i == 5)
//			this.speed = 3; //boss
//		this.speed = 1;
//	}
	// 是否还活着
	boolean isLive = true;

	public Shot(int x, int y, int direct) {
		this.x = x;
		this.y = y;
		this.direct = direct;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				// TODO: handle exception
			}

			switch (direct) {
			case 0:
				// 上
				y -= speed;
				break;
			case 1:
				x += speed;
				break;
			case 2:
				y += speed;
				break;
			case 3:
				x -= speed;
				break;
			}

			// System.out.println("子弹坐标x="+x+" y="+y);
			// 子弹何时死亡???

			// 判断该子弹是否碰到边缘.
			if (x < 0 || x > 400 || y < 0 || y > 300) {
				this.isLive = false;
				break;
			}
		}
	}
}

//坦克类
class Tank {
	// 表示坦克的横坐标
	int mylife = 1;
	int x = 0;
	// 坦克纵坐标
	int y = 0;

	// 坦克方向
	// 0表示上 1表示 右 2表示下 3表示左
	int direct = 0;
	int color;

	boolean isLive = true;

	// 坦克的速度
	int speed = 1;
	int value = 100;
	int type = 1; // 决定坦克的种类

	public void settank(int i) {// 颜色 移动速度 子弹速度 生命值
		if (i == 1) {// cyan
			this.speed = 1;
			this.value = 100;
			this.type = 1;
			this.color = 0;
		}
		if (i == 2) {// pink
			this.speed = 1;
			this.value = 200;
			this.type = 2;
			this.color = 2;
		}
		if (i == 3) {// red
			this.speed = 2;
			this.value = 300;
			this.type = 3;
			this.color = 3;
		}
		if (i == 4) {// green
			this.speed = 1;
			this.value = 400;
			this.mylife = 4;
			this.type = 4;
			this.color = 4;
		}
		if (i == 5) {// blue
			this.speed = 2;
			this.value = 500;
			this.mylife = 6;
			this.type = 5;
			this.color = 5;
		}
	}

	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDirect() {
		return direct;
	}

	public boolean setDirect(int direct) {
		this.direct = direct;
		return true;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public void ducelife() {
		this.mylife -= 1;
	}

	public int getmylife() {
		return this.mylife;
	}

}

//敌人的坦克,把敌人做成线程类
class EnemyTank extends Tank implements Runnable {
	int times = 0;
	Vector<bug> bugs = new Vector<bug>();

	public void setbugs(bug g1) {
		this.bugs.add(g1);
	}

	// 定义一个向量，可以访问到MyPanel上所有敌人的坦克
	Vector<EnemyTank> ets = new Vector<EnemyTank>();

	// 定义一个向量，可以存放敌人的子弹
	Vector<Shot> ss = new Vector<Shot>();

	// 敌人添加子弹，应当在刚刚创建坦克和敌人的坦克子弹死亡后
	public EnemyTank(int x, int y) {
		super(x, y);
	}

	// 得到MyPanel的敌人坦克向量
	public void setEts(Vector<EnemyTank> vv) {
		this.ets = vv;
	}

	// 判断是否碰到了别的敌人坦克
	public boolean isTouchOtherEnemy() {
		boolean b = false;
		switch (this.direct) {
		case 0:
			// 我的坦克向上
			// 取出所有的敌人坦克
			for (int i = 0; i < ets.size(); i++) {
				// 取出第一个坦克 //取出队列的第i 个坦克
				EnemyTank et = ets.get(i);
				// 如果不是自己
				if (et != this) {
					// 如果敌人的方向是向下或者向上
					if (et.direct == 0 || et.direct == 2) {
						// 左点
						if (this.x >= et.x && this.x <= et.x + 20 && this.y >= et.y && this.y <= et.y + 30) {
							return true;
						}
						if (this.x + 20 >= et.x && this.x + 20 <= et.x + 20 && this.y >= et.y && this.y <= et.y + 30) {
							return true;
						}
					}
					if (et.direct == 3 || et.direct == 1) {
						if (this.x >= et.x && this.x <= et.x + 30 && this.y >= et.y && this.y <= et.y + 20) {
							return true;
						}
						if (this.x + 20 >= et.x && this.x + 20 <= et.x + 30 && this.y >= et.y && this.y <= et.y + 20) {
							return true;
						}
					}
				}
			}
			break;
		case 1:
			// 坦克向右
			// 取出所有的敌人坦克
			for (int i = 0; i < ets.size(); i++) {
				// 取出第一个坦克
				EnemyTank et = ets.get(i);
				// 如果不是自己
				if (et != this) {
					// 如果敌人的方向是向下或者向上
					if (et.direct == 0 || et.direct == 2) {
						// 上点
						if (this.x + 30 >= et.x && this.x + 30 <= et.x + 20 && this.y >= et.y && this.y <= et.y + 30) {
							return true;
						}
						// 下点
						if (this.x + 30 >= et.x && this.x + 30 <= et.x + 20 && this.y + 20 >= et.y
								&& this.y + 20 <= et.y + 30) {
							return true;
						}
					}
					if (et.direct == 3 || et.direct == 1) {
						if (this.x + 30 >= et.x && this.x + 30 <= et.x + 30 && this.y >= et.y && this.y <= et.y + 20) {
							return true;
						}
						if (this.x + 30 >= et.x && this.x + 30 <= et.x + 30 && this.y + 20 >= et.y
								&& this.y + 20 <= et.y + 20) {
							return true;
						}
					}
				}
			}
			break;
		case 2:
			// 坦克向下
			// 取出所有的敌人坦克
			for (int i = 0; i < ets.size(); i++) {
				// 取出第一个坦克
				EnemyTank et = ets.get(i);
				// 如果不是自己
				if (et != this) {
					// 如果敌人的方向是向下或者向上
					if (et.direct == 0 || et.direct == 2) {
						// 我的左点
						if (this.x >= et.x && this.x <= et.x + 20 && this.y + 30 >= et.y && this.y + 30 <= et.y + 30) {
							return true;
						}
						// 我的右点
						if (this.x + 20 >= et.x && this.x + 20 <= et.x + 20 && this.y + 30 >= et.y
								&& this.y + 30 <= et.y + 30) {
							return true;
						}
					}
					if (et.direct == 3 || et.direct == 1) {
						if (this.x >= et.x && this.x <= et.x + 30 && this.y + 30 >= et.y && this.y + 30 <= et.y + 20) {
							return true;
						}

						if (this.x + 20 >= et.x && this.x + 20 <= et.x + 30 && this.y + 30 >= et.y
								&& this.y + 30 <= et.y + 20) {
							return true;
						}
					}
				}
			}
			break;
		case 3:
			// 向左
			// 取出所有的敌人坦克
			for (int i = 0; i < ets.size(); i++) {
				// 取出第一个坦克
				EnemyTank et = ets.get(i);
				// 如果不是自己
				if (et != this) {
					// 如果敌人的方向是向下或者向上
					if (et.direct == 0 || et.direct == 2) {
						// 我的上一点
						if (this.x >= et.x && this.x <= et.x + 20 && this.y >= et.y && this.y <= et.y + 30) {
							return true;
						}
						// 下一点
						if (this.x >= et.x && this.x <= et.x + 20 && this.y + 20 >= et.y && this.y + 20 <= et.y + 30) {
							return true;
						}
					}
					if (et.direct == 3 || et.direct == 1) {
						// 上一点
						if (this.x >= et.x && this.x <= et.x + 30 && this.y >= et.y && this.y <= et.y + 20) {
							return true;
						}
						if (this.x >= et.x && this.x <= et.x + 30 && this.y + 20 >= et.y && this.y + 20 <= et.y + 20) {
							return true;
						}
					}
				}
			}
			break;
		}

		return b;
	}

	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			switch (this.direct) {
			case 0:
				// 说明坦克正在向上
				for (int i = 0; i < 30; i++) {
					if (y > 0 && !this.isTouchOtherEnemy()) {
						if (y - speed < 0)
							continue;
						// System.out.println(bugs.size());
						for (int ii = 0; ii < bugs.size(); ii++) {
							bug b = bugs.get(ii);
							if ((x - b.x) < 20 && (x - b.x) > -20 && (y == b.y + 30)) {// (x>=b.x) && (x<b.x+30)
								// System.out.println("bbb");
								y += speed;
								break;
							}
						}
						y -= speed;
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
					}
				}
				break;
			case 1:
				// 向右
				for (int i = 0; i < 30; i++) {
					// 保证坦克不出边界
					if (x < 400 && !this.isTouchOtherEnemy()) {
						if (x + speed > 370)
							continue;
						for (int ii = 0; ii < bugs.size(); ii++) {
							bug b = bugs.get(ii);
							if ((y - b.y) < 30 && (y - b.y) > -20 && (x + 30 == b.x)) {// (x>=b.x) && (x<b.x+30)
								// System.out.println("bbb");
								x -= speed;
								break;
							}
						}
						x += speed;
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
					}
				}
				break;
			case 2:
				// 向下
				for (int i = 0; i < 30; i++) {
					if (y < 300 && !this.isTouchOtherEnemy()) {
						if (y + speed > 270)
							continue;
						for (int ii = 0; ii < bugs.size(); ii++) {
							bug b = bugs.get(ii);
							if ((x - b.x) < 20 && (x - b.x) > -20 && (y == b.y - 30)) {// (x>=b.x) && (x<b.x+30)
								// System.out.println("bbb");
								y -= speed;
								break;
							}
						}
						y += speed;
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
					}
				}
				break;
			case 3:
				// 向左
				for (int i = 0; i < 30; i++) {
					if (x > 0 && !this.isTouchOtherEnemy()) {
						if (x - speed < 0)
							continue;
						for (int ii = 0; ii < bugs.size(); ii++) {
							bug b = bugs.get(ii);
							if ((y - b.y) < 30 && (y - b.y) > -20 && (x - 30 == b.x)) {// (x>=b.x) && (x<b.x+30)
								// System.out.println("bbb");
								x += speed;
								break;
							}
						}
						x -= speed;
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
					}
				}
				break;

			}

			this.times++;

			if (times % 2 == 0) {
				if (isLive) {
					if (ss.size() < 5) {
						// System.out.println("et.ss.size()<5="+et.ss.size());
						Shot s = null;
						// 没有子弹
						// 添加
						switch (direct) {
						case 0:
							// 创建一颗子弹
							s = new Shot(x + 10, y, 0);
							// 把子弹加入向量
							ss.add(s);
							break;
						case 1:
							s = new Shot(x + 30, y + 10, 1);
							ss.add(s);
							break;
						case 2:
							s = new Shot(x + 10, y + 30, 2);
							ss.add(s);
							break;
						case 3:
							s = new Shot(x, y + 10, 3);
							ss.add(s);
							break;
						}

						// 启动子弹
						Thread t = new Thread(s);
						t.start();
					}
				}
			}
			// 让坦克随机产生一个新的方向
			this.direct = (int) (Math.random() * 4);

			// 判断敌人坦克是否死亡
			if (this.isLive == false) {
				// 让坦克死亡后，退出线程.
				break;
			}
		}
	}
}

//我的坦克
class Hero extends Tank {
	Vector<bug> bugs = new Vector<bug>();

	public void setbugs(bug g1) {
		this.bugs.add(g1);
	}

	public void newbugs(Vector<bug> gg) {
		this.bugs = gg;
	}

	int speed = 3;

//	public void setbugs(bug g1,bug g2) {
//		this.bugs.add(g1);
//		this.bugs.add(g2);  //多组地形 就添加多次
//	}
	public boolean setDirect(int direct) {
		for (int i = 0; i < bugs.size(); i++) {
			bug b = bugs.get(i);
			if (direct == 1) {// xiang you
				if (x + 30 > b.x && y >= b.y - 20 && y <= b.y + 30)
					return false;
			}
//			if(direct==0) {//xiang shang 没问题
//				if(x+30>b.x && x<b.x+20 && y<=b.y+30)
//					return false;
//			}

			if (direct == 2) {// xiang you
				if (x + 20 > b.x && x < b.x + 20 && y > b.y - 30)
					return false;
			}
			if (direct == 3) {// xiang you
				if (y < b.y + 30 && y > b.y - 30 && x + 30 > b.x)
					return false;
			}

		}
		this.direct = direct;
		return true;
	}

	// 子弹
	// Shot s=null;
	int mylife = 4;
	Vector<Shot> ss = new Vector<Shot>();
	Shot s = null;

	public Hero(int x, int y) {
		super(x, y);
	}

	public int getmylife() {
		return this.mylife;
	}

	// 开火
	public void ducelife() {
		this.mylife -= 1;
		Recorder.currntlife -= 1;
	}

	public void shotEnemy() {
		switch (this.direct) {
		case 0:
			// 创建一颗子弹
			s = new Shot(x + 10, y, 0);
			// 把子弹加入向量
			ss.add(s);
			break;
		case 1:
			s = new Shot(x + 30, y + 10, 1);
			ss.add(s);
			break;
		case 2:
			s = new Shot(x + 10, y + 30, 2);
			ss.add(s);
			break;
		case 3:
			s = new Shot(x, y + 10, 3);
			ss.add(s);
			break;

		}
		// 启动子弹线程
		Thread t = new Thread(s);
		t.start();
	}

	// 坦克向上移动
	public void moveUp() // 多组地形 取出多次 ，外加类型判断
	{
		if (y - speed < 0)
			return;
		// System.out.println(bugs.size());
		for (int i = 0; i < bugs.size(); i++) {
			bug b = bugs.get(i);
			int yy = y - speed;
			if ((x - b.x) < 20 && (x - b.x) > -20 && (yy < b.y + 30) && (yy > b.y)) {// (x>=b.x) && (x<b.x+30)
				// System.out.println("bbb");
				return;
			}
		}
		y -= speed;
	}

	// 坦克向右移动
	public void moveRight() {
		if (x + speed > 370)
			return;
		for (int i = 0; i < bugs.size(); i++) {
			bug b = bugs.get(i);
			if ((y - b.y) < 30 && (y - b.y) > -20 && (x + 30 == b.x)) {// (x>=b.x) && (x<b.x+30)
				// System.out.println("bbb");
				return;
			}
		}
		x += speed;
	}

	// 坦克向下移动
	public void moveDown() {
		if (y + speed > 270)
			return;
		for (int i = 0; i < bugs.size(); i++) {
			bug b = bugs.get(i);
			if ((x - b.x) < 20 && (x - b.x) > -20 && (y == b.y - 30)) {// (x>=b.x) && (x<b.x+30)
				// System.out.println("bbb");
				return;
			}
		}
		y += speed;
	}

	// 向左
	public void moveLeft() {
		if (x - speed < 0)
			return;
		for (int i = 0; i < bugs.size(); i++) {
			bug b = bugs.get(i);
			if ((y - b.y) < 30 && (y - b.y) > -20 && (x - 30 == b.x)) {// (x>=b.x) && (x<b.x+30)
				// System.out.println("bbb");
				return;
			}
		}
		x -= speed;
	}
}