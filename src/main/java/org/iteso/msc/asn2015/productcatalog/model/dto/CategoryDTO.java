package org.iteso.msc.asn2015.productcatalog.model.dto;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="categories")
public class CategoryDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="description")
	private String description;
	
	@OneToOne
	@JoinColumn(name="image_id")
	private ImageDTO image;
	
	public CategoryDTO(){
		super();
	}
	public CategoryDTO(String name) {
		this();
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ImageDTO getImage() {
		return image;
	}
	public void setImage(ImageDTO image) {
		this.image = image;
	}

}
