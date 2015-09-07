package com.hy.data.entity.mapper;

import com.example.bean.DomainLine;
import com.hy.data.entity.LineEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/9/2.
 */
public class LineDataMapper {
    public LineDataMapper() {
    }

    public DomainLine transform(LineEntity lineEntity) {
        DomainLine domainLine = null;

        if (lineEntity != null) {
            domainLine = new DomainLine();
            domainLine.setName(lineEntity.getName());
            domainLine.setTowers(PoleDataMapper.transform(lineEntity.getPoleSet()));
            if(lineEntity.getLineSn() != 0){
                domainLine.setLineSn(lineEntity.getLineSn());
            }
        }
        return domainLine;
    }

    public List<DomainLine> transform(Collection<LineEntity> adminLineEntities) {
        List<DomainLine> domainLines = new ArrayList<>();
        DomainLine domainLine;
        for (LineEntity lineEntity : adminLineEntities) {
            domainLine = transform(lineEntity);
            if (domainLine != null) {
                domainLines.add(domainLine);
            }
        }
        return domainLines;
    }

}
