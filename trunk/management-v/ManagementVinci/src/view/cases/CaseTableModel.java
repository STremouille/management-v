package view.cases;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.AbstractTableModel;

import model.Case;
import model.Employee;
import model.Factory;

public class CaseTableModel extends AbstractTableModel {

	private Factory f;
	private ArrayList<Case> cList;
	private boolean isGeneralCase;
	
	public CaseTableModel(Factory f,ArrayList<Case> caseList,boolean isGeneralCase) {
		this.f=f;
		this.cList=caseList;
		this.isGeneralCase=isGeneralCase;
	}
	

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(isGeneralCase){
			return getValueAt(0, columnIndex).getClass();
		} else {
			if(columnIndex==5||columnIndex==6||columnIndex==11||columnIndex==12){
				return new Date().getClass();
			} else {
				return getValueAt(0, columnIndex).getClass();
			}
		}
		
	}

	@Override
	public int getColumnCount() {
		if(isGeneralCase){
			return 4;
		} else {
			return 11;
		}
	}

	@Override
	public String getColumnName(int columnIndex) {
		if(isGeneralCase){
			switch (columnIndex) {
			case 0:
				return "Numéro d'Affaire";
			case 1:
				return "Client";
			case 2:
				return "Projet";
			case 3:
				return "Contrats en cours";
			default:
				return "FAUX!";
			}
		} else {
			switch (columnIndex) {
			case 0:
				return "Numéro d'Affaire";
			case 1:
				return "Client";
			case 2:
				return "Projet";
			case 3:
				return "Position";
			case 4:
				return "Status";
			case 5:
				return "Date Début";
			case 6:
				return "Date Fin";
			/*case 7:
				return "Nombre de jours facturés";*/
			case 7:
				return "Daily Rate";
			case 8:
				return "IGD";
			case 9:
				return "Autres Conditions";
			case 10:
				return "Pays";
			/*case 11:
				return "Echeance de paiement";
			case 12:
				return "Date de paiment";
			case 13:
				return "Montant HT";
			case 14:
				return "Montant TTC";*/
			default:
				return "FAUX!";
			}
		}
	}

	@Override
	public int getRowCount() {
		return cList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(cList.size()>0){
			if(isGeneralCase){
				switch (columnIndex) {
				case 0:
					return cList.get(rowIndex).getNumber();
				case 1:
					return cList.get(rowIndex).getClient();
				case 2:
					return cList.get(rowIndex).getProject();
				case 3:
					return f.getEmployeeForThisCase(cList.get(rowIndex)).size();
				default:
					return "FAUX!";
				}
			} else{
				switch (columnIndex) {
				case 0:
					return cList.get(rowIndex).getNumber();
				case 1:
					return cList.get(rowIndex).getClient();
				case 2:
					return cList.get(rowIndex).getProject();
				case 3:
					return cList.get(rowIndex).getPositon();
				case 4:
					return cList.get(rowIndex).getStatus();
				case 5:
					return cList.get(rowIndex).getStartDate()*10000.0;
				case 6:
					return cList.get(rowIndex).getEndDate()*10000.0;
				/*case 7:
					return cList.get(rowIndex).getNumberOfDayInvoice();*/
				case 7:
					return cList.get(rowIndex).getDailyRate();
				case 8:
					return cList.get(rowIndex).getDailyIndemnity();
				case 9:
					return cList.get(rowIndex).getOtherConditions();
				case 10:
					return cList.get(rowIndex).getCountry();
				/*case 11:
					return cList.get(rowIndex).getPaymentDueDate()*10000.0;
				case 12:
					return cList.get(rowIndex).getPaymentDate()*10000.0;
				case 13:
					return cList.get(rowIndex).getAmountTaxFree();
				case 14:
					return cList.get(rowIndex).getAmountWithTax();*/
				default:
					return "FAUX!";
				}
			}
		} else {
			return "Empty";
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(isGeneralCase){
			if(columnIndex==3){
				return false;
			}
			else{
				return true;
			}
		} else {
			if(columnIndex>2){
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if(isGeneralCase){
			String oldNumber = cList.get(rowIndex).getNumber();
			switch (columnIndex) {
			case 0:
				//CHECK UNICITY OF NEW NUMBER
				Iterator<Case> it = f.getCases("").iterator();
				boolean unicityEnsured=true;
				while(it.hasNext()){
					Case c = it.next();
					if(aValue.equals(c.getNumber()))
						unicityEnsured=false;
				}
				if(unicityEnsured)
					cList.get(rowIndex).setNumber((String) aValue);
				break;
			case 1:
				cList.get(rowIndex).setClient((String) aValue);
				break;
			case 2:
				cList.get(rowIndex).setProject((String) aValue);
				break;
			default:
				break;
			}
			f.updateAllCase(cList.get(rowIndex),oldNumber);
		} else {
			switch (columnIndex) {
			case 3:
				cList.get(rowIndex).setPositon((String) aValue);
				break;
			case 4:
				cList.get(rowIndex).setStatus((String) aValue);
				break;
			case 5:
				cList.get(rowIndex).setStartDate((int)(((Date) aValue).getTime()/10000));
				break;
			case 6:
				cList.get(rowIndex).setEndDate((int)(((Date) aValue).getTime()/10000));
				break;
			/*case 7:
				cList.get(rowIndex).setNumberOfDayInvoice((Double) aValue);
				break;*/
			case 7:
				cList.get(rowIndex).setDailyRate((String) aValue);
				break;
			case 8:
				cList.get(rowIndex).setDailyIndemnity(aValue.toString());
				break;
			case 9:
				cList.get(rowIndex).setOtherConditions((String) aValue);
				break;
			case 10:
				cList.get(rowIndex).setCountry((String) aValue);
				break;
			/*case 11:
				cList.get(rowIndex).setPaymentDueDate((int)(((Date) aValue).getTime()/10000));
				break;
			case 12:
				cList.get(rowIndex).setPaymentDate((int)(((Date) aValue).getTime()/10000));
				break;
			case 13:
				cList.get(rowIndex).setAmountTaxFree((Double) aValue);
				break;
			case 14:
				cList.get(rowIndex).setAmountWithTax((Double) aValue);
				break;*/
			default:
				break;
			}
			f.updateCase(cList.get(rowIndex));
		}
	}


	public void fireTableDataChanged(String s){
		cList = f.getCases(s);
		super.fireTableDataChanged();
	}
	
	public void fireTableDataChangedForThisEmployee(Employee e){
		cList = f.getCasesForThisEmployee(e);
		super.fireTableDataChanged();
	}
	
	public void fireTableDataChangedForThisEmployeeForCaseHistory(Employee e){
		cList = f.getHistoryForThisEmployee(e);
		super.fireTableDataChanged();
	}


	public ArrayList<Case> getCaseList() {
		return cList;
	}


	public void fireTableDataChangedForDeletedCase() {
		cList = f.getDeletedCases();
		super.fireTableDataChanged();
	}
	

}
