package itsolver.model.entity.sufield;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="sufield_graph")
@XmlRootElement(name="graph")
public class SuFieldGraph implements Serializable {	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 8542705944260036611L;

	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "sufield_graph_id", nullable = false)
	private Long id;
	
	// TODO: crear un DAO para Node y para Edge
	@OneToMany(mappedBy="suFieldGraph", fetch=FetchType.LAZY)
	private List<Node> nodes;
	
	@OneToMany(mappedBy="suFieldGraph", fetch=FetchType.LAZY)
	private List<Edge> edges;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlElements(
			@XmlElement(name="node", type=Node.class)
	)
	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	@XmlElements(
			@XmlElement(name="edge", type=Edge.class)
	)
	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	
}