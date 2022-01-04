package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Material;
import entidades.Tipo_Tarea;
import logica.MaterialLogic;
import logica.Tipo_TareaLogic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Tipo_Tarea
 */
@WebServlet("/Tipo_Tarea")
public class Tipo_Tarea extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Tipo_Tarea() {
    	super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<entidades.Tipo_Tarea> tps=null;
		try {
			tps= Tipo_TareaLogic.getAll();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", "Un error ha ocurrido mientras procesabamos su solicitud: "+e.getMessage());
		}
		request.setAttribute("tipos_tarea", tps);
		request.getRequestDispatcher("./Tipos_Tarea.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String accion=(String)request.getParameter("accion");
			switch(accion) {
			case "Registrar":{
				registrar(request,response);
				break;
			}
			case "Editar":{
				actualizar(request,response);
				break;
			}
			case "Eliminar":{
				eliminar(request,response);
				break;
			}
			}
		}
		catch(Exception e) {
			request.setAttribute("error", "Un error ha ocurrido mientras procesabamos su solicitud: "+e.getMessage());
		}
		doGet(request, response);
	}
	
	protected void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			String descripcion=(String)request.getParameter("descripciontipotarea");
			float precio=Float.parseFloat(request.getParameter("preciotipotarea"));
			entidades.Tipo_Tarea tt=new entidades.Tipo_Tarea(0,descripcion, precio);
			Tipo_TareaLogic.Registrar(tt);
		}
		catch(Exception e) {
			request.setAttribute("error", "No fue posible registrar el tipo de tarea>> "+e.getMessage());
		}
	}
	
	protected void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			int id=Integer.parseInt(request.getParameter("idtipotarea"));
			String descripcion=(String)request.getParameter("descripciontipotarea");
			float precio=Float.parseFloat(request.getParameter("preciotipotarea"));
			entidades.Tipo_Tarea t=new entidades.Tipo_Tarea(id,descripcion, precio);
			Tipo_TareaLogic.ActualizarDatos(t);
		}
		catch(Exception e) {
			request.setAttribute("error", "No fue posible actualizar los datos del tipo de tarea>> "+e.getMessage());
		}
	}

	protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			int id=Integer.parseInt(request.getParameter("idtipotarea"));
			MaterialLogic.Eliminar(id);
		}
		catch(Exception e) {
			request.setAttribute("error", "No fue posible eliminar el tipo de tarea>> "+e.getMessage());
		}
	}

}
