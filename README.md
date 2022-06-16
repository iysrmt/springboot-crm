[TOC]

# springboot-crm

## CRM的技术框架

 - 视图层(view): 展示数据, 跟用户交互. html, css, js, jquery, bootstrap
 - 控制层(controller): 控制业务处理的流程(接收请求, 接收参数, 封装参数; 根据不同的请求调用业务层处理业务; 根据处理结果, 返回响应信息). springMVC
 - 业务层(service): 处理业务逻辑(处理业务的步骤以及操作的原子性).
 - 持久层(dao/mapper): 操作数据库(jdbc), mybatis
 - 整合层: 维护类资源, 维护数据库资源. spring(IOC, AOP)

## 目标

- 对软件公司和软件开发有一定程度的了解
- 了解CRM项目的核心业务
- 能够独立完成CRM项目核心业务的开发
- 能够对前期所学的技术进行回顾, 熟练, 加深, 扩展
- 掌握互联网技术的基础课: Linux, Redis, Git

## 软件公司的组织结构

- 研发部 (程序员, 美工, DBA数据库管理员)
- 测试部
- 产品部
- 实施部
- 运维部
- 市场部

## 软件开发的生命周期

- 招标---标书
- 可行性分析---可行性分析报告
- 需求分析---需求文档
- 分析与设计
  - 架构设计
    - 物理架构设计
      - 应用服务器: `tomcat`, `weblogic`, `websphere`, `jboss`
      - 数据库服务器: `mysql`, `oracle`, `db2`, `sqlserver`
    - 数据架构设计
      - 代码分层: `视图层`-->`控制层`-->`业务层`-->`持久层`-->`数据库`
      - 技术选型: `java`, `.net`
  - 项目设计---项目设计文档
    - 物理模型设计: 数据表设计, 表与表之间的关系(设计工具powerdesigner->x.pdm)
    - 逻辑模型设计: 类, 类中的方法, 类和类之间的关系(设计工具rational rose->-.mdl)
    - 界面设计---项目原型
    - 算法设计---算法设计文档
- 搭建开发环境---技术架构文档
  - 创建项目->添加jar包->添加配置文件->添加静态页面->添加公共的类以及其他的资源->能够正常的启动
- 编码实现---注释
- 测试---测试用例
- 试运行---使用手册
- 上线---实施文档
- 运维---运维手册
- 文档编篡

## CRM项目的核心业务

- CRM项目的简介: Customer Relationship Management, 客户关系管理系统. 企业级应用, 传统应用. 通常是给销售或贸易销售型公司使用, 在市场, 销售, 服务等各环节中维护客户关系. CRM项目的宗旨: 增加新客户, 留住老客户, 把已有客户转化为忠诚客户.
- CRM是一类项目, 我们的CRM项目是给一个大型的进出口贸易公司来使用的, 做大宗商品进出口贸易. 商品是受国家管制的.
- CRM核心业务
  - 系统管理功能: 不是直接处理业务数据, 为了保证业务管理的功能正常安全运行的功能. 用户登陆, 安全退出, 登陆验证等. 给超级管理员, 开发运维人员使用.
  - 业务管理功能: 处理业务数据
    - 市场活动: 市场部, 设计市场活动营销活动
    - 线索: 销售部(初级销售), 增加线索
    - 客户和联系人: 销售部(高级销售), 有效的区分和跟踪客户和联系人
    - 交易: 销售部(高级销售), 更好的区分和统计交易的各个阶段.
    - 售后回访: 客服部, 妥善安排售后回访. 主动提醒.
    - 统计图表: 管理层, 统计交易表中各个阶段的数据量.

## 本CRM项目的数据库

- 数据表

  - tbl_user 用户表
  - tbl_dic_type 数据字典类型表
  - tbl_dic_value 数据字典值
  - tbl_activity 市场活动表
  - tbl_activity_remark 市场活动备注表
  - tbl_clue 线索表
  - tbl_clue_remark 线索备注表
  - tbl_cule_activity_relation 线索和市场活动的关联关系表
  - tbl_customer 客户表
  - tbl_customer_remark 客户备注表
  - tbl_contacts 联系人表
  - tbl_contacts_remark 联系人备注表
  - tbl_contacts_activity_relation 联系人和市场活动的关联关系表.
  - tbl_tran 交易表
  - tbl_tran_remark 交易备注表
  - tbl_tran_history 交易历史表
  - tbl_task 任务表

- 关键字段

  - 主键字段

    - 在数据库表中, 如果有一组字段能够唯一确定一条记录, 则可以把它设计成表的主键字段. 推荐使用一个字段做主键, 不推荐多个字段做主键, 推荐使用没有业务含义的字段.
    - 主键字段的类型和长度由主键值的生成方式来决定. 
    - 主键值的生成方式有
      - 自增: 借助数据库自身主键生成机制, 数值型 长度由数据量来决定. 实际开发不使用, 效率低.
      - assighed: 程序员手动生成主键值, 需要满足唯一性, 使用算法实现: 
        - `hi/low` 数值型 长度由数据量决定
        - `UUID` 字符串类型 长度是32位
      - 共享主键: 由另一张表的类型长度决定
      - 联合主键: 由多个字段的类型和长度决定

  - 外键字段

    - 用来确定表和表之间的关系
    - 一对多: A表中的一条记录对应B表中的多条记录, B表中的一条记录只能对应A表的一条记录.
      - 内连接: 查询所有符合条件的数据, 并且要求结果在两张表中都有对应的记录.
      - 左/右外连接: 查询左/右侧表中所有符合条件的数据, 即使在右/左侧表中没有相对应的记录.
      - 内外连接如何选择? 如果外键不能为空, 则优先使用内连接. 如果外键可以为空, 看情况使用外键.
    - 一对一: A表中的一条记录对应B张表的一条记录, B表中的一条记录也只能对应A表中的一条记录.
      - 共享主键, 不推荐.
      - 唯一性约束外键
    - 多对多: A表中的一条记录可以对应B表中的多条记录, B表中的一条记录也可以对应A表中的多条记录.
      - 多对多的时候, 会创建一个专门存储外键的表来关联A, B两张表, 这里假设该表为C表.
      - 添加数据时, 先添加父表(A, B)记录, 再添加子表C记录.
      - 删除数据时, 先删除子表C记录, 再删除父表(A, B)记录.
      - 查询记录是, 可能会进行关联查询.

  - 关于日期和时间的字段

    - java和mysql的日期存储格式不同, 存取需要转换, 因为比较麻烦, 所以数据库中都以字符串来保存.

    - | java日期格式        | 数据库存储类型 |
      | ------------------- | -------------- |
      | yyyy-MM-dd          | char(10)       |
      | yyyy-MM-dd HH:mm:ss | char(19)       |

## 项目开发

- 分析需求

- 分析与设计

- 编码实现

- 测试

## mybatis逆向工程

- 简介: 根据表生成mapper层三部分代码: 实体类, mapper接口, 映射文件.

- 使用mybatis逆向工程:

  - 创建Maven工程: mybatis-generator

  - 添加Maven插件

    ```xml
    <build>
        <plugins>
            <plugin>
                <!--  https://mvnrepository.com/artifact/org.mybatis.generator/mybatis-generator-maven-plugin  -->
                <groupId>org.mybatis.generator</groupId>
                <artifactId>mybatis-generator-maven-plugin</artifactId>
                <version>1.4.1</version>
                <dependencies>
                    <dependency>
                        <groupId>mysql</groupId>
                        <artifactId>mysql-connector-java</artifactId>
                        <version>8.0.29</version>
                    </dependency>
                </dependencies>
                <configuration>
                    <verbose>true</verbose>
                    <overwrite>true</overwrite>
                </configuration>
            </plugin>
        </plugins>
    </build>
    ```
  
  - 添加文件src/main/java/resources/generatorConfig.xml
  
    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE generatorConfiguration
            PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
            "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
    
    <generatorConfiguration>
        
        <properties resource="generator.properties"/>
        
        <!--
            context标签
                targetRuntime属性
                    MyBatis3DynamicSql属性值
                        默认, 可忽略. 生成注解方式的接口, 会忽略javaClientGenerator标签的type值
                    MyBatis3Kotlin属性值
                        以Kotlin语言生成, 具体生成同上 
                    MyBatis3属性值
                        生成动态sql的CRUD
                    MyBatisSimple属性值
                        生成5个最基本的CRUD方法
        -->
        <context id="MysqlTables" targetRuntime="MyBatis3Simple">
            <jdbcConnection driverClass="${jdbc.driverClass}"
                            connectionURL="${jdbc.connectionURL}"
                            userId="${jdbc.userId}"
                            password="${jdbc.password}">
                <property name="nullCatalogMeansCurrent" value="true"/>
            </jdbcConnection>
    
            <javaTypeResolver>
                <property name="forceBigDecimals" value="false"/>
            </javaTypeResolver>
    
            <!-- 实体类 -->
            <javaModelGenerator targetPackage="org.example.domain" targetProject="../crm/src/main/java">
                <property name="enableSubPackages" value="true"/>
                <property name="trimStrings" value="true"/>
            </javaModelGenerator>
    
            <!-- mapper接口 -->
            <sqlMapGenerator targetPackage="org.example.mapper" targetProject="../crm/src/main/resources">
                <property name="enableSubPackages" value="true"/>
            </sqlMapGenerator>
    
            <!--
                javaClientGenerator标签
                    type属性
                        ANNOTATEDMAPPER属性值
    						生成注解类型 mapper
                        XMLMAPPER属性值
    						生成xml文件类型 mapper
            -->
            <javaClientGenerator type="XMLMAPPER" targetPackage="org.example.mapper" targetProject="../crm/src/main/java">
                <property name="enableSubPackages" value="true"/>
            </javaClientGenerator>
    
            <!--
                table标签
                    tableName属性
    					数据表名
                    domainObjectName属性
    					实体类名
            -->
            <table tableName="tbl_table" domainObjectName="Table"
                   enableCountByExample="false" enableUpdateByExample="false"
                   enableDeleteByExample="false" enableSelectByExample="false"
                   selectByExampleQueryId="false"/>
            
        </context>
    
    </generatorConfiguration>
    ```
  
  - 添加文件src/main/java/resources/generator.properties
  
    ```properties
    jdbc.driverClass=com.mysql.cj.jdbc.Driver
    jdbc.connectionURL=jdbc:mysql://localhost:3306/crm
    jdbc.userId=root
    jdbc.password=root
    ```
  
  - 添加数据库连接信息, 代码保存目录, 表的信息.

## 关于

参考

- [动力节点SSM框架项目实战|Spring+Mybatis+Springmvc框架项目实战整合-【CRM客户管理系统】](https://www.bilibili.com/video/BV1tZ4y1d7kg)

**本项目只做个人学习研究之用, 不得用于商业用途! **
