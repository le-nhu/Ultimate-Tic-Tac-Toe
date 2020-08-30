package tttProject;

public enum Mark {
	X("X"), O("O");
	private String mark;
	
	//mark of the player depending on whose turn it is
	Mark(String mark){
		this.mark = mark;
	}
	
	//return the current player's mark
	public String getMark() {
		return mark;
	}
	
}
