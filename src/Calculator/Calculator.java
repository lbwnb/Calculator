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

	String str1 = "0"; // ������1
	String str2 = "0"; // ������2
	String signal = "+"; // �����
	String result = ""; // ������

	// ����K1��k2Ϊ״̬����

	// ����1����ѡ�����뷽�򣬽�Ҫд��str1��str2
	int k1 = 1;
	// ����2�����¼���ż��Ĵ��������k2>1 ˵�����е��Ƕ��������
	int k2 = 1;
	// ����3���ڱ�ʶstr1�Ƿ���Ա���0������1ʱ���ԣ�������1ʱ���ܱ���0
	int k3 = 1;
	// ����4���ڱ�ʶstr2�Ƿ���Ա���0
	int k4 = 1;
	// ����5���ڿ���С����ɷ�¼�ã�����1ʱ���ԣ���Ϊ1ʱ�������С���㱻����
	int k5 = 1;
	// store���������ͼĴ��������ڼ�¼�Ƿ��������·��ż�
	JButton store;

	Vector vt = new Vector(20, 10);
	// ��������UI������󲢳�ʼ��
	JFrame frame = new JFrame("Calculator");
	JTextField result_TextField = new JTextField(result, 20); // 20���ı�����
	JButton clear_Button = new JButton("Clear"); // �����ť
	// ���ּ�0��9
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

	// �������ť
	JButton button_Dian = new JButton(".");
	JButton button_jia = new JButton("+");
	JButton button_jian = new JButton("-");
	JButton button_cheng = new JButton("*");
	JButton button_chu = new JButton("/");

	// ���㰴ť
	JButton button_dy = new JButton("=");

	public Calculator() {
		// Ϊ��ť���õ�Ч��������ͨ����Ӧ�ļ��̰���������
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

		// �����ı���Ϊ�Ҷ��룬ʹ����ͽ����������ʾ
		result_TextField.setHorizontalAlignment(JTextField.RIGHT);

		// ����һ��Jpanel���󲢳�ʼ��
		JPanel pan = new JPanel();
		// ���ø������Ĳ���Ϊ�������У��߾�Ϊ6����
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

		// ����pan����ı߾�
		pan.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		JPanel pan2 = new JPanel();
		pan2.setLayout(new BorderLayout());
		pan2.add(result_TextField, BorderLayout.WEST);
		pan2.add(clear_Button, BorderLayout.EAST);

		// ���������ڳ�������Ļ��
		frame.setLocation(300, 200);
		// ���ô��岻�ܵ���С
		frame.setResizable(false);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(pan2, BorderLayout.NORTH);
		frame.getContentPane().add(pan, BorderLayout.CENTER);

		frame.pack();
		frame.setVisible(true);

		// �¼��������

		// ���ּ�
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
						// ��ԭ����5״̬
						k5 = 1;
					}
					str1 = str1 + ss;
					k3 = k3 + 1;

					// ��ʾ���
					result_TextField.setText(str1);

				} else if (k1 == 2) {
					if (k4 == 1) {
						str2 = "";
						// ��ԭ����k5״̬
						k5 = 1;
					}
					str2 = str2 + ss;
					k4 = k4 + 1;
					result_TextField.setText(str2);
				}
			}

		}

		// �����������ŵĴ���
		class Listener_signal implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String ss2 = ((JButton) e.getSource()).getText();
				store = (JButton) e.getSource();
				vt.add(store);

				if (k2 == 1) {
					// ����k1Ϊ1ʱ����1д����ֵ��Ϊ2ʱ����2д����ֵ��
					k1 = 2;
					k5 = 1;
					signal = ss2;
					k2 = k2 + 1; // �����ż��Ĵ���
				} else {
					int a = vt.size();
					JButton c = (JButton) vt.get(a - 2);

					if (!(c.getText().equals("+")) && !(c.getText().equals("*")) && !(c.getText().equals("*"))
							&& !(c.getText().equals("/"))) {
						cal();
						str1 = result;
						// ����k1Ϊ1ʱ������1дֵ��Ϊ2ʱ����2д
						k1 = 2;
						k5 = 1;
						k4 = 1;
						signal = ss2;
					}
					k2 = k2 + 1;
				}
			}

		}

		// ��������߼�(Clear)

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

		// ���ڼ����߼�
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
				str1 = result; // ��������

			}

		}

		// С����Ĵ���
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
						// ��ʾ���
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
		// ע������������������¼���Ӧ�߼�������UI�����
		Listener_dy jt_dy = new Listener_dy();

		// �������ּ�
		Listener jt = new Listener();
		// �������ż�
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

		// ���ڹر��¼�����Ӧ����
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	private void cal() {
		// TODO Auto-generated method stub
		// ������1
		double a2;
		// ������2
		double b2;
		// �����
		String c = signal;
		double result2 = 0;
		if (c.equals("")) {
			result_TextField.setText("�����������");
		} else {
			// �ֶ�����С���������

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
