package test.kotlin.config

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.hibernate5.HibernateTransactionManager
import org.springframework.orm.hibernate5.LocalSessionFactoryBean
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource


@EnableTransactionManagement
@Configuration
open class DBConfig(val context: ApplicationContext) {

    @Bean
    open fun dataSource(): DataSource {
        val dataSourceBuilder = DataSourceBuilder.create()
        dataSourceBuilder.driverClassName("org.h2.Driver")
        dataSourceBuilder.url("jdbc:h2:file:./db/db")
        dataSourceBuilder.username("sa")
        dataSourceBuilder.password("")
        return dataSourceBuilder.build()
    }

    @Bean
    open fun sessionFactory(dataSource: DataSource) : LocalSessionFactoryBean = LocalSessionFactoryBean()
            .apply {
                setConfigLocation(context.getResource("classpath:hibernate.cfg.xml"))
    }

    @Bean
    open fun hibernateTransactionManager(sessionFactory: LocalSessionFactoryBean) : HibernateTransactionManager = HibernateTransactionManager()
            .apply {
                this.sessionFactory = sessionFactory.getObject()
            }
}