package view;

import model.Factory;
import model.users.User;

import start.ManagementVinci;
import view.employees.UserComboBoxModel;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
public class UserEditor extends JFrame {

	private JPanel contentPane;
	private JTextField login;
	private JTextField pwd;
	private JComboBox comboBoxUsers;
	private ArrayList<User> users;
	private JButton btnValidate;
	private JButton btnAdd;
	private JButton btnRemove;
	private UserComboBoxModel ucbm;

	/**
	 * Create the frame.
	 */
	public UserEditor(Factory f) {
		setTitle("User Editor");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/img/logo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 334, 252);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0};
		gbl_panel.rowHeights = new int[]{0};
		gbl_panel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		
		
//		users = f.getUserList();
//		//Convert in a array
//		String[] cb = new String[users.size()];
//		Iterator<User> it = users.iterator();
//		int acc=0;
//		while(it.hasNext()){
//			cb[acc]=it.next().getLogin();
//			acc++;
//		}
//		if(cb!=null)
		ucbm = new UserComboBoxModel(f);
		
		btnAdd = new JButton("");
		btnAdd.setIcon(new ImageIcon(UserEditor.class.getResource("/img/icon/octicons_f06b(5)_32.png")));
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.anchor = GridBagConstraints.EAST;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 0;
		panel_1.add(btnAdd, gbc_btnAdd);
		comboBoxUsers = new JComboBox(ucbm);
		comboBoxUsers.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		GridBagConstraints gbc_comboBoxUsers = new GridBagConstraints();
		gbc_comboBoxUsers.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxUsers.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxUsers.gridx = 1;
		gbc_comboBoxUsers.gridy = 0;
		panel_1.add(comboBoxUsers, gbc_comboBoxUsers);
		
		btnRemove = new JButton("");
		btnRemove.setIcon(new ImageIcon(UserEditor.class.getResource("/img/icon/octicons_f06c(4)_32.png")));
		GridBagConstraints gbc_btnRemove = new GridBagConstraints();
		gbc_btnRemove.anchor = GridBagConstraints.EAST;
		gbc_btnRemove.insets = new Insets(0, 0, 5, 5);
		gbc_btnRemove.gridx = 0;
		gbc_btnRemove.gridy = 1;
		panel_1.add(btnRemove, gbc_btnRemove);
		
		JLabel lblLogin = new JLabel("Login :");
		lblLogin.setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.anchor = GridBagConstraints.EAST;
		gbc_lblLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogin.gridx = 0;
		gbc_lblLogin.gridy = 2;
		panel_1.add(lblLogin, gbc_lblLogin);
		
		login = new JTextField();
		login.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		GridBagConstraints gbc_login = new GridBagConstraints();
		gbc_login.insets = new Insets(0, 0, 5, 0);
		gbc_login.fill = GridBagConstraints.HORIZONTAL;
		gbc_login.gridx = 1;
		gbc_login.gridy = 2;
		panel_1.add(login, gbc_login);
		login.setColumns(10);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
		GridBagConstraints gbc_lblMotDePasse = new GridBagConstraints();
		gbc_lblMotDePasse.anchor = GridBagConstraints.EAST;
		gbc_lblMotDePasse.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotDePasse.gridx = 0;
		gbc_lblMotDePasse.gridy = 3;
		panel_1.add(lblMotDePasse, gbc_lblMotDePasse);
		
		pwd = new JTextField();
		pwd.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		GridBagConstraints gbc_pwd = new GridBagConstraints();
		gbc_pwd.insets = new Insets(0, 0, 5, 0);
		gbc_pwd.fill = GridBagConstraints.HORIZONTAL;
		gbc_pwd.gridx = 1;
		gbc_pwd.gridy = 3;
		panel_1.add(pwd, gbc_pwd);
		pwd.setColumns(10);
		
		btnValidate = new JButton("");
		btnValidate.setIcon(new ImageIcon(UserEditor.class.getResource("/img/icon/octicons_f03a(7)_32.png")));
		GridBagConstraints gbc_btnValidate = new GridBagConstraints();
		gbc_btnValidate.gridx = 1;
		gbc_btnValidate.gridy = 4;
		panel_1.add(btnValidate, gbc_btnValidate);
	}
	
	public void setUpComboboxModel(){
		comboBoxUsers= new JComboBox(ucbm);
		comboBoxUsers.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
	}
	
	public void addRemoveListener(ActionListener al){
		this.btnRemove.addActionListener(al);
	}
	
	public void addNewUserListener(ActionListener al){
		this.btnAdd.addActionListener(al);
	}

	public JTextField getLoginTextField(){
		return login;
	}
	
	public JTextField getPwdTextField(){
		return pwd;
	}
	
	public JComboBox getComboBox() {
		return comboBoxUsers;
	}

	public void addComboBoxListener(ActionListener comboBoxListener) {
		comboBoxUsers.addActionListener(comboBoxListener);
	}
	
	public ArrayList<User> getUsers(){
		return users;
	}

	public void addApplyListener(ActionListener applyListener) {
		btnValidate.addActionListener(applyListener);
	}


	public UserComboBoxModel getUserComboBoxModel() {
		return ucbm;
	}
}
