package lk.easycarrental.spring.config;

/**
 * @author : Danuja 6/14/21 8:09 PM
 * @version : 1.0
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.core.env.Environment;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "lk.easycarrental.spring.repo")
@PropertySource("classpath:application.properties")
public class JPAConfig {

    @Autowired
    Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds, JpaVendorAdapter va) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(ds);
        factoryBean.setJpaVendorAdapter(va);
        factoryBean.setPackagesToScan(env.getRequiredProperty("entity.package"));
        return factoryBean;

    }

    @Bean
    public DataSource dataSource() throws NamingException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("my.driver"));
        dataSource.setUrl(env.getRequiredProperty("db.url"));
        dataSource.setUsername(env.getRequiredProperty("db.username"));
        dataSource.setPassword(env.getRequiredProperty("db.password"));
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter vendorAdapter() {
        HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
        va.setDatabasePlatform(env.getRequiredProperty("db.dialect"));
        va.setDatabase(Database.MYSQL);
        va.setGenerateDdl(true);
        va.setShowSql(true);
        return va;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

   /* @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds, JpaVendorAdapter va) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(ds);
        factoryBean.setJpaVendorAdapter(va);
        factoryBean.setPackagesToScan("lk.easycarrental.spring.entity");

        return factoryBean;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/EasyCarRental?createDatabaseIfNotExist=true");
        dataSource.setUsername("root");
        dataSource.setPassword("25412541");

        return dataSource;
    }

    @Bean
    public JpaVendorAdapter vendorAdapter() {
        HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
        va.setDatabasePlatform("org.hibernate.dialect.MySQL57Dialect");
        va.setDatabase(Database.MYSQL);
        va.setGenerateDdl(true);
        va.setShowSql(true);

        return va;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }*/
}
