package modelo;

import java.util.List;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PaisDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.ProvinciaDAO;
import persistencia.dao.interfaz.TipoContactoDAO;


public class Agenda 
{
	private PersonaDAO persona;	
	private TipoContactoDAO tipoContacto;
	private PaisDAO pais;
	private ProvinciaDAO prvs;
	private LocalidadDAO localidad;
	
	//---Agenda---
	public Agenda(DAOAbstractFactory metodo_persistencia)
	{
		this.persona = metodo_persistencia.createPersonaDAO();
		this.tipoContacto = metodo_persistencia.createTipoContactoDAO();
		this.pais = metodo_persistencia.createPaisDAO(); 
		this.prvs = metodo_persistencia.createProvinciaDAO();
		this.localidad = metodo_persistencia.createLocalidadDAO();
	}
	
	/// --- Persona
	
	public void agregarPersona(PersonaDTO nuevaPersona)
	{
		this.persona.insert(nuevaPersona);
	}

	public void borrarPersona(PersonaDTO persona_a_eliminar) 
	{
		this.persona.delete(persona_a_eliminar);
	}
	
	public void editarPersona(PersonaDTO persona_a_editar) 
	{
		this.persona.update(persona_a_editar);
	}
	
	public List<PersonaDTO> obtenerPersonas()
	{
		return this.persona.readAll();		
	}
	
	/// --- Tipo de contacto
		
	public void agregarTipoContacto(TipoContactoDTO nuevoTipoContacto)
	{
		this.tipoContacto.insert(nuevoTipoContacto);
	}

	public void borrarTipoContacto(TipoContactoDTO persona_a_eliminar) 
	{
		this.tipoContacto.delete(persona_a_eliminar);
	}
	
	public void editarTipoContacto(TipoContactoDTO persona_a_editar) 
	{
		this.tipoContacto.update(persona_a_editar);
	}
	
	public List<TipoContactoDTO> obtenerTipoContactos()
	{
		return this.tipoContacto.readAll();		
	}
	
	/// --- Pais
	
	public void agregarPais(PaisDTO nuevopais)
	{
		this.pais.insert(nuevopais);
	}

	public void borrarPais(PaisDTO pais_a_eliminar) 
	{
		this.pais.delete(pais_a_eliminar);
	}
	
	public void editarPais(PaisDTO pais_a_editar) 
	{
		this.pais.update(pais_a_editar);
	}
	
	public List<PaisDTO> obtenerPaises()
	{
		return this.pais.readAll();		
	}
	
	/// --- Provincias
	public void agregarPrv(ProvinciaDTO nuevopais)
	{
		this.prvs.insert(nuevopais);
	}

	public void borrarPrv(ProvinciaDTO prv_a_eliminar) 
	{
		this.prvs.delete(prv_a_eliminar);
	}
	
	public void editarPais(ProvinciaDTO prv_a_editar) 
	{
		this.prvs.update(prv_a_editar);
	}
	
	public List<ProvinciaDTO> obtenerPrvs()
	{
		return this.prvs.readAll();		
	}
	
	public List<ProvinciaDTO> obtenerPrvsPais(int idPais)
	{
		return this.prvs.readAllById(idPais);		
	}
	
	/// --- Localidad
	public void agregarLocaidad(LocalidadDTO nuevaLocalidad)
	{
		this.localidad.insert(nuevaLocalidad);
	}

	public void borrarLocalidad(LocalidadDTO localidad_a_eliminar) 
	{
		this.localidad.delete(localidad_a_eliminar);
	}
	
	public void editarPais(LocalidadDTO localidad_a_editar) 
	{
		this.localidad.update(localidad_a_editar);
	}
	
	public List<LocalidadDTO> obtenerLocalidad()
	{
		return this.localidad.readAll();		
	}
	
	public List<LocalidadDTO> obtenerLocalidadProv(int idProv)
	{
		return this.localidad.readAllById(idProv);		
	}
	
}
