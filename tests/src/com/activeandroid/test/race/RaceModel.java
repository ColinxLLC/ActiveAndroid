package com.activeandroid.test.race;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name="RaceModel")
public class RaceModel extends Model {

	@Column
	public String raceValue;
	
}
