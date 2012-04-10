package itsolver.controller.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * This class wraps a HttpServletResponse with a buffered output.
 * You can use this to forward or include a Servlet or JSP page
 * and capture the output from it.
 * 
 * Use getOutput to get the output which was written to the response.
 * Only buffers the Writer. Not the OutputStream !!
 *  
 * @author Joost den Boer
 */
public class BufferedHttpResponseWrapper extends HttpServletResponseWrapper {

	PrintWriter writer = null;
	ByteArrayOutputStream baos = null;

	/**
	 * Constructor for BufferedHttpResponseWrapper.
	 * Create a new buffered Writer
	 * 
	 * @param response The response object to wrap
	 */
	public BufferedHttpResponseWrapper(HttpServletResponse response) {
		super(response);
		baos = new ByteArrayOutputStream();
		writer = new PrintWriter(baos);
	}

	/**
	 * Return the buffered Writer
	 *  
	 * @see javax.servlet.ServletResponse#getWriter()
	 */
	public PrintWriter getWriter() throws IOException {
		return writer;
	}

	/**
	 * Return the output written to the Writer.
	 * To get the output, the Writer must be flushed and closed.
	 * The content is captured by the ByteArrayOutputStream.
	 *  
	 * @return
	 */
	public String getOutput() {
		writer.flush();
		writer.close();
		return baos.toString();
	}
}
