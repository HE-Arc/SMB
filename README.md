# Installation mysql
```sql
create database db_smf;
create user 'springuser'@'%' identified by '1234';
grant all on db_smf.* to 'springuser'@'%';
```

# Pseudo live reload
[Source](https://stackoverflow.com/questions/23155244/spring-boot-hotswap-with-intellij-ide)