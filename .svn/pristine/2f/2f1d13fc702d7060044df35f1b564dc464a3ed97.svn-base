package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import model.Factory;
import model.users.User;
import view.UserEditor;
import view.employees.UserComboBoxModel;

public class UserEditorController {
	
	private UserEditor view;
	private Factory f;
	
	
	public UserEditorController(UserEditor view,Factory f){
		this.view=view;
		this.f=f;
		
		view.addComboBoxListener(new ComboBoxListener());
		view.addApplyListener(new ApplyListener());
		view.addNewUserListener(new AddUserListener());
		view.addRemoveListener(new RemoveUserListener());
	}
	
	class ComboBoxListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String user = (String) view.getComboBox().getSelectedItem();
			view.getLoginTextField().setText(user);
			view.getPwdTextField().setText("");
		}
		
	}
	
	class ApplyListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println((String) view.getUserComboBoxModel().getSelectedItem());
			User u = f.getUserFromLogin((String) view.getUserComboBoxModel().getSelectedItem());
			u.setLogin(view.getLoginTextField().getText());
			u.setPwd(view.getPwdTextField().getText());
			f.updateUser(u);
			view.getComboBox().removeAllItems();
			view.getComboBox().setModel(new UserComboBoxModel(f));
		}
		
	}
	
	class AddUserListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			f.insertUser("New USER", "newMDP");
			view.getComboBox().removeAllItems();
			view.getComboBox().setModel(new UserComboBoxModel(f));
		}
		
	}

	class RemoveUserListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String userLogin=(String) view.getComboBox().getModel().getSelectedItem();
			ArrayList<User> UList = f.getUserList();
			User userToDelete = new User(-1, "", "");
			Iterator<User> it = UList.iterator();
			while(it.hasNext()){
				User tmp = it.next();
				if(tmp.getLogin().equals(userLogin)){
					userToDelete=tmp;
					break;
				}
			}
			f.deleteUser(userToDelete);
			view.getComboBox().removeAllItems();
			view.getComboBox().setModel(new UserComboBoxModel(f));
		}
		
	}
	
}
