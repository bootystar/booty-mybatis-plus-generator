package io.github.bootystar.mybatisplus.interfaces;

import java.io.Serializable;

/**
 * @author booty
 */
@FunctionalInterface
public interface GetterReference<R> extends Serializable {
    R getter();
}
