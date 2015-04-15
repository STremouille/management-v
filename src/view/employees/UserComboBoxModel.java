package view.employees;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListDataListener;

import model.Factory;
import model.users.User;

public class UserComboBoxModel extends  DefaultComboBoxModel{
	
	private Factory factory;
	private String selectedItem="";
	private int selectedItemIndex=0;
	
	public UserComboBoxModel(Factory factory) {
		this.factory=factory;
	}
	
	@Override
	public void addListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getElementAt(int arg0) {
		return factory.getUserList().get(arg0).getLogin();
	}

	@Override
	public int getSize() {
		System.out.println(factory.getUserList().size());
		return factory.getUserList().size();
	}

	@Override
	public void removeListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getSelectedItem() {
		return factory.getUserList().get(selectedItemIndex).getLogin();
	}

	@Override
	public void setSelectedItem(Object anItem) {
		ArrayList<User> userList = factory.getUserList();
		int acc=0;
		Iterator<User> it = userList.iterator();
		while(it.hasNext()){
			User u = it.next();
			if(u.getLogin().equals(anItem)){
				selectedItem=(String) anItem;
				selectedItemIndex= acc;
			}
			acc++;
		}
	}
	
	
	
	public void refresh() {
		selectedItem="";
		selectedItemIndex=0;
		/*revalidate();
		repaint();*/
	}
}
