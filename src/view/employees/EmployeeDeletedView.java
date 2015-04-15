package view.employees;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import start.ManagementVinci;
import model.Employee;
import model.Factory;

public class EmployeeDeletedView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Factory factory;
	private ArrayList<Employee> eList;
	private EmployeeTableModel etm;


	/**
	 * Create the frame.
	 */
	public EmployeeDeletedView(Factory f) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(EmployeeDeletedView.class.getResource("/img/logo.png")));
		setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		this.factory=f;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(150, 150, 1500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		eList = f.getDeletedEmployee();
		System.out.println(eList.size());
		etm = new EmployeeTableModel(factory, eList, table);
		
		table = new JTable(etm);
		table.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		table.getTableHeader().setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
		
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table.addMouseListener(new EmployeeDeletedMouseAdaptater());
		
		
	}
	
	class EmployeeDeletedMouseAdaptater extends MouseAdapter{

		@Override
		public void mouseReleased(MouseEvent e) {
			JTable tableC = table;
			int rC = tableC.rowAtPoint(e.getPoint());
	        if (rC >= 0 && rC < tableC.getRowCount()) {
	            tableC.setRowSelectionInterval(rC, rC);
	        } else {
	            tableC.clearSelection();
	        }

	        final int rowindexC = tableC.getSelectedRow();
	        if (rowindexC < 0)
	            return;
	        if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
	            final JPopupMenu popup = new JPopupMenu();
	            JMenuItem mh = new JMenuItem("Annuler la suppression");
	            mh.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						factory.unDeleteEmployeeId(etm.getEmployeeList().get(rowindexC));
						etm.fireTableDataChangedForDeletedEmployee();
					}
				});
	            popup.add(mh);
	            JMenuItem mDelete = new JMenuItem("Supprimer définitivement l'employé");
	            mDelete.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(JOptionPane.showConfirmDialog(popup, "Êtes vous sur de supprimer définitivement l'employé ?")==JOptionPane.OK_OPTION){
							factory.deleteDefinitely(etm.getEmployeeList().get(rowindexC));
							etm.fireTableDataChangedForDeletedEmployee();
						} 
					}
				});
	            popup.add(mDelete);
	            popup.show(e.getComponent(), e.getX(), e.getY());
	        }
		}
		
	}


}
