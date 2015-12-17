
package cz.habarta.typescript.generator.parser;

import java.lang.reflect.*;


public class SourceType<T extends Type> {
    
    public final T type;
    public final Class<?> usedInClass;
    public final String usedInMember;

    public SourceType(T type) {
        this (type, null, null);
    }

    public SourceType(T type, Class<?> usedInClass, String usedInMember) {
        this.type = type;
        this.usedInClass = usedInClass;
        this.usedInMember = usedInMember;
    }

}
