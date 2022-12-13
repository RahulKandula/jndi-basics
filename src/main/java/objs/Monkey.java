package objs;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;

public class Monkey implements Referenceable {
	public static final String NAME = "name";
	public static final String IS_GOOD = "isGood";
	
	private String name;
	private boolean isGood;

	public Monkey() {
		super();
	}

	public Monkey(String name, boolean isGood) {
		super();
		this.name = name;
		this.isGood = isGood;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isGood() {
		return isGood;
	}

	public void setGood(boolean isGood) {
		this.isGood = isGood;
	}

	@Override
	public Reference getReference() throws NamingException {
		Reference mRef = new Reference(Monkey.class.getName(), MonkeyObjectFactory.class.getName(), null);
		mRef.add( new StringRefAddr(NAME, this.name) );
		mRef.add( new StringRefAddr(IS_GOOD, Boolean.toString(this.isGood)) );
		return mRef;
	}

	@Override
	public String toString() {
		return "Monkey [name=" + name + ", isGood=" + isGood + "]";
	}

}
