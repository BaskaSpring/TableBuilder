package ru.mfn.TableBuilder.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mfn.TableBuilder.payload.exception.LengthStringNotValidException;
import ru.mfn.TableBuilder.payload.table.Request.ColumnInfo;
import ru.mfn.TableBuilder.payload.table.Request.CreateTableRequest;
import ru.mfn.TableBuilder.repository.TableRepository;

import java.util.List;



@Repository
@RequiredArgsConstructor
public class TableRepositoryImpl implements TableRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void create(CreateTableRequest createTableRequest) {
        jdbcTemplate.execute(
                createTableQuery(createTableRequest.getName(), createTableRequest.getColumnInfos())
        );
    }

    @SneakyThrows
    private String createTableQuery(String name, List<ColumnInfo> columnInfos) {
        String columns = "";
        String type = "";
        for (int i = 0; i <columnInfos.size(); i++) {
            ColumnInfo columnInfo = columnInfos.get(i);
            switch (columnInfo.getType()){
                case ДАТА:
                    type="TIMESTAMP";
                    break;
                case ЧИСЛО:
                    type="DECIMAL(20,2)";
                    break;
                case БУЛЕВО:
                    type="BOOLEAN";
                    break;
                case СТРОКА:
                    try {
                        int length = columnInfo.getLength();
                        if (length<=0){
                            throw new LengthStringNotValidException("Error: length not valid!");
                        }
                        type="VARCHAR("+length+")";
                    } catch (NullPointerException ex){
                        throw new LengthStringNotValidException("Error: length string not be null or empty!");
                    }
                default:
            }
            columns=columns+columnInfo.getTitle()+" "+type+", ";
        }
        return "create table " + name + " (id VARCHAR(50) NOT NULL," + columns + "primary key (id))";
    }
}
