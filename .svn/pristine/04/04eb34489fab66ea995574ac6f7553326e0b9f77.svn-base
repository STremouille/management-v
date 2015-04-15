package view.employees;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import view.TableColumnAdjuster;
import model.Case;
import model.Employee;
import model.Factory;


public class EmployeeTableModel extends AbstractTableModel{

	
	
	private ArrayList<Employee> employeeList;
	private Factory f;
	private JTable table;
	
	public EmployeeTableModel(Factory f,ArrayList<Employee> employee,JTable table){
		this.employeeList = employee;
		this.f=f;
		this.table=table;
	}
	

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex==10){
			return Integer.class;
		}
		else{	
			
			if(getValueAt(0, columnIndex)==null)
			{
				return String.class;
			}
			else{
					return getValueAt(0, columnIndex).getClass();
			}
		}
	}

	@Override
	public int getColumnCount() {
		return 11;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "Disponible";
		case 1:
			return "Prénom";
		case 2:
			return "Nom";
		case 3:
			return "Téléphone";
		case 4:
			return "Téléphone Secondaire";
		case 5:
			return "Téléphone Client";
		case 6:
			return "E-mail";
		case 7:
			return "E-mail Secondaire";
		case 8:
			return "E-mail Ternaire";
		case 9:
			return "Statut";
		case 10:
			return "Affaires";
		default:
			return "Faux";
		}
	}

	@Override
	public int getRowCount() {
		return employeeList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(employeeList.size()>0){
		switch (columnIndex) {
		case 0:
			return employeeList.get(rowIndex).isAvailable();
		case 1:
			return employeeList.get(rowIndex).getFirstName();
		case 2:
			return employeeList.get(rowIndex).getLastName();
		case 3:
			return employeeList.get(rowIndex).getPhoneNumber();
		case 4:
			return employeeList.get(rowIndex).getPhoneNumberSecond();
		case 5:
			return employeeList.get(rowIndex).getPhoneNumberClient();
		case 6:
			return employeeList.get(rowIndex).getEmail();
		case 7:
			return employeeList.get(rowIndex).getEmailSecond();
		case 8:
			return employeeList.get(rowIndex).getEmailThird();
		case 9:
			return employeeList.get(rowIndex).getStatus();
		case 10:
			if(employeeList.get(rowIndex).getCases()!=null)
				return employeeList.get(rowIndex).getCases().size();
			else
				return 0;
		default:
			return "Faux";
		}
		}
		else
			return "Empty";
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return true;
		case 1:
			return true;
		case 2:
			return true;
		case 3:
			return true;
		case 4:
			return true;
		case 5:
			return true;
		case 6:
			return true;
		case 7:
			return true;
		case 8:
			return true;
		case 9:
			return true;
		default:
			return false;
		}
	}


	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			employeeList.get(rowIndex).setAvailable((Boolean) aValue);
			break;
		case 1:
			employeeList.get(rowIndex).setFirstName(aValue.toString());
			break;
		case 2:
			employeeList.get(rowIndex).setLastName(aValue.toString());
			break;
		case 3:
			employeeList.get(rowIndex).setPhoneNumber(aValue.toString());
			break;
		case 4:
			employeeList.get(rowIndex).setPhoneNumberSecond(aValue.toString());
			break;
		case 5:
			employeeList.get(rowIndex).setPhoneNumberClient(aValue.toString());
			break;
		case 6:
			employeeList.get(rowIndex).setEmail(aValue.toString());
			break;
		case 7:
			employeeList.get(rowIndex).setEmailSecond(aValue.toString());
			break;
		case 8:
			employeeList.get(rowIndex).setEmailThird(aValue.toString());
			break;
		case 9:
			employeeList.get(rowIndex).setStatus(aValue.toString());
			break;
		default:
			break;
		}
		f.updateEmployee(employeeList.get(rowIndex));
	}


	public void setEmployeeList(ArrayList<Employee> employee) {
		this.employeeList=employee;
	}


	public void fireTableDataChanged(String s) {
		employeeList = f.getEmployees(s);
		super.fireTableDataChanged();
	}


	public ArrayList<Employee> getEmployeeList() {
		return employeeList;
	}


	public void fireTableDataChanged(Case c) {
		employeeList = f.getEmployeeForThisCase(c);
		super.fireTableDataChanged();
	}


	public void fireTableDataChangedForDeletedEmployee() {
		employeeList = f.getDeletedEmployee();
		super.fireTableDataChanged();
	}

		
}
