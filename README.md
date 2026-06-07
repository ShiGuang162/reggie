# 瑞吉外卖 (Reggie Take Out)

一个基于 Spring Boot + MyBatis-Plus + Vue 的全栈外卖订餐系统，包含管理后台和用户端 H5 页面，支持菜品管理、订单处理、购物车、地址管理、短信验证码登录等核心功能。

---

## 技术栈

| 层级 | 技术 |
|------|------|
| 后端框架 | Spring Boot 2.4.5 |
| ORM | MyBatis-Plus 3.4.2 |
| 数据库 | MySQL 8.0 |
| 连接池 | Druid 1.1.23 |
| 缓存 | Redis |
| 短信服务 | 阿里云 SMS |
| 前端（管理后台） | Vue 2 + ElementUI |
| 前端（用户端） | Vue 2 + Vant |
| 构建工具 | Maven |
| JDK | Java 1.8+ |

---

## 项目结构

```
reggie_take_out/
├── src/main/java/com/HNX/
│   ├── ReggieApplication.java          # 启动类
│   ├── config/                         # 配置类
│   │   ├── MybatisPlusConfig.java      # MyBatis-Plus 配置
│   │   ├── RedisConfig.java            # Redis 配置
│   │   └── WebMvcConfig.java           # WebMvc 配置（静态资源映射、拦截器）
│   ├── controller/                     # 控制器层（11 个模块）
│   │   ├── AddressBookController.java  # 地址簿
│   │   ├── CategoryController.java     # 菜品/套餐分类
│   │   ├── CommonController.java       # 通用（文件上传下载）
│   │   ├── DishController.java         # 菜品管理
│   │   ├── EmployeeController.java     # 员工管理
│   │   ├── OrderController.java        # 订单管理
│   │   ├── OrderDetailController.java  # 订单明细
│   │   ├── SetmealController.java      # 套餐管理
│   │   ├── ShoppingCartController.java # 购物车
│   │   └── UserController.java         # 用户管理
│   ├── service/ + service/impl/        # Service 接口与实现
│   ├── mapper/                         # MyBatis-Plus Mapper
│   ├── entity/                         # 实体类（12 个）
│   │   ├── AddressBook.java
│   │   ├── Category.java
│   │   ├── Dish.java
│   │   ├── DishFlavor.java
│   │   ├── Employee.java
│   │   ├── OrderDetail.java
│   │   ├── Orders.java
│   │   ├── Setmeal.java
│   │   ├── SetmealDish.java
│   │   ├── ShoppingCart.java
│   │   └── User.java
│   ├── dto/                            # 数据传输对象
│   │   ├── DishDto.java                # 菜品 + 口味
│   │   └── SetmealDto.java             # 套餐 + 菜品
│   ├── common/                         # 通用工具
│   │   ├── R.java                      # 统一响应结果
│   │   ├── BaseContext.java            # 线程上下文（用户ID）
│   │   ├── CustomException.java        # 自定义异常
│   │   ├── GlobalExceptionHandler.java # 全局异常处理
│   │   ├── JacksonObjectMapper.java    # JSON 序列化配置
│   │   └── MyMetaObjecthandler.java    # 自动填充字段
│   ├── filter/                         # 过滤器
│   │   └── LoginCheckFilter.java       # 登录状态校验
│   └── utils/                          # 工具类
│       ├── SMSUtils.java               # 阿里云短信发送
│       └── ValidateCodeUtils.java      # 验证码生成
├── src/main/resources/
│   ├── application.yml                 # 默认配置（无敏感信息）
│   ├── application-prod.yml            # 生产环境配置
│   ├── application-local.yml           # 本地开发配置（含密码，已加入 .gitignore）
│   ├── backend/                        # 管理后台前端页面
│   │   ├── index.html                  # 后台首页
│   │   ├── page/                       # 功能页面
│   │   ├── api/                        # JS 接口封装
│   │   ├── js/                         # 公共 JS
│   │   └── plugins/                    # Vue、ElementUI、Axios
│   └── front/                          # 用户端 H5 页面
│       ├── index.html                  # 首页（菜品浏览）
│       ├── page/                       # 功能页面
│       ├── api/                        # JS 接口封装
│       ├── js/                         # 公共 JS
│       └── styles/                     # CSS 样式
├── init-db/
│   └── reggie.sql                      # 数据库初始化脚本
├── Dockerfile                          # Docker 构建文件
├── docker-compose.yml                  # Docker Compose 编排
└── pom.xml                             # Maven 配置
```

---

## 业务模块

| 模块 | 功能说明 |
|------|---------|
| **Employee** | 员工增删改查、登录（MD5 加密） |
| **Category** | 菜品分类 / 套餐分类管理 |
| **Dish** | 菜品管理（含口味信息）、图片上传 |
| **Setmeal** | 套餐管理（关联多个菜品） |
| **User** | 用户注册 / 登录（手机验证码） |
| **AddressBook** | 收货地址增删改查、默认地址 |
| **ShoppingCart** | 购物车添加 / 清空 / 查询 |
| **Orders** | 下单、订单查询、状态更新 |
| **OrderDetail** | 订单明细查询 |
| **Common** | 文件上传（图片）到本地磁盘 |

---

## 环境要求

- **JDK**: 1.8 或更高
- **Maven**: 3.6+
- **MySQL**: 8.0+
- **Redis**: 5.0+

---

## 快速开始

### 1. 克隆项目

```bash
git clone https://github.com/ShiGuang162/reggie.git
cd reggie
```

### 2. 创建本地配置文件

项目已配置 `.gitignore` 保护敏感信息，你需要手动创建本地配置文件：

```bash
cat > src/main/resources/application-local.yml << 'EOF'
server:
  port: 8080

spring:
  application:
    name: reggie_take_out
  datasource:
    druid:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://localhost:3306/reggie?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true
      username: root
      password: 你的MySQL密码
      initial-size: 5
      min-idle: 5
      max-active: 30
      max-wait: 20000
      test-while-idle: true
      validation-query: SELECT 1
  redis:
    host: 127.0.0.1
    port: 6379
    password: 你的Redis密码
    database: 0

mybatis-plus:
  configuration:
    map-underscore-to-camel-case: true
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  global-config:
    db-config:
      id-type: ASSIGN_ID

logging:
  level:
    root: info
    com.HNX.mapper: debug

reggie:
  path: /Users/shiguang/Desktop/项目/reggie_take_out/图片资源/
  sms:
    expose-code: true

aliyun:
  sms:
    access-key-id: ''
    access-key-secret: ''
    region-id: cn-hangzhou
    sign-name: 速通互联验证码
    template-code: 100001
EOF
```

> **注意**：`application-local.yml` 已加入 `.gitignore`，不会被提交到 Git。

### 3. 初始化数据库

```bash
# 创建数据库
mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS reggie CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"

# 导入表结构和初始数据
mysql -u root -p reggie < init-db/reggie.sql
```

### 4. 启动项目

#### 方式一：命令行启动

```bash
# 打包
mvn clean package -DskipTests

# 启动（使用 local 配置）
java -jar target/reggie_take_out-1.0-SNAPSHOT.jar --spring.profiles.active=local
```

#### 方式二：IDEA 中启动

1. 打开 `ReggieApplication.java`
2. 点击运行按钮
3. 在 Run Configuration 的 VM options 中添加：
   ```
   -Dspring.profiles.active=local
   ```

#### 方式三：Maven 插件启动

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

### 5. 访问系统

| 端 | 地址 |
|----|------|
| 管理后台 | http://localhost:8080/backend/index.html |
| 用户端 H5 | http://localhost:8080/front/index.html |

**默认管理员账号**：`admin` / `123456`

---

## 生产部署

### 方式一：Jar 包部署

```bash
# 打包
mvn clean package -DskipTests

# 通过环境变量启动
export SPRING_DATASOURCE_URL=jdbc:mysql://生产MySQL地址:3306/reggie?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true
export SPRING_DATASOURCE_USERNAME=root
export SPRING_DATASOURCE_PASSWORD=你的生产密码
export SPRING_REDIS_HOST=生产Redis地址
export SPRING_REDIS_PASSWORD=你的Redis密码
export ALIYUN_SMS_ACCESS_KEY_ID=你的阿里云AccessKey
export ALIYUN_SMS_ACCESS_KEY_SECRET=你的阿里云Secret
export REGGIE_PATH=/app/images/

java -jar target/reggie_take_out-1.0-SNAPSHOT.jar --spring.profiles.active=prod
```

### 方式二：Docker 部署

```bash
# 构建并启动所有服务（MySQL + Redis + App）
docker-compose up -d

# 查看日志
docker-compose logs -f app

# 停止
docker-compose down
```

Docker Compose 会自动：
- 启动 MySQL 8.0 并初始化数据库
- 启动 Redis 7 并设置密码
- 构建并启动 Spring Boot 应用
- 映射端口 `8080`

---

## 配置文件说明

| 文件 | 用途 | 是否提交 Git |
|------|------|-------------|
| `application.yml` | 默认配置，仅包含环境变量引用 | ✅ |
| `application-prod.yml` | 生产配置，纯环境变量模式 | ✅ |
| `application-local.yml` | 本地开发配置（含真实密码） | ❌ 已加入 `.gitignore` |

---

## 安全说明

- **密码加密**：员工密码使用 MD5 加密存储
- **敏感信息保护**：数据库密码、Redis 密码、阿里云 AccessKey 均通过环境变量或外部配置文件注入，不硬编码在代码中
- **短信验证码**：本地开发时可通过 `reggie.sms.expose-code=true` 在响应中查看验证码（生产环境请关闭）
- **登录校验**：`LoginCheckFilter` 拦截未登录请求，支持员工端和用户端分别校验

---

## 常见问题

### Q1: 启动时报 `newPosition < 0` 错误？

这是 Maven Resources 插件的已知问题，通常由 `application-local.yml` 中的空值或特殊字符引起。请确保该文件中的值不为空字符串，例如：

```yaml
aliyun:
  sms:
    access-key-id: 'dummy'      # 不要留空
    access-key-secret: 'dummy'  # 不要留空
```

### Q2: 图片上传后无法访问？

确保 `reggie.path` 配置的路径存在且应用有读写权限：

```yaml
reggie:
  path: /Users/shiguang/Desktop/项目/reggie_take_out/图片资源/
```

### Q3: 短信发送失败？

需要配置真实的阿里云 SMS 参数：

```yaml
aliyun:
  sms:
    access-key-id: 你的AccessKey
    access-key-secret: 你的Secret
    sign-name: 你的短信签名
    template-code: 你的模板CODE
```

本地测试时可开启 `reggie.sms.expose-code: true`，验证码会返回到响应中。

### Q4: 如何切换数据库或 Redis？

修改 `application-local.yml` 中的连接信息，或通过环境变量覆盖：

```bash
export SPRING_DATASOURCE_URL=jdbc:mysql://新地址:3306/reggie?...
export SPRING_REDIS_HOST=新地址
```

---

## 接口文档

项目启动后，可通过以下方式查看接口：

- 管理后台 API：`backend/api/*.js`
- 用户端 API：`front/api/*.js`
- 或直接浏览器访问各 Controller 的 REST 接口

---

## 贡献与反馈

如有问题或建议，欢迎提交 Issue 或 Pull Request。

---

## License

本项目仅供学习交流使用。
