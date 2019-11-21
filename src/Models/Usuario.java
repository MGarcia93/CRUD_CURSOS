package Models;

public class Usuario{

	private String name;
	private String password;
	private int type;
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Usuario(String name, String password, int type) {
		super();
		this.name = name;
		this.password = password;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
