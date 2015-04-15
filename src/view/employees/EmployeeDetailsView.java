package view.employees;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import start.ManagementVinci;
import view.MainView;
import model.Employee;
import model.Factory;

public class EmployeeDetailsView extends JFrame {

	private JPanel contentPane;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField phoneNumber;
	private JTextField phoneNumber2;
	private JTextField phoneNumberC;
	private JTextField email;
	private JTextField email2;
	private JTextField email3;
	
	private Employee employee;
	private Factory factory;
	private JTextField status;


	/**
	 * Create the frame.
	 */
	public EmployeeDetailsView(Employee e,final Factory factory) {
		setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		this.employee=e;
		this.factory=factory;
		
		setTitle("Editeur d'employ\u00E9");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/img/logo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 660, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel(" First Name :");
		lblNewLabel.setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		firstName = new JTextField(e.getFirstName());
		firstName.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		GridBagConstraints gbc_firstName = new GridBagConstraints();
		gbc_firstName.insets = new Insets(0, 0, 5, 0);
		gbc_firstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_firstName.gridx = 1;
		gbc_firstName.gridy = 0;
		panel.add(firstName, gbc_firstName);
		firstName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel(" Last Name :");
		lblNewLabel_1.setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		lastName = new JTextField(e.getLastName());
		lastName.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		GridBagConstraints gbc_lastName = new GridBagConstraints();
		gbc_lastName.insets = new Insets(0, 0, 5, 0);
		gbc_lastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lastName.gridx = 1;
		gbc_lastName.gridy = 1;
		panel.add(lastName, gbc_lastName);
		lastName.setColumns(10);
		
		JLabel lblTel = new JLabel("Tel :");
		lblTel.setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
		GridBagConstraints gbc_lblTel = new GridBagConstraints();
		gbc_lblTel.anchor = GridBagConstraints.EAST;
		gbc_lblTel.insets = new Insets(0, 0, 5, 5);
		gbc_lblTel.gridx = 0;
		gbc_lblTel.gridy = 2;
		panel.add(lblTel, gbc_lblTel);
		
		phoneNumber = new JTextField(e.getPhoneNumber());
		phoneNumber.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		GridBagConstraints gbc_phoneNumber = new GridBagConstraints();
		gbc_phoneNumber.insets = new Insets(0, 0, 5, 0);
		gbc_phoneNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_phoneNumber.gridx = 1;
		gbc_phoneNumber.gridy = 2;
		panel.add(phoneNumber, gbc_phoneNumber);
		phoneNumber.setColumns(10);
		
		JLabel lblTel_1 = new JLabel("Tel (2) :");
		lblTel_1.setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
		GridBagConstraints gbc_lblTel_1 = new GridBagConstraints();
		gbc_lblTel_1.anchor = GridBagConstraints.EAST;
		gbc_lblTel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblTel_1.gridx = 0;
		gbc_lblTel_1.gridy = 3;
		panel.add(lblTel_1, gbc_lblTel_1);
		
		phoneNumber2 = new JTextField(e.getPhoneNumberSecond());
		phoneNumber2.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		GridBagConstraints gbc_phoneNumber2 = new GridBagConstraints();
		gbc_phoneNumber2.insets = new Insets(0, 0, 5, 0);
		gbc_phoneNumber2.fill = GridBagConstraints.HORIZONTAL;
		gbc_phoneNumber2.gridx = 1;
		gbc_phoneNumber2.gridy = 3;
		panel.add(phoneNumber2, gbc_phoneNumber2);
		phoneNumber2.setColumns(10);
		
		JLabel lblTel_C = new JLabel("Tel Client :");
		lblTel_C.setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
		GridBagConstraints gbc_lblTel_C = new GridBagConstraints();
		gbc_lblTel_C.anchor = GridBagConstraints.EAST;
		gbc_lblTel_C.insets = new Insets(0, 0, 5, 5);
		gbc_lblTel_C.gridx = 0;
		gbc_lblTel_C.gridy = 4;
		panel.add(lblTel_C, gbc_lblTel_C);
		
		phoneNumberC = new JTextField(e.getPhoneNumberClient());
		phoneNumberC.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		GridBagConstraints gbc_phoneNumberC = new GridBagConstraints();
		gbc_phoneNumberC.insets = new Insets(0, 0, 5, 0);
		gbc_phoneNumberC.fill = GridBagConstraints.HORIZONTAL;
		gbc_phoneNumberC.gridx = 1;
		gbc_phoneNumberC.gridy = 4;
		panel.add(phoneNumberC, gbc_phoneNumberC);
		phoneNumberC.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 5;
		panel.add(lblEmail, gbc_lblEmail);
		
		email = new JTextField(e.getEmail());
		email.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		GridBagConstraints gbc_email = new GridBagConstraints();
		gbc_email.insets = new Insets(0, 0, 5, 0);
		gbc_email.fill = GridBagConstraints.HORIZONTAL;
		gbc_email.gridx = 1;
		gbc_email.gridy = 5;
		panel.add(email, gbc_email);
		email.setColumns(10);
		
		JLabel lblEmail_1 = new JLabel("Email (2) :");
		lblEmail_1.setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
		GridBagConstraints gbc_lblEmail_1 = new GridBagConstraints();
		gbc_lblEmail_1.anchor = GridBagConstraints.EAST;
		gbc_lblEmail_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail_1.gridx = 0;
		gbc_lblEmail_1.gridy = 6;
		panel.add(lblEmail_1, gbc_lblEmail_1);
		
		email2 = new JTextField(e.getEmailSecond());
		email2.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		GridBagConstraints gbc_email2 = new GridBagConstraints();
		gbc_email2.insets = new Insets(0, 0, 5, 0);
		gbc_email2.fill = GridBagConstraints.HORIZONTAL;
		gbc_email2.gridx = 1;
		gbc_email2.gridy = 6;
		panel.add(email2, gbc_email2);
		email2.setColumns(10);
		
		JLabel lblEmail_2 = new JLabel("Email (3) :");
		lblEmail_2.setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
		GridBagConstraints gbc_lblEmail_2 = new GridBagConstraints();
		gbc_lblEmail_2.anchor = GridBagConstraints.EAST;
		gbc_lblEmail_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail_2.gridx = 0;
		gbc_lblEmail_2.gridy = 7;
		panel.add(lblEmail_2, gbc_lblEmail_2);
		
		email3 = new JTextField(e.getEmailThird());
		email3.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		GridBagConstraints gbc_email3 = new GridBagConstraints();
		gbc_email3.insets = new Insets(0, 0, 5, 0);
		gbc_email3.fill = GridBagConstraints.HORIZONTAL;
		gbc_email3.gridx = 1;
		gbc_email3.gridy = 7;
		panel.add(email3, gbc_email3);
		email3.setColumns(10);
		
		JLabel lblStatus = new JLabel("Status :");
		lblStatus.setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.anchor = GridBagConstraints.EAST;
		gbc_lblStatus.insets = new Insets(0, 0, 0, 5);
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 8;
		panel.add(lblStatus, gbc_lblStatus);
		
		status = new JTextField(e.getStatus());
		status.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 8;
		panel.add(status, gbc_textField);
		status.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnOK = new JButton("");
		btnOK.setPreferredSize(new Dimension(65, 40));
		btnOK.setIcon(new ImageIcon(EmployeeDetailsView.class.getResource("/img/icon/octicons_f03a(7)_32.png")));
		btnOK.setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				employee.setFirstName(firstName.getText());
				employee.setLastName(lastName.getText());
				employee.setPhoneNumber(phoneNumber.getText());
				employee.setPhoneNumberSecond(phoneNumber2.getText());
				employee.setPhoneNumberClient(phoneNumberC.getText());
				employee.setEmail(email.getText());
				employee.setEmailSecond(email2.getText());
				employee.setEmailThird(email3.getText());
				employee.setStatus(status.getText());
				
				factory.updateEmployee(employee);
				dispose();
			}
		});
		panel_1.add(btnOK);
		
		JButton btnCancel = new JButton("");
		btnCancel.setPreferredSize(new Dimension(65, 40));
		btnCancel.setIcon(new ImageIcon(EmployeeDetailsView.class.getResource("/img/icon/octicons_f032(2)_32.png")));
		btnCancel.setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panel_1.add(btnCancel);
	}

}
