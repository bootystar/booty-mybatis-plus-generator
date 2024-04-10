package io.github.bootystar.mybatisplus.interfaces;

import com.baomidou.mybatisplus.core.conditions.interfaces.Func;

import java.util.List;

/**
 * @author booty
 */
public interface EnhanceFunc<Children, R> extends Func<Children, R> {

    List<R> listByDto();

    List<R> oneByDto();

}
