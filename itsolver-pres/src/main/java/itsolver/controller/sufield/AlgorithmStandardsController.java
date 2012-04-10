package itsolver.controller.sufield;

import itsolver.controller.utils.GenericZkComposer;
import itsolver.controller.utils.ItSolverEvents;
import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.AlgorithmQuestion;
import itsolver.model.entity.InventiveStandard;
import itsolver.service.AlgorithmStandardsService;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.SimpleTreeModel;
import org.zkoss.zul.SimpleTreeNode;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

public class AlgorithmStandardsController extends GenericZkComposer {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -4747260940227492141L;

	public Listbox questionsListbox;

	private List<AlgorithmQuestion> listQuestions = new ArrayList<AlgorithmQuestion>();

	private AlgorithmStandardsService algorithmStandardsService;

	public Window algorithmStandardsWin;

	public Tree inventiveStandardsTree;

	public Panel inventiveStandardPanel;

	private Vbox vboxParentSolution;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		algorithmStandardsService = ServiceLocator
				.getAlgorithmStandardsService();

		AlgorithmQuestion firstQuestion = algorithmStandardsService
				.getAlgorithmQuestionById("Q3");
		listQuestions.add(firstQuestion);

		questionsListbox.setModel(new ListModelList(listQuestions));
		questionsListbox.setItemRenderer(new QuestionItemRenderer());

		// parent - vbox donde esta el boton que muestra esta ventana
		vboxParentSolution = (Vbox) comp.getParent();

	}

	private void cleanInvStandardPanel() {
		List<Component> childrens = inventiveStandardPanel.getChildren();
		if (childrens != null) {
			if (childrens.size() != 0) {
				inventiveStandardPanel.removeChild(childrens.get(0));
			}
		}
	}

	class QuestionItemRenderer implements ListitemRenderer {
		public void render(Listitem item, Object data) throws Exception {

			AlgorithmQuestion question = (AlgorithmQuestion) data;

			Hbox hboxQuestion = new Hbox();

			Label lblQuestion = new Label(question.getQuestion());
			lblQuestion.setParent(hboxQuestion);

			int indexItem = item.getIndex();

			RadioAnswerListener radioAnswerListener = new RadioAnswerListener(
					indexItem, question);

			Radio radioYes = new Radio();
			radioYes.setValue(question.getYesQuestionNumber());
			radioYes.setLabel("Yes");
			radioYes.addEventListener(Events.ON_CLICK, radioAnswerListener);

			Radio radioNo = new Radio();
			radioNo.setValue(question.getNoQuestionNumber());
			radioNo.setLabel("No");
			radioNo.addEventListener(Events.ON_CLICK, radioAnswerListener);

			Radiogroup radioGrpAnswers = new Radiogroup();
			radioGrpAnswers.appendChild(radioYes);
			radioGrpAnswers.appendChild(radioNo);
			radioGrpAnswers.setParent(hboxQuestion);

			Listcell cell = new Listcell();
			hboxQuestion.setParent(cell);
			cell.setParent(item);
		}
	}

	// end QuestionItemRenderer

	class RadioAnswerListener implements EventListener {

		private int indexItem;
		private AlgorithmQuestion currentQuestion;

		public RadioAnswerListener(int indexItem,
				AlgorithmQuestion currentQuestion) {
			this.indexItem = indexItem;
			this.currentQuestion = currentQuestion;
		}

		
		public void onEvent(Event event) throws Exception {

			Radio radioAnswer = (Radio) event.getTarget();
			String nextQuestionId = radioAnswer.getValue();

			if (nextQuestionId != null && !nextQuestionId.isEmpty()) {
				AlgorithmQuestion foundQuestion = algorithmStandardsService
						.getAlgorithmQuestionById(nextQuestionId);

				List<AlgorithmQuestion> tempQuestions = new ArrayList<AlgorithmQuestion>();

				for (int i = 0; i <= indexItem; i++) {
					tempQuestions.add(listQuestions.get(i));
				}

				tempQuestions.add(foundQuestion);

				listQuestions.clear();
				listQuestions.addAll(tempQuestions);

				questionsListbox.setModel(new ListModelList(listQuestions));
				// se limpia el arbol de los estandares de inventiva
				inventiveStandardsTree.clear();
				// se limpia el panel que muestra el estandar de inventiva
				// seleccionado del arbol
				cleanInvStandardPanel();

			} else {

				String lblRadio = radioAnswer.getLabel();
				List<InventiveStandard> inventiveStandardsList = null;

				if (lblRadio.equalsIgnoreCase("yes")) {
					inventiveStandardsList = currentQuestion
							.getIfYesStandardsList();
				} else if (lblRadio.equalsIgnoreCase("no")) {
					inventiveStandardsList = currentQuestion
							.getIfNoStandardsList();
				}

				// arbol de los estandares de inventiva
				SimpleTreeNode root = new SimpleTreeNode("ROOT",
						inventiveStandardsList);
				SimpleTreeModel treeModel = new SimpleTreeModel(root);

				inventiveStandardsTree.setModel(treeModel);
				inventiveStandardsTree
						.setTreeitemRenderer(new InventiveStandardTreeRenderer());
			}

		}

	}

	// end RadioAnswerListener

	class InventiveStandardTreeRenderer implements TreeitemRenderer {
		
		public void render(Treeitem item, Object data) throws Exception {
			InventiveStandard inventiveStandard = (InventiveStandard) data;

			Treecell tcInventiveStandard = new Treecell(inventiveStandard
					.getStandardNumber());

			Treerow treeRow = null;

			if (item.getTreerow() == null) {
				treeRow = new Treerow();
				treeRow.setParent(item);
			} else {
				treeRow = item.getTreerow();
				treeRow.getChildren().clear();
			}

			tcInventiveStandard.setParent(treeRow);
			item.setOpen(false);

			treeRow.addEventListener(Events.ON_CLICK,
					new InventiveStandardTreeListener());
		}
	}

	// end InventiveStandardTreeRenderer

	class InventiveStandardTreeListener implements EventListener {
		private InventiveStandard inventiveStandard;

		
		public void onEvent(Event event) throws Exception {

			Treerow treeRow = (Treerow) event.getTarget();

			List children = treeRow.getChildren();

			Treecell treeCell = (Treecell) children.get(0);

			inventiveStandard = algorithmStandardsService
					.getInventiveStandardById(treeCell.getLabel());

			if (inventiveStandard != null) {

				cleanInvStandardPanel();

				Div div = new Div();
				div.setHeight("240px");
				div.setStyle("overflow:auto");

				Vbox vbox = new Vbox();
				vbox.setSpacing("5px");
				vbox.setStyle("overflow:auto");

				// numero de estandar
				Label lblInvStandardNumber = new Label(inventiveStandard
						.getStandardNumber());
				lblInvStandardNumber.setStyle("font-weight:bold");
				lblInvStandardNumber.setParent(vbox);
				// descripcion
				Label lblInvStandardDesc = new Label(inventiveStandard
						.getDescription());
				lblInvStandardDesc.setParent(vbox);
				// imagen
				String imageURL = inventiveStandard.getImageURL();
				if (imageURL != null && !imageURL.isEmpty()) {
					Image imgInvStandard = new Image();
					imgInvStandard.setWidth("310px");
					imgInvStandard.setSrc(imageURL);
					imgInvStandard.setParent(vbox);
				}
				// descripcion de la imagen
				Label lblInvStandardImgdesc = new Label(inventiveStandard
						.getImageDesc());
				lblInvStandardImgdesc.setParent(vbox);
				// boton para aplicar el estandar seleccionado
				Button btnSelectInvStandard = new Button();
				btnSelectInvStandard.setLabel("Use this standard");
				btnSelectInvStandard.addEventListener(Events.ON_CLICK,
						new EventListener() {
							
							public void onEvent(Event event) throws Exception {
								algorithmStandardsWin.detach();
								Events
										.postEvent(
												ItSolverEvents.ON_SELECT_INVENTIVE_STANDARD,
												vboxParentSolution,
												inventiveStandard);
							}
						});
				btnSelectInvStandard.setParent(vbox);
				// panel children
				Panelchildren panelChildren = new Panelchildren();

				vbox.setParent(div);
				div.setParent(panelChildren);

				panelChildren.setParent(inventiveStandardPanel);
			}
		}
	}
	// end InventiveStandardTreeListener

}
