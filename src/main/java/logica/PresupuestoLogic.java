package logica;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.PresupuestoData;
import entidades.Obra;
import entidades.Presupuesto;
import entidades.Material;
import entidades.Material_a_usar;
import entidades.Maquinaria;
import entidades.Tarea;

public class PresupuestoLogic {
	public static PresupuestoData source=new PresupuestoData();
	
	/**
	 * @see _ Devuelve los presupuestos de una obra
	 * el parametro detalles sirve para saber si es requerido recuperar
	 * informacion de las tareas incluidas en ese presupuesto
	 */
	public static ArrayList<Presupuesto> getPresuspuestos(Obra o, boolean detalles) throws Exception{
		ArrayList<Presupuesto> presups=new ArrayList<Presupuesto>();
		try{
			presups=source.getPresupuestos(o);
		}
		catch(Exception e) {
			throw new Exception("No fue posible cargar los presupuestos:"+e.getMessage());
		}
		if(detalles) {
			for(Presupuesto p: presups) {
				p.setTareas(TareaLogic.getTareas(p));
				/*if(p.getFecha_aceptacion()!=null) {
					//recuperar los obreros
				}*/
			}
		}
		return presups;

	}
	
	public static ArrayList<Material_a_usar> getMateriales(Presupuesto p) throws Exception{
		return source.getMateriales(p);
	}
	
	public static ArrayList<Maquinaria> getMaquinarias(Presupuesto p) throws Exception{
		return source.getMaquinarias(p);
	}
	
	public static Presupuesto getOne(int id, boolean detalles) throws Exception{
		Presupuesto p=new Presupuesto();
		try {
			p=source.getOne(id);
			if(detalles) {
				p.setTareas(TareaLogic.getTareas(p));
				/*if(p.getFecha_aceptacion()!=null) {
				//recuperar los obreros
				}*/
			}
		}
		catch(Exception e) {
			throw new Exception("No fue posible cargar el presupuesto:"+e.getMessage());
		}
		return p;
	}
	
	public static void RegistrarPresupuesto(int idObra,Presupuesto p) throws Exception{
		source.Registrar(idObra, p);
		ArrayList<Presupuesto> pres=getPresuspuestos(new Obra(idObra,"",""), false);
		for(Presupuesto pre:pres) {
			if(p.getFecha_emision()==pre.getFecha_emision() && p.getMonto()==pre.getMonto()) {
				p.setId_presupuesto(pre.getId_presupuesto());
			}
		}
		TareaLogic.Registrar(p.getId_presupuesto(), p.getTareas());
		
	}
}
