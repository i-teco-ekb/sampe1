package test.kotlin.config

import org.apache.commons.dbcp2.BasicDataSource
import org.hibernate.SessionFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.hibernate5.HibernateTransactionManager
import org.springframework.orm.hibernate5.LocalSessionFactoryBean
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource


@EnableTransactionManagement
@Configuration
open class DBConfig(val context: ApplicationContext) {

    @Bean
    open fun sessionFactory(dataSource: DataSource) : LocalSessionFactoryBean = LocalSessionFactoryBean()
            .apply {
                setConfigLocation(context.getResource("classpath:hibernate.cfg.xml"))
//                setDataSource(dataSource)
            }

//    @Bean
//    open fun sessionFactory(factory: EntityManagerFactory): SessionFactory {
//        if (factory.unwrap(SessionFactory::class.java) == null) {
//            throw NullPointerException("factory is not a hibernate factory")
//        }
//        return entityManagerFactory.unwrap(SessionFactory::class.java)
//    }

    @Bean
    open fun getDataSource(): DataSource = BasicDataSource()
            .apply {
                driverClassName = "org.h2.Driver"
                url = "jdbc:h2:file:./db/db"
                username = "sa"
                password = ""
            }
//        dataSource.driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
//        dataSource.url = "jdbc:sqlserver://localhost:1433;databaseName=Licenta"
//        dataSource.username = "sa"
//        dataSource.password = "admin9"
//        return dataSource

    @Bean
    open fun hibernateTransactionManager(sessionFactory: LocalSessionFactoryBean) : HibernateTransactionManager = HibernateTransactionManager()
            .apply {
                this.sessionFactory = sessionFactory.getObject()
            }
}