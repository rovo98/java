package com.rovo98.summerTraining;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
/**
 * 小学生出题程序：
 * 		要求1：加减乘除自由选择
 * 		要求2：难度选择：
 * 			加减法：
 * 				1）范围：10以内、20以内、100以内、1000以内等
 * 				2）一位数加一位数、两位数加一位数、两位数加两位数等
 *			乘法：
 * 				3）表内乘法、两位乘以一位，两位乘以两位
 * 			除法：
 * 				4）表内除法、两位除以一位、多位数除以两位，除法结果为非负数且为整数
 * 			题量：
 * 				5）20道、50道、100道
 * 			保存格式要求：
 * 				输出为pdf
 * 程序分析：
 * 		1.使用Java的图形界面和事件处理实现,提供给用户选择题目的类型和难度
 * 		2.保存格式输出为pdf需要引入第三方库，暂且输出为txt文档
 * 
 * 
 * @author rovo98
 *
 */
@SuppressWarnings("serial")
/**
 * 主要界面显示类
 * @author rovo98
 * 
 */
class WindowShow extends JFrame{
	private JFrame frm_1 = null ; 
	private JFrame frm_2 = null ;   
	private JButton[] btn = null ;
	private JCheckBox[] jck = null ;
	private JRadioButton[] jrb = null ;
	private ButtonGroup[]  bg = null ;
	private JLabel[] jLab = null ;
	
	/**
	 * 事件适配器监听类
	 * @author rovo98
	 * 
	 */
	class myActionListener implements ActionListener {
		boolean[] type = new boolean[4] ;
		int[] choice = {-1,-1,-1,-1,-1} ;
		boolean sign = true ;
		/**
		 * 事件处理
		 */
		public void actionPerformed(ActionEvent ae) {
			JButton bt = (JButton)ae.getSource() ;
			if ( btn[0] == bt ) {
				// 转到第二个界面，并且记录下题型的选择信息
				for (int j=0; j<type.length; j++) {
					type[j] = false ;
				}
				for (int i=0; i<4; i++) {
					if (jck[i].isSelected()) {
						type[i] = true ;
					}
				}
				showSecWin(type) ; 
			}
			else if ( btn[1] == bt ) {
				// 完成出题操作
				if (type[0] || type[1]) {  // choice[0]对应加减法的范围，取值：0~3
					for (int i=0; i<4; i++) {
						if (jrb[i].isSelected()) {
							choice[0] = i ;
						}
					}
					if (type[0]) {
						for (int i=4; i<7; i++) {
							if (jrb[i].isSelected()) {  // choice[1]对应加法的难度，取值：0~2
								choice[1] = i - 4 ;
							}
						}
					}
				}
				if (type[2]) {
					for (int i=7; i<10; i++) {   // choice[2]对应乘法的难度选择，取值：0~2
						if (jrb[i].isSelected()) {
							choice[2] = i - 7 ;
						}
					}
				}
				if (type[3]) {
					for (int i=10; i<13; i++) { // choice[3]对应除法的难度选择，取值：0~2
						if (jrb[i].isSelected()) {
							choice[3] = i - 10 ;
						}
					}
				}
				for (int i=13; i<16; i++) {    // choice[4]对应题目数量的选择，取值：0~2
					if (jrb[i].isSelected()) {
						choice[4] = i -13 ;
					}
				}
				// 判断用户是否选择完毕
				if (choice[4] >=0) {
						if (type[0]) {
							if (choice[0]<0 || choice[1]<0) {
								sign = false ;
						}
						if (type[1]) {
							if (choice[0]<0) {
								sign = false ;
							}
						}
						if (type[2]) {
							if (choice[2]<0) {
								sign = false ;
							}
						}
						if (type[3]) {
							if (choice[3]<0) {
								sign = false ;
							}
						}
					}
					if (sign) {
						// 关闭两个窗口，避免重复操作
						frm_1.setVisible(false) ;
						frm_2.setVisible(false) ;
						// 生成题目
						QuestionsCreate qc = new QuestionsCreate() ;
						qc.createQuestions(type, choice) ;
					}
				}
				else {
					System.out.println("请完成各个选项的选择!") ;
				}
			}
		}
	}
	/**
	 * 显示第一个题目类型选择窗口
	 */
	public void showWindows() {
		frm_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		frm_1.setLayout(null) ;
		frm_1.setBounds(100,200,350,200) ;
		jLab[0].setBounds(10,10,150,30) ;
		jck[0].setBounds(20,40,80,20) ;
		jck[1].setBounds(20,60,80,20) ;
		jck[2].setBounds(20,80,80,20) ;
		jck[3].setBounds(20,100,80,20) ;
		btn[0].setBounds(150,130,60,20) ;
		frm_1.add(jLab[0]) ;
		frm_1.add(jck[0]) ;frm_1.add(jck[1]) ;
		frm_1.add(jck[2]) ;frm_1.add(jck[3]) ;
		frm_1.add(btn[0]) ;
		
		frm_1.setVisible(true) ;
		frm_1.setResizable(false) ;
	}
	/**
	 * 根据已经选好的题目类型选择对应的难度
	 * @param type
	 */
	public void showSecWin(boolean[] type) {

		frm_2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		frm_2.setLayout(null) ;
		frm_2.setBounds(100,400,350,500) ;
		
		// 加减法难度选择
		if (type[0] || type[1]) {
			jLab[1].setBounds(20,10,150,20) ;
			jrb[0].setBounds(20,30,100,20) ;
			jrb[1].setBounds(20,50,100,20) ;
			jrb[2].setBounds(20,70,100,20) ;
			jrb[3].setBounds(20,90,100,20) ;
			if (type[0]) {
				jrb[4].setBounds(20,110,140,20) ;
				jrb[5].setBounds(20,130,140,20) ;
				jrb[6].setBounds(20,150,140,20) ;
				frm_2.add(jrb[4]);frm_2.add(jrb[5]);frm_2.add(jrb[6]);
			}
			else {
				frm_2.remove(jrb[4]);frm_2.remove(jrb[5]);frm_2.remove(jrb[6]);
			}
			frm_2.add(jLab[1]) ;
			frm_2.add(jrb[0]);frm_2.add(jrb[1]);frm_2.add(jrb[2]);frm_2.add(jrb[3]);
		}
		else {
			frm_2.remove(jLab[1]);
			frm_2.remove(jrb[0]);frm_2.remove(jrb[1]);frm_2.remove(jrb[2]);frm_2.remove(jrb[3]);
			frm_2.remove(jrb[4]);frm_2.remove(jrb[5]);frm_2.remove(jrb[6]);
		}
		// 乘法难度选择
		if (type[2]) {
			jLab[2].setBounds(20,170,150,20) ;
			jrb[7].setBounds(20,190,120,20) ;
			jrb[8].setBounds(20,210,120,20) ;
			jrb[9].setBounds(20,230,120,20) ;
			frm_2.add(jLab[2]);
			frm_2.add(jrb[7]);frm_2.add(jrb[8]);frm_2.add(jrb[9]);
		}
		else {
			frm_2.remove(jLab[2]);
			frm_2.remove(jrb[7]);frm_2.remove(jrb[8]);frm_2.remove(jrb[9]);
		}
		// 除法难度选择
		if (type[3]) {
			jLab[3].setBounds(20,250,150,20) ;
			jrb[10].setBounds(20,270,120,20) ;
			jrb[11].setBounds(20,290,120,20) ;
			jrb[12].setBounds(20,310,120,20) ;
			frm_2.add(jLab[3]);
			frm_2.add(jrb[10]);frm_2.add(jrb[11]);frm_2.add(jrb[12]);
		}
		else {
			frm_2.remove(jLab[3]);
			frm_2.remove(jrb[10]);frm_2.remove(jrb[11]);frm_2.remove(jrb[12]);
		}
		// 题量选择
		if (type[0] || type[1] || type[2] || type[3]) {
			jLab[4].setBounds(20,330,150,20) ;
			jrb[13].setBounds(20,350,120,20) ;
			jrb[14].setBounds(20,370,120,20) ;
			jrb[15].setBounds(20,390,120,20) ;
			btn[1].setBounds(130,410,60,20) ;
			frm_2.add(jLab[4]) ;
			frm_2.add(jrb[13]);frm_2.add(jrb[14]);frm_2.add(jrb[15]);
			frm_2.add(btn[1]) ;
		}
		else {
			frm_2.remove(jLab[4]);
			frm_2.remove(jrb[13]);frm_2.remove(jrb[14]);frm_2.remove(jrb[15]);
			frm_2.remove(btn[1]);
		}

		frm_2.setVisible(true) ;
		frm_2.setResizable(false) ;
	}
	/**
	 * 初始化各组件对象
	 */
	public void createObjects() {  // 创建对象
		frm_1 = new JFrame("小学生出题程序") ;
		frm_2 = new JFrame("题目具体要求") ;
		btn = new JButton[3] ;
		btn[0] = new JButton("确定") ;
		btn[1] = new JButton("确定") ;
		jck = new JCheckBox[4] ;
		jck[0] = new JCheckBox("加法") ;
		jck[1] = new JCheckBox("减法") ;
		jck[2] = new JCheckBox("乘法") ;
		jck[3] = new JCheckBox("除法") ;
		jLab = new JLabel[5] ;
		jLab[0] = new JLabel("题目类型选择:") ;
		jLab[1] = new JLabel("加减法难度选择:") ;
		jLab[2] = new JLabel("乘法难度选择:") ;
		jLab[3] = new JLabel("除法难度选择:") ;
		jLab[4] = new JLabel("题目数量选择:") ;
		jrb = new JRadioButton[16] ;
		bg = new ButtonGroup[5] ;
		// 加减法难度选择
		bg[0] = new ButtonGroup() ;
		jrb[0] = new JRadioButton("10以内") ;
		jrb[1] = new JRadioButton("20以内") ;
		jrb[2] = new JRadioButton("100以内") ;
		jrb[3] = new JRadioButton("1000以内") ;
		bg[0].add(jrb[0]);bg[0].add(jrb[1]);bg[0].add(jrb[2]);bg[0].add(jrb[3]);
		bg[1] = new ButtonGroup() ;
		jrb[4] = new JRadioButton("1位数加1位数") ;
		jrb[5] = new JRadioButton("2位数加1位数") ;
		jrb[6] = new JRadioButton("2位数加2位数") ;
		bg[1].add(jrb[4]);bg[1].add(jrb[5]);bg[1].add(jrb[6]);bg[1].add(jrb[7]);
		bg[2] = new ButtonGroup() ;
		jrb[7] = new JRadioButton("表内乘法") ;
		jrb[8] = new JRadioButton("2位数乘1位数") ;
		jrb[9] = new JRadioButton("2位数乘2位数") ;
		bg[2].add(jrb[7]);bg[2].add(jrb[8]);bg[2].add(jrb[9]);
		bg[3] = new ButtonGroup() ;
		jrb[10] = new JRadioButton("表内除法") ;
		jrb[11] = new JRadioButton("2位数除1位数") ;
		jrb[12] = new JRadioButton("多位数除2位数") ;
		bg[3].add(jrb[10]);bg[3].add(jrb[11]);bg[3].add(jrb[12]);
		bg[4] = new ButtonGroup() ;
		jrb[13] = new JRadioButton("20题") ;
		jrb[14] = new JRadioButton("50题") ;
		jrb[15] = new JRadioButton("100题") ;
		bg[4].add(jrb[13]);bg[4].add(jrb[14]);bg[4].add(jrb[15]);
	}
	/**
	 * 添加事件监听
	 */
	public void addActionListeners() { 
		myActionListener actionListener = new myActionListener() ;
		btn[0].addActionListener(actionListener) ;
		btn[1].addActionListener(actionListener) ;
	}
}
/**
 * 文件写入工具
 * @author rovo98
 * 
 */
class SaveData {
	private FileWriter fw = null ;
	private BufferedWriter bw = null ;
	private File f = null ;
	String filename = "E:"+File.seperator+"小学生题目.txt" ;
	/**
	 * 打开要保存到的文件
	 */
	public void openFile() {
		try{
			f = new File(filename) ;
			if (f.exists()) { // 如果文件已经存在，重新创建
				f.delete() ;
				f = new File(filename) ;
			}
			fw = new FileWriter(f,true) ;
			bw = new BufferedWriter(fw) ;
		}
		catch (IOException e) {
			e.printStackTrace() ;
		}
	}
	/**
	 * 获取保存到的文件的文件路劲
	 * @return
	 */
	public String getPath() {
		return f.getPath() ;
	}
	/**
	 * 关闭文件
	 */
	public void closeFile() {
		try {
			bw.close() ;
			fw.close();
		}
		catch (IOException e) {
			e.printStackTrace() ;
		}
	}
	/**
	 * 向文件中写入一行文本数据
	 * @param data
	 * @return
	 */
	public  boolean Save(String data) {
		try {
			bw.write(data) ;
			bw.newLine() ;
		}
		catch (IOException e) {
			e.printStackTrace() ;
			return false ;
		}
		return true ;
	}
}
/**
 * 小学生问题生成类
 * @author rovo98
 * 
 */
class QuestionsCreate {
	private static SaveData sd = new SaveData() ;
	public QuestionsCreate() {
	}
	/**
	 * 根据出题类型和难度出题
	 * @param type      出题类型标记数组
	 * @param choice    对应类型的难度选项
	 */
	public void createQuestions(boolean[] type, int[] choice) {
		
		int[] range = {10,20,100,1000} ;
		int[] question_table = {20,50,100} ;
		int num1 = 0,num2 = 0,num3 = 0,num4 = 0 ;
		int count = 0 ;
		int num_of_questions = question_table[choice[4]] ;
		// 先确定各种类型题的出题的数量，即choice[4]的值对应数量
		for (int i=0; i < type.length; i++) {
			if (type[i]) {
				count++ ;
			}
		}
		double r = (int)((10 * 1.0 / count )*100) * 1.0 / 1000 ;
		System.out.println("正在生成题目......") ;
		sd.openFile() ;     // 打开文件
		sd.Save("\t\t小学生题目测试——题目数量:"+num_of_questions) ;
		if (type[0]) {
			num1 = num_of_questions - (int)(r*num_of_questions) * (count-1) ;
			PlusQuestions(range[choice[0]], num1, choice[1]) ;
		}
		if (type[1]) {
			num2 = (int)(r * num_of_questions) ;
			MunusQuestions(range[choice[0]],num2) ;
		}
		if (type[2]) {
			num3 = (int)(r * num_of_questions) ;
			MultiplyQuestions(num3,choice[2]) ;
		}
		if (type[3]) {
			num4 = (int)(r * num_of_questions) ;
			DivisionQuestions(num4, choice[3]) ;
		}
		sd.closeFile() ;   // 关闭文件
		System.out.println("题目生成完成，文件路径为:"+sd.getPath()) ;
	}
	/**
	 * 加法类型题目的生成并保存到文件中
	 * @param range      题目范围
	 * @param num        加法题目数量
	 * @param type       难度类型
	 */
	public static void PlusQuestions(int range,int num, int type) { //加法出题具体实现
		
		String title = "加法题目：" + num + "道" ;
		String questions = "" ;
		sd.Save(title) ;
		if (type == 0) { // 1位数加1位数
			for (int i=0; i<num; i++) {
			 questions += "(" +(i+1)+ ")"+ (int)(Math.random()*9+1)+ " + " + (int)(Math.random()*9+1)+" = ?" ;
			 // 写入文件中
				if ((i+1) % 3 == 0) {
					sd.Save(questions) ;
					questions = "" ;
				}
				else {
					questions += "\t" ;
				}
			}
			sd.Save(questions) ;
		}
		if (type == 1) { // 2位数加1位数
			if (range < 100 ) {
				for (int i=0; i<num; i++) {
					questions += "(" +(i+1)+ ")" + (int)(Math.random()*10+10)+ " + " + (int)(Math.random()*9+1)+" = ?" ;
					// 写入文件中
					if ((i+1) % 3 == 0) {
						sd.Save(questions) ;
						questions = "" ;
					}
					else {
						questions += "\t" ;
					}
				}
				sd.Save(questions) ;
			}
			else {
				for (int i=0; i<num; i++) {
					questions += "("+(i+1)+")"+(int)(Math.random()*90+10)+" + "+(int)(Math.random()*9+1)+" = ?" ;
					// 写入文件
					if ((i+1) % 3 == 0) {
						sd.Save(questions) ;
						questions = "" ;
					}
					else {
						questions += "\t" ;
					}
				}
				sd.Save(questions) ;
			}
		}
		if (type == 2) {  // 2位数加2位数
 			if (range < 100) {
 				for (int i=0; i<num; i++) {
 					questions += "("+(i+1)+")"+(int)(Math.random()*10+10)+" + "+(int)(Math.random()*10+10)+" = ?" ;
 					// 写入文件
 					if ((i+1) % 3 == 0) {
 						sd.Save(questions) ;
 						questions = "" ;
 					}
 					else {
 						questions += "\t" ;
 					}
 				}
 				sd.Save(questions) ;
 			}
 			else {
 				for (int i=0; i<num; i++) {
 					questions += "("+(i+1)+")"+(int)(Math.random()*90+10)+" + "+(int)(Math.random()*90+10)+" = ?" ;
 					// 写入文件
 					if ((i+1) % 3 == 0) {
 						sd.Save(questions) ;
 						questions = "" ;
 					}
 					else {
 						questions += "\t" ;
 					}
 				}
 				sd.Save(questions) ;
 			}
		}
	}
	/**
	 * 减法题目生成并保存到文件中
	 * @param range     范围
	 * @param num       题目数量
	 */
	public static void MunusQuestions(int range, int num) {  // 减法出题具体实现
		
		String title = "减法题目：" + num + "道";
		String questions = "" ;
		sd.Save(title) ;
		for (int i=0; i<num; i++) {
			questions += "("+(i+1)+")"+(int)(Math.random()*(range-1)+1) + " - "+(int)(Math.random()*(range-1)+1) + " = ?" ;
			// 写入文件中
			if ((i+1) % 3 == 0) {
				sd.Save(questions) ;
				questions = "" ;
			}
			else {
				questions += "\t" ;
			}
		}
		sd.Save(questions) ;
	}
	/**
	 * 乘法题目的生成并保存到文件中
	 * @param num     题目数量
	 * @param type    难度
	 */
	public static void MultiplyQuestions(int num, int type) {  // 乘法出题具体实现
		
		String title = "乘法题目：" + num + "道";
		String questions = "" ;
		sd.Save(title) ;
		if (type == 0) { // 表内乘法
			for (int i=0; i<num; i++) {
				questions += "("+(i+1)+")"+(int)(Math.random()*9+1)+" X "+(int)(Math.random()*9+1)+" = ?" ;
				// 写入文件中
				if ((i+1) % 3 == 0) {
					sd.Save(questions) ;
					questions = "" ;
				}
				else {
					questions += "\t" ;
				}
			}
			sd.Save(questions) ;
		}
		else if (type == 1) { // 2位数乘1位数
			for (int i=0; i<num; i++) {
				questions += "("+(i+1)+")"+(int)(Math.random()*90+10)+" X "+(int)(Math.random()*9+1)+" = ?" ;
				// 写入文件中
				if ((i+1) % 3 == 0) {
					sd.Save(questions) ;
					questions = "" ;
				}
				else {
					questions += "\t" ;
				}
			}
			sd.Save(questions) ;
		}
		else if (type == 2) { // 2位数乘2位数
			for (int i=0; i<num; i++) {
				questions += "("+(i+1)+")"+(int)(Math.random()*90+10)+" X "+(int)(Math.random()*90+10)+" = ?" ;
				// 写入文件中
				if ((i+1) % 3 == 0) {
					sd.Save(questions) ;
					questions = "" ;
				}
				else {
					questions += "\t" ;
				}
			}
			sd.Save(questions) ;
		}
	}
	/**
	 * 除法题目的生成并保存到文件中
	 * @param num        题目的数量
	 * @param type       对应的难度
	 */
	public static void DivisionQuestions(int num, int type) {  // 除法出题具体实现
		
		String title = "除法题目：" + num + "道" ;
		String questions = "" ;
		sd.Save(title) ;
		if (type == 0) { // 表内除法
			for (int i=0; i<num; i++) {
				while (true) {
					int num_1 = (int)(Math.random()*9+1) ;
					int num_2 = (int)(Math.random()*9+1) ;
					if ( num_1 > num_2 && num_1 % num_2 == 0 ) {
						questions += "("+(i+1)+")"+num_1+" / "+num_2+" = ?" ;
						// 写入文件中
						if ((i+1) % 3 == 0) {
							sd.Save(questions) ;
							questions = "" ;
						}
						else{
							questions += "\t" ;
						}
						break ;
					}
				}
			}
			sd.Save(questions) ;
		}
		else if (type == 1) { // 2位数除以1位数
			for (int i=0; i<num; i++) {
				while (true) {
					int num_1 = (int)(Math.random()*90+10) ;
					int num_2 = (int)(Math.random()*8+2) ;
					if (num_1 % num_2 ==  0) {
						questions += "("+(i+1)+")"+num_1+" / "+num_2+" = ?" ;
						// 写入文件中
						if ((i+1) % 3 == 0) {
							sd.Save(questions) ;
							questions = "" ;
						}
						else {
							questions += "\t" ;
						}
						break ;
					}
				}
			}
			sd.Save(questions) ;
		}
		else if (type == 2) { // 多位数除以2位数
			for (int i=0; i<num; i++) {
				while (true) {
					int num_1 = (int)(Math.random()*900+100) ;
					int num_2 = (int)(Math.random()*10+10) ;
					if (num_1 % num_2 == 0) {
						questions += "("+(i+1)+")"+num_1+" / "+num_2+" = ?" ;
						// 写入文件中
						if ((i+1) % 3 == 0) {
							sd.Save(questions) ;
							questions = "" ;
						}
						else {
							questions += "\t" ;
						}
						break ;
					}
				}
			}
			sd.Save(questions) ;
		}
	}
}
public class StuAssistant {
	public static void main(String[] args) {
		WindowShow ws = new WindowShow() ;
		Date start = new Date() ;
		ws.createObjects() ;
		ws.addActionListeners() ; 
		ws.showWindows() ;
		Date end = new Date() ;
		System.out.println("总共用时:"+String.valueOf(end.getTime()-start.getTime())+"毫秒!") ;
	}
}
