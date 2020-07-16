##1. 关于 ojdbc6.jar 的安装方式
1. 使用 `IDEA` - `终端` 进入 `ojbdc6.jar` 所在的目录
2. 执行以下预计
```html
mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.4.0 -Dpackaging=jar -Dfile=ojdbc6.jar
```
3. 检查调用 `oracle` 引用的项目的 `POM` 文件是否存在，下面依赖，如不存在，则加入
```html
<dependency>
	<groupId>com.oracle</groupId>
	<artifactId>ojdbc6</artifactId>
	<version>11.2.0.4.0</version>
</dependency>
```