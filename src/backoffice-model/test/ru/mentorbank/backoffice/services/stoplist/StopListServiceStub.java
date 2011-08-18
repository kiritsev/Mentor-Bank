package ru.mentorbank.backoffice.services.stoplist;

import ru.mentorbank.backoffice.model.stoplist.JuridicalStopListRequest;
import ru.mentorbank.backoffice.model.stoplist.PhysicalStopListRequest;
import ru.mentorbank.backoffice.model.stoplist.StopListInfo;
import ru.mentorbank.backoffice.model.stoplist.StopListStatus;

public class StopListServiceStub implements StopListService {

	public static final String INN_FOR_OK_STATUS = "1111111111111";
	public static final String INN_FOR_STOP_STATUS = "22222222222222";
	public static final String INN_FOR_ASKSECURITY_STATUS = "33333333333333";

	public static final String DOCUMENT_NUMBER_FOR_OK_STATUS = "111111";
	public static final String DOCUMENT_SERIES_FOR_OK_STATUS = "1111";

	public static final String DOCUMENT_NUMBER_FOR_STOP_STATUS = "222222";
	public static final String DOCUMENT_SERIES_FOR_STOP_STATUS = "2222";

	public static final String DOCUMENT_NUMBER_FOR_ASKSECURITY_STATUS = "333333";
	public static final String DOCUMENT_SERIES_FOR_ASKSECURITY_STATUS = "3333";

	@Override
	public StopListInfo getJuridicalStopListInfo(
			JuridicalStopListRequest request) {
		StopListInfo stopListInfo = new StopListInfo();
		stopListInfo.setComment("�����������");
		if (INN_FOR_OK_STATUS.equals(request.getInn())) {
			stopListInfo.setStatus(StopListStatus.OK);
		} else if (INN_FOR_STOP_STATUS.equals(request.getInn())) {
			stopListInfo.setStatus(StopListStatus.STOP);
		} else {
			stopListInfo.setStatus(StopListStatus.ASKSECURITY);
		}
		return stopListInfo;
	}

	@Override
	public StopListInfo getPhysicalStopListInfo(PhysicalStopListRequest request) {
		// TODO: �����������

		StopListInfo stopListInfo = new StopListInfo();
		stopListInfo.setComment("�����������");

		if (DOCUMENT_NUMBER_FOR_OK_STATUS.equals(request.getDocumentNumber())
				&& DOCUMENT_SERIES_FOR_OK_STATUS.equals(request
						.getDocumentSeries())) {
			stopListInfo.setStatus(StopListStatus.OK);
		} else if (DOCUMENT_NUMBER_FOR_STOP_STATUS.equals(request
				.getDocumentNumber())
				&& DOCUMENT_SERIES_FOR_STOP_STATUS.equals(request
						.getDocumentSeries())) {
			stopListInfo.setStatus(StopListStatus.STOP);
		} else {
			stopListInfo.setStatus(StopListStatus.ASKSECURITY);
		}

		return stopListInfo;
	}
}
