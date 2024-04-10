package io.github.bootystar.mybatisplus.interfaces;

import java.io.Serializable;

/**
 * @author booty
 */
@FunctionalInterface
public interface MethodReference<R,T> extends Serializable {
    R method(T t);
}
