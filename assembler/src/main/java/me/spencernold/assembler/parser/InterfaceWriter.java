package me.spencernold.assembler.parser;

import me.spencernold.assembler.ObjectWriter;
import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.jasm.intermediary.pools.InterfacePool;

public class InterfaceWriter implements ParserWriter<JClass> {

	private InterfacePool interfacePool;
	
	@Override
	public void read(JClass jclass) {
		interfacePool = jclass.getInterfacePool();
	}
	
	@Override
	public void write(ObjectWriter writer) {
		Short[] interfaces = interfacePool.getInterfaces();
		String[] names = new String[interfaces.length];
		for (int i = 0; i < interfaces.length; i++)
			names[i] = String.valueOf(interfaces[i]);
		writer.printf(".interfaces [%s]\n", String.join(",", names));
	}
}
