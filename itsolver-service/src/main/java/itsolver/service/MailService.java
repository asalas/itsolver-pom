package itsolver.service;

import java.io.File;

public interface MailService {

	/**
	 * Enviar email usando un archivo HTML como plantilla
	 * 
	 * @param recipients
	 * @param htmlTemplate
	 */
	public void sendMailHTMLTemplate(String recipients, File htmlTemplate);
	
	/**
	 * Envia email usando texto plano, dicho texto puede incluir etiquetas HTML
	 * 
	 * @param recipients
	 * @param txtPlainMessage
	 */
	public void sendMailTxtPlain(String recipients, File txtPlainMessage);
	
}
