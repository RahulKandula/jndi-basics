import java.io.File;
import java.util.Hashtable;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import objs.Monkey;

public class Main {

	public static void main(String[] args) throws NamingException {

		// Env
		Hashtable<String, String> environment = new Hashtable<String, String>();
		environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
		environment.put(Context.PROVIDER_URL, "file:/c:/jndi-bindings/");

		Context context = new InitialContext(environment);

		// SUBCONTEXTS
		//context.createSubcontext("monkeys-data");
		//context.createSubcontext("monkeys-data/samples");
		//context.destroySubcontext("monkeys-data/samples");

		
		// BINDING, REBINDING AND UNBINDING
		//context.bind("a-monkey", new Monkey("steve irwin", false));
		//context.bind("another-one", new Monkey("sravya irwin", false));
		//context.unbind("a-monkey.monkeys-data");
		
		//context.rebind("a-monkey", new Monkey("steve irwin", false));


		// LOOKUP
		//Object lookup = context.lookup("another-one");
		//System.out.println(lookup.getClass().getName());
		//System.out.println(lookup);


		// LISTINGS
		NamingEnumeration<NameClassPair> allBounds = context.list("");
		while(allBounds.hasMore()) {
			NameClassPair boundItem = allBounds.next();
			System.out.format("%s | %s ", boundItem.getName(), boundItem.getClassName());
			System.out.println();
		}

		NamingEnumeration<Binding> listBindings = context.listBindings("");
		while(listBindings.hasMore()) {
			Binding boundItem = listBindings.next();
			System.out.format("%s | %s ", boundItem.getName(), boundItem.getClassName());
			if (boundItem.getObject() instanceof File) {
				System.out.format(" > size %d", ((File) boundItem.getObject()).length());
			}
			System.out.println();
		}
	}



}
