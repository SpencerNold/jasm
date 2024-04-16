package me.spencernold.assembler.writer.access;

public interface AccessTranslator {

    String translate(int access);

    enum Type {
        CLASS, FIELD, METHOD;
    }

    class Factory {

        private final Type type;

        public Factory(Type type) {
            this.type = type;
        }

        public AccessTranslator create() {
            switch (type) {
                case CLASS:
                    return new ClassAccessTranslator();
                case FIELD:
                    return new FieldAccessTranslator();
                case METHOD:
                    return new MethodAccessTranslator();
                default:
                    return null;
            }
        }
    }
}
