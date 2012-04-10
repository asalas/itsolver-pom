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
			<tr><td class="title-header">Project details</td></tr>
			<tr>
				<td class="table-content">					
					<table>
						<tr>
							<td  align="right" width="20%">Project name:</td> 
							<td>${project.projectName}</td>
						</tr>
						<tr>
							<td  align="right">Project nature:</td> 
							<td>${project.problemDescription.problemNature}</td>
						</tr>
						<tr>
							<td   align="right">Created by:</td> 
							<td>${project.profile.fullName}</td>
						</tr>
						<tr>
							<td   align="right">Created on:</td> 
							<td>${project.createdOn}</td>
						</tr>
						<tr>
							<td  align="right">Problem situation:</td> 
							<td>${project.problemDescription.longDescription}</td>
						</tr>						
					</table>
				</td>
			</tr>
			
			<tr><td class="title-header">System description</td></tr>				
			<tr>
				<td class="table-content">					
					<table>
						<tr>
							<td width="100%" align="center">
								<b>System components</b>
							</td>
						</tr>						
						<tr>
							<td>
								<b>Power source</b>
							</td>
						</tr>
						<tr>
							<td>
								${project.systemComponents.powerSource}
							</td>
						</tr>
						<tr>
							<td>
								<b>Engine</b>
							</td>
						</tr>
						<tr>
							<td>
								${project.systemComponents.engine}
							</td>
						</tr>
						<tr>
							<td>
								<b>Transmission</b>
							</td>
						</tr>
						<tr>
							<td>
								${project.systemComponents.transmition}
							</td>
						</tr>
						<tr>
							<td>
								<b>Working Unit</b>
							</td>
						</tr>
						<tr>
							<td>
								${project.systemComponents.workingUnit}
							</td>
						</tr>
						<tr>
							<td>
								<b>Control Unit</b>
							</td>
						</tr>
						<tr>
							<td>
								${project.systemComponents.controlUnit}
							</td>
						</tr>
						<tr>
							<td width="100%" align="center">
								<b>Super-system components</b>
							</td>
						</tr>						
						<tr>
							<td>
								<b>Power source</b>
							</td>
						</tr>
						<tr>
							<td>
								${project.superSystemComponents.powerSource}
							</td>
						</tr>
						<tr>
							<td>
								<b>Engine</b>
							</td>
						</tr>
						<tr>
							<td>
								${project.superSystemComponents.engine}
							</td>
						</tr>
						<tr>
							<td>
								<b>Transmission</b>
							</td>
						</tr>
						<tr>
							<td>
								${project.superSystemComponents.transmition}
							</td>
						</tr>
						<tr>
							<td>
								<b>Working Unit</b>
							</td>
						</tr>
						<tr>
							<td>
								${project.superSystemComponents.workingUnit}
							</td>
						</tr>
						<tr>
							<td>
								<b>Control Unit</b>
							</td>
						</tr>
						<tr>
							<td>
								${project.superSystemComponents.controlUnit}
							</td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr><td class="title-header">Identified resources</td></tr>
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
			 
			<tr><td class="title-header">IFR</td></tr>
			<tr>
				<td class="table-content">					
					<table>
						<tr>
							<td>
								Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in 
								Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in
							</td>
						</tr>
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
										<b>Improving characteristic:</b> ${project.contradiction.positiveCharacteristic.nameToXML}								
									</td>
								</tr>
								<tr>
									<td>
										<b>Negative characteristic:</b> ${project.contradiction.negativeCharacteristic.nameToXML}								
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
										<b>Associated Inventive Standard:</b> ${project.projectSolution.inventiveStandard.inventiveStandardNumber}								
									</td>
								</tr>
								<tr>
									<td>
										<b>Description:</b> ${project.projectSolution.inventiveStandard.description}								
									</td>
								</tr>								
							</table>
						</td>
					</tr>
				</c:when>
			</c:choose>
			
			<tr><td class="title-header">Solution</td></tr>
			<tr>
				<td class="table-content">					
					<table>
						<tr>
							<td><b>How was it is adapted?</b>
							<br/>
								${project.projectSolution.howWasItAdapted}
							</td>
						</tr>
					</table>
				</td>
			</tr>
			
		</table>
		
	</body>
</html>