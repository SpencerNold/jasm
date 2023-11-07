package me.spencernold.jasm.intermediary.verify;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.Opcodes;
import me.spencernold.jasm.exceptions.ClassFormatException;
import me.spencernold.jasm.exceptions.ClassFormatException.Type;
import me.spencernold.jasm.intermediary.verify.frames.AppendFrame;
import me.spencernold.jasm.intermediary.verify.frames.ChopFrame;
import me.spencernold.jasm.intermediary.verify.frames.Frame;
import me.spencernold.jasm.intermediary.verify.frames.FullFrame;
import me.spencernold.jasm.intermediary.verify.frames.SameExtendedFrame;
import me.spencernold.jasm.intermediary.verify.frames.SameFrame;
import me.spencernold.jasm.intermediary.verify.frames.SameLocals1StackItemExtendedFrame;
import me.spencernold.jasm.intermediary.verify.frames.SameLocals1StackItemFrame;

public class FrameEncoder {

	@SuppressWarnings("deprecation")
	public Frame decode(ByteBuf buf) {
		int type = buf.readByte();
		if (Opcodes.FRAME_TYPE_SAME.isInRange(type))
			return new SameFrame(type);
		else if (Opcodes.FRAME_TYPE_SAME_LOCALS_1_STACK_ITEM.isInRange(type))
			return new SameLocals1StackItemFrame(type);
		else if (Opcodes.FRAME_TYPE_SAME_LOCALS_1_STACK_ITEM_EXTENDED.isInRange(type))
			return new SameLocals1StackItemExtendedFrame(type);
		else if (Opcodes.FRAME_TYPE_RESERVED.isInRange(type))
			throw new ClassFormatException(Type.MALFORMED, "frame type is in the reserved range");
		else if (Opcodes.FRAME_TYPE_CHOP.isInRange(type))
			return new ChopFrame(type);
		else if (Opcodes.FRAME_TYPE_SAME_EXTENDED.isInRange(type))
			return new SameExtendedFrame(type);
		else if (Opcodes.FRAME_TYPE_APPEND.isInRange(type))
			return new AppendFrame(type);
		else if (Opcodes.FRAME_TYPE_FULL.isInRange(type))
			return new FullFrame(type);
		else
			throw new ClassFormatException(Type.MALFORMED, "unknown frame type: %d", type);
	}
	
	public void encode(Frame frame, ByteBuf buf) {
		buf.writeByte(frame.getType());
		frame.write(buf);
	}
}
