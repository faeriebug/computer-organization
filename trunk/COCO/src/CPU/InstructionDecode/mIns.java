package CPU.InstructionDecode;

/**
 * ��ʾ<ָ��,String[]<΢����>>�����ݽṹ
 * @author HuHaixiao
 *
 */
public class mIns {
	/**ָ��*/
	public String Instruction;
	/**ָ���Ӧ��΢����*/
	public mOrder[] mOrders;
	/**
	 * @param insdesc ָ������
	 * @param microIns ָ���Ӧ��΢����
	 */
	public mIns(String insdesc, mOrder[] mOrders) {
		Instruction = insdesc;
		this.mOrders = mOrders;
	}
}
