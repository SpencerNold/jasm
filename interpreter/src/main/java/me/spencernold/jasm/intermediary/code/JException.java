package me.spencernold.jasm.intermediary.code;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;

/**
 * Implementation of decoded exception which can be thrown by a method.
 * 
 * @author Spencer Nold
 * @since 1.0.0
 * @see me.spencernold.jasm.intermediary.JMethod
 */
public class JException implements ReadWriteable<ByteBuf> {

	private int startPc;
	private int endPc;
	private int handlerPc;
	private int catchType;
	
	public int getStartPc() {
		return startPc;
	}
	
	public void setStartPc(int startPc) {
		this.startPc = startPc;
	}
	
	public int getEndPc() {
		return endPc;
	}
	
	public void setEndPc(int endPc) {
		this.endPc = endPc;
	}
	
	public int getHandlerPc() {
		return handlerPc;
	}
	
	public void setHandlerPc(int handlerPc) {
		this.handlerPc = handlerPc;
	}
	
	public int getCatchTypeIndex() {
		return catchType;
	}
	
	public void setCatchTypeIndex(int catchType) {
		this.catchType = catchType;
	}
	
	@Override
	public void read(ByteBuf buf) {
		startPc = buf.readShort();
		endPc = buf.readShort();
		handlerPc = buf.readShort();
		catchType = buf.readShort();
	}
	
	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(startPc);
		buf.writeShort(endPc);
		buf.writeShort(handlerPc);
		buf.writeShort(catchType);
	}
}
