package CPU.InstructionDecode;

/**
 * 表示<指令,String[]<微命令>>的数据结构
 * @author HuHaixiao
 *
 */
public class mIns {
	/**指令*/
	public String Instruction;
	/**指令对应的微命令*/
	public mOrder[] mOrders;
	/**
	 * @param insdesc 指令描述
	 * @param microIns 指令对应的微命令
	 */
	public mIns(String insdesc, mOrder[] mOrders) {
		Instruction = insdesc;
		this.mOrders = mOrders;
	}
}
