package com.example.repository;

import rx.Observable;

public interface VideoRepository {
    Observable<String> getVideoUrl();
}
