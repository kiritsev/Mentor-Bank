package ru.mentorbank.backoffice.services.moneytransfer;

import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import ru.mentorbank.backoffice.dao.OperationDao;
import ru.mentorbank.backoffice.dao.exception.OperationDaoException;
import ru.mentorbank.backoffice.model.stoplist.JuridicalStopListRequest;
import ru.mentorbank.backoffice.model.stoplist.StopListInfo;
import ru.mentorbank.backoffice.model.stoplist.StopListStatus;
import ru.mentorbank.backoffice.model.transfer.JuridicalAccountInfo;
import ru.mentorbank.backoffice.model.transfer.TransferRequest;
import ru.mentorbank.backoffice.services.accounts.AccountService;
import ru.mentorbank.backoffice.services.accounts.AccountServiceBean;
import ru.mentorbank.backoffice.services.moneytransfer.exceptions.TransferException;
import ru.mentorbank.backoffice.services.stoplist.StopListServiceStub;
import ru.mentorbank.backoffice.storage.moneytransfer.OperationsSet;
import ru.mentorbank.backoffice.test.AbstractSpringTest;

public class MoneyTransferServiceTest extends AbstractSpringTest {

	// @Autowired
	private MoneyTransferServiceBean moneyTransferService;
	private StopListServiceStub mockedStopListService;
	private AccountService mockedAccountService;
	private JuridicalAccountInfo srcAccountInfo;
	private TransferRequest transferRequest;
	private JuridicalAccountInfo dstAccountInfo;

	@Before
	public void setUp() {
		moneyTransferService = new MoneyTransferServiceBean();

		srcAccountInfo = new JuridicalAccountInfo();
		srcAccountInfo.setInn(StopListServiceStub.INN_FOR_OK_STATUS);

		dstAccountInfo = new JuridicalAccountInfo();
		dstAccountInfo.setInn(StopListServiceStub.INN_FOR_OK_STATUS);

		transferRequest = new TransferRequest();
		transferRequest.setSrcAccount(srcAccountInfo);
		transferRequest.setDstAccount(dstAccountInfo);

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

		setStopListService();

		setAccountService();

		setOperationDao();

		moneyTransferService.transfer(transferRequest);

		JuridicalStopListRequest request2 = new JuridicalStopListRequest();
		request2.setInn(srcAccountInfo.getInn());

		verify(mockedStopListService, times(2)).getJuridicalStopListInfo(
				refEq(request2));
		verify(mockedAccountService).verifyBalance(dstAccountInfo);
	}

	private void setStopListService() {
		mockedStopListService = mock(StopListServiceStub.class);
		JuridicalStopListRequest request = new JuridicalStopListRequest();
		request.setInn(srcAccountInfo.getInn());
		JuridicalStopListRequest request1 = new JuridicalStopListRequest();
		request1.setInn(dstAccountInfo.getInn());

		StopListInfo stopListInfo_OK = new StopListInfo();
		stopListInfo_OK.setStatus(StopListStatus.OK);

		when(mockedStopListService.getJuridicalStopListInfo(refEq(request)))
				.thenReturn(stopListInfo_OK);
		when(mockedStopListService.getJuridicalStopListInfo(refEq(request1)))
				.thenReturn(stopListInfo_OK);

		moneyTransferService.setStopListService(mockedStopListService);
	}

	private void setAccountService() {
		mockedAccountService = mock(AccountServiceBean.class);
		when(mockedAccountService.verifyBalance(dstAccountInfo)).thenReturn(
				true);
		moneyTransferService.setAccountService(mockedAccountService);
	}

	private void setOperationDao() {
		OperationDao operationDao = new OperationsSet();
		moneyTransferService.setOperationDao(operationDao);
	}
}
