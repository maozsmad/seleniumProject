package enums;

public enum ByTypes {

	id("id"),xpath("xpath"),link("link"),name("name"),className("class");
	
	public  String text;
	@Override
	public String toString(){
		return this.text;
	}
	//accepts the value of the enum
	ByTypes(String text){
		this.text = text;
	}
}
