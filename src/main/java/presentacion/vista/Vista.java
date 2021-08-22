package presentacion.vista;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.PersonaDTO;

import javax.swing.JButton;

import persistencia.conexion.Conexion;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Vista
{
	private JFrame frame;
	private JTable tablaPersonas;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnReporte;
	private JButton btnNewContacto;
	private DefaultTableModel modelPersonas;
	private  String[] nombreColumnas = {"Nombre y apellido","Telefono"};
	private JLabel lblGestion;
	private JButton btnLocalidaABM;
	private JButton btnProvinciaABM;
	private JButton btnPaisABM;

	public Vista() 
	{
		super();
		initialize();
	}


	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 809, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 426, 262);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(10, 11, 405, 182);
		panel.add(spPersonas);
		
		modelPersonas = new DefaultTableModel(null,nombreColumnas);
		tablaPersonas = new JTable(modelPersonas);
		
		tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaPersonas.getColumnModel().getColumn(0).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaPersonas.getColumnModel().getColumn(1).setResizable(false);
		
		spPersonas.setViewportView(tablaPersonas);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, 228, 89, 23);
		panel.add(btnAgregar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(109, 228, 89, 23);
		panel.add(btnEditar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(208, 228, 89, 23);
		panel.add(btnBorrar);
		
		btnReporte = new JButton("Reporte");
		btnReporte.setBounds(307, 228, 89, 23);
		panel.add(btnReporte);
		
		btnNewContacto = new JButton("ABM Tipo de Contacto");
		btnNewContacto.setBounds(436, 40, 147, 23);
		frame.getContentPane().add(btnNewContacto);
		
		lblGestion = new JLabel("Gestión");
		lblGestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestion.setBounds(436, 15, 347, 14);
		frame.getContentPane().add(lblGestion);
		
		btnLocalidaABM = new JButton("ABM Localida");
		btnLocalidaABM.setBounds(436, 74, 147, 23);
		frame.getContentPane().add(btnLocalidaABM);
		
		btnProvinciaABM = new JButton("ABM Provincia");
		btnProvinciaABM.setBounds(436, 108, 147, 23);
		frame.getContentPane().add(btnProvinciaABM);
		
		btnPaisABM = new JButton("ABM País");
		btnPaisABM.setBounds(436, 142, 147, 23);
		frame.getContentPane().add(btnPaisABM);
	}
	
	public void show()
	{
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() 
		{
			@Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "¿Estás seguro que quieres salir de la Agenda?", 
		             "Confirmación", JOptionPane.YES_NO_OPTION,
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		        if (confirm == 0) {
		        	Conexion.getConexion().cerrarConexion();
		           System.exit(0);
		        }
		    }
		});
		this.frame.setVisible(true);
	}
	
	//-- nuevo contacto
	public JButton getBtnContacto() 
	{
		return btnNewContacto;
	}
	//-- nuevo pais
	public JButton getBtnPaisABM() 
	{
		return btnPaisABM;
	}
	//-- nueva provincia
	public JButton getBtnProvinciaABM() 
	{
		return btnProvinciaABM;
	}
	//-- nueva Localidad
	public JButton getBtnLocalidadABM() 
	{
		return btnLocalidaABM;
	}
	//--
	public JButton getBtnAgregar() 
	{
		return btnAgregar;
	}

	public JButton getBtnBorrar() 
	{
		return btnBorrar;
	}
	
	public JButton getBtnReporte() 
	{
		return btnReporte;
	}
	
	public DefaultTableModel getModelPersonas() 
	{
		return modelPersonas;
	}
	
	public JTable getTablaPersonas()
	{
		return tablaPersonas;
	}

	public String[] getNombreColumnas() 
	{
		return nombreColumnas;
	}


	public void llenarTabla(List<PersonaDTO> personasEnTabla) {
		this.getModelPersonas().setRowCount(0); //Para vaciar la tabla
		this.getModelPersonas().setColumnCount(0);
		this.getModelPersonas().setColumnIdentifiers(this.getNombreColumnas());

		for (PersonaDTO p : personasEnTabla)
		{
			String nombre = p.getNombre();
			String tel = p.getTelefono();
			Object[] fila = {nombre, tel};
			this.getModelPersonas().addRow(fila);
		}
		
	}
}
