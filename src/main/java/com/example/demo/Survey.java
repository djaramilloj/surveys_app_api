package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Survey {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  private String documentNumber;

  private String pcBrandId;

  private String email;

  private String comments;

  private Integer created;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDoc() {
    return documentNumber;
  }

  public void setDoc(String documentNumber) {
    this.documentNumber = documentNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

   public String getBrand() {
    return pcBrandId;
  }

  public void setBrand(String pcBrandId) {
    this.pcBrandId = pcBrandId;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public Integer getCreated() {
    return created;
  }

  public void setCreated(Integer created) {
    this.created = created;
  }
}