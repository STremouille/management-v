package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Iterator;

import javax.swing.JOptionPane;

import view.LoginFrame;
import view.MainView;
import model.Case;
import model.Employee;
import model.Factory;
import model.users.User;

public class LoginController {

	private Factory factory;
	private LoginFrame lf;
	private MainView mv;
	
	public LoginController(Factory f,LoginFrame lf,MainView mv){
		this.factory=f;
		this.lf=lf;
		this.mv=mv;
		
		lf.addConnectListener(new ConnectListener());
	}
	
	class ConnectListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(factory.login(lf.getLogin(),lf.getPwd())){
				lf.dispose();
				mv.setVisible(true);
				
				contractWhistleBlower();
				
			} else {
				JOptionPane.showMessageDialog(lf, "Mauvais login/mot de passe");
			}
		}
		
		
		public void contractWhistleBlower(){
			//CONTRACT ALERT
			Iterator<Employee> it = factory.getEmployees("").iterator();
			while(it.hasNext()){
				Employee employee = it.next();
				Iterator<Case> itCaseForThisEmployee = factory.getCasesForThisEmployee(employee).iterator();
				while(itCaseForThisEmployee.hasNext()){
					Case c = itCaseForThisEmployee.next();
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(Calendar.getInstance().getTime());
					calendar.add(Calendar.MONTH, 2);
					
					if(c.getNumber().equals("FFF548423")){
						System.out.println(Long.valueOf(c.getEndDate()+"")*10000);
						System.out.println(calendar.getTime().getTime());
					}
					
					if(((Long.valueOf(c.getEndDate()+"")*10000)-calendar.getTime().getTime())<0){
						JOptionPane.showMessageDialog(mv.getFrame(), "Attention ! Le contrat relatif a l'affaire "+c.getNumber()+" pour l'employé "+employee.getFirstName()+" "+employee.getLastName()+" va arriver à échéance dans moins de 60 jours", "Contrat à terme", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		}
		
	}
}
