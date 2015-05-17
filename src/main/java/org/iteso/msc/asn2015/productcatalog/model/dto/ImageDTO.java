package org.iteso.msc.asn2015.productcatalog.model.dto;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="images")
public class ImageDTO implements Serializable{

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
	
	@Column(name="mime_type",nullable=false)
	private String mimeType;
	
	@Column(name="description")
	private String description;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name="image_file",nullable=false)
	private byte[] imageFile;
	
	public ImageDTO(){
		super();
	}
	public ImageDTO(String name, String mimeType, byte[] imageFile) {
		this();
		this.name = name;
		this.mimeType = mimeType;
		this.imageFile = imageFile;
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

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImageFile() {
		return imageFile;
	}

	public void setImageFile(byte[] imageFile) {
		this.imageFile = imageFile;
	}

}
