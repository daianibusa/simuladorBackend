package br.com.simulador.credito.autenticacao;

import br.com.simulador.credito.entity.UsuarioEntity;
import br.com.simulador.credito.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daiani
 */
@Repository
public class ImplementsUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        UsuarioEntity usuario = usuarioRepository.findByEmail(email);
		
		if(usuario == null){
			throw new UsernameNotFoundException("Usuario não encontrado!");
		}
		return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());
	}
    }