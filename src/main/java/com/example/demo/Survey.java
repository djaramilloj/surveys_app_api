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

  private Integer document_number;

  private Integer pc_brand_id;

  private String email;

  private String comments;

  private Integer created;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getDoc() {
    return document_number;
  }

  public void setDoc(Integer document_number) {
    this.document_number = document_number;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

   public Integer getBrand() {
    return pc_brand_id;
  }

  public void setBrand(Integer pc_brand_id) {
    this.pc_brand_id = pc_brand_id;
  }

  public String getComments() {
    return comments;
  }

  public void setCommenys(String comments) {
    this.comments = comments;
  }

  public Integer getCreated() {
    return created;
  }

  public void setCreated(Integer created) {
    this.created = created;
  }
}