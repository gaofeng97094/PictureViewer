package pv;
/*
 * 
 * 
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PicViewer extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7863499558485353141L;
	JPanel toolPanel;
	//private JImagePane jImagePane;
	// private JPanel contentPane;
	JLabel titleLabel;
	JLabel showLabel;
	JPanel showPanel;
	String picture;

	// 在构造器中构造出界面
	public PicViewer(String title) {
		super(title);

		FileDialog op, sv = null;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		setLayout(new BorderLayout(3, 3));

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("文件");
		menuBar.add(mnNewMenu);
		JMenu mnNewMenu_1 = new JMenu("编辑");
		menuBar.add(mnNewMenu_1);
		JMenu mnNewMenu_2 = new JMenu("查看");
		menuBar.add(mnNewMenu_2);
		JMenu menu = new JMenu("帮助");
		menuBar.add(menu);

		JMenuItem menuItem = new JMenuItem("打开");
		mnNewMenu.add(menuItem);
		op = new FileDialog(this, "打开", FileDialog.LOAD);
		menuItem.addActionListener(new FileMenuOperation(this, op, sv));

		JMenuItem mntmNewMenuItem = new JMenuItem("保存");
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("另存为");
		mnNewMenu.add(mntmNewMenuItem_1);
		sv = new FileDialog(this, "另存为", FileDialog.SAVE);
		mntmNewMenuItem_1
				.addActionListener(new FileMenuOperation(this, op, sv));

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("备份");
		mnNewMenu.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("删除");
		mnNewMenu.add(mntmNewMenuItem_3);

		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("打印");
		mnNewMenu.add(mntmNewMenuItem_4);

		JMenuItem menuItem_1 = new JMenuItem("属性");
		mnNewMenu.add(menuItem_1);

		JSeparator separator_1 = new JSeparator();
		mnNewMenu.add(separator_1);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("移动到");
		mnNewMenu.add(mntmNewMenuItem_5);

		JMenuItem mntmNewMenuItem_6 = new JMenuItem("复制到");
		mnNewMenu.add(mntmNewMenuItem_6);

		JSeparator separator_2 = new JSeparator();
		mnNewMenu.add(separator_2);

		JMenuItem menuItem_2 = new JMenuItem("退出");
		mnNewMenu.add(menuItem_2);
		menuItem_2.addActionListener(new FileMenuOperation(this, op, sv));

		JMenuItem mntmNewMenuItem_7 = new JMenuItem("裁剪");
		mnNewMenu_1.add(mntmNewMenuItem_7);

		JMenuItem mntmNewMenuItem_8 = new JMenuItem("涂鸦");
		mnNewMenu_1.add(mntmNewMenuItem_8);

		JMenuItem mntmNewMenuItem_9 = new JMenuItem("颜色");
		mnNewMenu_1.add(mntmNewMenuItem_9);

		JMenuItem mntmNewMenuItem_10 = new JMenuItem("对比度");
		mnNewMenu_1.add(mntmNewMenuItem_10);

		JMenuItem mntmNewMenuItem_11 = new JMenuItem("分类");
		mnNewMenu_1.add(mntmNewMenuItem_11);

		JMenuItem mntmNewMenuItem_12 = new JMenuItem("全屏");
		mnNewMenu_2.add(mntmNewMenuItem_12);

		JMenuItem mntmNewMenuItem_13 = new JMenuItem("预览");
		mnNewMenu_2.add(mntmNewMenuItem_13);

		JMenuItem mntmNewMenuItem_14 = new JMenuItem("幻灯片放映");
		mnNewMenu_2.add(mntmNewMenuItem_14);

		JSeparator separator_3 = new JSeparator();
		mnNewMenu_2.add(separator_3);

		JMenuItem mntmNewMenuItem_15 = new JMenuItem("顺时针旋转");
		mnNewMenu_2.add(mntmNewMenuItem_15);

		JMenuItem mntmNewMenuItem_16 = new JMenuItem("逆时针旋转");
		mnNewMenu_2.add(mntmNewMenuItem_16);

		JMenuItem mntmNewMenuItem_17 = new JMenuItem("水平翻转");
		mnNewMenu_2.add(mntmNewMenuItem_17);

		JMenuItem mntmNewMenuItem_18 = new JMenuItem("垂直翻转");
		mnNewMenu_2.add(mntmNewMenuItem_18);

		JSeparator separator_4 = new JSeparator();
		mnNewMenu_2.add(separator_4);

		JMenuItem mntmNewMenuItem_19 = new JMenuItem("放大");
		mnNewMenu_2.add(mntmNewMenuItem_19);

		JMenuItem mntmNewMenuItem_20 = new JMenuItem("缩小");
		mnNewMenu_2.add(mntmNewMenuItem_20);

		JSeparator separator_5 = new JSeparator();
		mnNewMenu_2.add(separator_5);

		JMenuItem mntmNewMenuItem_21 = new JMenuItem("正常大小");
		mnNewMenu_2.add(mntmNewMenuItem_21);

		JMenuItem mntmNewMenuItem_22 = new JMenuItem("最佳匹配");
		mnNewMenu_2.add(mntmNewMenuItem_22);

		JMenuItem menuItem_3 = new JMenuItem("关于");
		menu.add(menuItem_3);

		// showPanel=new JPanel();
		titleLabel = new JLabel("title", JLabel.CENTER);
		showPanel = new JPanel();
		showPanel.setBackground(Color.GRAY);
		showPanel.setLayout(null);
		showPanel.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()));
		showPanel.setBorder(BorderFactory.createEtchedBorder());
		showLabel = new JLabel();
		showPanel.add(showLabel);
		toolPanel = new JPanel(new FlowLayout());
		JButton large = new JButton("放大");
		JButton small = new JButton("缩小");
		JButton slideShow = new JButton("幻灯片放映");
		JButton last = new JButton("上一张");
		JButton next = new JButton("下一张");
		JButton clockwise = new JButton("顺时针");
		JButton anti_clockwise = new JButton("逆时针");

		toolPanel.add(large);
		toolPanel.add(small);
		toolPanel.add(slideShow);
		toolPanel.add(last);
		toolPanel.add(next);
		toolPanel.add(clockwise);
		toolPanel.add(anti_clockwise);

		add(showPanel,BorderLayout.CENTER);
		add(titleLabel, BorderLayout.NORTH);
		add(toolPanel, BorderLayout.SOUTH);
		this.setExtendedState(MAXIMIZED_BOTH);
		super.setVisible(true);
	}

	// 截入图像

	private int iScreenWidth;
	private int iScreenHeight;
	private int iImageWidth;
	private int iImageHeight;
	private double dImageProportion;
	private double dScreenProportion;
	private String sImagePath;
	private ImageIcon icon;
	public void showImage(String path) {
		sImagePath=path;
		icon = new ImageIcon(sImagePath);
		iImageWidth = icon.getIconWidth();
		iImageHeight = icon.getIconHeight();
		dImageProportion = 1.0 * iImageHeight / iImageWidth;
		//showLabel = new JLabel();
		showPanel.setBorder(BorderFactory.createEtchedBorder());
		toShowPicture();
	}
	
	public void adjust() {
		//showPanel.setPreferredSize(new Dimension(getWidth(),getHeight()));
		iScreenWidth = showPanel.getWidth();
		iScreenHeight = showPanel.getHeight();
		dScreenProportion = 1.0 * iScreenHeight / iScreenWidth;
		
	}

	public void judge() {
		if (dScreenProportion > dImageProportion) {
			iImageWidth = iScreenWidth;
			iImageHeight = (int) (((double) iScreenWidth) * dImageProportion);
		} else {
			iImageHeight = iScreenHeight;
			iImageWidth = (int) (((double) iScreenHeight) / dImageProportion);
		}
	}

	public void toShowPicture() {
		// showPanel.setOpaque(false);
		adjust();
		if (iImageWidth > iScreenWidth && iImageHeight > iScreenHeight) {
			judge();
			System.out.println("image: w " + iImageWidth + " ,h "
					+ iImageHeight + " ,p1 " + dImageProportion);
			// showLabel.setBounds(0, 0, iScreenWidth, iScreenHeight);
		} else if (iImageWidth > iScreenWidth && iImageHeight < iScreenHeight) {
			iImageWidth = iScreenWidth;
			System.out.println("  p0>p1  h= " + iImageHeight);
		} else if (iImageWidth < iScreenWidth && iImageHeight > iScreenHeight) {
			iImageHeight = iScreenHeight;
		} else {
//			int iSuitableX = (int) ((iScreenWidth - iImageWidth) / 2);
//			int iSuitableY = (int) ((iScreenHeight - iImageHeight) / 2);
//			parent.showLabel.setPreferredSize(new Dimension(iSuitableX,iSuitableY));
		}
		Image img = icon.getImage().getScaledInstance(iImageWidth,
				iImageHeight, Image.SCALE_SMOOTH);
		showLabel.setIcon(new ImageIcon(img));
		titleLabel.setText(sImagePath);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new PicViewer("图片浏览器");
			}
		});
		// 建立一个实例变量，并传给其名字

	}

}
