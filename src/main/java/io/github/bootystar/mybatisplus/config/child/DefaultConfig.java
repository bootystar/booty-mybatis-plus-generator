package io.github.bootystar.mybatisplus.config.child;

import io.github.bootystar.mybatisplus.config.ConfigBase;
import io.github.bootystar.mybatisplus.config.ConfigBaseBuilder;

/**
 * @author booty
 * @since 2023/12/19
 */
public class DefaultConfig extends ConfigBase {


    public static class Builder extends ConfigBaseBuilder<DefaultConfig, Builder> {
        @Override
        protected DefaultConfig initConfig() {
            return new DefaultConfig();
        }

        @Override
        protected Builder initBuilder() {
            return this;
        }
    }
}
