package app;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Dabin on 2017/10/21.
 */

public class MyApp extends Application{
    public static MyApp mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration aDefault = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(aDefault);
        mInstance = this;

    }
    public static MyApp getInstance() {
        return mInstance;
    }
}
