package com.timely.ProyectoTimely.repository;

import com.timely.ProyectoTimely.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

/**
 * Repository para la tabla "usuario".
 *
 * JpaRepository<Usuario, String>:
 *   - Usuario → la entidad
 *   - String  → tipo de la PK (dni es un String)
 *
 * NUEVO: Optional<T>
 *   Optional es como una "caja" que puede contener un valor o estar vacía.
 *   - Optional.of(usuario)  → tiene un usuario dentro
 *   - Optional.empty()      → no se encontró nada
 *
 *   ¿Para qué sirve? Para evitar NullPointerException.
 *   En vez de: Usuario u = repo.findByEmail("x"); if(u != null) ...
 *   Usas:     Optional<Usuario> u = repo.findByEmail("x"); if(u.isPresent()) ...
 */
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    // Buscar usuario por email
    // Spring genera: SELECT * FROM usuario WHERE email = ?
    Optional<Usuario> findByEmail(String email);

    // Buscar usuarios por tipo
    // Spring genera: SELECT * FROM usuario WHERE user_type = ?
    List<Usuario> findByUserType(com.timely.ProyectoTimely.model.UserType userType);

    // Buscar usuarios de un departamento
    // Spring genera: SELECT * FROM usuario WHERE department = ?
    List<Usuario> findByDepartamentoId(int departamentoId);
}
