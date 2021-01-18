1.整合log4j2，service或者web模块通过maven dependency分析，快速去除spring-boot-starter-logging依赖，否则会引起jar包冲突！！！
2.tomcat控制台乱码，添加-Dfile.encoding=UTF-8 vm参数
3.