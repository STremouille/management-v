package model;

import java.util.ArrayList;
import java.util.Iterator;

import model.users.User;

public class Factory {
	private FactorySQLUtility sqlu;
	private User user;
	
	public Factory(){
		sqlu = new FactorySQLUtility();
	}

	public void init(){
		//sqlu.initDB();
	}
	
	public ArrayList<Employee> getEmployees(String filter) {
		return sqlu.getEmployeesList(filter);
	}
	

	public void addEmployee(Employee employee) {
		sqlu.insertEmployee(employee);
	}
	
	public void updateEmployee(Employee employee){
		sqlu.updateEmployee(employee);
	}
	
	public void deleteEmployee(Employee employee){
		sqlu.deleteEmployee(employee);
	}
	
	
	public void insertCase(Case caseToInsert, Employee e){
		if(e!=null)
			caseToInsert.seteRef(e.getId());
		else
			caseToInsert.seteRef(-1);
		sqlu.insertCase(caseToInsert);
	}

	public ArrayList<Case> getCases(String s) {
		return sqlu.getCaseList(s);
	}

	public ArrayList<Case> getCasesForThisEmployee(Employee e) {
		return sqlu.getCaseListForThisEmployee(e);
	}

	public void updateCase(Case case1) {
		sqlu.updateCase(case1);
	}

	public ArrayList<Employee> getEmployeeForThisCase(Case c) {
		return sqlu.getEmployeeForThisCase(c);
		
	}
	
	public Employee getEmployeeForThisCaseId(Case c){
		return sqlu.getEmployeeForThisCaseId(c);
	}
	
	public Employee getEmployeeForThisCaseIdWithHistory(Case c){
		return sqlu.getEmployeeForThisCaseIdWithHistory(c);
	}

	public boolean caseHasEmployee(Case case1) {
		return sqlu.caseHasEmployee(case1);
	}

	public void deleteCase(Case case1) {
		sqlu.deleteCase(case1);
	}

	public void deleteCaseId(Case case1) {
		sqlu.deleteCaseId(case1);
	}

	public void deleteEmployeeFromCase(Employee employee, Case c) {
		sqlu.deleteEmployeeFromCase(employee,c);
	}

	public ArrayList<Case> getHistoryForThisEmployee(Employee e) {
		return sqlu.getHistoryForThisEmployee(e);
	}

	public boolean thisEmployeeHasCase(Employee employee) {
		return sqlu.thisEmployeeHasCase(employee);
	}

	public void updateAllCase(Case case1,String oldNumber) {
		this.sqlu.updateAllCase(case1,oldNumber);
	}

	public void historiseCaseId(Case case1) {
		this.sqlu.historiseCaseId(case1);
	}

	public void unhistoriseCaseId(Case case1) {
		this.sqlu.unhistoriseCaseId(case1);
	}

	public ArrayList<Employee> getWhosNotWorking() {
		ArrayList<Employee> listEmployee = sqlu.getEmployeesList("");
		Iterator<Employee> it = listEmployee.iterator();
		
		ArrayList<Employee> res = new ArrayList<Employee>();
		
		while(it.hasNext()){
			Employee e = it.next();
			System.out.println(e.getFirstName()+" "+!sqlu.thisEmployeeHasCase(e));
			if(!sqlu.thisEmployeeHasCase(e)){
				res.add(e);
			}
		}
		return res;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean login(String login, String pwd) {
		if(sqlu.checkLogin(login,pwd)){
			return true;
		} else {
			return false;
		}
	}

	public void insertUser(String login, String mdp) {
		sqlu.insertLogin(login, mdp);
	}

	public ArrayList<User> getUserList() {
		return sqlu.getUserList();
	}

	public void updateUser(User u) {
		sqlu.updateLogin(u);
	}

	public void deleteUser(User userToDelete) {
		sqlu.deleteUser(userToDelete);
	}

	public User getUserFromLogin(String selectedItem) {
		return sqlu.getUserFromLogin(selectedItem);
	}

	public ArrayList<Employee> getDeletedEmployee() {
		return sqlu.getDeletedEmployeeList();
	}

	public void unDeleteEmployeeId(Employee employee) {
		sqlu.unDeleteEmployeeId(employee);
	}

	public ArrayList<Case> getDeletedCases() {
		return sqlu.getDeletedCases();
	}

	public void unDeleteCaseId(Case case1) {
		sqlu.unDeleteCaseId(case1);
	}

	public void deleteDefinitely(Case case1) {
		sqlu.deleteDefinitely(case1);
	}
	
	public void deleteDefinitely(Employee empl) {
		sqlu.deleteDefinitely(empl);
	}

	public ArrayList<Case> getContractsWithHistory(String string) {
		return sqlu.getContractsWithHistory(string);
	}
}
