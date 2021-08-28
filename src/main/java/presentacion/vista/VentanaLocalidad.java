package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import dto.PaisDTO;
import dto.ProvinciaDTO;

public class VentanaLocalidad extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLocalidad;
	private JButton btnAgregarLocalidad;
	private JComboBox<ProvinciaDTO> comboProvincia;
	private JComboBox<PaisDTO> comboPais;
	private static VentanaLocalidad INSTANCE;

	public static VentanaLocalidad getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaLocalidad();
			return new VentanaLocalidad();
		}
		else
			return INSTANCE;
	}

	private VentanaLocalidad()
	{
		super();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 343, 231);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 307, 176);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNombrePais = new JLabel("Nombre País");
		lblNombrePais.setBounds(10, 11, 113, 14);
		panel.add(lblNombrePais);

		JLabel lblNomLocalidad = new JLabel("Nombre Localidad");
		lblNomLocalidad.setBounds(10, 95, 113, 14);
		panel.add(lblNomLocalidad);

		txtLocalidad = new JTextField();
		txtLocalidad.setBounds(133, 92, 164, 20);
		panel.add(txtLocalidad);
		txtLocalidad.setColumns(10);

		btnAgregarLocalidad = new JButton("Agregar");
		btnAgregarLocalidad.setBounds(208, 142, 89, 23);
		panel.add(btnAgregarLocalidad);

		JLabel lblNomProvincia = new JLabel("Nombre Provincia");
		lblNomProvincia.setBounds(10, 52, 113, 14);
		panel.add(lblNomProvincia);

		comboProvincia = new JComboBox<>();
		comboProvincia.setBounds(133, 52, 164, 18);
		panel.add(comboProvincia);

		comboPais = new JComboBox<>();
		comboPais.setBounds(133, 7, 164, 18);
		panel.add(comboPais);

		this.setVisible(false);
	}

	public void mostrarVentana()
	{
		this.setVisible(true);
	}

	public JComboBox<ProvinciaDTO> getComboProvincia()
	{
		return comboProvincia;
	}

	public JComboBox<PaisDTO> getComboPais()
	{
		return comboPais;
	}

	public JTextField getTxtLocalidad()
	{
		return txtLocalidad;
	}

	public JButton getBtnAgregarLocalidad()
	{
		return btnAgregarLocalidad;
	}

	public void cerrar()
	{
		this.txtLocalidad.setText(null);
		this.dispose();
	}
}

