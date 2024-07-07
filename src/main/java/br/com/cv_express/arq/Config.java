package br.com.cv_express.arq;

import br.com.cv_express.entities.Usuario;
import br.com.cv_express.repositories.EmpresaRepository;
import br.com.cv_express.repositories.SubmissaoRepository;
import br.com.cv_express.repositories.UsuarioRepository;
import br.com.cv_express.repositories.VagaRepository;
import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class Config {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    VagaRepository vagaRepository;

    @Autowired
    SubmissaoRepository submissaoRepository;

    @Autowired
    private AuthProvider authProvider;

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests((authorize) -> {
            authorize.requestMatchers(mvc.pattern("/empresa**")).hasRole("empresa");
            authorize.requestMatchers(mvc.pattern("/candidato**")).hasRole("candidato");
            authorize.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll();
            authorize.dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll();
            authorize.requestMatchers(mvc.pattern("/login**"), mvc.pattern("/candidato/cadastro"), mvc.pattern("/empresa/cadastro"), mvc.pattern("/js**"),
                    mvc.pattern("/css**"), mvc.pattern("/images**")).permitAll();
            authorize.anyRequest().authenticated();
        });
        http.formLogin(form -> {
            form.loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/login-success", true);
            form.usernameParameter("email").passwordParameter("senha");
            form.failureUrl("/login?error=true");
        });
        http.logout(logout -> {
            logout.logoutUrl("/logout");
            logout.deleteCookies("JSESSIONID");
        });

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

}