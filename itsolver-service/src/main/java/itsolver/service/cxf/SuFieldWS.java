package itsolver.service.cxf;

import itsolver.model.entity.AlgorithmQuestion;
import itsolver.model.entity.InventiveStandard;
import itsolver.model.entity.InventiveStandardClass;
import itsolver.model.entity.TreeField;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface SuFieldWS {
	
	@WebMethod(operationName="get_tree_fields")
	@WebResult(name="treefields")
	public TreeField getTreeField();
	
	@WebMethod(operationName="update_sufield_graph")
	@WebResult(name="result_update_sufield_graph")
	public Boolean updateSuFieldGraph(@WebParam(name="project_id") Long projectId, 
										@WebParam(name="sufield_graph") String suFieldGraph);
	
	@WebMethod(operationName="get_sufield_graph")
	@WebResult(name="result_sufield_graph")
	public String getSuFieldGraph(@WebParam(name="project_id") Long projectId);
	
	@WebMethod(operationName="get_algorithm_question")
	@WebResult(name="algorithm_question")
	public AlgorithmQuestion getAlgorithmQuestion(@WebParam(name="question_id") String questionId);
	
	@WebMethod(operationName="get_inventive_standards_classification")
	@WebResult(name="standard_classification")
	public List<InventiveStandardClass> getStandardsClassification();
	
	@WebMethod(operationName="get_inventive_standard")
	@WebResult(name="inventive_standard")
	public InventiveStandard getInventiveStandardById(@WebParam(name="standard_number")String id);
	
	@WebMethod(operationName="set_inventive_standard_to_sufield_project")
	@WebResult(name="result_set_standard")
	public Boolean setStandardToSuFieldProject(@WebParam(name="project_id")Long projId, 
												@WebParam(name="standard_number")String standardId);
	
}