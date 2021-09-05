package main;

import presentacion.controlador.ControllerLogin;
import presentacion.vista.Login;


public class Main
{

	public static void main(String[] args)
	{
		Login login = new Login();
		ControllerLogin cl = new ControllerLogin(login);
		cl.inicializar();
//		Vista vista = new Vista();
//		Agenda modelo = new Agenda(new DAOSQLFactory());
//		Controlador controlador = new Controlador(vista, modelo);
//		controlador.inicializar();

	}
}
