package laboratoare.Laborator4;

public class Tuple<Type> {
	
	private Type e1;
	private Type e2;
	
	//*********************
	
	public Tuple(Type e1, Type e2){
		
		this.e1 = e1;
		this.e2 = e2;
		
	}
		
	public void setFirstElement(Type e){ e1 = e; }
	
	public void setSecondElement(Type e){ e2 = e; }
	
	public Type getFirstElement(){ return e1; }
	
	public Type getSecondElement(){ return e2; }
	
	public String toString(){
		
		StringBuilder sb = new StringBuilder();
		sb.append(e1);
		sb.append(" -- ");
		sb.append(e2);
		
		return sb.toString(); 
	}
	
}
