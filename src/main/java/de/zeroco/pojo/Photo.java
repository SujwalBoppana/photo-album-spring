package de.zeroco.pojo;

public class Photo {
	private int id;
	private byte[] image;
	private String encoder;

	public Photo() {
		super();
	}

	public Photo(int id, byte[] image) {
		super();
		this.id = id;
		this.image = image;
	}

	public String getEncoder() {
		return encoder;
	}

	public void setEncoder(String encoder) {
		this.encoder = encoder;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
