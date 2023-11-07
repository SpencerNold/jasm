package me.spencernold.jasm.intermediary.verify.locals;

import me.spencernold.jasm.intermediary.ReadWriteable;

public abstract class VariableInfo implements ReadWriteable {

	private int type;
	
	public VariableInfo(int type) {
		this.type = type;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
}
