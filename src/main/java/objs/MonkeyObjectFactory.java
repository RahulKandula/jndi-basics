package objs;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;

public class MonkeyObjectFactory implements ObjectFactory {

	@Override
	public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment)
			throws Exception {
		if (!(obj instanceof Reference))
			return null;

		
		Reference robj = (Reference) obj;
		if (!Monkey.class.getName().equals(robj.getClassName()))
			return null;

		Monkey mobj = new Monkey();
		Enumeration<RefAddr> allRefs = robj.getAll();
		while (allRefs.hasMoreElements()) {
			RefAddr refAddr = allRefs.nextElement();
			if(refAddr.getType().equals(Monkey.NAME)) {
				mobj.setName( (String) refAddr.getContent());
			} else if(refAddr.getType().equals(Monkey.IS_GOOD)) {
				mobj.setGood(Boolean.valueOf((String) refAddr.getContent() ));
			}
		}

		return mobj;
	}

}
