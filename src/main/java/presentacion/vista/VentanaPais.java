package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

public class VentanaPais extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombrePais;
	private JButton btnAgregarPais;
	private static VentanaPais INSTANCE;
	
	public static VentanaPais getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaPais(); 	
			return new VentanaPais();
		}
		else
			return INSTANCE;
	}

	private VentanaPais() 
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
		
		JLabel lblNombrePais = new JLabel("Nombre País");
		lblNombrePais.setBounds(10, 11, 113, 14);
		panel.add(lblNombrePais);
		
		txtNombrePais = new JTextField();
		txtNombrePais.setBounds(133, 8, 164, 20);
		panel.add(txtNombrePais);
		txtNombrePais.setColumns(10);
		
		btnAgregarPais = new JButton("Agregar");
		btnAgregarPais.setBounds(208, 49, 89, 23);
		panel.add(btnAgregarPais);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana()
	{
		this.setVisible(true);
	}
	
	public JTextField getBtnTextPais() 
	{
		return txtNombrePais;
	}
	public JButton getBtnAgregarPais() 
	{
		return btnAgregarPais;
	}

	public void cerrar()
	{
		this.txtNombrePais.setText(null);
		this.dispose();
	}
}

