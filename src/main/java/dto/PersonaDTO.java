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

	public PersonaDTO(int idPersona, String nombre, String telefono, String calle, int altura, int piso, String depto, String email, String fCumple, int idLocalidad, int idTipoContacto)
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
	
	
	
}
