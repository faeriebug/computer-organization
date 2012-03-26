package CPU.Com;


/**
 * 单向的导线，不存储信息；
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
	 * 从源锚点传输到目的锚点，并引发传输事件
	 */
	public void trans() {
		System.out.println("\t"+src.owner.name+"-->"+des.owner.name+",data="+src.data);
		des.data=src.data;
		
		if(des.isTrack){
			des.transEvent();
		}
	}
}
