import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


//实现接口ActionListener
public class Test implements ActionListener {
	public static String[] strs2=null;
	JFrame jf;
	JPanel jpanel;
	JButton jb1, jb2, jb3, jb4;
	JTextArea jta = null;
	JScrollPane jscrollPane;

	public Test() {

		jf = new JFrame("xml生成javabean工具");
		Container contentPane = jf.getContentPane();
		contentPane.setLayout(new BorderLayout());

		jta = new JTextArea(10, 15);
		jta.setTabSize(4);
		jta.setFont(new Font("标楷体", Font.BOLD, 16));
		jta.setLineWrap(true);// 激活自动换行功能
		jta.setWrapStyleWord(true);// 激活断行不断字功能
		jta.setBackground(Color.pink);

		jscrollPane = new JScrollPane(jta);
		jpanel = new JPanel();
		jpanel.setLayout(new GridLayout(1, 4));

		jb1 = new JButton("中文生成");
		jb1.addActionListener(this);
		jb2 = new JButton("粘贴");
		jb2.addActionListener(this);
		jb3 = new JButton("剪切");
		jb3.addActionListener(this);
		jb4 = new JButton("生成");
		jb4.addActionListener(this);
		jpanel.add(jb1);
		jpanel.add(jb2);
		jpanel.add(jb3);
		jpanel.add(jb4);

		contentPane.add(jscrollPane, BorderLayout.CENTER);
		contentPane.add(jpanel, BorderLayout.SOUTH);

		jf.setSize(400, 300);
		jf.setLocation(400, 200);
		jf.setVisible(true);

		jf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	// 覆盖接口ActionListener的方法actionPerformed
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jb1) {
			jta.setText(aotuChange2(jta.getText()));
		} else if (e.getSource() == jb2) {
			jta.paste();
		} else if (e.getSource() == jb3) {
			jta.cut();
		} else if (e.getSource() == jb4) {
			jta.setText(aotuChange(jta.getText()));
		}
	}

	private String aotuChange2(String xml) {
		String[] strs = xml.trim().split("\\s+");
		StringBuilder sb = new StringBuilder();
		for (String html : strs) {
			html = html.replaceAll("<.*</", "");
			html = html.replace(">", "");
			sb.append("@XStreamAlias(\"");
			sb.append(html);
			sb.append("\")");
			sb.append("\n");
		}
		strs2=sb.toString().split("\\s+");
		return sb.toString();
	}

	public String aotuChange(String xml) {
		int i=0;
		String[] strs = xml.trim().split("\\s+");
		StringBuilder sb = new StringBuilder();
		for (String html : strs) {
			Pattern p = Pattern.compile(">([^</]+)</");
			Matcher m = p.matcher(html);//
			while (m.find()) {
				String s = m.group(1);
				if (NumberUtil.isInteger(s)) {
					sb.append(strs2[i]);
					sb.append("\n");
					i++;
					html = html.replaceAll("<.*</", "private Integer ");
					html = html.replace(">", "; ");
					sb.append(html);
					sb.append("\n");
				} else {
					sb.append(strs2[i]);
					sb.append("\n");
					i++;
					html = html.replaceAll("<.*</", "private String ");
					html = html.replace(">", "; ");
					sb.append(html);
					sb.append("\n");
				}
			}
		}

		/*
		 * xml = xml.replaceAll("<.*</", "private String "); xml =
		 * xml.replace(">", "; ");
		 */
		return sb.toString();
	}

	public static void main(String[] args) {
		new Test();
	}
}