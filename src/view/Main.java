package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entities.Grafo;
import entities.Usuario;
import tablemodel.UsuariosTableModel;
import util.Rede;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelCadastrar;
	private JTextField txtNome;
	private JTextField txtSenha;
	private List<Usuario> bancoDados;
	private JTextField txtUsuarioUsado;
	private JTextField txtUsuarioParaSeguir;
	private JTable tblUsuariosSeguidos;
	private Grafo grafo;
	private JComboBox<String> cmbRecomendados;
	private UsuariosTableModel tableModel;
	private DefaultTableModel defaultModel;
	private JTable tblUsuariosCadastrados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {

		bancoDados = new ArrayList<Usuario>();
		grafo = new Grafo();
		tblUsuariosSeguidos = new JTable();

		defaultModel = new DefaultTableModel();
		defaultModel.setRowCount(0);

		Usuario kaik = new Usuario("Kaik", "123");
		Usuario joao = new Usuario("João", "123");
		Usuario enzo = new Usuario("Enzo", "123");
		Usuario gui = new Usuario("Gui", "123");
		Usuario inacio = new Usuario("Inacio", "123");

		bancoDados.add(kaik);
		bancoDados.add(joao);
		bancoDados.add(enzo);
		bancoDados.add(gui);
		bancoDados.add(inacio);

		grafo.adicionarVertice(joao);
		grafo.adicionarVertice(enzo);
		grafo.adicionarVertice(kaik);
		grafo.adicionarVertice(gui);
		grafo.adicionarVertice(inacio);

		grafo.adicionarAresta(joao, inacio);
		grafo.adicionarAresta(enzo, inacio);
		grafo.adicionarAresta(inacio, kaik);
		grafo.adicionarAresta(joao, gui);
		grafo.adicionarAresta(kaik, enzo);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 552, 312);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 538, 22);
		contentPane.add(menuBar);

		JButton btnAdministrarUsuarios = new JButton("Administrar usuários");

		btnAdministrarUsuarios.setBorder(new EmptyBorder(0, 5, 0, 5));
		btnAdministrarUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdministrarUsuarios.setFocusPainted(false);
		btnAdministrarUsuarios.setContentAreaFilled(false);
		menuBar.add(btnAdministrarUsuarios);

		JButton btnAdministrarSeguidores = new JButton("Administrar seguidores");

		btnAdministrarSeguidores.setBorder(new EmptyBorder(0, 5, 0, 5));
		btnAdministrarSeguidores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdministrarSeguidores.setFocusPainted(false);
		btnAdministrarSeguidores.setContentAreaFilled(false);
		menuBar.add(btnAdministrarSeguidores);

		JButton btnGerarGrafo = new JButton("Gerar Grafo");
		btnGerarGrafo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGerarGrafo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Screen screen = new Screen(grafo);
				screen.run();
			}
		});
		btnGerarGrafo.setFocusPainted(false);
		btnGerarGrafo.setContentAreaFilled(false);
		btnGerarGrafo.setBorder(new EmptyBorder(0, 5, 0, 5));
		menuBar.add(btnGerarGrafo);

		JPanel panelProcurarSeguidores = new JPanel();
		panelProcurarSeguidores.setVisible(false);
		panelProcurarSeguidores.setBounds(0, 21, 543, 260);
		contentPane.add(panelProcurarSeguidores);
		panelProcurarSeguidores.setLayout(null);

		JLabel lblQuemVoc = new JLabel("Quem é você?");
		lblQuemVoc.setBounds(40, 24, 150, 14);
		panelProcurarSeguidores.add(lblQuemVoc);

		txtUsuarioUsado = new JTextField();

		txtUsuarioUsado.setColumns(10);
		txtUsuarioUsado.setBounds(40, 49, 484, 25);
		panelProcurarSeguidores.add(txtUsuarioUsado);

		JLabel lblInformeONome = new JLabel("Informe o nome do usuário para segui-lo");
		lblInformeONome.setBounds(40, 140, 244, 14);
		panelProcurarSeguidores.add(lblInformeONome);

		txtUsuarioParaSeguir = new JTextField();
		txtUsuarioParaSeguir.setColumns(10);
		txtUsuarioParaSeguir.setBounds(40, 165, 230, 25);
		panelProcurarSeguidores.add(txtUsuarioParaSeguir);

		JLabel lblUsurioRecomendados = new JLabel("Usuário recomendados");
		lblUsurioRecomendados.setBounds(40, 85, 157, 14);
		panelProcurarSeguidores.add(lblUsurioRecomendados);

		cmbRecomendados = new JComboBox();
		cmbRecomendados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = "";
				if (cmbRecomendados.getSelectedItem() != null) {
					nome = cmbRecomendados.getSelectedItem().toString();
				}

				txtUsuarioParaSeguir.setText(nome);
			}
		});
		cmbRecomendados.setBounds(40, 107, 113, 22);
		panelProcurarSeguidores.add(cmbRecomendados);

		JButton btnSeguir = new JButton("Seguir");
		btnSeguir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario usuarioUsado = pegar(txtUsuarioUsado.getText());
				Usuario usuarioParaSeguir = pegar(txtUsuarioParaSeguir.getText());
				Rede rd = new Rede(grafo);

				if (txtUsuarioUsado.getText().equals(txtUsuarioParaSeguir.getText())
						|| rd.getPessoasSeguidas(usuarioUsado).contains(usuarioParaSeguir)) {
					JOptionPane.showMessageDialog(null, "Entrada de seguidor inválida", "ERRO",
							JOptionPane.ERROR_MESSAGE);
				} else {
					rd.seguir(usuarioUsado, usuarioParaSeguir);
					txtUsuarioUsado.setText("");
					txtUsuarioParaSeguir.setText("");
					limparCmb();
					tblUsuariosSeguidos.setModel(defaultModel);
				}

			}
		});
		btnSeguir.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnSeguir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSeguir.setBounds(40, 214, 113, 23);
		panelProcurarSeguidores.add(btnSeguir);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(294, 85, 230, 152);
		panelProcurarSeguidores.add(scrollPane);
		panelCadastrar = new JPanel();
		panelCadastrar.setBounds(-5, 23, 543, 260);
		contentPane.add(panelCadastrar);
		panelCadastrar.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome de usuário");
		lblNewLabel.setBounds(43, 31, 119, 25);
		panelCadastrar.add(lblNewLabel);

		txtNome = new JTextField();
		txtNome.setBounds(43, 56, 490, 25);
		panelCadastrar.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setBounds(43, 92, 97, 25);
		panelCadastrar.add(lblNewLabel_1);

		txtSenha = new JTextField();
		txtSenha.setColumns(10);
		txtSenha.setBounds(43, 119, 230, 25);
		panelCadastrar.add(txtSenha);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar(new Usuario(txtNome.getText(), txtSenha.getText()));
				limpar();
				tblUsuariosCadastrados.setModel(new UsuariosTableModel(bancoDados, "Cadastrados"));
			}
		});
		btnSalvar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalvar.setBounds(43, 179, 89, 23);
		panelCadastrar.add(btnSalvar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(142, 179, 89, 23);
		panelCadastrar.add(btnLimpar);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(303, 92, 230, 152);
		panelCadastrar.add(scrollPane_1);

		tblUsuariosCadastrados = new JTable(new UsuariosTableModel(bancoDados, "Cadastrados"));
		scrollPane_1.setViewportView(tblUsuariosCadastrados);

		panelCadastrar.setVisible(true);

		btnAdministrarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCadastrar.setVisible(true);
				panelProcurarSeguidores.setVisible(false);
			}
		});

		btnAdministrarSeguidores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCadastrar.setVisible(false);
				panelProcurarSeguidores.setVisible(true);
			}
		});

		txtUsuarioUsado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String nome;
				List<Usuario> usuariosSeguidos;
				List<Usuario> usuarioRecomendados;
				Usuario us;
				Rede rd;

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					limparCmb();
					nome = txtUsuarioUsado.getText();

					us = pegar(nome);
					rd = new Rede(grafo);

					if (us != null) {
						usuariosSeguidos = rd.getPessoasSeguidas(us);

						tableModel = new UsuariosTableModel(usuariosSeguidos, "Usuários seguidos");
						tblUsuariosSeguidos.setModel(tableModel);
						scrollPane.setViewportView(tblUsuariosSeguidos);

						usuarioRecomendados = rd.getUsuariosRecomendados(us);

						for (Usuario s : usuarioRecomendados) {
							cmbRecomendados.addItem(s.getNome());
						}
					}

				}
			}

		});
	}

	private void limpar() {
		txtNome.setText("");
		txtSenha.setText("");
	}

	private void adicionar(Usuario user) {
		if (!bancoDados.contains(user)) {
			bancoDados.add(user);
			grafo.adicionarVertice(user);
			JOptionPane.showMessageDialog(Main.this, "Usuário cadastrado!", "Sucesso", JOptionPane.PLAIN_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(Main.this, "Usuário já existe!!", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}

	private Usuario pegar(String nome) {

		for (int i = 0; i < bancoDados.size(); i++) {
			if (bancoDados.get(i).getNome().equals(nome)) {
				return bancoDados.get(i);
			}
		}
		return null;
	}

	private void limparCmb() {

		while (cmbRecomendados.getItemCount() != 0) {
			cmbRecomendados.removeItemAt(0);
		}

	}
}
