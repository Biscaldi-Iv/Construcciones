package logica;

import java.util.ArrayList;

import datos.MaquinariaData;
import entidades.Maquinaria;
import entidades.Tarea;

public class MaquinariaLogic {
	
	private static MaquinariaData source=new MaquinariaData();
		
	public static void Registrar(Maquinaria m) throws Exception {
		
		try {
			source.Registrar(m);
		}
		catch(Exception e){
			//a presentacion solo deben llegar exceptciones de la clase Exception
			String msg=e.getMessage();
			throw new Exception(msg);
		}
		
	}
	
	public static void ActualizarDatos(Maquinaria m) throws Exception {
		
		try {
			source.ActualizarDatos(m);
		}
		catch(Exception e) {
			String msg=e.getMessage();
			throw new Exception(msg);
		}
	}
	
	public static void Eliminar(int id) throws Exception {
		try {
			source.Eliminar(id);
		}
		catch(Exception e) {
			String msg=e.getMessage();
			throw new Exception(msg);
		}
	}
	
	public static Maquinaria getOne(int id) throws Exception {
		Maquinaria m;
		try {
			m=source.getOne(id);
		}
		catch(Exception e) {
			String msg=e.getMessage();
			throw new Exception(msg);
		}
		
		return m;
	}
	
	public static ArrayList<Maquinaria> getAll() throws Exception{
		ArrayList<Maquinaria> maqs;
		try {
			maqs=source.getAll();
		}
		catch(Exception e) {
			String msg=e.getMessage();
			throw new Exception(msg);
		}
		return maqs;
	}
	
	public static ArrayList<Tarea> maquinas_tareas(ArrayList<Tarea> tareas) throws Exception{
		
			for(Tarea t:tareas) {
				try{
					t.setMaquinas(source.getMaquinasTarea(t.getIdTarea()));
				}catch(Exception e) {
					throw new Exception("Se produjo un error mientras se cargaban las maquinas de la tarea "
							+t.getIdTarea()+"-"+t.getTipo_tarea().getDescripcion()+":"+e.getMessage());
				}
				}
		return tareas;
	}
	
	public static Tarea getMaquinasTarea(Tarea t) throws Exception{
		t.setMaquinas(source.getMaquinasTarea(t.getIdTarea()));
		return t;
	}
	
	public static void RegistrarUsoMaquinas(int idTarea, ArrayList<Maquinaria> mqs) throws Exception {
		source.RegistrarUsoMaquinas(idTarea, mqs);
	}

}
