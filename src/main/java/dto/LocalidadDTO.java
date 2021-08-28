package dto;

public class LocalidadDTO {

	private int id;
	private String NombreLocalida;
	private int idProvincia;

	public LocalidadDTO(int id, String NombreLocalida, int idProvincia) {


		this.id = id;
		this.NombreLocalida = NombreLocalida;
		this.idProvincia = idProvincia;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombreLocalida() {
		return NombreLocalida;
	}
	public void setNombreLocalida(String nombreLocalida) {
		NombreLocalida = nombreLocalida;
	}
	public int getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}
	@Override
	public String toString() {
		return  NombreLocalida ;
	}
}
