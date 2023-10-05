import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExcursaoGUI {

	private JFrame frmSistemaDeReserva;

	JLabel lblTituloPainel;
	JLabel lblCancelarReservas;
	JLabel lblListarReservas;

	JButton btnCriarExcursao;
	JButton btnRecuperarExcursao;
	JButton btnCriarReserva;
	JButton btnCancelarReservaIndividual;
	JButton btnCancelarReservaGrupo;
	JButton btnListarReservasCPF;
	JButton btnListarReservasNome;
	JButton btnCalcularValorTotal;
	JButton btnSalvar;	

	Excursao excursao = new Excursao(5555, 300.0, 10); 
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExcursaoGUI window = new ExcursaoGUI();
					window.frmSistemaDeReserva.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ExcursaoGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSistemaDeReserva = new JFrame();
		frmSistemaDeReserva.setResizable(false);
		frmSistemaDeReserva.getContentPane().setBackground(new Color(240, 240, 240));
		frmSistemaDeReserva.setTitle("Sistema de Reserva de Excurs\u00E3o");
		frmSistemaDeReserva.setBounds(100, 100, 720, 520);
		frmSistemaDeReserva.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaDeReserva.getContentPane().setLayout(null);

		lblTituloPainel = new JLabel("Reservas de Excurs\u00F5es");
		lblTituloPainel.setBounds(235, 11, 175, 14);
		lblTituloPainel.setFont(new Font("Tahoma", Font.BOLD, 14));
		frmSistemaDeReserva.getContentPane().add(lblTituloPainel);

		btnCriarExcursao = new JButton("Criar Excurs\u00E3o");
		btnCriarExcursao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TelaCriarExcursao telaCriarExcursao = new TelaCriarExcursao();
				//telaCriarExcursao.setVisible(true);
			
				//Excursao excursao = new Excursao(5555, 300.0, 10);
				
				int codigo = Integer.parseInt(JOptionPane.showInputDialog("Informe codigo: "));
				double preco = Double.parseDouble(JOptionPane.showInputDialog("Informe preco: "));
				int max = Integer.parseInt(JOptionPane.showInputDialog("Informe max: "));
				
				excursao.setCodigo(codigo);
				excursao.setPreco(preco);
				excursao.setMax(max);
				
				excursao.salvar();
				System.out.println(excursao);
			
			
			}
		});
		btnCriarExcursao.setBounds(86, 64, 130, 40);
		btnCriarExcursao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frmSistemaDeReserva.getContentPane().add(btnCriarExcursao);

		btnRecuperarExcursao = new JButton("Recuperar Excurs\u00E3o");
		btnRecuperarExcursao.setBounds(70, 133, 160, 40);
		frmSistemaDeReserva.getContentPane().add(btnRecuperarExcursao);

		btnCriarReserva = new JButton("Criar Reserva");
		btnCriarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cpf = JOptionPane.showInputDialog("Informe CPF: ");
				String nome = JOptionPane.showInputDialog("Informe Nome: ");
				
				//excursao.carregar();
				excursao.criarReserva(cpf, nome);
				
				excursao.salvar();
				//System.out.println(excursao);
			}
		});
		btnCriarReserva.setBounds(458, 64, 140, 40);
		frmSistemaDeReserva.getContentPane().add(btnCriarReserva);

		lblCancelarReservas = new JLabel("Cancelar Reservas:");
		lblCancelarReservas.setBounds(474, 137, 115, 30);
		lblCancelarReservas.setFont(new Font("Tahoma", Font.BOLD, 12));
		frmSistemaDeReserva.getContentPane().add(lblCancelarReservas);

		btnCancelarReservaIndividual = new JButton("Individual");
		btnCancelarReservaIndividual.setBounds(411, 178, 105, 30);
		frmSistemaDeReserva.getContentPane().add(btnCancelarReservaIndividual);

		btnCancelarReservaGrupo = new JButton("Grupo");
		btnCancelarReservaGrupo.setBounds(537, 178, 105, 30);
		frmSistemaDeReserva.getContentPane().add(btnCancelarReservaGrupo);

		lblListarReservas = new JLabel("Listar Reservas:");
		lblListarReservas.setBounds(485, 251, 115, 30);
		lblListarReservas.setFont(new Font("Tahoma", Font.BOLD, 12));
		frmSistemaDeReserva.getContentPane().add(lblListarReservas);

		btnListarReservasCPF = new JButton("Por CPF");
		btnListarReservasCPF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(excursao.getReservas());
				JOptionPane.showMessageDialog(null, excursao.listarReservasPorCpf(""));
				
			}
		});
		btnListarReservasCPF.setBounds(411, 292, 105, 30);
		frmSistemaDeReserva.getContentPane().add(btnListarReservasCPF);

		btnListarReservasNome = new JButton("Por Nome");
		btnListarReservasNome.setBounds(537, 292, 105, 30);
		frmSistemaDeReserva.getContentPane().add(btnListarReservasNome);

		btnCalcularValorTotal = new JButton("Calcular Valor Total");
		btnCalcularValorTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Valor Total: " + excursao.calcularValorTotal());
				//System.out.println(excursao.calcularValorTotal());
			}
		});
		btnCalcularValorTotal.setBounds(70, 282, 160, 40);
		frmSistemaDeReserva.getContentPane().add(btnCalcularValorTotal);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(270, 394, 140, 40);
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 12));
		frmSistemaDeReserva.getContentPane().add(btnSalvar);



	}//Final método initialize
} //Final class ExcursaoGUI
