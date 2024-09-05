package com.zaurtregulov.spring.rest.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
//`MyConfig`: Этот класс определяет конфигурацию приложения, включая конфигурацию бинов, сервисов и контроллеров.
// класс MyConfig используется вместо applicationContext.xml
@Configuration //этот аннотация указывает, что данный класс является конфигурационным
@ComponentScan(basePackages = "com.zaurtregulov.spring.rest") //этот аннотация указывает, что Spring должен искать бины в пакете com.zaurtregulov.spring.rest
@EnableWebMvc //этот аннотация указывает, что Spring должен искать бины в пакете org.springframework.web
@EnableTransactionManagement//этот аннотация указывает, что Spring должен искать бины в пакете org.springframework.transaction
public class MyConfig {

    @Bean
    public DataSource dataSource() { ///метод возвращает источник данных для приложения

        ComboPooledDataSource dataSource = new ComboPooledDataSource(); //класс ComboPooledDataSource используется для установки соединения с базой данных
        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");//указываем имя драйвер
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/employee?useSSL=false");//указываем адрес базы данных
            dataSource.setUser("root");//указываем имя пользователя
            dataSource.setPassword("Jiffbuh1");//указываем пароль
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }
    @Bean
    public LocalSessionFactoryBean sessionFactory() { //метод возвращает фабрику сессий, которая используется для создания сессий Hibernate, выполняя запросы к базе данных

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean(); //класс LocalSessionFactoryBean используется для создания фабрики сессий
        sessionFactory.setDataSource(dataSource()); //указываем источник данных - dataSource() базу данных
        sessionFactory.setPackagesToScan("com.zaurtregulov.spring.rest.entity"); //указываем пакет с моделями

        Properties hibernateProperties = new Properties(); //класс Properties используется для хранения настроек Hibernate
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect"); //указываем тип базы данных
        hibernateProperties.setProperty("hibernate.show_sql", "true");//указываем, что надо вывести SQL-запросы
        sessionFactory.setHibernateProperties(hibernateProperties);//указываем настройки Hibernate

        return sessionFactory;
    }
    @Bean
    public HibernateTransactionManager transactionManager() { //метод возвращает менеджер транзакций, который используется для управления транзакциями в Hibernate

        HibernateTransactionManager transactionManager = new HibernateTransactionManager();//класс HibernateTransactionManager используется для управления транзакциями
        transactionManager.setSessionFactory(sessionFactory().getObject());//указываем фабрику сессий - sessionFactory()

        return transactionManager;
    }
}
