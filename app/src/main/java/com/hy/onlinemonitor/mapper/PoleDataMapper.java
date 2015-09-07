package com.hy.onlinemonitor.mapper;

import com.example.bean.DomainPole;
import com.hy.onlinemonitor.bean.Pole;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PoleDataMapper {
    public PoleDataMapper() {}

    public static Pole transform(DomainPole domainPole) {
        if (null == domainPole) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        Pole pole = new Pole();
        pole.setPoleSn(domainPole.getPoleSn());
        pole.setPoleName(domainPole.getPoleName());
        if(domainPole.getEquipmentList() !=null){
            EquipmentDataMapper equipmentDataMapper = new EquipmentDataMapper();
            pole.setEquipmentList(equipmentDataMapper.transform(domainPole.getEquipmentList()));
        }
        return pole;
    }

    public static List<Pole> transform(Collection<DomainPole> domainPoles){
        List<Pole> poles = new ArrayList<>();
        Pole pole;
        for (DomainPole domainPole : domainPoles) {
            pole = transform(domainPole);
            if (pole != null) {
                poles.add(pole);
            }
        }
        return poles;
    }
}
