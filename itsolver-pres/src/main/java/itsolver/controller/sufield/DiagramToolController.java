package itsolver.controller.sufield;

import itsolver.controller.utils.GenericZkComposer;
import itsolver.model.entity.InventiveStandard;
import itsolver.model.entity.SuField;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Html;

public class DiagramToolController extends GenericZkComposer {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -5919028540135517278L;	
	
	public Html flexDiagramToolHTML;
	
	public Button btnAlgorithmStandars;
	public Div divDiagramTool;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {	
		super.doAfterCompose(comp);
		
		
		SuField project = (SuField)desktop.getAttribute("currentProject");
		
		Long projId = project.getId();
		String standardNumber="";
		
		InventiveStandard inventiveStandard = project.getInventiveStandard();
		if(inventiveStandard!=null) {
			standardNumber = inventiveStandard.getInventiveStandardNumber();
		}	
		
		flexDiagramToolHTML.setContent("<script type='text/javascript' src='/itsolver-war/sufield/flex-resources/swfobject.js'></script>"+
							"<script type='text/javascript'>"+
							    "var swfVersionStr = '10.0.0';"+
							    "var xiSwfUrlStr = '/itsolver-war/sufield/flex-resources/playerProductInstall.swf';"+
							    "var flashvars = {};"+
								"flashvars.projId = "+projId+";"+
								"flashvars.standardNumber ='"+standardNumber+"';"+
							    "var params = {};"+
							    "params.quality = 'high';"+
							    "params.bgcolor = '#ffffff';"+
							    "params.allowscriptaccess = 'sameDomain';"+
							    "params.allowfullscreen = 'true';"+
							    "var attributes = {};"+
							    "attributes.id = 'DiagramToolSuField';"+
							    "attributes.name = 'DiagramToolSuField';"+
							    "attributes.align = 'middle';"+
							    "swfobject.embedSWF("+
							        "'/itsolver-war/sufield/flex-resources/DiagramToolSuField.swf', 'flashContent',"+ 
							        "'770', '525',"+ 
							        "swfVersionStr, xiSwfUrlStr,"+ 
							        "flashvars, params, attributes);"+	
								"swfobject.createCSS('#flashContent', 'display:block;text-align:left;');"+
							"</script>"+
							"<div id='flashContent'>"+
							"</div>");
	}
	
}