package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.PaisDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PaisDAO;

public class PaisDAOSQL implements PaisDAO
{
	private static final String insert = "INSERT INTO pais(id, nombre) VALUES(?, ?)";
	private static final String delete = "DELETE FROM pais WHERE id = ?";
	private static final String readall = "SELECT * FROM pais";
	private static final String update = "UPDATE pais SET nombre = ? WHERE id = ?";

	@Override
	public boolean insert(PaisDTO pais)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, pais.getIdPais());
			statement.setString(2, pais.getnombrePais());
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
	public boolean delete(PaisDTO pais_a_eliminar)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(pais_a_eliminar.getIdPais()));
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
	public List<PaisDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PaisDTO> paises = new ArrayList<>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				paises.add(getPaisDTO(resultSet));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return paises;
	}

	private PaisDTO getPaisDTO(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("id");
		String nombre = resultSet.getString("Nombre");
		return new PaisDTO(id, nombre);
	}

	@Override
	public boolean update(PaisDTO pais) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdatedSuccess = false;
		try
		{
			statement = conexion.prepareStatement(update);
			statement.setString(1, pais.getnombrePais());
			statement.setInt(2, pais.getIdPais());
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
}
