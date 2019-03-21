package CodeImplementationDemos.restAutomation;

public class UserPojo {

	private String putId;

	private int userID;
	private int id;
	private String title;
	private String body;

	public UserPojo() {

	}

	public UserPojo(int userID, int id, String title, String body) {
		this.userID = userID;
		this.id = id;
		this.title = title;
		this.body = body;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getPutId() {
		return putId;
	}

	public void setPutId(String putId) {
		this.putId = putId;
	}

}
