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
@Table(name="sufield_graph_node")
@XmlRootElement(name="node")
public class Node implements Serializable {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 5138751213175534434L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "node_id", nullable = false)
	private Long id;
	
	@Column(name="node_id_string")
	private String nodeId;
	
	@Column(name="type")
	private String type;
	
	@Column(name="x")
	private String x;
	
	@Column(name="y")
	private String y;
	
	@Column(name="caption")
	private String caption;
	
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
	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	@XmlAttribute(name="type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlAttribute(name="x")
	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	@XmlAttribute(name="y")
	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	@XmlElement(name="caption")
	public String getCaption() throws Exception{
		return URLDecoder.decode(caption, "UTF-8");
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	@XmlElement(name="description")
	public String getDescription() throws Exception {		
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
			if(obj instanceof Node) {
				Node node = (Node)obj;
				String type = node.getType();
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
