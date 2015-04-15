package view;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import start.ManagementVinci;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	private JButton btnConnect;

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setTitle("Identification");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/img/logo.png")));
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(LoginFrame.class.getResource("/img/Project_Management_Icon.png")));
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new CompoundBorder());
		contentPane.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblLogin = new JLabel("Identifiant :");
		lblLogin.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.anchor = GridBagConstraints.EAST;
		gbc_lblLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogin.gridx = 0;
		gbc_lblLogin.gridy = 0;
		panel_1.add(lblLogin, gbc_lblLogin);
		
		textField = new JTextField();
		textField.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel_1.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		GridBagConstraints gbc_lblMotDePasse = new GridBagConstraints();
		gbc_lblMotDePasse.anchor = GridBagConstraints.EAST;
		gbc_lblMotDePasse.insets = new Insets(0, 0, 0, 5);
		gbc_lblMotDePasse.gridx = 0;
		gbc_lblMotDePasse.gridy = 1;
		panel_1.add(lblMotDePasse, gbc_lblMotDePasse);
		
		textField_1 = new JPasswordField();
		textField_1.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		panel_1.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		btnConnect = new JButton("Connect");
		btnConnect.setPreferredSize(new Dimension(140, 40));
		btnConnect.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, 16));
		btnConnect.setIcon(new ImageIcon(LoginFrame.class.getResource("/img/icon/octicons_f03a(7)_32.png")));
		btnConnect.setSelectedIcon(new ImageIcon(LoginFrame.class.getResource("/img/icon/octicons_f03a(7)_128.png")));
		panel_2.add(btnConnect);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setPreferredSize(new Dimension(140, 40));
		btnQuit.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, 16));
		btnQuit.setIcon(new ImageIcon(LoginFrame.class.getResource("/img/icon/octicons_f032(2)_32.png")));
		btnQuit.setSelectedIcon(new ImageIcon(LoginFrame.class.getResource("/img/icon/octicons_f032(2)_128.png")));
		btnQuit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel_2.add(btnQuit);
		
		this.pack();
	}

	public void addConnectListener(ActionListener connectListener) {
		btnConnect.addActionListener(connectListener);
	}

	public String getLogin() {
		return textField.getText();
	}

	public String getPwd() {
		return textField_1.getText();
	}

}
