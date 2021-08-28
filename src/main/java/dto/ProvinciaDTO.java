package dto;

public class ProvinciaDTO
{
	private int idProvincia;
	private String nombreProvincia;
	private int idPais;

	public ProvinciaDTO(int idProvincia, String nombreProvincia, int idPais)
	{
		this.idProvincia = idProvincia;
		this.nombreProvincia = nombreProvincia;
		this.idPais = idPais;
	}

	public int getIdProvincia()
	{
		return this.idProvincia;
	}

	public void setIdProvincia(int idProvincia)
	{
		this.idProvincia = idProvincia;
	}

	public String getnombreProvincia()
	{
		return this.nombreProvincia;
	}

	public void setNombreProvincia(String nombreProvincia)
	{
		this.nombreProvincia = nombreProvincia;
	}

	public int getIdPais() {
		return idPais;
	}

	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}
	@Override
	public String toString() {
		return  nombreProvincia;
	}

}
