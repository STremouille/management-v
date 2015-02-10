package controller;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.ArrayList;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import start.ManagementVinci;
import view.LoginFrame;
import view.MainView;
import view.UserEditor;
import view.cases.CaseDeletedView;
import view.cases.CaseEmployeeEditorView;
import view.employees.EmployeeCaseEditorView;
import view.employees.EmployeeDeletedView;
import view.employees.EmployeeDetailsView;
import view.employees.EmployeeTableModel;
import model.Case;
import model.Employee;
import model.Factory;

public class MainController {

	private Factory factory;
	private MainView view;
	
	private String INIT_TEXT = "Search";
	
	public MainController(Factory factory,MainView view){
		this.factory=factory;
		this.view=view;
		
		view.addSeachListener(new SearchDocumentListener());
		view.addFocusListener(new SearchFocusListener());
		view.addRightClickMouseListenerOnEmployeeTable(new EmployeeMouseAdaptater());
		view.addRightClickMouseListenerOnCaseTable(new CaseMouseAdaptater());
		view.addButtonAddItemListener(new ButtonAddListener());
		view.addPrintListener(new PrintListener());
		
		view.addTabbedPaneChangeListerner(new TabbebPaneChangeListener());
		view.addWhosNotWorking(new WhosNotWorkingListener());
		view.addNewUserListener(new ManageUserListener());
		view.addQuitListener(new QuitListener());
		view.addDisconectListener(new LogOutListener());
		view.addSeeDeletedEmployeeListener(new SeeDeletedEmployeeListener());
		view.addSeeDeletedCaseListener(new SeeDeletedCaseListener());
		
		//view.addTreeSeletectionListener(new TreeListener());
	}
	
	public void updateEmployeeList(){
		view.refreshTree();
		if(!(view.getSearchCriteria().equals(INIT_TEXT)||view.getSearchCriteria().equals(""))){
			view.getEmployeeTableModel().fireTableDataChanged(view.getSearchCriteria());
		} else {
			view.getEmployeeTableModel().fireTableDataChanged("%");
		}
		if(view.getTabbedPane().getSelectedIndex()==1)
			view.setElementsNumber(view.getEmployeeTableModel().getEmployeeList().size());
	}
	
	public void updateCaseList() {
		view.refreshTree();
		if(!(view.getSearchCriteria().equals(INIT_TEXT)||view.getSearchCriteria().equals(""))){
			view.getCaseListTableModel().fireTableDataChanged(view.getSearchCriteria());
		} else {
			view.getCaseListTableModel().fireTableDataChanged("%");
		}
		if(view.getTabbedPane().getSelectedIndex()==2)
			view.setElementsNumber(view.getCaseListTableModel().getCaseList().size());
	}
	
	public void updateResumeList(){
		view.refreshTree();
		if(!(view.getSearchCriteria().equals(INIT_TEXT)||view.getSearchCriteria().equals(""))){
			view.getResumeTableModel().fireTableDataChanged(view.getSearchCriteria());
		} else {
			view.getResumeTableModel().fireTableDataChanged("%");
		}
		if(view.getTabbedPane().getSelectedIndex()==3)
			view.setElementsNumber(view.getResumeTableModel().getCaseList().size());
	}
	
	class SearchFocusListener implements FocusListener{
				
		@Override
		public void focusGained(FocusEvent e) {
			if(view.getSearchCriteria().equals(INIT_TEXT))
			view.setSearchText("");
		}

		@Override
		public void focusLost(FocusEvent e) {
			if(view.getSearchCriteria().isEmpty()){
				view.setSearchText(INIT_TEXT);
			}
				
		}
		
	}
	
	class ManageUserListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			UserEditor ue = new UserEditor(factory);
			UserEditorController uec = new UserEditorController(ue, factory);
			ue.setVisible(true);
		}
		
	}
	
	class SearchDocumentListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent e) {
			updateEmployeeList();
			updateCaseList();
			updateResumeList();
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			updateEmployeeList();
			updateCaseList();
			updateResumeList();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			updateEmployeeList();
			updateCaseList();
			updateResumeList();
		}
		
		
	}
	
	class EmployeeMouseAdaptater extends MouseAdapter{
		
		 @Override
		    public void mouseReleased(MouseEvent e) {
					JTable table = view.getEmployeeTable();
					int r = table.rowAtPoint(e.getPoint());
			        if (r >= 0 && r < table.getRowCount()) {
			            table.setRowSelectionInterval(r, r);
			        } else {
			            table.clearSelection();
			        }

			        final int rowindex = table.convertRowIndexToModel(table.getSelectedRow());
			        if (rowindex < 0)
			            return;
			        if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
			            JPopupMenu popup = new JPopupMenu();
			            JMenuItem mEmployeeDetail = new JMenuItem("Details");
			            mEmployeeDetail.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								EmployeeDetailsView edv = new EmployeeDetailsView(view.getEmployeeList().get(rowindex), factory);
								edv.setVisible(true);
							}
						});
			            popup.add(mEmployeeDetail);
			            JMenuItem mi = new JMenuItem("Supprimer "+ view
			            		.getEmployeeList()
			            		.get(rowindex)
			            		.getFirstName()+" "+view.getEmployeeList().get(rowindex).getLastName());
			            JMenuItem mCase = new JMenuItem("Voir les contrats en cours");
			            mCase.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								EmployeeCaseEditorView eev = new EmployeeCaseEditorView(factory, view.getEmployeeList().get(rowindex));
								eev.setVisible(true);
							}
						});
			            popup.add(mCase);
			            popup.add(mi);
			            mi.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								if(!factory.thisEmployeeHasCase(view.getEmployeeList().get(rowindex))){
									if(JOptionPane.showConfirmDialog(view.getFrame(), "Êtes vous sur de vouloir supprimer "+view.getEmployeeList().get(rowindex).getFirstName()+" "+view.getEmployeeList().get(rowindex).getLastName()+" ?")==JOptionPane.OK_OPTION){
										factory.deleteEmployee(view.getEmployeeList().get(rowindex));
										updateEmployeeList();
									}
								} else {
									JOptionPane.showMessageDialog(view.getFrame(),"Cette employé ne peut être supprimer car il est affecté sur une affaire");
								}
							}
						});
			            popup.show(e.getComponent(), e.getX(), e.getY());
			        }
		        
		    }
	}
	
	class CaseMouseAdaptater extends MouseAdapter{

		@Override
		public void mouseReleased(MouseEvent e) {
			view.setCaseList(view.getCaseListTableModel().getCaseList());
			JTable tableC = view.getCaseTable();
			int rC = tableC.rowAtPoint(e.getPoint());
	        if (rC >= 0 && rC < tableC.getRowCount()) {
	            tableC.setRowSelectionInterval(rC, rC);
	        } else {
	            tableC.clearSelection();
	        }

	        final int rowindexC =  tableC.convertRowIndexToModel(tableC.getSelectedRow());
	        if (rowindexC < 0)
	            return;
	        if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
	            JPopupMenu popup = new JPopupMenu();
	            JMenuItem mi = new JMenuItem("Supprimer "+ view.getCaseList().get(rowindexC).getNumber());
	            JMenuItem mCase = new JMenuItem("Voir les employés affectés sur cette affaire");
	            mCase.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						CaseEmployeeEditorView ceev = new CaseEmployeeEditorView(factory, view.getCaseList().get(rowindexC));
						ceev.setVisible(true);
					}
				});
	            popup.add(mCase);
	            popup.add(mi);
	            mi.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(!factory.caseHasEmployee(view.getCaseList().get(rowindexC))){
							if(JOptionPane.showConfirmDialog(view.getFrame(), "Êtes vous sur de vouloir supprimer "+view.getCaseList().get(rowindexC).getNumber()+" ?")==JOptionPane.OK_OPTION){
								factory.deleteCase(view.getCaseList().get(rowindexC));
								updateCaseList();
							}
						}else{
							JOptionPane.showMessageDialog(view.getFrame(), "Cette affaire ne peut pas être supprimée car elle contient des contrats");
						}
					}

				});
	            popup.show(e.getComponent(), e.getX(), e.getY());
	        }
		}
		
	}
	
	class ButtonAddListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (view.getTabbedPane().getSelectedIndex()) {
			case 1:
				factory.addEmployee(new Employee(-1,true,"", "", "","","","","","","", new ArrayList<Case>()));
				updateEmployeeList();
				break;
			case 2:
				factory.insertCase(new Case(-1,0.0,0,0,0,0,"NOUVELLE AFFAIRE "+(factory.getCases("").size()+1)+"","","","","","",0.0,0.0,"0.0","0.0",""), null);
				updateCaseList();
				break;
			default:
				break;
			}
			
		}
		
	}

	class PrintListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				view.getFrame().repaint();
				MessageFormat header = null;
				if(!(view.getSearchCriteria().equals("")||view.getSearchCriteria().equals(INIT_TEXT))){
					header = new MessageFormat("Impression : Filtre "+view.getSearchCriteria());
				} else {
					header = new MessageFormat("Impression");
				}
				MessageFormat footer = new MessageFormat("Page {0,number,integer}");
				PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
				pras.add(OrientationRequested.LANDSCAPE);
				switch (view.getTabbedPane().getSelectedIndex()) {
				case 1:
					view.getEmployeeTable().print(JTable.PrintMode.FIT_WIDTH, header, footer, true, pras, true);
					break;
				case 2:
					view.getCaseTable().print(JTable.PrintMode.FIT_WIDTH, header, footer, true, pras, true);
					break;
				case 3:
					view.getResumeTable().print(JTable.PrintMode.FIT_WIDTH, header, footer, true, pras, true);
					break;
				default:
					break;
				}
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	class TabbebPaneChangeListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			if (e.getSource() instanceof JTabbedPane) {
				view.refreshTree();
				((JComponent) e.getSource()).grabFocus();
                JTabbedPane pane = (JTabbedPane) e.getSource();
                //System.out.println("Selected paneNo : " + pane.getSelectedIndex());
                view.setSearchText(INIT_TEXT);
                updateCaseList();
                updateEmployeeList();
                switch (pane.getSelectedIndex()) {
				case 0:
					enableToolbar(false);
					view.setElementsNumber(factory.getEmployees("").size());
					break;
				case 1:
					view.setElementsNumber(factory.getEmployees("").size());
					view.getCaseListTableModel().fireTableDataChanged("");
					enableToolbar(true);
					break;
				case 2:
					view.setElementsNumber(factory.getCases("").size());
					view.getEmployeeTableModel().fireTableDataChanged("");
					enableToolbar(true);
					break;
				case 3:
					view.setElementsNumber(view.getResumeTableModel().getCaseList().size());
					enableToolbar(true);
					view.getAddButton().setEnabled(false);
				default:
					break;
				}
                
            }
		}
		
		public void enableToolbar(boolean b){
			view.getAddButton().setEnabled(b);
			view.getSearchField().setEnabled(b);
			view.getPrintButton().setEnabled(b);
		}
		
	}
	
	public void updateElementNumber(){
		
	}
	class WhosNotWorkingListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Employee> res = factory.getWhosNotWorking();
			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			JTable table = new JTable();
			table.setModel(new EmployeeTableModel(factory, res,table));
			table.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
			table.getTableHeader().setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
			frame.getContentPane().add(new JScrollPane(table));
			frame.setBounds(100, 100, 1400, 300);
			frame.setVisible(true);
		}
		
	}
	
	class QuitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			view.getFrame().dispose();
			System.exit(0);
		}
		
	}
	
	class LogOutListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			view.getFrame().dispose();
			LoginFrame lf = new LoginFrame();
			lf.setVisible(true);
			LoginController lc = new LoginController(factory, lf, view);
			
		}
		
	}
	
	class SeeDeletedEmployeeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			EmployeeDeletedView edv = new EmployeeDeletedView(factory);
			edv.setVisible(true);
		}
		
	}
	
	class SeeDeletedCaseListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			CaseDeletedView cdv = new CaseDeletedView(factory);
			cdv.setVisible(true);
		}
		
	}
	
//	class TreeListener implements TreeSelectionListener{
//
//		@Override
//		public void valueChanged(TreeSelectionEvent e) {
//			DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) view.getTree().getLastSelectedPathComponent();
//			/*if(dmtn == null)
//				return ;*/
//			Object node = dmtn.getUserObject();
//			
//			if(node.getClass().equals(Employee.class)){
//				System.out.println("Employee");
//			} else if (node.getClass().equals(Case.class)){
//				System.out.println("Case");
//			}
//		}
//		
//	}

}
