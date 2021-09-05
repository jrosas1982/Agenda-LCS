package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.PersonaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PersonaDAO;

public class PersonaDAOSQL implements PersonaDAO
{
	private static final String insert = "INSERT INTO personas(idPersona, nombre, telefono, calle, altura, piso, depto, email, f_cumple , idTipoContacto, idLocalidad , idProvincia , idPais, lugarTur, dominioMail) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	private static final String readall = "SELECT * FROM personas";
	private static final String update = "UPDATE personas SET nombre = ?, telefono = ?, calle = ?, altura = ?, piso = ?, depto = ?, email = ?, f_cumple = ? , idTipoContacto = ?, idLocalidad = ? , idProvincia = ?  , idPais = ? , lugarTur = ?, dominioMail = ? WHERE idPersona = ?";

	private static final String getById= "  SELECT idPersona, ap.Nombre, Telefono, calle, altura, piso, f_cumple, email, depto, atip.nombre, al.nombre , apr.nombre , aps.nombre , lugarTur, dominioMail FROM agenda.personas ap\r\n"
			+ " left join   agenda.tipocontacto atip\r\n"
			+ " on  ap.idTipoContacto = atip.id\r\n"
			+ " left join agenda.localidad al\r\n"
			+ " on ap.idLocalidad = al.id \r\n"
			+ " left join agenda.provincia apr\r\n"
			+ " on ap.idProvincia = apr.id\r\n"
			+ " left join agenda.pais aps\r\n"
			+ " on ap.idPais = aps.id"
			+ " where idPersona = ?; ";



	private static final String readallLoc= " SELECT idPersona, ap.Nombre, Telefono, calle, altura, piso, f_cumple, email, depto, atip.nombre, al.nombre , apr.nombre , aps.nombre, lugarTur, dominioMail FROM agenda.personas ap\r\n"
			+ "	 left join   agenda.tipocontacto atip\r\n"
			+ "	 on  ap.idTipoContacto = atip.id\r\n"
			+ "	 left join agenda.localidad al\r\n"
			+ "	 on ap.idLocalidad = al.id \r\n"
			+ "	 left join agenda.provincia apr\r\n"
			+ "	 on ap.idProvincia = apr.id\r\n"
			+ "	 left join agenda.pais aps\r\n"
			+ "	 on ap.idPais = aps.id;";



	@Override
	public boolean insert(PersonaDTO persona)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, persona.getIdPersona());
			statement.setString(2, persona.getNombre());
			statement.setString(3, persona.getTelefono());

			statement.setString(4, persona.getCalle());
			statement.setInt(5, persona.getAltura());
			statement.setInt(6, persona.getPiso());
			statement.setString(7, persona.getDepto());
			statement.setString(8, persona.getEmail());
			statement.setString(9, persona.getFCumple());
			statement.setInt(10, persona.getIdTipoContacto());
			statement.setInt(11, persona.getIdLocalidad());
			statement.setInt(12, persona.getIdProvincia());
			statement.setInt(13, persona.getIdPais());
			statement.setString(14, persona.getLugarTuristico());
			statement.setString(15, persona.getDominioEmail());

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
	public boolean delete(PersonaDTO persona_a_eliminar)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(persona_a_eliminar.getIdPersona()));
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
	public List<PersonaDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readallLoc);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				personas.add(getPersonaDTO(resultSet));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return personas;
	}

	private PersonaDTO getPersonaDTO(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("idPersona");
		String nombre = resultSet.getString("Nombre");
		String tel = resultSet.getString("Telefono");
		String calle = resultSet.getString("Calle");
		int altura = resultSet.getInt("Altura");
		int piso = resultSet.getInt("Piso");
		String depto = resultSet.getString("Depto");
		String email = resultSet.getString("email");
		String fCumple = resultSet.getString("f_cumple");
		String nombreLocalidad = resultSet.getString("al.nombre");
		String nombreTipoContacto = resultSet.getString("atip.nombre");
		String nombreProvincia = resultSet.getString("apr.nombre");
		String nombrePais = resultSet.getString("aps.nombre");
		String lugatT = resultSet.getString("lugarTur");
		String dominioEmail = resultSet.getString("dominioMail");

		PersonaDTO retPersona =  new PersonaDTO(id, nombre, tel, calle, altura, piso, depto, email, fCumple, nombreLocalidad, nombreTipoContacto, nombreProvincia, nombrePais, lugatT, dominioEmail);

//		PersonaDTO retPersona =  new PersonaDTO(id, nombre, tel, calle, altura, piso, depto, email, fCumple, nombreLocalidad, nombreTipoContacto, nombreProvincia, nombrePais);
//		retPersona.setLugarTuristico(lugatT);
//		retPersona.setDominioEmail(dominioEmail);
		return retPersona;
	}

	@Override
	public boolean update(PersonaDTO persona) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdatedSuccess = false;
		try
		{
			statement = conexion.prepareStatement(update);
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getTelefono());
			statement.setString(3, persona.getCalle());
			statement.setInt(4, persona.getAltura());
			statement.setInt(5, persona.getPiso());
			statement.setString(6, persona.getDepto());
			statement.setString(7, persona.getEmail());
			statement.setString(8, persona.getFCumple());
			statement.setInt(9, persona.getIdTipoContacto());
			statement.setInt(10, persona.getIdLocalidad());
			statement.setInt(11, persona.getIdProvincia());
			statement.setInt(12, persona.getIdPais());
			statement.setString(13, persona.getLugarTuristico());
			statement.setString(14, persona.getDominioEmail());
			statement.setInt(15, persona.getIdPersona());


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
	public PersonaDTO geyById(int id) {

		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(getById);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				personas.add(getPersonaDTO(resultSet));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return personas.get(0);
	}

}
