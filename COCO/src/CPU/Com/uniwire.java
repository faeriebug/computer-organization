package CPU.Com;


/**
 * ����ĵ��ߣ����洢��Ϣ��
 * @author HuHaixiao
 *
 */
public class uniwire {
	private anchor src, des;
	public uniwire(anchor src, anchor des) {
		this.src = src;
		this.des = des;
	}
	/**
	 * ��Դê�㴫�䵽Ŀ��ê�㣬�����������¼�
	 */
	public void trans() {
		System.out.println("\t"+src.owner.name+"-->"+des.owner.name+",data="+src.data);
		des.data=src.data;
		
		if(des.isTrack){
			des.transEvent();
		}
	}
}
