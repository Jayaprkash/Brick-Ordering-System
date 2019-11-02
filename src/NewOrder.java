
/* Creating a New Order */
public class NewOrder {

	private int noOfBricksWanted;

	@JsonCreator
	public NewOrder(@JsonProperty("noOfBricksWanted") int noOfBricksWanted) {
		this.noOfBricksWanted = noOfBricksWanted;
	}

	public int getNoOfBricksWanted() {
		return noOfBricksWanted;
	}

}
