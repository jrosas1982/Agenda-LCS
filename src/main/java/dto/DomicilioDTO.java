package dto;

public class DomicilioDTO {

	private int altura;
	private String calle;
	private int piso;
	private String depto;
	private LocalidadDTO localidad;
	
	
	public DomicilioDTO(int altura, String calle, int piso, String depto, LocalidadDTO localidad) {

		this.altura = altura;
		this.calle = calle;
		this.piso = piso;
		this.depto = depto;
		this.localidad = localidad;
	}



	public int getAltura() {
		return altura;
	}



	public void setAltura(int altura) {
		this.altura = altura;
	}



	public String getCalle() {
		return calle;
	}



	public void setCalle(String calle) {
		this.calle = calle;
	}



	public int getPiso() {
		return piso;
	}



	public void setPiso(int piso) {
		this.piso = piso;
	}



	public String getDepto() {
		return depto;
	}



	public void setDepto(String depto) {
		this.depto = depto;
	}



	public LocalidadDTO getLocalidad() {
		return localidad;
	}



	public void setLocalidad(LocalidadDTO localidad) {
		this.localidad = localidad;
	}

	
}

