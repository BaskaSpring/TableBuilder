package ru.mfn.TableBuilder.repository.impl;

import lombok.RequiredArgsConstructor;
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

    private String getColumnQuery(List<ColumnInfo> columnInfos) throws LengthStringNotValidException {
        String columns = "";
        String type = "";
        for (int i = 0; i < columnInfos.size(); i++) {
            ColumnInfo columnInfo = columnInfos.get(i);
            switch (columnInfo.getType()) {
                case ДАТА:
                    type = "TIMESTAMP";
                    break;
                case ЧИСЛО:
                    type = "DECIMAL(20,2)";
                    break;
                case БУЛЕВО:
                    type = "BOOLEAN";
                    break;
                case СТРОКА:
                    try {
                        int length = columnInfo.getLength();
                        if (length <= 0) {
                            throw new LengthStringNotValidException("Error: length not valid!");
                        }
                        type = "VARCHAR(" + length + ")";
                    } catch (NullPointerException | LengthStringNotValidException ex) {
                        throw new LengthStringNotValidException("Error: length string not be null or empty!");
                    }
                default:
            }
            columns = columns + columnInfo.getTitle() + " " + type + ", ";
        }
        return columns;
    }

    @Override
    public void create(CreateTableRequest createTableRequest) throws LengthStringNotValidException {
        String columns = getColumnQuery(createTableRequest.getColumnInfos());
        jdbcTemplate.execute(createMainTableQuery(createTableRequest.getName(),createTableRequest.getDivision()));
        jdbcTemplate.execute(createTableQuery(createTableRequest.getName(),columns));
        jdbcTemplate.execute(createStoryTableQuery(createTableRequest.getName(), columns));
    }

    private String createMainTableQuery(String name,Boolean div) {
        if (div) {
            return "create table " + name.toUpperCase() + "_main (id VARCHAR(50) NOT NULL,DIVISION VARCHAR(40), DELETED BOOLEAN,OWNER VARCHAR(40), TIMESTAMP TIMESTAMP, primary key (id))";
        }
        return "create table " + name.toUpperCase() + "_main (id VARCHAR(50) NOT NULL, DELETED BOOLEAN,OWNER VARCHAR(40), TIMESTAMP TIMESTAMP, primary key (id))";
    }

    private String createStoryTableQuery(String name, String columns) {
        return "create table " + name.toUpperCase() + "_story (id serial, number VARCHAR(40),TIMESTAMP TIMESTAMP, " + columns + "primary key (id))";
    }

    private String createTableQuery(String name, String columns) {
        return "create table " + name.toUpperCase() + " (id serial , number VARCHAR(40), " + columns + "primary key (id))";
    }
}
