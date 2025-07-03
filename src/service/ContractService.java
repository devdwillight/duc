package service;

import model.Contract;
import model.Customer;
import repository.ContrastRepository;
import utils.CustomException;

import java.util.ArrayList;
import java.util.Queue;

public class ContractService implements IContractService {
    ContrastRepository contrastRepository = new ContrastRepository();
    @Override
    public Contract findById(String id) {
        Queue<Contract> contracts = contrastRepository.readFromFile(); // thường là LinkedList
        for (Contract contract : contracts) {
            if (contract.getContractID().equals(id)) {
                return contract;
            }
        }
        return null;
    }


    @Override
    public void add(Contract entity) throws CustomException {

    }

    @Override
    public void save() {

    }
    public Queue<Contract> findAll() {
        Queue<Contract> contracts = contrastRepository.readFromFile();
        return contracts;
    }
    public void updateContract(Contract updated) {
        contrastRepository.updateContract(updated);
    }
}
