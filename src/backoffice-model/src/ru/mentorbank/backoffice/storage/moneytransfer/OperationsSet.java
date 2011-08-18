package ru.mentorbank.backoffice.storage.moneytransfer;

import java.util.HashSet;
import java.util.Set;

import ru.mentorbank.backoffice.dao.OperationDao;
import ru.mentorbank.backoffice.dao.exception.OperationDaoException;
import ru.mentorbank.backoffice.model.Operation;

public class OperationsSet implements OperationDao {

	private Set<Operation> operations;

	public OperationsSet() {
		operations = new HashSet<Operation>(1);
	}

	@Override
	public Set<Operation> getOperations() throws OperationDaoException {
		return this.operations;
	}

	@Override
	public void saveOperation(Operation operation) throws OperationDaoException {
		this.operations.add(operation);
	}
}
