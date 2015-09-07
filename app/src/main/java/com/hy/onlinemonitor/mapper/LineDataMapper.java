package com.hy.onlinemonitor.mapper;

import com.example.bean.DomainLine;
import com.hy.onlinemonitor.bean.Line;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LineDataMapper {
    public LineDataMapper() {}

    public static Line transform(DomainLine domainLine) {
        if (null == domainLine) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        Line line = new Line();
        line.setPoleList(PoleDataMapper.transform(domainLine.getTowers()));
        line.setLineName(domainLine.getName());
        if(domainLine.getTowers()!=null){
            line.setPoleList(PoleDataMapper.transform(domainLine.getTowers()));
        }
        return line;
    }

    public static List<Line> transform(Collection<DomainLine> domainLines){
        List<Line> lines = new ArrayList<>();
        Line line;
        for (DomainLine domainLine : domainLines) {
            line = transform(domainLine);
            if (line != null) {
                lines.add(line);
            }
        }

        return lines;
    }
}
