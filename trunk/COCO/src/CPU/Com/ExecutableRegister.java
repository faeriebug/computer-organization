package CPU.Com;

/**
 * ��ִ���źŵļĴ������ͣ����ٴ����¼���
 * @author WuyaMony
 *
 */
public abstract class ExecutableRegister extends RegisterType {
	public anchor sig=new anchor();
	public ExecutableRegister() {
		sig.owner=this;
		sig.isTrack=true;
	}
	
	/**
	 * �ź��¼�����
	 * @param sig	�����ź��¼���ê��
	 */
	public abstract void signalProcess(anchor sig);
}
