package com.example.repository;

import rx.Observable;

/**
 * Created by 24363 on 2015/8/27.
 */
public interface VideoRepository {
    Observable<String> getVideoUrl();
}
