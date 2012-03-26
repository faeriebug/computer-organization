package CPU.InstructionDecode;

/**微命令*/
public class mOrder {
	/**可执行单元*/
	public String exreg;
	/**微命令*/
	public int sig;
	public mOrder(String exreg,int sig){
		this.exreg=exreg;
		this.sig=sig;
	}
}
