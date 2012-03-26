package CPU.InstructionDecode;

import java.util.ArrayList;
import java.util.List;

import CPU.Instruction.InsConst;
import CPU.InstructionDecode.DoubleOperandInstruction;
import CPU.InstructionDecode.JMP_JSR;
import CPU.InstructionDecode.Move;
import CPU.InstructionDecode.SingleOperandInstruction;

/**
 * 指令译码器 并依据操作码，调用相应的微命令发生器
 * 
 * @author HuHaixiao
 * 
 */
public class mInsResolverSelector {
	private DoubleOperandInstruction DOI;
	private SingleOperandInstruction SOI;
	private JMP_JSR JmJ;
	private Move M;

	public mInsResolverSelector() {
		M = new Move();
		DOI = new DoubleOperandInstruction();
		SOI = new SingleOperandInstruction();
		JmJ = new JMP_JSR();
	}

	public List<mIns> Decode(int ins, int psw) {
		int opn = (ins & InsConst.Op_start_m) >>> InsConst.Op_start_p;// 操作码
		List<mIns> m = null;
		if (opn == 0) {// MOVE
			m = M.getMicroOrders(ins, 0);
		} else if (opn >= 1 && opn <= 5) {// 双操作数
			m = DOI.getMicroOrders(ins, 0);
		} else if (opn >= 6 && opn <= 11) {// 单操作数
			m = SOI.getMicroOrders(ins, 0);
		} else if (opn <= 14) {// 程序控制指令，转移指令
			m = JmJ.getMicroOrders(ins, 0);
		} else if (opn == 15) {// 终止符
			m = new ArrayList<mIns>();// 空的序列
		} else {// 其他未定义操作码,终止程序运行
			m = null;
		}
		return m;
	}
}
