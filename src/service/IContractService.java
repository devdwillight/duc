package service;

import model.Contract;
import utils.CustomException;

public interface IContractService extends Service<Contract> {
    @Override
    Contract findById(String id);

    @Override
    void add(Contract entity) throws CustomException;
}
