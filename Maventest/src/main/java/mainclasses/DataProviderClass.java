package mainclasses;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

	@DataProvider(name = "SearchProvider")
	public static Object[][] getDataFromDataprovider() {
		return new Object[][] { 
			{ "Naman", "ARTS" }, 
			{ "John", "COMM" }, 
			{ "Bob", "PHIL" } };
	}

}
