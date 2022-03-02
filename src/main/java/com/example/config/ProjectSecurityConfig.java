package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * /myAccount - Secured
     * /myBalance - Secured
     * /myLoans   - Secured
     * /myCards   - Secured
     * /notices   - Not Secured
     * /contact   - Not Secured
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /**
         * Default configuration which will secure all the requests
         * */
        /*
         *     http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
         */

        /**
         *  Custom configuration as per our requirement
         * */
        http.authorizeRequests()
                .antMatchers("/myAccount").authenticated()
                .antMatchers("/myBalance").authenticated()
                .antMatchers("/myLoans").authenticated()
                .antMatchers("/myCards").authenticated()
                .antMatchers("/notices").permitAll()
                .antMatchers("/contact").permitAll()
                .and()
                .formLogin()
                .and()
                .httpBasic();

        /**
         * Configuration to deny all the requests
         * */
        /*
         *  http.authorizeRequests()
         *               .anyRequest().denyAll()
         *               .and()
         *               .formLogin()
         *               .and()
         *               .httpBasic();
         */

        /**
         *  Configuration to permit all the requests
         * */
        /*
         *  http.authorizeRequests()
         *        .anyRequest().permitAll()
         *        .and()
         *        .httpBasic()
         *        .and()
         *        .formLogin();
         */
    }

    /* @Override
     *   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     *       auth.inMemoryAuthentication()
     *               .withUser("alireza").password("123").authorities("test").
     *               and()
     *               .withUser("reza").password("321").authorities("Admin")
     *               .and()
     *             .passwordEncoder(NoOpPasswordEncoder.getInstance());
     * }
     */

    /*  @Override
     * protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     *    InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
     *    UserDetails user1 = User.withUsername("admin").password("123456").authorities("admin").build();
     *    UserDetails user2 = User.withUsername("user").password("123456").authorities("read").build();
     *    userDetailsManager.createUser(user1);
     *    userDetailsManager.createUser(user2);
     *    auth.userDetailsService(userDetailsManager).passwordEncoder(NoOpPasswordEncoder.getInstance());
     * }
     */

    /*  @Bean
     *  protected UserDetailsService userDetailsService(DataSource dataSource) {
     *      return new JdbcUserDetailsManager(dataSource);
     *  }*/

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    /*    @Bean
     * public PasswordEncoder passwordEncoder() {
     *     return new CustomPasswordEncoder();
     *}*/
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
