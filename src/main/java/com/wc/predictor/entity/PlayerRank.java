package com.wc.predictor.entity;

public class PlayerRank {

	private String name;
	private double score;
	private String email;
	private String phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public PlayerRank(String name, double score, String email, String phone) {
		super();
		this.name = name;
		this.score = score;
		this.email = email;
		this.phone = phone;
	}

}
