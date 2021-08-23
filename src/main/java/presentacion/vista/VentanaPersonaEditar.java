package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import dto.TipoContactoDTO;
import dto.PaisDTO;
import dto.ProvinciaDTO;
import dto.LocalidadDTO;

public class VentanaPersonaEditar extends JFrame 
{
	

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JButton btnAgregarPersona;
	private static VentanaPersonaEditar INSTANCE;
	private JTextField txtEmail;
	private JTextField txtCumpleaños;
	private JTextField txtCalle;
	private JTextField txtAltura;
	private JTextField txtPiso;
	private JTextField txtDepto;
	
	private JComboBox<ProvinciaDTO> comboProvincia;
	private JComboBox<PaisDTO> comboPais;
	private JComboBox<TipoContactoDTO> comboTipoContacto;
	private JComboBox<LocalidadDTO> comboLocalidad;	
	
	
	
	public static VentanaPersonaEditar getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaPersonaEditar(); 	
			return new VentanaPersonaEditar();
		}
		else
			return INSTANCE;
	}

	private VentanaPersonaEditar() 
	{
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 343, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 307, 411);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(10, 38, 113, 14);
		panel.add(lblTelefono);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 65, 113, 14);
		panel.add(lblEmail);
		
		JLabel lblFechaCumple = new JLabel("Fecha de Cumpleaños");
		panel.add(lblFechaCumple);
		
		JLabel lblTipoContacto = new JLabel("Tipo de Conatcto");
		panel.add(lblTipoContacto);
		
		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 11, 113, 14);
		panel.add(lblNombreYApellido);
		
		JLabel lblNewLabel = new JLabel("Fecha de Nacimiento");
		lblNewLabel.setBounds(10, 96, 113, 14);
		panel.add(lblNewLabel);
				
		JLabel lblTipContacto = new JLabel("Tipo de Contacto");
		lblTipContacto.setBounds(10, 132, 113, 14);
		panel.add(lblTipContacto);
		
		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(25, 173, 46, 14);
		panel.add(lblDomicilio);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(10, 212, 46, 14);
		panel.add(lblCalle);
		
		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(10, 237, 36, 14);
		panel.add(lblAltura);
		
		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(186, 237, 24, 14);
		panel.add(lblPiso);
		
		JLabel lblDepto = new JLabel("Depto");
		lblDepto.setBounds(242, 237, 29, 14);
		panel.add(lblDepto);
				
		JLabel lblPais = new JLabel("País");
		lblPais.setBounds(10, 274, 46, 14);
		panel.add(lblPais);
		
		JLabel lblProvincia= new JLabel("Provincia");
		lblProvincia.setBounds(10, 309, 94, 14);
		panel.add(lblProvincia);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, 342, 94, 14);
		panel.add(lblLocalidad);
				
		
		
		txtNombre = new JTextField();
		txtNombre.setBounds(114, 8, 183, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(114, 35, 183, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(114, 62, 183, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtCumpleaños = new JTextField();
		txtCumpleaños.setBounds(114, 93, 81, 20);
		panel.add(txtCumpleaños);
		txtCumpleaños.setColumns(10);
		
		txtCalle = new JTextField();
		txtCalle.setBounds(114, 209, 183, 20);
		panel.add(txtCalle);
		txtCalle.setColumns(10);
		
		txtAltura = new JTextField();
		txtAltura.setBounds(114, 234, 62, 20);
		panel.add(txtAltura);
		txtAltura.setColumns(10);
		
		txtPiso = new JTextField();
		txtPiso.setBounds(208, 234, 24, 20);
		panel.add(txtPiso);
		txtPiso.setColumns(10);
		
		txtDepto = new JTextField();
		txtDepto.setBounds(273, 234, 24, 20);
		panel.add(txtDepto);
		txtDepto.setColumns(10);
		

		comboTipoContacto = new JComboBox<TipoContactoDTO>();
		comboTipoContacto.setBounds(114, 128, 164, 18);
		panel.add(comboTipoContacto);
		
		comboPais = new JComboBox<PaisDTO>();
		comboPais.setBounds(114, 270, 164, 18);
		panel.add(comboPais);
		
		comboProvincia = new JComboBox<ProvinciaDTO>();
		comboProvincia.setBounds(114, 305, 164, 18);
		panel.add(comboProvincia);
		
		comboLocalidad = new JComboBox<LocalidadDTO>();
		comboLocalidad.setBounds(114, 338, 164, 18);
		panel.add(comboLocalidad);
		
		
		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.setBounds(208, 374, 89, 23);
		panel.add(btnAgregarPersona);
		
		
		this.setVisible(false);
	}
	
	public void mostrarVentana()
	{
		this.setVisible(true);
	}
	
	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}

	public JTextField getTxtTelefono() 
	{
		return txtTelefono;
	}


	public JTextField getTxtAltura() 
	{
		return txtAltura;
	}
	
	public JTextField getTxtPiso() 
	{
		return txtPiso;
	}
	
	public JTextField getTxtDepto() 
	{
		return txtDepto;
	}	
	
	public JTextField getTxtCalle() 
	{
		return txtCalle;
	}
	
	public JTextField getTxtEmail() 
	{
		return txtEmail;
	}
	
	public JTextField getTxtFCumple() 
	{
		return txtCumpleaños;
	}
	
	
	public JComboBox<LocalidadDTO> getComboLocalidad()
	{
		return comboLocalidad;
	}
	
	
	public JComboBox<ProvinciaDTO> getComboProvincia()
	{
		return comboProvincia;
	}

	public JComboBox<PaisDTO> getComboPais()
	{
		return comboPais;
	}
	
	
	public JButton getBtnAgregarPersona() 
	{
		return btnAgregarPersona;
	}

	public void cerrar()
	{
		this.txtNombre.setText(null);
		this.txtTelefono.setText(null);
		this.dispose();
	}
	
	
}