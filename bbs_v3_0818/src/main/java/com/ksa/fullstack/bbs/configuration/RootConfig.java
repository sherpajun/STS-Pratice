package com.ksa.fullstack.bbs.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
//@PropertySource("classpath:/db.properties")
@EnableTransactionManagement //오토네이션 기반 트랜잭션 관리 사용
//@MapperScan(basePackages ="com.ksa.fullstack.bbs.dao")
public class RootConfig {
	@Autowired
	ApplicationContext applicationContext;

	
	@Value("oracle.jdbc.driver.OracleDriver")
	private String driver;
	
	@Value("jdbc:oracle:thin:@127.0.0.1:1521:XE")
	private String url;
	
	@Value("human")
	private String username;
	
	@Value("1234")
	private String password;
	
	//@PropertySource을 사용할때는 아래와 같이 할 수 있음
    //    @Value("${jdbc.driver}")
	//    private String driver;
	
	//    @Value("${jdbc.url}")
	//    private String url;
	
	//    @Value("${jdbc.username")
	//    private String username;
	
	//    @Value("${jdbc.password")
	//    private String password;
	      
     @Bean
     public DataSource dataSource() {
    	 DriverManagerDataSource dmds = new DriverManagerDataSource();
//    	 BasicDataSource dmds = new BasicDataSource();
    	 dmds.setDriverClassName(driver);
    	 dmds.setUrl(url);
    	 dmds.setUsername(username);
    	 dmds.setPassword(password);
    	 return dmds;    	 
     }
     
     @Bean 
     public PlatformTransactionManager transactionManager(){
    	 DataSourceTransactionManager dataSourceTransactionManager= new DataSourceTransactionManager();
    	 dataSourceTransactionManager.setDataSource(dataSource());
    	 return dataSourceTransactionManager;    	 
     }
//   @Bean 생성자를 통해서 DataSource를 di받을수도 있음
//   public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource){
//  	 SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//  	 factoryBean.setDataSource(dataSource);   	
//  	 return null;    	 
//   }
     
     @Bean 
     public SqlSessionFactoryBean sqlSessionFactory(){
    	 SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    	 factoryBean.setDataSource(dataSource());
    	 factoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatisConfig/mybatisConfig.xml"));
    	 factoryBean.setMapperLocations(applicationContext.getResource("classpath:/com/ksa/mapper/bbs/bbs.xml"));
//    	 factoryBean.setMapperLocations(applicationContext.getResource("classpath:/com/ksa/mapper/comment/comment.xml"));
    	 return factoryBean;    	 
     }
     
     @Bean
     public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
    	 return new SqlSessionTemplate(sqlSessionFactory);
     }
     
     @Bean
     public String saveDir() {
    	 return new String("c:/upload/");
     }
     

     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
