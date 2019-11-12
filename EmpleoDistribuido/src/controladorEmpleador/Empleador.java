package controladorEmpleador;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import interfazRMI.InterfazEmpleador;
import modelo.Empresa;
import modelo.Estudio;
import modelo.Experiencia;
import modelo.Oferta;
import vista.VistaEmpleador;

public class Empleador {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		VistaEmpleador vista = new VistaEmpleador();
		vista.setVisible(true);
		
		for (Empresa e : leerEmpleadores()) {
			Thread.sleep(1000);
			ThreadEmpleador t = new ThreadEmpleador(vista, e);
			Thread s = new Thread(t);
			s.start();
			System.out.println(e.getNombre());
			System.out.println(e.getNIT());

			for (Oferta o : e.getOfertas()) {
				System.out.println("Oferta");
				System.out.println("\tCargo: " + o.getCargo() + " Experiencia: " + o.getExperiencia() + " Salario: "
						+ o.getSalarioOfrecido() + " Sector: " + o.getSectorEmpresa() + " Nivel: "
						+ o.getNivelEstudio());
			}
		}
	}

	public static ArrayList<Empresa> leerEmpleadores() {
		String cadena;
		Empresa e = null;
		ArrayList<Empresa> empresas = new ArrayList<>();
		String datos[];

		try {
			FileReader f = new FileReader(Paths.get("InfoEmpresas").toString());
			BufferedReader b = new BufferedReader(f);
			while ((cadena = b.readLine()) != null) {
				if (cadena.equals("Empleador")) {

					cadena = b.readLine();
					datos = cadena.split(";");
					String nombre = datos[0];
					String nit = datos[1];
					e = new Empresa(nombre, nit, LocalDateTime.now(), LocalDateTime.now());
					// System.out.println(e.getNombre());

				} else if (cadena.equals("Oferta")) {
					// System.out.println("Entra");
					cadena = b.readLine();
					while (!cadena.equals("#")) {

						datos = cadena.split(";");
						String cargo = datos[0];
						int experiencia = Integer.parseInt(datos[1]);
						double salario = Double.parseDouble(datos[2]);
						String sector = datos[3];
						Estudio nivel = Estudio.valueOf(datos[4]);
						Oferta offer = new Oferta(cargo, experiencia, salario, sector, nivel, LocalDateTime.now(),
								LocalDateTime.now());
						e.agregarOferta(offer);

						cadena = b.readLine();

					}

					empresas.add(e);
					e = null;
				}
			}
			b.close();
		} catch (FileNotFoundException ex) {

			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return empresas;

	}
}
