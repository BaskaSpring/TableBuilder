package ru.mfn.TableBuilder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mfn.TableBuilder.model.core.Table;

import java.util.Optional;

@Repository
public interface TableRepository extends JpaRepository<Table,String> {

    Optional<Table> findByName(String tableName);
}
