package entidades;

import java.util.ArrayList;

public class Proveedor {
	private int idProveedor;
	private String RazonSocial;
	private String direccion;
	private long telefono;
	
	public int getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public long getTelefono() {
		return telefono;
	}
	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}
	
	public String getRazonSocial() {
		return RazonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		RazonSocial = razonSocial;
	}
	
	private ArrayList<Material> materiales;
	
	
	
	
	public ArrayList<Material> getMateriales() {
		return materiales;
	}
	public void setMateriales(ArrayList<Material> materiales) {
		this.materiales = materiales;
	}
	
	
	
	public Proveedor(int idProveedor, String razonsocial, String direccion, long telefono) {
		super();
		this.idProveedor = idProveedor;
		this.direccion = direccion;
		this.telefono = telefono;
		this.RazonSocial=razonsocial;
	}
	
	

}
