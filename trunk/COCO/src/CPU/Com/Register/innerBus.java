package CPU.Com.Register;

import CPU.Com.RegisterType;
import CPU.Com.anchor;

/**
 * ×ÜÏß
 * @author WuyaMony
 *
 */
public class innerBus extends RegisterType {
	public innerBus(int num){
		inout=new anchor[1];
		inout[0]=new anchor(num);
		inout[0].owner=this;
	}
}
