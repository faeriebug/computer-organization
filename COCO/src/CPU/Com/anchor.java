package CPU.Com;


/**
 * ê�㣬��������wire
 * @author WuyaMony
 *
 */
public class anchor implements transEventListener{
	/**ê���ӵ����*/
	public RegisterType owner=null;
	/**ê�㱣�������*/
	public int data=0;
	/**
	 * ������
	 */
	public uniwire[] wire;
	
	/**�Ƿ���ٴ����¼�*/
	public boolean isTrack=false;
	
	public anchor(){
		this(1);
	}
	
	/**
	 * 
	 * @param in ��������
	 * @param out ��������
	 */
	public anchor(int num){
		wire=new uniwire[num];
	}
	
	/**
	 * ��wire�ϴ��������������¼�
	 * @author WuyaMony
	 */
	@Override
	public void transEvent() {
		((ExecutableRegister)owner).signalProcess(this);
	}
}
