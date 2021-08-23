package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.model.ConvertAnchor;

import antlr.Parser;
import modelo.Agenda;
import persistencia.dao.interfaz.PaisDAO;
import persistencia.dao.mysql.PaisDAOSQL;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaLocalidad;
import presentacion.vista.VentanaPais;
import presentacion.vista.VentanaPersona;
import presentacion.vista.VentanaPersonaEditar;
import presentacion.vista.VentanaProvincia;
import presentacion.vista.VentanaTipoContacto;
import presentacion.vista.Vista;
import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;
import groovyjarjarantlr4.v4.parse.ANTLRParser.id_return;

public class Controlador implements ActionListener
{
		private Vista vista;
		private List<PersonaDTO> personasEnTabla;
		private List<PaisDTO> paises;
		private VentanaPersona ventanaPersona; 
		private VentanaTipoContacto ventanaTipoContacto; 
		private VentanaProvincia ventanaProvincia; 
		private VentanaLocalidad ventanaLocalidad; 
		private VentanaPais ventanaPais;
		private VentanaPersonaEditar  ventanaPersonaEditar;
		
		private Agenda agenda;
		
		public Controlador(Vista vista, Agenda agenda)
		{
			this.vista = vista;
			this.vista.getBtnAgregar().addActionListener(a->ventanaAgregarPersona(a));
			this.vista.getBtnBorrar().addActionListener(s->borrarPersona(s));
			this.vista.getBtnReporte().addActionListener(r->mostrarReporte(r));
			this.vista.getBtnEditar().addActionListener(q->editarPersona(q));
			
			//-- Persona
			this.ventanaPersona = VentanaPersona.getInstance();
			this.ventanaPersona.getBtnAgregarPersona().addActionListener(p->guardarPersona(p));
			this.ventanaPersona.getComboPais().addActionListener(u->refreshProvinciaPersona(u));
			this.ventanaPersona.getComboProvincia().addActionListener(z->refreshLocalidadPersona(z));
			
			this.agenda = agenda;
			//-- Tipo de Contacto
			this.vista.getBtnContacto().addActionListener(b->ventanaAgregarTipoContacto(b));
			this.ventanaTipoContacto = VentanaTipoContacto.getInstance();
			this.ventanaTipoContacto.getBtnAgregarTipoContacto().addActionListener(c->guardarTipoContacto(c));
			//-- Tipo de Pais
			this.vista.getBtnPaisABM().addActionListener(d->ventanaAgregarPais(d));
			this.ventanaPais = VentanaPais.getInstance();
			this.ventanaPais.getBtnAgregarPais().addActionListener(e->guardarPais(e));
			
			//-- Tipo de Provincia
			this.vista.getBtnProvinciaABM().addActionListener(f->ventanaAgregarPrv(f));
			this.ventanaProvincia = VentanaProvincia.getInstance();
			this.ventanaProvincia.getBtnAgregarProvincia().addActionListener(g->guardarProvincia(g));
		
			//-- Tipo de Localidad
			this.vista.getBtnLocalidadABM().addActionListener(h->ventanaAgregarLocalidad(h));
			this.ventanaLocalidad = VentanaLocalidad.getInstance();
			this.ventanaLocalidad.getComboPais().addActionListener(j->refreshProvincia(j));
			this.ventanaLocalidad.getBtnAgregarLocalidad().addActionListener(i->guardarLocalidad(i));
			
			
			//-- EditarPersona
			this.ventanaPersonaEditar = VentanaPersonaEditar.getInstance();
			this.ventanaPersonaEditar.getBtnAgregarPersona().addActionListener(p->guardarPersona(p));
			this.ventanaPersonaEditar.getComboPais().addActionListener(u->refreshProvinciaPersona(u));

			
	
		}
		
	///--------- Localidad ---------
		
		private void refreshProvincia(ActionEvent j) {
			this.ventanaLocalidad.getComboProvincia().removeAllItems();
			List<ProvinciaDTO> listaProvincias = new ArrayList<ProvinciaDTO>();
			PaisDTO idPais = (PaisDTO) this.ventanaLocalidad.getComboPais().getSelectedItem();
			listaProvincias = agenda.obtenerPrvsPais(idPais.getIdPais());	
			for (ProvinciaDTO prv : listaProvincias) {
				this.ventanaLocalidad.getComboProvincia().addItem(new ProvinciaDTO(prv.getIdProvincia(), prv.getnombreProvincia(), prv.getIdPais()));
			}
			this.ventanaLocalidad.mostrarVentana();
		}

		private void ventanaAgregarLocalidad(ActionEvent a) {
			List<PaisDTO> listaPaises = new ArrayList<PaisDTO>();
			listaPaises = agenda.obtenerPaises();
			for (PaisDTO paisDTO : listaPaises) {
				this.ventanaLocalidad.getComboPais().addItem(new PaisDTO(paisDTO.getIdPais(), paisDTO.getnombrePais()));
			}
		}
		
		private void guardarLocalidad(ActionEvent g) {
			PaisDTO idPais = (PaisDTO) this.ventanaLocalidad.getComboPais().getSelectedItem();
			ProvinciaDTO idProv = (ProvinciaDTO) this.ventanaLocalidad.getComboProvincia().getSelectedItem();
			String NombreLocalida = this.ventanaLocalidad.getTxtLocalidad().getText();
			LocalidadDTO nuevaLocalidad = new LocalidadDTO(0, NombreLocalida, idProv.getIdProvincia());
			this.agenda.agregarLocaidad(nuevaLocalidad);
			this.refrescarTabla();
			this.ventanaLocalidad.cerrar();
		}
		///--------- Provincias ---------
		
		private void ventanaAgregarPrv(ActionEvent a) {
			List<PaisDTO> listaPaises = new ArrayList<PaisDTO>();
			listaPaises = agenda.obtenerPaises();
			for (PaisDTO paisDTO : listaPaises) {
				this.ventanaProvincia.getComboPais().addItem(new PaisDTO(paisDTO.getIdPais(), paisDTO.getnombrePais()));
			}
			this.ventanaProvincia.mostrarVentana();
		}
		
		private void guardarProvincia(ActionEvent g) {
			PaisDTO idPais = (PaisDTO) this.ventanaProvincia.getComboPais().getSelectedItem();
			String NombreProv = this.ventanaProvincia.getTxtNombreProvincia().getText();
			ProvinciaDTO nuevaPrv = new ProvinciaDTO(0, NombreProv ,idPais.getIdPais());
			this.agenda.agregarPrv(nuevaPrv);
			this.refrescarTabla();
			this.ventanaProvincia.cerrar();
		}
		
		///--------- Pais ---------
		
		private void ventanaAgregarPais(ActionEvent a) {
			this.ventanaPais.mostrarVentana();
		}
		
		private void guardarPais(ActionEvent c) {
			String nombrePais = this.ventanaPais.getBtnTextPais().getText();
			PaisDTO nuevoPais = new PaisDTO(0, nombrePais);
			this.agenda.agregarPais(nuevoPais);
			this.refrescarTabla();
			this.ventanaPais.cerrar();
		}
		///---------tipo de contacto ---------
	
		private void ventanaAgregarTipoContacto(ActionEvent a) {
			this.ventanaTipoContacto.mostrarVentana();
		}
		
		private void guardarTipoContacto(ActionEvent c) {
			String nombreTipoContacto = this.ventanaTipoContacto.getTxtNombreContacto().getText();
			TipoContactoDTO nuevoTipoContacto = new TipoContactoDTO(0, nombreTipoContacto);
			this.agenda.agregarTipoContacto(nuevoTipoContacto);
			this.refrescarTabla();
			this.ventanaTipoContacto.cerrar();
		}
		
		///--------- Persona
		
		private void ventanaAgregarPersona(ActionEvent a) {
			
		try {
			
			List<PaisDTO> listaPaises = new ArrayList<PaisDTO>();
			listaPaises = agenda.obtenerPaises();
			for (PaisDTO paisDTO : listaPaises) {
				this.ventanaPersona.getComboPais().addItem(new PaisDTO(paisDTO.getIdPais(), paisDTO.getnombrePais()));
			}	
						
			List<TipoContactoDTO> listaContactos = new ArrayList<TipoContactoDTO>();
			listaContactos = agenda.obtenerTipoContactos();
			for (TipoContactoDTO contactos : listaContactos) {
				this.ventanaPersona.getComboTipoContacto().addItem(new TipoContactoDTO(contactos.getIdTipoContacto(), contactos.getnombreTipoContacto()));
			}	
													

			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		this.ventanaPersona.mostrarVentana();
		

		}

	

		private void refreshProvinciaPersona(ActionEvent u) {
			
			this.ventanaPersona.getComboProvincia().removeAllItems();
			this.ventanaPersona.getComboLocalidad().removeAllItems();
			
			
		try {
			
			List<ProvinciaDTO> listaProvincias = new ArrayList<ProvinciaDTO>();
			PaisDTO idPais = (PaisDTO) this.ventanaPersona.getComboPais().getSelectedItem();
			listaProvincias = agenda.obtenerPrvsPais(idPais.getIdPais());	
			for (ProvinciaDTO prv : listaProvincias) {
				this.ventanaPersona.getComboProvincia().addItem(new ProvinciaDTO(prv.getIdProvincia(), prv.getnombreProvincia(), prv.getIdPais()));
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
						

			

		}
		
		
		private void refreshLocalidadPersona(ActionEvent z) {
			
			this.ventanaPersona.getComboLocalidad().removeAllItems();
			
		try {
			

			
			List<LocalidadDTO> listaLocalidades = new ArrayList<LocalidadDTO>();
			ProvinciaDTO idPrv = (ProvinciaDTO) this.ventanaPersona.getComboProvincia().getSelectedItem();		
			listaLocalidades = agenda.obtenerLocalidadProv(idPrv.getIdProvincia());	
			for (LocalidadDTO lcl : listaLocalidades) {
				this.ventanaPersona.getComboLocalidad().addItem(new LocalidadDTO(lcl.getId(), lcl.getNombreLocalida(), lcl.getIdProvincia()));
			}			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
			
						
	
								
		}
		
		
		private void guardarPersona(ActionEvent p) {
			String nombre = this.ventanaPersona.getTxtNombre().getText();
			String tel = ventanaPersona.getTxtTelefono().getText();
						
			String calle = ventanaPersona.getTxtCalle().getText();
			String altura = ventanaPersona.getTxtAltura().getText();
			String piso = ventanaPersona.getTxtPiso().getText();
			String depto = ventanaPersona.getTxtDepto().getText();
			String email = ventanaPersona.getTxtEmail().getText();
			String fCumple = ventanaPersona.getTxtFCumple().getText();

			
			int alturaInt = Integer.parseInt(altura);
			int pisoInt = Integer.parseInt(piso);
			
			LocalidadDTO idLocalidad = (LocalidadDTO) this.ventanaPersona.getComboLocalidad().getSelectedItem();
			TipoContactoDTO idTipoContacto = (TipoContactoDTO) this.ventanaPersona.getComboTipoContacto().getSelectedItem();
						
			PersonaDTO nuevaPersona = new PersonaDTO(0, nombre, tel, calle, alturaInt, pisoInt, depto, email, fCumple, idLocalidad.getId(), idTipoContacto.getIdTipoContacto());
			this.agenda.agregarPersona(nuevaPersona);
			this.refrescarTabla();
			this.ventanaPersona.cerrar();
		}

		private void mostrarReporte(ActionEvent r) {
			ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
			reporte.mostrar();	
		}

		public void borrarPersona(ActionEvent s)
		{
			int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
			for (int fila : filasSeleccionadas)
			{
				this.agenda.borrarPersona(this.personasEnTabla.get(fila));
			}
			
			this.refrescarTabla();
		}
		

		
		public void inicializar()
		{
			this.refrescarTabla();
			this.vista.show();
		}
		
		private void refrescarTabla()
		{
			this.personasEnTabla = agenda.obtenerPersonas();
			this.vista.llenarTabla(this.personasEnTabla);
		}

		@Override
		public void actionPerformed(ActionEvent e) { }
		
		
		
	// -----------Editar Persona
		
		
		public void editarPersona(ActionEvent t)
		{
			
try {
	int filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRow();
	
	int id = this.personasEnTabla.get(filasSeleccionadas).getIdPersona();
	
	this.ventanaPersonaEditar.getTxtNombre().setText(this.personasEnTabla.get(filasSeleccionadas).getNombre());
	this.ventanaPersonaEditar.getTxtTelefono().setText(this.personasEnTabla.get(filasSeleccionadas).getTelefono());
	this.ventanaPersonaEditar.getTxtCalle().setText(this.personasEnTabla.get(filasSeleccionadas).getCalle());
//	this.ventanaPersonaEditar.getTxtAltura().setText(this.personasEnTabla.get(filasSeleccionadas).to);
	this.ventanaPersonaEditar.getTxtEmail().setText(this.personasEnTabla.get(filasSeleccionadas).getDepto());
	this.ventanaPersonaEditar.getTxtFCumple().setText(this.personasEnTabla.get(filasSeleccionadas).getEmail());

	
	
////	String alturaInt = this.personasEnTabla.get(filasSeleccionadas).getAltura());
////	int pisoInt = this.personasEnTabla.get(filasSeleccionadas).getPiso();
////	String depto = this.personasEnTabla.get(filasSeleccionadas).getDepto();
//	String email =this.personasEnTabla.get(filasSeleccionadas).getEmail();
//	String fCumple = this.personasEnTabla.get(filasSeleccionadas).getFCumple();
//	int idTipoContacto = this.personasEnTabla.get(filasSeleccionadas).getIdTipoContacto();
//	int idLocalidad = this.personasEnTabla.get(filasSeleccionadas).getIdLocalidad();
	
	this.ventanaPersonaEditar.mostrarVentana();
	this.refrescarTabla();
} catch (Exception e) {
	// TODO: handle exception
}
this.ventanaPersonaEditar.mostrarVentana();

		}

		
	
		
		
		public void guardarEditarPersona(ActionEvent t)
		{

			this.ventanaPersonaEditar.getTxtNombre().getText();
			this.ventanaPersonaEditar.getTxtTelefono().getText();
			this.ventanaPersonaEditar.getTxtCalle().getText();
//			this.ventanaPersonaEditar.getTxtAltura().getText();
			this.ventanaPersonaEditar.getTxtEmail().getText();
			this.ventanaPersonaEditar.getTxtFCumple().getText();

			this.ventanaPersonaEditar.mostrarVentana();
//			PersonaDTO nuevaPersona = new PersonaDTO(id, nombre, tel, calle, alturaInt, pisoInt, depto, email, fCumple, idTipoContacto , idLocalidad);			
//			this.agenda.editarPersona(nuevaPersona);
			this.refrescarTabla();
		}
		

		
}
