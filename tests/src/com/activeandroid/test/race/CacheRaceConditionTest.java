package com.activeandroid.test.race;

import com.activeandroid.query.Select;
import com.activeandroid.test.ActiveAndroidTestCase;

public class CacheRaceConditionTest extends ActiveAndroidTestCase {

	public void testSelectRaceCondition() {
		RaceModel createdModel = new RaceModel();
		createdModel.raceValue = "createdValue";
		createdModel.save();
		
		RaceModel loadedModel = new Select().from(RaceModel.class).where("raceValue = ?", "createdValue").executeSingle();
		assertEquals("createdValue", loadedModel.raceValue);
		
		loadedModel.raceValue = "modifiedValue";
		assertEquals("modifiedValue", loadedModel.raceValue);
		
		new Select().from(RaceModel.class).execute();
		
		loadedModel.save();
		
		RaceModel finalModel = new Select().from(RaceModel.class).where("raceValue = ?", "modifiedValue").executeSingle();
		assertNotNull(finalModel);
	}
	
}
