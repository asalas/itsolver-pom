package itsolver.controller.report;

import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.ContradictionProject;
import itsolver.model.entity.Project;
import itsolver.model.entity.SuField;
import itsolver.service.ProjectMgmtService;
import itsolver.utils.CEncrypt;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringBufferInputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 * Servlet implementation class ProjectToReport
 */
@SuppressWarnings("deprecation")
public class ProjectToReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ProjectMgmtService projectMgmtService;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectToReport() {
        super();
        projectMgmtService = ServiceLocator.getProjectMgmtService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//abc es el parametro con el Id del proyecto
		if ( request.getParameter("abc") != null ){ 
			String strProjecId	= request.getParameter("abc");
			String language = request.getParameter("lang");
			
			Long projectId = -1L;
			
			try{
				projectId = Long.parseLong(CEncrypt.decryptString(strProjecId));				
			}catch(NumberFormatException exception){
				printHtml(response, "Error illegal argument");
			}
			
			if ( projectId > 0){
				Project project = projectMgmtService.getProject(projectId);
				
				if( project instanceof ContradictionProject ){
					project = (ContradictionProject) project;
				}else if( project instanceof SuField ){
					project = (SuField) project;
				}
				
				String projectReport = generateReport(request, response, project, language);
				
				//Si esta asignado el parametro html significa que es un reporte en HTML
				if ( request.getParameter("html") != null ){
					response.setContentType("text/html");
					generateHtmlReport(response, projectReport);
				}else{
					generatePdfReport(response, projectReport);
				}
				
			}else{
				printHtml(response, "Project is required");
			}
		}else{
			printHtml(response, "Missed argument");
		}
		
	}

	private void generatePdfReport(HttpServletResponse response,
			String projectReport) {
		response.setContentType("application/pdf");
		response.setHeader("Expires", "0");
		//response.setHeader("Cache-Control", "no-cache");
		//response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		//response.setHeader("Content-Disposition","attachment; filename=\"example.pdf\"");
		try {
			doReport(response.getOutputStream(), projectReport);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void generateHtmlReport(HttpServletResponse response,
			String projectReport) throws IOException {
		printHtml(response, projectReport);
	}

	private void printHtml(HttpServletResponse response, String message) throws IOException {
		PrintWriter writer = response.getWriter();
		writer.print("<br/>" + message);
		writer.flush();
	}

	private String generateReport(HttpServletRequest request,
		HttpServletResponse response, Project project, String language) throws ServletException, IOException {
		BufferedHttpResponseWrapper wrapper = new BufferedHttpResponseWrapper(response);
		
		ServletContext context = request.getSession().getServletContext();
		
		String url = response.encodeRedirectURL("/report/report-template-EN.jsp");
		if(language.equals("ES")) {
			url = response.encodeRedirectURL("/report/report-template-ES.jsp");
		}// TODO: pendiente el frances		
		
		context.setAttribute("project", project);
		RequestDispatcher dispatcher = context.getRequestDispatcher(url);	
		/* must use include. With forward the RequestDispatcher seems to hold an internal state
		   which prevents to do a forward after this to return to the webclient */
		dispatcher.include(request, wrapper);
		String report = new String(wrapper.getOutput());
		return report;
	}
		
	private void doReport(OutputStream  outputStream, String pdfContent ) throws Exception{		
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	    Document parse = builder.parse(new StringBufferInputStream(pdfContent));

	    ITextRenderer renderer = new ITextRenderer();
	    renderer.setDocument(parse, null);
	    renderer.layout();	    
		renderer.createPDF(outputStream);		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}