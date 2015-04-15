package view.resume;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import model.Case;
import model.Employee;
import model.Factory;

public class ResumeTableModel extends AbstractTableModel{

	Factory factory;
	ArrayList<Case> cList;
	
	public ResumeTableModel(Factory f) {
		this.factory=f;
		cList=factory.getContractsWithHistory("");
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex==6||columnIndex==7)
			return Date.class;
		else if(columnIndex==0)
			return Boolean.class;
		else
			return super.getColumnClass(columnIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public int getColumnCount() {
		return 13;
	}

	@Override
	public int getRowCount() {
		return cList.size();
	}

	
	
	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 1:
			return "Numéro d'Affaire";
		case 2:
			return "Client";
		case 3:
			return "Projet";
		case 4:
			return "Position";
		case 5:
			return "Status";
		case 6:
			return "Date Début";
		case 7:
			return "Date Fin";
		case 8:
			return "Daily Rate";
		case 9:
			return "IGD";
		case 10:
			return "Autres Conditions";
		case 11:
			return "Pays";
		case 12:
			return "Employé";
		case 0:
			return "En cours";
		default:
			return "FAUX!";
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int col) {
		switch (col) {
		case 1:
			return cList.get(rowIndex).getNumber();
		case 2:
			return cList.get(rowIndex).getClient();
		case 3:
			return cList.get(rowIndex).getProject();
		case 4:
			return cList.get(rowIndex).getPositon();
		case 5:
			return cList.get(rowIndex).getStatus();
		case 6:
			return cList.get(rowIndex).getStartDate()*10000.0;
		case 7:
			return cList.get(rowIndex).getEndDate()*10000.0;
		case 8:
			return cList.get(rowIndex).getDailyRate();
		case 9:
			return cList.get(rowIndex).getDailyIndemnity();
		case 10:
			return cList.get(rowIndex).getOtherConditions();
		case 11:
			return cList.get(rowIndex).getCountry();
		case 12 : 
			Employee e = factory.getEmployeeForThisCaseIdWithHistory(cList.get(rowIndex));
			return e.getFirstName()+" "+e.getLastName();
		case 0 :
			return !cList.get(rowIndex).isHistorised();
		default:
			return "FAUX!";
		}
	}

	public void fireTableDataChanged(String filter) {
		cList=factory.getContractsWithHistory(filter);
		super.fireTableDataChanged();
	}

	public ArrayList<Case> getCaseList() {
		return cList;
	}
	
	

}
