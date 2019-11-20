package test.kotlin.config

import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.hibernate5.HibernateTransactionManager
import org.springframework.orm.hibernate5.LocalSessionFactoryBean
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.sql.DataSource

@EnableTransactionManagement
@Configuration
open class DBConfig(val context: ApplicationContext) {

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