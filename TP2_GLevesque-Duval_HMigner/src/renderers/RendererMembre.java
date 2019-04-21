package renderers;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RendererMembre extends DefaultTableCellRenderer{
	
	private ImageIcon imgMembre, imgRien;
	
	public RendererMembre() {
		imgMembre = new ImageIcon(getClass().getResource("/images/logo.png"));
		Image image = imgMembre.getImage();
		Image newImage = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		imgMembre = new ImageIcon(newImage);
		
		imgRien = new ImageIcon(getClass().getResource("/images/rien.png"));
		Image image1 = imgRien.getImage();
		Image newImage1 = image1.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		imgRien = new ImageIcon(newImage1);
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		String membre = (String) value;
		if (membre.equalsIgnoreCase("Oui")) {
			setIcon(imgMembre);
			setText("");
		}else {
			setIcon(imgRien);
			setText("");
		}
		setHorizontalAlignment(CENTER);
		return this;
	}

}
