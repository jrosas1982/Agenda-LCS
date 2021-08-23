package dto;

public class TipoContactoDTO 
{
	private int idTipoContacto;
	private String nombreTipoContacto;

	public TipoContactoDTO(int idTipoContacto, String nombreTipoContacto)
	{
		this.idTipoContacto = idTipoContacto;
		this.nombreTipoContacto = nombreTipoContacto;
	}
	
	public int getIdTipoContacto() 
	{
		return this.idTipoContacto;
	}

	public void setIdTipoContacto(int idTipoContacto) 
	{
		this.idTipoContacto = idTipoContacto;
	}

	public String getnombreTipoContacto() 
	{
		return this.nombreTipoContacto;
	}

	public void setNombre(String nombre) 
	{
		this.nombreTipoContacto = nombre;
	}

	@Override
	public String toString() {
		return  nombreTipoContacto ;
	}

}
