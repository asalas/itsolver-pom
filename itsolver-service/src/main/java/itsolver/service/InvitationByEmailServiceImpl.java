package itsolver.service;

import itsolver.model.dao.InvitationByEmailDAO;
import itsolver.model.entity.InvitationByEmail;
import itsolver.model.entity.Profile;
import itsolver.model.entity.Project;
import itsolver.utils.HiloIdGenerator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class InvitationByEmailServiceImpl implements InvitationByEmailService {
	
	private static final String EMAIL_MESSAGE = "#MESSAGE";

	private static final String PROJECT_NAME = "#PROJECTNAME";

	private static final String PROFILE_NAME = "#PROFILENAME";

	private static final String HILO_ID = "#HILOID";
	
	private static final String EMAIL_FROM = "noreply.itsolver@gmail.com";
	
	private static final String EMAIL_FROM_PASS = "itz0lver";

	private InvitationByEmailDAO invitationByEmailDAO;
	
	private URL location;   
    private ClassLoader loader = InvitationByEmailServiceImpl.class.getClassLoader();
    private Properties props;
    private Session session;
    private Message message;
    private BodyPart textBodyPart;
    private Multipart mimeMultipart;
    private MimeBodyPart mimeBodyPart;
    private Transport transport;
    private HiloIdGenerator hiloIdGenerator;
    

	
	public void sendMailInvitations(String recipients, String emailMessage, Profile profileHost, Project project) 
			throws Exception {	
		
	    List<String> allRecipients = stuffRecipients(recipients);   
	    
	    for(String recipient: allRecipients) {
	    	sendMail(recipient, emailMessage, profileHost, project);
	    }		
	}

	private void sendMail(String recipient, String emailMessage, Profile profileHost, Project project)
			throws IOException, FileNotFoundException, MessagingException,
			AddressException, NoSuchProviderException {
		
		location = loader.getResource("itsolver/service/");
		String path = location.getFile();
		
		props = new Properties();
		props.load(new FileInputStream(path+"email/email.properties"));

		hiloIdGenerator = new HiloIdGenerator(33);
		String hiloId = hiloIdGenerator.nextString();		
		
		// get the session with the properties defined
		session = Session.getDefaultInstance(props);
		
		// create a empty message
		message  = new MimeMessage(session);
		// fill the attributes and the content
		// subject
		message.setSubject("Correo HTML");
		// message from
		message.setFrom(new InternetAddress(EMAIL_FROM));
		
		// message to			
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		
		//read the HTML file
		String htmlMessage = "";
		String line;
		BufferedReader bufferedReader = new BufferedReader(
				new FileReader(path+"email/emailtemplate.html")); 			
		
		while((line = bufferedReader.readLine()) != null) {
			
			if(line.contains(PROFILE_NAME)) {
				line = line.replace(PROFILE_NAME, profileHost.getFullName()); 					
			}else if(line.contains(PROJECT_NAME)) {
				line = line.replace(PROJECT_NAME, project.getProjectName());
			}else if(line.contains(HILO_ID)) {
				line = line.replace(HILO_ID, hiloId);
			}else if(line.contains(EMAIL_MESSAGE)) {
				line = line.replace(EMAIL_MESSAGE, emailMessage);				
			}
			
			htmlMessage+=line; 				
		}
		
		bufferedReader.close();
		
		textBodyPart = new MimeBodyPart();
		textBodyPart.setContent(htmlMessage, "text/html");
		
		mimeMultipart = new MimeMultipart("related");
		mimeMultipart.addBodyPart(textBodyPart);
		
		// process the image
		mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.attachFile(path+"email/itsolverlogo.png");
		mimeBodyPart.setHeader("Content-ID", "<figure1>");
		
		mimeMultipart.addBodyPart(mimeBodyPart);
		
		message.setContent(mimeMultipart);
		
		try {
			// send the message 			
			transport = session.getTransport("smtp");
			transport.connect(EMAIL_FROM, EMAIL_FROM_PASS);
			// don't forget this
			message.saveChanges();
			transport.sendMessage(message, message.getAllRecipients());
		}finally {
			transport.close();
		}		
		
		// persist invitation in the DB
		InvitationByEmail invitationByEmail = new InvitationByEmail();
    	invitationByEmail.setHiloId(hiloId);
    	invitationByEmail.setProject(project);
    	invitationByEmail.setSendFrom(profileHost);
    	invitationByEmail.setEmail(recipient);
    	invitationByEmail.setMessage(emailMessage);
    	invitationByEmail.setIsAccepted(false);
    	
    	invitationByEmailDAO.persist(invitationByEmail);		
	}	

	/**
	 * Este metodo prepara las direcciones de email a las que se las enviara la invitacion
	 * 
	 * @param addresses
	 * @return
	 * @throws Exception
	 */
	private List<String> stuffRecipients(String addresses) throws Exception {
		
		String[] recipients = addresses.split(",");
		
		List<String> cleanRecipients = new ArrayList<String>();
		
		for(String recipient: recipients) {
			cleanRecipients.add(recipient.trim());
		}
		
		return cleanRecipients;
	}	
	
	
	public InvitationByEmail findByHiloId(String hiloId) {
		return invitationByEmailDAO.findByHiloId(hiloId);
	}
	
	
	public void mergeInvitationByEmail(InvitationByEmail invitationByEmail) {
		invitationByEmailDAO.merge(invitationByEmail);		
	}
	
	// get invitationByEmailDAO
	public InvitationByEmailDAO getInvitationByEmailDAO() {
		return invitationByEmailDAO;
	}

	public void setInvitationByEmailDAO(InvitationByEmailDAO invitationByEmailDAO) {
		this.invitationByEmailDAO = invitationByEmailDAO;
	}

}