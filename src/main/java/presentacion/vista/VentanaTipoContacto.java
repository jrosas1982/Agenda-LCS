package presentacion.vista;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dto.PersonaDTO;
import dto.TipoContactoDTO;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class VentanaTipoContacto extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombreContacto;
	private JButton btnAgregarContacto;
	private static VentanaTipoContacto INSTANCE;
	private DefaultTableModel modelTipoContacto;
	private  String[] nombreColumnas = {"ID", "Tipo de contacto"};
	private JTable table;
	private JButton btnEditar ;
	private JButton btnBorrar ;
	
	
	public static VentanaTipoContacto getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaTipoContacto();
			return new VentanaTipoContacto();
		}
		else
			return INSTANCE;
	}

	private VentanaTipoContacto()
	{
		super();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 442, 284);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 406, 223);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNombrePais = new JLabel("Nombre Tipo de Contacto");
		lblNombrePais.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombrePais.setBounds(10, 144, 386, 14);
		panel.add(lblNombrePais);

		txtNombreContacto = new JTextField();
		txtNombreContacto.setBounds(10, 158, 386, 20);
		panel.add(txtNombreContacto);
		txtNombreContacto.setColumns(10);

		btnAgregarContacto = new JButton("Agregar");
		btnAgregarContacto.setBounds(10, 189, 89, 23);
		panel.add(btnAgregarContacto);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 386, 122);
		panel.add(scrollPane);
		
		

		modelTipoContacto = new DefaultTableModel(null,nombreColumnas);
		table = new JTable(modelTipoContacto);
		table.getColumnModel().getColumn(0).setPreferredWidth(103);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setResizable(false);
		
		scrollPane.setViewportView(table);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(166, 189, 89, 23);
		panel.add(btnEditar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(307, 189, 89, 23);
		panel.add(btnBorrar);
		
		
		this.setVisible(false);
	}

	public void mostrarVentana()
	{
		this.setVisible(true);
	}

	public JTextField getTxtNombreContacto()
	{
		return txtNombreContacto;
	}
	public JButton getBtnAgregarTipoContacto()
	{
		return btnAgregarContacto;
	}

	public void cerrar()
	{
		this.txtNombreContacto.setText(null);
		this.dispose();
	}
	

	public DefaultTableModel getModelTipoContacto() {
		return modelTipoContacto;
	}

	public void setModelTipoContacto(DefaultTableModel modelTipoContacto) {
		this.modelTipoContacto = modelTipoContacto;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	public String[] getNombreColumnas()
	{
		return nombreColumnas;
	}
	
	public JButton getBtnEditar() {
		return btnEditar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public void setBtnEditar(JButton btnEditar) {
		this.btnEditar = btnEditar;
	}

	public void setBtnBorrar(JButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}

	public void llenarTabla(List<TipoContactoDTO> personasEnTabla) {
		this.getModelTipoContacto().setRowCount(0); //Para vaciar la tabla
		this.getModelTipoContacto().setColumnCount(0);
		this.getModelTipoContacto().setColumnIdentifiers(this.getNombreColumnas());

		for (TipoContactoDTO p : personasEnTabla)
		{
			int id = p.getIdTipoContacto();
			String nombre = p.getnombreTipoContacto();
			Object[] fila = {id, nombre};
			this.getModelTipoContacto().addRow(fila);
		}

	}
}

