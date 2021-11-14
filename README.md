# HyperPlugin

HyperPlat, 智能合约一体化开发平台 -- idea 插件.

## usage

通过 `Open Cello` 按钮打开 cello 平台进行部署.

默认 cello 平台路径为 localhost:8080, 可以手动设置.

同时, `Show SC Apis` 可以从默认路径 `src/main/js/lib/SmartContract.js` 中读取 api 信息, 并呈现在表格中.

## Project Structure

项目结构如下 ( 根目录为 src/main/java/cn/edu/nju/hyperplugin ):

```text
.
├── HyperPlatFactory.java
├── Shower.java
└── util
    ├── Browser.java
    ├── Cello.java
    ├── Config.java
    ├── OS.java
    └── SmartContract.java
```

- util: 工具类
- HyperPlatFactory: Tool Window 的注册工厂类
- Shower: 插件实现类

Plugin Display Structure:

```text
---
toolbar: buttons
---
table: api info
---
cello path input field
---
```