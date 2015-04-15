package view.cases;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import model.Case;
import model.Employee;
import model.Factory;

import start.ManagementVinci;
import view.MainView;
import view.cases.CaseTableModel;
import view.employees.EmployeeTableModel;

public class CaseEmployeeEditorView extends JDialog {
	private JTable table;
	private final EmployeeTableModel ctm;
	private Factory f;
	private Case c;

	/**
	 * Create the dialog.
	 */
	public CaseEmployeeEditorView(final Factory f,final Case c) {
		
		this.setTitle(c.getNumber());
		this.f=f;
		this.c=c;
		
		
		setBounds(100, 100, 1500, 300);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/img/logo.png")));
		getContentPane().setLayout(new BorderLayout());
		
		//TABLE
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable();
				table.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
				table.addMouseListener(new EmployeeMouseAdaptater());
				table.getTableHeader().setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
				System.out.println(f.getEmployeeForThisCase(c).size());
				ctm = new EmployeeTableModel(f, f.getEmployeeForThisCase(c),table);
				table.setModel(ctm);
				scrollPane.setViewportView(table);
			}
		}
		
		//BUTTONS
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("");
				okButton.setIcon(new ImageIcon(CaseEmployeeEditorView.class.getResource("/img/icon/octicons_f032(2)_32.png")));
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
	}
	
	class EmployeeMouseAdaptater extends MouseAdapter{

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
	            JPopupMenu popup = new JPopupMenu();
	            JMenuItem mi = new JMenuItem("Supprimer "+ ctm.getEmployeeList().get(rowindexC).getFirstName()+" "+ctm.getEmployeeList().get(rowindexC).getLastName()+" de cette affaire?");
	            popup.add(mi);
	            mi.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(JOptionPane.showConfirmDialog(null, "Êtes vous sur de vouloir supprimer "+ctm.getEmployeeList().get(rowindexC).getFirstName()+" "+ctm.getEmployeeList().get(rowindexC).getLastName()+" de cette affaire ?")==JOptionPane.OK_OPTION){
								f.deleteEmployeeFromCase(ctm.getEmployeeList().get(rowindexC),c);
								ctm.fireTableDataChanged(c);
						}
					}

				});
	            popup.show(e.getComponent(), e.getX(), e.getY());
	        }
		}
		
	}

}
