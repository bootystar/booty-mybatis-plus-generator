package io.github.bootystar.mybatisplus.generator.config.child;

import io.github.bootystar.mybatisplus.generator.config.ConfigBase;
import io.github.bootystar.mybatisplus.generator.config.ConfigBaseBuilder;

/**
 * @author booty
 * @since 2023/9/15 14:49
 */
public class ParentConfig extends ConfigBase {

    /**
     * 显示 service impl方法
     */
    protected boolean showServiceImplMethod = true;

    /**
     * 显示mapper方法
     */
    protected boolean showMapperMethod = true;

    public boolean isShowServiceImplMethod() {
        return showServiceImplMethod;
    }

    public boolean isShowMapperMethod() {
        return showMapperMethod;
    }

    /**
     * 构造器
     * @author booty
     * @since 2023/12/19
     */
    public static class Builder extends ConfigBaseBuilder<ParentConfig, Builder> {

        @Override
        protected ParentConfig initConfig() {
            return new ParentConfig();
        }

        @Override
        protected Builder initBuilder() {
            return this;
        }


        /**
         * 显示服务impl的父类方法
         *
         * @param b b
         * @return {@code U }
         * @author booty
         * @since 2023/09/18 11:32
         */
        public Builder showServiceImplMethod(boolean b) {
            this.config.showServiceImplMethod = b;
            return this;
        }

        /**
         * 显示mapper的父类方法
         *
         * @param b b
         * @return {@code U }
         * @author booty
         * @since 2023/09/18 11:32
         */
        public Builder showMapperMethod(boolean b) {
            this.config.showMapperMethod = b;
            return this;
        }


    }

}


