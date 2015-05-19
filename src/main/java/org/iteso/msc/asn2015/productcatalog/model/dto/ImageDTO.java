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
	
	@Column(name="type",nullable=false)
	private String type;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name="image_file",nullable=false)
	private byte[] imageFile;
	
	public ImageDTO(){
		super();
	}
	public ImageDTO(String type, byte[] imageFile) {
		this();
		this.type = type;
		this.imageFile = imageFile;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getImageFile() {
		return imageFile;
	}

	public void setImageFile(byte[] imageFile) {
		this.imageFile = imageFile;
	}

}
