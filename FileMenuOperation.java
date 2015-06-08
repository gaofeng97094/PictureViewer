package pv;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.filechooser.*;

public class FileMenuOperation implements ActionListener {
	FileDialog op, sv;
	private PicViewer parent;
	private ShowImage showImage;

	// 利用构造器进行传递变量
	public FileMenuOperation(PicViewer parent, FileDialog op, FileDialog sv) {
		this.parent = parent;
		this.op = op;
		this.sv = sv;
	}

	// 事件监听器
	public void actionPerformed(ActionEvent e) {
		JMenuItem item = (JMenuItem) e.getSource();
		String count = item.getText();
		String str = "";
		// 打开
		if (count == "打开") {
			op.setVisible(true);
			try {
				File f1 = new File(op.getDirectory(), op.getFile());
				str = f1.getAbsolutePath();
				//System.out.println(str);
				showImage = new ShowImage(str,parent);
				showImage.toShowPicture();
				//parent.showPicture(str);
				//parent.showImage(str);
			
			} catch (Exception e1) {
				e1.getStackTrace();
			}
		}
		// 另存为
		// if (count=="另存为"){
		// sv.setVisible(true);
		// try{
		// File f1=new File(sv.getDirectory(),sv.getFile());
		// @SuppressWarnings("resource")
		// PrintWriter out = new PrintWriter(new FileOutputStream(f1),true);
		// String[] lines =parent.getText();
		// for(String line:lines){
		// out.println(line);
		// }
		// }
		// catch(Exception e2){
		// e2.getStackTrace();
		// }
		// }
		// 退出
		if (count == "退出") {
			System.exit(0);
		}

	}

}
