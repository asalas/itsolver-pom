package itsolver.service;

import itsolver.model.dao.EdgeDAO;
import itsolver.model.dao.NodeDAO;
import itsolver.model.dao.ProjectDAO;
import itsolver.model.dao.SuFieldGraphDAO;
import itsolver.model.dao.TreeFieldDAO;
import itsolver.model.entity.SuField;
import itsolver.model.entity.SuFieldModel;
import itsolver.model.entity.TreeField;
import itsolver.model.entity.sufield.Edge;
import itsolver.model.entity.sufield.Node;
import itsolver.model.entity.sufield.SuFieldGraph;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class SuFieldServiceImpl implements SuFieldService {


	private TreeFieldDAO treeFieldDAO;
	private ProjectDAO projectDAO;
	private SuFieldGraphDAO suFieldGraphDAO;
	private NodeDAO nodeDAO;
	private EdgeDAO edgeDAO;
	
	
	public TreeField getTreeField() {		
		return treeFieldDAO.getTreeField();
	}
	
	
	public void createTreeField(TreeField treeField) {
		treeFieldDAO.persist(treeField);		
	}
	
	
	public Boolean updateSuFieldGraphByProjId(Long id, String suFieldGraphML) throws Exception {
		SuField suFieldProj = null;
		suFieldProj = (SuField)projectDAO.findById(id);
		
		if(suFieldProj != null) {		
			
			// se borra el sufieldgraph anterior
			SuFieldGraph oldSuFieldGraph = suFieldProj.getSufieldGraph();
			Long oldSFGraphId = null;		
			SuFieldGraph osfg = null;
			
			if(oldSuFieldGraph!=null){
				oldSFGraphId = oldSuFieldGraph.getId();
				osfg = suFieldGraphDAO.findById(oldSFGraphId);
				
				if(osfg!=null) {
					// se asigna temporalmente nulo el SuFieldGraph
					suFieldProj.setSufieldGraph(null);
					projectDAO.merge(suFieldProj);
					
					// se borran los nodos
					Node tmpNode = null;
					List<Node> oldNodes = osfg.getNodes();
					for(Node oldNode: oldNodes) {						
						tmpNode = nodeDAO.findById(oldNode.getId());
						nodeDAO.remove(tmpNode);
					}
					
					// se borran los arcos
					Edge tmpEdge = null;
					List<Edge> oldEdges = osfg.getEdges();
					for(Edge oldEdge: oldEdges) {
						tmpEdge = edgeDAO.findById(oldEdge.getId());
						edgeDAO.remove(tmpEdge);
					}

					// se borra el sufieldgraph
					suFieldGraphDAO.remove(osfg);
				}
			}
			
			// se serializa el objeto (XML->Java)
			byte[] bytes = suFieldGraphML.getBytes();
			JAXBContext context = JAXBContext.newInstance(SuFieldGraph.class);
			Unmarshaller unmarshaller  = context.createUnmarshaller();
			// se asigna nulo el esquema para evitar la validacion del XML
			unmarshaller.setSchema(null);
			Object unmarshal = unmarshaller.unmarshal(new ByteArrayInputStream(bytes));
			SuFieldGraph newSuFieldGraph = SuFieldGraph.class.cast(unmarshal);			
			
			// persistir el nuevo SuFieldGraph
			suFieldGraphDAO.persist(newSuFieldGraph);
			
			// se persisten los nuevos arcos
			List<Edge> edges = newSuFieldGraph.getEdges();
			for(Edge edge: edges){
				edge.setSuFieldGraph(newSuFieldGraph);
				edgeDAO.persist(edge);
			}
			
			// se persisten los nuevos nodos
			List<Node> nodes = newSuFieldGraph.getNodes();
			for(Node node: nodes) {
				node.setSuFieldGraph(newSuFieldGraph);
				nodeDAO.persist(node);
			}
			
			// se actualiza el sufieldgraph
			suFieldGraphDAO.merge(newSuFieldGraph);			
			
			// se asigna el nuevo SuFieldGraph a la solucion del proyecto
			suFieldProj.setSufieldGraph(newSuFieldGraph);
			
			// se actualiza el modelo
			SuFieldModel sufieldModel = suFieldProj.getSufieldModel();
			sufieldModel.setModelGraphML(suFieldGraphML);		
			// se asigna el nuevo modelo
			suFieldProj.setSufieldModel(sufieldModel);			
			
			projectDAO.merge(suFieldProj);
			
			return true;
		}
		
		return false;
	}
	
	
	public String getSuFieldGraphByProjId(Long id) throws Exception {
		SuField suFieldProj = (SuField) projectDAO.findById(id);
		
		SuFieldModel sufieldModel = suFieldProj.getSufieldModel();
		String modelGraphML = sufieldModel.getModelGraphML();
		
		return modelGraphML;
	}
	
	
	public void mergeSuField(SuField suFieldProj) {
		projectDAO.merge(suFieldProj);		
	}
	
	public TreeFieldDAO getTreeFieldDAO() {
		return treeFieldDAO;
	}

	public void setTreeFieldDAO(TreeFieldDAO treeFieldDAO) {
		this.treeFieldDAO = treeFieldDAO;
	}

	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}

	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	public SuFieldGraphDAO getSuFieldGraphDAO() {
		return suFieldGraphDAO;
	}

	public void setSuFieldGraphDAO(SuFieldGraphDAO suFieldGraphDAO) {
		this.suFieldGraphDAO = suFieldGraphDAO;
	}

	public NodeDAO getNodeDAO() {
		return nodeDAO;
	}

	public void setNodeDAO(NodeDAO nodeDAO) {
		this.nodeDAO = nodeDAO;
	}

	public EdgeDAO getEdgeDAO() {
		return edgeDAO;
	}

	public void setEdgeDAO(EdgeDAO edgeDAO) {
		this.edgeDAO = edgeDAO;
	}

}
