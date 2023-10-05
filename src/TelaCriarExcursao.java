import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCriarExcursao extends JFrame {

	private JPanel contentPane;
	private JLabel lblCodigo;
	private JLabel lblPreco;
	private JLabel lblMaximoDeReservas;
	private JTextField textFieldCodigo;
	private JTextField textFieldPreco;
	private JTextField textFieldMaximoDeReservas;

	
	Excursao excursao = new Excursao(111, 100, 2);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCriarExcursao frame = new TelaCriarExcursao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCriarExcursao() {
		setTitle("Criar Excurs\u00E3o");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 475, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCodigo.setBounds(30, 17, 145, 40);
		contentPane.add(lblCodigo);
		
		lblPreco = new JLabel("Pre\u00E7o:");
		lblPreco.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPreco.setBounds(30, 79, 145, 40);
		contentPane.add(lblPreco);
		
		lblMaximoDeReservas = new JLabel("M\u00E1ximo de Reservas:");
		lblMaximoDeReservas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMaximoDeReservas.setBounds(30, 142, 145, 40);
		contentPane.add(lblMaximoDeReservas);
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setBounds(195, 24, 130, 30);
		contentPane.add(textFieldCodigo);
		textFieldCodigo.setColumns(10);
		
		textFieldPreco = new JTextField();
		textFieldPreco.setToolTipText("Informe o pre\u00E7o, ex: 200.00");
		textFieldPreco.setBounds(195, 86, 130, 30);
		contentPane.add(textFieldPreco);
		textFieldPreco.setColumns(10);
		
		textFieldMaximoDeReservas = new JTextField();
		textFieldMaximoDeReservas.setBounds(195, 149, 130, 30);
		contentPane.add(textFieldMaximoDeReservas);
		textFieldMaximoDeReservas.setColumns(10);
		
		JButton btnSalvarExcursao = new JButton("Salvar");
		btnSalvarExcursao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int codigo = Integer.parseInt(textFieldCodigo.getText());
				double preco = Double.parseDouble(textFieldPreco.getText());
				int max = Integer.parseInt(textFieldMaximoDeReservas.getText());
				
				
				excursao.setCodigo(codigo);
				excursao.setPreco(preco);
				excursao.setMax(max);
				excursao.salvar();

			}
		});
		btnSalvarExcursao.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSalvarExcursao.setBounds(335, 216, 100, 40);
		contentPane.add(btnSalvarExcursao);
	}
}
