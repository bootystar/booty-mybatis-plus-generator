package io.github.bootystar.mybatisplus.generator.config;


import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 配置基类
 * @author booty
 */
public abstract class ConfigBase implements IConfig {
    private static final Logger log = LoggerFactory.getLogger(ConfigBase.class);

    //--------------常量---------------
    protected final String shift3 = "#";
    protected final String shift4 = "$";
    protected final String shift5 = "%";
    protected final String shift8 = "*";
    protected final String shiftLeft = "{";
    protected final String shiftRight = "}";


    /**
     * 自定义文件
     */
    protected List<CustomFile> customFiles;

    @Override
    public Map<String, Object> renderData(TableInfo tableInfo) {
        HashMap<String, Object> data = new HashMap<>();
        // 添加自定义字段
        try {
            for (Field field : this.getClass().getDeclaredFields()) {
                if (unAccessibleFiled(field)) continue;
                String name = field.getName();
                field.setAccessible(true);
                data.put(name, field.get(this));
            }
            for (Field field : ConfigBase.class.getDeclaredFields()) {
                if (unAccessibleFiled(field)) continue;
                String name = field.getName();
                field.setAccessible(true);
                if (!"customFiles".equals(field.getName())) {
                    data.put(name, field.get(this));
                }
            }
        } catch (IllegalAccessException e) {
            log.error("Generate Injection Field Error Please Report to Developer",e);
        }

        // 当前时间
        data.put("nowTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        // 时间类型列表
        List<JdbcType> jdbcTimeTypes =
                Arrays.asList(
                        JdbcType.DATE,
                        JdbcType.TIME,
                        JdbcType.TIMESTAMP,
                        JdbcType.DATETIMEOFFSET,// SQL Server 2008
                        JdbcType.TIME_WITH_TIMEZONE,// JDBC 4.2 JDK8
                        JdbcType.TIMESTAMP_WITH_TIMEZONE // JDBC 4.2 JDK8
                );
        // 对应fields[i].metaInfo.jdbcType
        data.put("jdbcTimeTypes", jdbcTimeTypes);
        // 排序字段
        List<TableField> fields = tableInfo.getFields();
        List<String> existColumnNames = fields.stream().map(TableField::getColumnName).collect(Collectors.toList());
        if (orderColumnMap != null && orderColumnMap.size() > 0) {
            orderColumnMap.entrySet().stream()
                    .filter(e -> existColumnNames.contains(e.getKey()))
                    .map(e -> String.format("a.%s%s", e.getKey(), e.getValue() ? " desc" : ""))
                    .reduce((e1, e2) -> e1 + "," + e2)
                    .ifPresent(e -> data.put("orderBySql", e))
            ;

        }
        return data;
    }

    protected static boolean unAccessibleFiled(Field field) {
        field.setAccessible(true);
        int modifiers = field.getModifiers();
//        if (Modifier.isFinal(modifiers)){
//            return true;
//        }
        if (Modifier.isStatic(modifiers)) {
            return true;
        }
        if (Modifier.isNative(modifiers)) {
            return true;
        }
        return false;
    }




    public void setCustomFiles(List<CustomFile> customFiles) {
        this.customFiles = customFiles;
    }

    @Override
    public List<CustomFile> getCustomFiles() {
        return this.customFiles;
    }

    @Override
    public boolean getFileOverride() {
        return this.fileOverride;
    }



    //--------------返回结果相关配置---------------

    /**
     * DTO所在包
     */
    protected String DTOPackage = "dto";
    /**
     * VO所在包
     */
    protected String VOPackage = "vo";

    /**
     * 返回结果类
     */
    protected String returnResultClass;

    /**
     * 返回结果类所在包
     */
    protected String returnResultClassPackage;

    /**
     * 返回结果是否支持泛型
     */
    protected boolean returnResultGenericType;

    /**
     * 返回结果静态方法名
     */
    protected String returnResultDefaultStaticMethodName;

    // ------------------controller相关配置----------------

    /**
     * controller是否使用@RequestBody注解
     */
    protected boolean requestBody = true;

    /**
     * 是否添加参数校验
     */
    protected boolean enableValidated = true;
    /**
     * 是否添加跨域注解
     */
    protected boolean enableOrigins;

    /**
     * 所有请求都使用post方法
     */
    protected boolean allPost = false;
    /**
     * 请求基础url
     */
    protected String baseUrl;

    /**
     * 是否覆盖已有文件
     */
    protected boolean fileOverride;

    /**
     * VO是否生成ResultMap
     */
    protected boolean resultMapForVO;
    /**
     * VO是否生成字段注释
     */
    protected boolean fieldAnnotationOnVO;

    /**
     * 新增排除的字段
     */
    protected Collection<String> insertExcludeFields;

    /**
     * 修改排除的字段
     */
    protected Collection<String> updateExcludeFields;

    /**
     * 排序字段map
     * 字段名 -> 是否倒序
     */
    protected Map<String, Boolean> orderColumnMap;

    /**
     * java api包
     */
    protected String javaApiPackage = "javax";

    /**
     * 新增DTO
     */
    protected boolean generateInsert = true;
    /**
     * 更新DTO
     */
    protected boolean generateUpdate = true;
    /**
     * 生成删除方法
     */
    protected boolean generateDelete = true;
    /**
     * 查询DTO
     */
    protected boolean generateSelect = true;
    /**
     * 导入DTO
     */
    protected boolean generateImport = true;
    /**
     * 导出DTO
     */
    protected boolean generateExport = true;

    /**
     * rest样式
     */
    protected boolean restStyle = true;

    /**
     * 使用vo生出导出方法(不生成额外ExportDTO)
     */
    protected boolean exportOnVO;
    /**
     * 使用vo生出导入方法(不生成额外ImportDTO)
     */
    protected boolean importOnVO;


    public String getShift3() {
        return shift3;
    }

    public String getShift4() {
        return shift4;
    }

    public String getShift5() {
        return shift5;
    }

    public String getShift8() {
        return shift8;
    }

    public String getShiftLeft() {
        return shiftLeft;
    }

    public String getShiftRight() {
        return shiftRight;
    }

    public String getDTOPackage() {
        return DTOPackage;
    }

    public String getVOPackage() {
        return VOPackage;
    }

    public String getReturnResultClass() {
        return returnResultClass;
    }

    public String getReturnResultClassPackage() {
        return returnResultClassPackage;
    }

    public boolean isReturnResultGenericType() {
        return returnResultGenericType;
    }

    public String getReturnResultDefaultStaticMethodName() {
        return returnResultDefaultStaticMethodName;
    }

    public boolean isRequestBody() {
        return requestBody;
    }

    public boolean isEnableValidated() {
        return enableValidated;
    }

    public boolean isEnableOrigins() {
        return enableOrigins;
    }

    public boolean isAllPost() {
        return allPost;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public boolean isFileOverride() {
        return fileOverride;
    }

    public boolean isResultMapForVO() {
        return resultMapForVO;
    }

    public boolean isFieldAnnotationOnVO() {
        return fieldAnnotationOnVO;
    }

    public Collection<String> getInsertExcludeFields() {
        return insertExcludeFields;
    }

    public Collection<String> getUpdateExcludeFields() {
        return updateExcludeFields;
    }

    public Map<String, Boolean> getOrderColumnMap() {
        return orderColumnMap;
    }

    public String getJavaApiPackage() {
        return javaApiPackage;
    }

    public boolean isGenerateInsert() {
        return generateInsert;
    }

    public boolean isGenerateUpdate() {
        return generateUpdate;
    }

    public boolean isGenerateDelete() {
        return generateDelete;
    }

    public boolean isGenerateSelect() {
        return generateSelect;
    }

    public boolean isGenerateImport() {
        return generateImport;
    }

    public boolean isGenerateExport() {
        return generateExport;
    }

    public boolean isRestStyle() {
        return restStyle;
    }

    public boolean isExportOnVO() {
        return exportOnVO;
    }

    public boolean isImportOnVO() {
        return importOnVO;
    }
}
