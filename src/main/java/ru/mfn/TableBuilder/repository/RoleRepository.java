package ru.mfn.TableBuilder.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ru.mfn.TableBuilder.model.auth.ERole;
import ru.mfn.TableBuilder.model.auth.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {


  Optional<Role> findByName(String name);

  Optional<Role> findById(Long id);

  @Query("select x from Role as x where x.enabled=true and x.eRole<>'ADMIN'")
  List<Role> findAll();

  @Query("select x from Role as x where x.eRole=:eRole")
  List<Role> findByRole(ERole eRole);


}
