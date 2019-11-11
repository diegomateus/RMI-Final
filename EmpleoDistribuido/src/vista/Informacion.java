package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import modelo.Candidato;
import modelo.Oferta;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Informacion extends JFrame {

	private JPanel contentPane;
	private JTable empleos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Informacion frame = new Informacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public synchronized void ActualizarLog( String Operacion, Candidato c, Oferta o) {
		
		DefaultTableModel table = (DefaultTableModel) empleos.getModel();
		Date fecha = new Date (System.currentTimeMillis());
		String formato_fecha = new SimpleDateFormat("hh:mm:ss").format( fecha );
		//table.addRow( new Object[]{ formato_fecha, Operacion, Cliente, Zona, Noticia} );
		if( o != null)
			table.addRow( new Object[]{ o.getSectorEmpresa(), Operacion, c.getDocumento() } );
		else
			table.addRow( new Object[]{ "", Operacion, c.getDocumento() } );
	}
	
	/**
	 * Create the frame.
	 */
	public Informacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 598, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPortalDeEmpleo = new JLabel("Portal de empleo");
		lblPortalDeEmpleo.setBounds(258, 11, 116, 14);
		contentPane.add(lblPortalDeEmpleo);
		
		JButton btnSeleccionarArchivo = new JButton("Seleccionar archivo");
		btnSeleccionarArchivo.setBounds(30, 40, 134, 29);
		contentPane.add(btnSeleccionarArchivo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(64, 103, 478, 199);
		contentPane.add(scrollPane);
		
		empleos = new JTable();
		empleos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Oferta", "Operacion", "Candidato"
			}
		));
		scrollPane.setViewportView(empleos);
	}
}
