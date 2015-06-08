package pv;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class ShowImage {
	private int iScreenWidth;
	private int iScreenHeight;
	private int iImageWidth;
	private int iImageHeight;
	private double dImageProportion;
	private double dScreenProportion;
	private String sImagePath;
	private ImageIcon icon;

	private int iSuitableX;
	private int iSuitableY;
	private PicViewer parent;
	public ShowImage(String path,PicViewer parent) {
		sImagePath = path;
		icon = new ImageIcon(sImagePath);
		iImageWidth = icon.getIconWidth();
		iImageHeight = icon.getIconHeight();
		dImageProportion = 1.0 * iImageHeight / iImageWidth;
		this.parent = parent;
		// showLabel = new JLabel();
		parent.showPanel.setBorder(BorderFactory.createEtchedBorder());
		
	}

	public void adjust() {
		// showPanel.setPreferredSize(new Dimension(getWidth(),getHeight()));
		iScreenWidth = parent.showPanel.getWidth();
		iScreenHeight = parent.showPanel.getHeight();
		dScreenProportion = 1.0 * iScreenHeight / iScreenWidth;
	}

	public void judge() {
		if (dScreenProportion > dImageProportion) {
			iImageWidth = iScreenWidth;
			iImageHeight = (int) (((double) iImageWidth) * dImageProportion);
			iSuitableX=0;
			iSuitableY=(int)((iScreenHeight-iImageHeight)/2);
		} else {
			iImageHeight = iScreenHeight;
			iImageWidth = (int) (((double) iImageHeight) / dImageProportion);
			iSuitableX=(int)((iScreenWidth-iImageWidth)/2);
			 //parent.showLabel.setPreferredSize(new
			//Dimension(iSuitableX,iSuitableY));
			 //parent.showLabel.setHorizontalAlignment(SwingConstants.CENTER);iSuitableY=0;
		}
	}
	public void toShowPicture() {
		// showPanel.setOpaque(false);
		adjust();
		if (iImageWidth > iScreenWidth && iImageHeight > iScreenHeight) {
			judge();
			System.out.println("123");
			System.out.println("image: w " + iImageWidth + " ,h "
					+ iImageHeight + " ,p1 " + dImageProportion);
			// showLabel.setBounds(0, 0, iScreenWidth, iScreenHeight);
		} else if (iImageWidth > iScreenWidth && iImageHeight < iScreenHeight) {
			iImageWidth = iScreenWidth;
			System.out.println("456");
			System.out.println("  p0>p1  h= " + iImageHeight);
		} else if (iImageWidth < iScreenWidth && iImageHeight > iScreenHeight) {
			iImageHeight = iScreenHeight;
			iSuitableX=(int)((iScreenWidth-iImageWidth)/2);
			System.out.println(789);
		} else {
			System.out.println(101112);
			iSuitableX = (int) ((iScreenWidth - iImageWidth) / 2);
			iSuitableY = (int) ((iScreenHeight - iImageHeight) / 2);
			 //parent.showLabel.setPreferredSize(new
			//Dimension(iSuitableX,iSuitableY));
			 //parent.showLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		parent.showLabel.setBounds(iSuitableX, iSuitableY, iImageWidth, iImageHeight);
		Image img = icon.getImage().getScaledInstance(iImageWidth,
				iImageHeight, Image.SCALE_SMOOTH);
		parent.showLabel.setIcon(new ImageIcon(img));
		parent.titleLabel.setText(sImagePath);
	}




}
