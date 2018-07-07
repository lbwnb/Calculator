package Calculator;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.math.BigDecimal;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator {

	String str1 = "0"; // 操作数1
	String str2 = "0"; // 操作数2
	String signal = "+"; // 运算符
	String result = ""; // 运算结果

	// 以下K1至k2为状态开关

	// 开关1用于选择输入方向，将要写入str1或str2
	int k1 = 1;
	// 开关2用与记录符号键的次数，如果k2>1 说明进行的是多符号运算
	int k2 = 1;
	// 开关3用于标识str1是否可以被清0，等于1时可以，不等于1时不能被清0
	int k3 = 1;
	// 开关4用于标识str2是否可以被清0
	int k4 = 1;
	// 开关5用于控制小数点可否被录用，等于1时可以，不为1时，输入的小数点被丢掉
	int k5 = 1;
	// store的作用类型寄存器，用于记录是否连续按下符号键
	JButton store;

	Vector vt = new Vector(20, 10);
	// 声明各个UI组件对象并初始化
	JFrame frame = new JFrame("Calculator");
	JTextField result_TextField = new JTextField(result, 20); // 20列文本内容
	JButton clear_Button = new JButton("Clear"); // 清除按钮
	// 数字键0到9
	JButton button0 = new JButton("0");
	JButton button1 = new JButton("1");
	JButton button2 = new JButton("2");
	JButton button3 = new JButton("3");
	JButton button4 = new JButton("4");
	JButton button5 = new JButton("5");
	JButton button6 = new JButton("6");
	JButton button7 = new JButton("7");
	JButton button8 = new JButton("8");
	JButton button9 = new JButton("9");

	// 计算命令按钮
	JButton button_Dian = new JButton(".");
	JButton button_jia = new JButton("+");
	JButton button_jian = new JButton("-");
	JButton button_cheng = new JButton("*");
	JButton button_chu = new JButton("/");

	// 计算按钮
	JButton button_dy = new JButton("=");

	public Calculator() {
		// 为按钮设置等效键，即可通过对应的键盘按键来代替
		button0.setMnemonic(KeyEvent.VK_0);
		button1.setMnemonic(KeyEvent.VK_1);
		button2.setMnemonic(KeyEvent.VK_2);
		button3.setMnemonic(KeyEvent.VK_3);
		button4.setMnemonic(KeyEvent.VK_4);
		button5.setMnemonic(KeyEvent.VK_5);
		button6.setMnemonic(KeyEvent.VK_6);
		button7.setMnemonic(KeyEvent.VK_7);
		button8.setMnemonic(KeyEvent.VK_8);
		button9.setMnemonic(KeyEvent.VK_9);

		// 设置文本框为右对齐，使输入和结果都靠右显示
		result_TextField.setHorizontalAlignment(JTextField.RIGHT);

		// 创建一个Jpanel对象并初始化
		JPanel pan = new JPanel();
		// 设置该容器的布局为四行四列，边距为6像素
		pan.setLayout(new GridLayout(4, 4, 5, 5));

		pan.add(button7);
		pan.add(button8);
		pan.add(button9);
		pan.add(button_chu);
		pan.add(button4);
		pan.add(button5);
		pan.add(button6);
		pan.add(button_cheng);
		pan.add(button1);
		pan.add(button2);
		pan.add(button3);
		pan.add(button_jian);
		pan.add(button0);
		pan.add(button_Dian);
		pan.add(button_dy);
		pan.add(button_jia);

		// 设置pan对象的边距
		pan.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		JPanel pan2 = new JPanel();
		pan2.setLayout(new BorderLayout());
		pan2.add(result_TextField, BorderLayout.WEST);
		pan2.add(clear_Button, BorderLayout.EAST);

		// 设置主窗口出现在屏幕上
		frame.setLocation(300, 200);
		// 设置窗体不能调大小
		frame.setResizable(false);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(pan2, BorderLayout.NORTH);
		frame.getContentPane().add(pan, BorderLayout.CENTER);

		frame.pack();
		frame.setVisible(true);

		// 事件处理程序

		// 数字键
		class Listener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String ss = ((JButton) e.getSource()).getText();
				store = (JButton) e.getSource();
				vt.add(store);
				if (k1 == 1) {
					if (k3 == 1) {
						str1 = "";
						// 还原开关5状态
						k5 = 1;
					}
					str1 = str1 + ss;
					k3 = k3 + 1;

					// 显示结果
					result_TextField.setText(str1);

				} else if (k1 == 2) {
					if (k4 == 1) {
						str2 = "";
						// 还原开关k5状态
						k5 = 1;
					}
					str2 = str2 + ss;
					k4 = k4 + 1;
					result_TextField.setText(str2);
				}
			}

		}

		// 输入的运算符号的处理
		class Listener_signal implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String ss2 = ((JButton) e.getSource()).getText();
				store = (JButton) e.getSource();
				vt.add(store);

				if (k2 == 1) {
					// 开关k1为1时向数1写输入值，为2时向数2写输入值。
					k1 = 2;
					k5 = 1;
					signal = ss2;
					k2 = k2 + 1; // 按符号键的次数
				} else {
					int a = vt.size();
					JButton c = (JButton) vt.get(a - 2);

					if (!(c.getText().equals("+")) && !(c.getText().equals("*")) && !(c.getText().equals("*"))
							&& !(c.getText().equals("/"))) {
						cal();
						str1 = result;
						// 开关k1为1时，向数1写值，为2时向数2写
						k1 = 2;
						k5 = 1;
						k4 = 1;
						signal = ss2;
					}
					k2 = k2 + 1;
				}
			}

		}

		// 清楚键的逻辑(Clear)

		class Listener_clear implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				store = (JButton) e.getSource();
				vt.add(store);
				k5 = 1;
				k2 = 1;
				k1 = 1;
				k3 = 1;
				k4 = 1;
				str1 = "0";
				str2 = "0";
				signal = "";
				result = "";
				result_TextField.setText(result);
				vt.clear();
			}

		}

		// 等于键的逻辑
		class Listener_dy implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				store = (JButton) e.getSource();
				vt.add(store);
				cal();
				k1 = 1;
				k2 = 1;
				k3 = 1;
				k4 = 1;
				str1 = result; // 连续计算

			}

		}

		// 小数点的处理
		class Listener_xiaos implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				store = (JButton) e.getSource();
				vt.add(store);
				if (k5 == 1) {
					String ss2 = ((JButton) e.getSource()).getText();
					if (k1 == 1) {
						if (k3 == 1) {
							str1 = "";
							k5 = 1;
						}
						str1 = str1 + ss2;
						k3 = k3 + 1;
						// 显示结果
						result_TextField.setText(str1);

					} else if (k1 == 2) {
						if (k4 == 1) {
							str2 = "";
							k5 = 1;
						}
						str2 = str2 + ss2;
						k4 = k4 + 1;
						result_TextField.setText(str2);
					}
				}
				k5 = k5 + 1;
			}

		}
		// 注册各个监听器，即绑定事件响应逻辑到各个UI组件上
		Listener_dy jt_dy = new Listener_dy();

		// 监听数字键
		Listener jt = new Listener();
		// 监听符号键
		Listener_signal jt_signal = new Listener_signal();
		Listener_clear jt_c = new Listener_clear();
		Listener_xiaos jt_xs = new Listener_xiaos();

		button1.addActionListener(jt);
		button2.addActionListener(jt);
		button3.addActionListener(jt);
		button4.addActionListener(jt);
		button5.addActionListener(jt);
		button6.addActionListener(jt);
		button7.addActionListener(jt);
		button8.addActionListener(jt);
		button9.addActionListener(jt);

		button_jia.addActionListener(jt_signal);
		button_jian.addActionListener(jt_signal);
		button_cheng.addActionListener(jt_signal);
		button_chu.addActionListener(jt_signal);
		button_Dian.addActionListener(jt_xs);
		button_dy.addActionListener(jt_dy);
		clear_Button.addActionListener(jt_c);

		// 窗口关闭事件的响应程序
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	private void cal() {
		// TODO Auto-generated method stub
		// 操作数1
		double a2;
		// 操作数2
		double b2;
		// 运算符
		String c = signal;
		double result2 = 0;
		if (c.equals("")) {
			result_TextField.setText("请输入运算符");
		} else {
			// 手动处理小数点的问题

			if (str1.equals("."))
				str1 = "0.0";
			if (str2.equals("."))
				str2 = "0.0";
			a2 = Double.valueOf(str1).doubleValue();
			b2 = Double.valueOf(str2).doubleValue();

			if (c.equals("+")) {
				result2 = a2 + b2;
			}
			if(c.equals("-")) {
				result2 = a2-b2;
			}
			if(c.equals("*")) {
				BigDecimal m1 = new BigDecimal(Double.toString(a2));
				BigDecimal m2 = new BigDecimal(Double.toString(b2));
				result2 = m1.multiply(m2).doubleValue();
			}
			if (c.equals("/")) {
				if (b2 == 0) {
					result2 = 0;
				} else {
					result2 = a2 / b2;
				}
			}
			result = (new Double(result2).toString());
			result_TextField.setText(result);
		}
	}

	public static void main(String[] args) {
		Calculator cal = new Calculator();

	}

}
