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
		
		private Agenda agenda;
		
		public Controlador(Vista vista, Agenda agenda)
		{
			this.vista = vista;
			this.vista.getBtnAgregar().addActionListener(a->ventanaAgregarPersona(a));
			this.vista.getBtnBorrar().addActionListener(s->borrarPersona(s));
			this.vista.getBtnReporte().addActionListener(r->mostrarReporte(r));
			//-- Persona
			this.ventanaPersona = VentanaPersona.getInstance();
			this.ventanaPersona.getBtnAgregarPersona().addActionListener(p->guardarPersona(p));
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
			this.ventanaPersona.mostrarVentana();
		}

		private void guardarPersona(ActionEvent p) {
			String nombre = this.ventanaPersona.getTxtNombre().getText();
			String tel = ventanaPersona.getTxtTelefono().getText();
			PersonaDTO nuevaPersona = new PersonaDTO(0, nombre, tel);
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
		
		public void editarPersona(ActionEvent s)
		{
			int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
			this.personasEnTabla.get(filasSeleccionadas[0]);
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
		
}
