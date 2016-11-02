package udesc.br.rakesfoot.core.model;

import udesc.br.rakesfoot.core.persistence.annotation.DataBaseInfo;
import udesc.br.rakesfoot.core.util.BeanUtils;
import udesc.br.rakesfoot.core.util.ClassUtils;
import udesc.br.rakesfoot.core.util.JSonUtils;

/**
 * Base class for Entities
 *
 *
 * @author Samuel Felício Adriano <felicio.samuel@gmail.com>
 * @since  08/05/2016
 */
public abstract class Entity implements java.io.Serializable, Cloneable {

    private static final long serialVersionUID = 0L;


    @Override
    public int hashCode() {
        long hashCode = 0;

        for(java.lang.reflect.Field field : this.getClass().getDeclaredFields()) {
            if(field.isAnnotationPresent(DataBaseInfo.class) &&
                    field.getAnnotation(DataBaseInfo.class).key()) {
                long valor = Long.parseLong(BeanUtils.callGetter(this, field.getName()).toString());
                hashCode  += valor;
            }
        }

        return (int) hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Entity)) {
            return false;
        }

        return obj.hashCode() == this.hashCode();
    }

    @Override
    public String toString() {
        return "";
    }

    /**
     * Gets a Clone of the Current state of the Class
     */
    @Override
    public Entity clone() {
        Entity clone = ClassUtils.getNewPOJO(this.getClass());

        for(java.lang.reflect.Field field : this.getClass().getDeclaredFields()) {
            if(field.isAnnotationPresent(DataBaseInfo.class)) {
                BeanUtils.callSetter(clone, field.getName(), BeanUtils.callGetter(this, field.getName(), true), true);
            }
        }

        return clone;
    }

}