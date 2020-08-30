package tttProject;

public class Player {
		private String name;
		private String mark;
		
		//create the name and mark for each player of a game
		public Player(String name, String mark) {
			this.setName(name);
			this.setMark(mark);
		}
		
		//return players name
		public String getName() {
			return name;
		}
		
		//set players name
		public void setName(String name) {
			this.name = name;
		}
		
		//set players marh
		public void setMark(String Mark) {
			this.mark = Mark;
		}

		//retrun players mark
		public String getMark() {
			return mark;
		}

}
