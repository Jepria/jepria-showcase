# Установка приложений на Tomcat средствами Maven.
Все команды установки приложений следует запускать из папки **App**.
- `mvn tomcat7:deploy-only -N -Ddeploy.url=http://localhost:8080/manager/text -Ddeploy.username=username -Ddeploy.password=password -Ddeploy.path=/ContextPath` – установка приложения с явным заданием параметров подключения к Tomcat.
Приложение должно быть собрано предварительно, *war*-файл приложения должен размещаться в папке *lib*

- `mvn tomcat7:deploy-only -N` – установка приложения с параметрами подключения к Tomcat, заданными в родительском *pom.xml* проекта.
Приложение должно быть собрано предварительно, *war*-файл приложения должен размещаться в папке *lib*

- `mvn tomcat7:undeploy -N` – удаление приложения с Tomcat

Замечания
- Для установки приложений требуется [установить и настроить](maven-installation.md) maven.
- Для корректной установки Maven-плагином необходимо включить опцию *antiJARLocking* (Tomcat < 8) или *antiResourceLocking* (Tomcat >= 8) для элемента *<Context>* в конфигурационном файле Tomcat *context.xml*:
    ```
    <Context antiResourceLocking="true">
    ```
