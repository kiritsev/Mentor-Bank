package ru.mentorbank.backoffice.services.stoplist;

import java.util.HashMap;
import java.util.Map;

import ru.mentorbank.backoffice.model.stoplist.JuridicalStopListRequest;
import ru.mentorbank.backoffice.model.stoplist.PhysicalStopListRequest;
import ru.mentorbank.backoffice.model.stoplist.StopListInfo;
import ru.mentorbank.backoffice.model.stoplist.StopListStatus;

public class StopListServiceStub implements StopListService {

	public static final String INN_FOR_OK_STATUS = "1111111111111";
	public static final String INN_FOR_STOP_STATUS = "22222222222222";
	public static final String INN_FOR_ASKSECURITY_STATUS = "33333333333333";

	public static final Map<String, String> REQUEST_FOR_PHYSICAL_OK_STATUS = new HashMap<String, String>() {
		{
			put("documentNumber", "111111");
			put("documentSeries", "1111");
			put("firstname", "John");
			put("lastname", "Smith");
			put("middlename", "Jr");
		}
	};

	public static final Map<String, String> REQUEST_FOR_PHYSICAL_STOP_STATUS = new HashMap<String, String>() {
		{
			put("documentNumber", "222222");
			put("documentSeries", "2222");
			put("firstname", "Josh");
			put("lastname", "Barnett");
			put("middlename", "Jr");
		}
	};

	@Override
	public StopListInfo getJuridicalStopListInfo(
			JuridicalStopListRequest request) {
		StopListInfo stopListInfo = new StopListInfo();
		stopListInfo.setComment("Комментарий");
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
		// TODO: Реализовать

		StopListInfo stopListInfo = new StopListInfo();
		stopListInfo.setComment("Комментарий");

		if (REQUEST_FOR_PHYSICAL_OK_STATUS.get("documentNumber").equals(
				request.getDocumentNumber())
				&& REQUEST_FOR_PHYSICAL_OK_STATUS.get("documentSeries").equals(
						request.getDocumentSeries())
				&& REQUEST_FOR_PHYSICAL_OK_STATUS.get("firstname").equals(
						request.getFirstname())
				&& REQUEST_FOR_PHYSICAL_OK_STATUS.get("lastname").equals(
						request.getLastname())
				&& REQUEST_FOR_PHYSICAL_OK_STATUS.get("middlename").equals(
						request.getMiddlename())) {
			stopListInfo.setStatus(StopListStatus.OK);
		} else if (REQUEST_FOR_PHYSICAL_STOP_STATUS.get("documentNumber")
				.equals(request.getDocumentNumber())
				&& REQUEST_FOR_PHYSICAL_STOP_STATUS.get("documentSeries")
						.equals(request.getDocumentSeries())
				&& REQUEST_FOR_PHYSICAL_STOP_STATUS.get("firstname").equals(
						request.getFirstname())
				&& REQUEST_FOR_PHYSICAL_STOP_STATUS.get("lastname").equals(
						request.getLastname())
				&& REQUEST_FOR_PHYSICAL_STOP_STATUS.get("middlename").equals(
						request.getMiddlename())) {
			stopListInfo.setStatus(StopListStatus.STOP);
		} else {
			stopListInfo.setStatus(StopListStatus.ASKSECURITY);
		}

		return stopListInfo;
	}
}
