package dto;

public class PersonaDTO 
{


	private int idPersona;
	private String nombre;
	private String telefono;	
	private String calle;
	private int altura;
	private int piso;
	private String depto;	
	private String email;
	private String fCumple;
	private int idLocalidad;
	private int idTipoContacto;
	private String nombreLocalidad;
	private String nombreContacto;
	private int idProvincia;
	private int idPais;
	private String nombreProvincia;
	private String nombrePais;
	
	public PersonaDTO(int idPersona, String nombre, String telefono, String calle, int altura, int piso, String depto, String email, String fCumple, int idLocalidad, int idTipoContacto , int idProvincia , int idPais)
	{
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.depto = depto;
		this.email = email;
		this.fCumple = fCumple;
		this.idLocalidad = idLocalidad;
		this.idTipoContacto = idTipoContacto;
		this.idProvincia = idProvincia;
	}
	
	
	public PersonaDTO(int idPersona, String nombre, String telefono, String calle, int altura, int piso, String depto, String email, String fCumple, String nombreLocalidad, String nombreContacto , String nombreProvincia , String nombrePais)
	{
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.depto = depto;
		this.email = email;
		this.fCumple = fCumple;
		this.nombreLocalidad = nombreLocalidad;
		this.nombreContacto = nombreContacto;
		this.nombreProvincia = nombreProvincia;
		this.nombrePais = nombrePais;
	}
	
	public PersonaDTO() {
		
	}
	
	public int getIdPais() {
		return idPais;
	}


	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}

	
	public String getNombreLocalidad() {
		return nombreLocalidad;
	}


	public void setNombreLocalidad(String nombreLocalidad) {
		this.nombreLocalidad = nombreLocalidad;
	}


	public String getNombreContacto() {
		return nombreContacto;
	}


	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}


	public int getIdPersona() 
	{
		return this.idPersona;
	}


	
	public void setIdPersona(int idPersona) 
	{
		this.idPersona = idPersona;
	}

	public String getNombre() 
	{
		return this.nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getTelefono() 
	{
		return this.telefono;
	}

	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
		
	}
	
	public String getCalle() 
	{
		return this.calle;
	}

	public void setCalle(String calle) 
	{
		this.calle = calle;
	
	}
	public int getIdProvincia() {
		return idProvincia;
	}


	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}

	public void setAltura(int altura) 
	{
		this.altura = altura;
	}

	public int getAltura() 
	{
		return this.altura;
	}
	
	public void setPiso(int piso) 
	{
		this.piso = piso;
	}

	public int getPiso() 
	{
		return this.piso;
	}
	
	public String getDepto() 
	{
		return this.depto;
	}

	public void setDepto(String depto) 
	{
		this.depto = depto;
	
	}
	
	public int getIdLocalidad() 
	{
		return this.idLocalidad;
	}
	
	public void setIdLocalidad(int idLocalidad) 
	{
		this.idLocalidad = idLocalidad;
	}
	
	public String getFCumple() 
	{
		return this.fCumple;
	}

	public void setFcumple(String fCumple) 
	{
		this.fCumple = fCumple;
	
	}
	
	public String getEmail() 
	{
		return this.email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	
	}
	
	public int getIdTipoContacto() 
	{
		return this.idTipoContacto;
	}
	
	public void setIdTipoContacto(int idTipoContacto) 
	{
		this.idTipoContacto = idTipoContacto;
	}


	public String getNombreProvincia() {
		return nombreProvincia;
	}


	public String getNombrePais() {
		return nombrePais;
	}


	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}


	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}
	
	
	
}
