package view.employees;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import model.Case;
import model.Employee;

public class EmployeeCaseCellRenderer extends JLabel implements TableCellRenderer{
	
		
	
		@Override
		public Component getTableCellRendererComponent(final JTable table,Object value, boolean isSelected, boolean hasFocus, final int row,int column) {
			this.setText("      "+value+"      ");
			setPreferredSize(new Dimension(200,getMinimumSize().height));
			//table.getColumnModel().getColumn(column).setPreferredWidth(10);
			setOpaque(true);
			if(((Integer)value)>0){
				setBackground(new Color(144, 255, 140));
			} else {
				setBackground(new Color(255, 140, 144));
			}
			setHorizontalAlignment(SwingConstants.CENTER);
			
			return this;
		}
		
	}