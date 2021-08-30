package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;
import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaLocalidad;
import presentacion.vista.VentanaPais;
import presentacion.vista.VentanaPersona;
import presentacion.vista.VentanaPersonaEditar;
import presentacion.vista.VentanaProvincia;
import presentacion.vista.VentanaTipoContacto;
import presentacion.vista.Vista;

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
		private List<TipoContactoDTO> contactoTabla;

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
			this.ventanaTipoContacto.getBtnBorrar().addActionListener(c->borrarTipoContacto(c));

			
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
			this.ventanaPersonaEditar.getBtnAgregarPersona().addActionListener(p->guardarEditarPersona(p));
			this.ventanaPersonaEditar.getComboPais().addActionListener(u->refreshProvinciaPersonaEditar(u));
			this.ventanaPersonaEditar.getComboProvincia().addActionListener(z->refreshLocalidadPersonaEditar(z));
//			this.ventanaPersonaEditar.getComboPais().addActionListener(u->refreshProvinciaPersona(u));



		}

	///--------- Localidad ---------

		private void refreshProvincia(ActionEvent j) {
			this.ventanaLocalidad.getComboProvincia().removeAllItems();
			List<ProvinciaDTO> listaProvincias = new ArrayList<>();
			PaisDTO idPais = (PaisDTO) this.ventanaLocalidad.getComboPais().getSelectedItem();
			listaProvincias = agenda.obtenerPrvsPais(idPais.getIdPais());
			for (ProvinciaDTO prv : listaProvincias) {
				this.ventanaLocalidad.getComboProvincia().addItem(new ProvinciaDTO(prv.getIdProvincia(), prv.getnombreProvincia(), prv.getIdPais()));
			}
			this.ventanaLocalidad.mostrarVentana();
		}

		private void ventanaAgregarLocalidad(ActionEvent a) {
			List<PaisDTO> listaPaises = new ArrayList<>();
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
			List<PaisDTO> listaPaises = new ArrayList<>();
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
			if(nombreTipoContacto.length() != 0 )
			{
				TipoContactoDTO nuevoTipoContacto = new TipoContactoDTO(0, nombreTipoContacto);
				this.agenda.agregarTipoContacto(nuevoTipoContacto);
			}
			this.ventanaTipoContacto.getTxtNombreContacto().setText("");
			this.refrescarTablaContacto();
		}
		


		///--------- Persona

		private void ventanaAgregarPersona(ActionEvent a) {

		try {

			List<PaisDTO> listaPaises = new ArrayList<>();
			listaPaises = agenda.obtenerPaises();
			for (PaisDTO paisDTO : listaPaises) {
				this.ventanaPersona.getComboPais().addItem(new PaisDTO(paisDTO.getIdPais(), paisDTO.getnombrePais()));
			}

			List<TipoContactoDTO> listaContactos = new ArrayList<>();
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

			List<ProvinciaDTO> listaProvincias = new ArrayList<>();
			PaisDTO idPais = (PaisDTO) this.ventanaPersona.getComboPais().getSelectedItem();
			listaProvincias = agenda.obtenerPrvsPais(idPais.getIdPais());
			for (ProvinciaDTO prv : listaProvincias) {
				this.ventanaPersona.getComboProvincia().addItem(new ProvinciaDTO(prv.getIdProvincia(), prv.getnombreProvincia(), prv.getIdPais()));
			}


		} catch (Exception e) {
			// TODO: handle exception
		}
		}
		
		private void refreshProvinciaPersonaEditar(ActionEvent u) {

			this.ventanaPersonaEditar.getComboProvincia().removeAllItems();
			this.ventanaPersonaEditar.getComboLocalidad().removeAllItems();


		try {

			List<ProvinciaDTO> listaProvincias = new ArrayList<>();
			PaisDTO idPais = (PaisDTO) this.ventanaPersonaEditar.getComboPais().getSelectedItem();
			listaProvincias = agenda.obtenerPrvsPais(idPais.getIdPais());
			for (ProvinciaDTO prv : listaProvincias) {
				this.ventanaPersonaEditar.getComboProvincia().addItem(new ProvinciaDTO(prv.getIdProvincia(), prv.getnombreProvincia(), prv.getIdPais()));
			}


		} catch (Exception e) {
			// TODO: handle exception
		}
		}

		private void refreshLocalidadPersona(ActionEvent z) {

			this.ventanaPersona.getComboLocalidad().removeAllItems();

		try {

			List<LocalidadDTO> listaLocalidades = new ArrayList<>();
			ProvinciaDTO idPrv = (ProvinciaDTO) this.ventanaPersona.getComboProvincia().getSelectedItem();
			listaLocalidades = agenda.obtenerLocalidadProv(idPrv.getIdProvincia());
			for (LocalidadDTO lcl : listaLocalidades) {
				this.ventanaPersona.getComboLocalidad().addItem(new LocalidadDTO(lcl.getId(), lcl.getNombreLocalida(), lcl.getIdProvincia()));
			}


		} catch (Exception e) {
			// TODO: handle exception
		}

		}
		
		private void refreshLocalidadPersonaEditar(ActionEvent z) {

			this.ventanaPersonaEditar.getComboLocalidad().removeAllItems();

		try {

			List<LocalidadDTO> listaLocalidades = new ArrayList<>();
			ProvinciaDTO idPrv = (ProvinciaDTO) this.ventanaPersonaEditar.getComboProvincia().getSelectedItem();
			listaLocalidades = agenda.obtenerLocalidadProv(idPrv.getIdProvincia());
			for (LocalidadDTO lcl : listaLocalidades) {
				this.ventanaPersonaEditar.getComboLocalidad().addItem(new LocalidadDTO(lcl.getId(), lcl.getNombreLocalida(), lcl.getIdProvincia()));
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
			String lugarTuristico = this.ventanaPersona.getTextFieldLTuristico().getText();


			String dominioEmail = getDominio(email);
			
			int alturaInt = Integer.parseInt(altura);
			int pisoInt = Integer.parseInt(piso);

			LocalidadDTO idLocalidad = (LocalidadDTO) this.ventanaPersona.getComboLocalidad().getSelectedItem();
			TipoContactoDTO idTipoContacto = (TipoContactoDTO) this.ventanaPersona.getComboTipoContacto().getSelectedItem();
			ProvinciaDTO idProvincia = (ProvinciaDTO) this.ventanaPersona.getComboProvincia().getSelectedItem();


			PersonaDTO nuevaPersona = new PersonaDTO(0, nombre, tel, calle, alturaInt, pisoInt, depto, email, fCumple, idLocalidad.getId(), idTipoContacto.getIdTipoContacto(), idProvincia.getIdProvincia(), idProvincia.getIdPais() , lugarTuristico, dominioEmail);
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
			this.refrescarTablaContacto();
			this.vista.show();
		}
		private void refrescarCombos()
		{
			this.ventanaLocalidad.getComboPais().removeAllItems();
			this.ventanaLocalidad.getComboProvincia().removeAllItems();
			this.ventanaProvincia.getComboPais().removeAllItems();
			this.ventanaPersonaEditar.getComboPais().removeAllItems();
			this.ventanaPersonaEditar.getComboProvincia().removeAllItems();
			this.ventanaPersonaEditar.getComboPais().removeAllItems();

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

				 PersonaDTO persona = new  PersonaDTO();
				 persona = this.agenda.buscarPersona(id);

				this.ventanaPersonaEditar.getTxtNombre().setText(persona.getNombre());
				this.ventanaPersonaEditar.getTxtTelefono().setText(persona.getTelefono());
				this.ventanaPersonaEditar.getTxtCalle().setText(persona.getCalle());
				this.ventanaPersonaEditar.getTxtAltura().setText(String.valueOf(persona.getAltura()));
				this.ventanaPersonaEditar.getTxtEmail().setText(persona.getEmail());
				this.ventanaPersonaEditar.getTxtFCumple().setText(persona.getFCumple());
				this.ventanaPersonaEditar.getTxtPiso().setText(String.valueOf(persona.getPiso()));
				this.ventanaPersonaEditar.getTxtDepto().setText(persona.getDepto());
				this.ventanaPersonaEditar.getTextFieldLTuristico().setText(persona.getLugarTuristico());
				
				List<PaisDTO> listaPaises = new ArrayList<>();
				listaPaises = agenda.obtenerPaises();
				for (PaisDTO paisDTO : listaPaises) {
					this.ventanaPersonaEditar.getComboPais().addItem(new PaisDTO(paisDTO.getIdPais(), paisDTO.getnombrePais()));
				}

				List<TipoContactoDTO> listaContactos = new ArrayList<>();
				listaContactos = agenda.obtenerTipoContactos();
				for (TipoContactoDTO contactos : listaContactos) {
					this.ventanaPersonaEditar.getComboTipoContacto().addItem(new TipoContactoDTO(contactos.getIdTipoContacto(), contactos.getnombreTipoContacto()));
				}
				this.ventanaPersonaEditar.getText_ID().setText(String.valueOf(id));
				this.ventanaPersonaEditar.mostrarVentana();
				this.refrescarTabla();

			} catch (Exception e) {
				// TODO: handle exception
			}
			this.ventanaPersonaEditar.mostrarVentana();

		}
		
		public String getDominio (String email) {
			return "hotmail.com";
			
		}
		
		
		
		
		public void guardarEditarPersona(ActionEvent t)
		{
			try {

			int id = Integer.parseInt(this.ventanaPersonaEditar.getText_ID().getText()) ;
			String nombre = this.ventanaPersonaEditar.getTxtNombre().getText();
			String tel = this.ventanaPersonaEditar.getTxtTelefono().getText();
			String calle = this.ventanaPersonaEditar.getTxtCalle().getText();
			String depto = this.ventanaPersonaEditar.getTxtDepto().getText();
			int alturaInt = Integer.parseInt(this.ventanaPersonaEditar.getTxtAltura().getText());
			String email = this.ventanaPersonaEditar.getTxtEmail().getText();
			String fCumple = this.ventanaPersonaEditar.getTxtFCumple().getText();
			int pisoInt = Integer.parseInt(this.ventanaPersonaEditar.getTxtPiso().getText());
			String lugarTuristico = this.ventanaPersonaEditar.getTextFieldLTuristico().getText();
			
			String dominioEmail = getDominio(email);
			
			TipoContactoDTO idTipoContacto = (TipoContactoDTO) this.ventanaPersonaEditar.getComboTipoContacto().getSelectedItem();

			LocalidadDTO idlocalida = (LocalidadDTO) this.ventanaPersonaEditar.getComboLocalidad().getSelectedItem();
			ProvinciaDTO idProv = (ProvinciaDTO) this.ventanaPersonaEditar.getComboProvincia().getSelectedItem();
			PaisDTO idPais = (PaisDTO) this.ventanaPersonaEditar.getComboPais().getSelectedItem();
			PersonaDTO nuevaPersona = new PersonaDTO(id, nombre, tel, calle, alturaInt, pisoInt, depto, email, fCumple, idTipoContacto.getIdTipoContacto() , idlocalida.getId(), idProv.getIdProvincia() , idPais.getIdPais(), lugarTuristico, dominioEmail);
			this.agenda.editarPersona(nuevaPersona);
			this.refrescarTabla();
			refrescarCombos();

			this.ventanaPersonaEditar.cerrar();
			} catch (Exception e) {
				// TODO: handle exception
			}
			this.refrescarTabla();
			this.ventanaPersonaEditar.cerrar();
		}
		

		private void refrescarTablaContacto()
		{
			this.contactoTabla = agenda.obtenerTipoContactos();
			this.ventanaTipoContacto.llenarTabla(contactoTabla);
		}
		
		public void borrarTipoContacto(ActionEvent s)
		{
			int[] filasSeleccionadas = this.ventanaTipoContacto.getTable().getSelectedRows();
			for (int fila : filasSeleccionadas)
			{
				this.agenda.borrarTipoContacto(this.contactoTabla.get(fila));
			}
			this.refrescarTablaContacto();
		}

}
