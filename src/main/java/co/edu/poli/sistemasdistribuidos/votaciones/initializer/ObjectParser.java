package co.edu.poli.sistemasdistribuidos.votaciones.initializer;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Collections;
import java.util.List;

@Component
public class ObjectParser {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public <T> List<T> obtenerListaObjetos(Class<T> type, String fileName) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            File file = new ClassPathResource(fileName).getFile();
            MappingIterator<T> readValues =
                    mapper.readerFor(type).with(bootstrapSchema).readValues(file);
            return readValues.readAll();
        } catch (Exception e) {
            logger.error("Error occurred while loading file " + fileName, e);
            return Collections.emptyList();
        }
    }

    public List<String[]> obtenerListaCadenas(String fileName) {
        try {
            CsvMapper mapper = new CsvMapper();
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withSkipFirstDataRow(true);
            mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
            File file = new ClassPathResource(fileName).getFile();
            MappingIterator<String[]> readValues =
                    mapper.readerFor(String[].class).with(bootstrapSchema).readValues(file);
            return readValues.readAll();
        } catch (Exception e) {
            logger.error(
                    "Error occurred while loading file = " + fileName, e);
            return Collections.emptyList();
        }
    }
}
