package es.iestetuan.dam2;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class GestorConfiguracion {

static Properties propiedades;
	
	public static Properties cargarConfiguracion() throws Exception{
		
		if(propiedades==null) {
			propiedades = new Properties();
			InputStream in = new FileInputStream("archivos/confi.properties");
			propiedades.load(in);
		}
		return propiedades;
	}
	
	public static String getInfoAtributoConfiguracion(String clave) throws Exception{
		
		String propiedad = cargarConfiguracion().getProperty(clave);
		return propiedad;
		
	}
}
