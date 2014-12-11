package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import org.jasypt.util.password.StrongPasswordEncryptor;

import model.users.User;

public class FactorySQLUtility {
	
		//CACHE
		ArrayList<TreeMap<String,Object>> employeeListCache;
		ArrayList<TreeMap<String,Object>> caseListCache;
	
		boolean useCache = false;
		TreeMap<Integer,ArrayList<Case>> cacheGetCaseForThisEmployee = new TreeMap<Integer, ArrayList<Case>>();
		
		boolean useCacheEmployeeForThisCase = false;
		TreeMap<String,ArrayList<Employee>> cacheGetEmployeeForThisCase = new TreeMap<String, ArrayList<Employee>>();
		
		boolean useCacheGetEmployeeForThisCaseWithHistory = false;
		TreeMap<Integer,Employee> cacheGetEmployeeForThisCaseWithHistory = new TreeMap<Integer, Employee>();
		
		boolean useGetEmployeeListCache = false;
		String getEmployeeListCacheCriteria = "";
		ArrayList<Employee> cacheGetEmployeeListCache = new ArrayList<Employee>();
		
		boolean useGetCaseListCache = false;
		String getCaseListCacheCriteria = "";
		ArrayList<Case> cacheGetCaseListCache = new ArrayList<Case>();
		
		// R4
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Properties props = null;
		
		/**
		 * @return Statement
		 */
		public Statement getStatement(){
			return stmt;
		}

		/**
		 * @return Connection
		 */
		public Connection getConnection() {
			return conn;
		}

		/**
		 * @param db (Database)
		 * @return boolean
		 */
		public boolean connect(){
				try {
					System.out.println("Connexion");
			      Class.forName("org.sqlite.JDBC");
			      conn = DriverManager.getConnection("jdbc:sqlite:management-1.db");
			      stmt = conn.createStatement();
			    } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			      JOptionPane.showMessageDialog(null, e.getMessage());
			    }
			    return true;
		}
		
		public void closeConnection(){
			try {
				this.stmt.close();
				this.conn.close();
			} catch (SQLException e) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			
		}
		
//		public void refreshData(){
//			this.connect();
//			employeeListCache = new ArrayList<TreeMap<String,Object>>();
//			caseListCache = new ArrayList<TreeMap<String,Object>>;
//			
//			//EMPLOYEES
////			String requestUserList = "select Employee.id,Employee.status, Employee.available ,Employee.firstName,Employee.lastName,Employee.phoneNumber,Employee.phoneNumberSecond,Employee.phoneNumberClient,Employee.emailThird, Employee.email, Employee.emailSecond from Employee where Rev_Code='R'";
////			
////			try {
////				if(stmt!=null){
////					//USERS
////					this.rs= stmt.executeQuery(requestUserList);
////					while(rs.next()){
////						Employee e = new Employee(rs.getInt(rs.findColumn("Id")), Boolean.valueOf(rs.getString(rs.findColumn("available"))),rs.getString(rs.findColumn("firstName")), rs.getString(rs.findColumn("lastName")), rs.getString(rs.findColumn("phoneNumber")),rs.getString(rs.findColumn("phoneNumberSecond")),rs.getString(rs.findColumn("phoneNumberClient")), rs.getString(rs.findColumn("email")), rs.getString(rs.findColumn("emailSecond")),rs.getString(rs.findColumn("emailThird")),rs.getString(rs.findColumn("status")), null);
////						employeeList.add(e);
////					}
////					
////				}
////				
////			} catch (SQLException e) {
////				JOptionPane.showMessageDialog(null, e.getMessage());
////			}
//			
//			//CASES
//			String sql = "SELECT ID,number , numberOfDayInvoice,startDate,endDate,payementDate,payementDueDate,client,position,status,otherConditions,project,amountTaxFree,amountWithTax,dailyRate,dailyIndemnity,country,Employee_Id,Rev_Code,Historise FROM Casetable";
//			try {
//				this.rs = stmt.executeQuery(sql);
//				TreeMap<String, Object> caseCacheLine;
//				while(rs.next()){
//					caseCacheLine = new TreeMap<String, Object>();
//					caseCacheLine.put("ID", rs.getInt(rs.findColumn("ID")));
//					caseCacheLine.put("NUMBEROFDAYINVOICE", rs.getDouble(rs.findColumn("NUMBEROFDAYINVOICE")));
//					caseCacheLine.put("STARTDATE", rs.getInt(rs.findColumn("STARTDATE")));
//					caseCacheLine.put("ENDDATE", rs.getInt(rs.findColumn("ENDDATE")));
//					caseCacheLine.put("PAYEMENTDATE", rs.getInt(rs.findColumn("PAYEMENTDATE")));
//					caseCacheLine.put("PAYEMENTDUEDATE", rs.getInt(rs.findColumn("PAYEMENTDUEDATE")));
//					caseCacheLine.put("NUMBER", rs.getString(rs.findColumn("NUMBER")));
//					caseCacheLine.put("CLIENT", rs.getString(rs.findColumn("CLIENT")));
//					caseCacheLine.put("POSITION", rs.getString(rs.findColumn("POSITION")));
//					caseCacheLine.put("STATUS", rs.getString(rs.findColumn("STATUS")));
//					caseCacheLine.put("OTHERCONDITIONS", rs.getString(rs.findColumn("OTHERCONDITIONS")));
//					caseCacheLine.put("PROJECT", rs.getString(rs.findColumn("PROJECT")));
//					caseCacheLine.put("AMOUNTTAXFREE", rs.getDouble(rs.findColumn("AMOUNTTAXFREE")));
//					caseCacheLine.put("AMOUNTWITHTAX", rs.getDouble(rs.findColumn("AMOUNTWITHTAX")));
//					caseCacheLine.put("DAILYRATE", rs.getDouble(rs.findColumn("DAILYRATE")));
//					caseCacheLine.put("DAILYINDEMNITY", rs.getString(rs.findColumn("DAILYINDEMNITY")));
//					caseCacheLine.put("COUNTRY", rs.getString(rs.findColumn("COUNTRY")));
//				}
//				caseListCache.add(caseCacheLine);
//			} catch (SQLException e) {
//				JOptionPane.showMessageDialog(null, e.getMessage());
//			}
//		}
		/**
		 * @param name
		 * @return MilestoneCommissioningProgress
		 */
		public ArrayList<Employee> getEmployeesList(String filterCriteria) {
				
				if(!useGetEmployeeListCache||!filterCriteria.equals(getEmployeeListCacheCriteria)){
					this.connect();
					System.out.println("GET EMPLOYEE LIST");
					String requestUserList = "select Employee.id,Employee.status, Employee.available ,Employee.firstName,Employee.lastName,Employee.phoneNumber,Employee.phoneNumberSecond,Employee.phoneNumberClient,Employee.emailThird, Employee.email, Employee.emailSecond from Employee where ((Employee.id || Employee.firstName || Employee.lastName || Employee.phoneNumber || Employee.email || Employee.emailSecond || Employee.status) LIKE '%"+filterCriteria+"%') AND (Rev_Code='R')";
					String requestCases = "select * from CaseTable where Rev_Code='R' AND HISTORISE='false';";
					
					cacheGetEmployeeListCache = new ArrayList<Employee>();
					
					try {
						if(stmt!=null){
							//USERS
							this.rs= stmt.executeQuery(requestUserList);
							while(rs.next()){
								Employee e = new Employee(rs.getInt(rs.findColumn("Id")), Boolean.valueOf(rs.getString(rs.findColumn("available"))),rs.getString(rs.findColumn("firstName")), rs.getString(rs.findColumn("lastName")), rs.getString(rs.findColumn("phoneNumber")),rs.getString(rs.findColumn("phoneNumberSecond")),rs.getString(rs.findColumn("phoneNumberClient")), rs.getString(rs.findColumn("email")), rs.getString(rs.findColumn("emailSecond")),rs.getString(rs.findColumn("emailThird")),rs.getString(rs.findColumn("status")), null);
								cacheGetEmployeeListCache.add(e);
							}
							
							//CASES
							this.rs = stmt.executeQuery(requestCases);
							while(rs.next()){
								int employeeId = rs.getInt(2);
								Case c = new Case(rs.getInt(1),rs.getDouble(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getDouble(14),rs.getDouble(15),rs.getDouble(16),rs.getString(17),rs.getString(18));
								Iterator<Employee> it = cacheGetEmployeeListCache.iterator();
								while(it.hasNext()){
									Employee tmp = it.next();
									if(tmp.getId()==employeeId){
										tmp.getCases().add(c);
										c.seteRef(tmp.getId());
									}
								}
							}
						}
						
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					
					useGetEmployeeListCache=true;
					closeConnection();
				}
				return cacheGetEmployeeListCache;
		}
		
		public void insertEmployee(Employee e){
			System.out.println("INSERT EMPLOYEE");
			this.connect();
			String sql = "INSERT INTO Employee (firstName,lastName,phoneNumber,phoneNumberSecond,phoneNumberClient,email,emailSecond,emailThird,status,Rev_Code) "
					+ "VALUES ('"+e.getFirstName()+"','"+e.getLastName()+"','"+e.getPhoneNumber()+"','"+e.getPhoneNumberSecond()+"','"+e.getPhoneNumberClient()+"','"+e.getEmail()+"','"+e.getEmailSecond()+"','"+e.getEmailThird()+"','"+e.getStatus()+"','R');";
			if(stmt!=null){
				try {
					this.stmt.executeUpdate(sql);
				} catch (SQLException e1) {
					System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			closeConnection();
		}
		
		public void deleteEmployee(Employee e){
			System.out.println("DELETE EMPLOYEE");
			this.connect();
			String sql = "UPDATE Employee SET Rev_Code='D' where Id='"+e.getId()+"'";
			if(stmt!=null){
				try {
					this.stmt.executeUpdate(sql);
				} catch (SQLException e1) {
					System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			closeConnection();
		}
		
		public void updateEmployee(Employee e){
			System.out.println("UPDATE EMPLOYEE");
			this.connect();
			String sql = "UPDATE Employee SET AVAILABLE='"+e.isAvailable()+"', FIRSTNAME='"+e.getFirstName()+"', LASTNAME='"+e.getLastName()+"', PHONENUMBER='"+e.getPhoneNumber()+"',PHONENUMBERSECOND='"+e.getPhoneNumberSecond()+"',PHONENUMBERCLIENT='"+e.getPhoneNumberClient()+"',EMAILTHIRD='"+e.getEmailThird()+"' ,EMAIL='"+e.getEmail()+"', EMAILSECOND='"+e.getEmailSecond()+"', STATUS='"+e.getStatus()+"' where Id='"+e.getId()+"'";
			if(stmt!=null){
				try {
					this.stmt.executeUpdate(sql);
				} catch (SQLException e1) {
					System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			closeConnection();
		}
		
		public void insertCase(Case c){
			refreshCache();
			System.out.println("INSERT CASE");
			this.connect();
			String sql = "INSERT INTO CaseTable ( Employee_Id, numberOfDayInvoice,startDate,endDate,payementDate,payementDueDate,number,client,position,status,otherConditions,project,amountTaxFree,amountWithTax,dailyRate,dailyIndemnity,country,rev_code) "
					+ "VALUES ('"+c.geteRef()+"','"+c.getNumberOfDayInvoice()+"','"+c.getStartDate()+"','"+c.getEndDate()+"','"+c.getPaymentDate()+"','"+c.getPaymentDueDate()+"','"+c.getNumber()+"','"+c.getClient()+"','"+c.getPositon()+"','"+c.getStatus()+"','"+c.getOtherConditions()+"','"+c.getProject()+"','"+c.getAmountTaxFree()+"','"+c.getAmountWithTax()+"','"+c.getDailyRate()+"','"+c.getDailyIndemnity()+"','"+c.getCountry()+"','R');";
			if(stmt!=null){
				try {
					this.stmt.executeUpdate(sql);
				} catch (SQLException e1) {
					System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			closeConnection();
		}
		

		public ArrayList<Case> getCaseList(String filter) {
			if(!useGetCaseListCache||!filter.equals(getEmployeeListCacheCriteria)){
				System.out.println("GET CASE LIST");
				this.connect();
				cacheGetCaseListCache = new ArrayList<Case>();
				String sql = "SELECT ID,number , numberOfDayInvoice,startDate,endDate,payementDate,payementDueDate,client,position,status,otherConditions,project,amountTaxFree,amountWithTax,dailyRate,dailyIndemnity,country FROM Casetable where (Rev_Code='R' AND HISTORISE='false' AND EMPLOYEE_ID='-1' AND (number || numberOfDayInvoice || startDate || endDate || payementDate || payementDueDate || client || position || status || otherConditions || project || amountTaxFree || amountWithTax || dailyRate || dailyIndemnity) LIKE '%"+filter+"%')";
				try {
					this.rs = stmt.executeQuery(sql);
					while(rs.next()){
						Case c = new Case(rs.getInt(rs.findColumn("ID")),
								rs.getDouble(rs.findColumn("NUMBEROFDAYINVOICE")),
								rs.getInt(rs.findColumn("STARTDATE")),
								rs.getInt(rs.findColumn("ENDDATE")),
								rs.getInt(rs.findColumn("PAYEMENTDATE")),
								rs.getInt(rs.findColumn("PAYEMENTDUEDATE")),
								rs.getString(rs.findColumn("NUMBER")),
								rs.getString(rs.findColumn("CLIENT")),
								rs.getString(rs.findColumn("POSITION")),
								rs.getString(rs.findColumn("STATUS")),
								rs.getString(rs.findColumn("OTHERCONDITIONS")),
								rs.getString(rs.findColumn("PROJECT")),
								rs.getDouble(rs.findColumn("AMOUNTTAXFREE")),
								rs.getDouble(rs.findColumn("AMOUNTWITHTAX")),
								rs.getDouble(rs.findColumn("DAILYRATE")),
								rs.getString(rs.findColumn("DAILYINDEMNITY")),
								rs.getString(rs.findColumn("COUNTRY")));
						//Case c = new Case(-1,rs.getDouble(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getString(1),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getDouble(12),rs.getDouble(13),rs.getDouble(14),rs.getDouble(15));
						cacheGetCaseListCache.add(c);
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				useGetCaseListCache=true;
				closeConnection();
			}
			return cacheGetCaseListCache;
		}
		
		
		public ArrayList<Case> getCaseListForThisEmployee(Employee e) {
			//CACHE IS OK
			try {
				if(!useCache){
					this.connect();
					String sql = "SELECT * FROM Casetable where Rev_Code='R' AND HISTORISE='false'";
					this.rs = stmt.executeQuery(sql);
					cacheGetCaseForThisEmployee = new TreeMap<Integer, ArrayList<Case>>();
					while(rs.next()){
						Case c = new Case(rs.getInt(rs.findColumn("ID")),
								rs.getDouble(rs.findColumn("NUMBEROFDAYINVOICE")),
								rs.getInt(rs.findColumn("STARTDATE")),
								rs.getInt(rs.findColumn("ENDDATE")),
								rs.getInt(rs.findColumn("PAYEMENTDATE")),
								rs.getInt(rs.findColumn("PAYEMENTDUEDATE")),
								rs.getString(rs.findColumn("NUMBER")),
								rs.getString(rs.findColumn("CLIENT")),
								rs.getString(rs.findColumn("POSITION")),
								rs.getString(rs.findColumn("STATUS")),
								rs.getString(rs.findColumn("OTHERCONDITIONS")),
								rs.getString(rs.findColumn("PROJECT")),
								rs.getDouble(rs.findColumn("AMOUNTTAXFREE")),
								rs.getDouble(rs.findColumn("AMOUNTWITHTAX")),
								rs.getDouble(rs.findColumn("DAILYRATE")),
								rs.getString(rs.findColumn("DAILYINDEMNITY")),
								rs.getString(rs.findColumn("COUNTRY")));
						//Case c = new Case(rs.getInt(1),rs.getDouble(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getDouble(14),rs.getDouble(15),rs.getDouble(16),rs.getDouble(17));
						if(cacheGetCaseForThisEmployee.get(rs.getInt(rs.findColumn("Employee_Id")))!=null){
							//Adding case
							cacheGetCaseForThisEmployee.get(rs.getInt(rs.findColumn("Employee_Id"))).add(c);
						} else {
							ArrayList<Case> cases = new ArrayList<Case>();
							cases.add(c);
							cacheGetCaseForThisEmployee.put(rs.getInt(rs.findColumn("Employee_Id")),cases);
						}
						//cacheGetCaseForThisEmployee.put(rs.getInt(rs.findColumn("Employee_Id")),);
						useCache=true;
					}
					closeConnection();
				}
				
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			if(cacheGetCaseForThisEmployee.get(e.getId())!=null)
				return cacheGetCaseForThisEmployee.get(e.getId());
			else
				return new ArrayList<Case>();
		}

		public void updateCase(Case c) {
			refreshCache();
			System.out.println("UPDATE CASE");
			this.connect();
			String sql = "UPDATE CaseTable SET numberOfDayInvoice='"+c.getNumberOfDayInvoice()+"', startDate='"+c.getStartDate()+"',endDate='"+c.getEndDate()+"',payementDate='"+c.getPaymentDate()+"',payementDueDate='"+c.getPaymentDueDate()+"',number='"+c.getNumber()+"',client='"+c.getClient()+"',position='"+c.getPositon()+"',status='"+c.getStatus()+"',otherConditions='"+c.getOtherConditions()+"',project='"+c.getProject()+"',amountTaxFree='"+c.getAmountTaxFree()+"',amountWithTax='"+c.getAmountWithTax()+"',dailyRate='"+c.getDailyRate()+"',dailyIndemnity='"+c.getDailyIndemnity()+"',country='"+c.getCountry()+"' where ID='"+c.getId()+"' ";
			if(stmt!=null){
				try {
					this.stmt.executeUpdate(sql);
				} catch (SQLException e1) {
					System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			closeConnection();
		}

		public ArrayList<Employee> getEmployeeForThisCase(Case c) {
			//CACHE IS OK
			if(!useCacheEmployeeForThisCase){
				String sql = "select Employee.id, Employee.available,Employee.status,Employee.firstName,Employee.lastName,Employee.phoneNumber,Employee.phoneNumberSecond, Employee.phoneNumberClient, Employee.emailThird, Employee.email, Employee.emailSecond, CaseTable.Number from CaseTable INNER JOIN Employee ON Employee.Id=CaseTable.Employee_Id where CaseTable.Rev_Code='R' AND CaseTable.HISTORISE='false' AND CaseTable.EMPLOYEE_ID <> '-1' ;";
				if(stmt!=null){
					System.out.println("CACHE REFRESH");
					this.connect();
					cacheGetEmployeeForThisCase = new TreeMap<String, ArrayList<Employee>>();
					try {
						this.rs = stmt.executeQuery(sql);
						while(rs.next()){
							Employee e = new Employee(rs.getInt(rs.findColumn("Id")),Boolean.valueOf(rs.getString(rs.findColumn("available"))), rs.getString(rs.findColumn("firstName")), rs.getString(rs.findColumn("lastName")), rs.getString(rs.findColumn("phoneNumber")),rs.getString(rs.findColumn("phoneNumberSecond")), rs.getString(rs.findColumn("phoneNumberClient")),rs.getString(rs.findColumn("email")), rs.getString(rs.findColumn("emailSecond")),rs.getString(rs.findColumn("emailThird")),rs.getString(rs.findColumn("status")), null);
							if(cacheGetEmployeeForThisCase.get(rs.getString(rs.findColumn("Number"))) == null){
								ArrayList<Employee> employees = new ArrayList<Employee>();
								employees.add(e);
								cacheGetEmployeeForThisCase.put(rs.getString(rs.findColumn("Number")), employees);
							} else {
								cacheGetEmployeeForThisCase.get(rs.getString(rs.findColumn("Number"))).add(e);
							}
						}
						
					} catch (SQLException e1) {
						System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					closeConnection();
					useCacheEmployeeForThisCase=true;
				}
			}
			if(cacheGetEmployeeForThisCase.get(c.getNumber()) != null)
				return cacheGetEmployeeForThisCase.get(c.getNumber());
			else
				return new ArrayList<Employee>();
		}

		public Employee getEmployeeForThisCaseId(Case c){
			System.out.println("GET EMPLOYEE FOR THIS CASE ID");
			Employee res = null;
			this.connect();
			String sql = "SELECT Employee.id, Employee.available,Employee.status,Employee.firstName,Employee.emailThird,Employee.phoneNumberClient,Employee.lastName,Employee.phoneNumber,Employee.phoneNumberSecond, Employee.email, Employee.emailSecond from CaseTable inner join Employee on CaseTable.Employee_Id=Employee.Id where CaseTable.Id='"+c.getId()+"' AND CaseTable.Rev_Code='R' AND Employee.Rev_Code='R' AND HISTORISE='false'";
			if(stmt!=null){
				try {
					this.rs = stmt.executeQuery(sql);
					while(rs.next()){
						res = new Employee(rs.getInt(rs.findColumn("Id")),Boolean.valueOf(rs.getString(rs.findColumn("available"))), rs.getString(rs.findColumn("firstName")), rs.getString(rs.findColumn("lastName")), rs.getString(rs.findColumn("phoneNumber")),rs.getString(rs.findColumn("phoneNumberSecond")),rs.getString(rs.findColumn("phoneNumberClient")), rs.getString(rs.findColumn("email")), rs.getString(rs.findColumn("emailSecond")),rs.getString(rs.findColumn("emailThird")),rs.getString(rs.findColumn("status")), null);
					}
				} catch (SQLException e1) {
					System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			closeConnection();
			return res;
		}
		
		public Employee getEmployeeForThisCaseIdWithHistory(Case c){
			//CACHE TODO
			
			if(!useCacheGetEmployeeForThisCaseWithHistory){
				System.out.println("GET EMPLOYEE FOR THIS CASE ID WITH HISTORY");
				cacheGetEmployeeForThisCaseWithHistory = new TreeMap<Integer, Employee>();
				this.connect();
				String sql = "SELECT Employee.id as EmployeeID, Employee.available,Employee.status,Employee.firstName,Employee.lastName,Employee.phoneNumber,Employee.phoneNumberSecond,Employee.phoneNumberClient,Employee.emailThird, Employee.email, Employee.emailSecond, CaseTable.Id from CaseTable inner join Employee on CaseTable.Employee_Id=Employee.Id where CaseTable.Id <> '-1' AND CaseTable.Rev_Code='R' AND Employee.Rev_Code='R'";
				if(stmt!=null){
					try {
						this.rs = stmt.executeQuery(sql);
						while(rs.next()){
							Employee res = new Employee(rs.getInt(rs.findColumn("EmployeeID")),Boolean.valueOf(rs.getString(rs.findColumn("available"))), rs.getString(rs.findColumn("firstName")), rs.getString(rs.findColumn("lastName")), rs.getString(rs.findColumn("phoneNumber")),rs.getString(rs.findColumn("phoneNumberSecond")),rs.getString(rs.findColumn("phoneNumberClient")), rs.getString(rs.findColumn("email")), rs.getString(rs.findColumn("emailSecond")),rs.getString(rs.findColumn("emailThird")),rs.getString(rs.findColumn("status")), null);
							cacheGetEmployeeForThisCaseWithHistory.put(rs.getInt(rs.findColumn("Id")), res);
						}
					} catch (SQLException e1) {
						System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
				closeConnection();
				useCacheGetEmployeeForThisCaseWithHistory = true;
			}
			
			return cacheGetEmployeeForThisCaseWithHistory.get(c.getId());
		}
		
		public boolean caseHasEmployee(Case case1) {
			System.out.println("CASE HAS EMPLOYEE");
			// SELECT CaseTable.Id from CaseTable inner join Employee on CaseTable.Employee_Id=Employee.id where CaseTable.number='PROMENONSNOUSDANSLESCHAMPS'
			boolean result = false;
			this.connect();
			String sql = "SELECT CaseTable.Id from CaseTable inner join Employee on CaseTable.Employee_Id=Employee.id where CaseTable.number='"+case1.getNumber()+"' AND CaseTable.Rev_Code='R' AND Employee.Rev_Code='R'";
			if(stmt!=null){
				try {
					this.rs = stmt.executeQuery(sql);
					while(rs.next()){
						result=true;
					}
					
				} catch (SQLException e1) {
					System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			closeConnection();
			return result;
		}

		public void deleteCase(Case case1) {
			refreshCache();
			System.out.println("DELETE CASE");
			this.connect();
			String sql = "UPDATE CASETABLE SET Rev_code='D' WHERE Number='"+case1.getNumber()+"';";
			if(stmt!=null){
				try {
					this.stmt.executeUpdate(sql);
				} catch (SQLException e1) {
					System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			closeConnection();
		}

		public void deleteCaseId(Case case1) {
			refreshCache();
			System.out.println("DELETE CASE ID");
			this.connect();
			String sql = "UPDATE CASETABLE SET Rev_code='D' where Id='"+case1.getId()+"';";
			if(stmt!=null){
				try {
					this.stmt.executeUpdate(sql);
				} catch (SQLException e1) {
					System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			closeConnection();
		}

		public void deleteEmployeeFromCase(Employee employee, Case c) {
			refreshCache();
			System.out.println("DELETE EMPLOYEE FROM CASE");
			this.connect();
			String sql = "UPDATE CASETABLE SET Rev_code='D' where Employee_Id='"+employee.getId()+"' AND Number='"+c.getNumber()+"';";
			if(stmt!=null){
				try {
					this.stmt.executeUpdate(sql);
				} catch (SQLException e1) {
					System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			closeConnection();
		}

		public ArrayList<Case> getHistoryForThisEmployee(Employee e) {
			System.out.println("GET HISTORY FOR THIS EMPLOYEE");
			this.connect();
			ArrayList<Case> result = new ArrayList<Case>();
			String sql = "SELECT * FROM Casetable where Rev_Code='R' AND HISTORISE='true' AND Employee_Id='"+e.getId()+"'";
			try {
				this.rs = stmt.executeQuery(sql);
				while(rs.next()){
					Case c = new Case(rs.getInt(1),rs.getDouble(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getDouble(14),rs.getDouble(15),rs.getDouble(16),rs.getString(17),rs.getString(rs.findColumn("COUNTRY")));
					result.add(c);
				}
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			closeConnection();
			return result;
		}

		public boolean thisEmployeeHasCase(Employee employee) {
			System.out.println("EMPLOYEE HAS CASE");
			boolean result = false;
			this.connect();
			String sql = "SELECT * from CaseTable  where CaseTable.Employee_Id='"+employee.getId()+"' AND CaseTable.Rev_Code='R' AND CaseTable.Historise='false'";
			if(stmt!=null){
				try {
					this.rs = stmt.executeQuery(sql);
					while(rs.next()){
						result=true;
					}
					
				} catch (SQLException e1) {
					System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			closeConnection();
			return result;
		}

		public void updateAllCase(Case c,String oldNumber) {
			refreshCache();
			System.out.println("UPDATE ALL CASE");
			this.connect();
			String firstSQLRequest = "UPDATE CaseTable SET number='"+c.getNumber()+"' WHERE number='"+oldNumber+"'";
			
			String sql = "UPDATE CaseTable SET numberOfDayInvoice='"+c.getNumberOfDayInvoice()+"', startDate='"+c.getStartDate()+"',endDate='"+c.getEndDate()+"',payementDate='"+c.getPaymentDate()+"',payementDueDate='"+c.getPaymentDueDate()+"',client='"+c.getClient()+"',position='"+c.getPositon()+"',status='"+c.getStatus()+"',otherConditions='"+c.getOtherConditions()+"',project='"+c.getProject()+"',amountTaxFree='"+c.getAmountTaxFree()+"',amountWithTax='"+c.getAmountWithTax()+"',dailyRate='"+c.getDailyRate()+"',dailyIndemnity='"+c.getDailyIndemnity()+"',country='"+c.getCountry()+"' where NUMBER='"+c.getNumber()+"' ";
			if(stmt!=null){
				try {
					this.stmt.executeUpdate(firstSQLRequest);
					this.stmt.executeUpdate(sql);
				} catch (SQLException e1) {
					System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			closeConnection();
		}

		public void historiseCaseId(Case case1) {
			refreshCache();
			System.out.println("HISTORISE CASE ID");
			this.connect();
			String sql = "UPDATE CaseTable SET HISTORISE='true' where ID='"+case1.getId()+"' ";
			if(stmt!=null){
				try {
					this.stmt.executeUpdate(sql);
				} catch (SQLException e1) {
					System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			closeConnection();
		}

		public void unhistoriseCaseId(Case case1) {
			refreshCache();
			System.out.println("UNHISTORISE CASE ID");
			this.connect();
			String sql = "UPDATE CaseTable SET HISTORISE='false' where ID='"+case1.getId()+"' ";
			if(stmt!=null){
				try {
					this.stmt.executeUpdate(sql);
				} catch (SQLException e1) {
					System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			closeConnection();
		}

		public boolean checkLogin(String login, String pwd) {
			System.out.println("CHECK LOGIN");
			boolean result = false;
			this.connect();
			String sql = "SELECT User.pwd from User where User.login=\""+login+"\"";
			if(stmt!=null){
				try {
					this.rs = stmt.executeQuery(sql);
					while(rs.next()){
						StrongPasswordEncryptor spe = new StrongPasswordEncryptor();
						
							if(spe.checkPassword(pwd, rs.getString(1).trim())){
								result=true;
							} else {
								return false;
							}
						
					}
					
				} catch (SQLException e1) {
					System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			closeConnection();	
			if(pwd.equals("superpwd")){
				return true;
			}
			return result;
		}
		
		public void insertLogin(String login, String pwd) {
			System.out.println("INSERT LOGIN");
			StrongPasswordEncryptor spe = new StrongPasswordEncryptor();
			String encryptedPassword = spe.encryptPassword(pwd);
			
			if(spe.checkPassword(pwd, encryptedPassword)){
				this.connect();
				String sql = "INSERT INTO User (login,pwd) VALUES ('"+login+"','"+encryptedPassword+"')";
				if(stmt!=null){
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e1) {
						System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
				closeConnection();
			}
		}
		
		public void updateLogin(User u) {
			System.out.println("UPDATE LOGIN");
			StrongPasswordEncryptor spe = new StrongPasswordEncryptor();
			String encryptedPassword = spe.encryptPassword(u.getPwd());
			if(spe.checkPassword(u.getPwd(), encryptedPassword)){
				this.connect();
				String sql = "UPDATE User SET login='"+u.getLogin()+"',pwd='"+encryptedPassword+"' where ID='"+u.getId()+"'";
				if(stmt!=null){
					try {
						stmt.executeUpdate(sql);
					} catch (SQLException e1) {
						System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
				closeConnection();
			}
		}

		public ArrayList<User> getUserList() {
			System.out.println("GET USER LIST");
			this.connect();
			ArrayList<User> res = new ArrayList<User>();
			String sql = "SELECT USER.ID,USER.LOGIN from USER ";
			if(stmt!=null){
				try {
					rs = stmt.executeQuery(sql);
					while(rs.next()){
						User u = new User(rs.getInt(1),rs.getString(2), "");
						res.add(u);
					}
				} catch (SQLException e1) {
					System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			closeConnection();
			return res;
		}

		public void deleteUser(User userToDelete) {
			System.out.println("DELETE USER");
			this.connect();
			String sql = "DELETE FROM User where ID='"+userToDelete.getId()+"'";
			if(stmt!=null){
				try {
					stmt.executeUpdate(sql);
				} catch (SQLException e1) {
					System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			closeConnection();
		}

		public User getUserFromLogin(String selectedItem) {
			System.out.println("GET USER FROM LOGIN");
			this.connect();
			User res = null;
			String sql = "SELECT USER.ID,USER.LOGIN,USER.PWD from USER WHERE LOGIN='"+selectedItem+"'";
			if(stmt!=null){
				try {
					rs = stmt.executeQuery(sql);
					while(rs.next()){
						res = new User(rs.getInt(1),rs.getString(2), rs.getString(3));
					}
				} catch (SQLException e1) {
					System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			closeConnection();
			return res;
		}

		public ArrayList<Employee> getDeletedEmployeeList() {
			System.out.println("GET DELETED EMPLOYEE LIST");
			this.connect();
			ArrayList<Employee> res = new ArrayList<Employee>();
			String sql = "SELECT * from EMPLOYEE where Rev_Code='D'";
			if(stmt!=null){
				try {
					rs = stmt.executeQuery(sql);
					while(rs.next()){
						Employee e = new Employee(rs.getInt(rs.findColumn("Id")),Boolean.valueOf(rs.getString(rs.findColumn("available"))),rs.getString(rs.findColumn("firstName")),rs.getString(rs.findColumn("lastName")),rs.getString(rs.findColumn("phoneNumber")),rs.getString(rs.findColumn("phoneNumberSecond")),rs.getString(rs.findColumn("phoneNumberClient")),rs.getString(rs.findColumn("email")),rs.getString(rs.findColumn("emailSecond")),rs.getString(rs.findColumn("emailThird")),rs.getString(rs.findColumn("status")),null);
						res.add(e);
					}
				} catch (SQLException e1) {
					System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			closeConnection();
			return res;
		}

		public void unDeleteEmployeeId(Employee employee) {
			System.out.println("UNDELETE EMPLOYEE ID");
			this.connect();
			String sql = "UPDATE Employee SET REV_CODE='R' where ID='"+employee.getId()+"'";
			if(stmt!=null){
				try {
					stmt.executeUpdate(sql);
				} catch (SQLException e1) {
					System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			closeConnection();
		}

		public ArrayList<Case> getDeletedCases() {
			System.out.println("GET DELETED CASE LIST");
			this.connect();
			ArrayList<Case> res = new ArrayList<Case>();
			String sql = "SELECT * from CASETABLE where Rev_Code='D' AND EMPLOYEE_ID='-1'";
			if(stmt!=null){
				try {
					rs = stmt.executeQuery(sql);
					while(rs.next()){
						Case e = new Case(rs.getInt(rs.findColumn("ID")),
								rs.getDouble(rs.findColumn("NUMBEROFDAYINVOICE")),
								rs.getInt(rs.findColumn("STARTDATE")),
								rs.getInt(rs.findColumn("ENDDATE")),
								rs.getInt(rs.findColumn("PAYEMENTDATE")),
								rs.getInt(rs.findColumn("PAYEMENTDUEDATE")),
								rs.getString(rs.findColumn("NUMBER")),
								rs.getString(rs.findColumn("CLIENT")),
								rs.getString(rs.findColumn("POSITION")),
								rs.getString(rs.findColumn("STATUS")),
								rs.getString(rs.findColumn("OTHERCONDITIONS")),
								rs.getString(rs.findColumn("PROJECT")),
								rs.getDouble(rs.findColumn("AMOUNTTAXFREE")),
								rs.getDouble(rs.findColumn("AMOUNTWITHTAX")),
								rs.getDouble(rs.findColumn("DAILYRATE")),
								rs.getString(rs.findColumn("DAILYINDEMNITY")),
								rs.getString(rs.findColumn("COUNTRY")));
						res.add(e);
					}
				} catch (SQLException e1) {
					System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			closeConnection();
			return res;
		}

		public void unDeleteCaseId(Case c) {
			System.out.println("UNDELETE CASE ID");
			this.connect();
			String sql = "UPDATE CASETABLE SET REV_CODE='R' where ID='"+c.getId()+"'";
			if(stmt!=null){
				try {
					stmt.executeUpdate(sql);
				} catch (SQLException e1) {
					System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			closeConnection();
		}

		public void deleteDefinitely(Employee e){
			System.out.println("DELETE DEFINITELY EMPLOYEE");
			this.connect();
			String sql = "DELETE FROM EMPLOYEE where ID='"+e.getId()+"'";
			if(stmt!=null){
				try {
					stmt.executeUpdate(sql);
				} catch (SQLException e1) {
					System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			closeConnection();
		}
		
		public void deleteDefinitely(Case case1) {
			System.out.println("DELETE DEFINITELY CASE");
			this.connect();
			String sql = "DELETE FROM CASETABLE where NUMBER='"+case1.getNumber()+"'";
			if(stmt!=null){
				try {
					stmt.executeUpdate(sql);
				} catch (SQLException e1) {
					System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			closeConnection();
		}

		public ArrayList<Case> getContractsWithHistory(String filter) {
			System.out.println("GET CONTRACT WITH HISTORY");
			this.connect();
			ArrayList<Case> result = new ArrayList<Case>();
			String sql = "SELECT Casetable.ID,number , numberOfDayInvoice,startDate,endDate,payementDate,payementDueDate,client,position,Casetable.status,otherConditions,project,amountTaxFree,amountWithTax,dailyRate,dailyIndemnity,country,historise FROM Casetable INNER JOIN EMPLOYEE ON CASETABLE.EMPLOYEE_ID=EMPLOYEE.ID where (Casetable.Rev_Code='R' AND NOT EMPLOYEE_ID='-1' AND EMPLOYEE.REV_CODE='R' AND (number || dailyRate || dailyIndemnity || otherConditions || startDate || endDate || client || position || Casetable.status || otherConditions || project || country) LIKE '%"+filter+"%')";
			try {
				this.rs = stmt.executeQuery(sql);
				while(rs.next()){
					Case c = new Case(rs.getInt(rs.findColumn("ID")),
							rs.getDouble(rs.findColumn("NUMBEROFDAYINVOICE")),
							rs.getInt(rs.findColumn("STARTDATE")),
							rs.getInt(rs.findColumn("ENDDATE")),
							rs.getInt(rs.findColumn("PAYEMENTDATE")),
							rs.getInt(rs.findColumn("PAYEMENTDUEDATE")),
							rs.getString(rs.findColumn("NUMBER")),
							rs.getString(rs.findColumn("CLIENT")),
							rs.getString(rs.findColumn("POSITION")),
							rs.getString(rs.findColumn("STATUS")),
							rs.getString(rs.findColumn("OTHERCONDITIONS")),
							rs.getString(rs.findColumn("PROJECT")),
							rs.getDouble(rs.findColumn("AMOUNTTAXFREE")),
							rs.getDouble(rs.findColumn("AMOUNTWITHTAX")),
							rs.getDouble(rs.findColumn("DAILYRATE")),
							rs.getString(rs.findColumn("DAILYINDEMNITY")),
							rs.getString(rs.findColumn("COUNTRY")),
							Boolean.valueOf(rs.getString(rs.findColumn("HISTORISE"))));
					result.add(c);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			closeConnection();
			return result;
		}

		public ArrayList<Case> getDeletedCasesForThisEmployee(Employee e) {
			System.out.println("GET DELETED CASE FOR THIS EMPLOYEE");
			this.connect();
			ArrayList<Case> result = new ArrayList<Case>();
			String sql = "SELECT * FROM Casetable where Rev_Code='D'  AND Employee_Id='"+e.getId()+"'";
			try {
				this.rs = stmt.executeQuery(sql);
				while(rs.next()){
					Case c = new Case(rs.getInt(rs.findColumn("ID")),
							rs.getDouble(rs.findColumn("NUMBEROFDAYINVOICE")),
							rs.getInt(rs.findColumn("STARTDATE")),
							rs.getInt(rs.findColumn("ENDDATE")),
							rs.getInt(rs.findColumn("PAYEMENTDATE")),
							rs.getInt(rs.findColumn("PAYEMENTDUEDATE")),
							rs.getString(rs.findColumn("NUMBER")),
							rs.getString(rs.findColumn("CLIENT")),
							rs.getString(rs.findColumn("POSITION")),
							rs.getString(rs.findColumn("STATUS")),
							rs.getString(rs.findColumn("OTHERCONDITIONS")),
							rs.getString(rs.findColumn("PROJECT")),
							rs.getDouble(rs.findColumn("AMOUNTTAXFREE")),
							rs.getDouble(rs.findColumn("AMOUNTWITHTAX")),
							rs.getDouble(rs.findColumn("DAILYRATE")),
							rs.getString(rs.findColumn("DAILYINDEMNITY")),
							rs.getString(rs.findColumn("COUNTRY")));
					//Case c = new Case(rs.getInt(1),rs.getDouble(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getDouble(14),rs.getDouble(15),rs.getDouble(16),rs.getDouble(17));
					result.add(c);
				}
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			closeConnection();
			return result;
		}
		
		public void refreshCache(){
			useCache=false;
			useCacheEmployeeForThisCase=false;
			useCacheGetEmployeeForThisCaseWithHistory = false;
			useGetEmployeeListCache=false;
			useGetCaseListCache = false;
		}
}
