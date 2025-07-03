package repository;

import model.Contract;

import java.util.Queue;

public interface IContactRepository extends Repository<Contract, Queue<Contract>> {
    @Override
    void writeToFile(Queue<Contract> enties);

    @Override
    Queue<Contract> readFromFile();
}
