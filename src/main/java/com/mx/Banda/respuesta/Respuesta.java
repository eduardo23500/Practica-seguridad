package com.mx.Banda.respuesta;

public class Respuesta {
	String mensaje;
	Object obj;
	boolean success;
	public Respuesta() {
	}
	public Respuesta(String mensaje, Object obj, boolean success) {
		this.mensaje = mensaje;
		this.obj = obj;
		this.success = success;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	@Override
	public String toString() {
		return "Respuesta [mensaje=" + mensaje + ", obj=" + obj + ", success=" + success + "]";
	}
	
}
