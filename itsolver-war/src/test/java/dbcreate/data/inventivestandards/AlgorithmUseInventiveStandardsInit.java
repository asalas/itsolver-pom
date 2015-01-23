package dbcreate.data.inventivestandards;

import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.AlgorithmQuestion;
import itsolver.model.entity.InventiveStandard;
import itsolver.model.entity.InventiveStandardGroup;
import itsolver.service.AlgorithmStandardsService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AlgorithmUseInventiveStandardsInit {

	// log
	protected static Logger log = Logger.getLogger("dbcreate/data/inventivestandards/AlgorithmUseInventiveStandardsInit");
	static {
		log.setLevel(Level.INFO);
	}
	
	private AlgorithmStandardsService algorithmStandardsService;
	private List<InventiveStandard> ifYesList;
	private List<InventiveStandard> ifNoList;
	private InventiveStandard standard;
	
	@Before
	public void setUp() throws Exception {
		algorithmStandardsService = ServiceLocator.getAlgorithmStandardsService();
	}

	@After
	public void tearDown() throws Exception {}
	
	@Test
	public void persistQuestions()throws Exception {		
		
		AlgorithmQuestion q3 = new AlgorithmQuestion();
		q3.setQuestionNumber("Q3");
		q3.setQuestion("Measurement problem?");
		algorithmStandardsService.persistAlgorithmQuestion(q3);
		
		AlgorithmQuestion q3_1 = new AlgorithmQuestion();
		q3_1.setQuestionNumber("Q3.1");
		q3_1.setQuestion("Transition to measurement problem possible?");
		algorithmStandardsService.persistAlgorithmQuestion(q3_1);
		
		AlgorithmQuestion q3_1_1 = new AlgorithmQuestion();
		q3_1_1.setQuestionNumber("Q3.1.1");
		q3_1_1.setQuestion("Transition to measurement problem too complicated?");
		algorithmStandardsService.persistAlgorithmQuestion(q3_1_1);
		
		AlgorithmQuestion q3_1_2 = new AlgorithmQuestion();
		q3_1_2.setQuestionNumber("Q3.1.2");
		q3_1_2.setQuestion("Transform problem to detection task, then to measurement task");
		algorithmStandardsService.persistAlgorithmQuestion(q3_1_2);
		
		AlgorithmQuestion q4 = new AlgorithmQuestion();
		q4.setQuestionNumber("Q4");
		q4.setQuestion("Su-Field complete?");
		algorithmStandardsService.persistAlgorithmQuestion(q4);
		
		AlgorithmQuestion q4_1 = new AlgorithmQuestion();
		q4_1.setQuestionNumber("Q4.1");
		q4_1.setQuestion("Harmful link present?");
		algorithmStandardsService.persistAlgorithmQuestion(q4_1);
		
		AlgorithmQuestion q4_1_1 = new AlgorithmQuestion();
		q4_1_1.setQuestionNumber("Q4.1.1");
		q4_1_1.setQuestion("Introduction of substances and fields allowed?");
		algorithmStandardsService.persistAlgorithmQuestion(q4_1_1);
		
		AlgorithmQuestion q4_2 = new AlgorithmQuestion();
		q4_2.setQuestionNumber("Q4.2");
		q4_2.setQuestion("Introduction of substances and fields allowed?");
		algorithmStandardsService.persistAlgorithmQuestion(q4_2);
		
		AlgorithmQuestion q5 = new AlgorithmQuestion();
		q5.setQuestionNumber("Q5");
		q5.setQuestion("Harmful link present?");
		algorithmStandardsService.persistAlgorithmQuestion(q5);
		
		AlgorithmQuestion q5_1 = new AlgorithmQuestion();
		q5_1.setQuestionNumber("Q5.1");
		q5_1.setQuestion("Introduction of substances and fields allowed?");
		algorithmStandardsService.persistAlgorithmQuestion(q5_1);
		
		AlgorithmQuestion q6 = new AlgorithmQuestion();
		q6.setQuestionNumber("Q6");
		q6.setQuestion("Are there magnetic substances in Su-Field?");
		algorithmStandardsService.persistAlgorithmQuestion(q6);
		
		AlgorithmQuestion q7 = new AlgorithmQuestion();
		q7.setQuestionNumber("Q7");
		q7.setQuestion("Introduction of M_Field allowed?");
		algorithmStandardsService.persistAlgorithmQuestion(q7);
		
		AlgorithmQuestion q8 = new AlgorithmQuestion();
		q8.setQuestionNumber("Q8");
		q8.setQuestion("Complex Su-Field formation allowable?");
		algorithmStandardsService.persistAlgorithmQuestion(q8);
		
		AlgorithmQuestion q9 = new AlgorithmQuestion();
		q9.setQuestionNumber("Q9");
		q9.setQuestion("Su-Field can be replaced?");
		algorithmStandardsService.persistAlgorithmQuestion(q9);
		
		AlgorithmQuestion q10 = new AlgorithmQuestion();
		q10.setQuestionNumber("Q10");
		q10.setQuestion("Is the system dynamic?");
		algorithmStandardsService.persistAlgorithmQuestion(q10);
		
		AlgorithmQuestion q11 = new AlgorithmQuestion();
		q11.setQuestionNumber("Q11");
		q11.setQuestion("Is Su-Field elements structure coordinated?");
		algorithmStandardsService.persistAlgorithmQuestion(q11);
		
		AlgorithmQuestion q12 = new AlgorithmQuestion();
		q12.setQuestionNumber("Q12");
		q12.setQuestion("Is Su-Field elements dynamics coordinated?");
		algorithmStandardsService.persistAlgorithmQuestion(q12);
		
		AlgorithmQuestion q13 = new AlgorithmQuestion();
		q13.setQuestionNumber("Q13");
		q13.setQuestion("Substitution of Su-Field elements by Ferromag. Subst. And M_Field allowed?");
		algorithmStandardsService.persistAlgorithmQuestion(q13);
		
		AlgorithmQuestion q14 = new AlgorithmQuestion();
		q14.setQuestionNumber("Q14");
		q14.setQuestion("Introduction of ferromag. Additives in available subst. Allowed?");
		algorithmStandardsService.persistAlgorithmQuestion(q14);
		
		AlgorithmQuestion q15 = new AlgorithmQuestion();
		q15.setQuestionNumber("Q15");
		q15.setQuestion("Introduction of ferromag. additives in environment allowed?");
		algorithmStandardsService.persistAlgorithmQuestion(q15);
		
		AlgorithmQuestion q16 = new AlgorithmQuestion();
		q16.setQuestionNumber("Q16");
		q16.setQuestion("Is use of electrical fields and/or currents allowed?");
		algorithmStandardsService.persistAlgorithmQuestion(q16);
		
		AlgorithmQuestion q17 = new AlgorithmQuestion();
		q17.setQuestionNumber("Q17");
		q17.setQuestion("Is Su-M_field dynamic?");
		algorithmStandardsService.persistAlgorithmQuestion(q17);
		
		AlgorithmQuestion q18 = new AlgorithmQuestion();
		q18.setQuestionNumber("Q18");
		q18.setQuestion("Is Su-M_Field elements structure coordinated?");
		algorithmStandardsService.persistAlgorithmQuestion(q18);
		
		AlgorithmQuestion q19 = new AlgorithmQuestion();
		q19.setQuestionNumber("Q19");
		q19.setQuestion("Are Su-M_Field elements dynamics coordinated?");
		algorithmStandardsService.persistAlgorithmQuestion(q19);
		
		// relaciones entre las preguntas y sus respuestas
		q3.setYesQuestionNumber("Q4.1");
		q3.setNoQuestionNumber("Q3.1");
		algorithmStandardsService.mergeAlgorithmQuestion(q3);
		
		q3_1.setYesQuestionNumber("Q3.1.1");
		q3_1.setNoQuestionNumber("Q4");
		algorithmStandardsService.mergeAlgorithmQuestion(q3_1);
		
		q3_1_1.setYesQuestionNumber("Q3.1.2");
		q3_1_1.setNoQuestionNumber("Q4");
		algorithmStandardsService.mergeAlgorithmQuestion(q3_1_1);
		
		q4.setYesQuestionNumber("Q5");
		q4.setNoQuestionNumber("Q4.1");
		algorithmStandardsService.mergeAlgorithmQuestion(q4);
		
		q4_1.setYesQuestionNumber("Q4.1.1");
		q4_1.setNoQuestionNumber("Q4.2");
		algorithmStandardsService.mergeAlgorithmQuestion(q4_1);		

		// La pregunta Q4.1.1 tiene respuestas asociadas
		// Si contesta YES
		ifYesList = new ArrayList<InventiveStandard>();		
		
		for(int i=1; i<=6; i++) {
			 standard = algorithmStandardsService.getInventiveStandardById("STANDARD 1-1-"+i);
			 ifYesList.add(standard);
		}
		
		InventiveStandardGroup group4_2 = algorithmStandardsService.getInventiveStandardGroupById("GROUP-4-2");
		List<InventiveStandard> group4_2StandardsList = group4_2.getStandardsList();		
		
		ifYesList.addAll(group4_2StandardsList);
		
		q4_1_1.setIfYesStandardsList(ifYesList);
		
		// Si contesta NO
		ifNoList = new ArrayList<InventiveStandard>();
		
		InventiveStandardGroup group5_1 = algorithmStandardsService.getInventiveStandardGroupById("GROUP-5-1");
		List<InventiveStandard> group5_1StandardsList = group5_1.getStandardsList();
		InventiveStandardGroup group5_2 = algorithmStandardsService.getInventiveStandardGroupById("GROUP-5-2");
		List<InventiveStandard> group5_2StandardsList = group5_2.getStandardsList();
		InventiveStandardGroup group5_5 = algorithmStandardsService.getInventiveStandardGroupById("GROUP-5-5");
		List<InventiveStandard> group5_5StandardsList = group5_5.getStandardsList();
		
		ifNoList.addAll(group5_1StandardsList);
		ifNoList.addAll(group5_2StandardsList);
		ifNoList.addAll(group5_5StandardsList);
		
		q4_1_1.setIfNoStandardsList(ifNoList);
		algorithmStandardsService.mergeAlgorithmQuestion(q4_1_1);
		
		//Q4.2
		// Si contesta si
		ifYesList = new ArrayList<InventiveStandard>();
		
		InventiveStandard standard1_1_7 = algorithmStandardsService.getInventiveStandardById("STANDARD 1-1-7");
		InventiveStandard standard1_1_8_1 = algorithmStandardsService.getInventiveStandardById("STANDARD 1-1-8-1");
		InventiveStandard standard1_1_8_2 = algorithmStandardsService.getInventiveStandardById("STANDARD 1-1-8-2");
		InventiveStandard standard1_2_3 = algorithmStandardsService.getInventiveStandardById("STANDARD 1-2-3");
		
		ifYesList.add(standard1_1_7);
		ifYesList.add(standard1_1_8_1);
		ifYesList.add(standard1_1_8_2);
		ifYesList.add(standard1_2_3);
		
		q4_2.setIfYesStandardsList(ifYesList);
		
		// Si contesta NO
		ifNoList = new ArrayList<InventiveStandard>();
		
		ifNoList.addAll(group5_1StandardsList);
		ifNoList.addAll(group5_2StandardsList);
		ifNoList.addAll(group5_5StandardsList);
		
		q4_2.setIfNoStandardsList(ifNoList);
		algorithmStandardsService.mergeAlgorithmQuestion(q4_2);
		
		//Q5
		q5.setYesQuestionNumber("Q5.1");
		q5.setNoQuestionNumber("Q6");
		algorithmStandardsService.mergeAlgorithmQuestion(q5);
		
		//Q5.1
		// Si contesta YES
		ifYesList = new ArrayList<InventiveStandard>();
		
		InventiveStandard standard1_2_1 = algorithmStandardsService.getInventiveStandardById("STANDARD 1-2-1");
		InventiveStandard standard1_2_2 = algorithmStandardsService.getInventiveStandardById("STANDARD 1-2-2");
		InventiveStandard standard1_2_4 = algorithmStandardsService.getInventiveStandardById("STANDARD 1-2-4");
		InventiveStandard standard1_2_5 = algorithmStandardsService.getInventiveStandardById("STANDARD 1-2-5");
		
		ifYesList.add(standard1_2_1);
		ifYesList.add(standard1_2_2);
		ifYesList.add(standard1_2_4);
		ifYesList.add(standard1_2_5);
		
		q5_1.setIfYesStandardsList(ifYesList);
		
		// Si contesta NO
		ifNoList = new ArrayList<InventiveStandard>();
		
		ifNoList.addAll(group5_1StandardsList);
		ifNoList.addAll(group5_2StandardsList);
		ifNoList.addAll(group5_5StandardsList);
		
		q5_1.setIfNoStandardsList(ifNoList);
		algorithmStandardsService.mergeAlgorithmQuestion(q5_1);
		
		//Q6
		q6.setYesQuestionNumber("Q7");
		q6.setNoQuestionNumber("Q8");
		algorithmStandardsService.mergeAlgorithmQuestion(q6);
		
		//Q7
		q7.setYesQuestionNumber("Q17");
		q7.setNoQuestionNumber("Q8");
		algorithmStandardsService.mergeAlgorithmQuestion(q7);

		//Q8
		// Si contesta YES
		ifYesList = new ArrayList<InventiveStandard>();
		InventiveStandardGroup group2_1 = algorithmStandardsService.getInventiveStandardGroupById("GROUP-2-1");
		ifYesList.addAll(group2_1.getStandardsList());
		
		q8.setIfYesStandardsList(ifYesList);

		q8.setNoQuestionNumber("Q9");
		algorithmStandardsService.mergeAlgorithmQuestion(q8);
		
		//Q9
		// Si contesta YES
		ifYesList = new ArrayList<InventiveStandard>();		
		InventiveStandard standard2_2_1 = algorithmStandardsService.getInventiveStandardById("STANDARD 2-2-1");
		ifYesList.add(standard2_2_1);
		q9.setIfYesStandardsList(ifYesList);
		
		q9.setNoQuestionNumber("Q10");
		algorithmStandardsService.mergeAlgorithmQuestion(q9);
		
		//Q10
		q10.setYesQuestionNumber("Q11");
		
		// Si contesta NO
		ifNoList = new ArrayList<InventiveStandard>();		
		
		for(int i=2; i<=4; i++) {
			standard = algorithmStandardsService.getInventiveStandardById("STANDARD 2-2-"+i);
			ifNoList.add(standard);
		}
		q10.setIfNoStandardsList(ifNoList);
		algorithmStandardsService.mergeAlgorithmQuestion(q10);
		
		//Q11
		q11.setYesQuestionNumber("Q12");
		
		// Si contesta NO
		ifNoList = new ArrayList<InventiveStandard>();
		
		InventiveStandard standard2_2_5 = algorithmStandardsService.getInventiveStandardById("STANDARD 2-2-5");
		InventiveStandard standard2_2_6 = algorithmStandardsService.getInventiveStandardById("STANDARD 2-2-6");
		InventiveStandard standard4_3_1 = algorithmStandardsService.getInventiveStandardById("STANDARD 4-3-1");
		
		ifNoList.add(standard2_2_5);
		ifNoList.add(standard2_2_6);
		ifNoList.add(standard4_3_1);
		
		InventiveStandardGroup group5_3 = algorithmStandardsService.getInventiveStandardGroupById("GROUP-5-3");
		InventiveStandardGroup group5_4 = algorithmStandardsService.getInventiveStandardGroupById("GROUP-5-4");
		
		ifNoList.addAll(group5_3.getStandardsList());
		ifNoList.addAll(group5_4.getStandardsList());
		q11.setIfNoStandardsList(ifNoList);
		algorithmStandardsService.mergeAlgorithmQuestion(q11);
		
		//Q12
		q12.setYesQuestionNumber("Q13");
		
		// Si contesta NO
		ifNoList = new ArrayList<InventiveStandard>();
		for(int i=1; i<=3; i++) {
			standard = algorithmStandardsService.getInventiveStandardById("STANDARD 2-3-"+i);
			ifNoList.add(standard);
		}
		for(int i=2; i<=3; i++) {
			standard = algorithmStandardsService.getInventiveStandardById("STANDARD 4-3-"+i);
			ifNoList.add(standard);
		}
		q12.setIfNoStandardsList(ifNoList);
		algorithmStandardsService.mergeAlgorithmQuestion(q12);
		
		// Q13
		// Si contesta YES
		ifYesList = new ArrayList<InventiveStandard>();		
		InventiveStandard standard2_4_1 = algorithmStandardsService.getInventiveStandardById("STANDARD 2-4-1");
		InventiveStandard standard4_4_1 = algorithmStandardsService.getInventiveStandardById("STANDARD 4-4-1");
		ifYesList.add(standard2_4_1);
		ifYesList.add(standard4_4_1);	
		
		q13.setIfYesStandardsList(ifYesList);
		
		// Si contesta NO
		q13.setNoQuestionNumber("Q14");
		algorithmStandardsService.mergeAlgorithmQuestion(q13);
		
		//Q14
		// Si contesta YES
		ifYesList = new ArrayList<InventiveStandard>();		
		InventiveStandard standard2_4_5 = algorithmStandardsService.getInventiveStandardById("STANDARD 2-4-5");
		InventiveStandard standard4_4_3 = algorithmStandardsService.getInventiveStandardById("STANDARD 4-4-3");
		ifYesList.add(standard2_4_5);
		ifYesList.add(standard4_4_3);
		q14.setIfYesStandardsList(ifYesList);
		
		q14.setNoQuestionNumber("Q15");
		algorithmStandardsService.mergeAlgorithmQuestion(q14);
		
		//Q15
		// Si contesta YES		
		ifYesList = new ArrayList<InventiveStandard>();		
		InventiveStandard standard2_4_6 = algorithmStandardsService.getInventiveStandardById("STANDARD 2-4-6");
		InventiveStandard standard4_4_4 = algorithmStandardsService.getInventiveStandardById("STANDARD 4-4-4");
		ifYesList.add(standard2_4_6);
		ifYesList.add(standard4_4_4);
		q15.setIfYesStandardsList(ifYesList);
		
		q15.setNoQuestionNumber("Q16");
		algorithmStandardsService.mergeAlgorithmQuestion(q15);
		
		// Q16
		// Si contesta YES
		ifYesList = new ArrayList<InventiveStandard>();		
		InventiveStandard standard2_4_11 = algorithmStandardsService.getInventiveStandardById("STANDARD 2-4-11");
		InventiveStandard standard2_4_12 = algorithmStandardsService.getInventiveStandardById("STANDARD 2-4-12");
		ifYesList.add(standard2_4_11);
		ifYesList.add(standard2_4_12);
		q16.setIfYesStandardsList(ifYesList);
		
		// Si contesta NO
		ifNoList = new ArrayList<InventiveStandard>();		
		InventiveStandard standard3_2_1 = algorithmStandardsService.getInventiveStandardById("STANDARD 3-2-1");
		InventiveStandard standard3_1_1 = algorithmStandardsService.getInventiveStandardById("STANDARD 3-1-1");
		InventiveStandard standard3_1_2 = algorithmStandardsService.getInventiveStandardById("STANDARD 3-1-2");
		InventiveStandard standard3_1_3 = algorithmStandardsService.getInventiveStandardById("STANDARD 3-1-3");
		InventiveStandard standard3_1_5 = algorithmStandardsService.getInventiveStandardById("STANDARD 3-1-5");
		ifNoList.add(standard3_2_1);
		ifNoList.add(standard3_1_1);
		ifNoList.add(standard3_1_2);
		ifNoList.add(standard3_1_3);
		ifNoList.add(standard3_1_5);		
		q16.setIfNoStandardsList(ifNoList);
		
		algorithmStandardsService.mergeAlgorithmQuestion(q16);
		
		// Q17
		// Si contesta YES
		q17.setYesQuestionNumber("Q18");
		
		// Si contesta NO
		ifNoList = new ArrayList<InventiveStandard>();		
		InventiveStandard standard2_4_2 = algorithmStandardsService.getInventiveStandardById("STANDARD 2-4-2");
		InventiveStandard standard2_4_3 = algorithmStandardsService.getInventiveStandardById("STANDARD 2-4-3");
		InventiveStandard standard2_4_4 = algorithmStandardsService.getInventiveStandardById("STANDARD 2-4-4");
		InventiveStandard standard2_4_7 = algorithmStandardsService.getInventiveStandardById("STANDARD 2-4-7");
		InventiveStandard standard2_4_8 = algorithmStandardsService.getInventiveStandardById("STANDARD 2-4-8");
		InventiveStandard standard4_4_2 = algorithmStandardsService.getInventiveStandardById("STANDARD 4-4-2");
		ifNoList.add(standard2_4_2);
		ifNoList.add(standard2_4_3);
		ifNoList.add(standard2_4_4);
		ifNoList.add(standard2_4_7);
		ifNoList.add(standard2_4_8);
		ifNoList.add(standard4_4_2);
		q17.setIfNoStandardsList(ifNoList);
		
		algorithmStandardsService.mergeAlgorithmQuestion(q17);
		
		// Q18
		// Si contesta YES
		q18.setYesQuestionNumber("Q19");
		
		// Si contesta NO
		ifNoList = new ArrayList<InventiveStandard>();		
		InventiveStandard standard2_4_9 = algorithmStandardsService.getInventiveStandardById("STANDARD 2-4-9");		
		ifNoList.add(standard2_4_9);
		q18.setIfNoStandardsList(ifNoList);
		
		algorithmStandardsService.mergeAlgorithmQuestion(q18);
		
		//Q19
		// Si contesta YES
		ifYesList = new ArrayList<InventiveStandard>();		
		ifYesList.add(standard3_2_1);
		ifYesList.add(standard3_1_1);
		ifYesList.add(standard3_1_2);
		ifYesList.add(standard3_1_3);
		ifYesList.add(standard3_1_5);
		q19.setIfYesStandardsList(ifYesList);
		
		// Si contesta NO
		ifNoList = new ArrayList<InventiveStandard>();
		InventiveStandard standard2_4_10 = algorithmStandardsService.getInventiveStandardById("STANDARD 2-4-10");
		InventiveStandard standard4_4_5 = algorithmStandardsService.getInventiveStandardById("STANDARD 4-4-5");
		ifNoList.add(standard2_4_10);
		ifNoList.add(standard4_4_5);
		
		ifNoList.addAll(group5_3.getStandardsList());
		ifNoList.addAll(group5_4.getStandardsList());
		q19.setIfNoStandardsList(ifNoList);
		
		algorithmStandardsService.mergeAlgorithmQuestion(q19);
		
	}	
	
}
