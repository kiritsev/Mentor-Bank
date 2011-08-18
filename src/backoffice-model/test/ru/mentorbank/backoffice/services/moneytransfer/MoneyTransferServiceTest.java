package ru.mentorbank.backoffice.services.moneytransfer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ru.mentorbank.backoffice.dao.OperationDao;
import ru.mentorbank.backoffice.dao.exception.OperationDaoException;
import ru.mentorbank.backoffice.model.Operation;
import ru.mentorbank.backoffice.model.transfer.JuridicalAccountInfo;
import ru.mentorbank.backoffice.model.transfer.TransferRequest;
import ru.mentorbank.backoffice.services.accounts.AccountService;
import ru.mentorbank.backoffice.services.accounts.AccountServiceBean;
import ru.mentorbank.backoffice.services.moneytransfer.exceptions.TransferException;
import ru.mentorbank.backoffice.services.stoplist.StopListServiceStub;
import ru.mentorbank.backoffice.storage.moneytransfer.OperationsSet;
import ru.mentorbank.backoffice.test.AbstractSpringTest;

public class MoneyTransferServiceTest extends AbstractSpringTest {

	@Autowired
	private MoneyTransferServiceBean moneyTransferService;
	private AccountService mockedAccountService;

	private JuridicalAccountInfo srcAccountInfo;
	private TransferRequest transferRequest;
	private JuridicalAccountInfo dstAccountInfo;

	@Before
	public void setUp() {
		srcAccountInfo = new JuridicalAccountInfo();
		srcAccountInfo.setAccountNumber("111111111111111");
		srcAccountInfo.setInn(StopListServiceStub.INN_FOR_OK_STATUS);

		dstAccountInfo = new JuridicalAccountInfo();
		dstAccountInfo.setAccountNumber("222222222222222");
		dstAccountInfo.setInn(StopListServiceStub.INN_FOR_OK_STATUS);

		transferRequest = new TransferRequest();
		transferRequest.setSrcAccount(srcAccountInfo);
		transferRequest.setDstAccount(dstAccountInfo);

		mockedAccountService = mock(AccountServiceBean.class);
		// Dynamic Stub
		when(mockedAccountService.verifyBalance(dstAccountInfo)).thenReturn(
				true);
		moneyTransferService.setAccountService(mockedAccountService);

	}

	@Test
	public void transfer() throws TransferException, OperationDaoException {
		// fail("not implemented yet");

		// TODO: Необходимо протестировать, что для хорошего перевода всё
		// работает и вызываются все необходимые методы сервисов
		// Далее следует закоментированная закотовка
		// StopListService mockedStopListService =
		// mock(StopListServiceStub.class);
		// AccountService mockedAccountService = mock(AccountServiceBean.class);
		//
		// moneyTransferService.transfer(null);
		//
		// verify(mockedStopListService).getJuridicalStopListInfo(null);
		// verify(mockedAccountService).verifyBalance(null);

		// ####################
		/*
		 * StopListService mockedStopListService =
		 * mock(StopListServiceStub.class);
		 * moneyTransferService.setStopListService(mockedStopListService);
		 */
		OperationDao operationDao = new OperationsSet();
		moneyTransferService.setOperationDao(operationDao);

		assertTrue(operationDao.getOperations().isEmpty());

		moneyTransferService.transfer(transferRequest);

		assertFalse(operationDao.getOperations().isEmpty());
		assertThat(operationDao.getOperations().size(), is(1));

		Operation ops[] = {};
		ops = operationDao.getOperations().toArray(ops);
		assertThat(ops[0].getSrcAccount().getAccountNumber(),
				is(srcAccountInfo.getAccountNumber()));
		assertThat(ops[0].getDstAccount().getAccountNumber(),
				is(dstAccountInfo.getAccountNumber()));

		// verify(mockedStopListService).getJuridicalStopListInfo(null);
		// verify(mockedAccountService).verifyBalance(null);
	}
}
