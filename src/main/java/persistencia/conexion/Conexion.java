package persistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class Conexion
{
	public static Conexion instancia;
	private Connection connection;
	private Logger log = Logger.getLogger(Conexion.class);
	private String Usuario;
	private String Pass;
	private String Server;
	private String Puerto;
	private String base;



	private Conexion()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); // quitar si no es necesario
			//this.connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/agenda?useSSL=false","root","Catupecu01-+");
			this.connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/agenda?useSSL=false","root","root");
//			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda","root","root");
			this.connection.setAutoCommit(false);
			log.info("Conexión exitosa");
		}
		catch(Exception e)
		{
			log.error("Conexión fallida", e);
		}
	}

	private Conexion(String server ,  String User , String pass)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); // quitar si no es necesario
			//this.connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/agenda?useSSL=false","root","Catupecu01-+");
			//this.connection = DriverManager.getConnection("jdbc:mysql://"+Server+":3306/agenda?useSSL=false",Usuario,Pass);
			this.connection = DriverManager.getConnection("jdbc:mysql://"+server+":3306/agenda?useSSL=false",User,pass);
			//this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda","root","root");
			this.connection.setAutoCommit(false);
			log.info("Conexión exitosa");
		}
		catch(Exception e)
		{
			log.error("Conexión fallida", e);
		}
	}

	public static Conexion getConexionLogin(String server ,  String User , String pass)
	{
		if(instancia == null)
		{
			instancia = new Conexion( server ,   User ,  pass);
		}
		return instancia;
	}

	public static Conexion getConexion()
	{
		if(instancia == null)
		{
			instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getSQLConexion()
	{
		return this.connection;
	}

	public void cerrarConexion()
	{
		try
		{
			this.connection.close();
			log.info("Conexion cerrada");
		}
		catch (SQLException e)
		{
			log.error("Error al cerrar la conexión!", e);
		}
		instancia = null;
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

	public String getPuerto() {
		return Puerto;
	}

	public String getBase() {
		return base;
	}

	public void setPuerto(String puerto) {
		Puerto = puerto;
	}

	public void setBase(String base) {
		this.base = base;
	}

}
