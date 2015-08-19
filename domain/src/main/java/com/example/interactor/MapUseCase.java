package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.repository.MapRepository;

import rx.Observable;

/**
 * Created by 24363 on 2015/8/19.
 */
public class MapUseCase extends UseCase{

    final private MapRepository mapRepository;

    public MapUseCase(PostExecutionThread postExecutionThread, MapRepository mapRepository) {
        super(postExecutionThread);
        this.mapRepository = mapRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.mapRepository.mapList();
    }
}
