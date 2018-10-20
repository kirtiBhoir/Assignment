package locators;

public interface HomePageLoc {
	public String LOGO_CLEAR_TRIP_="span.cleartripLogo";
	public String TEXT_SEARCH_FLIGHTS="//h1[contains(text(),'Search')]";
	public String BUTTON_SEARCH_FLIGHTS="//input[@id='SearchBtn']";
	public String DROPDOWN_FROM_CITY="//label[@for='FromTag']/following::input[@id='FromTag']";
	public String DROPDOWN_TO_CITY="//label[@for='ToTag']/following::input[@id='ToTag']";
	public String ERROR_MESSAGE_EMPTY_CITY="//div[@id='homeErrorMessage']";
	public String DATE_DEPART_DATE="[id=DepartDate]";
	public String DATE_RETURN_DATE="[id=ReturnDate]";
	public String DROPDOWN_ADULTS="//label[@for='Adults']/following::select[@id='Adults']";
	public String DROPDOWN_CHILDREN="//label[@for='Childrens']/following::select[@id='Childrens']";
	public String DROPDOWN_INFANT="//label[@for='Infants']/following::select[@id='Infants']";
	public String RADIO_BTN_ONE_WAY_TRIP="[id='OneWay']";
	public String RADIO_BTN_ROUND_TRIP="[id='RoundTrip']";
	
}
