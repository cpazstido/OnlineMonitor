package com.example.repository;

import rx.Observable;

public interface VideoRepository {
    Observable<String> getVideoUrl();

    Observable<String> leftControl();

    Observable<String> rightControl();

    Observable<String> upControl();

    Observable<String> downControl();

    Observable<String> getStatus();

    Observable<String> openPower();

    Observable<String> openCameraPower();

    Observable<String> changePtz(boolean isAuto);

    Observable<String> stopPtz();

    Observable<String> stopPlay();
}
