package CPU.Com;


/**
 * 锚点，用来连接wire
 * @author WuyaMony
 *
 */
public class anchor implements transEventListener{
	/**锚点的拥有者*/
	public RegisterType owner=null;
	/**锚点保存的数据*/
	public int data=0;
	/**
	 * 连接线
	 */
	public uniwire[] wire;
	
	/**是否跟踪传输事件*/
	public boolean isTrack=false;
	
	public anchor(){
		this(1);
	}
	
	/**
	 * 
	 * @param in 入线条数
	 * @param out 出线条数
	 */
	public anchor(int num){
		wire=new uniwire[num];
	}
	
	/**
	 * 从wire上传输数据引发的事件
	 * @author WuyaMony
	 */
	@Override
	public void transEvent() {
		((ExecutableRegister)owner).signalProcess(this);
	}
}
