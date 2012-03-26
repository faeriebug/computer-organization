package CPU.InstructionDecode;

import java.util.ArrayList;
import java.util.List;

import CPU.Instruction.InsConst;
import CPU.InstructionDecode.DoubleOperandInstruction;
import CPU.InstructionDecode.JMP_JSR;
import CPU.InstructionDecode.Move;
import CPU.InstructionDecode.SingleOperandInstruction;

/**
 * ָ�������� �����ݲ����룬������Ӧ��΢�������
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
		int opn = (ins & InsConst.Op_start_m) >>> InsConst.Op_start_p;// ������
		List<mIns> m = null;
		if (opn == 0) {// MOVE
			m = M.getMicroOrders(ins, 0);
		} else if (opn >= 1 && opn <= 5) {// ˫������
			m = DOI.getMicroOrders(ins, 0);
		} else if (opn >= 6 && opn <= 11) {// ��������
			m = SOI.getMicroOrders(ins, 0);
		} else if (opn <= 14) {// �������ָ�ת��ָ��
			m = JmJ.getMicroOrders(ins, 0);
		} else if (opn == 15) {// ��ֹ��
			m = new ArrayList<mIns>();// �յ�����
		} else {// ����δ���������,��ֹ��������
			m = null;
		}
		return m;
	}
}
