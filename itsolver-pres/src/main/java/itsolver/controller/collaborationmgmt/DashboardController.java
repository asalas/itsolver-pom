package itsolver.controller.collaborationmgmt;

import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Div;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Separator;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;

import itsolver.controller.utils.GenericZkComposer;
import itsolver.controller.utils.ItSolverEvents;
import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.Dashboard;
import itsolver.model.entity.Notification;
import itsolver.service.ProjectMgmtService;
import itsolver.utils.CurrentSession;

public class DashboardController extends GenericZkComposer {
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 3103320440419137528L;
	
	private ProjectMgmtService projectMgmtService;
	private static  ListitemRenderer _defRend;
	private static String lblUser = null;
	private static String lblProject = null;
	private static String lblDescription = null;
	
	public Listbox lbxNotifications;
	
	public Label msgDashboard;
	public Toolbarbutton btnLatestUpdates;
	
	public Div divDashboard;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {	
		super.doAfterCompose(comp);		
		_defRend = new ItemRender(session);
		this.projectMgmtService = ServiceLocator.getProjectMgmtService();
		CurrentSession currentSession = (CurrentSession)session.getAttribute("currentSession");
		if (currentSession != null && currentSession.getProfile() != null){
			Dashboard dashboard = currentSession.getProfile().getDashboard();
			if (dashboard != null  && dashboard.getNotificationList() != null && dashboard.getNotificationList().size() > 0 ){			
				ListModel model = new SimpleListModel(dashboard.getNotificationList().toArray());
				lbxNotifications.setModel(model);
				lbxNotifications.setItemRenderer(getItemRenderer());
			}
		}
		
		// cambiar de idioma
		divDashboard.addEventListener(ItSolverEvents.CHANGE_LANGUAGE_HOME, new EventListener() {
			public void onEvent(Event event) throws Exception {
				String lang = (String)event.getData();
				setLanguage(lang);
				updateLabelLanguage(session);				
				//TODO HACER MUCHA REFACTORIZACION EN ESTA SECCION TRATAR DE CREAR EL COMPONETE DEL ItemRenderer COMO UN ZUL
				lbxNotifications.getChildren().clear();
				CurrentSession currentSession = (CurrentSession)session.getAttribute("currentSession");
				Dashboard dashboard = currentSession.getProfile().getDashboard();
				if (dashboard != null  && dashboard.getNotificationList() != null && dashboard.getNotificationList().size() > 0 ){			
					ListModel model = new SimpleListModel(dashboard.getNotificationList().toArray());
					lbxNotifications.setModel(model);
					lbxNotifications.setItemRenderer(getItemRenderer());
				}
			}
		});
	}
	
	
	public ListitemRenderer getItemRenderer() {
		return _defRend;
	}
	
	private static void updateLabelLanguage(Session _session ){
		lblUser = "User:";
		lblProject = "Project:";
		lblDescription = "Description:";			
		 String sessLang = (String) _session.getAttribute("preflang");
	        if (!Strings.isEmpty(sessLang)) {
	        	if (sessLang == "ES"){
	        		lblUser = "Usario:";
	    			lblProject = "Proyecto:";
	    			lblDescription = "Descripción:";
	        	}else if ( sessLang == "EN"){
	        		lblUser = "User:";
	    			lblProject = "Project:";
	    			lblDescription = "Description:";
	        	}else if (sessLang == "FR"){
	        		lblUser = "Usager:";
	    			lblProject = "Projet:";
	    			lblDescription = "Description:";
	        	}
	        }
	}
		
	private static class ItemRender implements ListitemRenderer, java.io.Serializable {		
		private Session session;
		
		public ItemRender(Session _session){
			session = _session;
		}
		
		public void render(Listitem item, Object data) {
			Notification notification = (Notification) data;
			item.setValue(notification);
			//item.setLabel(notification.getProjectName());
			if ( session != null ){
				updateLabelLanguage(session);
			}
			Groupbox groupbox = new Groupbox();			
			groupbox.setSclass("notification_item");
			Hbox hboxConteiner = new Hbox();
			hboxConteiner.setParent(groupbox);
			hboxConteiner.setAlign("center");
			hboxConteiner.setWidth("100%");
			
			Vbox vboxImage = new Vbox();
			vboxImage.appendChild(new Image("/assets/image/sample/desconocido.gif"));
			vboxImage.setParent(hboxConteiner);
			
			Vbox vboxNotificationDetail = new Vbox();
			
			Hbox hboxUserName = new Hbox();			
			Label labelUserName = new Label(lblUser);
			labelUserName.setSclass("dashboard_item_caption");
			hboxUserName.appendChild(labelUserName);
			hboxUserName.appendChild(new Label(notification.getFullName()));
			hboxUserName.setParent(vboxNotificationDetail);
			
			Hbox hboxProjectName = new Hbox();			
			Label labelProjectName = new Label(lblProject);
			labelProjectName.setSclass("dashboard_item_caption");
			hboxProjectName.appendChild(labelProjectName);
			hboxProjectName.appendChild(new Label(notification.getProjectName()));
			hboxProjectName.setParent(vboxNotificationDetail);
			
			Hbox hboxDescription = new Hbox();			
			Label labelDescription = new Label(lblDescription);
			labelDescription.setSclass("dashboard_item_caption");
			hboxDescription.appendChild(labelDescription);
			hboxDescription.appendChild(new Label(notification.getDescription()));
			hboxDescription.setParent(vboxNotificationDetail);
			
			Hbox hboxDate = new Hbox();
			//Label labelProjectName = new Label("Project:");
			//labelProjectName.setSclass("dashboard_item_caption");
			//hboxProjectName.appendChild(labelProjectName);
			hboxDate.appendChild(new Label(notification.getCreatedOn().toString()));
			hboxDate.setParent(vboxNotificationDetail);
			
			vboxNotificationDetail.setParent(hboxConteiner);			
			Listcell listcell = new Listcell();
			
			Hbox main  = new Hbox();
			
			groupbox.setParent(main);
			main.setAlign("center");
			main.setWidth("100%");
			
			main.setParent(listcell);
			
			listcell.setParent(item);
			
			Separator separator = new Separator();
			separator.setSclass("separator_dashboard_simple");
			separator.setParent(listcell);
			
			Separator separator2 = new Separator();
			separator2.setSclass("separator_dashboard");
			separator2.setParent(listcell);
			
			Separator separator3 = new Separator();
			separator3.setSclass("separator_dashboard_simple");
			separator3.setParent(listcell);
			
			/*DemoItem di = (DemoItem) data;
			Listcell lc = new Listcell();
			item.setValue(di);
			lc.setHeight("30px");
			lc.setImage(di.getIcon());
			item.setId(di.getId());
			lc.setLabel(di.getLabel());
			lc.setParent(item);*/
		}
	};


}
