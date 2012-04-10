package itsolver.model.entity.sufield;

import java.io.Serializable;
import java.net.URLDecoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="sufield_graph_edge")
@XmlRootElement(name="edge")
public class Edge implements Serializable {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -9115001533528285985L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "edge_id", nullable = false)
	private Long id;
	
	@Column(name="edge_id_string")
	private String edgeId;
	
	@Column(name="type")
	private String type;
	
	@Column(name="source")
	private String source;
	
	@Column(name="target")
	private String target;
	
	@Column(name="x1")
	private String x1;
	
	@Column(name="y1")
	private String y1;
	
	@Column(name="x2")
	private String x2;
	
	@Column(name="y2")
	private String y2;
	
	@Column(name="moved")
	private Boolean moved;
	
	@Column(name="description")
	private String description;
	
	
	@ManyToOne(targetEntity = SuFieldGraph.class)
	@JoinColumn(name = "sufield_graph_id", nullable = true)
	private SuFieldGraph suFieldGraph;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlAttribute(name="id")
	public String getEdgeId() {
		return edgeId;
	}

	public void setEdgeId(String edgeId) {
		this.edgeId = edgeId;
	}

	@XmlAttribute(name="type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlAttribute(name="source")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@XmlAttribute(name="target")
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@XmlAttribute(name="x1")
	public String getX1() {
		return x1;
	}

	public void setX1(String x1) {
		this.x1 = x1;
	}

	@XmlAttribute(name="y1")
	public String getY1() {
		return y1;
	}

	public void setY1(String y1) {
		this.y1 = y1;
	}

	@XmlAttribute(name="x2")
	public String getX2() {
		return x2;
	}

	public void setX2(String x2) {
		this.x2 = x2;
	}

	@XmlAttribute(name="y2")
	public String getY2() {
		return y2;
	}

	public void setY2(String y2) {
		this.y2 = y2;
	}

	@XmlAttribute(name="moved")
	public Boolean getMoved() {
		return moved;
	}

	public void setMoved(Boolean moved) {
		this.moved = moved;
	}

	@XmlElement(name="description")
	public String getDescription() throws Exception{
		return URLDecoder.decode(description, "UTF-8");
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	@XmlTransient
	public SuFieldGraph getSuFieldGraph() {
		return suFieldGraph;
	}

	public void setSuFieldGraph(SuFieldGraph suFieldGraph) {
		this.suFieldGraph = suFieldGraph;
	}
	
	@Transient
	public boolean equals(Object obj) {
		if(obj!=null) {
			if(obj instanceof Edge) {
				Edge edge = (Edge)obj;
				String type = edge.getType();
				if(type!=null && this.getType()!=null) {
					if(type.equals(this.getType())) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
}