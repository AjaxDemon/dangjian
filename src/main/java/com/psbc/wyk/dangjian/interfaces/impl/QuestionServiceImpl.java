package com.psbc.wyk.dangjian.interfaces.impl;

import com.psbc.wyk.dangjian.dao.dos.QuestionDO;
import com.psbc.wyk.dangjian.dao.repo.QuestionMapper;
import com.psbc.wyk.dangjian.interfaces.QuestionService;

import com.psbc.wyk.dangjian.interfaces.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author wyk on 2019/02/28
 */
@Service
public class QuestionServiceImpl extends BaseServiceImpl<QuestionMapper, QuestionDO> implements QuestionService {
}
