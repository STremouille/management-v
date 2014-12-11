package view.employees;

import org.jdesktop.swingx.table.DatePickerCellEditor;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.TableModel;

import model.Case;
import model.Employee;
import model.Factory;
import start.ManagementVinci;
import view.MainView;
import view.cases.CaseTableModel;

public class EmployeeCaseHistory extends JFrame {

	private JPanel contentPane;
	private Factory f;
	private Employee emp;
	private JTable table;
	private ArrayList<Case> cList;
	private CaseTableModel ctm,previousCtm;
	/**
	 * Create the frame.
	 */
	public EmployeeCaseHistory(Factory f,Employee e, CaseTableModel previousCtm) {
		this.setTitle("Historique des affaires pour "+e.getFirstName()+" "+e.getLastName());
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/img/logo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(150, 150, 1500, 300);
		
		this.f=f;
		this.emp=e;
		this.previousCtm=previousCtm;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		cList = f.getHistoryForThisEmployee(e);
		
		ctm = new CaseTableModel(f, cList,false);
		table = new JTable((TableModel) ctm);
		table.setDefaultEditor(Date.class, new DatePickerCellEditor());
		table.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		table.getTableHeader().setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
		JScrollPane scrollPane = new JScrollPane(table);
		
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table.addMouseListener(new CaseMouseAdaptater());
		
		//this.pack();
	}
	
	class CaseMouseAdaptater extends MouseAdapter{

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
	            JMenuItem mh = new JMenuItem("Contrat Pas Finie");
	            mh.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						f.unhistoriseCaseId(ctm.getCaseList().get(rowindexC));
						previousCtm.fireTableDataChangedForThisEmployee(emp);
						ctm.fireTableDataChangedForThisEmployeeForCaseHistory(emp);
					}
				});
	            popup.add(mh);
	            JMenuItem mSuppr = new JMenuItem("Supprimer le contrat");
	            mSuppr.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(JOptionPane.showConfirmDialog(contentPane, "Voulez-vous supprimer le contrat ?")==JOptionPane.OK_OPTION){
							f.deleteCaseId(ctm.getCaseList().get(rowindexC));
							ctm.fireTableDataChangedForThisEmployeeForCaseHistory(emp);
						}
					}
				});
	            popup.add(mSuppr);
	            popup.show(e.getComponent(), e.getX(), e.getY());
	        }
		}
		
	}

}
