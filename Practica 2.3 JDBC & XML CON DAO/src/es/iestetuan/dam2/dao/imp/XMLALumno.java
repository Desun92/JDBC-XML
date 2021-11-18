package es.iestetuan.dam2.dao.imp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import es.iestetuan.dam2.dao.IAlumno;
import es.iestetuan.dam2.dao.vo.Alumno;
import es.iestetuan.dam2.GestorConfiguracion;


public class XMLALumno implements IAlumno{
	
	public void consultarAlumno(int nia) {
		
		Alumno devolver = new Alumno();
		
		try {
		
			File fichero = new File(GestorConfiguracion.getInfoAtributoConfiguracion("destino"));
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fichero);
			doc.getDocumentElement().normalize();
			NodeList listaAlumnos = doc.getElementsByTagName("alumno");
			
			for(int i=0; i<listaAlumnos.getLength(); i++) {
				
				Node nodoAlumno = listaAlumnos.item(i);
				Element alumno = (Element) nodoAlumno;
					
					String id = alumno.getAttribute("id");
					long niaComparar=Long.parseLong(id);
					
					if(niaComparar == nia) {
						NodeList contenidoNombre = alumno.getElementsByTagName("nombre");
						String nombreAlumno = contenidoNombre.item(0).getTextContent();
						
						NodeList contenidoApellido1 = alumno.getElementsByTagName("apellido1");
						String apellido1Alumno = contenidoApellido1.item(0).getTextContent();
						
						NodeList contenidoApellido2 = alumno.getElementsByTagName("apellido2");
						String apellido2Alumno = contenidoApellido2.item(0).getTextContent();
						
						devolver.setNombre(nombreAlumno);
						devolver.setApellido1(apellido1Alumno);
						devolver.setApellido2(apellido2Alumno);
						
						break;
					}
					
				
			}
		
		}
		
		catch(ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		catch(IOException e) {
			e.printStackTrace();
		}
		
		catch(SAXException e) {
			e.printStackTrace();
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(devolver.toString());
	}
	public List<Alumno> consultarAlumnos(){
		
		List<Alumno> listaAlumnosDevolver = new ArrayList<Alumno>();
		
		try {
				
				File fichero = new File(GestorConfiguracion.getInfoAtributoConfiguracion("destino"));
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fichero);
				doc.getDocumentElement().normalize();
				NodeList listaAlumnos = doc.getElementsByTagName("alumno");
				
				for(int i=0; i<listaAlumnos.getLength(); i++) {
					
					Alumno alumnoAnyadir = new Alumno();
					
					Node nodoAlumno = listaAlumnos.item(i);
					Element alumno = (Element) nodoAlumno;
					
					String id = alumno.getAttribute("nia");
					int nia = Integer.parseInt(id);
					
					NodeList contenidoNombre = alumno.getElementsByTagName("nombre");
					String nombre = contenidoNombre.item(0).getTextContent();
					
					NodeList contenidoApellido1 = alumno.getElementsByTagName("apellido1");
					String apellido1 = contenidoApellido1.item(0).getTextContent();
					
					NodeList contenidoApellido2 = alumno.getElementsByTagName("apellido2");
					String apellido2 = contenidoApellido2.item(0).getTextContent();
					
					alumnoAnyadir.setNia(nia);
					alumnoAnyadir.setNombre(nombre);
					alumnoAnyadir.setApellido1(apellido1);
					alumnoAnyadir.setApellido2(apellido2);
					
					listaAlumnosDevolver.add(alumnoAnyadir);
					
				}
			}	
		
		catch (Exception e) {
			e.printStackTrace();
		}
		return listaAlumnosDevolver;
	}
	public void guardarAlumnos(List<Alumno> alumnos) {
		
		Document documento = null;
		
		try {
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			DOMImplementation domImpl = dBuilder.getDOMImplementation();
			documento = domImpl.createDocument(null, "alumnos", null);
			
			//Crear nodo raiz
		//	Element raiz = documento.createElement("alumno");
		//	documento.getDocumentElement().appendChild(raiz);
			
			//Informacion nodos internos
			Element nodoAlumno = null;
			Element nodoDatos = null;
			Text texto = null;
			Attr atributo = null;
			
			for(Alumno alumno : alumnos) {
				
				//Creo la cabecera
				nodoAlumno = documento.createElement("alumno");
				atributo = documento.createAttribute("nia");
				documento.getDocumentElement().appendChild(nodoAlumno);
				
				//Le asigno valor al atributo y lo asigno al elemento cabecera
				atributo.setValue(String.valueOf(alumno.getNia()));
				nodoAlumno.setAttribute(atributo.getName(), atributo.getValue());;
				
				nodoDatos = documento.createElement("nie");
				nodoAlumno.appendChild(nodoDatos);
				texto=documento.createTextNode(alumno.getNie());
				nodoDatos.appendChild(texto);
				
				//Creo un elemento hijo y lo añado al padre
				nodoDatos = documento.createElement("nombre");
				nodoAlumno.appendChild(nodoDatos);
				//Le asigno valor
				texto=documento.createTextNode(alumno.getNombre());
				nodoDatos.appendChild(texto);
				
				// Y así con todos los restantes
				nodoDatos = documento.createElement("apellido1");
				nodoAlumno.appendChild(nodoDatos);
				texto=documento.createTextNode(alumno.getApellido1());
				nodoDatos.appendChild(texto);
				
				nodoDatos = documento.createElement("apellido2");
				nodoAlumno.appendChild(nodoDatos);
				texto=documento.createTextNode(alumno.getApellido2());
				nodoDatos.appendChild(texto);
				
				nodoDatos = documento.createElement("email");
				nodoAlumno.appendChild(nodoDatos);
				texto=documento.createTextNode(alumno.getEmail());
				nodoDatos.appendChild(texto);
				
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(documento);
			StreamResult result = new StreamResult(new File(GestorConfiguracion.getInfoAtributoConfiguracion("destino")));
			transformer.transform(source, result);
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void altaAlumno(Alumno alumno) {
		
		List<Alumno> listaAlumnos = consultarAlumnos();
		boolean esta = false;
		
		for(Alumno alumnito : listaAlumnos) {
			if(alumnito.getNia() == alumno.getNia()) {
				esta = true;
				break;
			}
		}
		
		if(!esta) {
			listaAlumnos.add(alumno);
			listaAlumnos.sort(new Comparator<Alumno>() {
				public int compare(Alumno a, Alumno b) {
					return a.getApellido1().compareTo(b.getApellido1());
				}
			});
			guardarAlumnos(listaAlumnos);
		}		
	}
	public void bajaAlumno(int id) {
		
		List<Alumno> listaAlumnos = consultarAlumnos();
		int indice = 0;
		boolean esta = false;
		
		for(Alumno alumnito : listaAlumnos) {
			if(alumnito.getNia() == id) {
				esta = true;
				break;
			}
			indice++;
		}
		
		if(esta) {
			listaAlumnos.remove(indice);
			guardarAlumnos(listaAlumnos);
		}
	}
	public void modificarAlumno(Alumno alumno) {
		
		List<Alumno> listaAlumnos = consultarAlumnos();
		boolean esta = false;
		int indice = 0;
		
		for(Alumno alumnito : listaAlumnos) {
			if(alumnito.getNia() == alumno.getNia()) {
				esta = true;
				break;
			}
			indice++;
		}
		
		if(esta) {
			listaAlumnos.set(indice, alumno);
			listaAlumnos.sort((Alumno a, Alumno b) -> a.getApellido1().compareTo(b.getApellido1()));
			guardarAlumnos(listaAlumnos);
		}
		
	}
	
}
