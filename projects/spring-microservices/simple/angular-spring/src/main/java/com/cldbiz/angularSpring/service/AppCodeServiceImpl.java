package com.cldbiz.angularSpring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cldbiz.angularSpring.common.AfwConstants;
import com.cldbiz.angularSpring.dao.AppCodeRepository;
import com.cldbiz.angularSpring.dto.AppCodeDto;


@Service
@Transactional
public class AppCodeServiceImpl implements AppCodeService {

	@Autowired
	AppCodeRepository appCodeRepo;
	
	@Override
	public List<AppCodeDto> findAll() {
		List<AppCodeDto> appCodeExecMode = new ArrayList<AppCodeDto>();
		
		AppCodeDto appCodeExecModeProd= new AppCodeDto();
		appCodeExecModeProd.setId(1L);
		appCodeExecModeProd.setType(AfwConstants.EXECUTION_MODE);
		appCodeExecModeProd.setCode(AfwConstants.PRODUCTION_MODE);
		appCodeExecModeProd.setValueText("Production");
		appCodeExecModeProd.setDescription("Running application in production");
		appCodeExecModeProd.setDefaultFlag(false);
		appCodeExecModeProd.setUserDefined(true);
		appCodeExecModeProd.setIsActive(true);
		
		appCodeExecMode.add(appCodeExecModeProd);
		
		AppCodeDto appCodeExecModeDev= new AppCodeDto();
		appCodeExecModeDev.setId(2L);
		appCodeExecModeDev.setType(AfwConstants.EXECUTION_MODE);
		appCodeExecModeDev.setCode(AfwConstants.DEVELOPMENT_MODE);
		appCodeExecModeDev.setValueText("Development");
		appCodeExecModeDev.setDescription("Running application in development");
		appCodeExecModeDev.setDefaultFlag(true);
		appCodeExecModeDev.setUserDefined(true);
		appCodeExecModeDev.setIsActive(true);

		appCodeExecMode.add(appCodeExecModeDev);
		
		AppCodeDto appCodeExecModeDTest= new AppCodeDto();
		appCodeExecModeDTest.setId(3L);
		appCodeExecModeDTest.setType(AfwConstants.EXECUTION_MODE);
		appCodeExecModeDTest.setCode(AfwConstants.TEST_MODE);
		appCodeExecModeDTest.setValueText("Testing");
		appCodeExecModeDTest.setDescription("Running application in qa");
		appCodeExecModeDTest.setDefaultFlag(false);
		appCodeExecModeDTest.setUserDefined(true);
		appCodeExecModeDTest.setIsActive(true);

		appCodeExecMode.add(appCodeExecModeDTest);
		
		return appCodeExecMode;
	}

}
