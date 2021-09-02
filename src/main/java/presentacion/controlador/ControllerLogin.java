package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.log4j.Logger;

import main.Main;
import modelo.Agenda;
import persistencia.conexion.Conexion;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.Login;
import presentacion.vista.Vista;

public class ControllerLogin implements ActionListener{

	private Login login;
	
	private Logger log = Logger.getLogger(Conexion.class);

	private boolean conectado;
	
	
	
	public ControllerLogin(Login login) {
		
		this.login = login;
		
		this.login = login.getInstance();
		this.login.getBtnAcceder().addActionListener(x -> acceder(x));
		
	}


	public void inicializar()
	{	
		this.login.setVisible(true);
	}


	private void  acceder(ActionEvent x) {
		// TODO Auto-generated method stub
		String Pass = login.getTxtPass().getText();
		String User = login.getTextUser().getText();
		String Host = login.getTextHost().getText();
		log.info("Conexión exitosa" + Host + User + Pass );	
		try {
			Conexion.getConexionLogin(Host, User, Pass);		
			this.login.dispose();
			iniciarApp();
		} catch (Exception e) {
			// TODO: handle exception
			this.login.dispose();
		}
		//Conexion.getConexionLogin(Host, User, Pass);
		
		//iniciarApp();
		
	}


private void iniciarApp() {
	
		Vista vista = new Vista();
		Agenda modelo = new Agenda(new DAOSQLFactory());
		Controlador controlador = new Controlador(vista, modelo);
		controlador.inicializar();	
}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


	public boolean isConectado() {
		return conectado;
	}


	public void setConectado(boolean conectado) {
		this.conectado = conectado;
	}

	
	
	
	
}
