package gestionficherosapp;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import gestionficheros.FormatoVistas;
import gestionficheros.GestionFicheros;
import gestionficheros.GestionFicherosException;
import gestionficheros.TipoOrden;

public class GestionFicherosImp implements GestionFicheros{
	private File carpetaDeTrabajo = null;
	private Object[][] contenido;
	private int filas=0;
	private int columnas=3;
	private FormatoVistas formatoVistas =FormatoVistas.NOMBRES;
	private TipoOrden ordenado = TipoOrden.DESORDENADO;
	
	public GestionFicherosImp() {
		carpetaDeTrabajo =File.listRoots()[0];
		actualiza();
	}
	private void actualiza() {
		// TODO Auto-generated method stub
		String[] ficheros = carpetaDeTrabajo.list(); // obtener los nombres
		// calcular el número de filas necesario
		filas = ficheros.length / columnas;
			if (filas * columnas < ficheros.length) {
				filas++; // si hay resto necesitamos una fila más
			}
			// dimensionar la matriz contenido según los resultados
			contenido = new String[filas][columnas];
			// Rellenar contenido con los nombres obtenidos
			for (int i = 0; i < columnas; i++) {
				for (int j = 0; j < filas; j++) {
					int ind = j * columnas + i;
					if (ind < ficheros.length) {
						contenido[j][i] = ficheros[ind];
					} else {
						contenido[j][i] = "";
					}
				}
			}
		}
	public void arriba() {
		// TODO Auto-generated method stub
		if(carpetaDeTrabajo.getParentFile()==null) {
		carpetaDeTrabajo= carpetaDeTrabajo.getParentFile();
		actualiza();
		}
	}
	public void creaCarpeta(String arg0) throws GestionFicherosException {
		File directorio=new File("nombredirectorio");
		directorio.mkdir();
		
	}
	public void creaFichero(String arg0) throws GestionFicherosException {
		File file= new File(carpetaDeTrabajo); 
		
		try {
			  
			  if (carpetaDeTrabajo.createNewFile())
			    System.out.println("El fichero se ha creado correctamente");
			  else
			    System.out.println("No ha podido ser creado el fichero");
			} catch (IOException ioe) {
			  ioe.printStackTrace();
			}
		
	}
	public void elimina(String arg0) throws GestionFicherosException {
		String sDirectorio = "c:\\";
		File f = new File(sDirectorio);
		if (f.delete())
		  System.out.println("El directorio " + sDirectorio + " ha sido borrado correctamente");
		else
		  System.out.println("El directorio " + sDirectorio + " no se ha podido borrar");
		
	}
	public void entraA(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		File file = new File(carpetaDeTrabajo,arg0);
		if(!file.exists()) {
			throw new GestionFicherosException("La ruta" +file.getAbsolutePath()+ " no existe");
		}
		if(!file.isDirectory()) {
			throw new GestionFicherosException("ERROR" +file.getAbsolutePath()+ " no existe el directorio");
	}
		carpetaDeTrabajo = file;
		actualiza();
		if(!file.canRead()) {
			throw new GestionFicherosException("No tienes permisos de lectura");
		}
	}
	public int getColumnas() {
		// TODO Auto-generated method stub
		return columnas;
	}
	public Object[][] getContenido() {
		// TODO Auto-generated method stub
		return contenido;
	}
	public String getDireccionCarpeta() {
		// TODO Auto-generated method stub
		return carpetaDeTrabajo.getAbsolutePath();
	}
	public String getEspacioDisponibleCarpetaTrabajo() {
		
		return null;
	}
	
	public String getEspacioTotalCarpetaTrabajo() {
		
		return null;
	}
	
	public int getFilas() {
		// TODO Auto-generated method stub
		return filas;
	}
	public FormatoVistas getFormatoContenido() {
		// TODO Auto-generated method stub
		return formatoVistas;
	}
	public String getInformacion(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		File file= new File(carpetaDeTrabajo,arg0);
		StringBuilder strb= new StringBuilder();
		
		if(!file.canRead()) {
			
			throw new GestionFicherosException("No tienes permisos de lectura");
			
		}else {
			
			Date fecha=new Date(file.lastModified());
			SimpleDateFormat fecha1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			strb.append("-------------INFORMACIÓN DEL FICHERO-------------");
			strb.append("El nombre del fichero es "+file.getName());
			
			if (file.isDirectory()) {
				
				strb.append("Es un directorio");
			}else {
				
				strb.append("Es un file");
			}
			
			strb.append("La ruta" + file.getAbsolutePath());
			strb.append("Última modificación-->"+fecha1.format(fecha));
			strb.append(file.isHidden()?" Oculto":"Visible");
			
			if(file.isDirectory()) {
				
				strb.append("Elementos---> "+file.list().length);
				
				long espaciototal=file.getTotalSpace();
				long espaciolibre=file.getFreeSpace();
				long espaciousable=file.getUsableSpace(); 
				
				strb.append("Espacio total---> "+ espaciototal/1000000000 +"bytes");
				strb.append("Espacio total---> "+ espaciolibre/1000000000 +"bytes");
				strb.append("Espacio total---> "+ espaciousable/1000000000 +"bytes");
				
			}else {
				
				long espaciototal=file.getTotalSpace();
				strb.append("Espacio total----> "+espaciototal/8);
			}
			
			String cad=strb.toString();
			return cad;
		}
		
		
	}
	public boolean getMostrarOcultos() {
		
		return false;
	}
	public String getNombreCarpeta() {
		
		return carpetaDeTrabajo.getName();
	}
	public TipoOrden getOrdenado() {
		// TODO Auto-generated method stub
		return ordenado;
	}
	public String[] getTituloColumnas() {
		// TODO Auto-generated method stub
		return null;
	}
	public long getUltimaModificacion(String arg0) throws GestionFicherosException {
		
		return 0;
	}
	
	
	public String nomRaiz(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	public int numRaices() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void renombra(String arg0, String arg1) throws GestionFicherosException {
		
		
	}
	public boolean sePuedeEjecutar(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean sePuedeEscribir(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean sePuedeLeer(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}
	public void setColumnas(int arg0) {
		// TODO Auto-generated method stub
		columnas=arg0;
	}
	public void setDirCarpeta(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		File file = new File(arg0);
		if(!file.exists()) {
			throw new GestionFicherosException("La ruta" +file.getAbsolutePath()+ " no existe");
		}
		if(!file.isDirectory()) {
			throw new GestionFicherosException("ERROR" +file.getAbsolutePath()+ " no existe el directorio");
	}
		carpetaDeTrabajo = file;
		actualiza();
		if(!file.canRead()) {
			throw new GestionFicherosException("No tienes permisos de letura");
		}
		}
		
	public void setFormatoContenido(FormatoVistas arg0) {
		// TODO Auto-generated method stub
		
	}
	public void setMostrarOcultos(boolean arg0) {
		// TODO Auto-generated method stub
		
	}
	public void setOrdenado(TipoOrden arg0) {
		// TODO Auto-generated method stub
		
	}
	public void setSePuedeEjecutar(String arg0, boolean arg1) throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}
	public void setSePuedeEscribir(String arg0, boolean arg1) throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}
	public void setSePuedeLeer(String arg0, boolean arg1) throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}
	public void setUltimaModificacion(String arg0, long arg1) throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}
	}

