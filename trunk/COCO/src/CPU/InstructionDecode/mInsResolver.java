package CPU.InstructionDecode;

import java.util.List;

/**
 * ָ�������
 * @author WuyaMony
 *
 */
public interface mInsResolver {
	/**
	 * ��ȡ΢����
	 * @param ins ָ��
	 * @param startT	����
	 * @return	΢�����б�
	 */
	public  List<mIns> getMicroOrders(int ins, int startT);
}
