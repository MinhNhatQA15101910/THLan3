package bai6.bll;

import bai6.dal.ConstraintDAL;
import bai6.dal.IConstraintDAL;
import bai6.dto.responses.ConstraintListResponse;

public class ConstraintBLL implements IConstraintBLL {
    private final IConstraintDAL _constraintDAL = new ConstraintDAL();

    @Override
    public ConstraintListResponse getAllConstraints() {
        return _constraintDAL.getAllConstraints();
    }
}
