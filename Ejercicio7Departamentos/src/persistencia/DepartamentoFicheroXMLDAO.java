package persistencia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import utilidades.Departamento;
import utilidades.ExcepcionDAO;

public class DepartamentoFicheroXMLDAO implements DepartamentoDAOInterface {
	private String nombreFichero;

	public DepartamentoFicheroXMLDAO(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}

	public void escribirDatos(List<Departamento> listaDepartamento) throws ExcepcionDAO {

		// Construimos el parse
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementacion = builder.getDOMImplementation();
			// Creamos un documento vacio con el elemento raiz
			Document document = implementacion.createDocument(null, "Departamentos", null);
			// Cambiamos la version del xml
			document.setXmlVersion("1.0");
			// Le añadimos la raiz al documento

			// Creamos los diferentes hijos de la raiz
			for (int i = 0; i < listaDepartamento.size(); i++) {
				Element raiz = document.createElement("Departamento");
				document.getDocumentElement().appendChild(raiz);

				// Le pasamos el nombre del nodohijo, el valor, la raiz y el
				// documento
				crearElemento("Nombre", listaDepartamento.get(i).getNombre(), raiz, document);
				crearElemento("Numero", Integer.toString(listaDepartamento.get(i).getNumero()), raiz, document);
				crearElemento("Localidad", listaDepartamento.get(i).getLocalidad(), raiz, document);
			}

			// Creamos la fuente XML
			Source source = new DOMSource(document);

			// creamos el resultado en el documento xml
			Result result = new StreamResult(new java.io.File("Departamento.xml"));

			// Se obtiene un transformer y se realiza la transformacion del
			// arbol al documento xml
			Transformer transformer = TransformerFactory.newInstance().newTransformer();

			transformer.transform(source, result);

			// Mostramos el resultado por pantalla
			// Result console=new StreamResult(System.out);
			// transformer.transform(source, console);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());

		}

	}

	// Metodo para crear los elementos de los hijos
	private void crearElemento(String dato, String valor, Element raiz, Document document) {

		// Creamos el elemento
		Element elem = document.createElement(dato);
		// creamos el nodoTexto con el valor
		Text text = document.createTextNode(valor);
		// Añadimos el elemento a la raiz
		raiz.appendChild(elem);

		// añadimos el texto a el elemento
		elem.appendChild(text);

	}

	@Override
	public List<Departamento> leerDatos() throws ExcepcionDAO {

		// List<Departamento> lista=new ArrayList<Departamento>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		List<Departamento> listaDepartamentos = new ArrayList<Departamento>();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(nombreFichero));
			document.getDocumentElement().normalize();

			NodeList departamentos = document.getElementsByTagName("Departamento");

			for (int i = 0; i < departamentos.getLength(); i++) {
				Node dep = departamentos.item(i);

				if (dep.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) dep;
					listaDepartamentos.add(new Departamento(getNodo("Nombre", elemento),
							Integer.parseInt(getNodo("Numero", elemento)), getNodo("Localidad", elemento)));

				}

			}

		} catch (Exception e) {
			throw new ExcepcionDAO(e.getMessage(), e);
		}
		return listaDepartamentos;
	}

	private String getNodo(String etiqueta, Element elemento) {

		NodeList nodo = elemento.getElementsByTagName(etiqueta).item(0).getChildNodes();
		Node valornodo = (Node) nodo.item(0);
		return valornodo.getNodeValue();
	}

	@Override
	public void actualizarDatos(Departamento d) throws ExcepcionDAO {
		// DepartamentoDAOInterface daoBinario=new
		// DepartamentoFicheroBinarioDAO(nombreFichero);
		List<Departamento> listaParaActualizar = leerDatos();

		for (int i = 0; i < listaParaActualizar.size(); i++) {
			if (listaParaActualizar.get(i).getNumero() == d.getNumero()) {
				listaParaActualizar.get(i).setNombre(d.getNombre());
				listaParaActualizar.get(i).setLocalidad(d.getLocalidad());
			}
		}

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementacion = builder.getDOMImplementation();
			// Creamos un documento vacio con el elemento raiz
			Document document = implementacion.createDocument(null, "Departamentos", null);
			// Cambiamos la version del xml
			document.setXmlVersion("1.0");
			// Le añadimos la raiz al documento

			// Creamos los diferentes hijos de la raiz
			for (int i = 0; i < listaParaActualizar.size(); i++) {
				Element raiz = document.createElement("Departamento");
				document.getDocumentElement().appendChild(raiz);

				// Le pasamos el nombre del nodohijo, el valor, la raiz y el
				// documento
				crearElemento("Nombre", listaParaActualizar.get(i).getNombre(), raiz, document);
				crearElemento("Numero", Integer.toString(listaParaActualizar.get(i).getNumero()), raiz, document);
				crearElemento("Localidad", listaParaActualizar.get(i).getLocalidad(), raiz, document);
			}

			// Creamos la fuente XML
			Source source = new DOMSource(document);

			// creamos el resultado en el documento xml
			Result result = new StreamResult(new java.io.File("Departamento.xml"));

			// Se obtiene un transformer y se realiza la transformacion del
			// arbol al documento xml
			Transformer transformer = TransformerFactory.newInstance().newTransformer();

			transformer.transform(source, result);

			// Mostramos el resultado por pantalla
			// Result console=new StreamResult(System.out);
			// transformer.transform(source, console);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());

		}
	}

	@Override
	public void borrarDatos(int n) throws ExcepcionDAO {
		// TODO Auto-generated method stub

	}

	@Override
	public void borrarTodosLosDatos() throws ExcepcionDAO {
		// TODO Auto-generated method stub
		
	}

}
