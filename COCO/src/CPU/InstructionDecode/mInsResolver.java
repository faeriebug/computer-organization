package CPU.InstructionDecode;

import java.util.List;

/**
 * 指令解析器
 * @author WuyaMony
 *
 */
public interface mInsResolver {
	/**
	 * 获取微命令
	 * @param ins 指令
	 * @param startT	周期
	 * @return	微命令列表
	 */
	public  List<mIns> getMicroOrders(int ins, int startT);
}
