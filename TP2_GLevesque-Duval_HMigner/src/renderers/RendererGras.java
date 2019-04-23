package renderers;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RendererGras extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent( JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column ) {
		
		super.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, column );
		setFont( getFont().deriveFont( Font.BOLD ) );
		setHorizontalAlignment( JLabel.CENTER );
		return this;

	}
}
