package repository;

import model.Employee;

import java.io.File;
import java.util.List;

public interface Repository<T, C> {
    final String path = new File("src").getAbsolutePath();

    void writeToFile(C enties);

    C readFromFile();
}
