package me.spencernold.jasm.intermediary.verify.frames;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;

public abstract class Frame implements ReadWriteable<ByteBuf> {
	
	private int type;
	
	public Frame(int type) {
		this.type = type;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
}
