package com.psbc.wyk.dangjian.interfaces.impl;

import com.psbc.wyk.dangjian.dao.dos.WrongBookDO;
import com.psbc.wyk.dangjian.dao.repo.WrongBookMapper;
import com.psbc.wyk.dangjian.interfaces.WrongBookService;

import com.psbc.wyk.dangjian.interfaces.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author wyk on 2019/02/28
 */
@Service
public class WrongBookServiceImpl extends BaseServiceImpl<WrongBookMapper, WrongBookDO> implements WrongBookService {
}
