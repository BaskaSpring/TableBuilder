package ru.mfn.TableBuilder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.mfn.TableBuilder.model.core.Table;

import java.util.Optional;

@Repository
public interface SupportTableRepository extends JpaRepository<Table,String> {

    @Query("select x from Table as x where x.tableName=:tableName")
    Optional<Table> findByName(String tableName);
}
