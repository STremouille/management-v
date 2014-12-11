package view.cases;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import org.jdesktop.swingx.JXDatePicker;

import start.ManagementVinci;
import model.Case;
import model.Employee;
import model.Factory;

public class CaseForEmployeeEditor extends JFrame {

	private JPanel contentPane;
	private JTextField position;
	private JTextField status;
	private JXDatePicker startDate;
	private JXDatePicker endDate;
	private JTextField dailyRate;
	private JTextField dailyIndemnity;
	private JTextField otherConditions;
	
	private Case c;
	private Factory factory;
	private CaseTableModel ctm;
	private Employee e;
	private JTextField country;


	/**
	 * Create the frame.
	 */
	public CaseForEmployeeEditor(Factory f,Case c,CaseTableModel ctm,Employee e) {
		
		this.c=c;
		this.factory=f;
		this.ctm=ctm;
		this.e=e;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(CaseForEmployeeEditor.class.getResource("/img/logo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		setTitle("Editeur de contrat");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel_2 = new JLabel(e.getFirstName()+" "+e.getLastName()+" : "+c.getNumber()+" - "+c.getClient()+" - "+c.getProject());
		lblNewLabel_2.setFont(new Font(ManagementVinci.fontName, Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 0;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Position :");
		lblNewLabel_1.setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		position = new JTextField(c.getPositon());
		position.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		GridBagConstraints gbc_position = new GridBagConstraints();
		gbc_position.insets = new Insets(0, 0, 5, 0);
		gbc_position.fill = GridBagConstraints.HORIZONTAL;
		gbc_position.gridx = 1;
		gbc_position.gridy = 1;
		panel.add(position, gbc_position);
		position.setColumns(10);
		
		JLabel lblStatus = new JLabel("Status :");
		lblStatus.setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.anchor = GridBagConstraints.EAST;
		gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 2;
		panel.add(lblStatus, gbc_lblStatus);
		
		status = new JTextField(c.getStatus());
		status.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		GridBagConstraints gbc_status = new GridBagConstraints();
		gbc_status.insets = new Insets(0, 0, 5, 0);
		gbc_status.fill = GridBagConstraints.HORIZONTAL;
		gbc_status.gridx = 1;
		gbc_status.gridy = 2;
		panel.add(status, gbc_status);
		status.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Date de d\u00E9but :");
		lblNewLabel_3.setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		startDate = new JXDatePicker(new Date((long) (c.getStartDate()*10000.0)));
		startDate.getEditor().setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		GridBagConstraints gbc_startDate = new GridBagConstraints();
		gbc_startDate.insets = new Insets(0, 0, 5, 0);
		gbc_startDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_startDate.gridx = 1;
		gbc_startDate.gridy = 3;
		panel.add(startDate, gbc_startDate);
		
		JLabel lblDateDeFin = new JLabel("Date de fin :");
		lblDateDeFin.setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
		GridBagConstraints gbc_lblDateDeFin = new GridBagConstraints();
		gbc_lblDateDeFin.anchor = GridBagConstraints.EAST;
		gbc_lblDateDeFin.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateDeFin.gridx = 0;
		gbc_lblDateDeFin.gridy = 4;
		panel.add(lblDateDeFin, gbc_lblDateDeFin);
		
		endDate = new JXDatePicker(new Date((long) (c.getEndDate()*10000.0)));
		endDate.getEditor().setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		GridBagConstraints gbc_endDate = new GridBagConstraints();
		gbc_endDate.insets = new Insets(0, 0, 5, 0);
		gbc_endDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_endDate.gridx = 1;
		gbc_endDate.gridy = 4;
		panel.add(endDate, gbc_endDate);
		
		JLabel lblDailyRate = new JLabel("Daily Rate:");
		lblDailyRate.setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
		GridBagConstraints gbc_lblDailyRate = new GridBagConstraints();
		gbc_lblDailyRate.anchor = GridBagConstraints.EAST;
		gbc_lblDailyRate.insets = new Insets(0, 0, 5, 5);
		gbc_lblDailyRate.gridx = 0;
		gbc_lblDailyRate.gridy = 5;
		panel.add(lblDailyRate, gbc_lblDailyRate);
		
		dailyRate = new JTextField(c.getDailyRate()+"");
		dailyRate.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		GridBagConstraints gbc_dailyRate = new GridBagConstraints();
		gbc_dailyRate.insets = new Insets(0, 0, 5, 0);
		gbc_dailyRate.fill = GridBagConstraints.HORIZONTAL;
		gbc_dailyRate.gridx = 1;
		gbc_dailyRate.gridy = 5;
		panel.add(dailyRate, gbc_dailyRate);
		
		JLabel lblIgd = new JLabel("IGD:");
		lblIgd.setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
		GridBagConstraints gbc_lblIgd = new GridBagConstraints();
		gbc_lblIgd.anchor = GridBagConstraints.EAST;
		gbc_lblIgd.insets = new Insets(0, 0, 5, 5);
		gbc_lblIgd.gridx = 0;
		gbc_lblIgd.gridy = 6;
		panel.add(lblIgd, gbc_lblIgd);
		
		dailyIndemnity = new JTextField(c.getDailyIndemnity()+"");
		dailyIndemnity.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		GridBagConstraints gbc_dailyIndemnity = new GridBagConstraints();
		gbc_dailyIndemnity.insets = new Insets(0, 0, 5, 0);
		gbc_dailyIndemnity.fill = GridBagConstraints.HORIZONTAL;
		gbc_dailyIndemnity.gridx = 1;
		gbc_dailyIndemnity.gridy = 6;
		panel.add(dailyIndemnity, gbc_dailyIndemnity);
		
		JLabel lblAutresConditions = new JLabel("Autres conditions :");
		lblAutresConditions.setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
		GridBagConstraints gbc_lblAutresConditions = new GridBagConstraints();
		gbc_lblAutresConditions.anchor = GridBagConstraints.EAST;
		gbc_lblAutresConditions.insets = new Insets(0, 0, 5, 5);
		gbc_lblAutresConditions.gridx = 0;
		gbc_lblAutresConditions.gridy = 7;
		panel.add(lblAutresConditions, gbc_lblAutresConditions);
		
		otherConditions = new JTextField(c.getOtherConditions());
		otherConditions.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		otherConditions.setColumns(10);
		GridBagConstraints gbc_otherConditions = new GridBagConstraints();
		gbc_otherConditions.insets = new Insets(0, 0, 5, 0);
		gbc_otherConditions.fill = GridBagConstraints.HORIZONTAL;
		gbc_otherConditions.gridx = 1;
		gbc_otherConditions.gridy = 7;
		panel.add(otherConditions, gbc_otherConditions);
		
		JLabel lblPays = new JLabel("Pays :");
		lblPays.setFont(new Font(ManagementVinci.fontName, Font.BOLD, ManagementVinci.fontSize));
		GridBagConstraints gbc_lblPays = new GridBagConstraints();
		gbc_lblPays.anchor = GridBagConstraints.EAST;
		gbc_lblPays.insets = new Insets(0, 0, 0, 5);
		gbc_lblPays.gridx = 0;
		gbc_lblPays.gridy = 8;
		panel.add(lblPays, gbc_lblPays);
		
		country = new JTextField(c.getCountry());
		country.setFont(new Font(ManagementVinci.fontName, Font.PLAIN, ManagementVinci.fontSize));
		country.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 8;
		panel.add(country, gbc_textField);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnValidate = new JButton("");
		btnValidate.setIcon(new ImageIcon(CaseForEmployeeEditor.class.getResource("/img/icon/octicons_f03a(7)_32.png")));
		panel_1.add(btnValidate);
		btnValidate.addActionListener(new OkButtonListener());
		
		JButton btnCancel = new JButton("");
		btnCancel.setIcon(new ImageIcon(CaseForEmployeeEditor.class.getResource("/img/icon/octicons_f032(2)_32.png")));
		panel_1.add(btnCancel);
		btnCancel.addActionListener(new CancelButtonListener());
	}

	
	class OkButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			c.setPositon(position.getText());
			c.setStatus(c.getStatus());
			if(startDate.getDate()!=null)
				c.setStartDate((int)(startDate.getDate().getTime()/10000));
			else
				c.setStartDate((int)(new Date(0).getTime()/10000));
			if(endDate.getDate()!=null)
				c.setEndDate((int)(endDate.getDate().getTime()/10000));
			else
				c.setEndDate((int)(new Date(0).getTime()/10000));
//			if(paiementDate.getDate()!=null)
//				c.setPaymentDate((int)(paiementDate.getDate().getTime()/10000));
//			else
//				c.setPaymentDate((int)(new Date(0).getTime()/10000));
//			if(paiementDueDate.getDate()!=null)
//				c.setPaymentDueDate((int)(paiementDueDate.getDate().getTime()/10000));
//			else
//				c.setPaymentDueDate((int)(new Date(0).getTime()/10000));
//			c.setAmountTaxFree(Double.valueOf(amountTaxFree.getText()));
//			c.setAmountWithTax(Double.valueOf(amountWithTax.getText()));
			c.setDailyRate(Double.valueOf(dailyRate.getText()));
			c.setDailyIndemnity(dailyIndemnity.getText());
			c.setOtherConditions(otherConditions.getText());
			c.setCountry(country.getText());
			c.setStatus(status.getText());
			
			factory.updateCase(c);
			ctm.fireTableDataChangedForThisEmployee(e);
			
			dispose();
		}
		
	}
	
	class CancelButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
		
	}
}
