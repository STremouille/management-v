package model.users;

public class User {
	
	private String login,pwd;
	private int id;
	
	public User(int id,String login,String pwd){
		this.id=id;
		this.login=login;
		this.pwd=pwd;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String toString(){
		return "login :"+login;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
