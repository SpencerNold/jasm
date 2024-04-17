package me.spencernold.jasm.intermediary.attributes;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.DynamicByteBuf;
import me.spencernold.jasm.Opcodes;
import me.spencernold.jasm.exceptions.ClassFormatException;
import me.spencernold.jasm.exceptions.ClassFormatException.Type;
import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.jasm.intermediary.ReadWriteable;
import me.spencernold.jasm.intermediary.attributes.impl.*;
import me.spencernold.jasm.intermediary.constants.Constant;
import me.spencernold.jasm.intermediary.constants.Utf8Constant;

/**
 * Contains data relating to attributes of methods, fields, classes and other
 * Java structures
 *
 * @author Spencer Nold
 * @since 1.0.0
 */
public class Attribute implements ReadWriteable<ByteBuf> {

    private final JClass jclass;
    private int nameIndex;
    private Body body;

    public Attribute(JClass jclass) {
        this.jclass = jclass;
    }

    /**
     * Gets a reference to a UTF-8 constant which defines what type of attribute
     * this is.
     *
     * @return index of the attribute name in the constant pool
     */
    public int getNameIndex() {
        return nameIndex;
    }

    /**
     * Sets a reference to a constant which defines what type of attribute this is.
     * This does not update the body of the attribute, and also does not ensure that
     * the name index points to a UTF-8 constant. Handle with care.
     *
     * @param nameIndex index into class pool
     */
    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    /**
     * Gets the value of the attribute name in the constant pool.
     *
     * @return utf8 string of the name
     */
    public String getName() {
        Constant constant = jclass.getConstPool().get(nameIndex);
        if (!(constant instanceof Utf8Constant) || !constant.isUtf8())
            throw new ClassFormatException(Type.MALFORMED, "name is not a string");
        return ((Utf8Constant) constant).getValue();
    }

    /**
     * Gets the body of the attribute, which is different depending on the type of
     * the attribute.
     *
     * @return body of the attribute
     */
    public Body getBody() {
        return body;
    }

    /**
     * Tries to get an instance of <T> which implements body, and is a specific type
     * of Body. For example, {@code getBody(CodeAttribute.class)} would attempt to
     * return an instance of the Code attribute body.
     *
     * @param <T>   instance of a target body
     * @param clazz target class type for <T>
     * @return instance of the body of type <T>
     */
    public <T extends Body> T getBody(Class<T> clazz) {
        return clazz.cast(body);
    }

    /**
     * Checks if the body of the attribute is of type <T>.
     *
     * @param <T>   type to be checked against
     * @param clazz class of type <T>
     * @return true if the body is an instance of <T>, false otherwise
     */
    public <T extends Body> boolean isInstanceOf(Class<T> clazz) {
        return clazz.isInstance(body);
    }

    @Override
    public void read(ByteBuf buf) {
        nameIndex = buf.readShort();
        int length = buf.readInt();
        ByteBuf buf0 = DynamicByteBuf.wrap(buf.read(length));
        Constant constant = jclass.getConstPool().get(nameIndex);
        if (!constant.isUtf8())
            throw new ClassFormatException(Type.MALFORMED, String.format("attribute points to a constant which is not utf8: 0x%s", Integer.toUnsignedString(nameIndex, 16)));
        String value = ((Utf8Constant) constant).getValue();
        switch (value) {
            case Opcodes.ATTR_CONSTANT_VALUE:
                body = new ConstValueAttribute();
                break;
            case Opcodes.ATTR_CODE:
                body = new CodeAttribute(jclass);
                break;
            case Opcodes.ATTR_STACK_MAP_TABLE:
                body = new StackMapTableAttribute();
                break;
            case Opcodes.ATTR_EXCEPTIONS:
                body = new ExceptionsAttribute();
                break;
            case Opcodes.ATTR_INNER_CLASSES:
                body = new InnerClassesAttribute();
                break;
            case Opcodes.ATTR_ENCLOSING_METHOD:
                body = new EnclosingMethodAttribute();
                break;
            case Opcodes.ATTR_SYNTHETIC:
                body = new SyntheticAttribute();
                break;
            case Opcodes.ATTR_SIGNATURE:
                body = new SignatureAttribute();
                break;
            case Opcodes.ATTR_SOURCE_FILE:
                body = new SourceFileAttribute();
                break;
            case Opcodes.ATTR_SOURCE_DEBUG_EXTENSION:
                body = new SourceDebugExtensionAttribute();
                break;
            case Opcodes.ATTR_LINE_NUMBER_TABLE:
                body = new LineNumberTableAttribute();
                break;
            case Opcodes.ATTR_LOCAL_VARIABLE_TABLE:
                body = new LocalVariableTableAttribute();
                break;
            case Opcodes.ATTR_LOCAL_VARIABLE_TYPE_TABLE:
                body = new LocalVariableTypeTableAttribute();
                break;
            case Opcodes.ATTR_DEPRECATED:
                body = new DeprecatedAttribute();
                break;
            case Opcodes.ATTR_RUNTIME_VISIBLE_ANNOTATIONS:
                body = new RuntimeVisibleAnnotationsAttribute();
                break;
            case Opcodes.ATTR_RUNTIME_INVISIBLE_ANNOTATIONS:
                body = new RuntimeInvisibleAnnotationsAttribute();
                break;
            case Opcodes.ATTR_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS:
                body = new RuntimeVisibleParameterAnnotationsAttribute();
                break;
            case Opcodes.ATTR_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS:
                body = new RuntimeInvisileParameterAnnotationsAttribute();
                break;
            case Opcodes.ATTR_RUNTIME_VISIBLE_TYPE_ANNOTATIONS:
                body = new RuntimeVisibleTypeAnnotationsAttribute();
                break;
            case Opcodes.ATTR_RUNTIME_INVISIBLE_TYPE_ANNOTATIONS:
                body = new RuntimeInvisibleTypeAnnotationsAttribute();
                break;
            case Opcodes.ATTR_ANNOTATION_DEFAULT:
                body = new AnnotationDefaultAttribute();
                break;
            case Opcodes.ATTR_BOOTSTRAP_METHODS:
                body = new BootstrapMethodsAttribute();
                break;
            case Opcodes.ATTR_METHOD_PARAMETERS:
                body = new MethodParametersAttribute();
                break;
            case Opcodes.ATTR_MODULE:
                body = new ModuleAttribute();
                break;
            case Opcodes.ATTR_MODULE_PACKAGES:
                body = new ModulePackagesAttribute();
                break;
            case Opcodes.ATTR_MODULE_MAIN_CLASS:
                body = new ModuleMainClassAttribute();
                break;
            case Opcodes.ATTR_NEST_HOST:
                body = new NestHostAttribute();
                break;
            case Opcodes.ATTR_NEST_MEMBERS:
                body = new NestMembersAttribute();
                break;
            case Opcodes.ATTR_RECORD:
                body = new RecordAttribute(jclass);
                break;
            case Opcodes.ATTR_PERMITTED_SUBCLASSES:
                body = new PermittedSubclassesAttribute();
                break;
            default:
                body = new GenericBody();
                break;
        }
        body.read(buf0);
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(nameIndex);
        ByteBuf buf0 = DynamicByteBuf.allocate();
        body.write(buf0);
        byte[] data = buf0.getRawBuffer();
        buf.writeInt(data.length);
        buf.write(data);
    }
}
