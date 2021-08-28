package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.LocalidadDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.LocalidadDAO;

public class LocalidadDAOSQL implements LocalidadDAO
{
	private static final String insert = "INSERT INTO localidad(id, nombre, idProvincia) VALUES(? , ? ,?)";
	private static final String delete = "DELETE FROM localidad WHERE id = ?";
	private static final String readall = "SELECT * FROM localidad";
	private static final String update = "UPDATE localidad SET nombre = ? WHERE id = ?";
	private static final String readallbyidprov = "SELECT * FROM localidad WHERE idProvincia = ?";
	private static final String readallbyidLoc = "SELECT * FROM localidad WHERE id = ?";

	@Override
	public boolean insert(LocalidadDTO localidad)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, localidad.getId());
			statement.setString(2, localidad.getNombreLocalida());
			statement.setInt(3, localidad.getIdProvincia());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return isInsertExitoso;
	}

	@Override
	public boolean delete(LocalidadDTO localidad_a_eliminar)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(localidad_a_eliminar.getId()));
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isdeleteExitoso = true;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}

	@Override
	public List<LocalidadDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<LocalidadDTO> localidades = new ArrayList<>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				localidades.add(getLocalidadDTO(resultSet));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return localidades;
	}

	private LocalidadDTO getLocalidadDTO(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("id");
		String nombre = resultSet.getString("Nombre");
		int idProvincia = resultSet.getInt("idProvincia");
		return new LocalidadDTO(id, nombre,idProvincia);
	}

	@Override
	public boolean update(LocalidadDTO localidad) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdatedSuccess = false;
		try
		{
			statement = conexion.prepareStatement(update);
			statement.setString(1, localidad.getNombreLocalida());
			statement.setInt(2, localidad.getId());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isUpdatedSuccess = true;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return isUpdatedSuccess;
	}

	@Override
	public List<LocalidadDTO>readAllById(int idProvincia) {

		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<LocalidadDTO> localidades = new ArrayList<>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readallbyidprov);
			statement.setInt(1, idProvincia);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				localidades.add(getLocalidadDTO(resultSet));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return localidades;
	}


	public List<LocalidadDTO>readAllByIdLocalidad(int idLocalidad) {

		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<LocalidadDTO> localidades = new ArrayList<>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readallbyidLoc);
			statement.setInt(1, idLocalidad);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				localidades.add(getLocalidadDTO(resultSet));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return localidades;
	}





}
