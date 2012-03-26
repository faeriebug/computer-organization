package CPU.Com;

/**
 * 可执行信号的寄存器类型，跟踪传输事件。
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
	 * 信号事件处理
	 * @param sig	产生信号事件的锚点
	 */
	public abstract void signalProcess(anchor sig);
}
