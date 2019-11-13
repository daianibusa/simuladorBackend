package br.com.simulador.credito.autenticacao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 *
 * @author Daiani
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private ImplementsUserDetailsService userDetailsService;
    
    
    @Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests()
		/*.antMatchers(HttpMethod.GET, "/admin/inicio").permitAll()
		.antMatchers(HttpMethod.GET, "/admin/usuarios").permitAll()
                .antMatchers(HttpMethod.POST, "/admin/usuarios").permitAll()
		.antMatchers(HttpMethod.PUT, "/admin/usuarios").permitAll()
                .antMatchers(HttpMethod.DELETE, "/admin/usuarios").permitAll()
                .antMatchers(HttpMethod.GET, "/admin/modalidades").permitAll()
                .antMatchers(HttpMethod.POST, "/admin/modalidades").permitAll()
                .antMatchers(HttpMethod.PUT, "/admin/modalidades").permitAll()
                .antMatchers(HttpMethod.DELETE, "/admin/modalidades").permitAll()
                .antMatchers(HttpMethod.GET, "/smulador-de-credito").permitAll()
                .antMatchers(HttpMethod.PUT, "/admin/relatorios").permitAll()*/
                 .antMatchers(HttpMethod.GET, "/").permitAll()    
                 .antMatchers(HttpMethod.GET, "/home").permitAll()  
                 .antMatchers(HttpMethod.POST, "/simulador-de-credito").permitAll()  
                 .antMatchers(HttpMethod.GET, "/quem-somos").permitAll()  
               
		.anyRequest().authenticated()
		.and().formLogin().permitAll()
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/materialize/**", "/style/**");
	}
    
}