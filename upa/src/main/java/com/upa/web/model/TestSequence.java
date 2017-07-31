package com.upa.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "upa", name="test_sequence")
public class TestSequence {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	
	public TestSequence(){
		
	}
	
	public TestSequence(Integer id){
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}



/*
DROP TABLE IF EXISTS test_sequence;
CREATE TABLE test_sequence (
  ID INT(10) UNSIGNED NOT NULL AUTO_INCREMENT
  )
*/
