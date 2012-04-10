<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<style type="text/css">
		.title-header-main{
			font-weight: bold;
			text-align: center;
			font-size: 20px;
		}
		.title-header{
			font-weight: bold;
			text-align: center;			
			border-width: 1px;
			border-color: black;
			border-style: solid;
			background-color: #3399DD;
			color: white;
		}
		.table-content{
			border-color: black;			
			border: solid;
			border-width: 1px;						
		}		
	</style>
</head>
<body style="font-size: 11px; font-family: arial">
		<table  width="690">
			<tr><td align="right" class="title-header-main">Project report<img src="assets/image/itsolverlogo-88x22.png"/> </td></tr>
			<tr><td class="title-header">Details</td></tr>
			<tr>
				<td class="table-content">					
					<table>
						<tr>
							<td  align="right" width="20%">Project name:</td> 
							<td>${project.projectName}</td>
						</tr>
						<tr>
							<td  align="right">Nature of the problem:</td> 
							<td>${project.problemDescription.problemNature}</td>
						</tr>
						<tr>
							<td   align="right">Created by:</td> 
							<td>${project.profile.fullName}</td>
						</tr>
						<tr>
							<td   align="right">Creation date:</td> 
							<td>${project.createdOn}</td>
						</tr>
						<tr>
							<td  align="right">Problem situation:</td> 
							<td>${project.problemDescription.longDescription}</td>
						</tr>						
					</table>
				</td>
			</tr>						
			<tr><td class="title-header">Resources</td></tr>
			<tr>
				<td class="table-content">					
					<table>
						<c:forEach var="projectResource" items="${project.projectResourceList}">
							<c:forEach var="selectedResource" items="${projectResource.selectedResourceList}">
								<tr>
									<td>
										${selectedResource.resource.resourceName} value ${selectedResource.selectedValue}
									</td>
								</tr>
							</c:forEach>
						</c:forEach>
					</table>
				</td>
			</tr>
			<tr><td class="title-header">Constraints</td></tr>
			<tr>
				<td class="table-content">					
					<table>
						<c:forEach var="restriction" items="${project.restrictionList}">							
							<tr>
								<td>
									${restriction.formattedMessageXML}
									<br/>									
									${restriction.description}
								</td>
							</tr>							
						</c:forEach>
					</table>
				</td>
			</tr>	
			<!-- Distincion en los tipos de proyecto -->			
			<c:choose>
				<c:when test="${project.projectType == 'Contradictions'}">			
					<tr><td class="title-header">Contradiction</td></tr>
					<tr>
						<td class="table-content">					
							<table>
								<tr>
									<td>
										<b>Positive characteristic:</b> ${project.contradiction.positiveCharacteristic.nameToXML}								
									</td>
								</tr>
								<tr>
									<td>
										<b>Description:</b> ${project.contradiction.positiveDescription}								
									</td>
								</tr>
								<tr>
									<td>
										<b>Negative characteristic:</b> ${project.contradiction.negativeCharacteristic.nameToXML}								
									</td>
								</tr>
								<tr>
									<td>
										<b>Description:</b> ${project.contradiction.negativeDescription}								
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</c:when>
				<c:when test="${project.projectType == 'Su-Field'}">			
					<tr><td class="title-header">SuField</td></tr>
					<tr>
						<td class="table-content">					
							<table>
								<tr>
									<td>
										<b>Associated inventive standard:</b> ${project.inventiveStandard.inventiveStandardNumber}								
									</td>
								</tr>
								<tr>
									<td>
										<b>Description:</b> ${project.inventiveStandard.description}								
									</td>
								</tr>								
							</table>
						</td>
					</tr>
				</c:when>
			</c:choose>
			
			<tr><td class="title-header">Solución</td></tr>
			<tr>
				<c:choose>
					<c:when test="${project.projectType == 'Contradictions'}">
						<td class="table-content">					
							<table>
								<tr>
									<td>
										<b>Solution principle:</b>${project.solutionContradiction.solutionPrinciple.id}.- ${project.solutionContradiction.solutionPrinciple.principleName}
									</td>
								</tr>
								<tr>
									<td>
										<b>How was it adapted:</b>${project.solutionContradiction.howWasItAdapted}
									</td>
								</tr>
							</table>
						</td>
					</c:when>
					<c:when test="${project.projectType == 'Su-Field'}">
						<td class="table-content">					
							<table>
								<tr>
									<td>
										<b>Inventive standard:</b> ${project.solutionSuField.inventiveStandard.inventiveStandardNumber}
									</td>
								</tr>
								<tr>
									<td>
										<b>How was it adapted:</b>${project.solutionSuField.howWasItAdapted}
									</td>
								</tr>
							</table>
						</td>
					</c:when>					
				</c:choose>
			</tr>
		</table>
		
	</body>
</html>