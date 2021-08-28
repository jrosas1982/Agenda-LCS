package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.TipoContactoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.TipoContactoDAO;

public class TipoContactoDAOSQL implements TipoContactoDAO
{
	private static final String insert = "INSERT INTO tipocontacto(id, nombre) VALUES(?, ?)";
	private static final String delete = "DELETE FROM tipocontacto WHERE id = ?";
	private static final String readall = "SELECT * FROM tipocontacto";
	private static final String update = "UPDATE tipocontacto SET nombre = ? WHERE id = ?";


	@Override
	public boolean insert(TipoContactoDTO tipoContacto)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, tipoContacto.getIdTipoContacto());
			statement.setString(2, tipoContacto.getnombreTipoContacto());
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
	public boolean delete(TipoContactoDTO tipoContacto_a_eliminar)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(tipoContacto_a_eliminar.getIdTipoContacto()));
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
	public List<TipoContactoDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<TipoContactoDTO> tipoContacto = new ArrayList<>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				tipoContacto.add(getTipoContactoDTO(resultSet));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return tipoContacto;
	}

	private TipoContactoDTO getTipoContactoDTO(ResultSet resultSet) throws SQLException
	{
		int idTipoContacto = resultSet.getInt("id");
		String nombre = resultSet.getString("Nombre");
		return new TipoContactoDTO(idTipoContacto, nombre);
	}

	@Override
	public boolean update(TipoContactoDTO tipoContacto) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdatedSuccess = false;
		try
		{
			statement = conexion.prepareStatement(update);
			statement.setString(1, tipoContacto.getnombreTipoContacto());
			statement.setInt(2, tipoContacto.getIdTipoContacto());
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
