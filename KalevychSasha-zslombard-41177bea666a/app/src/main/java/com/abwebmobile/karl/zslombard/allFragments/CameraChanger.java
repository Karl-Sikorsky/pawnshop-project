package com.abwebmobile.karl.zslombard.allFragments;

/**
 * Created by Karl on 20.02.2018.
 */
//Интерфейс замены камеры на фронтальную при неработающей задней, используется в QR-сканнере
public interface CameraChanger {
    void changeCamera(int frontCam);
}
