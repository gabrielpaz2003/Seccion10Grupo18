import java.io.IOException;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

import dataAccessLayer.EmbeddedNeo4j;

import org.json.simple.JSONArray;

import java.text.Normalizer;
import java.util.regex.Pattern;


/**
 * Servlet implementation class SaveClientServlet
 */
@WebServlet("/SaveClientServlet")
public class SaveClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
	 	response.setContentType("application/json");
	 	response.setCharacterEncoding("UTF-8");
	 	JSONObject myResponse = new JSONObject();
	 	
	 	JSONArray insertionResult = new JSONArray();
	 	
	 	String nombreCliente = request.getParameter("nombreCliente");
	 	String apellidoCliente = request.getParameter("apellidoCliente");
	 	String edadCliente = request.getParameter("edadCliente");

		var primerasTresLetras = apellidoCliente.substring(0, 3);
		String idCliente = primerasTresLetras + edadCliente;
	 	

		myResponse.put("nombreCliente", nombreCliente);
		myResponse.put("apellidoCliente", apellidoCliente);
		myResponse.put("edadCliente", edadCliente);
		myResponse.put("idCliente", idCliente);

	 	
	 	 try ( EmbeddedNeo4j dataBase = new EmbeddedNeo4j( "neo4j+s://40c6bfa9.databases.neo4j.io", "neo4j", "tojG1xseDQwqu_DVyu9g1VAzGfM5COUKbLn9hs2vBIs")) 
	        {
			 	String myResultTx = dataBase.insertClient(nombreCliente, apellidoCliente, Integer.parseInt(edadCliente), idCliente);
	        	
			 	myResponse.put("resultado", myResultTx);
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				myResponse.put("resultado", "Error: " + e.getMessage());
			}



			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
