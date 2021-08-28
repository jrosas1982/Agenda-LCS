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

public class VentanaProvincia extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombreProvincia;
	private JButton btnAgregarProvincia;
	private JComboBox<PaisDTO> comboPais;
	private static VentanaProvincia INSTANCE;

	public static VentanaProvincia getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaProvincia();
			return new VentanaProvincia();
		}
		else
			return INSTANCE;
	}

	private VentanaProvincia()
	{
		super();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 343, 178);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 307, 128);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNombrePais = new JLabel("Nombre País");
		lblNombrePais.setBounds(10, 11, 113, 14);
		panel.add(lblNombrePais);

		txtNombreProvincia = new JTextField();
		txtNombreProvincia.setBounds(133, 49, 164, 20);
		panel.add(txtNombreProvincia);
		txtNombreProvincia.setColumns(10);

		btnAgregarProvincia = new JButton("Agregar");
		btnAgregarProvincia.setBounds(208, 92, 89, 23);
		panel.add(btnAgregarProvincia);

		JLabel lblNomProvincia = new JLabel("Nombre Provincia");
		lblNomProvincia.setBounds(10, 52, 113, 14);
		panel.add(lblNomProvincia);

		comboPais = new JComboBox<>();
		comboPais.setBounds(133, 7, 164, 18);
		panel.add(comboPais);

		this.setVisible(false);
	}

	public void mostrarVentana()
	{
		this.setVisible(true);
	}

	public JTextField getTxtNombreProvincia()
	{
		return txtNombreProvincia;
	}

	public JComboBox<PaisDTO> getComboPais()
	{
		return comboPais;
	}


	public JButton getBtnAgregarProvincia()
	{
		return btnAgregarProvincia;
	}

	public void cerrar()
	{
		this.txtNombreProvincia.setText(null);
		this.dispose();
	}
}

