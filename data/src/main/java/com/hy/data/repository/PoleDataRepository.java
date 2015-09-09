package com.hy.data.repository;

import com.example.bean.DomainLine;
import com.example.repository.PoleRepository;

import java.util.List;

import rx.Observable;

/**
 * Created by 24363 on 2015/9/9.
 */
public class PoleDataRepository implements PoleRepository{
    @Override
    public Observable<List<DomainLine>> getAllTower() {
        return null;
    }
}
