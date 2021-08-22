package persistencia.dao.interfaz;

import java.util.List;

import dto.ProvinciaDTO;

public interface ProvinciaDAO 
{
	
	public boolean insert(ProvinciaDTO prov);

	public boolean delete(ProvinciaDTO prov_a_eliminar);
	
	public List<ProvinciaDTO> readAll();
	
	public boolean update(ProvinciaDTO prov);
	
	public List<ProvinciaDTO> readAllById(int idPais);

	
}
