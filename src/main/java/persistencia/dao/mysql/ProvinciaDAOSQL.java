package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ProvinciaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ProvinciaDAO;


public class ProvinciaDAOSQL implements ProvinciaDAO
{
	private static final String insert = "INSERT INTO provincia(id, nombre,idPais) VALUES(?, ?, ?)";
	private static final String delete = "DELETE FROM provincia WHERE id = ?";
	private static final String readall = "SELECT * FROM provincia";
	private static final String update = "UPDATE provincia SET nombre = ? , idPais = ? WHERE id = ?";
	private static final String readallbyidpais = "SELECT * FROM provincia WHERE idPais = ?";

	@Override
	public boolean insert(ProvinciaDTO prv)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, prv.getIdProvincia());
			statement.setString(2, prv.getnombreProvincia());
			statement.setInt(3, prv.getIdPais());
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
	public boolean delete(ProvinciaDTO prv_a_eliminar)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(prv_a_eliminar.getIdProvincia()));
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
	public List<ProvinciaDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<ProvinciaDTO> prvs = new ArrayList<>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				prvs.add(getProvinciaDTO(resultSet));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return prvs;
	}

	private ProvinciaDTO getProvinciaDTO(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("id");
		String nombre = resultSet.getString("Nombre");
		int idPais = resultSet.getInt("idPais");
		return new ProvinciaDTO(id, nombre, idPais);
	}

	@Override
	public boolean update(ProvinciaDTO prv) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdatedSuccess = false;
		try
		{
			statement = conexion.prepareStatement(update);
			statement.setString(1, prv.getnombreProvincia());
			statement.setInt(2, prv.getIdPais());
			statement.setInt(3, prv.getIdProvincia());
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
	public List<ProvinciaDTO> readAllById(int idPais) {

		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<ProvinciaDTO> prvs = new ArrayList<>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readallbyidpais);
			statement.setInt(1, idPais);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				prvs.add(getProvinciaDTO(resultSet));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return prvs;
	}
}
