package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPass;
	private JButton btnAcceder;
	private static Login INSTANCE;
	private JTextField textUser;
	private JTextField textHost;
	private JButton btnCancelar;

	public static Login getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new Login();
			return new Login();
		}
		else
			return INSTANCE;
	}

	public Login()
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

		JLabel lblNombrePais = new JLabel("Servidor");
		lblNombrePais.setBounds(10, 11, 113, 14);
		panel.add(lblNombrePais);

		JLabel lblNomLocalidad = new JLabel("Password");
		lblNomLocalidad.setBounds(10, 95, 113, 14);
		panel.add(lblNomLocalidad);

		txtPass = new JTextField();
		txtPass.setBounds(133, 92, 164, 20);
		panel.add(txtPass);
		txtPass.setColumns(10);

		btnAcceder = new JButton("Acceder");
		btnAcceder.setBounds(62, 142, 89, 23);
		panel.add(btnAcceder);

		JLabel lblNomProvincia = new JLabel("Usuario");
		lblNomProvincia.setBounds(10, 52, 113, 14);
		panel.add(lblNomProvincia);

		textUser = new JTextField();
		textUser.setColumns(10);
		textUser.setBounds(133, 49, 164, 20);
		panel.add(textUser);

		textHost = new JTextField();
		textHost.setColumns(10);
		textHost.setBounds(133, 8, 164, 20);
		panel.add(textHost);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(208, 142, 89, 23);
		panel.add(btnCancelar);

		this.setVisible(false);
	}

	public void mostrarVentana()
	{
		this.setVisible(true);
	}



	public void cerrar()
	{
		this.txtPass.setText(null);
		this.dispose();
	}

	public JTextField getTxtPass() {
		return txtPass;
	}

	public JButton getBtnAcceder() {
		return btnAcceder;
	}

	public JTextField getTextUser() {
		return textUser;
	}

	public JTextField getTextHost() {
		return textHost;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setTxtPass(JTextField txtPass) {
		this.txtPass = txtPass;
	}

	public void setBtnAcceder(JButton btnAcceder) {
		this.btnAcceder = btnAcceder;
	}

	public void setTextUser(JTextField textUser) {
		this.textUser = textUser;
	}

	public void setTextHost(JTextField textHost) {
		this.textHost = textHost;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

}

