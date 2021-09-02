package dto;

public class LoginDTO {

	private String Usuario;
	private String Pass;
	private String Server;
	
	public LoginDTO(String usuario, String pass, String server) {
		super();
		Usuario = usuario;
		Pass = pass;
		Server = server;
	}

	public String getUsuario() {
		return Usuario;
	}

	public String getPass() {
		return Pass;
	}

	public String getServer() {
		return Server;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public void setPass(String pass) {
		Pass = pass;
	}

	public void setServer(String server) {
		Server = server;
	}


}
