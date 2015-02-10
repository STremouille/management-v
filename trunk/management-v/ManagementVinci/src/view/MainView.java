package view;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;

import start.ManagementVinci;
import view.cases.CaseTableModel;
import view.employees.EmployeeCaseCellRenderer;
import view.employees.EmployeeTableModel;
import view.resume.ResumeTableModel;
import model.Case;
import model.Employee;
import model.Factory;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.table.DatePickerCellEditor;

public class MainView {

	private JFrame frmManagementApplication;
	private Factory factory;
	private JTextField textField;
	private ArrayList<Employee> employeeList;
	private ArrayList<Case> caseList;
	
	private JButton btnAddItem;
	
	private EmployeeTableModel employeeTM;
	private CaseTableModel caseTM;
	private ResumeTableModel resumeTM;
	
	private JTable employeeListTable,caseListTable,resumeTable;
	
	private JTabbedPane tabbedPane;
	
	private JMenuBar menuBar;
	private JMenu mnFichier;
	private JMenuItem mntmImprimer;
	private JMenu mnUtilits;
	private JMenuItem mntmQuiNestPas;
	private JLabel userInfo;
	private JMenuItem mntmAjouterUnUtilisateur;
	private JMenuItem mntmQuitter;
	private JMenuItem mntmDeconnection;
	private JSeparator separator;
	private JSeparator separator_2;
	private JMenuItem mntmVoirLesEmploys;
	private JMenuItem mntmVoirLesAffaires;
	private JScrollPane scrollPane;
	private JTree tree;
	private JPanel southPanel;
	private JLabel lblXlments;
	private JScrollPane resumeScrollPane;

	/**
	 * Create the application.
	 */
	public MainView(Factory factory) {
		this.factory=factory;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Initialise lists
		if(employeeList==null){
			employeeList=factory.getEmployees("");
		} 
		
		
		if(caseList==null){
			caseList = factory.getCases("");
		}
		
		
		frmManagementApplication = new JFrame();
		frmManagementApplication.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		frmManagementApplication.setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/img/logo.png")));
		frmManagementApplication.setTitle("Management Application");
		frmManagementApplication.setBounds(100, 100, 450, 300);
		frmManagementApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel northPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) northPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		frmManagementApplication.getContentPane().add(northPanel, BorderLayout.NORTH);
		
		if(factory.getUser()!=null){
			userInfo = new JLabel("Connect\u00E9 en tant que :"+factory.getUser().getLogin());
			northPanel.add(userInfo);
		}
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		frmManagementApplication.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		menuBar = new JMenuBar();
		frmManagementApplication.setJMenuBar(menuBar);
		
		mnFichier = new JMenu(" Fichier ");
		mnFichier.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		menuBar.add(mnFichier);
		
		mntmImprimer = new JMenuItem("Imprimer");
		mntmImprimer.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		mntmImprimer.setIcon(new ImageIcon(MainView.class.getResource("/img/icon/octicons_f0cf(6)_24.png")));
		mnFichier.add(mntmImprimer);
		
		separator = new JSeparator();
		mnFichier.add(separator);
		
		mntmDeconnection = new JMenuItem("D\u00E9connexion");
		mntmDeconnection.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		mntmDeconnection.setIcon(new ImageIcon(MainView.class.getResource("/img/icon/octicons_f051(1)_24.png")));
		mnFichier.add(mntmDeconnection);
		
		mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		mntmQuitter.setIcon(new ImageIcon(MainView.class.getResource("/img/icon/octicons_f032(2)_24.png")));
		mnFichier.add(mntmQuitter);
		
		
		mnUtilits = new JMenu(" Utilit\u00E9s ");
		mnUtilits.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		menuBar.add(mnUtilits);
		
		mntmQuiNestPas = new JMenuItem("Qui n'est pas affect\u00E9 ?");
		mntmQuiNestPas.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		mntmQuiNestPas.setIcon(new ImageIcon(MainView.class.getResource("/img/icon/octicons_f02c(8)_24.png")));
		mnUtilits.add(mntmQuiNestPas);
		
		mntmAjouterUnUtilisateur = new JMenuItem("Manager les utilisateurs");
		mntmAjouterUnUtilisateur.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		mntmAjouterUnUtilisateur.setIcon(new ImageIcon(MainView.class.getResource("/img/icon/octicons_f031(3)_24.png")));
		mnUtilits.add(mntmAjouterUnUtilisateur);
		
		separator_2 = new JSeparator();
		mnUtilits.add(separator_2);
		
		mntmVoirLesEmploys = new JMenuItem("Voir les employ\u00E9s supprim\u00E9s");
		mntmVoirLesEmploys.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		mntmVoirLesEmploys.setIcon(new ImageIcon(MainView.class.getResource("/img/icon/octicons_f04e(0)_24.png")));
		mnUtilits.add(mntmVoirLesEmploys);
		
		mntmVoirLesAffaires = new JMenuItem("Voir les affaires supprim\u00E9es");
		mntmVoirLesAffaires.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		mntmVoirLesAffaires.setIcon(new ImageIcon(MainView.class.getResource("/img/icon/octicons_f04e(0)_24.png")));
		mnUtilits.add(mntmVoirLesAffaires);
		
		JScrollPane employeeScrollPane = new JScrollPane();
		
		JScrollPane caseScrollPanel = new JScrollPane();
		
		if(factory!=null && employeeList!=null){
			employeeTM = new EmployeeTableModel(factory,employeeList,employeeListTable);
			employeeListTable = new JTable(employeeTM);
			employeeListTable.setDefaultRenderer(Integer.class, new EmployeeCaseCellRenderer());
			employeeListTable.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
			employeeListTable.getTableHeader().setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
			employeeListTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			employeeListTable.setAutoCreateRowSorter(true);
			employeeScrollPane = new JScrollPane(employeeListTable);
		}
		if(factory!=null && caseList != null){
			caseTM = new CaseTableModel(factory,caseList,true);
			caseListTable = new JTable(caseTM);
			caseListTable.setDefaultEditor(Date.class, new DatePickerCellEditor());
			caseListTable.setDefaultRenderer(Integer.class, new EmployeeCaseCellRenderer());
			caseListTable.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
			caseListTable.getTableHeader().setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
			caseListTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			caseListTable.setAutoCreateRowSorter(true);
			caseScrollPanel = new JScrollPane(caseListTable);
		}
			
			
			scrollPane = new JScrollPane();
			tabbedPane.addTab("Contrats en cours", null, scrollPane, null);
			 
			
			//TREE
			tree = new JTree(computeTreeRoot());
			tree.setShowsRootHandles(true);
			tree.setForeground(Color.BLACK);
			tree.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
			tree.setCellRenderer(new MyTreeCellRenderer());
			
			
			scrollPane.setViewportView(tree);
		
			tabbedPane.addTab("Employ\u00E9s", null, employeeScrollPane, null);
			
			
			tabbedPane.addTab("Affaires", null, caseScrollPanel, null);
			
			resumeTM = new ResumeTableModel(factory);
			resumeTable = new JTable(resumeTM);

			resumeScrollPane = new JScrollPane(resumeTable);
			tabbedPane.addTab("Resume", null, resumeScrollPane, null);
			resumeTable.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
			resumeTable.getTableHeader().setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
			resumeTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			
			JPanel toolPanel = new JPanel();
			frmManagementApplication.getContentPane().add(toolPanel, BorderLayout.WEST);
			
			employeeListTable.requestFocusInWindow();
			GridBagLayout gbl_toolPanel = new GridBagLayout();
			gbl_toolPanel.columnWidths = new int[]{86, 0};
			gbl_toolPanel.rowHeights = new int[]{40, 23, 0, 0, 0};
			gbl_toolPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
			gbl_toolPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			toolPanel.setLayout(gbl_toolPanel);
			
			textField = new JTextField("Search");
			textField.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
			textField.setToolTipText("Search");
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.anchor = GridBagConstraints.NORTH;
			gbc_textField.insets = new Insets(0, 0, 5, 0);
			gbc_textField.gridx = 0;
			gbc_textField.gridy = 1;
			toolPanel.add(textField, gbc_textField);
			textField.setColumns(10);
			
			btnAddItem = new JButton("");
			btnAddItem.setIcon(new ImageIcon(MainView.class.getResource("/img/icon/octicons_f06b(5)_64.png")));
			GridBagConstraints gbc_btnAddItem = new GridBagConstraints();
			gbc_btnAddItem.insets = new Insets(0, 0, 5, 0);
			gbc_btnAddItem.gridx = 0;
			gbc_btnAddItem.gridy = 2;
			toolPanel.add(btnAddItem, gbc_btnAddItem);
		
			btnAddItem.setEnabled(false);
			textField.setEnabled(false);
			
			southPanel = new JPanel();
			FlowLayout flowLayout_1 = (FlowLayout) southPanel.getLayout();
			flowLayout_1.setAlignment(FlowLayout.RIGHT);
			frmManagementApplication.getContentPane().add(southPanel, BorderLayout.SOUTH);
			
			lblXlments = new JLabel("x \u00E9l\u00E9ment(s)");
			lblXlments.setHorizontalAlignment(SwingConstants.RIGHT);
			southPanel.add(lblXlments);
			mntmImprimer.setEnabled(false);
			
			
			
			processTable(caseListTable);
			processTable(employeeListTable);
			processTable(resumeTable);
		
	}
	
	public void processTable(JTable table){
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		 
		//TableColumnAdjuster tca = new TableColumnAdjuster(table);
		//tca.adjustColumns();
		
		for (int column = 0; column < table.getColumnCount(); column++)
		{
		    TableColumn tableColumn = table.getColumnModel().getColumn(column);
		    int preferredWidth = tableColumn.getPreferredWidth();
		    int maxWidth = tableColumn.getMaxWidth();
		 
		    System.out.print("preferredWidth :" + preferredWidth+"->");
		    for (int row = 0; row < table.getRowCount(); row++)
		    {
		        TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
		        Component c = table.prepareRenderer(cellRenderer, row, column);
		        int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
		        preferredWidth = Math.max(preferredWidth, width);
		 
		        //  We've exceeded the maximum width, no need to check other rows
		 
		        if (preferredWidth >= maxWidth)
		        {
		            preferredWidth = maxWidth;
		            break;
		        }
		    }
		    System.out.println("preferredWidth: " + preferredWidth);
		    tableColumn.setPreferredWidth( preferredWidth );
		}
	}
	
	public DefaultMutableTreeNode computeTreeRoot(){
		DefaultMutableTreeNode res = new DefaultMutableTreeNode("Liste des employés");
		employeeList = factory.getEmployees("");
		Iterator<Employee> it = employeeList.iterator();
		while(it.hasNext()){
			Employee e = it.next();
			DefaultMutableTreeNode employeeNode = new DefaultMutableTreeNode(e); 
			Iterator<Case> itCase = factory.getCasesForThisEmployee(e).iterator();
			while(itCase.hasNext()){
				Case c = itCase.next();
				employeeNode.add(new DefaultMutableTreeNode(c));
			}
			res.add(employeeNode);
		}
		
		return res;
	}
	
	public void setElementsNumber(int number){
		lblXlments.setText(number+" \u00E9l\u00E9ment(s)");
	}
	
	public void refreshTree(){
		((DefaultTreeModel) tree.getModel()).setRoot(computeTreeRoot());
		((DefaultTreeModel) tree.getModel()).reload();
	}
	
	public void addSeeDeletedCaseListener(ActionListener al){
		this.mntmVoirLesAffaires.addActionListener(al);
	}
	
	public void addSeeDeletedEmployeeListener(ActionListener al){
		this.mntmVoirLesEmploys.addActionListener(al);
	}
	
	public void addDisconectListener(ActionListener al){
		mntmDeconnection.addActionListener(al);
	}
	
	public void addQuitListener(ActionListener al){
		mntmQuitter.addActionListener(al);
	}
	
	public void addNewUserListener(ActionListener al){
		this.mntmAjouterUnUtilisateur.addActionListener(al);
	}
	
	public void addWhosNotWorking(ActionListener al){
		this.mntmQuiNestPas.addActionListener(al);
	}
	
	public void addTabbedPaneChangeListerner(ChangeListener cl){
		this.tabbedPane.addChangeListener(cl);
	}
	
	public void addPrintListener(ActionListener al){
		this.mntmImprimer.addActionListener(al);
	}

	public void addButtonAddItemListener(ActionListener al){
		this.btnAddItem.addActionListener(al);
	}
	
	public JTabbedPane getTabbedPane(){
		return tabbedPane;
	}
	
	public void setVisible(boolean b) {
		frmManagementApplication.setVisible(true);
	}

	public void addSeachListener(DocumentListener dl ){
		textField.getDocument().addDocumentListener(dl);
	}
	
	public void addFocusListener(FocusListener fl ){
		textField.addFocusListener(fl);
	}
	
	
	public void addRightClickMouseListenerOnEmployeeTable(MouseAdapter ma){
		employeeListTable.addMouseListener(ma);
	}
	
	public void addRightClickMouseListenerOnCaseTable(MouseAdapter ma){
		caseListTable.addMouseListener(ma);
	}

	public ArrayList<Employee> getEmployeeList() {
		return employeeTM.getEmployeeList();
	}

	public String getSearchCriteria() {
		return textField.getText();
	}

	public void setSearchText(String string) {
		textField.setText(string);
	}

	public JTable getEmployeeTable() {
		return employeeListTable;
	}

	public EmployeeTableModel getEmployeeTableModel() {
		return employeeTM;
	}

	public void setFullScreen() {
		frmManagementApplication.setExtendedState(Frame.MAXIMIZED_BOTH);
	}

	public JFrame getFrame() {
		return frmManagementApplication;
	}

	public void refresh() {
		initialize();
	}

	public JTable getCaseTable() {
		return caseListTable;
	}

	public ArrayList<Case> getCaseList() {
		return caseList;
	}

	public CaseTableModel getCaseListTableModel() {
		return caseTM;
	}

	public void setCaseList(ArrayList<Case> caseList2) {
		caseList = caseList2;
	}

	public JTextField getSearchField(){
		return textField;
	}
	
	public JButton getAddButton() {
		return btnAddItem;
	}

	public void addTreeSeletectionListener(TreeSelectionListener treeListener) {
		tree.addTreeSelectionListener(treeListener);
	}

	public JTree getTree() {
		return tree;
	}

	public JMenuItem getPrintButton() {
		return mntmImprimer;
	}
	
	class MyTreeCellRenderer implements TreeCellRenderer{

		private JLabel label;
		
		public MyTreeCellRenderer() {
			label = new JLabel();
		}
		
		@Override
		public Component getTreeCellRendererComponent(JTree tree, Object value,
				boolean selected, boolean expanded, boolean leaf, int row,
				boolean hasFocus) {
			Object o = ((DefaultMutableTreeNode) value).getUserObject();
			if(o instanceof Employee){
				label.setText(((Employee) o).getFirstName()+" "+((Employee) o).getLastName());
				if(factory.getCasesForThisEmployee((Employee) o).size()>0){
					label.setIcon(new ImageIcon(MainView.class.getResource("/img/icon/Entypo_d83d(0)_24.png")));
				} else {
					label.setIcon(new ImageIcon(MainView.class.getResource("/img/icon/Entypo_d83d(2)_24.png")));
				}
			} else if(o instanceof Case){
				label.setIcon(new ImageIcon(MainView.class.getResource("/img/icon/Entypo_d83d(1)_24.png")));
				label.setText("<html>"+((Case) o).getNumber()+" - <i>"+((Case) o).getClient()+" - "+((Case) o).getPositon()+"</i></html>");
			} else {
				label.setIcon(new ImageIcon(MainView.class.getResource("/img/icon/Entypo_d83d(0)_32.png")));
				label.setText("<html>Employé(s)</html>");
			}
			return label;
		}
		
	}

	public ResumeTableModel getResumeTableModel() {
		return resumeTM;
	}

	public JTable getResumeTable() {
		return resumeTable;
	}
}
