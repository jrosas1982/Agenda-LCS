package presentacion.vista;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;

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
	private JTextField text_ID;
	private JTextField textFieldLTuristico;


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

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 697, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 661, 411);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(10, 71, 113, 14);
		panel.add(lblTelefono);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 99, 113, 14);
		panel.add(lblEmail);

		JLabel lblFechaCumple = new JLabel("Fecha de Cumpleaños");
		panel.add(lblFechaCumple);

		JLabel lblTipoContacto = new JLabel("Tipo de Conatcto");
		panel.add(lblTipoContacto);

		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 38, 113, 14);
		panel.add(lblNombreYApellido);

		JLabel lblNewLabel = new JLabel("Fecha de Cumpleaños");
		lblNewLabel.setBounds(10, 135, 113, 14);
		panel.add(lblNewLabel);

		JLabel lblTipContacto = new JLabel("Tipo de Contacto");
		lblTipContacto.setBounds(10, 173, 113, 14);
		panel.add(lblTipContacto);

		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setHorizontalAlignment(SwingConstants.CENTER);
		lblDomicilio.setBounds(344, 11, 287, 14);
		panel.add(lblDomicilio);

		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(344, 35, 46, 14);
		panel.add(lblCalle);

		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(344, 68, 36, 14);
		panel.add(lblAltura);

		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(468, 68, 36, 14);
		panel.add(lblPiso);

		JLabel lblDepto = new JLabel("Depto");
		lblDepto.setBounds(550, 68, 46, 14);
		panel.add(lblDepto);

		JLabel lblPais = new JLabel("País");
		lblPais.setBounds(344, 100, 46, 14);
		panel.add(lblPais);

		JLabel lblProvincia= new JLabel("Provincia");
		lblProvincia.setBounds(344, 132, 94, 14);
		panel.add(lblProvincia);

		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(344, 173, 64, 14);
		panel.add(lblLocalidad);



		txtNombre = new JTextField();
		txtNombre.setBounds(139, 35, 159, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(140, 68, 158, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(139, 96, 158, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);

		txtCumpleaños = new JTextField();
		txtCumpleaños.setBounds(138, 132, 103, 20);
		panel.add(txtCumpleaños);
		txtCumpleaños.setColumns(10);

		txtCalle = new JTextField();
		txtCalle.setBounds(397, 32, 234, 20);
		panel.add(txtCalle);
		txtCalle.setColumns(10);

		txtAltura = new JTextField();
		txtAltura.setBounds(397, 65, 64, 20);
		panel.add(txtAltura);
		txtAltura.setColumns(10);

		txtPiso = new JTextField();
		txtPiso.setBounds(504, 65, 36, 20);
		panel.add(txtPiso);
		txtPiso.setColumns(10);

		txtDepto = new JTextField();
		txtDepto.setBounds(595, 65, 36, 20);
		panel.add(txtDepto);
		txtDepto.setColumns(10);


		comboTipoContacto = new JComboBox<>();
		comboTipoContacto.setBounds(139, 171, 158, 18);
		panel.add(comboTipoContacto);

		comboPais = new JComboBox<>();
		comboPais.setBounds(397, 96, 234, 18);
		panel.add(comboPais);

		comboProvincia = new JComboBox<>();
		comboProvincia.setBounds(397, 130, 234, 18);
		panel.add(comboProvincia);

		comboLocalidad = new JComboBox<>();
		comboLocalidad.setBounds(397, 169, 234, 18);
		panel.add(comboLocalidad);


		btnAgregarPersona = new JButton("Actualizar");
		btnAgregarPersona.setBounds(251, 377, 157, 23);
		panel.add(btnAgregarPersona);

		JLabel lblNewLabel_1 = new JLabel("DD/MM");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(251, 135, 46, 14);
		panel.add(lblNewLabel_1);

		JLabel lblContacto = new JLabel("Datos de contacto");
		lblContacto.setHorizontalAlignment(SwingConstants.CENTER);
		lblContacto.setBounds(11, 13, 287, 14);
		panel.add(lblContacto);

		JLabel lblLugarTuristcoPreferido = new JLabel("Lugar Turistco Preferido");
		lblLugarTuristcoPreferido.setHorizontalAlignment(SwingConstants.CENTER);
		lblLugarTuristcoPreferido.setBounds(11, 233, 287, 14);
		panel.add(lblLugarTuristcoPreferido);

		JLabel lblLugarTuristico = new JLabel("Lugar Turistico");
		lblLugarTuristico.setBounds(10, 271, 113, 14);
		panel.add(lblLugarTuristico);

		textFieldLTuristico = new JTextField();
		textFieldLTuristico.setColumns(10);
		textFieldLTuristico.setBounds(139, 268, 159, 20);
		panel.add(textFieldLTuristico);

		text_ID = new JTextField();
		text_ID.setBounds(10, 378, 86, 20);
		text_ID.setVisible(false);
		panel.add(text_ID);

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

	public JTextField getTextFieldLTuristico() {
		return textFieldLTuristico;
	}


	public void setTextFieldLTuristico(JTextField textFieldLTuristico) {
		this.textFieldLTuristico = textFieldLTuristico;
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

	public JTextField getText_ID() {
		return text_ID;
	}

	public void setText_ID(JTextField text_ID) {
		this.text_ID = text_ID;
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

	public JComboBox<TipoContactoDTO> getComboTipoContacto()
	{
		return comboTipoContacto;
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