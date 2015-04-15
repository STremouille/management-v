package view.employees;
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
import view.cases.CaseEmployeeEditorView;
import view.cases.CaseForEmployeeEditor;
import view.cases.CaseTableModel;

import org.jdesktop.swingx.table.DatePickerCellEditor;

public class EmployeeCaseEditorView extends JDialog {
	private JTable table;
	private Factory f;
	private ArrayList<Case> cList;
	private final CaseTableModel ctm;
	private Employee employee;
	private JDialog view;
	/**
	 * Create the dialog.
	 */
	public EmployeeCaseEditorView(final Factory f,final Employee employee) {
		this.view=this;
		this.f=f;
		this.employee=employee;
		this.setTitle(employee.getFirstName()+" "+employee.getLastName());
		
		setBounds(100, 100, 1400, 300);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/img/logo.png")));
		getContentPane().setLayout(new BorderLayout());
		
		//TABLE
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable();
				table.addMouseListener(new CaseMouseAdaptater());
				cList = f.getCasesForThisEmployee(employee);
				table.setDefaultEditor(Date.class, new DatePickerCellEditor());
				ctm = new CaseTableModel(f, cList, false);
				table.setModel((TableModel) ctm);
				table.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
				table.getTableHeader().setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.EAST);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{105, 0};
			gbl_panel.rowHeights = new int[]{23, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JButton btnAddCase = new JButton("");
				btnAddCase.setPreferredSize(new Dimension(80, 40));
				btnAddCase.setIcon(new ImageIcon(EmployeeCaseEditorView.class.getResource("/img/icon/octicons_f06b(5)_32.png")));
				GridBagConstraints gbc_btnAddCase = new GridBagConstraints();
				gbc_btnAddCase.anchor = GridBagConstraints.NORTH;
				gbc_btnAddCase.insets = new Insets(0, 0, 5, 0);
				gbc_btnAddCase.gridx = 0;
				gbc_btnAddCase.gridy = 0;
				panel.add(btnAddCase, gbc_btnAddCase);
				btnAddCase.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						//C'est dégeu mais ça marche
							final JFrame caseSelector = new JFrame();
							caseSelector.setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/img/logo.png")));
							caseSelector.setLocationRelativeTo(view);
							caseSelector.getContentPane().setLayout(new BorderLayout());
							caseSelector.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						    final ArrayList<Case> resCases = f.getCases("");
						    ArrayList<Case> caseForThisEmployee = f.getCasesForThisEmployee(employee);
						    Iterator<Case> it = caseForThisEmployee.iterator();
						    ArrayList<String> tmpStringList = new ArrayList<String>();
						    while(it.hasNext()){
						    	Case c = it.next();
						    	tmpStringList.add(c.getNumber());
						    } 
						    
						    ArrayList<String> listCasesNumber = new ArrayList<String>();
						    it = resCases.iterator();
						    while(it.hasNext()){
						    	Case c = it.next();
						    	//UNCOMMENT TO NOT DISPLAY CASE ON WHICH EMPLOYEE IS ALREADY AFFECETED
						    	//if(!tmpStringList.contains(c.getNumber()))
						    		listCasesNumber.add(c.getNumber());
						    }
						    final JList list = new JList(new Vector<String>(listCasesNumber));
						    JLabel info = new JLabel("Choisissez l'affaire à ajouter");
						    caseSelector.getContentPane().add(info,BorderLayout.NORTH);
						    caseSelector.getContentPane().add(new JScrollPane(list),BorderLayout.CENTER);
						    JButton ok = new JButton("Ok");
						    ok.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									Iterator<Case> it = resCases.iterator();
									while(it.hasNext()){
										Case c = it.next();
										if(c.getNumber().equals(list.getSelectedValue())){
											c.seteRef(employee.getId());
											f.insertCase(c, employee);
											break;
										}
									}
									caseSelector.dispose();
									ctm.fireTableDataChangedForThisEmployee(employee);
								}
							});
						    JButton cancel = new JButton("Cancel");
						    cancel.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									caseSelector.dispose();
								}
							});
						    
						    JPanel okCancelButtonPanel = new JPanel();
						    okCancelButtonPanel.setLayout(new FlowLayout());
						    okCancelButtonPanel.add(ok);
						    okCancelButtonPanel.add(cancel);
						    
						    caseSelector.getContentPane().add(okCancelButtonPanel, BorderLayout.SOUTH);
						    
						    caseSelector.pack();

						    caseSelector.setVisible(true);
							
					}
				});
			}
			{
				JButton btnVoirLhistorique = new JButton("Historique");
				btnVoirLhistorique.setPreferredSize(new Dimension(80, 40));
				btnVoirLhistorique.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						EmployeeCaseHistory ech = new EmployeeCaseHistory(f, employee, ctm);
						ech.setVisible(true);
					}
				});
				GridBagConstraints gbc_btnVoirLhistorique = new GridBagConstraints();
				gbc_btnVoirLhistorique.anchor = GridBagConstraints.NORTH;
				gbc_btnVoirLhistorique.gridx = 0;
				gbc_btnVoirLhistorique.gridy = 1;
				panel.add(btnVoirLhistorique, gbc_btnVoirLhistorique);
			}
		}
		
		
		
		
		//BUTTONS
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
	}
	
	class CaseMouseAdaptater extends MouseAdapter{

		@Override
		public void mouseReleased(MouseEvent e) {
			cList=ctm.getCaseList();
			JTable tableC =table;
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
	            JMenuItem mCaseDetail = new JMenuItem("Détails");
	            mCaseDetail.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						CaseForEmployeeEditor cfee = new CaseForEmployeeEditor(f,cList.get(rowindexC),ctm,employee);
						cfee.setVisible(true);
					}
				});
	            popup.add(mCaseDetail);
	            JMenuItem mh = new JMenuItem("Contrat Fini");
	            mh.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						int res=JOptionPane.showConfirmDialog(view, "Faire un avenant au contrat ?");
						if(res==JOptionPane.CANCEL_OPTION){
							
						} else if(res==JOptionPane.OK_OPTION||res==JOptionPane.NO_OPTION){
							if(res==JOptionPane.OK_OPTION){
								//ADD ONE MONTH TO EACH DATE?
								f.insertCase(cList.get(rowindexC), employee);
							}
							f.historiseCaseId(cList.get(rowindexC));
							ctm.fireTableDataChangedForThisEmployee(employee);
						} 
						
					}
				});
	            popup.add(mh);
	            JMenuItem mi = new JMenuItem("Supprimer "+employee.getFirstName()+" "+employee.getLastName()+" de l'affaire "+ cList.get(rowindexC).getNumber());
	            popup.add(mi);
	            mi.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(JOptionPane.showConfirmDialog(null, "Êtes vous sur de vouloir supprimer "+cList.get(rowindexC).getNumber()+" ?")==JOptionPane.OK_OPTION){
								f.deleteCaseId(cList.get(rowindexC));
								ctm.fireTableDataChangedForThisEmployee(employee);
						}
					}

				});
	            popup.show(e.getComponent(), e.getX(), e.getY());
	        }
		}
		
	}

}
