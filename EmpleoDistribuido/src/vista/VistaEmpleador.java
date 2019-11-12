package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Candidato;
import modelo.Empresa;
import modelo.Oferta;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingConstants;

public class VistaEmpleador extends JFrame {

	private JPanel contentPane;
	private JTable empleos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaEmpleador frame = new VistaEmpleador();
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
	public VistaEmpleador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 598, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPortalDeEmpleo = new JLabel("Ofertas empresa");
		lblPortalDeEmpleo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPortalDeEmpleo.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblPortalDeEmpleo.setBounds(10, 11, 562, 30);
		contentPane.add(lblPortalDeEmpleo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(63, 52, 478, 199);
		contentPane.add(scrollPane);

		empleos = new JTable();
		empleos.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Oferta", "Operacion", "Empresa" }));
		scrollPane.setViewportView(empleos);
	}

	public synchronized void ActualizarLog(String Operacion, Empresa e, Oferta o) {

		DefaultTableModel table = (DefaultTableModel) empleos.getModel();
		Date fecha = new Date(System.currentTimeMillis());
		String formato_fecha = new SimpleDateFormat("hh:mm:ss").format(fecha);
		if (o != null)
			table.addRow(new Object[] { o.getSectorEmpresa(), Operacion, e.getNombre() });
		else
			table.addRow(new Object[] { "", Operacion, e.getNombre() });
	}

}
