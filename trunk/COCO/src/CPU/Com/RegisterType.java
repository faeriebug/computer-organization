package CPU.Com;

/**
 * 
 * @author WuyaMony
 */
public abstract class RegisterType {
	private int data; 
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public Port[] ports;
	public String name;
}
