package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

public class VentanaTipoContacto extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombreContacto;
	private JButton btnAgregarContacto;
	private static VentanaTipoContacto INSTANCE;
	
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
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 343, 143);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 307, 90);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombrePais = new JLabel("Nombre Tipo de Contacto");
		lblNombrePais.setBounds(10, 11, 136, 14);
		panel.add(lblNombrePais);
		
		txtNombreContacto = new JTextField();
		txtNombreContacto.setBounds(156, 8, 141, 20);
		panel.add(txtNombreContacto);
		txtNombreContacto.setColumns(10);
		
		btnAgregarContacto = new JButton("Agregar");
		btnAgregarContacto.setBounds(208, 49, 89, 23);
		panel.add(btnAgregarContacto);
		
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
}

